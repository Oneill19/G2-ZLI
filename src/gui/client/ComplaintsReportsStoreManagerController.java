package gui.client;

import client.ChatClient;
import client.ClientUI;
import entity.ComplaintReport;
import entity.StoreWorker;
import entity.Survey;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComplaintsReportsStoreManagerController {

    @FXML
    private BarChart<String, Integer> complaintBarChart;

    @FXML
    private Label errorLabel;

    @FXML
    private Button exitBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button onBack;

    @FXML
    private ComboBox<Integer> quarterComboBox;

    @FXML
    private Button show;

    @FXML
    private Button userOptBtn;

    @FXML
    private ComboBox<Integer> yearComboBox;
    
    private CommonController cc = new CommonController();
    
    private ComplaintReport cp = null;

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
		cc.changeFXML(event, "StoreManagerScreen.fxml", "Zer-Li Customer Store Manager Screen");
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

    @FXML
    void onShowComplaints(ActionEvent event) throws Exception {
    	if (quarterComboBox.getValue() == null || yearComboBox.getValue() == null) {
    		return;
    	}
    	ClientUI.chat.accept("GetComplaintReportByStore" + "\t" + yearComboBox.getValue() + "\t" + quarterComboBox.getValue() + "\t" + ((StoreWorker)ChatClient.user).getStoreName());
    	cp = ChatClient.selectedComplaintReport;
    	if (cp == null) {
    		return;
    	}
    	
    	// first init
    	complaintBarChart.setAnimated(false);
    	
    	setChart();
    }
    
    private void setChart() {
    	XYChart.Series<String, Integer> complaintsSeries = new Series<String, Integer>();
    	XYChart.Series<String, Integer> orderSeries = new Series<String, Integer>();
    	if (quarterComboBox.getValue() == 1) {
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("January ", cp.getNumberOfComplaints()[0]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("February", cp.getNumberOfComplaints()[1]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("March", cp.getNumberOfComplaints()[2]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("January", cp.getNumberOfComplaints()[0]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("February", cp.getNumberOfComplaints()[1]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("March", cp.getNumberOfComplaints()[2]));
    	}
    	else if (quarterComboBox.getValue() == 2) {
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("April ", cp.getNumberOfComplaints()[0]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("May", cp.getNumberOfComplaints()[1]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("June", cp.getNumberOfComplaints()[2]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("April", cp.getNumberOfComplaints()[0]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("May", cp.getNumberOfComplaints()[1]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("June", cp.getNumberOfComplaints()[2]));
    	}
    	else if (quarterComboBox.getValue() == 3) {
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("July ", cp.getNumberOfComplaints()[0]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("August", cp.getNumberOfComplaints()[1]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("September", cp.getNumberOfComplaints()[2]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("July", cp.getNumberOfComplaints()[0]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("August", cp.getNumberOfComplaints()[1]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("September", cp.getNumberOfComplaints()[2]));
    	}
    	else {
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("October ", cp.getNumberOfComplaints()[0]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("November", cp.getNumberOfComplaints()[1]));
    		complaintsSeries.getData().add(new XYChart.Data<String, Integer>("December", cp.getNumberOfComplaints()[2]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("October", cp.getNumberOfComplaints()[0]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("November", cp.getNumberOfComplaints()[1]));
    		orderSeries.getData().add(new XYChart.Data<String, Integer>("December", cp.getNumberOfComplaints()[2]));
    	}
    	
    	complaintsSeries.setName("Complaints");
    	orderSeries.setName("Orders");
    	
    	complaintBarChart.getData().clear();
    	complaintBarChart.getData().add(complaintsSeries);
    	complaintBarChart.getData().add(orderSeries);
    }
    
    /**
	 * initialize the screen
	 */
	public void initialize() {
		// set name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		
		quarterComboBox.getItems().clear();
		quarterComboBox.getItems().add(1);
		quarterComboBox.getItems().add(2);
		quarterComboBox.getItems().add(3);
		quarterComboBox.getItems().add(4);
		
		yearComboBox.getItems().clear();
		yearComboBox.getItems().add(2021);
		yearComboBox.getItems().add(2022);
	}

}
