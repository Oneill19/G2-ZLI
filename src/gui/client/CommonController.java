package gui.client;

import java.io.IOException;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CommonController {

	/**
	 * Hides current screen and replaces it with param fxmlPath
	 * @param event the event which triggered the call
	 * @param fxmlPath the path of target FXML
	 * @param title the title to give the FXML
	 * @throws IOException	
	 */
	public void changeFXML(Event event, String fxmlPath, String title) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource(fxmlPath));
		Scene scene = new Scene(root);
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * logs out current user and exits system
	 * @throws Exception
	 */
	public void OnExit()  throws Exception {
    	// send a logout request
    	ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());  	
    	// remove the user in program
		ChatClient.user = null;
		// disconnect from the server and exit
    	ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}
	
    /**
     * logs out current user and returns to login screen
     * @param event
     * @throws Exception
     */
    public void onLogout(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
    	ChatClient.user = null;
    	changeFXML(event, "LoginScreen.fxml", "Zer-Li Client");
    }
}
