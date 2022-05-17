package entity;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class Customer extends User {
	
	private double balance;

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
	 * @param balance
	 */
	public Customer(int userID, 
					String firstName, 
					String lastName, 
					String creditCard, 
					String phone, 
					String email,
					String password, 
					String userRole, 
					String status, 
					boolean isLogged, 
					double balance) {
		super(userID, firstName, lastName, creditCard, phone, email, password, userRole, status, isLogged);
		this.balance = balance;
	}
	
	/**
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	} 
	
	/**
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String toString() {
		return "Customer";
	}

}
