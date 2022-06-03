package gui.client;

import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import entity.Survey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SurveyReportController {

	@FXML
	private Label errorLabel;

	@FXML
	private Button exitBtn;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button nextQ;

	@FXML
	private Button onBack;

	@FXML
	private Button pdfButton;

	@FXML
	private Button prevQ;

	@FXML
	private ComboBox<String> reportComboBox;

	@FXML
	private Button show;

	@FXML
	private BarChart<String, Number> surveyBarChart;

	@FXML
	private Button userOptBtn;

	private CommonController cc = new CommonController();

	private int questionIndex = 0;

	private Survey survey;

	private int[][] answers;

	/**
	 * go to the previous screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		// clear the surveys list
		ChatClient.surveysWithReports.clear();
		ChatClient.selectedSurveyReport = null;
		
		// go to the previous screen
		cc.changeFXML(event, "CustomerServiceWorkerScreen.fxml", "Zer-Li Customer Service Worker Screen");
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
	 * import pdf file from db - still not implemented
	 * 
	 * @param event
	 */
	@FXML
	void onImport(ActionEvent event) {
		System.out.println("Import PDF File");
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
	 * change chart to next question chart
	 * 
	 * @param event
	 */
	@FXML
	void onNextQuestion(ActionEvent event) {
		// set index
		questionIndex = (questionIndex + 1) > 5 ? 0 : (questionIndex + 1);
		
		// update chart
		setChart();
	}

	/**
	 * change chart to previous question chart
	 * 
	 * @param event
	 */
	@FXML
	void onPrevQuestion(ActionEvent event) {
		// set index
		questionIndex = (questionIndex - 1) < 0 ? 5 : (questionIndex - 1);
		
		// update chart
		setChart();
	}

	/**
	 * get survey report data and show on chart
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void onShowSurvery(ActionEvent event) throws IOException {
		// check if a value is selected
		if (reportComboBox.getValue() == null) {
			errorLabel.setText("Please choose and option!");
			return;
		}
		
		// get the survey id
		int surveyId = 0;
		for (Survey survey : ChatClient.surveysWithReports) {
			if (reportComboBox.getValue().equals(survey.getSurveyName())) {
				surveyId = survey.getSurveyId();
			}
		}
		
		// enable the next and prev buttons
		errorLabel.setText("");
		nextQ.setDisable(false);
		prevQ.setDisable(false);
		
		// first init
		surveyBarChart.setAnimated(false);
		
		// send request to get surveys
		ClientUI.chat.accept("GetSurveyReport" + "\t" + surveyId);
		survey = ChatClient.selectedSurveyReport.getSurvey();
		answers = ChatClient.selectedSurveyReport.getAnswers();
		
		// set survey chart
		setChart();
	}

	private void setChart() {
		// init data
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		
		// add data to dataSeries
		for (int i = 0; i < 10; i++) {
			dataSeries.getData().add(new XYChart.Data<String, Number>("Answer " + (i + 1), answers[questionIndex][i]));
		}
		dataSeries.setName("Users voted");
		
		// set title of the chart
		surveyBarChart.setTitle(survey.getQuestions()[questionIndex]);
		
		// add data to chart
		surveyBarChart.getData().clear();
		surveyBarChart.getData().add(dataSeries);
	}

	/**
	 * initialize the screen
	 */
	public void initialize() {
		// set name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		
		// disable buttons
		nextQ.setDisable(true);
		prevQ.setDisable(true);
		pdfButton.setDisable(true);
		
		// init combobox
		reportComboBox.getSelectionModel().clearSelection();
		reportComboBox.getItems().clear();
		for (Survey survey : ChatClient.surveysWithReports) {
			reportComboBox.getItems().add(survey.getSurveyName());
		}
	}

}
