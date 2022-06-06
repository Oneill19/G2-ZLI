package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Customer;
import entity.Order;
import entity.StoreWorker;
import entity.User;

/**
 * A class that performs queries related to the store manager
 * @author Topaz Eldori,Koral Biton
 *
 */
public class StoreManagerQuery {

	public static AuthQuery query = new AuthQuery();

	/**
	 * method to execute sql query and return a list of not approved users that need to register
	 * 
	 * @param con-connection
	 * @return ReturnCommand-ArrayList of the users that need to register
	 */
	public static ReturnCommand getUsersRegsiter(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE status='' OR status='WAITING_FOR_CANCELATION';";
		ResultSet rs = null;
		ArrayList<User> NotApprovedUsers = new ArrayList<>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				NotApprovedUsers.add(new User(rs.getInt(1), // UserID
						rs.getString(2), // FirstName
						rs.getString(3), // LastName
						rs.getString(4), // CreditCard
						rs.getString(5), // Phone
						rs.getString(6), // Email
						rs.getString(7), // Password
						rs.getString(8), // UserRole
						rs.getString(9), // Status
						rs.getBoolean(10) // ask if we can like this !! IsLogged
				));
			}
			return new ReturnCommand("GetNotApprovedUsers", NotApprovedUsers);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * method to execute sql query that get a list of Orders awaiting approval or
	 * cancellation 
	 * @param con-connection
	 * @param storeName- the store name of the store manager 
	 * @return  ReturnCommand-ArrayList of the appropriate orders
	 */
	public static ReturnCommand getPendingOrders(Connection con, String storeName) {
		ArrayList<Order> NotAprroveorders = new ArrayList<>();
		Statement stmt = null;
		LocalDate supplyDate = null;
		LocalTime supplyTime = null;
		String sqlQuery = "SELECT * From zli.orders WHERE  fromStore='" + storeName + "' AND (orderStatus='WAITING_FOR_CONFIRMATION' OR orderStatus='WAITING_FOR_CANCELATION') ;";
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			 rs = stmt.executeQuery(sqlQuery); // Get all Orders from DB

			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), // orderNumber
						rs.getDouble(2), // total price
						rs.getString(3), // greetingCard
						rs.getString(4), // color
						rs.getString(5), // orderDesc
						rs.getString(6), // fromStore
						LocalDate.parse(rs.getString(7)), // orderCreationDate
						LocalTime.parse(rs.getString(8)), // orderCreationTime
						rs.getInt(9), // customerID
						rs.getString(10), // paymentMethod
						rs.getString(11), // orderStatus
						rs.getString(12), // confirmedDate
						rs.getString(13), // completeDate
						rs.getString(14) // deliveryMethod
				);

				// For now, this can't be null
				// TODO
				String checkNotNull = rs.getString(15);
				if (checkNotNull != null)
					supplyDate = LocalDate.parse(rs.getString(15));
				else
					supplyDate = LocalDate.parse("2012-12-12");
				checkNotNull = rs.getString(16);
				if (checkNotNull != null)
					supplyTime = LocalTime.parse(rs.getString(16));
				else
					supplyTime = LocalTime.parse("12:12");
				order.setSupplyDate(supplyDate);
				order.setSuppplyTime(supplyTime);
				NotAprroveorders.add(order);
			}
			return new ReturnCommand("GetPendingOrders", NotAprroveorders);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
}

	

	
	/**
	 *  method to execute sql query that update order status to confirm
	 * @param con--connection
	 * @param OrderNumber- Order number that needs to be confirmed or canceled
	 * @param OrderStatus- The status order "CONFIRMED" or "CANCELED"
	 */
	public static ReturnCommand UpdateStatusOrders(Connection con, String OrderNumber, String OrderStatus) {
		Statement stmt;
		String sql = "";
	    String dateAprrove;
	    dateAprrove= getAprroveDate();
		if (OrderStatus.equals("WAITING_FOR_CONFIRMATION")) {
			sql = "UPDATE zli.orders SET orderStatus='CONFIRMED', confirmedDate='" + dateAprrove + "' WHERE orderNumber='" + OrderNumber + "';";
		} else {
			sql = "UPDATE zli.orders SET orderStatus='CANCELED' WHERE orderNumber ='" + OrderNumber + "';";
		}
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return new ReturnCommand("UpdateStatusOrders", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 *  method to execute sql query that get customer from DB 
	 * @param con-connection
	 * @param storeName- the store name of the store manager 
	 * @return  ReturnCommand-ArrayList- list of the customers 
	 */
	public static ReturnCommand GetApprovedUsers(Connection con, String storeName) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE status='CONFIRMED' OR status='FREEZED' ;";
		ResultSet rs = null;
		ArrayList<User> ApprovedUserToPer = new ArrayList<>();
		//String storeNameWorker;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				if (rs.getString(8).equals("Customer")) {
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
					double balance = AuthQuery.getCustomerBalance(con, userId);
					ApprovedUserToPer.add(new Customer(userId, firstName, lastName, creditCard, phone, email,
							userPassword, userRole, status, isLogged, balance));

				}

			}
			return new ReturnCommand("GetApprovedUsers", ApprovedUserToPer);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *   method to execute sql query that update customer status 
	 * @param con-connection
	 * @param NewStatus- The new status "CONFIRMED" OR "FROZEN"
	 * @param IDUser- The id of the user 
	 */
	public static ReturnCommand UpdateUserStatus(Connection con, String NewStatus, String IDUser) {
		Statement stmt;
		String sql = "";
		sql = "UPDATE zli.users SET Status='" + NewStatus + "' WHERE UserID='" + IDUser + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return new ReturnCommand("ChangeUserStatus", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method to execute sql query and return a list of not approved users that need to register
	 * @param con-connection
	 * @return ReturnCommand-ArrayList of the users that need to register
	 */
	public static ReturnCommand getAllWaitingRegistersUsers(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE status='NOT_CONFIRMED';";
		ResultSet rs = null;
		ArrayList<User> waitingUsers = new ArrayList<>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				waitingUsers.add(new User(rs.getInt(1), // UserID
						rs.getString(2), // FirstName
						rs.getString(3), // LastName
						rs.getString(4), // CreditCard
						rs.getString(5), // Phone
						rs.getString(6), // Email
						rs.getString(7), // Password
						rs.getString(8), // UserRole
						rs.getString(9), // Status
						rs.getBoolean(10) // ! IsLogged
				));
			}
			return new ReturnCommand("GetRegistersUsers", waitingUsers);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method to execute sql query that Performs user-registration confirmation in DB and adding
	 *  credit card details
	 * @param con-connection
	 * @param idUser- the user id 
	 * @param cardNum- the number of the card
	 */
	public static ReturnCommand ConfirmedUserUpdate(Connection con, String idUser, String cardNum) {
		Statement stmt;
		String sql1 = "";
		String sql2 = "";
		sql1 = "UPDATE zli.users SET Status='CONFIRMED' WHERE UserID='" + idUser + "';";
		sql2 = "UPDATE zli.users SET CreditCard='" + cardNum + "' WHERE UserID='" + idUser + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			return new ReturnCommand("ConfirmedUserUpdate", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method to execute sql query that get a list of store manager employees
	 * @param con-connection
	 * @param storeName- the store name of the store manager 
	 * @return ReturnCommand-ArrayList of the worker
	 */
	public static ReturnCommand GetWorkers(Connection con, String storeName) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.users WHERE status='CONFIRMED' OR status='FREEZED' ;";
		ResultSet rs = null;
		ArrayList<User> workers = new ArrayList<>();
		String storeNameWorker;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				if (rs.getString(8).equals("StoreWorker")) {
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
					storeNameWorker = AuthQuery.getStoreWorkerStore(con, userId);
					if (storeNameWorker.equals(storeName))
						workers.add(new StoreWorker(userId, firstName, lastName, creditCard, phone, email, userPassword,
								userRole, status, isLogged, storeName));
				}

			}
			return new ReturnCommand("GetWorkers", workers);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *  method to execute sql query that update worker status 
	 * @param con-connection
	 * @param NewStatus- the new  status of the worker
	 * @param IDUser- the id of the  selected worker
	 */
	public static ReturnCommand UpdateWorkerStatus(Connection con, String NewStatus, String IDUser) {
		Statement stmt;
		String sql = "";
		sql = "UPDATE zli.users SET UserRole='" + NewStatus + "' WHERE UserID='" + IDUser + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return new ReturnCommand("ChangeWorkerStatus", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *  method to execute sql query that check if the customer should receive refund
	 * @param con-connection
	 * @param OrderNumber -the order number 
	 * @param supplyDate- the supply date of the order
	 * @param supplyTime- the supply time of the order
	 * @param totalPrice-  the supply total Price of the order
	 * @return ReturnCommand-the refund
	 */
	public static ReturnCommand CheckRefund(Connection con, String OrderNumber, LocalDate supplyDate,
			LocalTime supplyTime, double totalPrice) {
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();
		int compaerDate = nowDate.compareTo(supplyDate);
		int compaerTime = nowTime.compareTo(supplyTime);
		double refundAmount = 0;
		String msg = null;
		String splitTimeNow[] = nowTime.toString().split(":");
		String splitSupplyTime[] = supplyTime.toString().split(":");
		double result = 0;
		if (compaerDate < 0) // receive full refound
		{
			refundAmount = totalPrice;
			msg = "RefundCancelOrderFullAmount";
			result = UpdateInDB(con, OrderNumber, supplyDate, supplyTime, totalPrice, refundAmount, msg);
		

		} else if (compaerDate == 0) // we need to check time
		{
			int sumHour = Integer.parseInt(splitSupplyTime[0]) - Integer.parseInt(splitTimeNow[0]);
			int sumMinute = Integer.parseInt(splitSupplyTime[1]) - Integer.parseInt(splitTimeNow[1]);
			int totalMinutes = sumHour * 60 + sumMinute;

			if (compaerTime < 0) {
				if (totalMinutes >= 180)  {
					msg = "RefundCancelOrderFullAmount";
					result = UpdateInDB(con, OrderNumber, supplyDate, supplyTime, totalPrice, refundAmount, msg);
				} else if (totalMinutes < 180 && totalMinutes > 60) {
					refundAmount = 0.5 * totalPrice;
					msg = "RefundCancelOrderpartialAmount";
					result = UpdateInDB(con, OrderNumber, supplyDate, supplyTime, totalPrice, refundAmount, msg);
				}

			}
		}
		return new ReturnCommand("CheckRefund", result);

	}
	

	/**
	 * Gets all the details for the update
	 * @param con-connection
	 * @param OrderNumber- The order number 
	 * @param supplyDate- the supply date of the order
	 * @param supplyTime-the supply time of the order
	 * @param totalPrice-the total price of the order
	 * @param refundAmount-the refund amount
	 * @param msg-the massage
	 * @return double refundAmount
	 */
	public static double UpdateInDB(Connection con, String OrderNumber, LocalDate supplyDate, LocalTime supplyTime,
			double totalPrice, double refundAmount, String msg) {
		double result = refundAmount;
		String sqlQuery;
		ResultSet rs;
		Statement stmt;
		int userId = -1;

		try {
			sqlQuery = "SELECT cutomerID FROM zli.orders WHERE orderNumber=" + OrderNumber + ";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				userId = rs.getInt(1);
			}
			// add the refund to customer balance
			sqlQuery = "SELECT balance FROM zli.user_customer WHERE userID=" + userId + ";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				refundAmount += rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return refundAmount;

	}
	/**
	 *  method to execute sql query that update costumer refund in this balance 
	 * @param con-connection
	 * @param balance- the sum of the refund 
	 * @param customerID- the customer 
	 */
	public static ReturnCommand UpdateBalance(Connection con, double balance,int customerID) {
		Statement stmt;
		String sql = "";
		sql ="UPDATE zli.user_customer SET balance='" + balance + "' WHERE userID='" + customerID + "';";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return new ReturnCommand("UpdateBalance", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @return String time and date in format "2002-01-03 11:11"
	 */
	public static String getAprroveDate()
	{
	LocalDate date = LocalDate.now();
	String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
	String timeAndDate = date + " " + time;
	System.out.println(timeAndDate);
	return timeAndDate;
	}
}
