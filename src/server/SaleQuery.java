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
		int idSale=0;
		String insertQuery = null, selectQuery = null;

//		 insert the new sale
		insertQuery = "INSERT INTO sale (saleName, discountAmount)" + "VALUES ('" + saleName + "'," + saleAmount+ ");";
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
			System.out.println(idSale);//debug
			
			return new ReturnCommand("insertNewSale", (Integer)idSale);
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
	public static ReturnCommand insertItemInSale(Connection con, String serial, String idSale) {
		PreparedStatement ps;
		String insertQuery = "INSERT INTO item_in_sale(itemSerial, idSale)" + "VALUES (?,?);";;
		String[] allItems = serial.split(" ");

		System.out.println("idSale in SaleQuery: "+idSale); 
				
//		insert the new sale
		for (String itemSerial : allItems) {
			try {
				ps = con.prepareStatement(insertQuery);
				ps.setString(1,itemSerial);
				ps.setInt(2, Integer.parseInt(idSale));
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("problem");
				return new ReturnCommand("insertItemInSale",false);
			}
		}

//		update the idSale of items
		for (String itemSerial : allItems) {
			String updateQuery = "UPDATE item SET idSale = " + idSale + " WHERE itemSerial = '" + itemSerial + "';";
			try {
				ps = con.prepareStatement(updateQuery);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("problem");
				return new ReturnCommand("insertItemInSale",false);
			}
		}
		return new ReturnCommand("insertItemInSale",true);
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
					
					int discountAmount = rs.getInt(3);
					double priceWithSale = (price-(price*(discountAmount/100.0)));
					
					itemsInSaleArray.add(new Item(serialNumber, name, price, imagePath, type, idSale, isSoldAlone, amountInProduct,color, priceWithSale));
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
					

					int discountAmount = rs.getInt(3);
					double priceWithSale = (price-(price*(discountAmount/100.0)));
					
					productInSaleArray.add(new Product(serialNumber, name, price, type, imagePath, other, madeFrom, idSale, color,priceWithSale));
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
	
	public static ReturnCommand updateSaleStatus(Connection conn, String idSale, String newStatus) {
		PreparedStatement ps;
		String updateQuery = "UPDATE sale SET status = '" + newStatus + "' WHERE idSale = '" + idSale + "';";;
		System.out.println(updateQuery);//debug
		
		try {
			ps = conn.prepareStatement(updateQuery);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("updateSaleStatus",false);
		}
		return new ReturnCommand("updateSaleStatus",true);
	}
	
	

	/**
	 * nuliify all items that are CURRENTLY connected with sale 'idSale'
	 * @param conn		connection to client
	 * @param idSale	the id of the sale
	 * @return
	 */
	public static ReturnCommand nullifyIdSaleOfItemsWithCurrentIdSale(Connection conn, String idSale) {
		Statement stmt;
		String selectQuery = "	select * from item_in_sale as itemSale "
				+ "	inner join item as i on i.itemSerial=itemSale.itemSerial "
				+ "	where i.idSale=itemSale.idSale AND i.idSale='"+idSale+"';";
		System.out.println(selectQuery);//debug
		ResultSet rs=null;
		StringBuilder sb = new StringBuilder();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectQuery);
			//has all itemSerial of items that's idSale need to be deleted.
			while(rs.next()) {
				sb.append(Integer.toString(rs.getInt(1))).append(" ");
			}
			//nullify the idSale of all items that had currently that '@idSale'
			
			ReturnCommand rc = changeItemIdSale(conn, sb.toString(), Integer.toString(0));
			boolean retVal = (boolean) rc.getReturnValue();
			if ( !retVal) {
				return new ReturnCommand("updateSaleStatus",false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnCommand("updateSaleStatus",false);
		}
		return new ReturnCommand("updateSaleStatus",true);
	}
	
	
	/**
	 * @param conn		connection to client
	 * @param items		the items which column idSale should be set to '@idSale'
	 * @param idSale	the idSale to give all 'items'
	 * @return
	 */
	public static ReturnCommand changeItemIdSale(Connection conn, String items, String idSale) {
		String[] itemsArray = items.split(" ");
		String updateQuery = "UPDATE item SET idSale=? WHERE itemSerial=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(updateQuery);
			for(String s : itemsArray) {
				ps.setInt(1, Integer.parseInt(idSale));
				ps.setString(2, s);
				ps.executeUpdate();
				System.out.println(ps);
			}
			return new ReturnCommand("changeItemIdSale",true);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("changeItemIdSale",false);
		}
		
	}
}
