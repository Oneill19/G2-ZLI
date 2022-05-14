package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import entity.Order;

public class mysqlConnection {

	// Sets orders' parameters from DB and returns an initialed order array list.
	// Triggered by clicking on Show Orders button at OptionsScreen.fxml
	public static ArrayList<Order> loadOrders(Connection con) {
		Statement stmt = null;
		LocalDate supplyDate = null;
		LocalTime supplyTime = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from orders;");
			
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
						rs.getString(10),	// paymentMethod
						rs.getString(11),	// orderStatus
						rs.getString(12),	// confirmedDate
						rs.getString(13),	// completeDate
						rs.getString(14)	// deliveryMethod
				);

				// For now, this can't be null
				// TODO
				String checkNotNull = rs.getString(14);
				if (checkNotNull != null)
					supplyDate = LocalDate.parse(rs.getString(14));
				else
					supplyDate = LocalDate.parse("2012-12-12");
				checkNotNull = rs.getString(15);
				if (checkNotNull != null)
					supplyTime = LocalTime.parse(rs.getString(15));
				else
					supplyTime = LocalTime.parse("12:12");
				order.setSupplyDate(supplyDate);
				order.setSuppplyTime(supplyTime);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {// DROP the temporary table that was created at the beginning of the function
				stmt.executeUpdate("DROP TEMPORARY TABLE temp_order_store;");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Updates relevant column in DB.
	public static void cellUpdate(Connection con, String orderNumber, String newValue, String column) {
		PreparedStatement ps;
		String sql = "UPDATE zli.orders SET " + column + " =? WHERE orderNumber = " + orderNumber + ";";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newValue.toString());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Order " + orderNumber + " was updated successfuly");
	}
}