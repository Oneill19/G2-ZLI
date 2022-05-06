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

    @FXML
    private TableColumn<Order, String> colorCol;

    @FXML
    private TableColumn<Order, String> dOrderCol;

    @FXML
    private Button exitClient;

    @FXML
    private TableColumn<Order, String> greetingCardCol;

    @FXML
    private Button onBack;

    @FXML
    private TableColumn<Order, LocalDate> orderDateCol;

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Double> priceCol;

    @FXML
    private TableColumn<Order, String> shopCol;

    @FXML
    private TableColumn<Order, LocalDate> supplyDateCol;

    @FXML
    private TableColumn<Order, LocalTime> timeDateCol;

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
				((Order) event.getTableView().getItems().get(event.getTablePosition().getRow())).setColor((event.getNewValue()));
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
		
		// Set handler on date column double click to trigger DatePicker
		class TimeCellHandler implements EventHandler<CellEditEvent<Order, LocalTime>> {

			@Override
			public void handle(CellEditEvent<Order, LocalTime> event) {
				ordersTable.setFocusTraversable(true);			
				((Order) event.getTableView().getItems().get(event.getTablePosition().getRow())).setSupplyTime(event.getNewValue());
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

		// Set handler on date column double click to trigger DatePicker
		class DateCellHandler implements EventHandler<CellEditEvent<Order, LocalDate>> {

			@Override
			public void handle(CellEditEvent<Order, LocalDate> event) {
				ordersTable.requestFocus();
				event.getRowValue().setSupplyDate(event.getNewValue());

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

		supplyDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		supplyDateCol.setCellFactory(col -> new MyDateCell());
		supplyDateCol.setOnEditCommit(new DateCellHandler());
		supplyDateCol.setEditable(true);
				
		timeDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		timeDateCol.setCellFactory(col -> new EditTimeCell());
		timeDateCol.setOnEditCommit(new TimeCellHandler());
		timeDateCol.setEditable(true);

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
