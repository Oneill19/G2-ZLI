package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Store implements Serializable{

	private String storeName, storeAddress, storePhone;
	
	/**
	 * @param storeName
	 * @param storeAddress
	 * @param storePhone
	 */
	public Store(String storeName, String storeAddress, String storePhone) {
		super();
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	@Override
	public int hashCode() {
		return storeName.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (storeAddress == null) {
			if (other.storeAddress != null)
				return false;
		} else if (!storeAddress.equals(other.storeAddress))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (storePhone == null) {
			if (other.storePhone != null)
				return false;
		} else if (!storePhone.equals(other.storePhone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return storeName;
	}
	
	
	
	
	
}
