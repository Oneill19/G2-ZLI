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

	/**
	 * constructor
	 * 
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
		super(serialNumber, name, price, image, type, sale, color);
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
	 * newest Item constructor after adding sale to project
	 *
	 * @param serialNumber
	 * @param name
	 * @param price
	 * @param imagePath
	 * @param type
	 * @param idSale
	 * @param isSoldAlone
	 * @param amountInProduct
	 * @param color
	 * @param priceWithSale
	 */
	public Item(String serialNumber, String name, double price, String imagePath, String type, int idSale
			, boolean isSoldAlone, int amountInProduct, String color,double priceWithSale) {
		super(serialNumber, name, price, imagePath, type, idSale,priceWithSale,color);
		this.isSoldAlone = isSoldAlone;
		this.amountInProduct = amountInProduct;
		this.color = color;
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

	@Override
	public String toString() {
		return super.getName();
	}
	
}
