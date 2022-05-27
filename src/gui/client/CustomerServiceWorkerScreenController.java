package gui.client;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerServiceWorkerScreenController {

    @FXML
    private Button Exit;

    @FXML
    private Button LogOut;

    @FXML
    private Button User;

    @FXML
    private Button addSurveyAnswersButton;

    @FXML
    private Button showReports;
    
    private CommonController cc = new CommonController();

    /**
     * @param event
     */
    @FXML
    void onAddSurveyAnswers(ActionEvent event) {
    	System.out.println("Not implements yet");
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
    void onLogOut(ActionEvent event) throws Exception {
    	cc.onLogout(event);
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onShowReports(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("GetSurveysWithReports");
    	cc.changeFXML(event, "SurveyReports.fxml", "Zer-Li Survey Reports", null);
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
     */
    public void initialize() {
    	User.setText("Hello, " + ChatClient.user.getFirstName());
    }

}
