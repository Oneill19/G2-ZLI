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
	private static final int ORDERNUMBER = 1;
	private static final int TOTALPRICE = 2;
	private static final int GREETINGCARD = 3;
	private static final int COLOR = 4;
	private static final int ORDERDESC = 5;
	private static final int storeName = 16;
	private static final int Order_Creation_Date = 6;
	private static final int Order_Creation_Time = 7;
	private static final int CUSTOMERID = 9;
	private static final int PAYMENTMETHOD = 10;
	private static final int ORDERSTATUS = 11;
	private static final int CONFIRMEDDATE = 12;
	private static final int COMPLETEDATE = 13;
	private static final int DELIVERYMETHOD = 14;
	private static final int EXPECTEDDATEINSTORE = 15;
	private static final int EXPECTEDTIMEINSTORE = 16;

	// sets orders' parameters from DB and returns an initialed order array list.
	public static ArrayList<Order> loadOrders(Connection con) {
		Statement stmt = null;
		int orderNumber = 0, customerID = 0;
		double totalPrice = 0;
		String greetingCard = null, color = null, orderDesc = null, fromStore = null, paymentMethos = null;
		String orderStatus = null, confirmedDate = null, completeDate = null, deliveryMethod = null;
		LocalDate orderCreationDate = null, supplyDate = null;
		LocalTime orderCreationTime = null, supplyTime = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TEMPORARY TABLE temp_order_store AS SELECT * FROM orders o INNER JOIN shop s ON o.fromStore=s.storeID;");
			stmt.executeUpdate("ALTER TABLE temp_order_store DROP storeID, DROP fromStore, DROP storeAddress, DROP storePhone;");
			ResultSet rs = stmt.executeQuery("select * from temp_order_store;");
			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				orderNumber = rs.getInt(1);
				totalPrice = rs.getDouble(2);
				greetingCard = rs.getString(3);
				color = rs.getString(4);
				orderDesc = rs.getString(5);
				fromStore = rs.getString(16);
				orderCreationDate = LocalDate.parse(rs.getString(6));
				orderCreationTime = LocalTime.parse(rs.getString(7));
				customerID = rs.getInt(8);
				paymentMethos = rs.getString(9);
				orderStatus = rs.getString(10);
				confirmedDate = rs.getString(11);
				completeDate = rs.getString(12);
				deliveryMethod = rs.getString(13);
				String checkNotNull = rs.getString(14);
				if (checkNotNull!=null)
					supplyDate = LocalDate.parse(rs.getString(14));
				else
					supplyDate=LocalDate.parse("2012-12-12");
				checkNotNull = rs.getString(15);
				if (checkNotNull!=null)
					supplyTime = LocalTime.parse(rs.getString(15));
				else
					supplyTime=LocalTime.parse("12:12");;				
				Order order = new Order(orderNumber, totalPrice, greetingCard, color, orderDesc, fromStore, orderCreationDate,
						orderCreationTime, customerID, paymentMethos, orderStatus, confirmedDate, completeDate, deliveryMethod,
						supplyDate, supplyTime);
				orders.add(order);
			}
			try {
				stmt.executeUpdate("DROP TEMPORARY TABLE temp_order_store;");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Recognizes the edited column and updates changes in DB.
	public static boolean cellUpdate(Connection con, String orderNumber, Object newValue, int column) {
		PreparedStatement ps;
		String sql;
		try {
			switch (column) {// start SWITCH
			case 3:
				sql = "UPDATE zli.orders SET color=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			case Order_Creation_Date:
				sql = "UPDATE zli.orders SET orderCreationDate=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			case Order_Creation_Time:
				sql = "UPDATE zli.orders SET orderCreationTime=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
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
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
			}
		System.out.println("Order Updated");
		return true;
			
	}

}