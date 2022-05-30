package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Item;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PaymentController {

    @FXML private PasswordField cvv, first4, fourth4, second4, third4; 
    @FXML private TextField day, fullName, month; 
    @FXML private Button exit, logoutBtn, nextBtn, onBack, userOptBtn;
    
    private CommonController cc = new CommonController();
    
    @FXML
	void onBack(ActionEvent event) throws IOException {		
		cc.changeFXML(event, "PersonalDetails.fxml", "Zer-Li Personal Details",null);
	}

    @FXML
	void onExit(ActionEvent event) throws Exception {
		cc.OnExit();
	}

	@FXML
	void onLogout(ActionEvent event) throws Exception {
		cc.onLogout(event);
	}

    @FXML
    void onNext(ActionEvent event) {
    	//TODO - does payment method is always credit card?
    	ChatClient.cartOrder.setPaymentMethod("Credit Card");
    	
    	//Add Order to DB
    	try {
    		ClientUI.chat.accept("AddOrderToDB\t"+ChatClient.cartOrder.DBToString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//Send data for managing reports every 30 days.
    	ArrayList<String> cartItems=new ArrayList<String>();
    	Integer itemCounter=0;
    	ArrayList<String> cartProduct=new ArrayList<String>();
    	Integer productCounter=0;
    	Double sumPrice=new Double(0);
    	Integer totalAmount=0;
    	for(AbstractProduct ap : ChatClient.cart) {
    		if (ap instanceof Product) {
    			productCounter++;
    			cartProduct.add(ap.getName());
    		}
    		if(ap instanceof Item) {
    			itemCounter++;
    			cartItems.add(ap.getName());
    		}
    		sumPrice+=ap.getPrice();
    		totalAmount++;
    	}    	
    	try {
			ClientUI.chat.accept("numberOfItemsInOrder\t"+productCounter.toString()+"\t"+itemCounter.toString()+
					"\t"+sumPrice.toString()+"\t"+totalAmount.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Order reception completed SUCCESSFULY!");
		alert.setHeaderText("You're order " + ChatClient.cartOrder.getOrderNumber() + " was set and is waiting"
				+ " for confirmation within the next 3 hours.\nYou can check its' status at the Watch Orders option.");
			alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("orderReception.png"))));
		alert.showAndWait();
    	
    		
    	
    	//TODO check if date is before
		//TODO check if time is before
		//TODO show next options
    }
    
    public void initialize() {
    	fullName.setText(ChatClient.user.getFirstName()+" "+ChatClient.user.getLastName());
    	String[] creditCard = new String[4]; 
    	try {
    	creditCard[0] = ChatClient.user.getCreditCard().subSequence(0, 4).toString();
    	creditCard[1] = ChatClient.user.getCreditCard().subSequence(4, 8).toString();
    	creditCard[2] = ChatClient.user.getCreditCard().subSequence(8, 12).toString();
    	creditCard[3] = ChatClient.user.getCreditCard().subSequence(12, 16).toString();
    	}catch(StringIndexOutOfBoundsException e) {
    		System.out.println("no valid credit card is saved in DB");
    	}
    	first4.setText(creditCard[0]);
    	second4.setText(creditCard[1]);
    	third4.setText(creditCard[2]);
    	fourth4.setText(creditCard[3]);
    }

}

