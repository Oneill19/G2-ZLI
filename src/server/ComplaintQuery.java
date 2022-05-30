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

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class ComplaintQuery {

	public static ReturnCommand addComplaint(Connection con, String complaint) {
		Statement stmt;
		String sqlQuery = "INSERT INTO zli.complaint (ComplaintID,OrderNumber,CustomerId,WorkerID,ComplaintDetails,RecieveDate,RecieveTime,Status,IsReminded,Refund,RefundDetails) VALUES (" + complaint + ");";
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
			return new ReturnCommand("AddComplaint", true);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ReturnCommand getAllOpenComplaints(Connection con, int workerId) {
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
				String complaintDetails = rs.getString(5);
				LocalDate reciveDate = LocalDate.parse(rs.getString(6));
				LocalTime reciveTime = LocalTime.parse(rs.getString(7));
				String status = rs.getString(8);
				boolean isReminded = rs.getInt(9) == 0 ? false : true;
				float refund = rs.getFloat(10);
				String refundDetails = rs.getString(11);
				complaints.add(new Complaint(complaintId, orderNumber, customerId, workerIdinDB, complaintDetails, reciveDate, reciveTime, status, isReminded, refund, refundDetails));
			}
			return new ReturnCommand("GetAllOpenComplaints", complaints);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ReturnCommand orderExist(Connection con, int orderNumber) {
		Statement stmt;
		String sqlQuery = ("SELECT orderNumber FROM zli.orders WHERE orderNumber=" + orderNumber + ";");
		ResultSet rs;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			if (rs.next()) {
				return new ReturnCommand("OrderExist", true);
			}
			else {
				return new ReturnCommand("OrderExist", false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("OrderExist", false);
		}
	}
	
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
	
	public static ReturnCommand refundForComplaintFullAmount(Connection con, int complaintId, int customerId, int orderNumber, String refundDetails) {
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
			sqlQuery = "SELECT blanace FROM zli.user_customer WHERE userID=" + customerId + ";";
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
			sqlQuery = "UPDATE zli.complaint SET Status='CLOSED', RefundDetails='" + refundDetails + "' WHERE ComplaintID=" + complaintId + ";";
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintFullAmount", false);
		}
		
		return new ReturnCommand("RefundForComplaintFullAmount", true);
	}
	
	public static ReturnCommand refundForComplaintNotFull(Connection con, int complaintId, int customerId, int orderNumber, float refundAmount, String refundDetails) {
		Statement stmt;
		String sqlQuery;
		ResultSet rs;
		
		// add the refund to customer balance
		try {
			sqlQuery = "SELECT blanace FROM zli.user_customer WHERE userID=" + customerId + ";";
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
		
		// change complaint status to CLOSED
		try {
			sqlQuery = "UPDATE zli.complaint SET Status='CLOSED', RefundDetails='" + refundDetails + "' WHERE ComplaintID=" + complaintId + ";";
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ReturnCommand("RefundForComplaintNotFull", false);
		}
		
		return new ReturnCommand("RefundForComplaintNotFull", true);
	}
}
