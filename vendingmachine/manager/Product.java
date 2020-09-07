package vendingmachine.manager;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String brand;
	private String productType;
	private String flavour;
	private BigDecimal price;

	/**
	 * Constructor creating a product with a brand, type, flavour and price
	 * 
	 * @param brand - the brand of the product
	 * @param productType - the type of the product
	 * @param flavour - the flavour of the product
	 * @param price - the price of the product
	 */
	public Product(String brand, String productType, String flavour, BigDecimal price) {
		setBrand(brand);
		setProductType(productType);
		setFlavour(flavour);
		setPrice(price);
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	private void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	private void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the flavour
	 */
	public String getFlavour() {
		return flavour;
	}

	/**
	 * @param flavour the flavour to set
	 */
	private void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	/**
	 * @return the rrpPrice
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param rrpPrice the rrpPrice to set
	 */
	private void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * A string which represents the products brand with the flavour
	 */
	@Override
	public String toString() {
		
		return productType + " of " + brand + " " + flavour;
	}

}
