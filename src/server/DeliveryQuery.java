package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Customer;
import entity.Order;

public class DeliveryQuery {
	public static Customer getUserById(Connection con, String id) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.users WHERE UserID=" + id +";";
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
				String password = rs.getString(7);
				String userRole = rs.getString(8);
				String status = rs.getString(9);
				double balance = 0;
				sqlQuery = "SELECT * FROM zli.user_customer WHERE userID=" + userId +";";
				stmt = con.createStatement();
				rs = stmt.executeQuery(sqlQuery);
				if (rs.next()) {
					balance = rs.getDouble(2);
				}
				return new Customer(userId, firstName, lastName, creditCard, phone, email, password, userRole, status, false, balance);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ReturnCommand approveDeliveryOfOrder(Connection con, String customerId, String orderNumber) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.orders SET orderStatus='COMPLETED' WHERE orderNumber=" + orderNumber + ";";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("ApproveDeliveryOfOrder", getUserById(con, customerId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("ApproveDeliveryOfOrder", null);
		}
	}
	
	public static Object fullRefundOf(Connection con, String customerId, double balance) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.user_customer SET balance=" + balance + " WHERE userID=" + customerId + ";";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ReturnCommand foundGetAllConfirmedOfDelivery(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.orders WHERE orderStatus='CONFIRMED' AND deliveryMethod='Delivery';";
		ResultSet rs = null;
		ArrayList<Order> orders = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
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
						rs.getString(14), // deliveryMethod
						LocalDate.parse(rs.getString(15)),
						LocalTime.parse(rs.getString(16))
				);
				orders.add(order);
			}
			return new ReturnCommand("GetAllConfirmedOfDelivery", orders);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("GetAllConfirmedOfDelivery", null);
		}
	}
}
