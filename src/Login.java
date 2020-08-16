import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;
	private JTextField txtUname;
	private JPasswordField pwdPswd;
	private ConnectDB con;
	private JFrame pFrame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login window = new Login();
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
	public Login(JFrame frame) {
		pFrame = frame;
		initialize();
		try {
			con = new ConnectDB();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Datebase Connection Problem!", "Errot", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 440, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(pFrame);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setBounds(12, 12, 411, 40);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setForeground(new Color(255, 255, 255));
		panel_2.add(lblAdminLogin, BorderLayout.CENTER);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button_1.setFont(new Font("Dialog", Font.BOLD, 16));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(0, 0, 102));
		panel_2.add(button_1, BorderLayout.EAST);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(12, 175, 100, 20);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(12, 250, 100, 20);
		panel.add(lblPassword);
		
		txtUname = new JTextField();
		txtUname.setBounds(12, 205, 404, 25);
		panel.add(txtUname);
		txtUname.setColumns(10);
		
		pwdPswd = new JPasswordField();
		pwdPswd.setBounds(12, 280, 404, 25);
		panel.add(pwdPswd);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Statement st = con.con.createStatement();
					ResultSet res = st.executeQuery("SELECT * FROM admins WHERE uname = \'"+txtUname.getText()+"\' AND password = \'"+pwdPswd.getText()+"\'");
					if(res.next()) {
						new Admin(frame).frame.setVisible(true);
						txtUname.setText("");
						pwdPswd.setText("");
						frame.setVisible(false);
					}else
						JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Oops!", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Database connection problem!", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		button.setBounds(154, 346, 114, 25);
		panel.add(button);
	}
}
