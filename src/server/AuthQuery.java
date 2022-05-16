package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @AUTHOR ONEILL PANKER
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
				userDetail = 
						rs.getInt(1) + ";" +		// id
						rs.getString(2) + ";" + 	// first name
						rs.getString(3) + ";" + 	// last name
						rs.getString(4) + ";" + 	// credit card
						rs.getString(5) + ";" +		// phone
						rs.getString(6) + ";" + 	// email
						rs.getString(7) + ";" + 	// password
						rs.getString(8) + ";" + 	// user role
						rs.getString(9) + ";" + 	// status
						rs.getInt(10);				// is logged
				if (rs.getString(8).equals("Customer")) {
					userDetail += ";" + getCustomerBalance(con, rs.getInt(1));	// balance
				}
				else if (rs.getString(8).equals("StoreWorker") || rs.getString(8).equals("StoreManager")) {
					userDetail += ";" + getStoreWorkerStore(con, rs.getInt(1));	// store name
				}
				return "GetUser" + ";" + userDetail; 
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
	public static String logoutUser(Connection con, String mail) {
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
	
	
	public static double getCustomerBalance(Connection con, int id) {
		Statement stmt;
		String sqlQuery = "SELECT balance From zli.user_customer WHERE userID=" + id;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getDouble(1); 
			}
 			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String getStoreWorkerStore(Connection con, int id) {
		Statement stmt;
		String sqlQuery = "SELECT storeName From zli.user_store_worker WHERE userID=" + id;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				return rs.getString(1); 
			}
 			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
