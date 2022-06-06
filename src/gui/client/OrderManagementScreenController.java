package gui.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import client.ChatClient;
import client.ClientUI;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrderManagementScreenController {

	private int OrderNumber;
	private String StatusOrder;

	ObservableList<Order> observableList;

	@FXML private TableView<Order> ordersTable;
	@FXML private TableColumn<Order, Integer> orderNumberCol;
	@FXML private TableColumn<Order, String> Status, dOrderCol;
	@FXML private TableColumn<Order, Double> Price;
	@FXML private TableColumn<Order, LocalDate> supplyDateCol, creationDateCol;
	@FXML private TableColumn<Order, LocalTime> supplyTimeCol, creationTimeCol;

	@FXML
	private Button Exit;

	@FXML
	private Button Back;

	@FXML
	private Button User;

	@FXML
	private Button LogOut;

	@FXML
	private Button Approve;

	@FXML
	private Text ErrorMsg;

	@FXML
	void OnAprrove(ActionEvent event) throws Exception {
		double compensation = 0;
		Order Selectedorder = ordersTable.getSelectionModel().getSelectedItem();
		if (Selectedorder == null)
			ErrorMsg.setText("Please choose one order to confirm!");
		else {
			OrderNumber = Selectedorder.getOrderNumber();
			StatusOrder = Selectedorder.getOrderStatus();
			ClientUI.chat.accept("UpdateStatusOrders" + "\t" + OrderNumber + "\t" + StatusOrder);
			ordersTable.getItems().remove(Selectedorder);

			//calculate compensation and send sms about order cancelation to user.
			if (Selectedorder.getOrderStatus().equals("WAITING_FOR_CANCELATION")) {
				if (Selectedorder.getDeliveryMethod().equals("Delivery")) {
					compensation = calculateCompensation(Selectedorder);
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Sms and Mail");
					alert.setHeaderText("An SMS and Email were send to the client that his order was approved.\n"
							+ "Details: Dear Customer\n"
							+ "You're request to cancel order was approved.\n"
							+ "According to our refund reules, you may get refund of " + compensation + "$\n"
							+ "Please come visit soon, Zer-li");
					alert.showAndWait();

				}
				ClientUI.chat.accept("updateBalance\t"+Selectedorder.getCustomerID()+"\t"+compensation);
				if(!ChatClient.requestSucceed) {
					System.out.println("problem with updateBalance");
					return;
				}
			}else if(Selectedorder.getOrderStatus().equals("WAITING_FOR_CONFIRMATION")) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Sms and Mail");
				alert.setHeaderText("An SMS and Email were send to the client that his order was approved.");
				alert.showAndWait();
			}
		}
		// initialize error message
		ErrorMsg.setText("");
	}

	private double calculateCompensation(Order order) throws IOException {
		double compensation = 0;
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

		ClientUI.chat.accept("getOrderSupplyDateTime\t" + OrderNumber);
		if (ChatClient.orderCreationDateTime == null) {
			System.out.println("getOrderCreation probelm");
			return 0;
		}
		String dateTime[] = ChatClient.orderCreationDateTime.split(" ");
		LocalDate date = LocalDate.parse(dateTime[0]);
		LocalTime time = LocalTime.parse(dateTime[1]);

		if (date.compareTo(today) < 1)
			compensation = 0;
		else {
			int hourDiff = time.getHour() - now.getHour();
			if (hourDiff < 0)
				compensation = 0;
			else if (hourDiff > 1 && hourDiff < 1)
				compensation = order.getTotalPrice() * 0.5;
			else
				compensation = order.getTotalPrice();
		}
		return compensation;
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("StoreManagerScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client->Options Screen");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onExit(ActionEvent event) throws Exception {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onLogOut(ActionEvent event) throws Exception {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("LoginScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@FXML
	void onUser(ActionEvent event) {

	}

	// get the pending orders from DB to order TableView
	@FXML
	void initialize() throws IOException {
		User.setText("Hello, " + ChatClient.user.getFirstName());

		ClientUI.chat.accept("GetPendingOrders");
		observableList = FXCollections.observableArrayList(ChatClient.NotAprroveorders);
		ordersTable.getItems().clear();
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		dOrderCol.setCellValueFactory(new PropertyValueFactory<>("orderDesc"));
		Price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		Status.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		creationDateCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationDate"));
		creationTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationTime"));
		supplyDateCol.setCellValueFactory(new PropertyValueFactory<>("supplyDate"));
		supplyTimeCol.setCellValueFactory(new PropertyValueFactory<>("supplyTime"));
		ordersTable.setItems(observableList);
	}
}
