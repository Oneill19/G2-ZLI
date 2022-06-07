package gui.client;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeliveryScreenController {

    @FXML
    private Button Exit;

    @FXML
    private Button LogOut;

    @FXML
    private Button User;

    @FXML
    private Button deliveryButton;
    
    private CommonController cc = new CommonController();

    /**
     * move to the delivery management screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onDeliveryManagement(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "ManageDelivery.fxml", "Zer-Li Delivery Management");
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
	
	/**
	 * initialize the screen
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		// set the user name
		User.setText("Hello, " + ChatClient.user.getFirstName());
	}

}
