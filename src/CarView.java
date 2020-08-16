import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CarView {

	public JFrame frame;
	private JFrame pFrame;
	private ConnectDB con;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CarView window = new CarView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public CarView(JFrame frame) {
		pFrame = frame;
		try {
			con = new ConnectDB();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(12, 12, 426, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCars = new JLabel("Cars");
		lblCars.setHorizontalAlignment(SwingConstants.CENTER);
		lblCars.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCars.setForeground(new Color(255, 255, 255));
		panel.add(lblCars, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 426, 423);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 15));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		Statement st;
		
		try {
			st = con.con.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM cars");
			while(res.next())
				textArea.setText(textArea.getText()+res.getString("id")+" : "+res.getString("brand")+", "+res.getString("model")+"("+res.getString("color")+") : "+res.getString("number")+" - "+res.getString("rate")+" /- per day\n");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(176, 483, 114, 25);
		frame.getContentPane().add(btnOk);
	}
}
