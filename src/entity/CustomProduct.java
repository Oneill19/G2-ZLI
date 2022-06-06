package entity;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class CustomProduct extends AbstractProduct {

	private String priceRange;
	private String productList;
	private String itemList;
	
	public CustomProduct(String serial, String name, String priceRange, String color, String productList, String itemList, int idSale) {
		super(serial, name, Double.parseDouble(priceRange.split("-")[1]), "/common/Assets/flower.png", "Custom", idSale);
		this.priceRange = priceRange;
		this.productList = productList;
		this.itemList = itemList;
	}

	/**
	 * @return the priceRange
	 */
	public String getPriceRange() {
		return priceRange;
	}

	/**
	 * @param priceRange the priceRange to set
	 */
	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	/**
	 * @return the productList
	 */
	public String getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(String productList) {
		this.productList = productList;
	}

	/**
	 * @return the itemList
	 */
	public String getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(String itemList) {
		this.itemList = itemList;
	}
}
