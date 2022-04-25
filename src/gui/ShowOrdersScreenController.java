package gui;

import java.sql.Timestamp;

import client.ChatClient;
import client.ClientUI;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowOrdersScreenController {
	
	ObservableList<Order> observableList;

	@FXML
    private TableView<Order> ordersTable;
	
	@FXML
    private TableColumn<Order, Integer> orderNumberCol;
	
	@FXML
    private TableColumn<Order, Double> priceCol;
	
	@FXML
    private TableColumn<Order, String> greetingCardCol;
	
	@FXML
    private TableColumn<Order, String> colorCol;

    @FXML
    private TableColumn<Order, String> dOrderCol;
    
    @FXML
    private TableColumn<Order, String> shopCol;

    @FXML
    private TableColumn<Order, Timestamp> dateCol;

    @FXML
    private TableColumn<Order, Timestamp> orderDateCol;
    
    @FXML
    private Button onBack;
    
    @FXML
    private Button exitClient;
    
    @FXML
    public void onBack(ActionEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("OptionsScreen.fxml"));
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
    
    public void initialize() {
    	observableList = FXCollections.observableArrayList(ChatClient.orders);
    	ordersTable.getItems().clear();
    	orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    	greetingCardCol.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
    	colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
    	dOrderCol.setCellValueFactory(new PropertyValueFactory<>("dorder"));
    	shopCol.setCellValueFactory(new PropertyValueFactory<>("shop"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    	orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    	ordersTable.setItems(observableList);
    }

}
