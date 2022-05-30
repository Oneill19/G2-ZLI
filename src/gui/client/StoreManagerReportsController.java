package gui.client;


import java.io.IOException;
import java.time.Year;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entity.StoreWorker;

public class StoreManagerReportsController {

	@FXML
	private Button User;

	@FXML
	private Button LogOut;

	@FXML
	private Button Back;

	@FXML
	private Button Exit;

	@FXML
	private ComboBox<String> YearC;

	@FXML
	private ComboBox<String> Month;

	@FXML
	private ComboBox<String> ReportType;

	@FXML
	private Button ViewReport;

	@FXML
	private TextArea reportText;

	@FXML
	private Button User1;

	@FXML
	private Text errortxt;

	@FXML
	private Button reportName;

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onBack(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("StoreManagerScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client->Options Screen");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onExit(ActionEvent event) throws Exception {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onLogOut(ActionEvent event) throws Exception {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("LoginScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	void onUser(ActionEvent event) throws Exception {
		System.out.println("User");
	}

	@FXML
	void onViewReport(ActionEvent event) throws Exception {
		String reportNme;
		String reportMonth;
		String reportYear;

		if (Month.getValue()==null) {
			errortxt.setText("Please choose Month!");
			return;
		}
		if (YearC.getValue()==null) {
			errortxt.setText("Please choose Year!");
			return;
		}
		if (ReportType.getValue()==null) {
			errortxt.setText("Please choose Report Type!");
			return;
		}
		errortxt.setText("");
		reportNme = ReportType.getValue();

		reportName.setText(reportNme + " Report");
		reportMonth = Month.getValue();
		reportYear = YearC.getValue();
		ClientUI.chat.accept("getReport" + "\t" + reportNme + "\t" + reportMonth + "\t" + reportYear + "\t"
				+ ((StoreWorker) ChatClient.user).getStoreName());
		reportText.setEditable(true);
		if(ChatClient.reportTxt==null) {
			errortxt.setText("No Report Found!");
			reportText.setText("");
		}
		else {
	
		reportText.setText(ChatClient.reportTxt);
		}
		reportText.setEditable(false);
		
	}

	@FXML
	void initialize() throws IOException {
		User.setText("Hello, " + ChatClient.user.getFirstName());
		Year y = Year.now();
		for (int i = y.getValue(); i > 2000; i--)
			YearC.getItems().add("" + i);
		for (int i = 1; i < 13; i++) {
			if (i < 10)
				Month.getItems().add("0" + i);
			else
				Month.getItems().add("" + i);
		}
		ReportType.getItems().add("Revenue");
		ReportType.getItems().add("Orders");
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				Month.getItems().add("0" + i);
			} else {
				Month.getItems().add("" + i);
			}
		}
	}

}
