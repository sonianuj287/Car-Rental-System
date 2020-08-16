import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Admin {

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
//					Admin window = new Admin();
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
	public Admin(JFrame frame) {
		pFrame = frame;
		initialize();
		try {
			con = new ConnectDB();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 440, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(pFrame);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(12, 12, 411, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Admin Login");
		label.setBounds(0, 0, 364, 40);
		panel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(291, 0, 120, 40);
		panel.add(btnLogOut);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLogOut.setBackground(new Color(0, 0, 102));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 52, 416, 456);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnAddNewCar = new JButton("Add New Car");
		btnAddNewCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CarEntry(frame).frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAddNewCar.setBounds(38, 116, 146, 35);
		panel_1.add(btnAddNewCar);
		
		JButton btnRemoveCar = new JButton("Remove Car");
		btnRemoveCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(frame, "Enter Car Id: ");
				try {
					Statement st = con.con.createStatement();
					st.executeUpdate("DELETE FROM cars WHERE id = \'"+id+"\'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRemoveCar.setBounds(225, 116, 146, 35);
		panel_1.add(btnRemoveCar);
		
		JButton btnDisplayCar = new JButton("Display Car");
		btnDisplayCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CarView(frame).frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnDisplayCar.setBounds(38, 207, 146, 35);
		panel_1.add(btnDisplayCar);
		
		JButton btnDisplayUsers = new JButton("Display Users");
		btnDisplayUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserView(frame).frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnDisplayUsers.setBounds(225, 207, 146, 35);
		panel_1.add(btnDisplayUsers);
	}
}
