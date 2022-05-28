package gui.client;

import client.ChatClient;
import client.ClientUI;
import entity.Survey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddSurveyAnswerController {

    @FXML
    private ComboBox<Number> answer1;

    @FXML
    private ComboBox<Number> answer2;

    @FXML
    private ComboBox<Number> answer3;

    @FXML
    private ComboBox<Number> answer4;

    @FXML
    private ComboBox<Number> answer5;

    @FXML
    private ComboBox<Number> answer6;

    @FXML
    private Button backButton;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> surveyName;

    @FXML
    private TextField userMail;

    @FXML
    private Button userOptBtn;
    
    @FXML
    private Label messageLabel;
    
    private CommonController cc = new CommonController();

    /**
     * go to the previous screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onBack(ActionEvent event) throws Exception {
    	// clear surveys list
    	ChatClient.allSurveys.clear();
		ChatClient.allSurveys = null;
		
		// go to the previous screen
		cc.changeFXML(event, "CustomerServiceWorkerScreen.fxml", "Zer-Li Customer Service Worker Screen", null);
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
    void onLogout(ActionEvent event) throws Exception {
    	// clear the survey list
    	ChatClient.surveysWithReports.clear();
		ChatClient.selectedSurveyReport = null;
		
		// logout and go to the login page
		cc.onLogout(event);
    }

    /**
     * save the new answer to the db
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onSave(ActionEvent event) throws Exception {
    	// check if all the fields are filled
    	if (userMail.getText().equals("") || surveyName.getValue() == null || answer1.getValue() == null || answer2.getValue() == null || answer3.getValue() == null || answer4.getValue() == null || answer5.getValue() == null || answer6.getValue() == null) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please fill al fields");
    		return;
    	}
    	
    	// get the survey id
    	int surveyId = 0;
    	for (Survey survey: ChatClient.allSurveys) {
    		if (surveyName.getValue().equals(survey.getSurveyName())) {
    			surveyId = survey.getSurveyId();
    			break;
    		}
    	}
    	
    	// send the request
    	ClientUI.chat.accept("AddSurveyAnswer" + "\t" + surveyId + "\t" + userMail.getText() + "\t" + answer1.getValue() + "\t" + answer2.getValue() + "\t" + answer3.getValue() + "\t" + answer4.getValue() + "\t" + answer5.getValue() + "\t" + answer6.getValue());
    	
    	// if succeed
    	if (ChatClient.requestSucceed) {
    		messageLabel.setTextFill(Color.GREEN);
    		messageLabel.setText("Answer Submitted!");
    	}
    	else {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Answer Did not Submitted!");
    	}
    }
    
    /**
	 * initialize the screen
	 */
	public void initialize() {
		// set the name on the user name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		
		// clear and add the name of surveys to the combobox
		surveyName.getItems().clear();
		for (Survey survey : ChatClient.allSurveys) {
			surveyName.getItems().add(survey.getSurveyName());
		}
		
		
		
		// clear and add the answers to the combobox
		answer1.getItems().clear();
		answer2.getItems().clear();
		answer3.getItems().clear();
		answer4.getItems().clear();
		answer5.getItems().clear();
		answer6.getItems().clear();
		
		for (int i = 1; i <= 10; i++) {
			answer1.getItems().add(i);
			answer2.getItems().add(i);
			answer3.getItems().add(i);
			answer4.getItems().add(i);
			answer5.getItems().add(i);
			answer6.getItems().add(i);
		}
		
	}
}
