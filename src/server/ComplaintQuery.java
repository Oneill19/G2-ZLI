package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import common.ReturnCommand;
import entity.Complaint;
import entity.ComplaintReport;
import entity.Order;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class ComplaintQuery {

	/**
	 * adding a new complaint to the database
	 * 
	 * @param con
	 * @param complaint
	 * @return true if complaint added successfully
	 */
	public static ReturnCommand addComplaint(Connection con, String orderNumber, String customerId, String workerId, String desc, String date, String time, String status, String isReminded) {
		Statement stmt;
		String storeName = ((Order)getOrderByNumber(con, Integer.parseInt(orderNumber)).getReturnValue()).getFromStore();
		String inputs = String.format("%s,%s,%s,'%s','%s','%s','%s','%s',%s", orderNumber, customerId, workerId, storeName, desc, date, time, status, isReminded);
		String sqlQuery = "INSERT INTO zli.complaint (OrderNumber,CustomerId,WorkerID,StoreName,ComplaintDetails,RecieveDate,RecieveTime,Status,IsReminded) VALUES ("
				+ inputs + ");";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("AddComplaint", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * get all to complaints by the id of the worker made them
	 * 
	 * @param con
	 * @param workerId
	 * @return array of the complaints of the worker
	 */
	public static ReturnCommand getAllOpenComplaintsOfWorker(Connection con, int workerId) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.complaint WHERE WorkerID=" + workerId + " AND Status='OPEN'";
		ArrayList<Complaint> complaints = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				int complaintId = rs.getInt(1);
				int orderNumber = rs.getInt(2);
				int customerId = rs.getInt(3);
				int workerIdinDB = rs.getInt(4);
				String storeName = rs.getString(5);
				String complaintDetails = rs.getString(6);
				LocalDate reciveDate = LocalDate.parse(rs.getString(7));
				LocalTime reciveTime = LocalTime.parse(rs.getString(8));
				String status = rs.getString(9);
				boolean isReminded = rs.getInt(10) == 0 ? false : true;
				float refund = rs.getFloat(11);
				String refundDetails = rs.getString(12);
				complaints.add(new Complaint(complaintId, orderNumber, customerId, workerIdinDB, storeName, complaintDetails,
						reciveDate, reciveTime, status, isReminded, refund, refundDetails));
			}
			return new ReturnCommand("GetAllOpenComplaintsOfWorker", complaints);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * check if the order number and user id are match
	 * 
	 * @param con
	 * @param orderNumber
	 * @param userId
	 * @return true if the order exist
	 */
	public static ReturnCommand orderExist(Connection con, int orderNumber, int userId) {
		Statement stmt;
		String sqlQuery = "SELECT orderNumber FROM zli.orders WHERE orderNumber=" + orderNumber + " AND cutomerID="
				+ userId + ";";
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				return new ReturnCommand("OrderExist", true);
			} else {
				return new ReturnCommand("OrderExist", false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("OrderExist", false);
		}
	}

	/**
	 * update the reminded column in the db
	 * 
	 * @param con
	 * @param complaintId
	 * @return true if the reminding was successfull
	 */
	public static ReturnCommand reminded(Connection con, int complaintId) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.complaint SET IsReminded=1 WHERE ComplaintId=" + complaintId + ";";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("Reminded", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("Reminded", false);
		}
	}

	/**
	 * close complaint status
	 * 
	 * @param con
	 * @param complaintId
	 * @return
	 */
	public static ReturnCommand closeComplaint(Connection con, int complaintId) {
		Statement stmt;
		String sqlQuery = "UPDATE zli.complaint SET Status='CLOSED' WHERE ComplaintId=" + complaintId + ";";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("CloseComplaint", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("CloseComplaint", false);
		}
	}

	/**
	 * refund for full amount
	 * 
	 * @param con
	 * @param complaintId
	 * @param customerId
	 * @param orderNumber
	 * @param refundDetails
	 * @return
	 */
	public static ReturnCommand refundForComplaintFullAmount(Connection con, int complaintId, int customerId,
			int orderNumber, String refundDetails) {
		Statement stmt;
		String sqlQuery;
		ResultSet rs;
		double refundAmount = 0;

		// get the amount to refund from the database
		try {
			sqlQuery = "SELECT totalPrice FROM zli.orders WHERE orderNumber=" + orderNumber + ";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				refundAmount = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintFullAmount", false);
		}

		// add the refund to customer balance
		try {
			sqlQuery = "SELECT balance FROM zli.user_customer WHERE userID=" + customerId + ";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				refundAmount += rs.getFloat(1);
			}

			sqlQuery = "UPDATE zli.user_customer SET balance=" + refundAmount + " WHERE userID=" + customerId + ";";
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintFullAmount", false);
		}

		// change complaint status to CLOSED
		try {
			sqlQuery = "UPDATE zli.complaint SET Status='CLOSED', RefundDetails='" + refundDetails + "',Refund="
					+ refundAmount + " WHERE ComplaintID=" + complaintId + ";";
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintFullAmount", false);
		}

		return new ReturnCommand("RefundForComplaintFullAmount", true);
	}

	/**
	 * refund for certain amount
	 * 
	 * @param con
	 * @param complaintId
	 * @param customerId
	 * @param orderNumber
	 * @param refundAmount
	 * @param refundDetails
	 * @return
	 */
	public static ReturnCommand refundForComplaintNotFull(Connection con, int complaintId, int customerId,
			int orderNumber, float refundAmount, String refundDetails) {
		Statement stmt;
		String sqlQuery;
		ResultSet rs;

		// change complaint status to CLOSED
		try {
			sqlQuery = "UPDATE zli.complaint SET Status='CLOSED', RefundDetails='" + refundDetails + "',Refund="
					+ refundAmount + " WHERE ComplaintID=" + complaintId + ";";
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintNotFull", false);
		}

		// add the refund to customer balance
		try {
			sqlQuery = "SELECT balance FROM zli.user_customer WHERE userID=" + customerId + ";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				refundAmount += rs.getFloat(1);
			}

			sqlQuery = "UPDATE zli.user_customer SET balance=" + refundAmount + " WHERE userID=" + customerId + ";";
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintNotFull", false);
		}
		return new ReturnCommand("RefundForComplaintNotFull", true);
	}

	/**
	 * get and order object by order id
	 * 
	 * @param con
	 * @param orderNumber
	 * @return Order
	 */
	public static ReturnCommand getOrderByNumber(Connection con, int orderNumber) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.orders WHERE orderNumber=" + orderNumber + ";";
		Order order = null;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				int orderId = rs.getInt(1);
				double totalPrice = rs.getDouble(2);
				String greetingCard = rs.getString(3);
				String color = rs.getString(4);
				String orderDesc = rs.getString(5);
				String fromStore = rs.getString(6);
				LocalDate orderCreationDate = LocalDate.parse(rs.getString(7));
				LocalTime orderCreationTime = LocalTime.parse(rs.getString(8));
				int customerId = rs.getInt(9);
				String paymentMethod = rs.getString(10);
				String orderStatus = rs.getString(11);
				String confirmedDate = rs.getString(12);
				String completeDate = rs.getString(13);
				String deliveryMethod = rs.getString(14);
				LocalDate supplyDate = LocalDate.parse(rs.getString(15));
				LocalTime supplyTime = LocalTime.parse(rs.getString(16));
				order = new Order(orderId, totalPrice, greetingCard, color, orderDesc, fromStore, orderCreationDate,
						orderCreationTime, customerId, paymentMethod, orderStatus, confirmedDate, completeDate,
						deliveryMethod, supplyDate, supplyTime);
			}
			return new ReturnCommand("GetOrderByNumber", order);
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * get a complaint report object by store, year and quarter
	 * 
	 * @param con
	 * @param year
	 * @param quarter
	 * @param store
	 * @return
	 */
	public static ReturnCommand getComplaintReportByStore(Connection con, String year, String quarter, String store) {
		int[] numberOfOrders = getNumberOrOrdersOfQuearterByStore(con, year, quarter, store);
		int[] numberOfComplaints = getNumberOfComplaintOfQuarterByStore(con, year, quarter, store);
		ComplaintReport rp = new ComplaintReport(year, quarter, numberOfOrders, numberOfComplaints);
		return new ReturnCommand("GetComplaintReportByStore", rp);
	}
	
	/**
	 * get the number of orders in certain year and quarter by store
	 * 
	 * @param con
	 * @param year
	 * @param quarter
	 * @param storeName
	 * @return
	 */
	public static int[] getNumberOrOrdersOfQuearterByStore(Connection con, String year, String quarter, String storeName) {
		int[] counter = new int[3];
		switch (quarter) {
		case "1":
			counter[0] = getNumberOfOrdersByYearAndMonthByStore(con, year, "01", storeName);
			counter[1] = getNumberOfOrdersByYearAndMonthByStore(con, year, "02", storeName);
			counter[2] = getNumberOfOrdersByYearAndMonthByStore(con, year, "03", storeName);
			break;
		case "2":
			counter[0] = getNumberOfOrdersByYearAndMonthByStore(con, year, "04", storeName);
			counter[1] = getNumberOfOrdersByYearAndMonthByStore(con, year, "05", storeName);
			counter[2] = getNumberOfOrdersByYearAndMonthByStore(con, year, "06", storeName);
			break;
		case "3":
			counter[0] = getNumberOfOrdersByYearAndMonthByStore(con, year, "07", storeName);
			counter[1] = getNumberOfOrdersByYearAndMonthByStore(con, year, "08", storeName);
			counter[2] = getNumberOfOrdersByYearAndMonthByStore(con, year, "09", storeName);
			break;
		default:
			counter[0] = getNumberOfOrdersByYearAndMonthByStore(con, year, "10", storeName);
			counter[1] = getNumberOfOrdersByYearAndMonthByStore(con, year, "11", storeName);
			counter[2] = getNumberOfOrdersByYearAndMonthByStore(con, year, "12", storeName);
		}
		return counter;
	}
	
	/**
	 * get number of orders in certain year and month
	 * 
	 * @param con
	 * @param year
	 * @param month
	 * @param storeName
	 * @return
	 */
	public static int getNumberOfOrdersByYearAndMonthByStore(Connection con, String year, String month, String storeName) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.orders WHERE fromStore='" + storeName + "' AND orderCreationDate LIKE '" + year + "-" + month + "-%'";
		int counter = 0;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				counter++;
			}
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return counter;
		}
	}
	
	/**
	 * get the number of complaints in certain year and quarter by store
	 * 
	 * @param con
	 * @param year
	 * @param quarter
	 * @param storeName
	 * @return
	 */
	public static int[] getNumberOfComplaintOfQuarterByStore(Connection con, String year, String quarter, String storeName) {
		int[] counter = new int[3];
		switch (quarter) {
		case "1":
			counter[0] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "01", storeName);
			counter[1] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "02", storeName);
			counter[2] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "03", storeName);
			break;
		case "2":
			counter[0] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "04", storeName);
			counter[1] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "05", storeName);
			counter[2] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "06", storeName);
			break;
		case "3":
			counter[0] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "07", storeName);
			counter[1] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "08", storeName);
			counter[2] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "09", storeName);
			break;
		default:
			counter[0] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "10", storeName);
			counter[1] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "11", storeName);
			counter[2] = getNumberOfComplaintsByYearAndMonthByStore(con, year, "12", storeName);
		}
		return counter;
	}
	
	/**
	 * get number of complaints in certain year and month
	 * 
	 * @param con
	 * @param year
	 * @param month
	 * @param storeName
	 * @return
	 */
	public static int getNumberOfComplaintsByYearAndMonthByStore(Connection con, String year, String month, String storeName) {
		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.complaint WHERE StoreName='" + storeName + "' AND RecieveDate LIKE '" + year + "-" + month + "-%'";
		int counter = 0;
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				counter++;
			}
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return counter;
		}
	}
}
