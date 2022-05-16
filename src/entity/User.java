package entity;

import java.io.Serializable;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	private int userID;
	private String firstName;
	private String lastName;
	private String creditCard;
	private String phone;
	private String email;
	private String password;
	private String userRole;
	private String status;
	private boolean isLogged;
	
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
	 */
	public User(int userID, String firstName, String lastName, String creditCard, String phone, String email,
			String password, String userRole, String status, boolean isLogged) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditCard = creditCard;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.status = status;
		this.isLogged = isLogged;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * @param isLogged the isLogged to set
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}
