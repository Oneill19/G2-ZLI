package gui.client;

import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StoreWorkerApprovedController {

    @FXML
    private Button Exit;

    @FXML
    private Button LogOut;

    @FXML
    private Button User;

    @FXML
    private Button addSurveyAnswersButton;
    
    private CommonController cc = new CommonController();

    /**
     * go to add a survey answer screen
     * 
     * @param event
     * @throws Exception 
     */
    @FXML
    void onAddSurveyAnswers(ActionEvent event) throws Exception{
    	ClientUI.chat.accept("GetAllSurveys");
    	cc.changeFXML(event, "AddSurveyAnswer.fxml", "Zer-Li Add Survey Answer");
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
    void onLogOut(ActionEvent event) throws Exception {
    	cc.onLogout(event);
    }

    @FXML
    void onUser(ActionEvent event) throws IOException {
    	cc.changeFXML(event, "StoreWorkerApproved", "Zer-Li Options");
    }
    
    /**
	 * initialize the screen
	 */
	public void initialize() {
		// set the name on the user name
		User.setText("Hello, " + ChatClient.user.getFirstName());
	}

}
