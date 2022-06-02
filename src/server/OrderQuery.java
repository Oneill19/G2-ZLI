package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.ReturnCommand;

public class OrderQuery {
	
	/**
	 * @param con
	 * @param orderString - contains the VALUES to INSERT TO the DB
	 * @return ReturnCommand
	 */
	public static ReturnCommand saveOrderToDB(Connection con, String orderString) {
		PreparedStatement ps;
		Statement stmt;
		String insertQuery = "INSERT INTO zli.orders (totalPrice, greetingCard, color, orderDesc,"
				+ "fromStore, orderCreationDate, orderCreationTime, cutomerID, paymentMethod, "
				+ "orderStatus, confirmedDate, completeDate, deliveryMethod, supplyDate, supplyTime) VALUES"
				+ "(" + orderString + ");";
		String selectQuery = "SELECT orderNumber from orders where orderNumber=(SELECT LAST_INSERT_ID());";
		
		try {
			ps = con.prepareStatement(insertQuery);
			ps.executeUpdate();
			stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			rs.next();
			return new ReturnCommand("AddOrderToDB", rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ReturnCommand addItemsAndProductsInOrder(Connection con, String orderNumber, String item_in_order, String product_in_order) {
		PreparedStatement ps;
		String[] itemArray = null, productArray = null;
		
		try {
			itemArray = item_in_order.split(",");
			productArray = product_in_order.split(",");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for(String itemSerial : itemArray) {
			String insertQuery = "INSERT INTO item_in_order(itemSerial, orderNumber) VALUES ("+itemSerial+","+orderNumber.toString()+")";
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			}catch(Exception ex) {
				System.out.println("Item: "+itemSerial);
				ex.printStackTrace();
			}
		}
		
		for(String productSerial : productArray) {
			String insertQuery = "INSERT INTO product_in_order(productSerial, orderNumber) VALUES ("+productSerial+","+orderNumber.toString()+")";
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			}catch(Exception ex) {
				System.out.println("Item: "+productSerial);
				ex.printStackTrace();
			}
		}
		return new ReturnCommand("addProductsAndItemsInOrderToDB",null);
	}
	
	public static ReturnCommand addDeliveryOrder(Connection con, String orderNumber, String deliveryData) {
		PreparedStatement ps;
		String insertQuery = "INSERT INTO orderbydelivery(orderNumber, nameOfReceiver, phoneOfReceiver, receptionAddress)"
				+"VALUES( "+orderNumber+"," + deliveryData +")";
		System.out.println("insertQuery: "+insertQuery);
		try {
			ps = con.prepareStatement(insertQuery);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ReturnCommand("addDeliveryOrder", "Delivery information: "+deliveryData+" was added.");
	}
}
