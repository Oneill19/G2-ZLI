package gui.client;

import java.io.IOException;
import java.time.Year;

import client.ChatClient;
import client.ClientUI;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Topaz Eldori,Koral Biton
 *
 */
public class CEORevenueQuertlyReportController {
	ObservableList<String> Combolist;

	@FXML
	private Button User;

	@FXML
	private Button LogOut1;

	@FXML
	private Button Exit1;

	@FXML
	private Text errortxt;

	@FXML
	private ComboBox<String> YearC;

	@FXML
	private ComboBox<String> quarterly;

	@FXML
	private ComboBox<String> store;

	@FXML
	private ComboBox<String> YearC1;

	@FXML
	private ComboBox<String> Quarterly1;

	@FXML
	private ComboBox<String> store1;

	@FXML
	private Button ViewReport;

	@FXML
	private Button Back;

	@FXML
	private RadioButton select1;

	@FXML
	private RadioButton select2;

	@FXML
	private Text quartLable;

	@FXML
	private Text storeLable;

	@FXML
	private Text yearLable;

	/**
	 *Go back to the options screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void onBack(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("CEOScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client->Options Screen");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * exit from Zer-Li system 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void onExit(ActionEvent event) throws IOException {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

	/**
	 * Log out from the user and go back to login screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void onLogOut(ActionEvent event) throws IOException {
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

	/**
	 * Select how much report to view and select quarterly, the year of the quarterly and the store and 
	 * show the selected histogram revenue reports
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onViewReport(ActionEvent event) throws Exception {
		if (!select1.isSelected() && !select2.isSelected()) {
			errortxt.setText("Please choose How Much Reports To View!");
		} else if (!select2.isSelected()) // 1 report to view selected
		{
			if (YearC.getValue() == null || quarterly.getValue() == null || store.getValue() == null) {

				errortxt.setText("You Must Fill All The Fields!");
				return;
			}

			else {
				ClientUI.chat.accept("GetReportByQuarter1" + "\t" + quarterly.getValue() + "\t" + YearC.getValue() + "\t"
						+ store.getValue());
				if (ChatClient.reportsq1.size() == 0) {
					errortxt.setText("No reports found for that time period!");
					return;
				} else {
					((Node) event.getSource()).getScene().getWindow().hide();
					Stage primaryStage = new Stage();
					new FXMLLoader();
					Pane root = FXMLLoader.<Pane>load(getClass().getResource("OneQuarterlyReport.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setTitle("Zer-Li Client->Options Screen");
					primaryStage.setScene(scene);
					primaryStage.show();
				}

			}
		}

		else

		{
			if (YearC.getValue() == null || quarterly.getValue() == null || store.getValue() == null
					|| YearC1.getValue() == null || Quarterly1.getValue() == null || store1.getValue() == null) {

				errortxt.setText("You Must Fill All The Fields!");
				return;

			} else {
				ClientUI.chat.accept("GetReportByQuarter1" + "\t" + quarterly.getValue() + "\t" + YearC.getValue()
						+ "\t" + store.getValue());
				if (ChatClient.reportsq1.size() == 0) {
					errortxt.setText("No reports found for the First Choice!");
					return;
				}
				ClientUI.chat.accept("GetReportByQuarter2" + "\t" + Quarterly1.getValue() + "\t" + YearC1.getValue()
						+ "\t" + store1.getValue());
				if (ChatClient.reportsq2.size() == 0) {
					errortxt.setText("No reports found for the Second Choice!");
					return;
				}
				if(ChatClient.reportsq2.size()>0 &&ChatClient.reportsq1.size()>0 ) {
					((Node) event.getSource()).getScene().getWindow().hide();
					Stage primaryStage = new Stage();
					new FXMLLoader();
					Pane root = FXMLLoader.<Pane>load(getClass().getResource("TwoQuarterlyReport.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setTitle("Zer-Li Client->Options Screen");
					primaryStage.setScene(scene);
					primaryStage.show();
				}
			}

		}

	}

	/**
	 * If viewing one report is selected hide the details of the second option
	 * @param event
	 */
	@FXML
	void onSelect1(ActionEvent event) {
		select2.setSelected(false);
		YearC1.setDisable(true);
		Quarterly1.setDisable(true);
		store1.setDisable(true);
	}

	/**
	 * If viewing two reports is selected show the details of the two options
	 * @param event
	 */
	@FXML
	void onSelect2(ActionEvent event) {		
		select1.setSelected(false);
		YearC1.setDisable(false);
		Quarterly1.setDisable(false);
		store1.setDisable(false);
	}
	

	/**
	 * Initialize the screen
	 * @throws Exception
	 */
	@FXML
	 void initialize() throws Exception {
		User.setText("Hello, " + ChatClient.user.getFirstName());
		Year y = Year.now();
		for (int i = y.getValue(); i > 2000; i--) {
			YearC.getItems().add("" + i);
			YearC1.getItems().add("" + i);
		}
		quarterly.getItems().add("1");
		quarterly.getItems().add("2");
		quarterly.getItems().add("3");
		quarterly.getItems().add("4");
		Quarterly1.getItems().add("1");
		Quarterly1.getItems().add("2");
		Quarterly1.getItems().add("3");
		Quarterly1.getItems().add("4");
		ClientUI.chat.accept("GetAllStores");
		// init combobox
		store.getSelectionModel().clearSelection();
		store.getItems().clear();
		store1.getSelectionModel().clearSelection();
		store1.getItems().clear();
		for (String storess : ChatClient.stores) {
			store1.getItems().add(storess);
			store.getItems().add(storess);

			
		}

}
}
