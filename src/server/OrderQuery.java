package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Item;
import entity.Order;
import entity.Product;

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
				try {
					ps = con.prepareStatement(insertQuery);
					ps.executeUpdate();
				}catch(Exception ex) {
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
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			}catch(Exception ex) {
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
		String selectQuery = "SELECT * FROM orders WHERE cutomerID = "+userID+";";
		StringBuilder sb = new StringBuilder();
		int j=1;
		ResultSet rs=null;
		ArrayList<Order> userOrdersList= new ArrayList<Order>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while(rs.next()) {
				sb.append(Integer.toString(rs.getInt(1))).append("\t");//orderNumber
				sb.append(Double.toString(rs.getDouble(2))).append("\t");//totalPrice
				for(j=3;j<=16;j++)
					sb.append(rs.getString(j)).append("\t");//otherStringData
				userOrdersList.add(new Order(sb.toString()));
				sb.setLength(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ReturnCommand("getUserOrders", userOrdersList);
	}
	
	/**
	 * @param con			connection to client
	 * @param orderNumber	order number of which the delivery data is necessary
	 * @return				new ReturnCommand() with concated Strings nameOfReceiver+phoneOfReceiver+receptionAddress
	 */
	public static ReturnCommand getOrderDeliveryData(Connection con, String orderNumber) {
		Statement stmt=null;
		String selectQuery = "SELECT * FROM orderbydelivery WHERE orderNumber = "+orderNumber+";";
		ResultSet rs=null;
		String nameOfReceiver = null, phoneOfReceiver = null, receptionAddress = null ;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while(rs.next()) {
				nameOfReceiver = rs.getString(2);
				phoneOfReceiver = rs.getString(3);
				receptionAddress = rs.getString(4);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		ReturnCommand rc = new ReturnCommand("getOrderDeliveryData", nameOfReceiver+"\t"+phoneOfReceiver+"\t"+receptionAddress);
		return rc;
	}
	
	public static ReturnCommand getOrderItems(Connection conn, String orderNumber) {
		Statement stmt;
		String selectQuery = "SELECT *"
							+ "FROM item I "
							+ "INNER JOIN item_in_order IO ON IO.itemSerial=I.itemSerial "
							+ "WHERE IO.orderNumber="+orderNumber;
		ResultSet rs = null;
		ArrayList<Item> itemsList= new ArrayList<Item>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while(rs.next()) {
				Item item = null;
				String itemSerial = rs.getString(1);
				String itemName =	rs.getString(2);
				Double itemPrice = rs.getDouble(3);
				String itemImage = rs.getString(5);
				String itemType = rs.getString(4);
				Boolean isSoldAlone = rs.getBoolean(6);
				String color = rs.getString(8);
				item = new Item(itemSerial, itemName, itemPrice, itemImage, itemType, isSoldAlone,0,0, color);
				item.setAmount(rs.getInt(9));//amount
				itemsList.add(item);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ReturnCommand("getOrderItems", (Object)itemsList);
	}
	
	
	public static ReturnCommand getOrderProducts(Connection conn, String orderNumber) {
		Statement stmt;
		String selectQuery = "SELECT *"
							+ "FROM product I "
							+ "INNER JOIN product_in_order IO ON IO.productSerial=I.productSerial "
							+ "WHERE IO.orderNumber="+orderNumber;
		ResultSet rs = null;
		ArrayList<Product> products= new ArrayList<Product>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while(rs.next()) {
				Product product = null;
				String productSerial = rs.getString(1);
				String productName = rs.getString(2);
				double productPrice = rs.getDouble(3);
				String productImage = rs.getString(4);
				String other = rs.getString(5);
				String productType = rs.getString(6);
				int sale = rs.getInt(7);
				String color = rs.getString(8);
				@SuppressWarnings("unchecked")
				ArrayList<Item> madeFrom = (ArrayList<Item>)ProductsQuery.getAllItemsInProduct(conn, productSerial).getReturnValue();
				product = new Product(productSerial, productName, productPrice, productType, productImage, other, madeFrom,sale, color);
				product.setAmount(rs.getInt(9));
				products.add(product);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ReturnCommand("getOrderProducts", products);
	}
	
	public static ReturnCommand changeOrderStatus(Connection conn, String orderNumber, String newStatus ) {
		PreparedStatement ps;

		String updateQuery = "UPDATE orders SET orderStatus = '"+newStatus+"' WHERE orderNumber = "+orderNumber;
//		System.out.println(updateQuery);
		try {
			ps = conn.prepareStatement(updateQuery);
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ReturnCommand("changeOrderStatus",null);
	}
	
	public static ReturnCommand insertTo_order_cancelation(Connection conn, String orderNumber) {
		PreparedStatement ps;
		String today = LocalDate.now().toString();
		String now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString();
//		System.out.println("now: "+now.toString());//debug
//
		String insertQuery = "INSERT INTO order_cancelation(orderNumber, requestCancelationDate, requestCancelationTime) VALUES (?,?,?)";
		try {
			ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, Integer.parseInt(orderNumber));
			ps.setString(2, today);
			ps.setString(3, now);
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ReturnCommand("insertTo_order_cancelation",false);
		}
		return new ReturnCommand("insertTo_order_cancelation",true);
	}
}
