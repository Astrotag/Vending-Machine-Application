package vendingmachine.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import vendingmachine.exceptions.CoinInventoryEmptyException;
import vendingmachine.exceptions.ProductUnavailableException;
import vendingmachine.manager.Transaction;
import vendingmachine.manager.VendingMachineManager;

public class VendingMachineController {

	@FXML
	private AnchorPane mainPane;

	@FXML
	private GridPane productBtnPane;

	@FXML
	private Button refundBtn, tenPenceBtn, twentyPenceBtn, fiftyPenceBtn, onePoundBtn, twoPoundBtn, productBtn1,
			productBtn2, productBtn3, productBtn4, productBtn5, productBtn6, productBtn7, productBtn8, productBtn9,
			productBtn10, productBtn11, productBtn12, productBtn13, productBtn14, productBtn15, adminRestockProductsBtn,
			adminRestockCoinsBtn, adminReportBtn, adminLoginBtn;

	@FXML
	private TableView<Transaction> transactionsTable;

	@FXML
	private Label balanceLabel;

	private double balance;

	private VendingMachineManager manager = new VendingMachineManager();

	@FXML
	void dispenseProduct(ActionEvent event) {

		String productBrand, productType, flavour;
		double price;

		// 1
		if (event.getSource().equals(productBtn1)) {
			price = 1.40;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Cola";
				//System.out.println(balance);
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				//System.out.println(balance);
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 2
		if (event.getSource().equals(productBtn2)) {
			price = 1.40;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Cherryade";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 3
		if (event.getSource().equals(productBtn3)) {
			price = 1.40;

			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Limeade";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 4
		if (event.getSource().equals(productBtn4)) {
			price = 1.40;

			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Vanilla Cola";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 5
		if (event.getSource().equals(productBtn5)) {
			price = 1.40;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Cherry Cola";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 6
		if (event.getSource().equals(productBtn6)) {
			price = 1.40;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Iron Brew";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 7
		if (event.getSource().equals(productBtn7)) {
			price = 1.40;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Water";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 8
		if (event.getSource().equals(productBtn8)) {
			price = 0.80;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Orange";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		// 9
		if (event.getSource().equals(productBtn9)) {
			price = 0.80;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Cola";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		if (event.getSource().equals(productBtn10)) {
			price = 0.80;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Cherryade";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		if (event.getSource().equals(productBtn11)) {
			price = 0.80;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Limeade";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		if (event.getSource().equals(productBtn12)) {
			price = 0.80;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Iron Brew";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		if (event.getSource().equals(productBtn13)) {
			price = 0.80;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Fanta Lime";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		if (event.getSource().equals(productBtn14)) {
			price = 0.80;
			//System.out.println("Normal balance - " + balance);
			//System.out.println("getBalance() - " + getBalance());
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Can";
				flavour = "Fanta Mango";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
				balanceLabel.setText(manager.balanceAsCurrency(balance));
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
		if (event.getSource().equals(productBtn15)) {
			price = 1.40;
			if (getBalance() >= price) {
				productBrand = "Nota";
				productType = "Bottle";
				flavour = "Cream-soda";
				try {
					balance = manager.dispenseProduct(productBrand, productType, flavour, price, balance);
				} catch (ProductUnavailableException e) {
					manager.showDialog(Alert.AlertType.ERROR, "Product Error", "Product not in stock",
							"The product you selected is not in stock");
				}
			} else {
				double remainder = price - balance;
				manager.showDialog(Alert.AlertType.ERROR, "Balance Error", "Not enough balance",
						"You do not have enough balance to purchase this item.\n You are missing "
								+ manager.balanceAsCurrency(remainder));
			}
		}
	}

	@FXML
	void insertCoin(ActionEvent event) {

		try {
			if (event.getSource().equals(tenPenceBtn)) {
				manager.insertCoin(0.10);
				balance += 0.1;
			} else if (event.getSource().equals(twentyPenceBtn)) {
				manager.insertCoin(0.20);
				balance += 0.2;
			} else if (event.getSource().equals(fiftyPenceBtn)) {
				manager.insertCoin(0.50);
				balance += 0.5;
			} else if (event.getSource().equals(onePoundBtn)) {
				manager.insertCoin(1.00);
				balance += 1.0;
			} else if (event.getSource().equals(twoPoundBtn)) {
				manager.insertCoin(2.00);
				balance += 2.0;
			}
		} catch (ClassNotFoundException | IOException e) {
			manager.showDialog(Alert.AlertType.ERROR, "Error", null,
					"An error has occured\n Please contact the administrator");
			System.exit(0);
		} finally {
			balanceLabel.setText(manager.balanceAsCurrency(balance));
		}
	}

	@FXML
	void refundBtnPressed(ActionEvent event) {
		BigDecimal b = BigDecimal.valueOf(balance);
		try {
			b = manager.giveChange(b);
		} catch (CoinInventoryEmptyException e) {
			e.printStackTrace();
		}

		balance = b.doubleValue();
		balanceLabel.setText(manager.balanceAsCurrency(balance));
	}

	@FXML
	void showAdminMenu(ActionEvent event) {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Admin Menu");
		dialog.setHeaderText(null);

		dialog.setContentText("Enter the pin to access the admin menu");

		String aPin;
		boolean valid = false;

		if (!adminReportBtn.isVisible()) {
			do {
				Optional<String> pin = null;

				pin = dialog.showAndWait();

				if (pin.isPresent()) {
					aPin = pin.get();
					valid = manager.adminCheck(aPin);
				} else {
					valid = false;
					break;
				}

			} while (!valid);

			if (valid) {
				adminLoginBtn.setText("Admin Logout");
				showHideBtns(valid);
			}
		} else {
			showHideBtns(valid);
			adminLoginBtn.setText("Admin Login");
		}

	}

	@FXML
	void restockCoins(ActionEvent event) {

		try {

			manager.restockCoinsInventory();
		} catch (IOException e) {
			manager.showDialog(Alert.AlertType.ERROR, "Coin Error", null, "There was an error updating the coins");
		}
	}

	@FXML
	void restockProducts(ActionEvent event) {
		try {
			manager.restockProductsInventory();
			manager.showDialog(Alert.AlertType.CONFIRMATION, "Products", null, "Products have been restocked");
		} catch (ClassNotFoundException | IOException e) {
			manager.showDialog(Alert.AlertType.ERROR, "Coin Error", null, "There was an error updating the products");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void generateReport(ActionEvent event) {
		Transaction[] transactions = manager.getTransactions();
		TableColumn productCol = new TableColumn<>("Product");
		productCol.setCellValueFactory(new PropertyValueFactory<>("productSold"));

		TableColumn dateCol = new TableColumn<>("Date Sold");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));

		transactionsTable.getColumns().clear();
		transactionsTable.getColumns().addAll(productCol, dateCol);
		transactionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		transactionsTable.getItems().clear();
		for (Transaction transaction : transactions) {
			transactionsTable.getItems().add(transaction);
		}
	}

	/**
	 * A method which will show/hide the buttons when the administrator is logged
	 * in.
	 * 
	 * @param bool - boolean value depending on either showing buttons, or hiding
	 *             the buttons.
	 */
	private void showHideBtns(boolean bool) {
		tenPenceBtn.setVisible(!bool);
		twentyPenceBtn.setVisible(!bool);
		fiftyPenceBtn.setVisible(!bool);
		onePoundBtn.setVisible(!bool);
		twoPoundBtn.setVisible(!bool);
		balanceLabel.setVisible(!bool);
		refundBtn.setVisible(!bool);

		productBtn1.setVisible(!bool);
		productBtn2.setVisible(!bool);
		productBtn3.setVisible(!bool);
		productBtn4.setVisible(!bool);
		productBtn5.setVisible(!bool);
		productBtn6.setVisible(!bool);
		productBtn7.setVisible(!bool);
		productBtn8.setVisible(!bool);
		productBtn9.setVisible(!bool);
		productBtn10.setVisible(!bool);
		productBtn11.setVisible(!bool);
		productBtn12.setVisible(!bool);
		productBtn13.setVisible(!bool);
		productBtn14.setVisible(!bool);
		productBtn15.setVisible(!bool);

		transactionsTable.setVisible(bool);
		transactionsTable.setDisable(!bool);

		adminReportBtn.setVisible(bool);
		adminReportBtn.setDisable(!bool);

		adminRestockCoinsBtn.setVisible(bool);
		adminRestockCoinsBtn.setDisable(!bool);

		adminRestockProductsBtn.setVisible(bool);
		adminRestockProductsBtn.setDisable(!bool);
	}

	/**
	 * A simple round function for the balance to be shown to the customer.
	 * 
	 * @return the rounded balance
	 */
	public double getBalance() {
		return Math.round(balance * 100.0) / 100.0;
	}
}
