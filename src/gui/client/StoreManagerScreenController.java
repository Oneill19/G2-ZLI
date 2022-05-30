package gui.client;
import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class StoreManagerScreenController {

    @FXML
    private Button ViewMonthlyReport;

    @FXML
    private Button UserRegistration;

    @FXML
    private Button OrderManagement;

    @FXML
    private Button Permissionmanagement;

    @FXML
    private Button Exit;

    @FXML
    private Button User;

    @FXML
    private Button LogOut;

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

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onOrderManagement(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("/gui/client/OrderManagementScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li->Options Screen->Order Management");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onPermissionmanagement(ActionEvent event) throws Exception {  
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("PermissionManagement.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li->Option Screen->Permission Management");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * @param event
     */
    @FXML
    void onUser(ActionEvent event) {
    	System.out.println("On User Options");
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onUserRegistration(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("/gui/client/UserRegistrationTable.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li->Option Screen->User Registration");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    void onViewMonthlyReport(ActionEvent event) throws Exception{
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("ViewMonthlyReportStoreManager.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li->Option Screen->View Monthly Report");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    public void initialize() {
    	User.setText("Hello, " + ChatClient.user.getFirstName());
    }

}
