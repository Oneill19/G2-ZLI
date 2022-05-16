package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.ReturnCommand;
import entity.Customer;
import entity.StoreWorker;
import entity.User;

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
	public static ReturnCommand getUser(Connection con, String mail, String password) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE Email='" + mail + "' AND Password='" + password + "';";
		User user = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				int userId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String creditCard = rs.getString(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String userPassword = rs.getString(7);
				String userRole = rs.getString(8);
				String status = rs.getString(9);
				boolean isLogged = rs.getInt(10) == 0 ? false : true;
				if (rs.getString(8).equals("Customer")) {
					double balance = getCustomerBalance(con, userId);
					System.out.println("Server Balance: " + balance);
					user = new Customer(userId,firstName, lastName, creditCard, phone, email, userPassword, userRole, status, isLogged, balance);
				}
				else if (rs.getString(8).equals("StoreWorker") || rs.getString(8).equals("StoreManager")) {
					String storeName = getStoreWorkerStore(con, userId);
					user = new StoreWorker(userId,firstName, lastName, creditCard, phone, email, userPassword, userRole, status, isLogged, storeName);
				}
				else {
					user = new User(userId, firstName, lastName, creditCard, phone, email, userPassword, userRole, status, isLogged);
				}
			}
 			return new ReturnCommand("GetUser", user);
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
	public static ReturnCommand loginUser(Connection con, String mail) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.users SET IsLogged='1' WHERE Email='" + mail + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("LogUser", null);
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
	public static ReturnCommand logoutUser(Connection con, String mail) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.users SET IsLogged='0' WHERE Email='" + mail + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("LogoutUser", null);
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
