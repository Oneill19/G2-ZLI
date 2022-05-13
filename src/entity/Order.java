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



	public Order(int orderNumber, double totalPrice, String greetingCard, String color, String orderDesc,
			String fromStore, LocalDate orderDate, LocalTime orderTime, int customerID, String paymentMethos,
			String orderStatus, String confirmedDate, String completeDate, String deliveryMethod,
			LocalDate expectedDateInStore, LocalTime expectedTimeInStore) {
		super();
		this.orderNumber = orderNumber;
		this.customerID = customerID;
		this.totalPrice = totalPrice;
		this.greetingCard = greetingCard;
		this.color = color;
		this.orderDesc = orderDesc;
		this.fromStore = fromStore;
		this.paymentMethod = paymentMethos;
		this.orderStatus = orderStatus;
		this.confirmedDate = confirmedDate;
		this.completeDate = completeDate;
		this.deliveryMethod = deliveryMethod;
		this.orderCreationDate = orderDate;
		this.supplyDate = expectedDateInStore;
		this.orderCreationTime = orderTime;
		this.supplyTime = expectedTimeInStore;
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

	public String getPaymentMethos() {
		return paymentMethod;
	}

	public void setPaymentMethos(String paymentMethos) {
		this.paymentMethod = paymentMethos;
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

	public LocalDate getOrderDate() {
		return orderCreationDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderCreationDate = orderDate;
	}

	public LocalDate getExpectedDateInStore() {
		return supplyDate;
	}

	public void setExpectedDateInStore(LocalDate expectedDateInStore) {
		this.supplyDate = expectedDateInStore;
	}

	public LocalTime getOrderTime() {
		return orderCreationTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderCreationTime = orderTime;
	}

	public LocalTime getExpectedTimeInStore() {
		return supplyTime;
	}

	public void setExpectedTimeInStore(LocalTime expectedTimeInStore) {
		this.supplyTime = expectedTimeInStore;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", customerID=" + customerID + ", totalPrice=" + totalPrice
				+ ", greetingCard=" + greetingCard + ", color=" + color + ", orderDesc=" + orderDesc + ", fromStore="
				+ fromStore + ", paymentMethos=" + paymentMethod + ", orderStatus=" + orderStatus + ", confirmedDate="
				+ confirmedDate + ", completeDate=" + completeDate + ", deliveryMethod=" + deliveryMethod
				+ ", orderDate=" + orderCreationDate + ", expectedDateInStore=" + supplyDate + ", orderTime="
				+ orderCreationTime + ", expectedTimeInStore=" + supplyTime + "]";
	}

}
