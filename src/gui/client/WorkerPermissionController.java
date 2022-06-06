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

/**
 * @author Koral Biton, Topaz Eldori
 *
 */
public class WorkerPermissionController {
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
    private TableColumn<User, String> role;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField roleTxt;
	User selectedUser;


    /**
     *  Go back to the options screen
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
     * exit from Zer-Li system
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
     * Button that allows changing the status of the worker to "store worker" or Store Worker With Permission
     * @param event
     * @throws Exception
     */
    @FXML
    void onSave(ActionEvent event) throws Exception {
		String newStatus = "";
		String statusInDB="";
		if (roleComboBox.getValue() != null) {
			newStatus = roleComboBox.getValue().toString();
		}
		if(newStatus.equals("Store Worker"))
			statusInDB="StoreWorker";
		else 
		{
			statusInDB="StoreWorkerWithPermission";
		}
		String userId = IDtxt.getText();
		if (IDtxt.getText().equals("")) {
			Errormsg.setText("Please choose one user to edit!");
			return;
		} else if (newStatus.equals("")) {
			Errormsg.setText("Please choose status!");
			return;

		} else {
			ClientUI.chat.accept("ChangeWorkerStatus" + "\t" + statusInDB + "\t" + userId);
			// SelectedUser.setStatus(StatusCol.getText());add convert
			if (selectedUser != null) {
				selectedUser.setUserRole(newStatus);
				userTable.getItems().clear();
				observableList = FXCollections.observableArrayList(ChatClient.workersStore);
				userTable.setItems(observableList);

			}
		}
		// initialize error message
		Errormsg.setText("");

	}
    

    /**
     * Check whether a worker is selected from the table
     * @param event
     */
    @FXML
    void onSelectedUser(MouseEvent event) {
    	selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (userTable == null || selectedUser == null) {
			Errormsg.setText("Please choose one User to edit!");
			return;
		}
		else {
			IDtxt.setText(String.valueOf(selectedUser.getUserID()));
			FirstNametxt.setText(selectedUser.getFirstName());
			LastNametxt.setText(selectedUser.getLastName());
			roleTxt.setText(selectedUser.getUserRole());
		}
		// initialize error message
		Errormsg.setText("");
	}
    

    @FXML
    void onUser(ActionEvent event) {
    	System.out.println("On User Options");
    }
	/**
	 * Initialize the screen
	 * @throws IOException
	 */
	@FXML
	void initialize() throws IOException {
    	User.setText("Hello, " + ChatClient.user.getFirstName());

		ClientUI.chat.accept("GetWorkers" + "\t" +((StoreWorker)ChatClient.user).getStoreName());
		observableList = FXCollections.observableArrayList(ChatClient.workersStore);
		userTable.getItems().clear();
		IDNumberCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
		UserFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		UserLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		role.setCellValueFactory(new PropertyValueFactory<>("userRole"));
		userTable.setItems(observableList);
		Combolist = FXCollections.observableArrayList("Store Worker", "Store Worker With Permission"); 
		roleComboBox.setItems(Combolist);

	}
}
