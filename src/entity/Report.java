package entity;

import java.io.Serializable;

/**
 * @author Topaz Eldori,Koral Biton
 *
 */
@SuppressWarnings("serial")
public class Report implements Serializable {

	private int reportId;
	private String reportYear;
	private String reportMonth;
	private String reortType;
	private float totalRevenue;
	private float revenueItem;
	private float revenueProduct;
	private int amountProduct;
	private int amountItem;
	
	public Report(int reportId, String reportYear, String reportMonth, String reortType, float totalRevenue,
			float revenueItem, float revenueProduct, int amountProduct, int amountItem) {
		super();
		this.reportId = reportId;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.reortType = reortType;
		this.totalRevenue = totalRevenue;
		this.revenueItem = revenueItem;
		this.revenueProduct = revenueProduct;
		this.amountProduct = amountProduct;
		this.amountItem = amountItem;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getReortType() {
		return reortType;
	}

	public void setReortType(String reortType) {
		this.reortType = reortType;
	}

	public float getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(float totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public float getRevenueItem() {
		return revenueItem;
	}

	public void setRevenueItem(float revenueItem) {
		this.revenueItem = revenueItem;
	}

	public float getRevenueProduct() {
		return revenueProduct;
	}

	public void setRevenueProduct(float revenueProduct) {
		this.revenueProduct = revenueProduct;
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	public int getAmountItem() {
		return amountItem;
	}

	public void setAmountItem(int amountItem) {
		this.amountItem = amountItem;
	}
}
	


