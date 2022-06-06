package entity;

import java.util.ArrayList;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class Product extends AbstractProduct {

	private String other;
	private ArrayList<Item> madeFrom;

	
	public Product() {
		super();
	}
	/**
	 * @param serialNumber
	 * @param name
	 * @param price
	 * @param type
	 * @param image
	 * @param other
	 * @param madeFrom
	 */

	public Product(String serialNumber, String name, double price, String type, String image, String other,
			ArrayList<Item> madeFrom, int sale, String color) {
		super(serialNumber, name, price, image, type, sale, color);
		this.other = other;
		this.madeFrom = madeFrom;
	}
	
	public Product(String serialNumber, String name, double price, String type, String image, String other,
			ArrayList<Item> madeFrom, int sale, String color, double priceWithSale) {
		super(serialNumber, name, price, image, type, sale, priceWithSale, color );
		this.other = other;
		this.madeFrom = madeFrom;
	}
	
	public Product(Product ap) {
		super(ap);
		this.other=  ap.getOther();
		this.madeFrom = ap.getMadeFrom();
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * @return the madeFrom
	 */
	public ArrayList<Item> getMadeFrom() {
		return madeFrom;
	}

	/**
	 * @param madeFrom the madeFrom to set
	 */
	public void setMadeFrom(ArrayList<Item> madeFrom) {
		this.madeFrom = madeFrom;
	}
	@Override
	public String toString() {
		return super.getName();
	}

	
}
