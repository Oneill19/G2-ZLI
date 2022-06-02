package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Customer;
import entity.Order;
import entity.Report;
import entity.Store;
import entity.StoreWorker;
import entity.User;

/**
 * @author Koral Biton,Topaz Eldori
 *
 */
public class ReportQuery {

	/**
	 * method to execute sql query that return the appropirate report
	 * 
	 * @param con
	 * @param reportNme
	 * @param reportMonth
	 * @param reportYear
	 * @param storeName
	 * @return String that contain the deatils of the report
	 */
	public static ReturnCommand getReport(Connection con, String reportNme, String reportMonth, String reportYear,
			String storeName) {
		Statement stmt;
		String sqlQuery = "SELECT * From zli.reports WHERE storeName='" + storeName + "' AND reportMonth='"
				+ reportMonth + "' AND reportYear='" + reportYear + "' ;";

		ResultSet rs = null;
		String report = reportNme + " Report dated " + reportMonth + "-" + reportYear + " for ZER-LI " + storeName
				+ " store: " + "\n";
		try {
			stmt = con.createStatement();

			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {

				if (reportNme.equals("Revenue")) {
					float sum = rs.getFloat(7) + rs.getFloat(8);
					report = report + "Revenue From Products:" + rs.getFloat(7) + "\n"; // revenue product
					report = report + "Revenue From Items:" + rs.getFloat(8) + "\n"; // revenue item
					report = report + "Total Revenue:" + sum + "\n";

				} else {
					int amount = rs.getInt(5) + rs.getInt(6);
					report = report + "Quantity of orders From Products:" + rs.getInt(5) + "\n"; // order product
					report = report + "Quantity of Items:" + rs.getInt(6) + "\n"; // order item
					report = report + "Total Orders:" + amount + "\n";
				}

			} else {
				report = null;
			}
			return new ReturnCommand("getReport", report);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * method to execute sql query that generate a report automatically
	 * 
	 * @param con
	 * @param order
	 * @param productAmount
	 * @param itemAmount
	 * @param productRevenue
	 * @param itemRevenue
	 * @return
	 */
	public static ReturnCommand generateReport(Connection con, String order, String productAmount, String itemAmount,
			String productRevenue, String itemRevenue) {
		String[] orderStr = order.split(",");
		String orderDate = orderStr[5];
		String orderDateArr[] = orderDate.split("-"); // [0] year , [1] month
		String storeName = orderStr[4]; // the name of the store for this order
		Statement stmt;
		int productsAmount = Integer.parseInt(productAmount);
		int itemsAmount = Integer.parseInt(itemAmount);
		float productsRevenue = Float.parseFloat(productRevenue);
		float itemsRevenue = Float.parseFloat(itemRevenue);
		int sumProductAmount; // to update in db
		int sumItemAmount;
		float sumProductRevenue;
		float sumItemRevenue;

		// String sqlQuery = "SELECT * From zli.reports WHERE storeName='" + storeName +
		// "' AND reportMonth='"
		// orderDateArr[1] + "' AND reportYear='" + orderDateArr[0] + "' ;";
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM zli.reports WHERE storeName = ? AND reportMonth = ? AND reportYear = ?;");
			ps.setString(1, storeName);
			ps.setString(2, orderDateArr[1]);
			ps.setString(3, orderDateArr[0]);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				// calculate the quarterly
				int quarterlyVal;
				if (orderDateArr[1].equals("01") || orderDateArr[1].equals("02") || orderDateArr[1].equals("03"))
					quarterlyVal = 1;
				else if (orderDateArr[1].equals("04") || orderDateArr[1].equals("05") || orderDateArr[1].equals("06"))
					quarterlyVal = 2;
				else if (orderDateArr[1].equals("07") || orderDateArr[1].equals("08") || orderDateArr[1].equals("09"))
					quarterlyVal = 3;
				else
					quarterlyVal = 4;
				PreparedStatement insertPs = con.prepareStatement(
						"INSERT INTO zli.reports ( storeName, reportYear, reportMonth,amountProduct,amountItem,revenueProduct,revenueItem,quarterly)VALUES  (?,?,?,?,?,?,?,?);");
				insertPs.setString(1, storeName);
				insertPs.setString(2, orderDateArr[0]);
				insertPs.setString(3, orderDateArr[1]);
				insertPs.setInt(4, productsAmount);
				insertPs.setInt(5, itemsAmount);
				insertPs.setFloat(6, productsRevenue);
				insertPs.setFloat(7, itemsRevenue);
				insertPs.setInt(8, quarterlyVal);

				insertPs.executeUpdate();
			} else {
				sumProductAmount = rs.getInt(5) + productsAmount;
				sumItemAmount = rs.getInt(6) + itemsAmount;
				sumProductRevenue = rs.getFloat(7) + productsRevenue;
				sumItemRevenue = rs.getFloat(8) + itemsRevenue;
				PreparedStatement updatePs = con.prepareStatement(
						"UPDATE  zli.reports SET amountProduct = ? WHERE amountItem = ? AND revenueProduct = ? AND revenueItem = ?;");
				updatePs.setInt(1, sumProductAmount);
				updatePs.setInt(2, sumItemAmount);
				updatePs.setFloat(3, sumProductRevenue);
				updatePs.setFloat(4, sumItemRevenue);
				updatePs.executeUpdate();
			}
			return new ReturnCommand("generateReport", null);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

	}

	public static ReturnCommand getReportByQuarterly1(Connection con, String quareterly, String year, String store) {

		Statement stmt;
		String sqlQuery = "SELECT * From zli.reports WHERE storeName='" + store + "' AND quarterly='" + quareterly
				+ "' AND reportYear='" + year + "' ;";
		ResultSet rs = null;
		ArrayList<Report> reports = new ArrayList<>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				reports.add(new Report(rs.getInt(1), // ReportID
						rs.getString(2), // StoreName
						rs.getString(3), // ReportYear
						rs.getString(4), // ReportMonth
						rs.getInt(5), // AmountProducts
						rs.getInt(6), // AmountItems
						rs.getFloat(7), // RevenueProducts
						rs.getFloat(8), // RevenueItems
						rs.getInt(9) // Quarterly
						
				));
			}
			return new ReturnCommand("GetReportByQuarter1",reports );
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ReturnCommand getReportByQuarterly2(Connection con, String quareterly, String year, String store) {

		Statement stmt;
		String sqlQuery = "SELECT * From zli.reports WHERE storeName='" + store + "' AND quarterly='" + quareterly
				+ "' AND reportYear='" + year + "' ;";
		ResultSet rs = null;
		ArrayList<Report> reports = new ArrayList<>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				reports.add(new Report(rs.getInt(1), // ReportID
						rs.getString(2), // StoreName
						rs.getString(3), // ReportYear
						rs.getString(4), // ReportMonth
						rs.getInt(5), // AmountProducts
						rs.getInt(6), // AmountItems
						rs.getFloat(7), // RevenueProducts
						rs.getFloat(8), // RevenueItems
						rs.getInt(9) // Quarterly
						
				));
			}
			return new ReturnCommand("GetReportByQuarter2",reports );
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
