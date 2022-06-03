package gui.client;

import client.ChatClient;
import client.ClientUI;
import entity.Complaint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
public class ShowComplaintsController {

    @FXML
    private TextField amount;

    @FXML
    private Button closeComplaint;
    
    @FXML
    private TableView<Complaint> complaints;
    
    @FXML
    private TableColumn<Complaint, Integer> complaintId;
    
    @FXML
    private TableColumn<Complaint, Integer> userId;

    @FXML
    private TableColumn<Complaint, Integer> orderNumber;
    
    @FXML
    private TableColumn<Complaint, String> complaintDescription;
    
    @FXML
    private Label messageLabel;

    @FXML
    private Button exitBtn;

    @FXML
    private Button fullRefund;

    @FXML
    private Button handleComplaint;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button onBack;
    
    @FXML
    private Button refundByAmountBtn;

    @FXML
    private Button userOptBtn;
    
    private ObservableList<Complaint> observableList;
    
    private CommonController cc = new CommonController();
    
    private Complaint selected;
    
    private int getComplaintId;
    
    private int getUserId;
    
    private int getOrderNumber;
    
    /**
     * go back to the user options screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onBack(ActionEvent event) throws Exception {
    	ChatClient.allComplaints.clear();
    	// go to the previous screen
    	cc.changeFXML(event, "CustomerServiceWorkerScreen.fxml", "Zer-Li Customer Service Worker Screen");
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
     * log out from the user
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onLogout(ActionEvent event) throws Exception {
    	ChatClient.allComplaints.clear();
    	cc.onLogout(event);
    }
    
    /**
     * handle a certain complaint from the table
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onHandleComplaint(ActionEvent event) throws Exception {
    	// if not complaint selected from the database
    	if (complaints.getSelectionModel() == null || complaints.getSelectionModel().getSelectedItem() == null) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please choose a complaint");
    		return;
    	}
    	
    	// get the data from the table
    	messageLabel.setText("");
    	getComplaintId = complaints.getSelectionModel().getSelectedItem().getComplaintId();
    	getUserId = complaints.getSelectionModel().getSelectedItem().getCustomerId();
    	getOrderNumber = complaints.getSelectionModel().getSelectedItem().getOrderNumber();
    	selected = complaints.getSelectionModel().getSelectedItem();
    	
    	// set the button to be useable
    	closeComplaint.setDisable(false);
    	fullRefund.setDisable(false);
    	refundByAmountBtn.setDisable(false);
    	amount.setEditable(true);
    }

    /**
     * close complaint
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onCloseComplaint(ActionEvent event) throws Exception {
    	// close the complaint
    	ClientUI.chat.accept("CloseComplaint" + "\t" + getComplaintId);
    	
    	// if the task completed successfully
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Complaint closed");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Could not close complaint");
    	}
    	
    	// update the table
    	observableList.remove(selected);
    	updateTable();
    	
    	// set the buttons to be not useable
    	closeComplaint.setDisable(true);
    	fullRefund.setDisable(true);
    	refundByAmountBtn.setDisable(true);
    	amount.clear();
    	amount.setEditable(false);
    }

    /**
     * close complaint with full refund
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onFullRefund(ActionEvent event) throws Exception {
    	// close and refund the complaint
    	ClientUI.chat.accept("RefundForComplaintFullAmount" + "\t" + getComplaintId + "\t" + getUserId + "\t" + getOrderNumber + "\t" + "Sorry for your inconvenience");
    	
    	// if the task completed successfully
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Order refunded");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Could not refund order");
    	}
    	
    	// update the table
    	observableList.remove(selected);
    	updateTable();
    	
    	// set the buttons to be not useable
    	closeComplaint.setDisable(true);
    	fullRefund.setDisable(true);
    	refundByAmountBtn.setDisable(true);
    	amount.clear();
    	amount.setEditable(false);
    }

    /**
     * close complaint with a refund
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onRefundByAmount(ActionEvent event) throws Exception {
    	// if the amount to refund is empty
    	if (amount.getText().equals("")) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please enter amount to refund");
    		return;
    	}
    	
    	// if the amount is not a number
    	try {
    		Float.parseFloat(amount.getText());
    	} catch (Exception e) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please enter numbers");
    		return;
    	}
    	
    	// close and refund the complaint
    	messageLabel.setText("");
    	ClientUI.chat.accept("RefundForComplaintNotFull" + "\t" + getComplaintId + "\t" + getUserId + "\t" + getOrderNumber + "\t" + amount.getText() + "\t" + "Sorry for your inconvenience");
    	
    	// if the task completed successfully
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Order refunded");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Could not refund order");
    	}
    	
    	// update the table
    	observableList.remove(selected);
    	updateTable();
    	
    	// set the buttons to be not useable
    	closeComplaint.setDisable(true);
    	fullRefund.setDisable(true);
    	refundByAmountBtn.setDisable(true);
    	amount.clear();
    	amount.setEditable(false);
    }
    
    /**
     * initialize the screen
     */
    public void initialize() {
    	// set the user name
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	
    	// get the observable list
    	observableList = FXCollections.observableArrayList(ChatClient.allComplaints);
    	
    	// update table values
    	updateTable();
    }
    
    /**
     * update the table values
     */
    private void updateTable() {
    	complaints.getItems().clear();
    	complaintId.setCellValueFactory(new PropertyValueFactory<>("complaintId"));
    	userId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    	orderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	complaintDescription.setCellValueFactory(new PropertyValueFactory<>("complaintDetails"));
    	complaints.setItems(observableList);
    }

}
