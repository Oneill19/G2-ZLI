package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import entity.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class mysqlConnection {

	public static String showOrder(Connection con, String orderNumber) {
		Statement stmt;
		try {
			String sql = "SELECT * FROM zli.orders WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
			String order_detalis = null;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				order_detalis = (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
						+ " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
						+ rs.getString(8));
			return order_detalis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		/*
		 * finally{ rs.close(); }
		 */

	}
	
	public static ArrayList<Order> loadOrders(Connection con) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM zli.orders");
			ArrayList<Order> orders = new ArrayList<>();
			while(rs.next()) {
				int orderNumber = rs.getInt(1);
				double price = rs.getDouble(2);
				String greetingCard = rs.getString(3);
				String color = rs.getString(4);
				String dorder = rs.getString(5);
				String shop = rs.getString(6);
				Timestamp date = Timestamp.valueOf(rs.getString(7));
				Timestamp orderDate = Timestamp.valueOf(rs.getString(8));
				Order order = new Order(orderNumber, price, greetingCard, color, dorder, shop, date, orderDate);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean updateOrder(Connection con, String orderNumber, String color, String date) {
		PreparedStatement ps;
		try {
			Timestamp timestamp = Timestamp.valueOf(date);
			timestamp.setHours(timestamp.getHours() - 2);
			timestamp.setMinutes(timestamp.getMinutes() - 30);
			String sql = "UPDATE zli.orders SET color=?,date=? WHERE orderNumber=" + Integer.parseInt(orderNumber)
					+ ";";
			ps = con.prepareStatement(sql);
			ps.setString(1, color);
			ps.setTimestamp(2, timestamp);
			ps.executeUpdate();
			System.out.println("Order Updated");
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
