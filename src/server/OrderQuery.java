package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.ReturnCommand;

public class OrderQuery {
	
	/**
	 * @param con			connection to client
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
	
	
	public static ReturnCommand addItemsInOrder(Connection con, String orderNumber, String item_in_order) {
		PreparedStatement ps;
		String[] itemArray = item_in_order.split(" ");
		
		//insert items to DB
		if(item_in_order.length() != 0)
			for(String itemSerial : itemArray) {
				String insertQuery = "INSERT INTO item_in_order(itemSerial, orderNumber,amount) VALUES ("+itemSerial+","+orderNumber.toString()+")";
				System.out.println(insertQuery);
				try {
					ps = con.prepareStatement(insertQuery);
					ps.executeUpdate();
				}catch(Exception ex) {
					System.out.println("Item: "+itemSerial);
					ex.printStackTrace();
				}
			}
		return new ReturnCommand("addItemsInOrder", "items: " + item_in_order + " were added.");
	}
	
	
	public static ReturnCommand addProductsInOrder(Connection con, String orderNumber, String product_in_order) {
		PreparedStatement ps;
		String[] productArray = product_in_order.split(",");
		
		//insert products to DB
		for(String productSerial : productArray) {
			String insertQuery = "INSERT INTO product_in_order(productSerial, orderNumber) VALUES ("+productSerial+","+orderNumber.toString()+")";
//			System.out.println(insertQuery);
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			}catch(Exception ex) {
				System.out.println("Item: "+productSerial);
				ex.printStackTrace();
			}
		}
		return new ReturnCommand("addProductsInOrder", "Products: " + product_in_order + " were added.");
	}
	
	/**
	 * inserts the address of order into the DB.
	 * @param con			connection to client
	 * @param orderNumber	PK in the DB
	 * @param deliveryData	name of receiver, phone of receiver, and the address to deliver to
	 * @return
	 */
	public static ReturnCommand addDeliveryOrder(Connection con, String orderNumber, String deliveryData) {
		PreparedStatement ps;
		String insertQuery = "INSERT INTO orderbydelivery(orderNumber, nameOfReceiver, phoneOfReceiver, receptionAddress)"
				+"VALUES( "+orderNumber+"," + deliveryData +")";
		try {
			ps = con.prepareStatement(insertQuery);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ReturnCommand("addDeliveryOrder", "Delivery information: "+deliveryData+" was added.");
	}
}
