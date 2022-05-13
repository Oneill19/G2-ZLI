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
			stmt.executeUpdate(//Create temporary table to exclude unnecessary columns from it
					"CREATE TEMPORARY TABLE temp_order_store AS SELECT * FROM orders o INNER JOIN shop s ON o.fromStore=s.storeID;");
			stmt.executeUpdate(//Remove the unnecessary columns
					"ALTER TABLE temp_order_store DROP storeID, DROP fromStore, DROP storeAddress, DROP storePhone;");
			ResultSet rs = stmt.executeQuery("select * from temp_order_store;");
			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), 	// orderNumber
						rs.getDouble(2), 				// total price
						rs.getString(3), 				// greetingCard
						rs.getString(4), 				// color
						rs.getString(5), 				// orderDesc
						rs.getString(16), 				// fromStore
						LocalDate.parse(rs.getString(6)), // orderCreationDate
						LocalTime.parse(rs.getString(7)), // orderCreationTime
						rs.getInt(8), 					// customerID
						rs.getString(9), 				// paymentMethos
						rs.getString(10), 				// orderStatus
						rs.getString(11), 				// confirmedDate
						rs.getString(12), 				// completeDate
						rs.getString(13) 				// deliveryMethod
				);

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
		}finally {
			try {//DROP the temporary table that was created at the beginning of the function
				stmt.executeUpdate("DROP TEMPORARY TABLE temp_order_store;");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// Recognizes the edited column and updates changes in DB.
	public static boolean cellUpdate(Connection con, String orderNumber, Object newValue, int column) {
		PreparedStatement ps;
		String sql;
		try {
			switch (column) {// start SWITCH
			case 3://update color
				sql = "UPDATE zli.orders SET color=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			case 6://update orderCreationDate
				sql = "UPDATE zli.orders SET orderCreationDate=? WHERE orderNumber=" + Integer.parseInt(orderNumber)
						+ ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			case 7://update orderCreationTime
				sql = "UPDATE zli.orders SET orderCreationTime=? WHERE orderNumber=" + Integer.parseInt(orderNumber)
						+ ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			default:// debugging
				System.out.println("Command doesn't exist");
			}// end SWITCH
		} catch (SQLException e) {
			System.out.println("CellUpdateQuery failed");
			e.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("Order Updated");
		return true;

	}

}