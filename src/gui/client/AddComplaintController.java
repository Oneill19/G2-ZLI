package gui.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import client.ChatClient;
import client.ClientUI;
import common.ButtonEventHandlerStyle;
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
     * go back to the options screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onBack(ActionEvent event) throws Exception {
    	// go to the previous screen
    	cc.changeFXML(event, "CustomerServiceWorkerScreen.fxml", "Zer-Li Customer Service Worker Screen");
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
     * log out from the user
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onLogout(ActionEvent event) throws Exception {
    	cc.onLogout(event);
    }

    /**
     * save the complaint in the database
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onSave(ActionEvent event) throws Exception {
    	// if some of the fields are empty
    	if (customerId.getText().equals("") || orderNumber.getText().equals("") || complaintDescriptionTxt.getText().equals("")) {
    		label.setTextFill(Color.RED);
    		label.setText("Please fill all fields");
    		return;
    	}
    	
    	// if the order and customer id are not match
    	ClientUI.chat.accept("OrderExist" + "\t" + orderNumber.getText() + "\t" + customerId.getText());
    	if (!ChatClient.requestSucceed) {
    		label.setTextFill(Color.RED);
    		label.setText("Order not exist");
    		return;
    	}
    	// add the complaint
    	label.setText("");
    	ClientUI.chat.accept("AddComplaint" + "\t" 
    			+ orderNumber.getText() + "\t"
    			+ customerId.getText() + "\t" 
    			+ ChatClient.user.getUserID() + "\t"
    			+ complaintDescriptionTxt.getText() + "\t"
    			+ LocalDate.now() + "\t"
    			+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "\t"
    			+ "OPEN" + "\t"
    			+ "0");
    	
    	// if the adding was a success show a successful message
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
		
		
//		//init buttons style
//		backButton.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(backButton));
//		backButton.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(backButton));
//		
//		exitBtn.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnExit(exitBtn));
//		exitBtn.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(exitBtn));
//		
//		saveButton.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(saveButton));
//		saveButton.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnEnter(saveButton));
	}
}
