package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class Complaint implements Serializable {
	private final int complaintId;
	private final int orderNumber;
	private final int customerId;
	private final int workerId;
	private final String complaintDetails;
	private final LocalDate reciveDate;  
	private final LocalTime reciveTime;
	private String status;
	private boolean isReminded;
	private float refund;
	private String refundDetails;
	
	/**
	 * @param complaintId
	 * @param orderNumber
	 * @param customerId
	 * @param workerId
	 * @param complaintDetails
	 * @param reciveDate
	 * @param reciveTime
	 * @param status
	 * @param isReminded
	 */
	public Complaint(int complaintId, int orderNumber, int customerId, int workerId, String complaintDetails,
			LocalDate reciveDate, LocalTime reciveTime, String status, boolean isReminded) {
		this.complaintId = complaintId;
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.workerId = workerId;
		this.complaintDetails = complaintDetails;
		this.reciveDate = reciveDate;
		this.reciveTime = reciveTime;
		this.status = status;
		this.isReminded = isReminded;
		this.refund = 0;
		this.refundDetails = null;
	}
	
	/**
	 * @param complaintId
	 * @param orderNumber
	 * @param customerId
	 * @param workerId
	 * @param complaintDetails
	 * @param reciveDate
	 * @param reciveTime
	 * @param status
	 * @param isReminded
	 * @param refund
	 * @param refundDetails
	 */
	public Complaint(int complaintId, int orderNumber, int customerId, int workerId, String complaintDetails,
			LocalDate reciveDate, LocalTime reciveTime, String status, boolean isReminded, float refund,
			String refundDetails) {
		this.complaintId = complaintId;
		this.orderNumber = orderNumber;
		this.customerId = customerId;
		this.workerId = workerId;
		this.complaintDetails = complaintDetails;
		this.reciveDate = reciveDate;
		this.reciveTime = reciveTime;
		this.status = status;
		this.isReminded = isReminded;
		this.refund = refund;
		this.refundDetails = refundDetails;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the isReminded
	 */
	public boolean isReminded() {
		return isReminded;
	}
	
	public int getIsRemindedNumber() {
		return isReminded ? 1 : 0;
	}
	
	/**
	 * @param isReminded the isReminded to set
	 */
	public void setReminded(boolean isReminded) {
		this.isReminded = isReminded;
	}
	/**
	 * @return the refund
	 */
	public float getRefund() {
		return refund;
	}
	/**
	 * @param refund the refund to set
	 */
	public void setRefund(float refund) {
		this.refund = refund;
	}
	/**
	 * @return the refundDetails
	 */
	public String getRefundDetails() {
		return refundDetails;
	}
	/**
	 * @param refundDetails the refundDetails to set
	 */
	public void setRefundDetails(String refundDetails) {
		this.refundDetails = refundDetails;
	}
	/**
	 * @return the complaintId
	 */
	public int getComplaintId() {
		return complaintId;
	}
	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @return the workerId
	 */
	public int getWorkerId() {
		return workerId;
	}
	/**
	 * @return the complaintDetails
	 */
	public String getComplaintDetails() {
		return complaintDetails;
	}
	/**
	 * @return the reciveDate
	 */
	public LocalDate getReciveDate() {
		return reciveDate;
	}
	/**
	 * @return the reciveTime
	 */
	public LocalTime getReciveTime() {
		return reciveTime;
	}
	
	public String toString() {
		return complaintId + "," +
				orderNumber + "," +
				customerId + "," +
				workerId + "," +
				complaintDetails + "," +
				reciveDate + "," +
				reciveTime + "," +
				status + "," +
				getIsRemindedNumber();
	}
}
