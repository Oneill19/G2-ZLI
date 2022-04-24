package controllers.client;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OptionsScreenController {
	
	Stage currentStage;

    @FXML
    private Button editOrder;

    @FXML
    private Button showOrders;
    
    @FXML
    private Button exitClient;
    
    @FXML
    public void onShowOrders(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("Load");
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root = new FXMLLoader().<Pane>load(getClass().getResource("/controllers/ShowOrdersScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    @FXML
    public void onEdit(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root = new FXMLLoader().<Pane>load(getClass().getResource("/controllers/EditScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
    } 
    
    @FXML
    public void onExit(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("Disconnect");
		System.exit(0);
    }

}
