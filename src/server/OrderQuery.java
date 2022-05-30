package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.ReturnCommand;

public class OrderQuery {
	
	/**
	 * @param con
	 * @param orderString - contains the VALUES to INSERT TO the DB
	 * @return ReturnCommand
	 */
	public static ReturnCommand saveOrderToDB(Connection con, String orderString) {
		PreparedStatement stmt;
		System.out.println(orderString);
		String sqlQuery = "INSERT INTO zli.orders (totalPrice, greetingCard, color, orderDesc,"
				+ "fromStore, orderCreationDate, orderCreationTime, cutomerID, paymentMethod, "
				+ "orderStatus, confirmedDate, completeDate, deliveryMethod, supplyDate, supplyTime) VALUES"
				+ "(" + orderString + ");";
		try {
			stmt = con.prepareStatement(sqlQuery);
			stmt.executeUpdate();
 			return new ReturnCommand("orderSavedToDB", orderString);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
