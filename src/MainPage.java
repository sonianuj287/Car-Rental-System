import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 430, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 102));
		panel_1.setBounds(12, 0, 406, 40);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Car Rental System");
		label.setBounds(0, 0, 426, 40);
		panel_1.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login(frame).frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAdmin.setBounds(162, 212, 114, 30);
		panel.add(btnAdmin);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserPage(frame).frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnCustomer.setBounds(162, 268, 114, 30);
		panel.add(btnCustomer);
		
		JLabel lblSelectUserType = new JLabel("Select User Type!");
		lblSelectUserType.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblSelectUserType.setBounds(147, 165, 155, 15);
		panel.add(lblSelectUserType);
	}

}
