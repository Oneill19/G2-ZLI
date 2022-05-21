package entity;

import java.io.Serializable;

import javafx.scene.image.ImageView;

/**
 * @author Oneill, Dorin
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractProduct implements Serializable {

	private String serialNumber;
	private String name;
	private double price;
	private String image;
	private String type;
	private boolean isItem; // new

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractProduct other = (AbstractProduct) obj;
		if (serialNumber == null) {
			if (other.serialNumber != null)
				return false;
		} else if (!serialNumber.equals(other.serialNumber))
			return false;
		return true;
	}

	/**
	 * @param serialNumber - primary key of the product/item
	 * @param name
	 * @param price
	 * @param image
	 * @param isItem       -true for Item, false for Product
	 */
	public AbstractProduct(String serialNumber, String name, double price, String image, boolean isItem, String type) {
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
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
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
