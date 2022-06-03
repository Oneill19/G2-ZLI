package gui.client;

import java.util.ArrayList;

import client.ChatClient;
import entity.AbstractProduct;
import entity.Item;
import entity.Order;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CustomerOrderHistoryController {


    @FXML private Button onBack, logoutBtn, userOptBtn, exit;
    @FXML private TableView<Order> ordersTable;
    @FXML private ScrollPane itemScrollPane;
    @FXML private GridPane grid;
    @FXML private Tooltip tableToolTip;
    @FXML private Text textCreationDate,textSupplyDate,textConfirmedDate,textCompleteDate;
    @FXML private Text textCreationTime,textSupplyTime;
    private ArrayList<Order> userOrdersDataFromDB = new ArrayList<Order>();
    private ArrayList<AbstractProduct> abstractProduct = new ArrayList<>();
    private Order selectedOrder = null;

    
    
    @FXML
    void onBack(ActionEvent event) {

    }

    @FXML
    void onExit(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {

    }
    
    //TODO - write the function initialUserOrderDataFromDB
    private void initialUserOrdersDataFromDB(Object ordersList, Object abstractProductsList) {
    	
    }
    
    
    public void initialize() {
    	Rectangle rect = new Rectangle(0, 0, 100, 100);
        Tooltip.install(rect, tableToolTip);
    	
//    	set the user button to show the name
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	
//    	set the first order in the side bar
    	if (userOrdersDataFromDB.size() > 0)
    		setChosenOrder(userOrdersDataFromDB.get(0)); 	
    	}
	
	
	
	
    /**
     * @param order
     * method to set the image, name, price and description in the side bar
     */
    private void setChosenOrder(Order order) {
//    	set the selected product
    	selectedOrder = order;
    	
//    	set selected order header info
    	textCreationDate.setText(order.getOrderCreationDate().toString());
    	textSupplyDate.setText(order.getSupplyDate().toString());
    	textConfirmedDate.setText(order.getConfirmedDate().toString());
    	textCompleteDate.setText(order.getCompleteDate().toString());
    	textCreationTime.setText(order.getOrderCreationTime().toString());
    	textSupplyTime.setText(order.getSupplyTime().toString());
    	
//    	show items and products of selected order
    	int i=0;
    	for (AbstractProduct ap : abstractProduct) {
    		Text name = new Text(ap.getName());
    		ImageView image = new ImageView(new Image(getClass().getResourceAsStream(ap.getImagePath()),100,100,false,false));
    		VBox vbox1 = new VBox(name,image);
    		VBox vbox2=null;
    		Text itemOrProduct=null, type=null,priceRange=null,color=null;
    		if (ap instanceof Product) {
    			itemOrProduct = new Text("Product");
    			vbox2 = new VBox(itemOrProduct);
    		}
    		else {
    			Item item = (Item)ap;
    			itemOrProduct = new Text("Item");
    			type = new Text(item.getType());
    			priceRange = new Text(item.getPriceRange());
    			color = new Text(item.getColor());
    			vbox2 = new VBox(itemOrProduct, type, priceRange,color);
    		}
    		HBox hbox = new HBox(vbox1, vbox2);
    		hbox.setAlignment(Pos.CENTER);
    		vbox1.setAlignment(Pos.CENTER);
    		vbox2.setAlignment(Pos.CENTER);
    		
    		hbox.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		vbox1.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		vbox2.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		
    		name.autosize();
    		name.setStyle("-fx-font-size: 15px");
    		if(ap instanceof Item) {
	    		itemOrProduct.autosize();
	    		type.autosize();
	    		priceRange.autosize();
	    		color.autosize();
	    		
	    		itemOrProduct.setStyle("-fx-font-size: 15px");
	    		type.setStyle("-fx-font-size: 15px");
	    		priceRange.setStyle("-fx-font-size: 15px");
	    		color.setStyle("-fx-font-size: 15px");
	    		
	    		grid.add(hbox, 0, i++);
	    		grid.setHgap(10);
	    		grid.setVgap(10);
    		}	
    	}
    }
}
