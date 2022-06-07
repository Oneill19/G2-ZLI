package gui.client;

import java.time.LocalDate;
import java.time.LocalTime;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ManageDeliveryController {

    @FXML
    private Button Approve;

    @FXML
    private Button Back;

    @FXML
    private Text ErrorMsg;

    @FXML
    private Button Exit;

    @FXML
    private Button LogOut;

    @FXML
    private Button User;

    @FXML
    private TableColumn<Order, Integer> orderNumberCol;

    @FXML
    private TableView<Order> ordersTable;
    
    @FXML
    private TableColumn<Order, Integer> customerId;

    @FXML
    private TableColumn<Order, Double> priceCol;
    
    private CommonController cc = new CommonController();
    
    private ObservableList<Order> observableList;

    @FXML
    void OnAprrove(ActionEvent event) throws Exception {
    	if (ordersTable.getSelectionModel() == null || ordersTable.getSelectionModel().getSelectedItem() == null) {
    		ErrorMsg.setText("Please choose order");
    		return;
    	}
    	
    	ErrorMsg.setText("");
    	
    	Order order = ordersTable.getSelectionModel().getSelectedItem();
    	
    	ClientUI.chat.accept("ApproveDeliveryOfOrder" + "\t" +  order.getCustomerID() + "\t" + order.getOrderNumber());
    	
    	String date = order.getConfirmedDate();
    	
    	LocalDate confirmDate = LocalDate.parse(date.split(" ")[0]);
    	LocalTime confirmTime = LocalTime.parse(date.split(" ")[1]);
    	
    	LocalDate nowDate = LocalDate.now();
    	LocalTime nowTime = LocalTime.now();
    	
    	if (ChatClient.customerOfDelivery != null) {
    		if (confirmDate.equals(order.getSupplyDate())) {
    			int nowMinutes = nowTime.getHour() * 60 + nowTime.getMinute();
    			int confirmMinutes = confirmTime.getHour() * 60 + confirmTime.getMinute();
    			if (nowMinutes - confirmMinutes > 180) {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Full Refund");
    				alert.setHeaderText("SMS and Email about full refund (" + order.getTotalPrice() + ") sent to: " + ChatClient.customerOfDelivery.getEmail() + ", and to: " + ChatClient.customerOfDelivery.getPhone());
    				alert.showAndWait();
    				ClientUI.chat.accept("FullRefundOf" + "\t" + ChatClient.customerOfDelivery.getUserID() + "\t" + (ChatClient.customerOfDelivery.getBalance() + order.getTotalPrice()));
    				return;
    			}
    		}
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Error in getting data from the server");
    		alert.showAndWait();
    		return;
    	}
    	
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Delivery Accepted");
		alert.setHeaderText("Delivery Accepted");
		alert.showAndWait();
    }

    /**
	 * go to the previous screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		// go to the previous screen
		cc.changeFXML(event, "DeliveryScreen.fxml", "Zer-Li Delivery");
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
	 * logout from the user
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onLogOut(ActionEvent event) throws Exception {
		// logout and go to the login page
		cc.onLogout(event);
	}

    @FXML
    void onUser(ActionEvent event) {

    }
    
    /**
	 * initialize the screen
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		// set the user name
		User.setText("Hello, " + ChatClient.user.getFirstName());
		
		ClientUI.chat.accept("GetAllConfirmedOfDelivery");
		
		if (ChatClient.confirmedOfDelivery != null) {
			
			observableList = FXCollections.observableArrayList(ChatClient.confirmedOfDelivery);
			
			orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
			customerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
			ordersTable.setItems(observableList);
		}
	}

}
