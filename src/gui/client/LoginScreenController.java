package gui.client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.EchoServer;

/**
 * @AUTHOR ONEILL PANKER
 */
public class LoginScreenController {

	@FXML
	private Button dorinConfirmedCustomer, exit, login, backBtn, disconnectUsers;
	@FXML
	private Text errorLabel;
	@FXML
	private TextField mail;
	@FXML
	private PasswordField password;

	@FXML
	void onMarketingWorker(ActionEvent event) throws Exception {
		mail.setText("naruto@zli");
		password.setText("1");
		login.fire();
	}

	// Delete when not usable
	@FXML
	void ondorinConfirmedCustomer(ActionEvent event) throws Exception {
		mail.setText("dorin@zli");
		password.setText("1");
		login.fire();
	}

	// TODO
	// delete this function when not usable anymore
	@SuppressWarnings("static-access")
	@FXML
	void onDisconnectUsers(ActionEvent event) throws Exception {
		EchoServer echoServer = new EchoServer("localhost", 5555, "root", "Aa123456", "zli");
		echoServer.connectToDB();
		Connection conn = echoServer.getConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE `zli`.`users` SET IsLogged=0 WHERE IsLogged=1;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Users were forced to log out");
	}

	/**
	 * method to disconnect from the client and exit the application
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onExit(ActionEvent event) throws Exception {
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

	/**
	 * method to get the user authentication information and redirect him to the
	 * current screen
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onLogin(ActionEvent event) throws Exception {
		// get user mail and password for authentication
		String userMail = mail.getText();
		String userPassword = password.getText();

		// check if the user typed all the information
		if (userMail.equals("") || userPassword.equals("")) {
			errorLabel.setText("Please enter Mail and Password!");
			return;
		}

		// get the user from the sql database
		ClientUI.chat.accept("GetUser" + "\t" + userMail + "\t" + userPassword);

		// if nothing returned from the database show error message
		if (ChatClient.user == null) {
			errorLabel.setText("Wrong Mail or Password!");
			return;
		}

		// if the user already logged in show error message
		if (ChatClient.user.isLogged()) {
			errorLabel.setText("User Already logged!");
			return;
		}

		// initialize error message
		errorLabel.setText("");

		// set the user as logged in the database
		ClientUI.chat.accept("LogUser" + "\t" + userMail);

		// set next screen stage
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = null;

		// switch to redirect to the appropriate screen
		switch (ChatClient.user.getUserRole()) {
		case "Customer":
			ChatClient.products.clear();
			ClientUI.chat.accept("GetAllProducts");
			ClientUI.chat.accept("GetAllItems");
			if (ChatClient.user.getStatus().equals("CONFIRMED")) {
				root = FXMLLoader.<Pane>load(getClass().getResource("ApprvoedCustomerOptions.fxml"));
				primaryStage.setTitle("Zer-li Options Screen");
			} else {
				root = FXMLLoader.<Pane>load(getClass().getResource("ReadOnlyCatalog.fxml"));
				primaryStage.setTitle("Zer-li Catalog");
			}
			break;
		case "StoreManager":
			root = FXMLLoader.<Pane>load(getClass().getResource("StoreManagerScreen.fxml"));
			primaryStage.setTitle("Zer-li Store Manager");
			break;
		case "StoreWorker":
			root = FXMLLoader.<Pane>load(getClass().getResource("StoreWorkerScreen.fxml"));
			primaryStage.setTitle("Zer-li Store Worker");
			break;
		case "StoreWorkerWithPermission":
			root = FXMLLoader.<Pane>load(getClass().getResource("StoreWorkerApprovedScreen.fxml"));
			primaryStage.setTitle("Zer-li Store Worker");
			break;
		case "CustomerServiceWorker":
			root = FXMLLoader.<Pane>load(getClass().getResource("CustomerServiceWorkerScreen.fxml"));
			primaryStage.setTitle("Zer-li Customer Service Worker");
			break;
		case "ServiceSpecialist":
			root = FXMLLoader.<Pane>load(getClass().getResource("ServiceSpecialistScreen.fxml"));
			primaryStage.setTitle("Zer-li Service Specialist");
			break;
		case "NetworkManager":
			root = FXMLLoader.<Pane>load(getClass().getResource("CEOScreen.fxml"));
			primaryStage.setTitle("Zer-li CEO");
			break;
		case "MarketingEmployee":
			ChatClient.products.clear();
			ClientUI.chat.accept("GetAllProducts");
			ClientUI.chat.accept("GetAllItems");
			root = FXMLLoader.<Pane>load(getClass().getResource("MarketingEmployeOptions.fxml"));
			primaryStage.setTitle("Zer-li Marketing Employee");
			break;
		case "Delivery":
			root = FXMLLoader.<Pane>load(getClass().getResource("DeliveryScreen.fxml"));
			primaryStage.setTitle("Zer-li Delivery");
			break;
		}

		if (root == null) {
			return;
		}

		Scene scene = new Scene(root);
//		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * initialize the screen
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
//		// init buttons style
		exit.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnEnter(exit));
		exit.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(exit));
		
		login.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(login));
		login.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnExit(login));
	}
}
