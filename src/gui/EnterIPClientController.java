package gui;

import client.ClientController;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EnterIPClientController {
	
	@FXML
    private Label errorLabel;

	@FXML
	private Button connectToClient;

	@FXML
	private Button exitClient;
	
	@FXML
	private Button insertDefault;

	@FXML
	private TextField ipClient;
	
	@FXML
    private TextField portClient;

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/EnterIPClient.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public String getIp() {
		return ipClient.getText().trim();
	}
	
	public String getPort() {
		return portClient.getText().trim();
	}
	
	public boolean isNumber(String port) {
		try {
			Integer.parseInt(port);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@FXML
	public void onConnect(ActionEvent event) throws Exception {
		if (getIp().equals("") || getPort().equals("")) {
			errorLabel.setText("You must enter IP and Port!");
		}
		else if (!isNumber(getPort())) {
			errorLabel.setText("Port must be number!");
		}
		else {
			errorLabel.setText("");
			ClientUI.chat = new ClientController(getIp(), Integer.parseInt(getPort()));
			ClientUI.chat.accept("Connected");
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Pane root = new FXMLLoader().<Pane>load(getClass().getResource("OptionsScreen.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Zer-Li Client");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	@FXML
	public void onDefault(ActionEvent event) throws Exception {
		ipClient.setText("localhost");
		portClient.setText("5555");
	}
	
	@FXML
	public void onExit(ActionEvent event) throws Exception {
		System.exit(0);
	}
}
