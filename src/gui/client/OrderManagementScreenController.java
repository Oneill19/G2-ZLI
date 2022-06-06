package gui.client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import client.ChatClient;
import client.ClientUI;
import entity.Order;
import entity.StoreWorker;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * @author Koral Biton, Topaz Eldori
 *
 */
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

    /**
     * Confirmation of orders awaiting cancellation / confirmation
     * @param event
     * @throws Exception
     */
    @FXML
    void OnAprrove(ActionEvent event) throws Exception {
    	Order Selectedorder= ordersTable.getSelectionModel().getSelectedItem();
    	if(Selectedorder==null)
    		ErrorMsg.setText("Please choose one order to confirm!");
    	else {
    		OrderNumber =Selectedorder.getOrderNumber();
    		StatusOrder=Selectedorder.getOrderStatus();
    		if((StatusOrder.equals("WAITING_FOR_CANCELATION")) && (Selectedorder.getDeliveryMethod().equals("Delivery")))
    		{
            	ClientUI.chat.accept("CheckRefund" + "\t" + OrderNumber + "\t" + Selectedorder.getSupplyDate() + "\t" + Selectedorder.getSupplyTime() +  "\t" + Selectedorder.getTotalPrice());
            	ClientUI.chat.accept("UpdateBalance" + "\t" + ChatClient.refundAmount + "\t" + Selectedorder.getCustomerID());            	if(ChatClient.refundAmount>0)
            	{
            		//show message
        			Alert alert = new Alert(AlertType.CONFIRMATION);
        			alert.setTitle("Credit updated successfully!");
        			alert.setHeaderText("A credit message was sent to the customer in the amount of: " + ChatClient.refundAmount + "NIS");
        			alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("orderReception.png"))));
        			alert.showAndWait();
            	}
    		}
    		
        	ClientUI.chat.accept("UpdateStatusOrders" + "\t" + OrderNumber + "\t" + StatusOrder );
        	ordersTable.getItems().remove(Selectedorder) ;
        	}
     	// initialize error message
    	ErrorMsg.setText("");
    }

    /**
     *  Go back to the options screen
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
     *  exit from Zer-Li system 
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
     * Log out from the user and go back to login screen
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
    	System.out.println("On User Options");
    }
    
    //get the pending orders from DB to order TableView
    @FXML
    void initialize() throws IOException {
    	User.setText("Hello, " + ChatClient.user.getFirstName());

    	ClientUI.chat.accept("GetPendingOrders" + "\t" +((StoreWorker)ChatClient.user).getStoreName());
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