package gui.client;

import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Item;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

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
    
    private ArrayList<AbstractProduct> products = ChatClient.products;
    
    private AbstractProduct selectedProduct;

    /**
     * @param event
     * method to add to the cart
     */
    @FXML
    void onAddToCart(ActionEvent event) throws Exception {
    	// get the number of items in the cart
    	int counter = Integer.parseInt(cartCounter.getText());
    	
    	// set the new amount
    	cartCounter.setText(++counter + "");
    	
    	// add the selected product to the cart
    	ChatClient.cart.add(selectedProduct);
    }

    /**
     * @param event
     * method to exit the program
     */
    @FXML
    void onExit(ActionEvent event) throws Exception {
    	// send a logout request
    	ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
    	
    	// remove the user in program
		ChatClient.user = null;
		
		// disconnect from the server and exit
    	ClientUI.chat.accept("Disconnect");
		System.exit(0);
    }

    /**
     * @param event
     * method to move to the cart screen
     */
    @FXML
    void onGoToCart(MouseEvent event) throws Exception {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
    	new FXMLLoader();
    	Pane root = FXMLLoader.<Pane>load(getClass().getResource("Cart.fxml"));
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add("css/newCascadeStyleSheet.css");
		primaryStage.setTitle("Zer-Li Cart");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * @param event
     * method to log out the user
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
    
    /**
     * @param product
     * method to set the image, name, price and description in the side bar
     */
    private void setChosenProduct(AbstractProduct product) {
    	// set the selected product
    	selectedProduct = product;
    	
    	// set the image, name, price and description of the selected product
    	productImg.setImage(new Image(getClass().getResourceAsStream(product.getImage()), 200, 200, false, false));
    	productName.setText(product.getName());
    	productPrice.setText(product.getPrice() + "£");
    	String description = "";
    	if (product instanceof Product) {
    		description += "Premade Product\n"
    				+ "Made From:\n";
    		Product tempProduct = (Product)product;
    		for (Item item : tempProduct.getMadeFrom()) {
    			description += item.getAmountInProduct() + " " + item.getName() + "\n";
    		}
    		productDescription.setText(description);
    	}
    	else {
    		description += "Sold Alone Beautiful " + product.getName();
    		productDescription.setText(description);
    	}
    }
    
    /**
     * initialize the screen
     */
    public void initialize() {
    	// set the user button to show the name
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	
    	// set the first product in the side bar
    	if (products.size() > 0) {
    		setChosenProduct(products.get(0));
    	}
    	
    	// for every product in the products
    	int i = 0;
    	for (AbstractProduct product : products) {
    		// create the container
    		HBox box = new HBox();
    		ImageView img = new ImageView(new Image(getClass().getResourceAsStream(product.getImage()), 100, 100, false, false));
    		Label nameLabel = new Label(product.getName());
    		Label priceLabel = new Label(product.getPrice() + "£");
    		Label typeLabel = new Label((product instanceof Product) ? "Premade Product": "Sold Alone");
    		Button viewButton = new Button();
    		
    		// set the action on mouse click
    		viewButton.setOnMouseClicked((MouseEvent event) -> {
    			setChosenProduct(product);
    		});
    		
    		// set styling
    		viewButton.setText("View");
    		box.setAlignment(Pos.CENTER);
    		box.setSpacing(10);
    		
    		nameLabel.setPrefWidth(170);
    		priceLabel.setPrefWidth(50);
    		typeLabel.setPrefWidth(120);
    		viewButton.setPrefWidth(120); 
    		
    		nameLabel.setStyle("-fx-font-size: 15px");
    		priceLabel.setStyle("-fx-font-size: 15px");
    		typeLabel.setStyle("-fx-font-size: 15px");
    		
    		box.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		
    		viewButton.setStyle("-fx-text-fill: white; -fx-background-color:  #2B0548; -fx-background-radius: 10px; -fx-font-weight: bold; -fx-font-size: 20px");
    		
    		// add all the children to the hbox container
    		box.getChildren().add(img);
    		box.getChildren().add(nameLabel);
    		box.getChildren().add(priceLabel);
    		box.getChildren().add(typeLabel);
    		box.getChildren().add(viewButton);
    		
    		// add the hbox to the grid
    		grid.add(box, 0, i++);
    		grid.setHgap(10);
    		grid.setVgap(10);
    	}
    }

}

