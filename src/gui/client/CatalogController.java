package gui.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Item;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
public class CatalogController {

	@FXML
    private Label messageLabel;
	
	@FXML
    private Button addCustomButton;
	
    @FXML
    private Button addToCartBtn;

    @FXML
    private ImageView cartBtn;
    
    @FXML
    private Button goToCartBtn2;

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
    
    @FXML private Pane pane;
    
    private ArrayList<AbstractProduct> products = ChatClient.products;
    
    private AbstractProduct selectedProduct;
    
    private CommonController cc = new CommonController();

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
    	
    	if (ChatClient.customerCart.containsKey(selectedProduct)) {
    		ChatClient.customerCart.put(selectedProduct, ChatClient.customerCart.get(selectedProduct) + 1);
    	}
    	else {
    		ChatClient.customerCart.put(selectedProduct, 1);
    	}
    	
    	messageLabel.setText(selectedProduct.getName() + " added!");
//    	System.out.println("Cart size:" + ChatClient.customerCart.size());
//    	System.out.println("Value: " + selectedProduct.getName() + ChatClient.customerCart.get(selectedProduct));
    }

    /**
     * @param event
     * method to exit the program
     */
    @FXML
    void onExit(ActionEvent event) throws Exception {
    	cc.OnExit();
    }

    /**
     * @param event
     * method to move to the cart screen
     */
    @FXML
    void onGoToCart(MouseEvent event) throws Exception {
    	//go to cart screen
		cc.changeFXML(event, "Cart.fxml", "Zer-Li Cart");
    }
    
    @FXML 
    void onGoToCart2(ActionEvent event) throws Exception {
    	//go to cart screen
    	cc.changeFXML(event, "Cart.fxml", "Zer-Li Cart");
    }
    

    /**
     * @param event
     * method to log out the user
     */
    @FXML
    void onLogout(ActionEvent event) throws Exception {
    	cc.onLogout(event);
    }

    /**
     * @param event
     */
    @FXML
    void onUserOptions(ActionEvent event) throws Exception {
    	if (ChatClient.user.getStatus().equals("CONFIRMED")) {
    		cc.changeFXML(event, "ApprvoedCustomerOptions.fxml", "Zer-Li");
    	}
    }
    
    /**
     * @param product
     * method to set the image, name, price and description in the side bar
     */
    private void setChosenProduct(AbstractProduct product) {
    	// set the selected product
    	selectedProduct = product;
    	
    	// set the image, name, price and description of the selected product
    	productImg.setImage(new Image(getClass().getResourceAsStream(product.getImagePath()), 200, 200, false, false));
    	productName.setText(product.getName());
    	productPrice.setText(product.getPrice() + "NIS");
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
     * go to add custom product screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onAddCustom(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "CreateCustomProduct.fxml", "Zer-Li Add Custom Product");
    }
    
    /**
     * initialize the screen
     */
    public void initialize() throws Exception {
    	try {
			ClientUI.chat.accept("getUserOrders\t"+ChatClient.user.getUserID());
		} catch (IOException e) {
			System.out.println("problem getting user orders");
			e.printStackTrace();
			return;
		}
    	if (ChatClient.userOrdersHistory.size()<1) {
    		Label firstOrder = new Label("20$ discount on you're first order!");
    		firstOrder.setStyle("-fx-font-size: 15px;");
    		firstOrder.setLayoutX(435);
    		firstOrder.setLayoutY(81);
    		pane.getChildren().add(firstOrder);
    		ChatClient.firstOrder=true;
    	}
		
    	//initialize cart size
    	cartCounter.setText(getCartSize() + "");
    	
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
    		ImageView img = null;
    		if (product.getImagePath() != null) {    			
    			img = new ImageView(new Image(getClass().getResourceAsStream(product.getImagePath()), 100, 100, false, false));
    		}
    		Label nameLabel = new Label(product.getName());
    		Label priceLabel = new Label(product.getPrice() + "NIS");
    		Label typeLabel = new Label((product instanceof Product) ? "Premade Product": "Sold Alone");
    		Button viewButton = new Button();
    		
    		// set the action on mouse click
    		viewButton.setOnMouseClicked((MouseEvent event) -> {
    			setChosenProduct(product);
    		});
    		
    		if (product.getSale() != 0) {
    			ClientUI.chat.accept("GetDiscountAmount" + "\t" + product.getSale());
    			if (ChatClient.discountAmount != 0) {
    				double temp = product.getPrice() - product.getPrice() * ((double)ChatClient.discountAmount / 100);
    				System.out.println(temp);
    				product.setPriceWithSale(temp);
    				priceLabel.setText("SALE!\n" + product.getPriceWithSale() + "NIS");
    			}
    			else {
    				priceLabel.setTextFill(Color.BLACK);
    			}
    		}
    		
    		// set styling
    		viewButton.setText("View");
    		box.setAlignment(Pos.CENTER);
    		box.setSpacing(10);
    		
    		nameLabel.setPrefWidth(170);
    		priceLabel.setPrefWidth(60);
    		typeLabel.setPrefWidth(120);
    		viewButton.setPrefWidth(110); 
    		
    		nameLabel.setStyle("-fx-font-size: 15px");
    		priceLabel.setStyle("-fx-font-size: 15px");
    		typeLabel.setStyle("-fx-font-size: 15px");
    		
    		box.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		
    		viewButton.setStyle("-fx-text-fill: white; -fx-background-color:  #2B0548; -fx-background-radius: 10px; -fx-font-weight: bold; -fx-font-size: 20px");
    		
    		// add all the children to the hbox container
    		if (img != null) {
    			box.getChildren().add(img); 
    		}
    		else {
    			Pane pane = new Pane();	
    			pane.setPrefSize(100, 100);
    			box.getChildren().add(pane);
    		}
    		box.getChildren().add(nameLabel);
    		box.getChildren().add(priceLabel);
    		box.getChildren().add(typeLabel);
    		box.getChildren().add(viewButton);
    		
    		// add the hbox to the grid
    		grid.add(box, 0, i++);
    		grid.setHgap(10);
    		grid.setVgap(10);
    	}
    	
//    	// init buttons style
    	exitBtn.setOnMouseEntered(new ButtonEventHandlerStyle.redBackgroundOnEnter(exitBtn));
		exitBtn.setOnMouseExited(new ButtonEventHandlerStyle.redBackgroundOnExit(exitBtn));
    }
    
    private int getCartSize() {
    	int counter = 0;
    	for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
    		counter += entry.getValue();
    	}
    	return counter;
    }
}

