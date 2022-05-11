package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int orderNumber;
	private double price;
	private String greetingCard;
	private String color;
	private String dOrder;
	private String shop;
	private LocalDate supplyDate;
	private LocalTime supplyTime;
	private LocalDate orderDate;
	private LocalTime orderTime;

	// Empty constructor nullifies fields
	public Order() {
		orderNumber = 0;
		price = 0;
		greetingCard = null;
		color = null;
		dOrder = null;
		shop = null;
		supplyDate = null;
		supplyTime = null;
		orderDate = null;
		orderTime = null;
	}

	// Constructor initiates fields
	public Order(int orderNumber, double totalPrice, String greetingCard, String color, String orderDesc,
			String fromStore, LocalDate orderDate, LocalTime orderTime, int customerID, String paymentMethos,
			String orderStatus, String confirmedDate, String completeDate, String deliveryMethod,
			LocalDate expectedDateInStore, LocalTime expectedTimeInStore) {
		this.orderNumber = orderNumber;
		this.price = price;
		this.greetingCard = greetingCard;
		this.color = color;
		this.dOrder = dOrder;
		this.shop = shop;
		this.supplyDate = supplyDate;
		this.supplyTime = supplyTime;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getDorder() {
		return dOrder;
	}

	public void setDorder(String dOrder) {
		this.dOrder = dOrder;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(LocalDate supplyDate) {
		this.supplyDate = supplyDate;
	}

	public LocalTime getSupplyTime() {
		return supplyTime;
	}

	public void setSupplyTime(LocalTime supplyTime) {
		this.supplyTime = supplyTime;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

}
