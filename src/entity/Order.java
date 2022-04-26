package entity;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Order implements Serializable {
	
	private int orderNumber;
	private double price;
	private String greetingCard;
	private String color;
	private String dOrder;
	private String shop;
	private LocalDate date;
	private LocalDate orderDate;
	
	public Order() {
		orderNumber = 0;
		price = 0;
		greetingCard = null;
		color = null;
		dOrder = null;
		shop = null;
		date = null;
		orderDate = null;
	}
	
	public Order(int orderNumber, double price, String greetingCard, String color, String dOrder, String shop, LocalDate date, LocalDate orderDate) {
		this.orderNumber = orderNumber;
		this.price = price;
		this.greetingCard = greetingCard;
		this.color = color;
		this.dOrder = dOrder;
		this.shop = shop;
		this.date = date;
		this.orderDate = orderDate;
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


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
}
