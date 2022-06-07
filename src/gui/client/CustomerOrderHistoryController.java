package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.CustomProduct;
import entity.Item;
import entity.Order;
import entity.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Dorin
 *
 */
public class CustomerOrderHistoryController {

    @FXML private Button onBack, logoutBtn, userOptBtn, exit;
    @FXML private Pane pane;
    
    //scroll pane FXMLs
    @FXML private ScrollPane itemScrollPane;
    @FXML private GridPane grid;
    @FXML private Tooltip tableToolTip;
    @FXML private Text textCreationDate,textSupplyDate,textConfirmedDate,textCompleteDate;
    @FXML private Text textCreationTime,textSupplyTime;
    @FXML private Text receiverNameText,receiverPhoneText, receiverAddressText,textOrderNumber,storeNameText,statusText,deliveryStatusText;
    @FXML private HBox deliveryHbox;
    @FXML private VBox pickupVbox;
    
    //tableview FXMLs
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, Integer> colOrderNumber;
    @FXML private TableColumn<Order, Double> colPrice;
    @FXML private TableColumn<Order, String> colGreetingCard,colDeliveryMethod,colCancelOrder;    

    private ArrayList<Order> userOrdersDataFromDB = new ArrayList<Order>();
    private ArrayList<AbstractProduct> orderHistoryAll = new ArrayList<AbstractProduct>();
    private CommonController cc = new CommonController();

	@FXML
	void onBack(ActionEvent event) throws IOException {		
		cc.changeFXML(event, "ApprvoedCustomerOptions.fxml", "Zer-Li Customer Options");
	}

	@FXML
	void onExit(ActionEvent event) throws Exception {
		cc.OnExit();
	}

	@FXML
	void onLogout(ActionEvent event) throws Exception {
		cc.onLogout(event);
	}
    
	/**
	 * initials userOrdersDataFromDB with all orders of the user from the DB,
	 * and sets the orderTable to select the first order from this list.
	 */
	private void setOrdersFromDB() {

//		get orders from DB
		try {
			ClientUI.chat.accept("getUserOrders\t"+Integer.toString(ChatClient.user.getUserID()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
//		initial classs' order array
		userOrdersDataFromDB.clear();
    	userOrdersDataFromDB = ChatClient.userOrdersHistory;

//    	set the first order in the side bar
    	if (ChatClient.userOrdersHistory.size() > 0) {
    		try { setChosenOrder(userOrdersDataFromDB.get(0)); } catch (IOException e) { e.printStackTrace(); }
    	}
    	else {
    		Text noOrdersText = new Text("You don't have any order yet\nMake you're first order right now, \nhere:");
    		noOrdersText.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 20));
    		Button catalogButton = new MyButtons().createRegularButton();

    		noOrdersText.setLayoutX(764);
    		noOrdersText.setLayoutY(232);
    		catalogButton.setLayoutX(820);
    		catalogButton.setLayoutY(260);
    		pane.getChildren().addAll(noOrdersText,catalogButton);
    		
    	}
	}
	
    public void initialize() {
    	pickupVbox.setVisible(false);
    	deliveryHbox.setVisible(false);
    	
    	setOrdersFromDB();
    	
    	Rectangle rect = new Rectangle(0, 0, 100, 100);
        Tooltip.install(rect, tableToolTip);
    	
//    	set the user button to show the name
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	
		colOrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		colGreetingCard.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
        colDeliveryMethod.setCellValueFactory(new PropertyValueFactory<>("deliveryMethod"));

        ordersTable.setId("my-table");
        ordersTable.getItems().clear();
        ordersTable.autosize();
        ordersTable.setItems(FXCollections.observableArrayList(ChatClient.userOrdersHistory));
        
        ordersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
			@Override
			public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue ) {
				try { setChosenOrder(newValue); } catch (IOException e) { e.printStackTrace(); }
			}
        });
     		
 		// Sets colDelete behavior
		colCancelOrder.setCellValueFactory(
			new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> p) {
					return new SimpleStringProperty(p.getValue() != null, null);
				}
			});

 		// Adding the Button to the cell
		colCancelOrder.setCellFactory(
			new Callback<TableColumn<Order, String>, TableCell<Order, String>>() {
				@Override
				public TableCell<Order, String> call(TableColumn<Order, String> p) {
					return new MyButtons().createDeleteButtonForTable();
				}
			});
    }
    
    /**
     * @param order
     * method to set the image, name, price and description in the side bar
     * @throws IOException 
     */
    private void setChosenOrder(Order order) throws IOException {
    	grid.getChildren().clear();
    	orderHistoryAll.clear();
    	
//    	set the selected product
    	textOrderNumber.setText("Order #"+ Integer.toString(order.getOrderNumber()));
    	
    	//get items and products of selected order
    	ClientUI.chat.accept("getOrderItems\t"+order.getOrderNumber());
    	ClientUI.chat.accept("getOrderProducts\t"+order.getOrderNumber());
    	ClientUI.chat.accept("getOrderCustom\t"+order.getOrderNumber());
    	
    	orderHistoryAll.addAll(ChatClient.orderHistoryItems);
    	orderHistoryAll.addAll(ChatClient.orderHistoryProducts);
    	if(ChatClient.orderHistoryCustom != null)
    		orderHistoryAll.addAll(ChatClient.orderHistoryCustom);
    	
//    	set selected order header info
    	textCreationDate.setText(order.getOrderCreationDate().toString());
    	textSupplyDate.setText(order.getSupplyDate().toString());
    	if(order.getConfirmedDate().equals("null"))
    		textConfirmedDate.setText(("Not Yet"));
    	else
    		textConfirmedDate.setText(order.getConfirmedDate());    	
    	if(order.getCompleteDate().equals("null"))
    		textCompleteDate.setText(("Not Yet"));
    	else
    		textCompleteDate.setText(order.getCompleteDate().toString());
    	textCreationTime.setText(order.getOrderCreationTime().toString());
    	textSupplyTime.setText(order.getSupplyTime().toString());
    	
    	statusText.setId("status1");
    	statusText.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 20));
    	deliveryStatusText.setId("status2");
    	deliveryStatusText.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 20));
   	
    	if (order.getDeliveryMethod().equals("Delivery")) {
    		pickupVbox.setVisible(false);
    		ClientUI.chat.accept("getOrderDeliveryData\t"+order.getOrderNumber());
    		String s = ChatClient.orderHistoryDeliveryData;
    		String[] receiverData = s.split("\t");
    		receiverNameText.setText(receiverData[0]);
    		receiverPhoneText.setText(receiverData[1].toString());
    		receiverAddressText.setText(receiverData[2].toString());
    		deliveryHbox.setVisible(true);
    		switch(order.getOrderStatus()) {
	        	case "CONFIREMED":
	        		deliveryStatusText.setText("Confirmed");
	        		deliveryStatusText.setFill(Color.GREEN);
	        		break;
	        	case "CANCELED":
	        		deliveryStatusText.setText("Canceled");
	        		deliveryStatusText.setFill(Color.RED);
	        		break;
	        	case "WAITING_FOR_CANCELATION":
	        		deliveryStatusText.setText("Waiting for cancelation");
	        		deliveryStatusText.setFill(Color.ORANGE);
	        		break;
	        	case "WAITING_FOR_CONFIRMATION":
	        		deliveryStatusText.setText("Waiting for confirmation");
	        		deliveryStatusText.setFill(Color.ORANGE);
	        		break;
	        	case "COMPLETED":
	        		deliveryStatusText.setText("Completed");
	        		deliveryStatusText.setFill(Color.DARKGREEN);
	        		break;
        	}
    	}
    	else {
    		deliveryHbox.setVisible(false);
    		pickupVbox.setVisible(true);
    		storeNameText.setText(order.getFromStore());
    		storeNameText.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 20));
    		storeNameText.setFill(Color.GREEN);
        	switch(order.getOrderStatus()) {
	        	case "CONFIREMED":
	        		statusText.setText("Confirmed");
	        		statusText.setFill(Color.GREEN);
	        		break;
	        	case "CANCELED":
	        		statusText.setText("Canceled");
	        		statusText.setFill(Color.GREEN);
	        		break;
	        	case "WAITING_FOR_CANCELATION":
	        		statusText.setText("Waiting for cancelation");
	        		statusText.setFill(Color.ORANGE);
	        		break;
	        	case "WAITING_FOR_CONFIRMATION":
	        		statusText.setText("Waiting for confirmation");
	        		statusText.setFill(Color.ORANGE);
	        		break;
	        	case "COMPLETED":
	        		deliveryStatusText.setText("Completed");
	        		deliveryStatusText.setFill(Color.DARKGREEN);
	        		break;
        	}
    	}
    	
//    	show items and products of selected order
    	int i=0;
    	for (AbstractProduct ap : orderHistoryAll) {
    		Text name = new Text(ap.getName());
    		ImageView image = new ImageView(new Image(getClass().getResourceAsStream(ap.getImagePath()),100,100,false,false));
    		VBox vbox1 = new VBox(name,image);
    		VBox vbox2=null;
    		Text itemOrProduct=null, type=null,price=null,color=null,amount = null, madeFrom=null;
    		
    		type = new Text("Type: "+ap.getType());
    		price = new Text("Price: "+ Double.toString(ap.getPrice())+"$");
    		amount = new Text("Amount: " + Integer.toString(ap.getAmount()));
    		
    		name.autosize();
	    	type.autosize();
	    	price.autosize();
    		amount.autosize();
    		
    		name.setFont(Font.font(null,FontWeight.EXTRA_BOLD,FontPosture.REGULAR,20));
    		type.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    		price.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    		amount.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
	    		
    		if (ap instanceof Product) {
    			itemOrProduct = new Text("A Premade Product");
    			Product product = (Product)ap;
    			madeFrom = new Text("Made from the items:\n"+product.getMadeFrom().toString());
    			madeFrom.autosize();
    			madeFrom.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    			vbox2 = new VBox(itemOrProduct,type,price, amount, madeFrom);
    		}
    		else if(ap instanceof Item){
    			Item item = (Item)ap;
    			itemOrProduct = new Text("A Premade Item");
    			color = new Text("Color: " + item.getColor());
    			color.autosize();
    			color.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    			vbox2 = new VBox(itemOrProduct, type, price,amount,color);
    		} 
    		else if(ap instanceof CustomProduct) {
    			System.out.println("here");
    			CustomProduct custom = (CustomProduct)ap;
    			itemOrProduct = new Text("A Custom Product");
    			color = new Text("Color: " + custom.getColor());
    			color.autosize();
    			color.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    			vbox2 = new VBox(itemOrProduct, type, price,amount,color);
    		}

    		itemOrProduct.autosize();
    		itemOrProduct.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    		
    		HBox hbox = new HBox(vbox1, vbox2);
    		hbox.setSpacing(15.0);
    		vbox1.setSpacing(5);
    		
    		vbox1.setPadding(new Insets(20));
    		vbox2.setPadding(new Insets(20));

    		vbox1.setAlignment(Pos.CENTER);
    		vbox2.setAlignment(Pos.CENTER);
    		
    		vbox1.setPrefWidth(260);
    		vbox2.setPrefWidth(260);
    		
    		hbox.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		hbox.autosize();
    		

//    		}
    		grid.add(hbox, 0, i++);
    	}
		grid.setHgap(10);
		grid.setVgap(10);
    }
}
