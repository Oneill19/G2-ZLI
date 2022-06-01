package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.AbstractProduct;
import entity.Product;
import entity.Item;

/**
 * @author oneill
 *
 */

public class ProductsQuery {
	/**
	 * @param con
	 * @return ReturnCommand object with the command and the array list of products
	 */
	@SuppressWarnings("unchecked")
	public static ReturnCommand getAllProducts(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.product";
		ArrayList<AbstractProduct> products = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				String productSerial = rs.getString(1);
				String productName = rs.getString(2);
				double productPrice = rs.getDouble(3);
				String productImage = rs.getString(4);
				String other = rs.getString(5);
				String productType = rs.getString(6);
				int sale = rs.getInt(7);
				ArrayList<Item> madeFrom = (ArrayList<Item>)getAllItemsInProduct(con, productSerial).getReturnValue();
				products.add(new Product(productSerial, productName, productPrice, productType, productImage, other, madeFrom,sale));
			}
 			return new ReturnCommand("GetAllProducts", products);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param con
	 * @return ReturnCommand object with the command and the array list of items
	 */
	public static ReturnCommand getAllItems(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.item";
		ArrayList<AbstractProduct> items = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				String itemSerial = rs.getString(1);
				String itemName = rs.getString(2);
				double itemPrice = rs.getDouble(3);
				String itemType = rs.getString(4);
				String itemImage = rs.getString(5);
				boolean isSoldAlone = rs.getInt(6) == 0 ? false : true;
				int sale = rs.getInt(7);
				items.add(new Item(itemSerial, itemName, itemPrice, itemImage, itemType, isSoldAlone, 0,sale));
			}
 			return new ReturnCommand("GetAllItems", items);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param con
	 * @param productSerial
	 * @return	ReturnCommand object with the command and the array list of items in a product
	 */
	public static ReturnCommand getAllItemsInProduct(Connection con, String productSerial) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.item WHERE zli.item.itemSerial IN(SELECT itemSerial FROM zli.item_in_product WHERE zli.item_in_product.productSerial=\"" + productSerial + "\");";
		ResultSet rs = null;
		ArrayList<Item> items = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				String itemSerial = rs.getString(1);
				String itemName = rs.getString(2);
				double itemPrice = rs.getDouble(3);
				String itemType = rs.getString(4);
				String itemImage = rs.getString(5);
				boolean isSoldAlone = rs.getInt(6) == 0 ? false : true;
				int sale = rs.getInt(7);
				int amountInProduct = (int)(getAmountInProduct(con, productSerial, itemSerial).getReturnValue());
				items.add(new Item(itemSerial, itemName, itemPrice, itemImage, itemType, isSoldAlone, amountInProduct,sale));
			}
 			return new ReturnCommand("GetItemsInProduct", items);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param con
	 * @param productSerial
	 * @param itemSerial
	 * @return ReturnCommand object with the amount of item in certein product
	 */
	public static ReturnCommand getAmountInProduct(Connection con, String productSerial, String itemSerial) {
		Statement stmt;
		String sqlQuery = "SELECT amount FROM zli.item_in_product WHERE productSerial=" + productSerial + " AND itemSerial=" + itemSerial + ";";
		ResultSet rs = null;
		int amount = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				amount = rs.getInt(1);
			}
 			return new ReturnCommand("GetAmountInProduct", amount);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
