package entity;

import javafx.scene.image.ImageView;

/**
 * @author Dorin
 *
 */
public abstract class AbstractProduct {

	private String serialNumber;
	private String name;
	private double price;
	private ImageView image;
	private String type;
	private boolean isItem; // new

	/**
	 * @param serialNumber - primary key of the product/item
	 * @param name
	 * @param price
	 * @param image
	 * @param isItem       -true for Item, false for Product
	 */
	public AbstractProduct(String serialNumber, String name, double price, ImageView image, boolean isItem, String type) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.price = price;
		this.image = image;
		this.isItem = isItem;
		this.type = type;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the image
	 */
	public ImageView getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImageView image) {
		this.image = image;
	}

	public boolean isItem() {
		return isItem;
	}

	public void setItem(boolean isItem) {
		this.isItem = isItem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		System.out.println("serialNumber = " + serialNumber);
		System.out.println(" name = " + name);
		System.out.println(" price = "+price);
		System.out.println(" type = " + type);
		System.out.println(" isItem = "+isItem);
		System.out.println(" ");
		return " ";
	}

}
