package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Store;

public class StoreQuery {
	
	public static ReturnCommand getAllStores(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.store";
		ArrayList<Store> stores = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				String storeName = rs.getString(1);
				String storeAddress = rs.getString(2);
				String storePhone = rs.getString(3);
				stores.add(new Store(storeName, storeAddress, storePhone));
			}
 			return new ReturnCommand("GetAllStores", stores);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
