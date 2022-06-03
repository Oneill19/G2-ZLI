package gui.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import client.ChatClient;
import client.ClientUI;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * @AUTHOR DORIN BEERY
 * IMPORTANT
 * This is a prototype class for all screens which have a tableview object.
 * It is important to know that TimeCellHandler and DateCellHandler
 * have to know which column is being edited
 * in order to decide which order.field is being get\set.
 * please notice these, and change the methods' where BEWARE is written
 * 
 */
public class ShowOrdersScreenController {

	ObservableList<Order> observableList;
	private int editedOrderNumber;
	private String editedColumn, editedNewValue;
	
	@FXML private Button exitClient, onBack; 
	@FXML private TableColumn<Order, String> colorCol, deliveryMethidCol, orderStatusCol, paymentMethodCol, greetingCardCol,
											DOrderCol, shopCol;
	@FXML private TableColumn<Order, LocalDate> completeDateCol, confirmedDateCol, orderCreationDateCol, supplyDateCol, orderDateCol;
	@FXML private TableColumn<Order, Integer> customerIDCol, orderNumberCol;
	@FXML private TableColumn<Order, LocalTime> orderCreationTimeCol, supplyTimeCol, orderTimeCol;
	@FXML private TableColumn<Order, Double> priceCol;
	@FXML private TableView<Order> ordersTable;

	/**
	 * @param event of clicking on Back button
	 * @throws Exception
	 */
	@FXML
	public void onBack(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("OptionsScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param event of clicking on exit button
	 * @throws Exception
	 */
	@FXML
	public void onExit(ActionEvent event) throws Exception {
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

	
	public void initialize() {
		ordersTable.setEditable(true);

		// Set handler on cell double click: Take input string and call accept.
		class CellHandler implements EventHandler<CellEditEvent<Order, String>> {
			@Override
			public void handle(CellEditEvent<Order, String> event) {
				ordersTable.setFocusTraversable(true);

				// Set the new value of the cell
				((Order) event.getTableView().getItems().get(event.getTablePosition().getRow()))
						.setColor((event.getNewValue()));

				// initial values for accept
				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedNewValue = event.getNewValue().toString();
				editedColumn = event.getTableColumn().getId();

				try {
					ClientUI.chat
							.accept("CellUpdate\t" + editedOrderNumber + "\t" + editedNewValue + "\t" + editedColumn);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// nullify values
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = null;
			}
		}

		/**
		 * @author Dorin Set handler on Time column
		 *
		 */
		class TimeCellHandler implements EventHandler<CellEditEvent<Order, LocalTime>> {
			@Override
			public void handle(CellEditEvent<Order, LocalTime> event) {
				ordersTable.setFocusTraversable(true);

				// initial values for accept
				editedColumn = event.getTableColumn().getId();
				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedNewValue = event.getNewValue().toString();
				String toAccept = "CellUpdate" + "\t" + editedOrderNumber + "\t" + editedNewValue + "\t" + editedColumn;

				// BEWARE
				// set the new value of the cell
				if (editedColumn.equals("orderCreationTime"))
					((Order) event.getTableView().getItems().get(event.getTablePosition().getRow()))
							.setOrderCreationTime(((event.getNewValue())));
				// else if(editedColumn.equals("supplyDate"))

				try {
					ClientUI.chat.accept(toAccept);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// nullify values for accept
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = null;
			}
		}

		/**
		 * @author Dorin Set handler on date column double click to trigger DatePicker
		 */
		class DateCellHandler implements EventHandler<CellEditEvent<Order, LocalDate>> {
			@Override
			public void handle(CellEditEvent<Order, LocalDate> event) {
				ordersTable.requestFocus();
				event.getRowValue().setOrderCreationDate(((event.getNewValue())));

				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedColumn = event.getTableColumn().getId();
				editedNewValue = event.getNewValue().toString();

				try {
					ClientUI.chat.accept(
							"CellUpdate" + "\t" + editedOrderNumber + "\t" + editedNewValue + "\t" + editedColumn);
				} catch (IOException e) {
					e.printStackTrace();
				}
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = null;
			}
		}

		colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
		colorCol.setCellFactory(col -> EditCell.createStringEditCell());
		colorCol.setOnEditCommit(new CellHandler());
		colorCol.setEditable(true);

		orderCreationDateCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationDate"));
		orderCreationDateCol.setCellFactory(col -> new MyDateCell());
		orderCreationDateCol.setOnEditCommit(new DateCellHandler());
		orderCreationDateCol.setEditable(true);

		orderCreationTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationTime"));
		orderCreationTimeCol.setCellFactory(col -> new EditTimeCell());
		orderCreationTimeCol.setOnEditCommit(new TimeCellHandler());
		orderCreationTimeCol.setEditable(true);

		// Bind other columns
		observableList = FXCollections.observableArrayList(ChatClient.orders);
		ordersTable.getItems().clear();
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		greetingCardCol.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
		DOrderCol.setCellValueFactory(new PropertyValueFactory<>("orderDesc"));
		shopCol.setCellValueFactory(new PropertyValueFactory<>("fromStore"));
		completeDateCol.setCellValueFactory(new PropertyValueFactory<>("completeDate"));
		confirmedDateCol.setCellValueFactory(new PropertyValueFactory<>("confirmedDate"));
		customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		deliveryMethidCol.setCellValueFactory(new PropertyValueFactory<>("deliveryMethod"));
		orderStatusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		paymentMethodCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
		supplyDateCol.setCellValueFactory(new PropertyValueFactory<>("supplyDate"));
		supplyTimeCol.setCellValueFactory(new PropertyValueFactory<>("supplyTime"));

		// Set ID for each column
		// Benefit: mysqlConnection.java
		// Concatenate the column id to the [sqlQuery], leads to shorter code
		// approximately by 16*7 lines.
		orderNumberCol.setId("orderNumber");
		priceCol.setId("totalPrice");
		greetingCardCol.setId("greetingCard");
		DOrderCol.setId("orderDesc");
		shopCol.setId("fromStore");
		completeDateCol.setId("completeDate");
		confirmedDateCol.setId("confirmedDate");
		customerIDCol.setId("customerID");
		deliveryMethidCol.setId("deliveryMethod");
		orderStatusCol.setId("orderStatus");
		paymentMethodCol.setId("paymentMethod");
		supplyDateCol.setId("supplyDate");
		supplyTimeCol.setId("supplyTime");
		colorCol.setId("color");
		orderCreationDateCol.setId("orderCreationDate");
		orderCreationTimeCol.setId("orderCreationTime");

		ordersTable.getItems().clear();
		ordersTable.setItems(observableList);
	}

}
