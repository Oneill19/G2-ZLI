package gui.client;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class CatalogController {

    @FXML
    private Button addToCartBtn;

    @FXML
    private ImageView cartBtn;

    @FXML
    private Text cartCounter;

    @FXML
    private Button exitBtn;

    @FXML
    private ScrollPane itemScrollPane;

    @FXML
    private Button logoutBtn;

    @FXML
    private Text productDescription;

    @FXML
    private ImageView productImg;

    @FXML
    private Text productName;

    @FXML
    private Text productPrice;

    @FXML
    private Button userOptBtn;

    /**
     * @param event
     */
    @FXML
    void onAddToCart(ActionEvent event) throws Exception {
  
    }

    /**
     * @param event
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
     */
    @FXML
    void onGoToCart(MouseEvent event) throws Exception {
    	System.out.println("On Go To Cart");
    	
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	new FXMLLoader();
    	Pane root = FXMLLoader.<Pane>load(getClass().getResource("Cart.fxml"));
    	Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Cart");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * @param event
     */
    @FXML
    void onLogout(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
    	ChatClient.user = null;
    	((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("LoginScreen.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("css/newCascadeStyleSheet.css");
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * @param event
     */
    @FXML
    void onUserOptions(ActionEvent event) throws Exception {
    	System.out.println("On User Options");
    }

}

