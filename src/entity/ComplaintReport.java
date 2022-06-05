package entity;

import java.io.Serializable;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class ComplaintReport implements Serializable {
	private String year;
	private String month;
	private int[] numberOfOrders;
	private int[] numberOfComplaints;
	
	/**
	 * @param year
	 * @param month
	 * @param numberOfOrders
	 * @param numberOfComplaints
	 */
	public ComplaintReport(String year, String month, int[] numberOfOrders, int[] numberOfComplaints) {
		this.year = year;
		this.month = month;
		this.numberOfOrders = numberOfOrders;
		this.numberOfComplaints = numberOfComplaints;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the numberOfOrders
	 */
	public int[] getNumberOfOrders() {
		return numberOfOrders;
	}

	/**
	 * @param numberOfOrders the numberOfOrders to set
	 */
	public void setNumberOfOrders(int[] numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	/**
	 * @return the numberOfComplaints
	 */
	public int[] getNumberOfComplaints() {
		return numberOfComplaints;
	}

	/**
	 * @param numberOfComplaints the numberOfComplaints to set
	 */
	public void setNumberOfComplaints(int[] numberOfComplaints) {
		this.numberOfComplaints = numberOfComplaints;
	}
	
}
