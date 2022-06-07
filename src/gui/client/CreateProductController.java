package gui.client;

import java.util.HashMap;
import java.util.Map;

import client.ChatClient;
import client.ClientUI;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class CreateProductController {

	@FXML
	private Label addLabel;

	@FXML
	private Button backButton;

	@FXML
	private Button addOne;

	@FXML
	private Button deleteOne;

	@FXML
	private ComboBox<String> colorComboBox;

	@FXML
	private ComboBox<String> productType;

	@FXML
	private Button exitBtn;

	@FXML
	private TableView<Item> itemTable;

	@FXML
	private TableColumn<Item, String> serialCol;

	@FXML
	private TableColumn<Item, String> nameCol;

	@FXML
	private TableColumn<Item, Integer> amountCol;

	@FXML
	private Button logoutBtn;

	@FXML
	private Label messageLabel;

	@FXML
	private TextField productName;

	@FXML
	private TextField productPrice;

	@FXML
	private TextField itemType;

	@FXML
	private Button saveButton;

	@FXML
	private Button userOptBtn;

	private ObservableList<Item> observableList;

	private HashMap<String, Integer> items = new HashMap<>();

	private CommonController cc = new CommonController();

	/**
	 * go to the previous screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		// go to the previous screen
		cc.changeFXML(event, "MarketingEmployeOptions.fxml", "Zer-Li Marketing Employee Options");
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

	/**
	 * add one item to the premade product list
	 * 
	 * @param event
	 */
	@FXML
	void onAddOne(ActionEvent event) {
		// check if the user selected a row in the table
		if (itemTable.getSelectionModel() == null || itemTable.getSelectionModel().getSelectedItem() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please choose item");
			return;
		}

		messageLabel.setText("");

		// add one to the row
		String itemId = itemTable.getSelectionModel().getSelectedItem().getSerialNumber();
		String itemName = itemTable.getSelectionModel().getSelectedItem().getName();
		int amount = 0;

		// add the item to the map holding the items and amounts
		if (items.containsKey(itemId)) {
			amount = 1 + items.get(itemId);
		} else {
			amount = 1;
		}
		// add the item amount to the hash map
		items.put(itemId, amount);

		// add one to the item in the table
		int index = itemTable.getSelectionModel().getFocusedIndex();
		Item item = itemTable.getSelectionModel().getSelectedItem();
		item.setAmountInProduct(amount);
		itemTable.getItems().set(index, item);

		// success message
		addLabel.setTextFill(Color.GREEN);
		addLabel.setText("One " + itemName + " Added");
	}

	/**
	 * subtract one item from the premade product list
	 * 
	 * @param event
	 */
	@FXML
	void onDeleteOne(ActionEvent event) {
		// check if the user selected a row in the table
		if (itemTable.getSelectionModel() == null || itemTable.getSelectionModel().getSelectedItem() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please choose item");
			return;
		}

		messageLabel.setText("");

		// subtract one from the row
		String itemId = itemTable.getSelectionModel().getSelectedItem().getSerialNumber();
		String itemName = itemTable.getSelectionModel().getSelectedItem().getName();
		int amount = 0;

		// subtract the item from the map holding the items and amounts
		if (items.containsKey(itemId)) {
			amount = items.get(itemId) - 1;
			if (amount < 0) {
				amount = 0;
			}
		} else {
			amount = 0;
		}
		items.put(itemId, amount);

		// subtract one from the item in the table
		int index = itemTable.getSelectionModel().getFocusedIndex();
		Item item = itemTable.getSelectionModel().getSelectedItem();
		item.setAmountInProduct(amount);
		itemTable.getItems().set(index, item);

		// success message
		addLabel.setTextFill(Color.RED);
		addLabel.setText("One " + itemName + " Deleted");
	}

	/**
	 * save the new product in the database
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onSave(ActionEvent event) throws Exception {
		// check if any field not field
		if (productName.getText().equals("") || productPrice.getText().equals("") || itemType.getText().equals("")
				|| colorComboBox.getValue() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please fill all fields");
			return;
		}

		// get the values
		String name = productName.getText();
		double price = 0;
		try {
			price = Double.parseDouble(productPrice.getText());
		} catch (Exception e) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Price must be a number");
			return;
		}
		String type = itemType.getText();
		String color = colorComboBox.getValue();

		// if premade product generate item list and build a new product
		if (productType.getValue() == "Premade Product") {

			// generate item list
			String itemsList = buildItemsString();
			if (itemsList.equals("")) {
				messageLabel.setTextFill(Color.RED);
				messageLabel.setText("Please choose at least one item from the list");
				return;
			}

			// add to the database
			ClientUI.chat.accept(
					"CreateProduct" + "\t" + name + "\t" + price + "\t" + type + "\t" + color + "\t" + itemsList);

			// if the request succeed
			if (ChatClient.requestSucceed) {
				messageLabel.setTextFill(Color.GREEN);
				messageLabel.setText(name + " Added");
			} else {
				messageLabel.setTextFill(Color.RED);
				messageLabel.setText("There was a problem adding " + name);
			}
		}

		// if item
		else {

			// add to the database
			ClientUI.chat.accept("CreateItem" + "\t" + name + "\t" + price + "\t" + type + "\t" + color);

			// if the request succeed
			if (ChatClient.requestSucceed) {
				messageLabel.setTextFill(Color.GREEN);
				messageLabel.setText(name + " Added");
			} else {
				messageLabel.setTextFill(Color.RED);
				messageLabel.setText("There was a problem adding " + name);
			}
		}

		// initialize the screen
		productName.setText("");
		productPrice.setText("");
		itemType.setText("");
		colorComboBox.valueProperty().set(null);
		productName.setDisable(true);
		productPrice.setDisable(true);
		itemType.setDisable(true);
		colorComboBox.setDisable(true);
		itemTable.getItems().clear();
		itemTable.setDisable(true);
		addOne.setDisable(true);
		deleteOne.setDisable(true);
	}

	/**
	 * initilize text fields and table after choosing product type to create
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onSelectProduct(ActionEvent event) throws Exception {
		// check if the user selected an option
		if (productType.getValue() == null) {
			return;
		}

		// initialize fields
		productName.setText("");
		productPrice.setText("");
		itemType.setText("");
		colorComboBox.valueProperty().set(null);
		productName.setDisable(false);
		productPrice.setDisable(false);
		itemType.setDisable(false);
		colorComboBox.setDisable(false);

		// if premade product initialize item table
		if (productType.getValue() == "Premade Product") {
			itemTable.setDisable(false);
			addOne.setDisable(false);
			deleteOne.setDisable(false);

			ClientUI.chat.accept("GetItemsForCreate");

			if (ChatClient.itemsForCreate == null) {
				messageLabel.setTextFill(Color.RED);
				messageLabel.setText("Error getting data");
				return;
			}
			messageLabel.setText("");

			observableList = FXCollections.observableArrayList(ChatClient.itemsForCreate);

			updateTable();
		} else {
			itemTable.getItems().clear();
			itemTable.setDisable(true);
			addOne.setDisable(true);
			deleteOne.setDisable(true);
		}
	}

	/**
	 * initialize the screen
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		// set the user name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());

		// add the product creation options
		productType.getItems().clear();
		productType.getItems().add("Premade Product");
		productType.getItems().add("Item");

		// get the colors from the database
		ClientUI.chat.accept("GetAllColors");

		if (ChatClient.colors == null) {
			messageLabel.setText("Error getting data from the database");
			return;
		}
		messageLabel.setText("");

		// add the colors to the combobox
		colorComboBox.getItems().clear();
		for (String color : ChatClient.colors) {
			colorComboBox.getItems().add(color);
		}

//		// init buttons style
		backButton.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(backButton));
		backButton.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(backButton));
		
		addOne.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(addOne));
		addOne.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnExit(addOne));
		
		deleteOne.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnEnter(deleteOne));
		deleteOne.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(deleteOne));
	}

	/**
	 * update the table values
	 */
	private void updateTable() {
		itemTable.getItems().clear();
		serialCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amountInProduct"));
		itemTable.setItems(observableList);
	}

	/**
	 * generate a list of item as a string
	 * 
	 * @return string of the item list
	 */
	private String buildItemsString() {
		String value = "";
		for (Map.Entry<String, Integer> entry : items.entrySet()) {
			value += entry.getKey() + " " + entry.getValue() + ",";
		}
		return value;
	}

}
