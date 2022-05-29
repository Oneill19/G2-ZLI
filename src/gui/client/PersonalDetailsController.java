package gui.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import client.ChatClient;
import client.ClientUI;
import entity.Store;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * @author DORIN BEERY
 *
 */
public class PersonalDetailsController {

	@FXML private ToggleGroup DeliveryMethod;
	@FXML private RadioButton DeliveryRadio, pickUpRadio;
	@FXML private ComboBox<Store> comboStore;
	@FXML private DatePicker datePicker;
	@FXML private Button exit, onBack, userOptBtn, nextBtn, logoutBtn;
	@FXML private TextField fieldAptNumber,fieldBlessing,fieldCity,fieldEmail,fieldFirst,fieldLast,fieldPostal,fieldSt,timePicker, fieldDescribtion; 
    @FXML private Label labelDestination, labelStore;
    @FXML private HBox HBoxAddress, HBoxstore;
	
	//Non FXML fields
	private CommonController cc = new CommonController();

	//settings for LocalTime and LocalDate
	private final String DefaultFormat = "HH:mm";
	@SuppressWarnings("unused")
	private DateTimeFormatter formatter;
	@SuppressWarnings("unused")
	private ObjectProperty<LocalDateTime> dateTimeValue = new SimpleObjectProperty<>(LocalDateTime.now());
	private ObjectProperty<String> format = new SimpleObjectProperty<String>() {
		public void set(String newValue) {
			super.set(newValue);
			formatter = DateTimeFormatter.ofPattern(newValue);
		}
	};
	
	 String fromStore;
	 Store selectedStore;

	//set onBackButton function
	@FXML void onBack(ActionEvent event) throws Exception{ 
		comboStore.getItems().clear();
		cc.changeFXML(event, "Cart.fxml", "Zer-Li Cart",null); 
		}

	@FXML void onExit(ActionEvent event) throws Exception{ cc.OnExit(); }

	@FXML void onLogout(ActionEvent event) throws Exception { cc.onLogout(event); }
	
	@FXML void onDelivery(ActionEvent event) 
	{
		HBoxAddress.setVisible(true);
		HBoxstore.setVisible(false);	
	}

	@FXML
	void onPickUp(ActionEvent event) {
		HBoxAddress.setVisible(false);
		HBoxstore.setVisible(true);	
	} 
	
	@FXML
	private void onNext(ActionEvent event) throws Exception{
		fromStore = pickUpRadio.isSelected() == false ? null : comboStore.getValue().getStoreName();
		if(!fromStore.equals(null))
			selectedStore = comboStore.getValue();
		String address = fieldCity.getText()+" "+fieldSt.getText()+" "+ fieldAptNumber.getText() +" "+fieldPostal.getText();
		
		//initial global values with local values
		ChatClient.cartOrder.setCustomerID(ChatClient.user.getUserID());
		ChatClient.cartOrder.setGreetingCard(fieldBlessing.getText());;
		//TODO - it is not clear what to do with field 'color'
		//ChatClient.cartOrder.setColor(color);
		ChatClient.cartOrder.setOrderDesc(fieldDescribtion.getText());
		ChatClient.cartOrder.setFromStore(fromStore); 
		//ChatClient.cartOrder.setPaymentMethod(); is from Payment.fxml
		ChatClient.cartOrder.setOrderStatus("WAITING_FOR_CONFIRMATION");
		ChatClient.cartOrder.setConfirmedDate(null);
		ChatClient.cartOrder.setCompleteDate(null);
		ChatClient.cartOrder.setDeliveryMethod(pickUpRadio.isSelected() == true ? address : comboStore.getValue().getStoreName());
		ChatClient.cartOrder.setOrderCreationDate(LocalDate.now());
		ChatClient.cartOrder.setOrderCreationTime( LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
		ChatClient.cartOrder.setSupplyDate(datePicker.getValue());
		ChatClient.cartOrder.setSuppplyTime(LocalTime.parse(timePicker.getText()));

		//debug
		System.out.println(ChatClient.cartOrder);
		comboStore.getItems().clear();
		cc.changeFXML(event, "Payment.fxml", "Zer-Li Payment",null);
	}

	public void initialize() throws Exception {
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		fieldEmail.setText(ChatClient.user.getEmail());
		fieldEmail.setDisable(true);
		fieldFirst.setText(ChatClient.user.getFirstName());
		fieldFirst.setDisable(true);
		fieldLast.setText(ChatClient.user.getLastName());
		fieldLast.setDisable(true);
		datePicker = new DateTimePicker();
		format.set(DefaultFormat);
		timePicker.setOnAction(evnt -> {
			try {
				timePicker.setText((LocalTime.parse(timePicker.getText())).toString());
				timePicker.setStyle("-fx-text-inner-color: black;"
						+ "-fx-focus-color: green;");
			} catch (Exception ex) {
				timePicker.clear();
				timePicker.setStyle("-fx-text-inner-color: red;"
						+ "-fx-focus-color:red;");
				timePicker.home();
			}
		});
		
		HBoxAddress.setVisible(false);
		HBoxstore.setVisible(false);
		ClientUI.chat.accept("GetAllStores");
		comboStore.setItems(FXCollections.observableArrayList(ChatClient.stores));
		//if back button was pressed from forward screens
		String hi = ChatClient.cartOrder.getDeliveryMethod();
		if ( hi != null ) {
			fieldBlessing.setText(ChatClient.cartOrder.getGreetingCard());
			fieldDescribtion.setText(ChatClient.cartOrder.getOrderDesc());
			datePicker.setValue(ChatClient.cartOrder.getSupplyDate());
			timePicker.setText(ChatClient.cartOrder.getSupplyTime().toString());
			if (ChatClient.cartOrder.getFromStore().equals(null)) {
				DeliveryRadio.setSelected(true);
				HBoxAddress.setVisible(true);
				HBoxstore.setVisible(false);
				String[] address = ChatClient.cartOrder.getDeliveryMethod().split(" ");
				fieldCity.setText(address[0]);
				fieldSt.setText(address[1]);
				fieldAptNumber.setText(address[2]);
				fieldPostal.setText(address[3]);
			}
			else {
				pickUpRadio.setSelected(true);
				HBoxAddress.setVisible(false);
				HBoxstore.setVisible(true);
				//TODO needs to be an object of type Store
				comboStore.setValue(selectedStore);
			}
			
		}
		
	}
}
