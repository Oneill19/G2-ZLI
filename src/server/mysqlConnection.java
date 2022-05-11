package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import entity.Order;

public class mysqlConnection {
	private static final int ORDERNUMBER = 1;
	private static final int TOTALPRICE = 2;
	private static final int GREETINGCARD = 3;
	private static final int COLOR = 4;
	private static final int ORDERDESC = 5;
	private static final int FROMSTORE = 6;
	private static final int ORDERDATE = 7;
	private static final int ORDERTIME = 8;
	private static final int CUSTOMERID = 9;
	private static final int PAYMENTMETHOD = 10;
	private static final int ORDERSTATUS = 11;
	private static final int CONFIRMEDDATE = 12;
	private static final int COMPLETEDATE = 13;
	private static final int DELIVERYMETHOD = 14;
	private static final int EXPECTEDDATEINSTORE = 15;
	private static final int EXPECTEDTIMEINSTORE = 16;

	// function shows details from DB on tableview of
	// gui.client.ShowOrdersScreen.fxml
	// Triggered by clicking on "Show Orders" button
	public static String showOrder(Connection con, String orderNumber) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.orders WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
		String orderDetails = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next())
				orderDetails = (rs.getInt(ORDERNUMBER) + " " + rs.getString(TOTALPRICE) + " "
						+ rs.getString(GREETINGCARD) + " " + rs.getString(COLOR) + " " + rs.getString(ORDERDESC) + " "
						+ rs.getString(FROMSTORE) + " " + rs.getString(ORDERDATE) + " " + rs.getString(ORDERTIME) + " "
						+ rs.getString(CUSTOMERID) + " " + rs.getString(PAYMENTMETHOD) + " " + rs.getString(ORDERSTATUS)
						+ " " + rs.getString(CONFIRMEDDATE) + " " + rs.getString(COMPLETEDATE) + " "
						+ rs.getString(DELIVERYMETHOD) + " " + rs.getString(EXPECTEDDATEINSTORE) + " "
						+ rs.getString(EXPECTEDTIMEINSTORE));
			return orderDetails;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//sets orders' parameters from DB and returns an initialed order array list.
	public static ArrayList<Order> loadOrders(Connection con) {
		Statement stmt = null;
		int orderNumber = 0, customerID = 0;
		double totalPrice = 0;
		String greetingCard = null, color = null, orderDesc = null, fromStore = null, paymentMethos = null;
		String orderStatus = null, confirmedDate = null, completeDate = null, deliveryMethod = null;
		LocalDate orderDate = null, expectedDateInStore = null;
		LocalTime orderTime = null, expectedTimeInStore = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM zli.orders");
			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				orderNumber = rs.getInt(ORDERNUMBER);
				totalPrice = rs.getDouble(TOTALPRICE);
				greetingCard = rs.getString(GREETINGCARD);
				color = rs.getString(COLOR);
				orderDesc = rs.getString(ORDERDESC);
				fromStore = rs.getString(FROMSTORE);
				orderDate = LocalDate.parse(rs.getString(ORDERDATE));
				orderTime = LocalTime.parse(rs.getString(ORDERTIME));
				customerID = rs.getInt(CUSTOMERID);
				paymentMethos = rs.getString(PAYMENTMETHOD);
				orderStatus = rs.getString(ORDERSTATUS);
				confirmedDate = rs.getString(CONFIRMEDDATE);
				completeDate = rs.getString(COMPLETEDATE);
				deliveryMethod = rs.getString(DELIVERYMETHOD);
				expectedDateInStore = LocalDate.parse(rs.getString(EXPECTEDDATEINSTORE));
				expectedTimeInStore = LocalTime.parse(rs.getString(EXPECTEDTIMEINSTORE));

				Order order = new Order(orderNumber, totalPrice, greetingCard, color, orderDesc, fromStore, orderDate,
						orderTime, customerID, paymentMethos, orderStatus, confirmedDate, completeDate, deliveryMethod,
						expectedDateInStore, expectedTimeInStore);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Recognizes the edited column and updates changes in DB.
	public static boolean cellUpdate(Connection con, String orderNumber, Object newValue, int column) {
		PreparedStatement ps;
		String sql;
		try {
			switch (column) {// start SWITCH
			case COLOR:
				sql = "UPDATE zli.orders SET color=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			case ORDERDATE:
				sql = "UPDATE zli.orders SET ORDERDATE=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			case ORDERTIME:
				sql = "UPDATE zli.orders SET ORDERTIME=? WHERE orderNumber=" + Integer.parseInt(orderNumber) + ";";
				ps = con.prepareStatement(sql);
				ps.setString(1, newValue.toString());
				ps.executeUpdate();
				ps.close();
				break;
			default:// debugging
				System.out.println("Command doesn't exist");
			}// end SWITCH
			System.out.println("Order Updated");
			return true;
		} catch (SQLException e) {
			System.out.println("CellUpdateQuery failed");
			e.printStackTrace();
			return false;
		}
	}

}