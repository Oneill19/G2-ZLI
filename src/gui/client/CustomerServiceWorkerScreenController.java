package gui.client;

import java.time.LocalDate;
import java.time.LocalTime;

import client.ChatClient;
import client.ClientUI;
import entity.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class CustomerServiceWorkerScreenController {

    @FXML
    private Button Exit;

    @FXML
    private Button LogOut;

    @FXML
    private Button User;
    
    @FXML
    private Button addComplaint;
    
    @FXML
    private Button watchComplaints;
    
    private CommonController cc = new CommonController();

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
    void onLogOut(ActionEvent event) throws Exception {
    	cc.onLogout(event);
    }
    
    /**
     * go to add complaint screen 
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void onAddComplaint(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "AddComplaint.fxml", "Zer-Li Add Complaint");
    }
    
    /**
     * go to watch complaints screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onWatchComplaints(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("GetAllOpenComplaintsOfWorker" + "\t" + ChatClient.user.getUserID());
    	cc.changeFXML(event, "ShowComplaints.fxml", "Zer-Li Complaints");
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onUser(ActionEvent event) throws Exception {

    }
    
    /**
     * initialize the screen
     * 
     * @throws Exception
     */
    public void initialize() throws Exception {
    	User.setText("Hello, " + ChatClient.user.getFirstName());
    	
    	ChatClient.allComplaints.clear();
    	ClientUI.chat.accept("GetAllOpenComplaintsOfWorker" + "\t" + ChatClient.user.getUserID());
    	
    	LocalDate nowDate = LocalDate.now();
    	LocalTime nowTime = LocalTime.now();
    	
    	if (ChatClient.allComplaints.size() <= 0) {
    		return;
    	}
    	
    	String waitingComplaints = "";
    	
    	for (Complaint complaint : ChatClient.allComplaints) {
    		if (nowDate.compareTo(complaint.getReciveDate()) > 0) {
    			if (nowTime.compareTo(complaint.getReciveTime()) > 0) {
    				if (complaint.isReminded()) {
    					continue;
    				}
    				waitingComplaints += complaint.getComplaintId() + ", ";
    				ClientUI.chat.accept("Reminded" + "\t" + complaint.getComplaintId());
    				if (!ChatClient.requestSucceed) {
    					return;
    				}
    			}
    		}
    	}
    	
    	if (waitingComplaints.equals("")) {
    		return;
    	}
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Unresolved Complaints");
		alert.setHeaderText("Please watch on complaint/s " + waitingComplaints + " There been more than 24 hours.");
		alert.showAndWait();
    }

}
