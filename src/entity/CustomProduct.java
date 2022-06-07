package entity;

import java.util.ArrayList;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class CustomProduct extends AbstractProduct {

	private String priceRange;
	private String productList;
	private String itemList;
	
	private ArrayList<Item> itemArrayList;
	private ArrayList<Product> productArrayList;
	
	public CustomProduct(String serial, 
						String name, 
						String priceRange, 
						String color, 
						String productList, 
						String itemList, 
						int idSale) {
		super(serial, name, Double.parseDouble(priceRange.split("-")[1]), "/common/Assets/flower.png", "Custom", idSale);
		this.priceRange = priceRange;
		this.productList = productList;
		this.itemList = itemList;
	}
	
	

	/**
	 *
	 * @param serialNumber
	 * @param name
	 * @param price
	 * @param priceRange
	 * @param color
	 * @param productList
	 * @param itemList
	 * @param idSale
	 * @param priceWithSale
	 */
	public CustomProduct(String serialNumber, 
							String name,
							double price,
							String priceRange,
							String color, 
							String productList,
							String itemList,
							int idSale,
							double priceWithSale
							){
		super(serialNumber, name, Double.parseDouble(priceRange.split("-")[1]), "/common/Assets/flower.png", "Custom", idSale, priceWithSale, color);
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



	public ArrayList<Item> getItemArrayList() {
		return itemArrayList;
	}



	public void setItemArrayList(ArrayList<Item> itemArrayList) {
		this.itemArrayList = itemArrayList;
	}



	public ArrayList<Product> getProductArrayList() {
		return productArrayList;
	}



	public void setProductArrayList(ArrayList<Product> productArrayList) {
		this.productArrayList = productArrayList;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((itemArrayList == null) ? 0 : itemArrayList.hashCode());
		result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
		result = prime * result + ((priceRange == null) ? 0 : priceRange.hashCode());
		result = prime * result + ((productArrayList == null) ? 0 : productArrayList.hashCode());
		result = prime * result + ((productList == null) ? 0 : productList.hashCode());
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
		CustomProduct other = (CustomProduct) obj;
		if (itemArrayList == null) {
			if (other.itemArrayList != null)
				return false;
		} else if (!itemArrayList.equals(other.itemArrayList))
			return false;
		if (itemList == null) {
			if (other.itemList != null)
				return false;
		} else if (!itemList.equals(other.itemList))
			return false;
		if (priceRange == null) {
			if (other.priceRange != null)
				return false;
		} else if (!priceRange.equals(other.priceRange))
			return false;
		if (productArrayList == null) {
			if (other.productArrayList != null)
				return false;
		} else if (!productArrayList.equals(other.productArrayList))
			return false;
		if (productList == null) {
			if (other.productList != null)
				return false;
		} else if (!productList.equals(other.productList))
			return false;
		return true;
	}
	
	
	
	
	
}
