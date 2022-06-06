package gui.client;

import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Koral Biton, Topaz Eldori
 *
 */
public class UserRegistrationPageController {

	ObservableList<User> observableList;
	@FXML
	private Button Back;

	@FXML
	private Button Exit;

	@FXML
	private Button User;

	@FXML
	private Button LogOut;

	@FXML
	private TextField FirstNametxt;

	@FXML
	private TextField LastNametxt;

	@FXML
	private TextField Idtxt;

	@FXML
	private TextField Phonetxt;

	@FXML
	private TextField Emailtxt;

	@FXML
	private TextField CardNumtxt;

	@FXML
	private Button Save;

	@FXML
	private Text errormsg;

	@FXML
	private Text txt;

	@FXML
	private TableView<User> userTable;

	@FXML
	private TableColumn<User, Integer> IDNumberCol;

	@FXML
	private TableColumn<User, String> UserFirstNameCol;

	@FXML
	private TableColumn<User, String> UserLastNameCol;

	@FXML
	private TableColumn<User, String> phoneNumberCol;

	@FXML
	private TableColumn<User, String> emailNumberCol;

	User selectedUser;

	/**
	 * Go back to the options screen
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
	 *  exit from Zer-Li system 
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
	 * Log out from the user and go back to login screen
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

	/**
	 * Registration of a user for the Zer-Li system
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void onSave(ActionEvent event) throws Exception {

		String userId = Idtxt.getText();
		String cardNum = CardNumtxt.getText();
		if (Idtxt.getText().equals("")) {
			errormsg.setText("Please choose one user to Register!");
			return;
		} else if (CardNumtxt.getText().equals("")) {
			errormsg.setText("Please Insert Card Number!");
			return;
		}

		else {
			ClientUI.chat.accept("ConfirmedUserUpdate" + "\t" + userId + "\t" + cardNum);
			// SelectedUser.setStatus(StatusCol.getText());add convert
			txt.setText("Saved successfully!");
			//show message
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Simulation");
			alert.setHeaderText("Customer registration completed successfully! 20% discount coupon sent for the first purchase!");
			alert.setContentText("Email: "+ Emailtxt.getText()+"\nPhone: "+Phonetxt.getText());
			alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("orderReception.png"))));
			alert.showAndWait();

		}

		// initialize error message
		errormsg.setText("");

	}

	@FXML
	void onUser(ActionEvent event) throws Exception {
		System.out.println("On User Options");
	}

	/**
	 * Check whether a user is selected from the table
	 * @param event
	 */
	@FXML
	void onSelectedUser(MouseEvent event) {
		if (userTable == null) {
			errormsg.setText("Please choose one User to edit!");
			return;
		}
		selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (selectedUser == null) {
			errormsg.setText("Please choose one User to edit!");
			return;
		}
		Idtxt.setText(String.valueOf(selectedUser.getUserID()));
		FirstNametxt.setText(selectedUser.getFirstName());
		LastNametxt.setText(selectedUser.getLastName());
		Phonetxt.setText(selectedUser.getPhone());
		Emailtxt.setText(selectedUser.getEmail());
		// initialize error message
		errormsg.setText("");

	}

	/**
	 * Initialize the screen
	 * @throws IOException
	 */
	@FXML
	void initialize() throws IOException {
		User.setText("Hello, " + ChatClient.user.getFirstName());
		ClientUI.chat.accept("GetRegistersUsers");
		observableList = FXCollections.observableArrayList(ChatClient.waitingUsers);
		userTable.getItems().clear();
		IDNumberCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
		UserFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		UserLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailNumberCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		userTable.setItems(observableList);

	}

}
