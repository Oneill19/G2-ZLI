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
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		ChatClient.surveysWithReports.clear();
		ChatClient.selectedSurveyReport = null;
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
	 */
	@FXML
	void onImport(ActionEvent event) {
		System.out.println("Import PDF File");
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onLogout(ActionEvent event) throws Exception {
		ChatClient.surveysWithReports.clear();
		ChatClient.selectedSurveyReport = null;
		cc.onLogout(event);
	}

	/**
	 * @param event
	 */
	@FXML
	void onNextQuestion(ActionEvent event) {
		questionIndex = (questionIndex + 1) > 5 ? 0 : (questionIndex + 1);
		setChart();
	}

	/**
	 * @param event
	 */
	@FXML
	void onPrevQuestion(ActionEvent event) {
		questionIndex = (questionIndex - 1) < 0 ? 5 : (questionIndex - 1);
		setChart();
	}

	/**
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void onShowSurvery(ActionEvent event) throws IOException {
		if (reportComboBox.getValue() == null) {
			errorLabel.setText("Please choose and option!");
			return;
		}
		int surveyId = 0;
		for (Survey survey : ChatClient.surveysWithReports) {
			if (reportComboBox.getValue().equals(survey.getSurveyName())) {
				surveyId = survey.getSurveyId();
			}
		}
		errorLabel.setText("");
		nextQ.setDisable(false);
		prevQ.setDisable(false);
		surveyBarChart.getData().clear();
		surveyBarChart.setAnimated(false);
		ClientUI.chat.accept("GetSurveyReport" + "\t" + surveyId);
		survey = ChatClient.selectedSurveyReport.getSurvey();
		answers = ChatClient.selectedSurveyReport.getAnswers();
		initSurveyData();
	}

	private void initSurveyData() {
		setChart();
	}

	private void setChart() {
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		for (int i = 0; i < 10; i++) {
			dataSeries.getData().add(new XYChart.Data<String, Number>("Answer " + (i + 1), answers[questionIndex][i]));
		}
		dataSeries.setName("Users voted");
		surveyBarChart.setTitle(survey.getQuestions()[questionIndex]);
		surveyBarChart.getData().clear();
		surveyBarChart.getData().add(dataSeries);
	}

	/**
	 * initialize the screen
	 */
	public void initialize() {
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		nextQ.setDisable(true);
		prevQ.setDisable(true);
		pdfButton.setDisable(true);
		reportComboBox.getSelectionModel().clearSelection();
		reportComboBox.getItems().clear();
		for (Survey survey : ChatClient.surveysWithReports) {
			reportComboBox.getItems().add(survey.getSurveyName());
		}
	}

}
