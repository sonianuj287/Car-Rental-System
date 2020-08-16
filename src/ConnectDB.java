import java.sql.*;

public class ConnectDB {
	Connection con;
	Statement st;
	public ConnectDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental_db?useSSL=false", "root", "TheGeniusM06");
		st = con.createStatement();
	}
}
