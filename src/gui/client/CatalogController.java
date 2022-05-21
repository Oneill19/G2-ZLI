package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import common.MyListener;
import entity.AbstractProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
    
    @FXML
    private GridPane grid;

    /**
     * @param event
     */
    @FXML
    void onAddToCart(ActionEvent event) throws Exception {
    	initialize();
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
    	scene.getStylesheets().add("css/newCascadeStyleSheet.css");
		primaryStage.setTitle("Zer-Li Cart");
		//primaryStage.getIcons().add(icon);
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
    
    private MyListener myListener;
    private ArrayList<AbstractProduct> products = ChatClient.products;
    
    private void setChosenProduct(AbstractProduct product) {
    	
    }
    
    public void initialize() {
        if (products.size() > 0) {
            setChosenProduct(products.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(AbstractProduct product) {
                    setChosenProduct(product);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Product.fxml"));
                AnchorPane anchorPane = loader.load();

                ProductController productController = loader.getController();
                productController.setData(products.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

