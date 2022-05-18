package gui.client;


import java.awt.event.ActionEvent;
import entity.User;
import client.ChatClient;
import client.ClientUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserRegistrationPageController {

    @FXML
    private Button Back;

    @FXML
    private Button Exit;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> IDNumberCol;

    @FXML
    private TableColumn<User,String> UserFirstNameCol;

    @FXML
    private TableColumn<User, String> UserLastNameCol;

    @FXML
    private Text ErrorMsg;

    @FXML
    private Button User;

    @FXML
    private Button LogOut;

    @FXML
    private Button Next;

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
    void onLogOut(ActionEvent event) throws Exception{
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

   // @FXML
  /*  void onNext(ActionEvent event) {
    	User Selecteduser= userTable.getSelectionModel().getSelectedItem();
    	if(Selecteduser==null)
    		ErrorMsg.setText("Please choose one User!");
    	else {
    		
	    	((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			new FXMLLoader();
			Pane root = FXMLLoader.<Pane>load(getClass().getResource("UserRegistration.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Zer-Li->Store Manager Screen->User Registration");
			primaryStage.setScene(scene);
			primaryStage.show();
    }*/
     	// initialize error message
    	//ErrorMsg.setText("");

  //  @FXML
  //  void onUser(ActionEvent event) {

  //  }

}
