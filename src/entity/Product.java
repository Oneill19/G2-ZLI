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
			ArrayList<Item> madeFrom) {
		super(serialNumber, name, price, image, false, type);
		this.other = other;
		this.madeFrom = madeFrom;
	}

	@Override
	public double getPrice() {
		double sum = 0;
		for (Item item : madeFrom)
			sum += item.getPrice() * item.getAmountInProduct();
		return sum;
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

}
