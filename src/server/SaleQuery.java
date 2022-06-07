package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.AbstractProduct;
import entity.CustomProduct;
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
		int idSale = 0;
		String insertQuery = null, selectQuery = null;

//		 insert the new sale
		insertQuery = "INSERT INTO sale (saleName, discountAmount)" + "VALUES ('" + saleName + "'," + saleAmount + ");";
//		System.out.println(insertQuery);//debug
		try {
			ps = con.prepareStatement(insertQuery);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("insertNewSale", null);
		}

		selectQuery = "SELECT idSale from sale order by idSale desc limit 1";
		// get the saleID
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectQuery);
			if (rs.next())
				idSale = rs.getInt(1);
			System.out.println(idSale);// debug

			return new ReturnCommand("insertNewSale", (Integer) idSale);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("insertNewSale", null);
		}
	}

	/**
	 * @param con    connection to client
	 * @param serial the serialNumber of the item
	 * @param idSale the idSale of the item
	 * @return nothing.
	 */
	public static ReturnCommand insertApInSale(Connection con, String serial, String idSale, String table) {
		PreparedStatement ps;
		String insertQuery = null, updateQuery = null;

		insertQuery = "INSERT INTO " + table + "_in_sale (" + table + "Serial, idSale)" + "VALUES (?,?);";

		String[] allAp = serial.split(" ");
		System.out.println(table);
//		insert the new sale
		for (String apSerial : allAp) {
			try {
				ps = con.prepareStatement(insertQuery);
				ps.setString(1, apSerial);
				ps.setInt(2, Integer.parseInt(idSale));
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("problem");
				return new ReturnCommand("insertApInSale", false);
			}
		}

//		update the idSale of ap
		for (String apSerial : allAp) {
			updateQuery = "UPDATE item SET idSale = " + idSale + " WHERE " + table + "Serial = '" + apSerial + "';";
			try {
				ps = con.prepareStatement(updateQuery);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("problem");
				return new ReturnCommand("insertApInSale", false);
			}
		}
		return new ReturnCommand("insertApInSale", true);
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
		String[] allProducts = serial.split(" ");

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
		String selectQuery = " SELECT * " + "FROM sale as s "
				+ "INNER JOIN item_in_sale as isale on s.idSale=isale.idSale "
				+ "INNER JOIN item as i on isale.itemSerial=i.itemSerial " + "WHERE s.idSale='" + saleID + "';";
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
				int amountInProduct = 0;

				int discountAmount = rs.getInt(3);
				double priceWithSale = (price - (price * (discountAmount / 100.0)));

				itemsInSaleArray.add(new Item(serialNumber, name, price, imagePath, type, idSale, isSoldAlone,
						amountInProduct, color, priceWithSale));
			}
			if (itemsInSaleArray.size() > 0)
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
		String selectQuery = " SELECT * " + "FROM sale as s "
				+ "INNER JOIN product_in_sale as ip on s.idSale=ip.idSale "
				+ "INNER JOIN product as p on ip.productSerial=p.productSerial " + "WHERE s.idSale='" + saleID + "';";
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
				ArrayList<Item> madeFrom = (ArrayList<Item>) ProductsQuery.getAllItemsInProduct(conn, serialNumber)
						.getReturnValue();
				int idSale = rs.getInt(1);
				String color = rs.getString(14);

				int discountAmount = rs.getInt(3);
				double priceWithSale = (price - (price * (discountAmount / 100.0)));

				productInSaleArray.add(new Product(serialNumber, name, price, type, imagePath, other, madeFrom, idSale,
						color, priceWithSale));
			}
			if (productInSaleArray.size() > 0)
				return new ReturnCommand("select_product_in_sale", productInSaleArray);
			else
				return new ReturnCommand("select_product_in_sale", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static ReturnCommand select_custom_in_sale(Connection conn, String saleID) {
		Statement stmt;
		String selectQuery = " SELECT * FROM sale as s "
				+ "INNER JOIN custom_in_sale as ip on s.idSale=ip.idSale "
				+ "INNER JOIN custom as p on ip.customSerial=p.customSerial "
				+ "WHERE s.idSale='"+saleID+"';";
//		System.out.println(selectQuery); //debug
		ResultSet rs = null;
		ArrayList<CustomProduct> customInSaleArray = new ArrayList<CustomProduct>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
							String serialNumber = rs.getString(5);
							String name = rs.getString(8);
							double price = Double.parseDouble(rs.getString(9).split("-")[1]);
							String priceRange = rs.getString(9);
							String color = rs.getString(10);
							String productList = rs.getString(11);
							String itemList = rs.getString(12);
							int idSale = rs.getInt(1);
							double priceWithSale = price-(price*(rs.getInt(3)/100.0));
				
							ArrayList<Item> madeFromItems = (ArrayList<Item>) ProductsQuery.getAllItemsInProduct(conn, serialNumber).getReturnValue();
							ArrayList<Product> madeFromProduct = (ArrayList<Product>) getProductsBySerials(conn, serialNumber).getReturnValue();
							CustomProduct custom = new CustomProduct(serialNumber, name, price, priceRange, color, productList, itemList, idSale, priceWithSale);
							custom.setItemArrayList(madeFromItems);
							custom.setProductArrayList(madeFromProduct);
							customInSaleArray.add(custom);
			}
			if(customInSaleArray.size()>0)
				return new ReturnCommand("select_custom_in_sale", customInSaleArray);
			else
				return new ReturnCommand("select_custom_in_sale", null);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("select_custom_in_sale", null);
		}
	}

	/**
	 * @param con
	 * @return ReturnCommand object with the command and the array list of products
	 */
	@SuppressWarnings("unchecked")
	public static ReturnCommand getProductsBySerials(Connection con, String serials) {
		PreparedStatement ps;
		String sqlQuery = "SELECT * From zli.product where productSerial=?";
		ArrayList<AbstractProduct> products = new ArrayList<>();
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sqlQuery);
			for (String serial : serials.split(",")) {
				ps.setString(1, serial.split(" ")[0]);
				rs = ps.executeQuery(sqlQuery);
				while (rs.next()) {
					String productSerial = rs.getString(1);
					String productName = rs.getString(2);
					double productPrice = rs.getDouble(3);
					String productImage = rs.getString(4);
					String other = rs.getString(5);
					String productType = rs.getString(6);
					int idSale = rs.getInt(7);
					String color = rs.getString(8);
					ArrayList<Item> madeFrom = (ArrayList<Item>) ProductsQuery.getAllItemsInProduct(con, productSerial)
							.getReturnValue();
					Product product = new Product(productSerial, productName, productPrice, productType, productImage,
							other, madeFrom, idSale, color);
					product.setAmount(Integer.parseInt(serial.split(" ")[1]));
					products.add(product);
				}
			}
			return new ReturnCommand("GetAllProducts", products);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ReturnCommand updateSaleStatus(Connection conn, String idSale, String newStatus) {
		PreparedStatement ps;
		String updateQuery = "UPDATE sale SET status = '" + newStatus + "' WHERE idSale = '" + idSale + "';";
		;
		System.out.println(updateQuery);// debug

		try {
			ps = conn.prepareStatement(updateQuery);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("updateSaleStatus", false);
		}
		return new ReturnCommand("updateSaleStatus", true);
	}

	/**
	 * nuliify all abstractProducts(item, product) that are CURRENTLY connected with
	 * sale 'idSale'
	 * 
	 * @param conn   connection to client
	 * @param idSale the id of the sale
	 * @return
	 */
	public static ReturnCommand nullifyIdSaleOfApWithCurrentIdSale(Connection conn, String idSale, String table) {
		Statement stmt;
		String selectQuery = "SELECT * FROM " + table + "in_sale AS itemSale " + "INNER JOIN " + table + " AS i ON i."
				+ table + "Serial = itemSale." + table + "Serial " + "WHERE i.idSale=itemSale.idSale AND i.idSale='"
				+ idSale + "';";
//		System.out.println("nullify: "+selectQuery);//debug
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
//			has all apSerial of items\products which idSale needs to be deleted.
			while (rs.next()) {
				sb.append(Integer.toString(rs.getInt(1))).append(" ");
			}
//			nullify the idSale of all items\products that had currently that '@idSale'
//			
			ReturnCommand rc = changeApIdSale(conn, sb.toString(), Integer.toString(0), table);
			boolean retVal = (boolean) rc.getReturnValue();
			if (!retVal) {
				return new ReturnCommand("updateSaleStatus", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("updateSaleStatus", false);
		}
		return new ReturnCommand("updateSaleStatus", true);
	}

	/**
	 * @param conn    connection to client
	 * @param serials the items which column idSale should be set to '@idSale'
	 * @param idSale  the idSale to give all 'items'
	 * @return
	 */
	public static ReturnCommand changeApIdSale(Connection conn, String serials, String idSale, String table) {
		String updateQuery = "UPDATE " + table + " SET idSale=? WHERE " + table + "Serial = ?";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(updateQuery);
			for (String serial : serials.split(" ")) {
				ps.setInt(1, Integer.parseInt(idSale));
				ps.setString(2, serial);
				ps.executeUpdate();
//				System.out.println("change id sale: "+ps); //debug
			}
			return new ReturnCommand("changeApIdSale", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("changeApIdSale", false);
		}
	}

	public static ReturnCommand updateSale(Connection conn, String idSale, String saleName, String discountAmount) {
		String updateQuery = "UPDATE sale SET saleName=?, discountAmount=? WHERE idSale=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(updateQuery);
			ps.setString(1, saleName);
			ps.setInt(2, Integer.parseInt(discountAmount));
			ps.setInt(3, Integer.parseInt(idSale));
			ps.executeUpdate();
			System.out.println("update sale: " + ps);
			return new ReturnCommand("changeItemIdSale", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("changeItemIdSale", false);
		}
	}

	public static ReturnCommand deleteAp_in_sale(Connection conn, String serial, String idSale, String table) {
		String deleteQuery = "DELETE FROM " + table + "_in_sale WHERE idSale=" + idSale + " AND " + table + "serial = "
				+ serial + ";";
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(deleteQuery);
			ps.executeUpdate();
			System.out.println("change id sale: " + ps); // debug
			return new ReturnCommand("deleteAp_in_sale", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("deleteAp_in_sale", false);
		}
	}
}
