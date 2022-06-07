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
import javafx.scene.text.Text;

public class AddSurveyAnswerController {

	@FXML
	private Text question1;

	@FXML
	private Text question2;

	@FXML
	private Text question3;

	@FXML
	private Text question4;

	@FXML
	private Text question5;

	@FXML
	private Text question6;

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

		// go to the previous screen
		cc.changeFXML(event, "StoreWorkerApprovedScreen.fxml", "Zer-Li Customer Service Worker Screen");
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
		ChatClient.allSurveys.clear();

		// logout and go to the login page
		cc.onLogout(event);
	}

	/**
	 * load the questions of the survey
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onChooseSurvey(ActionEvent event) throws Exception {
		Survey survey = null;
		;

		// check if a survey is selected
		if (surveyName.getValue() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please Choose a survey");
			return;
		}

		// get the selected survey
		for (Survey s : ChatClient.allSurveys) {
			if (s.getSurveyName().equals(surveyName.getValue())) {
				survey = s;
				break;
			}
		}

		if (survey == null) {
			return;
		}

		// make the user to be able to choose answers
		userMail.setDisable(false);
		answer1.setDisable(false);
		answer2.setDisable(false);
		answer3.setDisable(false);
		answer4.setDisable(false);
		answer5.setDisable(false);
		answer6.setDisable(false);

		// initialize the questions
		question1.setText(survey.getQuestions()[0]);
		question2.setText(survey.getQuestions()[1]);
		question3.setText(survey.getQuestions()[2]);
		question4.setText(survey.getQuestions()[3]);
		question5.setText(survey.getQuestions()[4]);
		question6.setText(survey.getQuestions()[5]);
	}

	/**
	 * save the new answer to the database
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onSave(ActionEvent event) throws Exception {
		// check if all the fields are filled
		if (userMail.getText().equals("") || surveyName.getValue() == null || answer1.getValue() == null
				|| answer2.getValue() == null || answer3.getValue() == null || answer4.getValue() == null
				|| answer5.getValue() == null || answer6.getValue() == null) {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Please fill al fields");
			return;
		}

		// get the survey id
		int surveyId = 0;
		for (Survey survey : ChatClient.allSurveys) {
			if (surveyName.getValue().equals(survey.getSurveyName())) {
				surveyId = survey.getSurveyId();
				break;
			}
		}

		// send the request
		ClientUI.chat.accept("AddSurveyAnswer" + "\t" + surveyId + "\t" + userMail.getText() + "\t" + answer1.getValue()
				+ "\t" + answer2.getValue() + "\t" + answer3.getValue() + "\t" + answer4.getValue() + "\t"
				+ answer5.getValue() + "\t" + answer6.getValue());

		// if succeed
		if (ChatClient.requestSucceed) {
			messageLabel.setTextFill(Color.GREEN);
			messageLabel.setText("Answer Submitted!");
		} else {
			messageLabel.setTextFill(Color.RED);
			messageLabel.setText("Answer Did not Submitted!");
		}

		// make the user to be able to choose answers
		userMail.setDisable(true);
		userMail.setText("");
		answer1.setDisable(true);
		answer2.setDisable(true);
		answer3.setDisable(true);
		answer4.setDisable(true);
		answer5.setDisable(true);
		answer6.setDisable(true);

		// initialize the questions
		question1.setText("");
		question2.setText("");
		question3.setText("");
		question4.setText("");
		question5.setText("");
		question6.setText("");
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

//		// init buttons style
		backButton.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(backButton));
		backButton.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(backButton));
		
		exitBtn.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnEnter(exitBtn));
		exitBtn.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(exitBtn));
		
		saveButton.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(saveButton));
		saveButton.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnExit(saveButton));
	}
}
