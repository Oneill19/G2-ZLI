package gui.client;

import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import entity.StoreWorker;
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
    @FXML
    void onExit(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
    }

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
    void onSave(ActionEvent event)  throws Exception{
		String newStatus = "";
		
		String userId = Idtxt.getText();
		String cardNum=CardNumtxt.getText();
		if (Idtxt.getText().equals("")) {
			errormsg.setText("Please choose one user to Register!");
			return;
		}
		else if(CardNumtxt.getText().equals("")) {
				errormsg.setText("Please Insert Card Number!");
				return;
			}

		else {
			ClientUI.chat.accept("ConfirmedUserUpdate" + "\t" + userId + "\t" + cardNum);
			// SelectedUser.setStatus(StatusCol.getText());add convert
			txt.setText("Saved successfully!");

		}
		
		// initialize error message
		errormsg.setText("");
		

	}
    

    @FXML
    void onUser(ActionEvent event) throws Exception {

    }
    @FXML
    void onSelectedUser(MouseEvent event)  {
		selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (userTable == null)
			errormsg.setText("Please choose one User to edit!");
		else {
			Idtxt.setText(String.valueOf(selectedUser.getUserID()));
			FirstNametxt.setText(selectedUser.getFirstName());
			LastNametxt.setText(selectedUser.getLastName());
			Phonetxt.setText(selectedUser.getPhone());
			Emailtxt.setText(selectedUser.getEmail());
		}
		// initialize error message
		errormsg.setText("");
	
    }
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
