package gui.client;

import java.awt.event.ActionEvent;
import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import entity.StoreWorker;
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

	@FXML
	private Button User;

	@FXML
	private Button LogOut;

	@FXML
	private Button Exit;

	@FXML
	private Text errortxt;

	@FXML
	private ComboBox<String> YearC;

	@FXML
	private ComboBox<String> Quarterly;

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
	private Text q1;

	@FXML
	private Text q3;

	@FXML
	private Text quartLable;

	@FXML
	private Text q2;

	@FXML
	private Text storeLable;

	@FXML
	private Text yearLable;

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

	@FXML
	void onExit(ActionEvent event) throws IOException {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

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

	@FXML
	void onViewReport(ActionEvent event) throws Exception {
		if (!select1.isSelected() && !select2.isSelected()) {
			errortxt.setText("Please choose How Much Reports To View!");
		} else if (!select2.isSelected()) // 1 report to view selected
		{
			if (YearC.getValue() == null || Quarterly.getValue() == null || store.getValue() == null) {

				errortxt.setText("You Must Fill All The Fields!");
				return;
			}

			else {
				ClientUI.chat.accept("GetReportByQuarter" + "\t" + Quarterly.getValue() + "\t" + YearC.getValue() + "\t"
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
			if (YearC.getValue() == null || Quarterly.getValue() == null || store.getValue() == null
					|| YearC1.getValue() == null || Quarterly1.getValue() == null || store1.getValue() == null) {

				errortxt.setText("You Must Fill All The Fields!");
				return;

			} else {
				ClientUI.chat.accept("GetReportByQuarter1" + "\t" + Quarterly.getValue() + "\t" + YearC.getValue()
						+ "\t" + store.getValue());
				if (ChatClient.reportsq1.size() == 0) {
					errortxt.setText("No reports found for the First Choice!");
					return;
				}
				ClientUI.chat.accept("GetReportByQuarter2" + "\t" + Quarterly.getValue() + "\t" + YearC.getValue()
						+ "\t" + store.getValue());
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

	@FXML
	void onSelect1(ActionEvent event) {

		if (select1.isSelected()) {
			select2.setSelected(false);
			select2.setVisible(false);
			YearC1.setVisible(false);
			Quarterly1.setVisible(false);
			store1.setVisible(false);
			q1.setVisible(false);
			q2.setVisible(false);
			q3.setVisible(false);
			storeLable.setVisible(false);
			quartLable.setVisible(false);
			yearLable.setVisible(false);

		} else {
			select2.setSelected(true);
			select2.setVisible(true);
			YearC1.setVisible(true);
			Quarterly1.setVisible(true);
			store1.setVisible(true);
			q1.setVisible(true);
			q2.setVisible(true);
			q3.setVisible(true);
			storeLable.setVisible(true);
			quartLable.setVisible(true);
			yearLable.setVisible(true);
		}
	}

	@FXML
	void onSelect2(ActionEvent event) {
		if (select2.isSelected()) {
			select1.setSelected(false);
			YearC1.setVisible(true);
			Quarterly1.setVisible(true);
			store1.setVisible(true);
			q1.setVisible(true);
			q2.setVisible(true);
			q3.setVisible(true);
			storeLable.setVisible(true);
			quartLable.setVisible(true);
			yearLable.setVisible(true);
		} else {
			select1.setSelected(true);
			select2.setSelected(false);
			YearC1.setVisible(false);
			Quarterly1.setVisible(false);
			store1.setVisible(false);
			q1.setVisible(false);
			q2.setVisible(false);
			q3.setVisible(false);
			storeLable.setVisible(false);
			quartLable.setVisible(false);
			yearLable.setVisible(false);

		}

	}

}
