package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import common.ReturnCommand;

public class SaleQuery {


	/**
	 * @param con			connection to client
	 * @param saleName		to insert to table
	 * @param saleAmount	to insert to table
	 * @return the id of the new sale
	 */
	public static ReturnCommand insertNewSale(Connection con, String saleName, String saleAmount) {
		PreparedStatement ps;
		Statement stmt;
		ResultSet rs;

		//insert the new sale
		String insertQuery = "INSERT INTO sale (saleName, discountAmount)" + "VALUES ('" + saleName + "'," + saleAmount
				+ ");";
//		System.out.println(insertQuery);//debug
		try {
			ps = con.prepareStatement(insertQuery);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		String selectQuery = "SELECT LAST_INSERT_ID();";
		//get the saleID
		try {
			stmt=con.createStatement();
			rs = stmt.executeQuery(selectQuery);
			rs.next();
			return new ReturnCommand ("insertNewSale", rs.getInt(1));
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * @param con		connection to client
	 * @param serial	the serialNumber of the item
	 * @param idSale	the idSale of the item
	 * @return			nothing.
	 */
	public static ReturnCommand insertItemInSale(Connection con, String serial, String idSale) {
		PreparedStatement ps;
		String insertQuery;
		String[] allItems = serial.split("\t");
		
//		insert the new sale
		for(String itemSerial : allItems) {
			insertQuery = "INSERT INTO item_in_sale(itemSerial, idSale)" + "VALUES ('" + itemSerial + "'," + idSale + ");";
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
//		update the idSale of items
		for(String itemSerial : allItems) {
			String updateQuery = "UPDATE item SET idSale = "+idSale+" WHERE itemSerial = '"+itemSerial+"';";
			try {
				ps = con.prepareStatement(updateQuery);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/**
	 * @param con		connection to client
	 * @param serial	the serialNumber of the product
	 * @param idSale	the idSale of the product
	 * @return			nothing.
	 */
	public static ReturnCommand insertProductsInSaleToDB(Connection con, String serial, String idSale) {
		PreparedStatement ps;
		String insertQuery;
		String[] allProducts = serial.split("\t");
		
//		insert the new sale
		for(String productSerial : allProducts) {
			insertQuery = "INSERT INTO product_in_sale(productSerial, idSale)" + "VALUES ('" + productSerial + "'," + idSale + ");";
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
//		update the idSale of items
		for(String productSerial : allProducts) {
			String updateQuery = "UPDATE product SET idSale = "+idSale+" WHERE productSerial = '"+productSerial+"';";
			try {
				ps = con.prepareStatement(updateQuery);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
