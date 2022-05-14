package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author oneil
 */
/**
 * @author oneil
 *
 */
public class AuthQuery {
	

	/**
	 * method to execute sql query and return a user as a string
	 * 
	 * @param con
	 * @param mail
	 * @param password
	 * @return string
	 */
	public static String getUser(Connection con, String mail, String password) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE Email='" + mail + "' AND Password='" + password + "';";
		String userDetail = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				userDetail = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) +
								" " + rs.getString(4) + " " + rs.getString(5) + " " +
								rs.getString(6) + " " + rs.getString(7) + 
								" " + rs.getString(8) + " " + rs.getInt(9) + " " + rs.getInt(10);
				return "GetUser " + userDetail; 
			}
 			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * method to set the user logged as logged in the database
	 * 
	 * @param con
	 * @param mail
	 * @return string
	 */
	public static String loginUser(Connection con, String mail) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.users SET IsLogged='1' WHERE Email='" + mail + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return "LogUser";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * method to set the user that logged out as logged out in the database
	 * 
	 * @param con
	 * @param mail
	 * @return string
	 */
	public static String logutUser(Connection con, String mail) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.users SET IsLogged='0' WHERE Email='" + mail + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return "LogoutUser";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
