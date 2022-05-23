package entity;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class Item extends AbstractProduct {

	private boolean isSoldAlone;
	private int amountInProduct;

	/**
	 * @param serialNumber
	 * @param name
	 * @param price
	 * @param type
	 * @param image
	 * @param isSoldAlone
	 */
	public Item(String serialNumber, 
			String name, 
			double price, 
			String image, 
			String type,
			boolean isSoldAlone,
			int amountInProduct
			) 
	{
		super(serialNumber, name, price, image, true, type);
		this.isSoldAlone = isSoldAlone;
		this.amountInProduct = amountInProduct;
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
}
