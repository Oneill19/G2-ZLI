package entity;

import java.io.Serializable;

import javafx.scene.image.Image;
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
	private String imagePath;
	private String type;	//for Item - single roses etc, for Product - for wedding etc
	private ImageView imageView;
	private int sale;
	private double priceWithSale;
	private int amount;
	private String color;

	public AbstractProduct() {
		super();
	}
	
	/**
	 * @param serialNumber - primary key of the product/item
	 * @param name
	 * @param price
	 * @param image
	 * @param isItem       -true for Item, false for Product
	 * @param color
	 */
	public AbstractProduct(String serialNumber, String name, double price, String image, String type, int sale, double priceWithSale, String color) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.price = price;
		this.imagePath = image;
		this.type = type;
		this.sale=sale;
		this.priceWithSale=priceWithSale;
		this.color = color;
	}
	
	/**
	 * @param serialNumber - primary key of the product/item
	 * @param name
	 * @param price
	 * @param image
	 * @param isItem       -true for Item, false for Product
	 * @param color
	 */
	public AbstractProduct(String serialNumber, String name, double price, String image, String type, int sale, String color) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.price = price;
		this.imagePath = image;
		this.type = type;
		this.sale=sale;
		priceWithSale=price;
		this.color = color;
	}

	/**
	 * @param ap  the AbstractProduct to initial fields from
	 */
	public AbstractProduct(AbstractProduct ap) {
		super();
		this.serialNumber = ap.getSerialNumber();
		this.name = ap.getName();
		this.price = ap.getPrice();
		this.imagePath = ap.getImagePath();
		this.type = ap.getType();
		this.sale=ap.getSale();
		this.priceWithSale = ap.getPriceWithSale();
		this.color = ap.getColor();
	}
	
	
	

	/**
	 * for selecting item_in_sale
	 * @param serialNumber
	 * @param name
	 * @param price
	 * @param imagePath
	 * @param type
	 * @param isItem
	 * @param idSale
	 * @param amount
	 * @param color
	 */
	public AbstractProduct(String serialNumber, String name, double price, String imagePath, String type
			, int idSale) {
		super();
		this.serialNumber = serialNumber;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
		this.type = type;
		this.sale = idSale;
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
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param image the image to set
	 */
	public void setImagePath(String image) {
		this.imagePath = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView() {
		imageView = new ImageView(new Image(getClass().getResourceAsStream(this.imagePath), 100, 100, false, false));
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		return serialNumber.hashCode();
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
		}
		return serialNumber.equals(other.serialNumber);
	}
	
	/**
	 * @return in percentages
	 */
	public int getSale(){
		return sale;
	}
	
	/**
	 * @param sale in percentages
	 */
	public void setSale(int sale) {
		this.sale=sale;
	}
	
	public double getPriceWithSale() {
		return priceWithSale;
	}
	
	public void setPriceWithSale(double priceWithSale) {
		this.priceWithSale = priceWithSale;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount=amount;
	}
	
		
	@Override
	public String toString() {
		return "serialNumber: "+serialNumber+"\n" +"name: "+ name+"\nprice: " + price+"\ntype: "+ type+"\nisItem: ";
	}
}
