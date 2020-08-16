import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UserPage {

	public JFrame frame;
	private JFrame pFrame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private ConnectDB con;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserPage window = new UserPage();
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
	public UserPage(JFrame frame) {
		pFrame = frame;
		initialize();
		try {
			con = new ConnectDB();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Datebase Connection Problem!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(pFrame);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Login", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setBounds(12, 0, 411, 40);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCustomerLogin = new JLabel("Customer Login");
		panel_2.add(lblCustomerLogin);
		lblCustomerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerLogin.setForeground(Color.WHITE);
		lblCustomerLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 16));
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(0, 0, 102));
		panel_2.add(button, BorderLayout.EAST);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(12, 163, 100, 20);
		panel.add(lblEmail);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 193, 404, 25);
		panel.add(textField);
		
		JLabel label_2 = new JLabel("Password :");
		label_2.setBounds(12, 238, 100, 20);
		panel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(12, 268, 404, 25);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement st = con.con.createStatement();
					ResultSet res = st.executeQuery("SELECT * FROM user WHERE email = \'"+textField.getText()+"\' AND pswd = \'"+passwordField.getText()+"\'");
					if(res.next()) {
						User user = new User(Integer.parseInt(res.getString("uid")), res.getString("name"), res.getString("email"), res.getString("phone"), res.getString("idType"), res.getString("idNumber"), res.getString("dlNumber"));
						new HomePage(frame, user).frame.setVisible(true);
						textField.setText("");
						passwordField.setText("");
						frame.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(frame, "Invalid Email or Password!", "Oops!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Datebase Connection Problem!", "Errot", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLogin.setBounds(167, 325, 114, 25);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Register", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 102));
		panel_3.setBounds(12, 12, 411, 40);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Customer Login");
		panel_3.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 16));
		button_1.setBackground(new Color(0, 0, 102));
		panel_3.add(button_1, BorderLayout.EAST);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(12, 55, 100, 20);
		panel_1.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 75, 404, 25);
		panel_1.add(textField_1);
		
		JLabel label_1 = new JLabel("Email :");
		label_1.setBounds(12, 110, 100, 20);
		panel_1.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(12, 130, 404, 25);
		panel_1.add(textField_2);
		
		JLabel lblPhone = new JLabel("Phone Number :");
		lblPhone.setBounds(12, 165, 180, 20);
		panel_1.add(lblPhone);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(12, 185, 404, 25);
		panel_1.add(textField_3);
		
		JLabel lblIdType = new JLabel("Id Type :");
		lblIdType.setBounds(12, 220, 180, 20);
		panel_1.add(lblIdType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Adhaar", "Voter ID", "College ID", "PAN Card", "Passport"}));
		comboBox.setBounds(12, 240, 404, 25);
		panel_1.add(comboBox);
		
		JLabel lblIdNumber = new JLabel("Id Number :");
		lblIdNumber.setBounds(12, 275, 180, 20);
		panel_1.add(lblIdNumber);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(12, 295, 404, 25);
		panel_1.add(textField_4);
		
		JLabel lblDrivingLicenseNumber = new JLabel("Driving License Number :");
		lblDrivingLicenseNumber.setBounds(12, 330, 180, 20);
		panel_1.add(lblDrivingLicenseNumber);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(12, 350, 404, 25);
		panel_1.add(textField_5);
		
		JLabel label_3 = new JLabel("Password :");
		label_3.setBounds(12, 385, 100, 20);
		panel_1.add(label_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(12, 405, 404, 25);
		panel_1.add(passwordField_1);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement st = con.con.createStatement();
					st.executeUpdate("INSERT INTO user (name, email, phone, idType, idNumber, dlNumber, pswd) VALUES (\'"+textField_1.getText()+"\', \'"+textField_2.getText()+"\', \'"+textField_3.getText()+"\', \'"+comboBox.getSelectedItem().toString()+"\', \'"+textField_4.getText()+"\', \'"+textField_5.getText()+"\', \'"+passwordField_1.getText()+"\')");
					JOptionPane.showMessageDialog(frame, "Registration Successfull!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Email already exists!", "Oops!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRegister.setBounds(166, 445, 114, 25);
		panel_1.add(btnRegister);
	}
}
