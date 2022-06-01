package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Customer;
import entity.Order;
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
				System.out.println(reportNme);
				System.out.println(reportMonth);
				System.out.println(reportYear);
				System.out.println(storeName);
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

}