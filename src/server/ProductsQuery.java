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

	/**
	 * get item object to edit
	 * 
	 * @param con
	 * @param productSerial
	 * @return Item
	 */
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
	 * get amount of item in certain product
	 * 
	 * @param con
	 * @param productSerial
	 * @param itemSerial
	 * @return ReturnCommand object with the amount of item in certain product
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
	 * @return string array of colors
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

	/**
	 * insert a new product to the data base
	 * 
	 * @param con
	 * @param name
	 * @param price
	 * @param type
	 * @param color
	 * @param items
	 * @return true is success
	 */
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
			ps.setString(4, "/common/Assets/flower.png");
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

	/**
	 * insert the items the product made from to the database
	 * 
	 * @param con
	 * @param productSerial
	 * @param items
	 * @return true if success
	 */
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

	/**
	 * insert a new item to the data base 
	 * 
	 * @param con
	 * @param name
	 * @param price
	 * @param type
	 * @param color
	 * @return true if success
	 */
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
			ps.setString(5, "/common/Assets/flower.png");
			ps.setInt(6, 1);
			ps.setString(7, color);
			ps.executeUpdate();
			return new ReturnCommand("CreateItem", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("CreateItem", false);
		}
	}

	/**
	 * get the number of items in the data base
	 * 
	 * @param con
	 * @return int
	 */
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

	/**
	 * get the number of products in the data base
	 * 
	 * @param con
	 * @return int
	 */
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

	/**
	 * edit an existing product
	 * 
	 * @param con
	 * @param serial
	 * @param name
	 * @param price
	 * @param type
	 * @param color
	 * @param items
	 * @return true if success
	 */
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

	/**
	 * edit existing item
	 * 
	 * @param con
	 * @param serial
	 * @param name
	 * @param price
	 * @param type
	 * @param color
	 * @return true if success
	 */
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

	/**
	 * delete all items that builds a product from zli.item_in_product
	 * 
	 * @param con
	 * @param productSerial
	 * @return true if success
	 */
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

	/**
	 * add all items that build a product to zli.item_in_product
	 * 
	 * @param con
	 * @param productSerial
	 * @param items
	 * @return true if success
	 */
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
	
	/**
	 * get number of custom products in the data base
	 * 
	 * @param con
	 * @return true if success
	 */
	public static ReturnCommand getAmountOfCustomProduct(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM custom;";
		int counter = 0;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				counter++;
			}
			return new ReturnCommand("GetAmountOfCustomProduct", counter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("GetAmountOfCustomProduct", 0);
		}
	}
	
	/**
	 * insert custom product to the data base
	 * 
	 * @param con
	 * @param id
	 * @param name
	 * @param price
	 * @param color
	 * @param productLists
	 * @param itemLists
	 * @return true if success
	 */
	public static ReturnCommand addCustomProduct(Connection con, String id, String name, String price, String color, String productLists, String itemLists) {
		PreparedStatement ps;
		String sqlQuery = "INSERT INTO custom (customSerial,customName,priceRange,color,productList,itemList,idSale) VALUES (?,?,?,?,?,?,0);";
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, price);
			ps.setString(4, color);
			ps.setString(5, productLists);
			ps.setString(6, itemLists);
			ps.executeUpdate();
			return new ReturnCommand("AddCustomProduct", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("AddCustomProduct", false);
		}
	}
	
	public static ReturnCommand getDiscountAmount(Connection con, int idSale) {
		Statement stmt;
		String sqlQuery = "SELECT discountAmount FROM zli.sale WHERE status='ACTIVE' AND idSale=" + idSale +";";
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				return new ReturnCommand("GetDiscountAmount", rs.getInt(1));
			}
			return new ReturnCommand("GetDiscountAmount", 0);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("GetDiscountAmount", 0);
		}
	}
	
	
	public static ReturnCommand getItemsBySerial(Connection conn, String serials) {
		PreparedStatement ps;
		String selectQuery = "SELECT * from item WHERE itemSerial=?";
		ResultSet rs;
		Item item;
		ArrayList<Item> itemArrayList = new ArrayList<Item>();
		try {
			ps = conn.prepareStatement(selectQuery);
			for(String serial : serials.split(",")) {
				ps.setString(1, serial.split(" ")[0]);
				rs = ps.executeQuery();
				while(rs.next()) {
					String serialNumber = rs.getString(1);
					String name = rs.getString(2);
					double price = rs.getDouble(3);
					String imagePath = rs.getString(5);
					String type = rs.getString(4);
					int idSale = rs.getInt(7);
					boolean isSoldAlone = rs.getBoolean(6);
					String color = rs.getString(8);
					
					item=new Item(serialNumber, name, price, imagePath, type, idSale, isSoldAlone, 0, color, price);
					item.setAmount(Integer.parseInt(serial.split(" ")[1]));
					itemArrayList.add(item);
				}
			}
			return new ReturnCommand("getItemsBySerial", itemArrayList);
		} catch (SQLException e) {
			return new ReturnCommand("getItemsBySerial", null);
		}
	}
	
	
	
}
