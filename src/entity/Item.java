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
	 */
	public Item(String serialNumber, 
			String name, 
			double price, 
			String image, 
			String type,
			boolean isSoldAlone,
			int amountInProduct,
			int sale
			) 
	{
		super(serialNumber, name, price, image, true, type, sale);
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + amountInProduct;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (isSoldAlone ? 1231 : 1237);
		result = prime * result + ((priceRange == null) ? 0 : priceRange.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (amountInProduct != other.amountInProduct)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (isSoldAlone != other.isSoldAlone)
			return false;
		if (priceRange == null) {
			if (other.priceRange != null)
				return false;
		} else if (!priceRange.equals(other.priceRange))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+"Item [isSoldAlone=" + isSoldAlone + ", amountInProduct=" + amountInProduct + ", color=" + color
				+ ", priceRange=" + priceRange + "]";
	}
	
}
