package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ReturnCommand;

public class StoreQuery {
	
	public static ReturnCommand getAllStores(Connection con) {
		Statement stmt;
		String sqlQuery = "SELECT storeName From zli.store";
		ArrayList<String> stores = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				String storeName = rs.getString(1);
				stores.add(storeName);
			}
 			return new ReturnCommand("GetAllStores", stores);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
