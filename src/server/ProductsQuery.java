package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				String color = rs.getString(8);
				ArrayList<Item> madeFrom = (ArrayList<Item>) getAllItemsInProduct(con, productSerial).getReturnValue();
				products.add(new Product(productSerial, productName, productPrice, productType, productImage, other,
						madeFrom, sale, color));
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
				String color = rs.getString(8);
				items.add(new Item(itemSerial, itemName, itemPrice, itemImage, itemType, isSoldAlone, 0, sale, color));
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
	 * @return ReturnCommand object with the command and the array list of items in
	 *         a product
	 */
	public static ReturnCommand getAllItemsInProduct(Connection con, String productSerial) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.item WHERE zli.item.itemSerial IN(SELECT itemSerial FROM zli.item_in_product WHERE zli.item_in_product.productSerial=\""
				+ productSerial + "\");";
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
				String color = rs.getString(8);
				int amountInProduct = (int) (getAmountInProduct(con, productSerial, itemSerial).getReturnValue());
				items.add(new Item(itemSerial, itemName, itemPrice, itemImage, itemType, isSoldAlone, amountInProduct,
						sale, color));
			}
			return new ReturnCommand("GetItemsInProduct", items);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ReturnCommand getItemsForEdit(Connection con, String productSerial) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.item;";
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
				String color = rs.getString(8);
				int amountInProduct = (int) (getAmountInProduct(con, productSerial, itemSerial).getReturnValue());
				items.add(new Item(itemSerial, itemName, itemPrice, itemImage, itemType, isSoldAlone, amountInProduct,
						sale, color));
			}
			return new ReturnCommand("GetItemsForEdit", items);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ReturnCommand getItemsForCreate(Connection con) {
		return new ReturnCommand("GetItemsForCreate", getAllItems(con).getReturnValue());
	}

	/**
	 * @param con
	 * @param productSerial
	 * @param itemSerial
	 * @return ReturnCommand object with the amount of item in certein product
	 */
	public static ReturnCommand getAmountInProduct(Connection con, String productSerial, String itemSerial) {
		Statement stmt;
		String sqlQuery = "SELECT amount FROM zli.item_in_product WHERE productSerial=" + productSerial
				+ " AND itemSerial=" + itemSerial + ";";
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

	/**
	 * get all the colors of products
	 * 
	 * @param con
	 * @return
	 */
	public static ReturnCommand getAllColors(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.colors";
		ResultSet rs;
		ArrayList<String> colors = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				colors.add(rs.getString(1));
			}
			return new ReturnCommand("GetAllColors", colors);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ReturnCommand createProduct(Connection con, String name, String price, String type, String color,
			String items) {
		PreparedStatement ps;
		int productSerial = getProductNumber(con) + 1;
		String sqlQuery = "INSERT INTO zli.product (productSerial,productName,productPrice,productImage,other,productType,Color) VALUES (?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, productSerial + "");
			ps.setString(2, name);
			ps.setDouble(3, Double.parseDouble(price));
			ps.setString(4, null);
			ps.setString(5, null);
			ps.setString(6, type);
			ps.setString(7, color);
			ps.executeUpdate();
			if (insertItemOfProduct(con, productSerial + "", items)) {
				return new ReturnCommand("CreateProduct", true);
			}
			return new ReturnCommand("CreateProduct", false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("CreateProduct", false);
		}
	}

	public static boolean insertItemOfProduct(Connection con, String productSerial, String items) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO zli.item_in_product (productSerial,itemSerial,amount) VALUES (?,?,?)";
		String[] itemArr = items.split(",");
		try {
			for (String item : itemArr) {
				if (item.equals("")) {
					break;
				}
				String[] itemNameAmount = item.split(" ");
				ps = con.prepareStatement(sqlQuery);

				ps.setString(1, productSerial);
				ps.setString(2, itemNameAmount[0]);
				ps.setInt(3, Integer.parseInt(itemNameAmount[1]));
				ps.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ReturnCommand createItem(Connection con, String name, String price, String type, String color) {
		PreparedStatement ps;
		int itemSerial = getItemNumber(con) + 1;
		String sqlQuery = "INSERT INTO zli.item (itemSerial,itemName,itemPrice,itemType,itemImage,isSoldAlone,Color) VALUES (?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, itemSerial + "");
			ps.setString(2, name);
			ps.setDouble(3, Double.parseDouble(price));
			ps.setString(4, type);
			ps.setString(5, null);
			ps.setInt(6, 1);
			ps.setString(7, color);
			ps.executeUpdate();
			return new ReturnCommand("CreateItem", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("CreateItem", false);
		}
	}

	public static int getItemNumber(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT itemSerial FROM zli.item;";
		ResultSet rs = null;
		int counter = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				counter++;
			}
			return counter;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getProductNumber(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT productSerial FROM zli.product;";
		ResultSet rs = null;
		int counter = 0;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				counter++;
			}
			return counter;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static ReturnCommand editProduct(Connection con, String serial, String name, String price, String type,
			String color, String items) {
		Statement stmt;
		String sqlQuery = String.format(
				"UPDATE zli.product SET productName='%s',productPrice=%s,productType='%s',Color='%s' WHERE productSerial='%s';",
				name, price, type, color, serial);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			if (deleteItemsOfProduct(con, serial)) {
				if (addItemsOfProduct(con, serial, items)) {
					return new ReturnCommand("EditProduct", true);
				}
			}
			return new ReturnCommand("EditProduct", false);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("EditProduct", false);
		}
	}

	public static ReturnCommand editItem(Connection con, String serial, String name, String price, String type,
			String color) {
		Statement stmt;
		String sqlQuery = String.format(
				"UPDATE zli.item SET itemName='%s',itemPrice=%s,itemType='%s',Color='%s' WHERE itemSerial='%s';", name, price,
				type, color, serial);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("EditItem", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("EditItem", false);
		}
	}

	public static boolean deleteItemsOfProduct(Connection con, String productSerial) {
		Statement stmt;
		String sqlQuery = "DELETE FROM zli.item_in_product WHERE productSerial=" + productSerial + ";";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addItemsOfProduct(Connection con, String productSerial, String items) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO zli.item_in_product (productSerial,itemSerial,amount) VALUES (?,?,?)";
		String[] itemsList = items.split(",");
		try {
			for (String item : itemsList) {
				if (item.equals("")) {
					break;
				}
				String[] itemNameAmount = item.split(" ");
				ps = con.prepareStatement(sqlQuery);

				ps.setString(1, productSerial);
				ps.setString(2, itemNameAmount[0]);
				ps.setInt(3, Integer.parseInt(itemNameAmount[1]));
				ps.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
