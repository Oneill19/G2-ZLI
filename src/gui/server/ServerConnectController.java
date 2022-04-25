package gui.server;

import java.io.IOException;

import common.ClientInfo;
import server.EchoServer;
import server.ServerUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ServerConnectController {

	@FXML
	private Button connectToDB;

	@FXML
	private TableView<ClientInfo> connectionTable;
	
	@FXML
	private TextField ipField;

	@FXML
	private TextField dbnameField;

	@FXML
	private Button disconnectFromDB;

	@FXML
	private TableColumn<ClientInfo, String> hostColumn;

	@FXML
	private TableColumn<ClientInfo, String> ipColumn;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField portField;

	@FXML
	private TableColumn<ClientInfo, String> statusColumn;

	@FXML
	private TextField usernameField;

	@FXML
	private Button defaultInsert;
	
	@FXML
    private Label statusLabel;

	@FXML
	void toDefault() {
		ipField.setText("localhost");
		portField.setText("5555");
		passwordField.setText("Aa123456");
		usernameField.setText("root");
		dbnameField.setText("zli");
	}

	@FXML
	void onConnect(ActionEvent event) throws IOException {
		boolean status = ServerUI.runServer(ipField.getText(), portField.getText(), usernameField.getText(), passwordField.getText(),
				dbnameField.getText());
		if (status) {
			statusLabel.setText("Server Running");			
		}
	}

	@FXML
	public void onDisconnect(ActionEvent event) throws Exception {
		statusLabel.setText("");
		ServerUI.stopServer();
	}

	@FXML
	public void initialize() {
		ipColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("ipAddress"));
		hostColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("hostName"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("status"));
		connectionTable.setItems(EchoServer.getClientlist());
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/server/ServerConnect.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Server");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
