package gui.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddComplaintController {
	
	@FXML
    private Button backButton;

    @FXML
    private TextArea complaintDescriptionTxt;

    @FXML
    private TextField customerId;

    @FXML
    private Button exitBtn;

    @FXML
    private Label label;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField orderNumber;

    @FXML
    private Button saveButton;

    @FXML
    private Button userOptBtn;
	
	private CommonController cc = new CommonController();
	
    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onBack(ActionEvent event) throws Exception {
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
    	cc.onLogout(event);
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onSave(ActionEvent event) throws Exception {
    	if (customerId.getText().equals("") || orderNumber.getText().equals("") || complaintDescriptionTxt.getText().equals("")) {
    		label.setTextFill(Color.RED);
    		label.setText("Please fill all fields");
    		return;
    	}
    	ClientUI.chat.accept("OrderExist" + "\t" + orderNumber.getText() + "\t" + customerId.getText());
    	if (!ChatClient.requestSucceed) {
    		label.setTextFill(Color.RED);
    		label.setText("Order not exist");
    		return;
    	}
    	label.setText("");
    	ClientUI.chat.accept("AddComplaint" + "\t" 
    			+ orderNumber.getText() + ","
    			+ customerId.getText() + "," 
    			+ ChatClient.user.getUserID() + "," 
    			+ "'" + complaintDescriptionTxt.getText() + "',"
    			+ "'" + LocalDate.now() + "',"
    			+ "'" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "',"
    			+ "'OPEN'" + ","
    			+ "0");
    	if (ChatClient.requestSucceed) {
    		label.setTextFill(Color.GREEN);
    		label.setText("Complaint Submitted");
    	}
    	else {
    		label.setTextFill(Color.RED);
    		label.setText("Could not submit complaint");
    	}
    }
    
    /**
	 * initialize the screen
	 */
	public void initialize() {
		// set the name on the user name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
	}
}
