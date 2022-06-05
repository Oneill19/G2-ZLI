package entity;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class Item extends AbstractProduct {

	private boolean isSoldAlone;
	private int amountInProduct;
	private String color;
	private String priceRange;

	/**
	 * @param serialNumber
	 * @param name
	 * @param price
	 * @param type
	 * @param image
	 * @param isSoldAlone
	 * @param color
	 */
	public Item(String serialNumber, 
			String name, 
			double price, 
			String image, 
			String type,
			boolean isSoldAlone,
			int amountInProduct,
			int sale,
			String color
			) 
	{
		super(serialNumber, name, price, image, true, type, sale, color);
		this.isSoldAlone = isSoldAlone;
		this.amountInProduct = amountInProduct;
		this.color = color;
	}
	
	public Item(Item item) {
		super(item);
		this.isSoldAlone = item.isSoldAlone;
		this.amountInProduct = item.amountInProduct;
	}

	/**
	 * @return the isSoldAlone
	 */
	public boolean isSoldAlone() {
		return isSoldAlone;
	}

	/**
	 * @param isSoldAlone the isSoldAlone to set
	 */
	public void setSoldAlone(boolean isSoldAlone) {
		this.isSoldAlone = isSoldAlone;
	}
	
	/**
	 * @return the amountInProduct
	 */
	public int getAmountInProduct() {
		return amountInProduct;
	}

	/**
	 * @param amountInProduct the amountInProduct to set
	 */
	public void setAmountInProduct(int amountInProduct) {
		this.amountInProduct = amountInProduct;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	@Override
	public String toString() {
		return super.getName();
	}
	
}
