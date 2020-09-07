package vendingmachine.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import vendingmachine.exceptions.CoinInventoryEmptyException;
import vendingmachine.exceptions.ProductUnavailableException;

public class VendingMachineManager {
	ArrayList<Coin> coins = new ArrayList<Coin>();
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	private String coinsFile = "VendingMachine-Coins.txt";
	private String productsFile = "VendingMachine-Products.txt";
	private String transactionsFile = "VendingMachine-Transactions.txt";
	private File coinsStorage = new File(coinsFile);
	private File productsStorage = new File(productsFile);
	private File transactionsStorage = new File(transactionsFile);
	private final int ADMINLOGIN = 1234;

	/**
	 * The constructor for the vending machine manager. When called it loads the
	 * coins, products and transactions into their representing ArrayList.
	 * 
	 */
	public VendingMachineManager() {
		try {

			loadCoins();
			loadProducts();
			loadTransactions();

		} catch (ClassNotFoundException e) {
			showDialog(Alert.AlertType.ERROR, "Critical Error", e.getMessage(),
					"There has been a critcal error. Contact an administrator");
		} catch (FileNotFoundException e) {
			showDialog(Alert.AlertType.ERROR, "Critical Error", e.getMessage(),
					"There has been a critcal error. Contact an administrator");
		} catch (IOException e) {
			showDialog(Alert.AlertType.ERROR, "Critical Error", e.getMessage(),
					"There has been a critcal error. Contact an administrator");
		}
	}

	/**
	 * Method which inserts a coin into the system when a customer presses a coin to
	 * enter.
	 * 
	 * @param value - the value of the coin being inserted
	 * @throws FileNotFoundException - the file to store coins is not found
	 * @throws IOException - occurs when an IO operation fails
	 * @throws ClassNotFoundException - Cannot load the coins into a coin object
	 */
	public void insertCoin(double value) throws FileNotFoundException, IOException, ClassNotFoundException {
		coins.add(new Coin(value));
		saveCoins();
		loadCoins();
	}

	/**
	 * A method that begins to give the client change. Potentially throws a custom
	 * exception saying it has no more coins to give out.
	 * 
	 * NOTE : This will also have to take the coins input to the machine into
	 * consideration
	 * 
	 * @param totalValue - the current balance
	 * @return a BigDecimal that will contain the current balance after change has
	 *         been given.
	 *         
	 * @throws CoinInventoryEmptyException - there are no coins available to complete this transaction
	 */
	public BigDecimal giveChange(BigDecimal totalValue) throws CoinInventoryEmptyException {

		do {
			totalValue.round(new MathContext(2, RoundingMode.DOWN));
			totalValue = dispenseChange(totalValue);
		} while (totalValue.doubleValue() > 0);

		return totalValue;
	}

	/**
	 * Restocking the coins is done by going through the thresholds of each coin, in
	 * reverse value order. Each coin value has a threshold. I have kept these
	 * purposely lower than they would be in a hardware implementation due to
	 * testing the functionality.
	 * 
	 * @throws FileNotFoundException - If there is not a file already created with
	 *                               the name and location expected
	 * @throws IOException           - A failed input/output if the action is
	 *                               interrupted
	 */
	public void restockCoinsInventory() throws FileNotFoundException, IOException {

		int[] thresholds = { 65, 60, 80, 150, 200 };
		double[] values = { 2.0, 1.0, 0.50, 0.20, 0.10 };

		coins.removeAll(coins);
		coinsStorage.delete();

		int k = 0;
		for (int i : thresholds) {
			for (int j = 0; j <= i; j++) {
				coins.add(new Coin(values[k]));
			}
			k++;
		}

		saveCoins();
		showDialog(Alert.AlertType.CONFIRMATION, "Coins Restocked", null, "The coins have been restocked");
	}

	/**
	 * A simple method which restocks the products in a fill to full structure. It
	 * will remove current products in the ArrayList, then replace the stock to
	 * full.
	 * 
	 * @throws FileNotFoundException  - If there is not a file already created with
	 *                                the name and location expected
	 * @throws IOException            - A failed input/output if the action is
	 *                                interrupted
	 * @throws ClassNotFoundException - The contents of the file doesn't match the
	 *                                class that it is being applied too.
	 */
	public void restockProductsInventory() throws FileNotFoundException, IOException, ClassNotFoundException {
		int max = 25;
		products.removeAll(products);
		productsStorage.delete();

		for (int i = 0; i < max; i++) {
			products.add(new Product("Nota", "Bottle", "Cola", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Bottle", "Cherryade", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Bottle", "Limeade", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Bottle", "Vanilla Cola", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Bottle", "Cherry Cola", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Bottle", "Iron Brew", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Bottle", "Water", BigDecimal.valueOf(1.40)));

			products.add(new Product("Nota", "Can", "Orange", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Cola", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Cherryade", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Limeade", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Iron Brew", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Fanta Lime", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Fanta Mango", BigDecimal.valueOf(1.40)));
			products.add(new Product("Nota", "Can", "Cream-soda", BigDecimal.valueOf(1.40)));
		}
		saveProducts();
		loadProducts();
	}

	/**
	 * The dispenseChange method which is the control method to give change to the
	 * customer.
	 * 
	 * Done by checking that the balance remaining is more than the value of the
	 * checked coin and that the checked coin has more coins of that value in the
	 * data storage. If both these requirements are true it dispenses the coin of
	 * that value.
	 * 
	 * @param totalValue - the current balance
	 * @return the balance after a coin has been dispensed
	 * @throws CoinInventoryEmptyException - if there are no coins that can fufil
	 *                                     the remaining balance
	 */
	private BigDecimal dispenseChange(BigDecimal totalValue) throws CoinInventoryEmptyException {

		BigDecimal coinValue = new BigDecimal(0);
		totalValue = totalValue.setScale(2, RoundingMode.HALF_EVEN);

		if (totalValue.doubleValue() > 0) {
			if (totalValue.doubleValue() >= 2.00 && coinAmountCheck(2) >= 60) {
				coinValue = BigDecimal.valueOf(2.0);

			} else if (totalValue.doubleValue() >= 1.00 && coinAmountCheck(1) >= 55) {
				coinValue = BigDecimal.valueOf(1.0);

			} else if (totalValue.doubleValue() >= 0.50 && coinAmountCheck(.50) >= 50) {
				coinValue = BigDecimal.valueOf(0.5);

			} else if (totalValue.doubleValue() >= 0.20 && coinAmountCheck(.20) >= 0) {
				coinValue = BigDecimal.valueOf(0.2);

			} else if (totalValue.doubleValue() >= 0.10 && coinAmountCheck(.10) > 0) {
				coinValue = BigDecimal.valueOf(0.1);

			} else {
				showDialog(Alert.AlertType.ERROR, "Change Error", "Not enough change",
						"There is not enough change in the system to dispense this product");
			}
			totalValue = totalValue.subtract(coinValue);
			dispenseCoins(coinValue);
		}

		return totalValue.setScale(2, RoundingMode.HALF_EVEN);
	}

	/**
	 * The method which will dispense the product selected by the customer.
	 * 
	 * @param brand   - Brand of the selected product
	 * @param type    - Type of the selected product
	 * @param flavour - Flavour of the selected product
	 * @param price   - The price of the selected product
	 * @param balance - The current amount the customer has entered into the machine
	 * @return the balance which is left after the product is dispensed.
	 * 
	 * @throws ProductUnavailableException - If the selected product is not
	 *                                     available throw a new product exception.
	 */
	public double dispenseProduct(String brand, String type, String flavour, double price, double balance)
			throws ProductUnavailableException {

		Product selectedProduct = new Product(brand, type, flavour, BigDecimal.valueOf(price));

		BigDecimal currentBalance = BigDecimal.valueOf(balance);

		if (productStockCheck(selectedProduct)) {
			for (Product product : products) {
				if (product.getBrand().equals(brand) && product.getProductType().equals(type)
						&& product.getFlavour().equals(flavour)) {
					selectedProduct = product;
				}
			}

			try {
				currentBalance = giveChange(BigDecimal.valueOf(balance - price));
				products.remove(selectedProduct);
				transactions.add(new Transaction(LocalDate.now(), selectedProduct));
				showDialog(Alert.AlertType.CONFIRMATION, "Product Dispensed", null,
						"Product: " + selectedProduct.toString());
				try {
					saveProducts();
					saveCoins();
					saveTransactions();
				} catch (IOException e) {
					showDialog(Alert.AlertType.ERROR, "Save Error", null,
							"There was an error saving the file.\nPlease contact an administrator");
				}
				return currentBalance.doubleValue();

			} catch (CoinInventoryEmptyException e) {

				showDialog(Alert.AlertType.ERROR, "Change Error", null,
						"There is not enough change in the system to dispense this product");
				return currentBalance.doubleValue();
			}
		}

		else {

			return currentBalance.doubleValue();
		}

	}

	/**
	 * A method that checks how many coins of a certain value are available in the
	 * system. Should it return a value lower than the threshold for each coin then
	 * it will not allow the system to give out change.
	 * 
	 * @param value - Given to the method by the dispenseChange method
	 * @return an integer representing how many of a given coin is available.
	 */
	private int coinAmountCheck(double value) {
		int i = 0;
		for (Coin coin : coins) {
			if (coin.getCoinValue() == value) {
				i++;
			}
		}
		return i;
	}

	/**
	 * A method that will remove a coin when it's subtracted from the balance.
	 * 
	 * @param coinValue - The value of the coin in the ArrayList to dispense to the
	 *                  customer
	 */
	private void dispenseCoins(BigDecimal coinValue) {
		Coin toRemove = null;
		for (Coin coin : coins) {
			if (coin.getCoinValue() == coinValue.doubleValue()) {
				toRemove = coin;
				break;
			}
		}
		showDialog(Alert.AlertType.CONFIRMATION, "Dispensed Coin", null, balanceAsCurrency(toRemove.getCoinValue()));
		coins.remove(toRemove);

		try {
			saveCoins();
			loadCoins();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A method to check the stock of a product.
	 * 
	 * @param product - The selected product to check
	 * 
	 * @return true if there is stock of the selected product, false if there is
	 *         not.
	 * @throws ProductUnavailableException - if the product selected is not in stock
	 */
	private boolean productStockCheck(Product product) throws ProductUnavailableException {
		for (Product productsInStock : products) {
			if (product.getBrand().equals(productsInStock.getBrand())
					&& product.getProductType().equals(productsInStock.getProductType())
					&& product.getFlavour().equals(productsInStock.getFlavour())) {
				return true;
			}
		}

		throw new ProductUnavailableException();
	}

	/**
	 * Short method to check the entered pin to login an administrator. This will be
	 * to simulate the hardware representation of a keycard swipe system.
	 * 
	 * @param pin - entered pin
	 * @return true or false if the pin was correct.
	 */
	public boolean adminCheck(String pin) {

		try {
			int code = Integer.parseInt(pin);

			if (code == ADMINLOGIN) {
				showDialog(Alert.AlertType.CONFIRMATION, "Login Successful", null, "The administrator is logged in");
				return true;
			} else {

				showDialog(Alert.AlertType.WARNING, "Admin Login Error", null, "The entered PIN was incorrect");
				return false;
			}

		} catch (NumberFormatException e) {
			showDialog(Alert.AlertType.ERROR, "Format Error", null, "Please enter a valid PIN");
			return false;
		}
	}

	/**
	 * ObjectOutputStream to save the coins to file. Will use the coinsStorage
	 * field.
	 * 
	 * @throws FileNotFoundException - If there is not a file already created with
	 *                               the name and location expected
	 * @throws IOException           - A failed input/output if the action is
	 *                               interrupted
	 */
	private void saveCoins() throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(coinsStorage))) {
			oos.writeObject(coins);
		}
		System.out.println("coins saved!");
	}

	/**
	 * ObjectInputStream to load the contents of the coinsStorage into the manager
	 * class as a Coin object in the coins ArrayList.
	 * 
	 * @throws FileNotFoundException  - If there is not a file already created with
	 *                                the name and location expected
	 * @throws IOException            - A failed input/output if the action is
	 *                                interrupted
	 * @throws ClassNotFoundException - The contents of the file doesn't match the
	 *                                class that it is being applied too.
	 */
	@SuppressWarnings("unchecked")
	private void loadCoins() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(coinsStorage))) {
			coins = (ArrayList<Coin>) ois.readObject();
		}
	}

	/**
	 * ObjectOutputStream to save the transactions to file. Will use the
	 * transactionStorage field.
	 * 
	 * @throws FileNotFoundException - If there is not a file already created with
	 *                               the name and location expected
	 * @throws IOException           - A failed input/output if the action is
	 *                               interrupted
	 */
	private void saveTransactions() throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(transactionsStorage))) {
			oos.writeObject(transactions);
		}
	}

	/**
	 * ObjectInputStream to load the contents of the transactionStorage into the
	 * manager class as a Transaction object in the transactions ArrayList.
	 * 
	 * @throws FileNotFoundException  - If there is not a file already created with
	 *                                the name and location expected
	 * @throws IOException            - A failed input/output if the action is
	 *                                interrupted
	 * @throws ClassNotFoundException - The contents of the file doesn't match the
	 *                                class that it is being applied too.
	 */
	@SuppressWarnings("unchecked")
	private void loadTransactions() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(transactionsStorage))) {
			transactions = (ArrayList<Transaction>) ois.readObject();
		}

	}

	/**
	 * ObjectOutputStream to save the products to file. Will use the productsStorage
	 * field.
	 * 
	 * @throws FileNotFoundException - If there is not a file already created with
	 *                               the name and location expected
	 * @throws IOException           - A failed input/output if the action is
	 *                               interrupted
	 */
	private void saveProducts() throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(productsStorage))) {
			oos.writeObject(products);
		}
	}

	/**
	 * ObjectInputStream to load the contents of the productsStorage into the
	 * manager class as a Product object in the products ArrayList.
	 * 
	 * @throws FileNotFoundException  - If there is not a file already created with
	 *                                the name and location expected
	 * @throws IOException            - A failed input/output if the action is
	 *                                interrupted
	 * @throws ClassNotFoundException - The contents of the file doesn't match the
	 *                                class that it is being applied too.
	 */
	@SuppressWarnings("unchecked")
	private void loadProducts() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(productsStorage))) {
			products = (ArrayList<Product>) ois.readObject();
		}

	}

	/**
	 * Using the Alert object from the javafx package to display an error message,
	 * confirmation message or a popout dialog to the customer.
	 * 
	 * @param type    - The enumerated AlertType of the message
	 * @param title   - The title for the dialog
	 * @param header  - The header for the dialog. Defaulted to null.
	 * @param content - The message that is displayed with the dialog.
	 */
	public void showDialog(Alert.AlertType type, String title, String header, String content) {

		Alert alert = new Alert(type, null, ButtonType.OK);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();

	}

	/**
	 * A transaction array that is used to return to the table in the user
	 * interface.
	 * 
	 * @return an array of transactions
	 */
	public Transaction[] getTransactions() {
		Transaction[] transactionsList = new Transaction[transactions.size()];
		transactionsList = transactions.toArray(transactionsList);
		return transactionsList;
	}

	/**
	 * A method which updates the balance. It uses the NumberFormat object which
	 * will be used to display the balance as in a currency format. This is to make
	 * it easier for the customer to see what their current balance is. Can also be
	 * used in other methods which show a coins value rounded to an exact two
	 * decimal amount.
	 * 
	 * @param balance - the current balance after a coin has been entered
	 * @return the formatted string of the balance.
	 */
	public String balanceAsCurrency(double balance) {
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
		String money = format.format(balance);

		return money;
	}

}
