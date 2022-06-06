package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	 * @return ReturnCommand
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
					user = new Customer(userId,firstName, lastName, creditCard, phone, email, userPassword, userRole, status, isLogged, balance);
				}
				else if (rs.getString(8).equals("StoreWorker") || rs.getString(8).equals("StoreManager") || rs.getString(8).equals("StoreWorkerWithPermission")) {
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
	 * @return ReturnCommand
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
	 * @return ReturnCommand
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
	
	
	/**
	 * get customer balance
	 * 
	 * @param con
	 * @param id
	 * @return ReturnCommand with customer balance
	 */
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
	
	/**
	 * return the store of a worker by id
	 * 
	 * @param con
	 * @param id
	 * @return String of store name
	 */
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
	
	/**
	 * import users from outside system
	 * 
	 * @param con
	 * @return true if all the users were imported
	 */
	public static boolean importUsersFromSystem(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.users_system;";
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				int userId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String creditCard = rs.getString(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String password = rs.getString(7);
				String userRole = rs.getString(8);
				String status = rs.getString(9);
				if (insertUser(con, userId, firstName, lastName, creditCard, phone, email, password, userRole, status)) {
					if (userRole.equals("Customer")) {
						double balance = rs.getDouble(11);
						if (!insertCustomer(con, userId, balance)) {
							return false;
						}
					}
					else if (userRole.equals("StoreWorker") || userRole.equals("StoreManager") || userRole.equals("StoreWorkerWithPermission")) {
						String storeName = rs.getString(10);
						if (!insertStoreWorker(con, userId, storeName)) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * insert user to zli.users
	 * 
	 * @param con
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param creditCard
	 * @param phone
	 * @param email
	 * @param password
	 * @param userRole
	 * @param status
	 * @return true if the user inserted
	 */
	public static boolean insertUser(Connection con, int userId, String firstName, String lastName, String creditCard, String phone, String email, String password, String userRole, String status) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO zli.users (UserID,FirstName,LastName,CreditCard,Phone,Email,Password,UserRole,Status,IsLogged) VALUES (?,?,?,?,?,?,?,?,?,0)";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, creditCard);
			ps.setString(5, phone);
			ps.setString(6, email);
			ps.setString(7, password);
			ps.setString(8, userRole);
			ps.setString(9, status);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * insert the customer information to zli.user_customer
	 * 
	 * @param con
	 * @param id
	 * @param balance
	 * @return true if the information inserted
	 */
	public static boolean insertCustomer(Connection con, int id, double balance) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO user_customer (userID,balance) VALUES (?,?);";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ps.setDouble(2, balance);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * insert the store worker information to zli.user_store_worker
	 * 
	 * @param con
	 * @param id
	 * @param store
	 * @return true if the information inserted
	 */
	public static boolean insertStoreWorker(Connection con, int id, String store) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO user_store_worker (userID,storeName) VALUES (?,?);";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, id);
			ps.setString(2, store);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
