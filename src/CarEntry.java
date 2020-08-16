import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CarEntry {

	public JFrame frame;
	private JFrame pFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private ConnectDB con;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CarEntry window = new CarEntry();
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
	public CarEntry(JFrame frame) {
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
		frame.setBounds(100, 100, 440, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(pFrame);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(17, 12, 411, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCarRegistration = new JLabel("Car Registration");
		lblCarRegistration.setBounds(0, 0, 364, 40);
		panel.add(lblCarRegistration);
		lblCarRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarRegistration.setForeground(Color.WHITE);
		lblCarRegistration.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnLogOut = new JButton("<");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(316, 0, 95, 40);
		panel.add(btnLogOut);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Dialog", Font.BOLD, 12));
		btnLogOut.setBackground(new Color(0, 0, 102));
		
		JLabel lblBrand = new JLabel("Brand :");
		lblBrand.setBounds(17, 64, 100, 20);
		frame.getContentPane().add(lblBrand);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(17, 84, 404, 25);
		frame.getContentPane().add(textField);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setBounds(17, 119, 100, 20);
		frame.getContentPane().add(lblModel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(17, 139, 404, 25);
		frame.getContentPane().add(textField_1);
		
		JLabel lblColor = new JLabel("Color :");
		lblColor.setBounds(17, 174, 180, 20);
		frame.getContentPane().add(lblColor);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(17, 194, 404, 25);
		frame.getContentPane().add(textField_2);
		
		JLabel lblVehicleNumber = new JLabel("Vehicle Number :");
		lblVehicleNumber.setBounds(17, 231, 180, 20);
		frame.getContentPane().add(lblVehicleNumber);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(17, 251, 404, 25);
		frame.getContentPane().add(textField_3);
		
		JLabel lblMarketValue = new JLabel("Market Value :");
		lblMarketValue.setBounds(17, 286, 180, 20);
		frame.getContentPane().add(lblMarketValue);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(17, 306, 404, 25);
		frame.getContentPane().add(textField_4);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setBounds(17, 341, 100, 20);
		frame.getContentPane().add(lblRate);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(17, 361, 404, 25);
		frame.getContentPane().add(textField_5);
		
		JButton button_1 = new JButton("Register");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement st = con.con.createStatement();
					st.executeUpdate("INSERT INTO cars(brand, model, color, number, value, rate) VALUES (\'"+textField.getText()+"\', \'"+textField_1.getText()+"\', \'"+textField_2.getText()+"\', \'"+textField_3.getText()+"\', \'"+textField_4.getText()+"\', \'"+textField_5.getText()+"\')");
					JOptionPane.showMessageDialog(frame, "Car Registered Successfully!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(171, 401, 114, 25);
		frame.getContentPane().add(button_1);
	}
}
