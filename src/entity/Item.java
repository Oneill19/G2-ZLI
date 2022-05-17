package entity;

import javafx.scene.image.Image;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class Item extends AbstractProduct {
	
	private boolean isSoldAlone;

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
				String type, 
				Image image,
				boolean isSoldAlone) {
		super(serialNumber, name, price, type, image);
		this.isSoldAlone = isSoldAlone;
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
	
}
