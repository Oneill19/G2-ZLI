package gui.server;

import java.io.IOException;

import common.ClientInfo;
import gui.client.ButtonEventHandlerStyle;
import gui.client.ButtonEventHandlerStyle.purpleBackgroundOnEnter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import server.AuthQuery;
import server.EchoServer;
import server.ServerUI;

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
	private Button importUsers;

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
		importUsers.setDisable(false);
	}

	@FXML
	public void onDisconnect(ActionEvent event) throws Exception {
		connectionTable.getItems().clear();
		statusLabel.setText("");
		ServerUI.stopServer();
	}

	@FXML
	public void initialize() {
		importUsers.setDisable(true);
		ipColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("ipAddress"));
		hostColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("hostName"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<ClientInfo, String>("status"));
		connectionTable.setItems(EchoServer.getClientlist());
		
		disconnectFromDB.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(disconnectFromDB));
		disconnectFromDB.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(disconnectFromDB));
		
		connectToDB.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(connectToDB));
		connectToDB.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(connectToDB));
		
		defaultInsert.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(defaultInsert));
		defaultInsert.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(defaultInsert));
		
		importUsers.setOnMouseEntered(new ButtonEventHandlerStyle.purpleBackgroundOnEnter(importUsers));
		importUsers.setOnMouseExited(new ButtonEventHandlerStyle.purpleBackgroundOnExit(importUsers));
		
		
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/server/ServerConnect.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Server");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	@FXML
    void onImportUsers(ActionEvent event) {
		if (AuthQuery.importUsersFromSystem(EchoServer.getConnection())) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Import User Success");
			alert.setHeaderText("Importing the users was susccessful!");
			alert.showAndWait();
			importUsers.setDisable(true);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Import User Fail");
			alert.setHeaderText("Improting the users failed");
			alert.showAndWait();
		}
    }

}
