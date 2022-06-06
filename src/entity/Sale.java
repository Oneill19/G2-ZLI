package entity;

import java.io.Serializable;

/**
 * for initialing tableview at watchsalescontroller.java
 * @author Dorin
 *
 */
@SuppressWarnings("serial")
public class Sale implements Serializable{

	private Integer idSale;
	private String saleName;
	private int discountAmount;
	private String status;
	
	/**
	 * Sale constructor that also initials idSale for SELECT from db
	 * @param idSale
	 * @param saleName
	 * @param discountAmount
	 * @param status
	 */
	public Sale(int idSale, String saleName, int discountAmount, String status) {
		super();
		this.idSale = idSale;
		this.saleName = saleName;
		this.discountAmount = discountAmount;
		this.status = status;
	}

	/**
	 * Sale constructor which doesn't initial idSale for INSERT TO the db
	 * @param saleName
	 * @param discountAmount
	 * @param status
	 */
	public Sale(String saleName, int discountAmount, String status) {
		super();
		this.saleName = saleName;
		this.discountAmount = discountAmount;
		this.status = status;
	}
	
	/**
	 * empty constructor
	 */
	public Sale() {
		super();
		this.saleName="sale";
		this.discountAmount=0;
		this.status="Active";
	}

	public int getIdSale() {
		return idSale;
	}

	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return idSale.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		return this.idSale.equals(other.idSale);
	}

	@Override
	public String toString() {
		return "Sale [idSale=" + idSale + ", saleName=" + saleName + ", discountAmount=" + discountAmount + ", status="
				+ status + "]";
	}
}
