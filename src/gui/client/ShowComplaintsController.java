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
     * @param event
     * @throws Exception
     */
    @FXML
    void onBack(ActionEvent event) throws Exception {
    	ChatClient.allComplaints.clear();
    	// go to the previous screen
    	cc.changeFXML(event, "CustomerServiceWorkerScreen.fxml", "Zer-Li Customer Service Worker Screen", null);
    }
    
    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onExit(ActionEvent event) throws Exception {
    	cc.OnExit();
    }
    
    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onLogout(ActionEvent event) throws Exception {
    	ChatClient.allComplaints.clear();
    	cc.onLogout(event);
    }
    
    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onHandleComplaint(ActionEvent event) throws Exception {
    	if (complaints.getSelectionModel() == null || complaints.getSelectionModel().getSelectedItem() == null) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please choose a complaint");
    		return;
    	}
    	messageLabel.setText("");
    	getComplaintId = complaints.getSelectionModel().getSelectedItem().getComplaintId();
    	getUserId = complaints.getSelectionModel().getSelectedItem().getCustomerId();
    	getOrderNumber = complaints.getSelectionModel().getSelectedItem().getOrderNumber();
    	selected = complaints.getSelectionModel().getSelectedItem();
    	closeComplaint.setDisable(false);
    	fullRefund.setDisable(false);
    	refundByAmountBtn.setDisable(false);
    	amount.setEditable(true);
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onCloseComplaint(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("CloseComplaint" + "\t" + getComplaintId);
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Complaint closed");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Could not close complaint");
    	}
    	observableList.remove(selected);
    	updateTable();
    	closeComplaint.setDisable(true);
    	fullRefund.setDisable(true);
    	refundByAmountBtn.setDisable(true);
    	amount.setText("");
    	amount.setEditable(false);
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onFullRefund(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("RefundForComplaintFullAmount" + "\t" + getComplaintId + "\t" + getUserId + "\t" + getOrderNumber + "\t" + "Sorry for your inconvenience");
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Order refunded");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Could not refund order");
    	}
    	observableList.remove(selected);
    	updateTable();
    	closeComplaint.setDisable(true);
    	fullRefund.setDisable(true);
    	refundByAmountBtn.setDisable(true);
    	amount.setText("");
    	amount.setEditable(false);
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onRefundByAmount(ActionEvent event) throws Exception {
    	if (amount.getText().equals("")) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please enter amount to refund");
    		return;
    	}
    	try {
    		Float.parseFloat(amount.getText());
    	} catch (Exception e) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please enter numbers");
    		return;
    	}
    	messageLabel.setText("");
    	ClientUI.chat.accept("RefundForComplaintNotFull" + "\t" + getComplaintId + "\t" + getUserId + "\t" + getOrderNumber + "\t" + amount.getText() + "\t" + "Sorry for your inconvenience");
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Order refunded");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Could not refund order");
    	}
    	observableList.remove(selected);
    	updateTable();
    	closeComplaint.setDisable(true);
    	fullRefund.setDisable(true);
    	refundByAmountBtn.setDisable(true);
    	amount.setText("");
    	amount.setEditable(false);
    }
    
    /**
     * initialize the screen
     */
    public void initialize() {
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	observableList = FXCollections.observableArrayList(ChatClient.allComplaints);
    	updateTable();
    }
    
    private void updateTable() {
    	complaints.getItems().clear();
    	complaintId.setCellValueFactory(new PropertyValueFactory<>("complaintId"));
    	userId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    	orderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	complaintDescription.setCellValueFactory(new PropertyValueFactory<>("complaintDetails"));
    	complaints.setItems(observableList);
    }

}
