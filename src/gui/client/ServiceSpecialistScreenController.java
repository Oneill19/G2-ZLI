package gui.client;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ServiceSpecialistScreenController {

    @FXML
    private Button Exit;

    @FXML
    private Button LogOut;

    @FXML
    private Button User;

    @FXML
    private Button addSurveyReportButton;
    
    private CommonController cc = new CommonController();

    /**
     * move to the next screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onAddSurveyReport(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "AddSurveyPDF.fxml", "Zer-Li Add Add Survey PDF");
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
    
    /**
     * initialize the screen
     * 
     * @throws Exception
     */
    public void initialize() throws Exception {
    	User.setText("Hello, " + ChatClient.user.getFirstName());
    }
}
