package gui.client;

import client.ChatClient;
import client.ClientUI;
import common.ButtonEventHandlerStyle;
import entity.ComplaintReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComplaintsReportsCEOController {

	@FXML
	private BarChart<String, Integer> complaintBarChart;

	@FXML
	private ComboBox<String> storeComboBox;

	@FXML
	private ComboBox<Integer> yearComboBox;

	@FXML
	private ComboBox<Integer> quarterComboBox;

	@FXML
	private Label errorLabel;

	@FXML
	private Button exitBtn;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button onBack;

	@FXML
	private Button show;

	@FXML
	private Button userOptBtn;

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
		cc.changeFXML(event, "CEOScreen.fxml", "Zer-Li Customer Network Manager");
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
		// clear the complaint list
		ChatClient.selectedComplaintReport = null;

		// logout and go to the login page
		cc.onLogout(event);
	}

	/**
	 * show the complaints and orders report values on the histogram
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onShowComplaints(ActionEvent event) throws Exception {
		// if not all fields are filled
		if (quarterComboBox.getValue() == null || yearComboBox.getValue() == null || storeComboBox.getValue() == null) {
			errorLabel.setText("Please fill all fields");
			return;
		}
		errorLabel.setText("");

		// get the complaint report by year, quarter and the store
		ClientUI.chat.accept("GetComplaintReportByStore" + "\t" + yearComboBox.getValue() + "\t"
				+ quarterComboBox.getValue() + "\t" + storeComboBox.getValue());
		cp = ChatClient.selectedComplaintReport;
		if (cp == null) {
			errorLabel.setText("Error getting the data");
			return;
		}

		// first init
		complaintBarChart.setAnimated(false);

		// set the histogram title
		complaintBarChart.setTitle(
				storeComboBox.getValue() + " " + yearComboBox.getValue() + " Quarter " + quarterComboBox.getValue());

		// set the chart
		setChart();
	}

	/**
	 * sets the histogram values
	 */
	private void setChart() {
		// create series for complaints and orders
		XYChart.Series<String, Integer> complaintsSeries = new Series<String, Integer>();
		XYChart.Series<String, Integer> orderSeries = new Series<String, Integer>();

		// if quarter one
		if (quarterComboBox.getValue() == 1) {

			// set the data for the complaints
			complaintsSeries.getData()
					.add(new XYChart.Data<String, Integer>("January ", cp.getNumberOfComplaints()[0]));
			complaintsSeries.getData()
					.add(new XYChart.Data<String, Integer>("February", cp.getNumberOfComplaints()[1]));
			complaintsSeries.getData().add(new XYChart.Data<String, Integer>("March", cp.getNumberOfComplaints()[2]));

			// set the date for the orders
			orderSeries.getData().add(new XYChart.Data<String, Integer>("January", cp.getNumberOfOrders()[0]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("February", cp.getNumberOfOrders()[1]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("March", cp.getNumberOfOrders()[2]));
		}

		// if quarter two
		else if (quarterComboBox.getValue() == 2) {

			// set the data for the complaints
			complaintsSeries.getData().add(new XYChart.Data<String, Integer>("April ", cp.getNumberOfComplaints()[0]));
			complaintsSeries.getData().add(new XYChart.Data<String, Integer>("May", cp.getNumberOfComplaints()[1]));
			complaintsSeries.getData().add(new XYChart.Data<String, Integer>("June", cp.getNumberOfComplaints()[2]));

			// set the date for the orders
			orderSeries.getData().add(new XYChart.Data<String, Integer>("April", cp.getNumberOfOrders()[0]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("May", cp.getNumberOfOrders()[1]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("June", cp.getNumberOfOrders()[2]));
		}

		// if quarter three
		else if (quarterComboBox.getValue() == 3) {

			// set the data for the complaints
			complaintsSeries.getData().add(new XYChart.Data<String, Integer>("July ", cp.getNumberOfComplaints()[0]));
			complaintsSeries.getData().add(new XYChart.Data<String, Integer>("August", cp.getNumberOfComplaints()[1]));
			complaintsSeries.getData()
					.add(new XYChart.Data<String, Integer>("September", cp.getNumberOfComplaints()[2]));

			// set the date for the orders
			orderSeries.getData().add(new XYChart.Data<String, Integer>("July", cp.getNumberOfOrders()[0]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("August", cp.getNumberOfOrders()[1]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("September", cp.getNumberOfOrders()[2]));
		}

		// if quarter four
		else {

			// set the data for the complaints
			complaintsSeries.getData()
					.add(new XYChart.Data<String, Integer>("October ", cp.getNumberOfComplaints()[0]));
			complaintsSeries.getData()
					.add(new XYChart.Data<String, Integer>("November", cp.getNumberOfComplaints()[1]));
			complaintsSeries.getData()
					.add(new XYChart.Data<String, Integer>("December", cp.getNumberOfComplaints()[2]));

			// set the date for the orders
			orderSeries.getData().add(new XYChart.Data<String, Integer>("October", cp.getNumberOfOrders()[0]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("November", cp.getNumberOfOrders()[1]));
			orderSeries.getData().add(new XYChart.Data<String, Integer>("December", cp.getNumberOfOrders()[2]));
		}

		// set the bars names
		complaintsSeries.setName("Complaints");
		orderSeries.setName("Orders");

		// add the data to the chart
		complaintBarChart.getData().clear();
		complaintBarChart.getData().add(complaintsSeries);
		complaintBarChart.getData().add(orderSeries);
	}

	/**
	 * initialize the screen
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		// set name
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());

		//// set the combo boxes values
		ClientUI.chat.accept("GetAllStores");

		storeComboBox.getItems().clear();
		for (String store : ChatClient.stores) {
			storeComboBox.getItems().add(store);
		}

		quarterComboBox.getItems().clear();
		quarterComboBox.getItems().add(1);
		quarterComboBox.getItems().add(2);
		quarterComboBox.getItems().add(3);
		quarterComboBox.getItems().add(4);

		yearComboBox.getItems().clear();
		yearComboBox.getItems().add(2021);
		yearComboBox.getItems().add(2022);

//		// init buttons style
		onBack.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(onBack));
		onBack.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(onBack));

		exitBtn.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnEnter(exitBtn));
		exitBtn.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(exitBtn));
		
		show.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(show));
		show.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnExit(show));
	}

}
