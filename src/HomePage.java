import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JTextArea;

public class HomePage {

	public JFrame frame;
	private JFrame pFrame;
	private JTextField textField;
	private JTextField textField_1;
	private ConnectDB con;
	private User user;
	ArrayList<Car> cars;
	private JTextField textField_2;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HomePage window = new HomePage();
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
	public HomePage(JFrame frame, User user) {
		pFrame = frame;
		cars = new ArrayList<Car>();
		this.user = user;
		initialize();
		try {
			con = new ConnectDB();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Datebase Connection Problem!", "Errot", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Car foundById(int id) {
		for(int i = 0; i < cars.size(); i++) {
			if(cars.get(i).id == id)
				return cars.get(i);
		}
		return null;
	}
	
	public void register(User user, Car car, String from, String to) {
		try {
			Statement st = con.con.createStatement();
			st.executeUpdate("INSERT INTO history(uid, cid, issueDate, returnDate) VALUES (\'"+user.id+"\', \'"+car.id+"\', \'"+from+"\', \'"+to+"\')");
			JOptionPane.showMessageDialog(frame, "Car Booked Successfully!\n Total rent : "+(car.rate * to.compareTo(from)+" /-"));
			btnSearch.doClick();
			frame.repaint();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Booking failed!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(frame);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(12, 12, 426, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCarRentalSystem = new JLabel("Car Rental System");
		lblCarRentalSystem.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCarRentalSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarRentalSystem.setForeground(new Color(255, 255, 255));
		panel.add(lblCarRentalSystem, BorderLayout.CENTER);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnLogOut.setFont(new Font("Dialog", Font.BOLD, 12));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(0, 0, 102));
		panel.add(btnLogOut, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 52, 426, 55);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(0, 35, 50, 20);
		panel_1.add(lblFrom);
		
		textField = new JTextField();
		textField.setBounds(53, 35, 100, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(170, 30, 35, 20);
		panel_1.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 35, 100, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPleaseTellUs = new JLabel("Please Tell us the period for which you need the car (dd/mm/yyyy)");
		lblPleaseTellUs.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		lblPleaseTellUs.setBounds(0, 10, 450, 15);
		panel_1.add(lblPleaseTellUs);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(325, 30, 100, 25);
		panel_1.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 120, 426, 342);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 460, 426, 50);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(0, 24, 300, 26);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(textField_2.getText());
					String dateFrom[] = textField.getText().split("/");
					String dateTo[] = textField_1.getText().split("/");
					LocalDate from = LocalDate.parse(dateFrom[2]+"-"+dateFrom[1]+"-"+dateFrom[0]);
					LocalDate to = LocalDate.parse(dateTo[2]+"-"+dateTo[1]+"-"+dateTo[0]);
					DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					Car car = foundById(id);
					textField_2.setText("");
					if(car == null)
						JOptionPane.showMessageDialog(frame, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
					else
						register(user, car, from.format(format), to.format(format));
				}catch(Exception x) {
					JOptionPane.showMessageDialog(frame, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBook.setBounds(312, 24, 114, 25);
		panel_2.add(btnBook);
		
		JLabel lblEnterCarId = new JLabel("Enter Car ID :");
		lblEnterCarId.setBounds(0, 5, 108, 15);
		panel_2.add(lblEnterCarId);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dateFrom[] = textField.getText().split("/");
				String dateTo[] = textField_1.getText().split("/");
				try {
					LocalDate from = LocalDate.parse(dateFrom[2]+"-"+dateFrom[1]+"-"+dateFrom[0]);
					LocalDate to = LocalDate.parse(dateTo[2]+"-"+dateTo[1]+"-"+dateTo[0]);
					if(from.isAfter(to) || LocalDate.now().isAfter(from)) {
						JOptionPane.showMessageDialog(frame, "Invalid Date!", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						for(int i = cars.size()-1; i >= 0; i--)
							cars.remove(i);
						textArea.setText("");
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						Statement st = con.con.createStatement();
						String query = "SELECT * FROM cars WHERE id NOT IN (SELECT cid FROM history WHERE (issueDate < \'"+from.format(format)+"\' AND returnDate > \'"+from.format(format)+"\') OR (issueDate < \'"+to.format(format)+"\' AND returnDate > \'"+to.format(format)+"\') OR (issueDate <= \'"+from.format(format)+"\' AND returnDate >= \'"+to.format(format)+"\'))";
						ResultSet res = st.executeQuery(query);
						while(res.next()) {
							Car car = new Car(Integer.parseInt(res.getString("id")), res.getString("brand"), res.getString("model"), res.getString("color"), res.getString("number"), Integer.parseInt(res.getString("value")), Double.parseDouble(res.getString("rate")));
							cars.add(car);
							textArea.setText(textArea.getText()+car.id+" : "+car.brand+", "+car.model+"("+car.color+") :- "+car.rate+"/- per day \n");
						}
					}
				}catch(DateTimeParseException x) {
					JOptionPane.showMessageDialog(frame, "Invalid Date!", "Error", JOptionPane.ERROR_MESSAGE);
				}catch(SQLException x) {
					JOptionPane.showMessageDialog(frame, "Datebase Connection Problem", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
}
