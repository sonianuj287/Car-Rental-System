import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UserView {

	public JFrame frame;
	private JTextField textField;
	private JFrame pFrame;
	private ConnectDB con;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserView window = new UserView();
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
	public UserView(JFrame frame) {
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
		frame.setBounds(100, 100, 440, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(12, 12, 416, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setForeground(new Color(255, 255, 255));
		panel.add(lblUsers, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 52, 416, 416);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		try {
			Statement st = con.con.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM user");
			while(res.next())
				textArea.setText(textArea.getText()+res.getString("uid")+" : "+res.getString("name")+", "+res.getString("email")+", "+res.getString("phone")+", "+res.getString("dlnumber")+"\n");
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
		btnOk.setBounds(12, 490, 100, 25);
		frame.getContentPane().add(btnOk);
		
		textField = new JTextField();
		textField.setBounds(150, 495, 166, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(textField.getText());
					Statement st = con.con.createStatement();
					st.executeUpdate("DELETE From history WHERE uid = \'"+id+"\'");
					st.executeUpdate("DELETE from user WHERE uid = \'"+id+"\'");
					JOptionPane.showMessageDialog(frame, "User Removed!");
				}catch(Exception x) {
					JOptionPane.showMessageDialog(frame, "Invalid ID!");
				}
				
			}
		});
		btnRemove.setBounds(326, 490, 100, 25);
		frame.getContentPane().add(btnRemove);
		
		JLabel lblUserId = new JLabel("User ID :");
		lblUserId.setBounds(150, 480, 66, 15);
		frame.getContentPane().add(lblUserId);
	}
}
