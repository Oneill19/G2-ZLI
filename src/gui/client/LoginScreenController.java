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
    @FXML  private Text errorLabel;
    @FXML private TextField mail;
    @FXML   private PasswordField password;
    
    @FXML
    void onMarketingWorker(ActionEvent event) throws Exception{
    	mail.setText("naruto@zli");
    	password.setText("1");
    	login.fire();
    }
    
    
    //Delete when not usable
    @FXML
    void ondorinConfirmedCustomer(ActionEvent event) throws Exception{
    	mail.setText("dorin@zli");
    	password.setText("1");
    	login.fire();
    }
    
    
    //TODO 
    //delete this function when not usable anymore
    @SuppressWarnings("static-access")
	@FXML
    void onDisconnectUsers(ActionEvent event)	throws Exception {
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
    void onExit(ActionEvent event)	throws Exception {
    	ClientUI.chat.accept("Disconnect");
    	System.exit(0);
    }

    /**
     * method to get the user authentication information and redirect him to the current screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onLogin(ActionEvent event)	throws Exception {
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
    		ClientUI.chat.accept("GetAllProducts");
    		ClientUI.chat.accept("GetAllItems");
    		if (ChatClient.user.getStatus().equals("CONFIRMED")) {
    			ClientUI.chat.accept("getUserOrders\t"+Integer.toString(ChatClient.user.getUserID()));
    			root = FXMLLoader.<Pane>load(getClass().getResource("ApprvoedCustomerOptions.fxml"));
    		}
    		else {
    			root = FXMLLoader.<Pane>load(getClass().getResource("ReadOnlyCatalog.fxml"));
    		}
    		break;
    	case "StoreManager":
//    		System.out.println("StoreManager");
    		root = FXMLLoader.<Pane>load(getClass().getResource("StoreManagerScreen.fxml"));
    		break;
    	case "StoreWorker":
    		System.out.println("StoreWorker");
    		break;
    	case "CustomerServiceWorker":
//    		System.out.println("CustomerServiceWorker");
    		root = FXMLLoader.<Pane>load(getClass().getResource("CustomerServiceWorkerScreen.fxml"));
    		break;
    	case "ServiceSpecialist":
    		System.out.println("ServiceSpecialist");
    		break;
    	case "NetworkManager":
//    		System.out.println("NetworkManager");
    		root = FXMLLoader.<Pane>load(getClass().getResource("CEOScreen.fxml"));
    		break;
    	case "MarketingEmployee":
//    		System.out.println("MarketingEmployee");
    		root = FXMLLoader.<Pane>load(getClass().getResource("MarketingEmployeOptions.fxml"));
    		break;
    	case "Delivery":
    		System.out.println("Delivery");
    		break;
    	}
    	
    	Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

}
