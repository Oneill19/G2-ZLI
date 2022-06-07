package gui.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.CustomProduct;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CreateCustomProductController {

	@FXML
	private Button addOne;

	@FXML
	private Button backButton;

	@FXML
	private ComboBox<String> colorComboBox;

	@FXML
	private Button deleteOne;

	@FXML
	private Button exitBtn;

	@FXML
	private TableView<TempProduct> itemTable;

	@FXML
	private TableColumn<TempProduct, ImageView> imageCol;

	@FXML
	private TableColumn<TempProduct, String> serialCol;

	@FXML
	private TableColumn<TempProduct, String> nameCol;

	@FXML
	private TableColumn<TempProduct, Integer> amountCol;

	@FXML
	private TableColumn<TempProduct, Double> priceCol;

	@FXML
	private Button logoutBtn;

	@FXML
	private Label messageLabel;

	@FXML
	private TextField productName;

	@FXML
	private TextField productPrice1;

	@FXML
	private TextField productPrice2;

	@FXML
	private Button saveButton;

	@FXML
	private Button userOptBtn;

	private CommonController cc = new CommonController();

	private ObservableList<TempProduct> observableList;

	private HashMap<String, Integer> items = new HashMap<>();

	private HashMap<String, Integer> products = new HashMap<>();

	private ArrayList<TempProduct> tp = new ArrayList<>();

	/**
	 * go to the previous screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		// go to the previous screen
		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog");
	}

	/**
	 * logout from the user
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onLogout(ActionEvent event) throws Exception {
		// logout and go to the login page
		cc.onLogout(event);
	}

	/**
	 * exit the program
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onExit(ActionEvent event) throws Exception {
		cc.OnExit();
	}

	@FXML
	void onAddOne(ActionEvent event) {
		// check if the user selected a row in the table
		if (itemTable.getSelectionModel() == null || itemTable.getSelectionModel().getSelectedItem() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please choose item");
			return;
		}

		messageLabel.setText("");

		TempProduct p = itemTable.getSelectionModel().getSelectedItem();
		int amount = 0;

		// add the item to the matching hash map
		if (p.getType().equals("Product")) {
			amount = addToHashMap(products, p);
		} else {
			amount = addToHashMap(items, p);
		}

		// add one to the item in the table
		int index = itemTable.getSelectionModel().getFocusedIndex();
		p.setAmount(amount);
		itemTable.getItems().set(index, p);
	}

	@FXML
	void onDeleteOne(ActionEvent event) {
		// check if the user selected a row in the table
		if (itemTable.getSelectionModel() == null || itemTable.getSelectionModel().getSelectedItem() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please choose item");
			return;
		}

		messageLabel.setText("");

		TempProduct p = itemTable.getSelectionModel().getSelectedItem();
		int amount = 0;

		// add the item to the matching hash map
		if (p.getType().equals("Product")) {
			amount = subtractFromHashMap(products, p);
		} else {
			amount = subtractFromHashMap(items, p);
		}

		// add one to the item in the table
		int index = itemTable.getSelectionModel().getFocusedIndex();
		p.setAmount(amount);
		itemTable.getItems().set(index, p);
	}

	@FXML
	void onSave(ActionEvent event) throws Exception {
		// check if any field not field
		if (productName.getText().equals("") || productPrice1.getText().equals("") || productPrice2.getText().equals("")
				|| colorComboBox.getValue() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please fill all fields");
			return;
		}

		// get the values;
		String name = productName.getText();
		try {
			Double.parseDouble(productPrice1.getText());
			Double.parseDouble(productPrice2.getText());
		} catch (Exception e) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Price must be a number");
			return;
		}
		String price = productPrice1.getText() + "-" + productPrice2.getText();
		String color = colorComboBox.getValue();
		String productLists = buildItemsString(products);
		String itemLists = buildItemsString(items);

		ClientUI.chat.accept("GetAmountOfCustomProduct");
		String id = (ChatClient.customProductCounter + 1) + "";

		CustomProduct newCustomProduct = new CustomProduct(id, name, price, color, productLists, itemLists, 0);

		ChatClient.customerCart.put(newCustomProduct, 1);

		ClientUI.chat.accept("AddCustomProduct" + "\t" + id + "\t" + name + "\t" + price + "\t" + color + "\t"
				+ productLists + "\t" + itemLists);

		if (ChatClient.requestSucceed) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Added custom product");
			alert.setHeaderText("Your custom product added to cart");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in adding ustom product");
			alert.setHeaderText("There was an error adding the item");
			alert.showAndWait();
		}

		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog");
	}

	/**
	 * update the table values
	 */
	private void updateTable() {
		itemTable.getItems().clear();
		imageCol.setCellValueFactory(new PropertyValueFactory<>("iv"));
		serialCol.setCellValueFactory(new PropertyValueFactory<>("serial"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		itemTable.setItems(observableList);
	}

	/**
	 * initialize the screen
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		// set the user name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());

		for (AbstractProduct ap : ChatClient.products) {
			String type = ap instanceof Product ? "Product" : "Item";
			ap.setImageView();
			tp.add(new TempProduct(ap.getImageView(), ap.getSerialNumber(), ap.getName(), 0, ap.getPrice(), type));
		}

		observableList = FXCollections.observableArrayList(tp);

		// get the colors from the database
		ClientUI.chat.accept("GetAllColors");

		if (ChatClient.colors == null) {
			messageLabel.setText("Error getting data from the database");
			return;
		}
		messageLabel.setText("");

		// add the colors to the combo box
		colorComboBox.getItems().clear();
		for (String color : ChatClient.colors) {
			colorComboBox.getItems().add(color);
		}

		updateTable();

//		// init buttons style
		backButton.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(backButton));
		backButton.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(backButton));

		addOne.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(addOne));
		addOne.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnExit(addOne));

		deleteOne.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnEnter(deleteOne));
		deleteOne.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(deleteOne));
	}

	private int addToHashMap(HashMap<String, Integer> hm, TempProduct ap) {
		// add the item to the map holding the items and amounts
		int amount = 0;
		if (hm.containsKey(ap.getSerial())) {
			amount = 1 + hm.get(ap.getSerial());
		} else {
			amount = 1;
		}
		hm.put(ap.getSerial(), amount);
		return amount;
	}

	private int subtractFromHashMap(HashMap<String, Integer> hm, TempProduct ap) {
		// add the item to the map holding the items and amounts
		int amount = 0;
		if (hm.containsKey(ap.getSerial())) {
			amount = hm.get(ap.getSerial()) - 1;
			if (amount < 0) {
				amount = 0;
			}
		} else {
			amount = 0;
		}
		hm.put(ap.getSerial(), amount);
		return amount;
	}

	/**
	 * generate a list of item as a string
	 * 
	 * @return string of the item list
	 */
	private String buildItemsString(HashMap<String, Integer> hm) {
		String value = "";
		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			value += entry.getKey() + " " + entry.getValue() + ",";
		}
		return value;
	}

	public class TempProduct {
		private ImageView iv;
		private String serial;
		private String name;
		private int amount;
		private double price;
		private String type;

		private TempProduct(ImageView iv, String serial, String name, int amount, double price, String type) {
			this.iv = iv;
			this.serial = serial;
			this.name = name;
			this.amount = amount;
			this.price = price;
			this.type = type;
		}

		/**
		 * @return the iv
		 */
		public ImageView getIv() {
			return iv;
		}

		/**
		 * @param iv the iv to set
		 */
		public void setIv(ImageView iv) {
			this.iv = iv;
		}

		/**
		 * @return the serial
		 */
		public String getSerial() {
			return serial;
		}

		/**
		 * @param serial the serial to set
		 */
		public void setSerial(String serial) {
			this.serial = serial;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the amount
		 */
		public int getAmount() {
			return amount;
		}

		/**
		 * @param amount the amount to set
		 */
		public void setAmount(int amount) {
			this.amount = amount;
		}

		/**
		 * @return the price
		 */
		public double getPrice() {
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(double price) {
			this.price = price;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
	}

}
