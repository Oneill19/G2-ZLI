package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int orderNumber = 0, customerID = 0;
	private double totalPrice = 0;
	private String greetingCard = null, color = null, orderDesc = null, fromStore = null, paymentMethod = null;
	private String orderStatus = null, confirmedDate = null, completeDate = null, deliveryMethod = null;
	private LocalDate orderCreationDate = null, supplyDate = null;
	private LocalTime orderCreationTime = null, supplyTime = null;

	// Empty Constructor
	public Order() {
		this.orderNumber = 0;
		this.customerID = 0;
		this.totalPrice = 0;
		this.greetingCard = null;
		this.color = null;
		this.orderDesc = null;
		this.fromStore = null;
		this.paymentMethod = null;
		this.orderStatus = null;
		this.confirmedDate = null;
		this.completeDate = null;
		this.deliveryMethod = null;
		this.orderCreationDate = null;
		this.supplyDate = null;
		this.orderCreationTime = null;
		this.supplyTime = null;
	}


	//Constructor Excluding 'supplyDate' and 'supplyTime' columns
	//These columns need to be checked if null or not
	//They are not initialed from the start
	public Order(int orderNumber, double totalPrice, String greetingCard, String color, String orderDesc,
			String fromStore, LocalDate orderCreationDate, LocalTime orderCreationTime, int customerID, String paymentMethod,
			String orderStatus, String confirmedDate, String completeDate, String deliveryMethod) {
		super();
		this.orderNumber = orderNumber;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.greetingCard = greetingCard;
		this.color = color;
		this.orderDesc = orderDesc;
		this.fromStore = fromStore;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.confirmedDate = confirmedDate;
		this.completeDate = completeDate;
		this.deliveryMethod = deliveryMethod;
		this.orderCreationDate = orderCreationDate;
		this.orderCreationTime = orderCreationTime;
	}
	
	// added constructor with supply date and time for places we need them in future
	public Order(int orderNumber, double totalPrice, String greetingCard, String color, String orderDesc,
			String fromStore, LocalDate orderCreationDate, LocalTime orderCreationTime, int customerID, String paymentMethod,
			String orderStatus, String confirmedDate, String completeDate, String deliveryMethod, LocalDate supplyDate, LocalTime supplyTime) {
		super();
		this.orderNumber = orderNumber;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.greetingCard = greetingCard;
		this.color = color;
		this.orderDesc = orderDesc;
		this.fromStore = fromStore;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.confirmedDate = confirmedDate;
		this.completeDate = completeDate;
		this.deliveryMethod = deliveryMethod;
		this.orderCreationDate = orderCreationDate;
		this.orderCreationTime = orderCreationTime;
		this.supplyDate = supplyDate;
		this.supplyTime = supplyTime;
	}


	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getGreetingCard() {
		return greetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		this.greetingCard = greetingCard;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getFromStore() {
		return fromStore;
	}

	public void setFromStore(String fromStore) {
		this.fromStore = fromStore;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(String confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public LocalDate getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(LocalDate orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	public LocalDate getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(LocalDate supplyDate) {
		this.supplyDate = supplyDate;
	}

	public LocalTime getOrderCreationTime() {
		return orderCreationTime;
	}

	public void setOrderCreationTime(LocalTime orderCreationTime) {
		this.orderCreationTime = orderCreationTime;
	}

	public LocalTime getSupplyTime() {
		return supplyTime;
	}

	public void setSuppplyTime(LocalTime supplyTime) {
		this.supplyTime = supplyTime;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", customerID=" + customerID + ", totalPrice=" + totalPrice
				+ ", greetingCard=" + greetingCard + ", color=" + color + ", orderDesc=" + orderDesc + ", fromStore="
				+ fromStore + ", paymentMethod=" + paymentMethod + ", orderStatus=" + orderStatus + ", confirmedDate="
				+ confirmedDate + ", completeDate=" + completeDate + ", deliveryMethod=" + deliveryMethod
				+ ", orderDate=" + orderCreationDate + ", expectedDateInStore=" + supplyDate + ", orderTime="
				+ orderCreationTime + ", expectedTimeInStore=" + supplyTime + "]";
	}
	
	/**
	 * @return String values of Order, by the order they present in DB.
	 */
	public String DBToString() {
		StringBuilder sb = new StringBuilder();
		//orderNumber is incremental
		sb.append(totalPrice).append(",");
		sb.append("'").append(greetingCard).append("'").append(",");
		sb.append(color).append(",");
		sb.append("'").append(orderDesc).append("'").append(",");
		sb.append("'").append(fromStore).append("'").append(",");
		sb.append("'").append(orderCreationDate).append("'").append(",");
		sb.append("'").append(orderCreationTime).append("'").append(",");
		sb.append(customerID).append(",");
		sb.append("'").append(paymentMethod).append("'").append(",");
		sb.append("'").append(orderStatus).append("'").append(",");
		sb.append(confirmedDate).append(",");
		sb.append(completeDate).append(",");
		sb.append("'").append(deliveryMethod).append("'").append(",");
		sb.append("'").append(supplyDate).append("'").append(",");
		sb.append("'").append(supplyTime).append("'");	
		return sb.toString();	
	}

}
;
