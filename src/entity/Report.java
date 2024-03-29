package entity;

import java.io.Serializable;

/**
 * class of reports with the details that should be in the reports
 * 
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
	private int quartlyNum;
	private String storeName;

	public Report(int reportId, String storeName, String reportYear, String reportMonth, int amountProduct,
			int amountItem, float revenueProduct, float revenueItem, int quartlyNum, float totalRevenue) {
		super();
		this.reportId = reportId;
		this.storeName = storeName;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.amountProduct = amountProduct;
		this.amountItem = amountItem;
		this.revenueProduct = revenueProduct;
		this.revenueItem = revenueItem;
		this.quartlyNum = quartlyNum;
		this.totalRevenue = totalRevenue;
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

	public int getQuartlyNum() {
		return quartlyNum;
	}

	public void setQuartlyNum(int quartlyNum) {
		this.quartlyNum = quartlyNum;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
