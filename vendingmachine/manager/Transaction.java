package vendingmachine.manager;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate dateOfSale;
	private Product productSold;

	/**
	 * @param dateOfSale - the date this transaction happened
	 * @param productSold - what product was sold
	 */
	public Transaction(LocalDate dateOfSale, Product productSold) {
		this.dateOfSale = dateOfSale;
		this.productSold = productSold;
	}

	/**
	 * @return the dateOfSale
	 */
	public LocalDate getDateOfSale() {
		return dateOfSale;
	}

	/**
	 * @return the productSold
	 */
	public Product getProductSold() {
		return productSold;
	}

	@Override
	public String toString() {
		return "Product Sold - " + productSold + "Date sold - " + dateOfSale;
	}
	
}
