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
    
    @FXML
    private Button addComplaint;
    
    @FXML
    private Button watchComplaints;
    
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
    	cc.changeFXML(event, "AddSurveyAnswer.fxml", "Zer-Li Add Survey Answer", null);
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
     * go to show survey reports screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onShowReports(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("GetSurveysWithReports");
    	cc.changeFXML(event, "SurveyReports.fxml", "Zer-Li Survey Reports", null);
    }
    
    /**
     * go to add complaint screen 
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void onAddComplaint(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "AddComplaint.fxml", "Zer-Li Add Complaint", null);
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
    	cc.changeFXML(event, "ShowComplaints.fxml", "Zer-Li Complaints", null);
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
