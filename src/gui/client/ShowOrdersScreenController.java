package gui.client;

import java.io.IOException;
import java.time.LocalDate;

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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowOrdersScreenController {

	ObservableList<Order> observableList;
	private int editedOrderNumber;
	private int editedColumn;
	private Object editedNewValue;

	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private TableColumn<Order, Integer> orderNumberCol;

	@FXML
	private TableColumn<Order, Double> priceCol;

	@FXML
	private TableColumn<Order, String> greetingCardCol;

	@FXML
	private TableColumn<Order, String> colorCol;

	@FXML
	private TableColumn<Order, String> dOrderCol;

	@FXML
	private TableColumn<Order, String> shopCol;

	@FXML
	private TableColumn<Order, LocalDate> dateCol;

	@FXML
	private TableColumn<Order, LocalDate> orderDateCol;

	@FXML
	private Button onBack;

	@FXML
	private Button exitClient;

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

		// Set handler on cell double click: Take input string and call accept.
		class CellHandler implements EventHandler<CellEditEvent<Order, String>> {

			@Override
			public void handle(CellEditEvent<Order, String> t) {
				((Order) t.getTableView().getItems().get(t.getTablePosition().getRow())).setColor((t.getNewValue()));
				editedOrderNumber = t.getRowValue().getOrderNumber();
				editedNewValue = t.getNewValue();
				editedColumn = t.getTablePosition().getColumn();

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

		// Set handler on date column double click to trigger DatePicker
		class DateCellHandler implements EventHandler<CellEditEvent<Order, LocalDate>> {

			@Override
			public void handle(CellEditEvent<Order, LocalDate> event) {
				event.getRowValue().setDate(event.getNewValue());

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

		ordersTable.setEditable(true);

		colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
		colorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		colorCol.setOnEditCommit(new CellHandler());
		colorCol.setEditable(true);

		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateCol.setCellFactory(col -> new MyDateCell());
		dateCol.setOnEditCommit(new DateCellHandler());
		dateCol.setEditable(true);

		// Bind other columns
		observableList = FXCollections.observableArrayList(ChatClient.orders);
		ordersTable.getItems().clear();
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		greetingCardCol.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
		dOrderCol.setCellValueFactory(new PropertyValueFactory<>("dorder"));
		shopCol.setCellValueFactory(new PropertyValueFactory<>("shop"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		ordersTable.setItems(observableList);
	}

}
