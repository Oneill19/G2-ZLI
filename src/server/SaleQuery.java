package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Item;
import entity.Product;
import entity.Sale;

public class SaleQuery {

	/**
	 * @param con        connection to client
	 * @param saleName   to insert to table
	 * @param saleAmount to insert to table
	 * @return the id of the new sale
	 */
	public static ReturnCommand insertNewSale(Connection con, String saleName, String saleAmount) {
		PreparedStatement ps;
		Statement stmt;
		ResultSet rs;

		// insert the new sale
		String insertQuery = "INSERT INTO sale (saleName, discountAmount)" + "VALUES ('" + saleName + "'," + saleAmount
				+ ");";
//		System.out.println(insertQuery);//debug
		try {
			ps = con.prepareStatement(insertQuery);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String selectQuery = "SELECT LAST_INSERT_ID();";
		// get the saleID
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery);
			rs.next();
			return new ReturnCommand("insertNewSale", rs.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param con    connection to client
	 * @param serial the serialNumber of the item
	 * @param idSale the idSale of the item
	 * @return nothing.
	 */
	public static ReturnCommand insertItemInSale(Connection con, String serial, String idSale) {
		PreparedStatement ps;
		String insertQuery;
		String[] allItems = serial.split("\t");

//		insert the new sale
		for (String itemSerial : allItems) {
			insertQuery = "INSERT INTO item_in_sale(itemSerial, idSale)" + "VALUES ('" + itemSerial + "'," + idSale
					+ ");";
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

//		update the idSale of items
		for (String itemSerial : allItems) {
			String updateQuery = "UPDATE item SET idSale = " + idSale + " WHERE itemSerial = '" + itemSerial + "';";
			try {
				ps = con.prepareStatement(updateQuery);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * @param con    connection to client
	 * @param serial the serialNumber of the product
	 * @param idSale the idSale of the product
	 * @return nothing.
	 */
	public static ReturnCommand insertProductsInSaleToDB(Connection con, String serial, String idSale) {
		PreparedStatement ps;
		String insertQuery;
		String[] allProducts = serial.split("\t");

//		insert the new sale
		for (String productSerial : allProducts) {
			insertQuery = "INSERT INTO product_in_sale(productSerial, idSale)" + "VALUES ('" + productSerial + "',"
					+ idSale + ");";
			try {
				ps = con.prepareStatement(insertQuery);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

//		update the idSale of items
		for (String productSerial : allProducts) {
			String updateQuery = "UPDATE product SET idSale = " + idSale + " WHERE productSerial = '" + productSerial
					+ "';";
			try {
				ps = con.prepareStatement(updateQuery);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static ReturnCommand selectAllSales(Connection conn) {
		Statement stmt;
		String selectQuery = " SELECT * FROM sale";
		ResultSet rs = null;
		ArrayList<Sale> saleArray = new ArrayList<Sale>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				Integer idSale = rs.getInt(1);
				String saleName = rs.getString(2);
				int discountAmount = rs.getInt(3);
				String status = rs.getString(4);

				saleArray.add(new Sale(idSale, saleName, discountAmount, status));
			}
			if (saleArray.size() > 0)
				return new ReturnCommand("selectAllSales", saleArray);
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ReturnCommand select_item_in_sale(Connection conn, String saleID) {
		Statement stmt;
		String selectQuery = " SELECT * "
				+ "FROM sale as s "
				+ "INNER JOIN item_in_sale as isale on s.idSale=isale.idSale "
				+ "INNER JOIN item as i on isale.itemSerial=i.itemSerial "
				+ "WHERE s.idSale='"+saleID+"';";
//		System.out.println(selectQuery); //debug
		ResultSet rs = null;
		ArrayList<Item> itemsInSaleArray = new ArrayList<Item>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
					String serialNumber = rs.getString(5);
					String name = rs.getString(8);
					double price = rs.getDouble(9);
					String imagePath = rs.getString(11);
					String type = rs.getString(10);
					boolean isSoldAlone = rs.getBoolean(12);
					int idSale = rs.getInt(1);
					String color = rs.getString(14);
					int amountInProduct=0;
					
					itemsInSaleArray.add(new Item(serialNumber, name, price, imagePath, type, idSale, isSoldAlone, amountInProduct, color));
			}
			if(itemsInSaleArray.size()>0)
				return new ReturnCommand("select_item_in_sale", itemsInSaleArray);
			else
				return new ReturnCommand("select_item_in_sale", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static ReturnCommand select_product_in_sale(Connection conn, String saleID) {
		Statement stmt;
		String selectQuery = " SELECT * "
				+ "FROM sale as s "
				+ "INNER JOIN product_in_sale as ip on s.idSale=ip.idSale "
				+ "INNER JOIN product as p on ip.productSerial=p.productSerial "
				+ "WHERE s.idSale='"+saleID+"';";
//		System.out.println(selectQuery); //debug
		ResultSet rs = null;
		ArrayList<Product> productInSaleArray = new ArrayList<Product>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
					String serialNumber = rs.getString(5);
					String name = rs.getString(8);
					double price = rs.getDouble(9);
					String type = rs.getString(12);
					String imagePath = rs.getString(10);
					String other = rs.getString(11);
					ArrayList<Item> madeFrom = (ArrayList<Item>) ProductsQuery.getAllItemsInProduct(conn, serialNumber).getReturnValue();
					int idSale = rs.getInt(1);
					String color = rs.getString(14);
					
					productInSaleArray.add(new Product(serialNumber, name, price, type, imagePath, other, madeFrom, idSale, color));
			}
			if(productInSaleArray.size()>0)
				return new ReturnCommand("select_product_in_sale", productInSaleArray);
			else
				return new ReturnCommand("select_product_in_sale", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
