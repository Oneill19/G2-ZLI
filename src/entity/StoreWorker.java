package entity;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class StoreWorker extends User {
	
	private String storeName;

	/**
	 * @param userID
	 * @param firstName
	 * @param lastName
	 * @param creditCard
	 * @param phone
	 * @param email
	 * @param password
	 * @param userRole
	 * @param status
	 * @param isLogged
	 * @param storeName
	 */
	public StoreWorker(int userID, 
						String firstName, 
						String lastName, 
						String creditCard, 
						String phone, 
						String email,
						String password, 
						String userRole, 
						String status, 
						boolean isLogged,
						String storeName) {
		super(userID, firstName, lastName, creditCard, phone, email, password, userRole, status, isLogged);
		this.storeName = storeName;
	}
	
	/**
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}
	
	/**
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String toString() {
		return "StoreWorker";
	}
}
