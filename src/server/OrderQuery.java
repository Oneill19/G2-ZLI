package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.ReturnCommand;

public class OrderQuery {
	private static final int ORDERS_CULUMN_NUMBER = 16;
	
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
	
	
	/**
	 * @param con			connection to client
	 * @param orderNumber	number of order that items are connected to
	 * @param item_in_order	list of item-amount
	 * @return
	 */
	public static ReturnCommand addItemsInOrder(Connection con, String orderNumber, String item_in_order) {
		PreparedStatement ps;
		String[] itemArray = item_in_order.split(" ");
		
		//insert items to DB
		if(item_in_order.length() != 0)
			for(String itemSerial : itemArray) {
				String insertQuery = "INSERT INTO item_in_order(itemSerial,amount,orderNumber) VALUES ("+itemSerial+","+orderNumber.toString()+")";
				System.out.println("item quey: "+insertQuery);
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
	
	
	/**
	 * @param con				connection to the client
	 * @param orderNumber		the orderNumber that the product are connected to
	 * @param product_in_order	list of products-amount
	 * @return
	 */
	public static ReturnCommand addProductsInOrder(Connection con, String orderNumber, String product_in_order) {
		PreparedStatement ps;
		String[] productArray = product_in_order.split(" ");
		
		//insert products to DB
		for(String productSerial : productArray) {
			String insertQuery = "INSERT INTO product_in_order(productSerial,amount,orderNumber) VALUES ("+productSerial+","+orderNumber.toString()+")";
			System.out.println("product query: " + insertQuery);
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
	 * inserts the data of delivery order into the DB.
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
	
	
	/**
	 * @param con		connection to client
	 * @param userID	userID which orders we are looking for
	 * @return			String that can be sent into Order(String orderData) constructor to initial an Order object.
	 */
	public static ReturnCommand getUserOrders(Connection con, String userID ) {
		Statement stmt = null;
		String selectQuery = "SELECT * FROM orders WHERE orderNumber = '"+userID+"';";
		StringBuilder sb = new StringBuilder();
		System.out.println(selectQuery); //debug
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			rs.next(); 
			int i,j=0;
			for(i=1+j ; rs.next() ; i++)
				sb.append(Integer.toString(rs.getInt(1+j))).append("\t");//orderNumber
				sb.append(Double.toString(rs.getDouble(2+j))).append("\t");//totalPrice
				for(j=i;j<=ORDERS_CULUMN_NUMBER*i && rs.next();j++) {
					sb.append(rs.getString(i)).append("\t");//otherStringData
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("sb: "+sb.toString());//debug
		return new ReturnCommand("getUserOrders", sb.toString());
	}
}
