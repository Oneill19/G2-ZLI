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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;

    @FXML
    private TableColumn<Order, String> Status;

    @FXML
    private TableColumn<Order, Double> Price;

    @FXML
    private TableColumn<Order, String> dOrderCol;

    @FXML
    private TableColumn<Order, LocalDate> supplyDateCol;

    @FXML
    private TableColumn<Order, LocalTime> supplyTimeCol;



    @FXML
    private TableColumn<Order, LocalDate> orderDateCol;

    @FXML
    private TableColumn<Order, LocalTime> orderTimeCol;

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
    	Order Selectedorder= ordersTable.getSelectionModel().getSelectedItem();
    	if(Selectedorder==null)
    		ErrorMsg.setText("Please choose one order to confirm!");
    	else {
    		OrderNumber =Selectedorder.getOrderNumber();
    		StatusOrder=Selectedorder.getOrderStatus();
        	ClientUI.chat.accept("UpdateStatusOrders" + "\t" + OrderNumber + "\t" + StatusOrder );
        	ordersTable.getItems().remove(Selectedorder) ;
        	}
     	// initialize error message
    	ErrorMsg.setText("");
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
    void onExit(ActionEvent event) throws Exception{
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
    
    //get the pending orders from DB to order TableView
    @FXML
    void initialize() throws IOException {
    	ClientUI.chat.accept("GetPendingOrders");
    	observableList=FXCollections.observableArrayList(ChatClient.NotAprroveorders);
    	ordersTable.getItems().clear();
    	orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	dOrderCol.setCellValueFactory(new PropertyValueFactory<>("orderDesc"));
		Price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		Status.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationDate"));
		orderTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderCreationTime"));
		ordersTable.setItems(observableList);
    }
}

  

