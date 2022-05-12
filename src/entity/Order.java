package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int orderNumber = 0, customerID = 0;
	private double totalPrice = 0;
	private String greetingCard = null, color = null, orderDesc = null, fromStore = null, paymentMethos = null;
	private String orderStatus = null, confirmedDate = null, completeDate = null, deliveryMethod = null;
	private LocalDate orderDate = null, expectedDateInStore = null;
	private LocalTime orderTime = null, expectedTimeInStore = null;

	// Empty Constructor
	public Order() {
		this.orderNumber = 0;
		this.customerID = 0;
		this.totalPrice = 0;
		this.greetingCard = null;
		this.color = null;
		this.orderDesc = null;
		this.fromStore = null;
		this.paymentMethos = null;
		this.orderStatus = null;
		this.confirmedDate = null;
		this.completeDate = null;
		this.deliveryMethod = null;
		this.orderDate = null;
		this.expectedDateInStore = null;
		this.orderTime = null;
		this.expectedTimeInStore = null;
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
		this.paymentMethos = paymentMethos;
		this.orderStatus = orderStatus;
		this.confirmedDate = confirmedDate;
		this.completeDate = completeDate;
		this.deliveryMethod = deliveryMethod;
		this.orderDate = orderDate;
		this.expectedDateInStore = expectedDateInStore;
		this.orderTime = orderTime;
		this.expectedTimeInStore = expectedTimeInStore;
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
		return paymentMethos;
	}

	public void setPaymentMethos(String paymentMethos) {
		this.paymentMethos = paymentMethos;
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
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getExpectedDateInStore() {
		return expectedDateInStore;
	}

	public void setExpectedDateInStore(LocalDate expectedDateInStore) {
		this.expectedDateInStore = expectedDateInStore;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public LocalTime getExpectedTimeInStore() {
		return expectedTimeInStore;
	}

	public void setExpectedTimeInStore(LocalTime expectedTimeInStore) {
		this.expectedTimeInStore = expectedTimeInStore;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", customerID=" + customerID + ", totalPrice=" + totalPrice
				+ ", greetingCard=" + greetingCard + ", color=" + color + ", orderDesc=" + orderDesc + ", fromStore="
				+ fromStore + ", paymentMethos=" + paymentMethos + ", orderStatus=" + orderStatus + ", confirmedDate="
				+ confirmedDate + ", completeDate=" + completeDate + ", deliveryMethod=" + deliveryMethod
				+ ", orderDate=" + orderDate + ", expectedDateInStore=" + expectedDateInStore + ", orderTime="
				+ orderTime + ", expectedTimeInStore=" + expectedTimeInStore + "]";
	}

}
