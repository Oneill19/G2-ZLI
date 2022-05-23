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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PermissionManagementController {

	ObservableList<User> observableList;
	ObservableList<String> Combolist;

	@FXML
	private Button Back;

	@FXML
	private Button Exit;

	@FXML
	private Button User;

	@FXML
	private Button LogOut;

	@FXML
	private TextField IDtxt;

	@FXML
	private TextField FirstNametxt;

	@FXML
	private TextField LastNametxt;

	@FXML
	private Button Save;

	@FXML
	private Text Errormsg;

	@FXML
	private TableView<User> userTable;

	@FXML
	private TableColumn<User, Integer> IDNumberCol;

	@FXML
	private TableColumn<User, String> UserFirstNameCol;

	@FXML
	private TableColumn<User, String> UserLastNameCol;

	@FXML
	private TableColumn<User, String> StatusCol;

	@FXML
	private ComboBox<String> StatusComboBox;
	
	User selectedUser;

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
	void onSave(ActionEvent event) throws Exception {
		String newStatus = "";
		if (StatusComboBox.getValue() != null) {
			newStatus = StatusComboBox.getValue().toString();
		}
		String userId = IDtxt.getText();
		if (IDtxt.getText().equals("")) {
			Errormsg.setText("Please choose one user to edit!");
			return;
		} else if (newStatus.equals("")) {
			Errormsg.setText("Please choose status!");
			return;

		} else {
			ClientUI.chat.accept("ChangeUserStatus" + "\t" + newStatus + "\t" + userId);
			// SelectedUser.setStatus(StatusCol.getText());add convert
			if (selectedUser != null) {
				selectedUser.setStatus(newStatus);
			}
		}
		// initialize error message
		Errormsg.setText("");

	}

	@FXML
	void onUser(ActionEvent event) {

	}

	@FXML
	void onSelectedUser(MouseEvent event) {
		selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (userTable == null)
			Errormsg.setText("Please choose one User to edit!");
		else {
			IDtxt.setText(String.valueOf(selectedUser.getUserID()));
			FirstNametxt.setText(selectedUser.getFirstName());
			LastNametxt.setText(selectedUser.getLastName());
		}
		// initialize error message
		Errormsg.setText("");
	}

	@FXML
	void initialize() throws IOException {
		ClientUI.chat.accept("GetApprovedUsers");
		observableList = FXCollections.observableArrayList(ChatClient.ApprovedUserToPer);
		userTable.getItems().clear();
		IDNumberCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
		UserFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		UserLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		userTable.setItems(observableList);
		Combolist = FXCollections.observableArrayList("CONFIRMED", "FREEZED");
		StatusComboBox.setItems(Combolist);

	}

}
