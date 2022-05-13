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

public class ShowOrdersScreenController {

	ObservableList<Order> observableList;
	private int editedOrderNumber;
	private int editedColumn;
	private Object editedNewValue;

	// ******************
	// *****Buttons******
	// ******************
	@FXML
	private Button exitClient;
	@FXML
	private Button onBack;

	// ******************
	// *****Columns******
	// ******************
	@FXML
	private TableColumn<Order, String> colorCol;
	@FXML
	private TableColumn<Order, LocalDate> completeDateCol;
	@FXML
	private TableColumn<Order, LocalDate> confirmedDateCol;
	@FXML
	private TableColumn<Order, Integer> customerIDCol;
	@FXML
	private TableColumn<Order, String> deliveryMethidCol;
	@FXML
	private TableColumn<Order, LocalDate> orderCreationDateCol;
	@FXML
	private TableColumn<Order, LocalTime> orderCreationTimeCol;
	@FXML
	private TableColumn<Order, String> orderStatusCol;
	@FXML
	private TableColumn<Order, String> paymentMethodCol;
	@FXML
	private TableColumn<Order, Integer> orderNumberCol;
	@FXML
	private TableColumn<Order, Double> priceCol;
	@FXML
	private TableColumn<Order, String> greetingCardCol;
	@FXML
	private TableColumn<Order, String> DOrderCol;
	@FXML
	private TableColumn<Order, String> shopCol;
	@FXML
	private TableColumn<Order, LocalDate> supplyDateCol;
	@FXML
	private TableColumn<Order, LocalTime> supplyTimeCol;
	@FXML
	private TableColumn<Order, LocalDate> orderDateCol;
	@FXML
	private TableColumn<Order, LocalTime> orderTimeCol;

	// ******************
	// *****Table******
	// ******************
	@FXML
	private TableView<Order> ordersTable;

	// ******************
	// *****ActionEvent**
	// ******************
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
				((Order) event.getTableView().getItems().get(event.getTablePosition().getRow()))
						.setColor((event.getNewValue()));
				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedNewValue = event.getNewValue();
				editedColumn = event.getTablePosition().getColumn();
				try {
					ClientUI.chat.accept("CellUpdate\t" + editedOrderNumber + "\t" + editedNewValue.toString() + "\t"
							+ editedColumn);
				} catch (IOException e) {
					e.printStackTrace();
				}

				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = 0;
			}
		}

		// Set handler on Time column
		class TimeCellHandler implements EventHandler<CellEditEvent<Order, LocalTime>> {
			@Override
			public void handle(CellEditEvent<Order, LocalTime> event) {
				ordersTable.setFocusTraversable(true);
				editedColumn = event.getTablePosition().getColumn();
				System.out.println(editedColumn);
				
				((Order) event.getTableView().getItems().get(event.getTablePosition().getRow()))
						.setOrderCreationTime(((event.getNewValue())));
				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedNewValue = event.getNewValue();
				editedColumn = event.getTablePosition().getColumn();

				/*
				try {
					String toAccept = "CellUpdate\t" + editedOrderNumber + "\t" + editedNewValue.toString() + "\t"
							+ editedColumn;
					System.out.println(toAccept);
					ClientUI.chat.accept(toAccept);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("end");
				 */
				
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = 0;
			}
		}

		// Set handler on date column double click to trigger DatePicker
		class DateCellHandler implements EventHandler<CellEditEvent<Order, LocalDate>> {
			@Override
			public void handle(CellEditEvent<Order, LocalDate> event) {
				ordersTable.requestFocus();
				event.getRowValue().setOrderCreationDate(((event.getNewValue())));

				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedNewValue = event.getNewValue();
				editedColumn = event.getTablePosition().getColumn();

				try {
					ClientUI.chat.accept("cellUpdate\t" + editedOrderNumber + "\t" + editedNewValue.toString() + "\t"
							+ editedColumn);
				} catch (IOException e) {
					e.printStackTrace();
				}
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = 0;
			}
		}

		colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
		colorCol.setCellFactory(col -> EditCell.createStringEditCell());
		colorCol.setOnEditCommit(new CellHandler());
		colorCol.setEditable(true);

		/*
		orderCreationDateCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationDate"));
		orderCreationDateCol.setCellFactory(col -> new MyDateCell());
		orderCreationDateCol.setOnEditCommit(new DateCellHandler());
		orderCreationDateCol.setEditable(true);
		*/

		
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

		ordersTable.setItems(observableList);
	}

}
