package gui.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import client.ChatClient;
import client.ClientUI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author DORIN BEERY
 *
 */
public class PersonalDetailsController {

	@FXML private ToggleGroup DeliveryMethod;
	@FXML private RadioButton DeliveryRadio, pickUpRadio;
	@FXML private ComboBox<String> comboStore;
	@FXML private DatePicker datePicker;
	@FXML private Button exit, onBack, userOptBtn, nextBtn, logoutBtn;
	@FXML private TextField fieldAptNumber,fieldBlessing,fieldCity,fieldEmail,fieldFirst,fieldLast,fieldPostal,fieldSt,hourPicker,minutesPicker, fieldDescribtion; 
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
		String address=null;
		
		if (DeliveryMethod.getSelectedToggle() == null) {
			setAlert("Please select delivery method",null).showAndWait();
			return;
		}

		if (pickUpRadio.isSelected()) {
			String store = comboStore.getValue();
			if (store == null) {
				setAlert("Click on the purple box:\nThen, select a store", "Error_ChooseDestinationStore.png").showAndWait();
				return;
			}
			ChatClient.cartOrder.setDeliveryMethod("Self Pickup");
			ChatClient.cartOrder.setFromStore(comboStore.getValue());
		}
		else {
			if (fieldCity.getText() == null || fieldSt.getText() == null || fieldAptNumber.getText() == null) {
				setAlert("Please fill all address fields", null).showAndWait();
				return;
			}
			ChatClient.cartOrder.setFromStore(null);
			address = fieldCity.getText()+" "+fieldSt.getText()+" "+ fieldAptNumber.getText() +" "+fieldPostal.getText();
			ChatClient.cartOrder.setDeliveryMethod(address);
		}
		
		LocalDate today = LocalDate.now();
		datePicker.setFocusTraversable(true);
		datePicker.requestFocus();
		if (datePicker.getValue() == null ) {
			setAlert("Select a date from the date picker", null).showAndWait();
			return;
		}else if(datePicker.getValue().compareTo(today) < 0) {
			setAlert("Please select future date", null).showAndWait();
			datePicker.setValue(today);
			datePicker.setStyle("-fx-border-color : red;");
			return;
		}else {
			datePicker.setStyle("-fx-text-color: green;");
			datePicker.setStyle("-fx-border-color : green;");
			ChatClient.cartOrder.setSupplyDate(datePicker.getValue());
		}
		
		
		if(hourPicker.getText().equals("") || minutesPicker.getText().equals("")) {
			setAlert("Insert time of order to be supplied",null).showAndWait();
			return;
		}
		else {
			LocalTime selectedSupplyTime = LocalTime.parse(hourPicker.getText()+":"+minutesPicker.getText());
			try {
				ChatClient.cartOrder.setSuppplyTime(selectedSupplyTime);
			}catch(DateTimeParseException ex) {
				setAlert("Please select valid time", null).showAndWait();
				datePicker.setValue(today);
				datePicker.setStyle("-fx-border-color : red;");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		
		}
					
		ChatClient.cartOrder.setCustomerID(ChatClient.user.getUserID());
		ChatClient.cartOrder.setGreetingCard(fieldBlessing.getText());;
		//TODO - it is not clear what to do with field 'color'
		//ChatClient.cartOrder.setColor(color);
		ChatClient.cartOrder.setOrderDesc(fieldDescribtion.getText());
		ChatClient.cartOrder.setOrderStatus("WAITING_FOR_CONFIRMATION");
		ChatClient.cartOrder.setConfirmedDate(null);
		ChatClient.cartOrder.setCompleteDate(null);
		ChatClient.cartOrder.setOrderCreationDate(LocalDate.now());
		ChatClient.cartOrder.setOrderCreationTime( LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

		comboStore.getItems().clear();
		cc.changeFXML(event, "Payment.fxml", "Zer-Li Payment",null);
		
	}
	
	
	/**
	 * @param errDesc		error to show on the alert box
	 * @param imagePath		an image to attach the alert
	 * @return				Alert object
	 */
	public Alert setAlert(String errDesc, String imagePath) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText(errDesc);
		if(imagePath != null) 
			alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(imagePath))));
		return alert;
	}

	public void initialize() throws Exception {
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		fieldEmail.setText(ChatClient.user.getEmail());
		fieldEmail.setDisable(true);
		fieldFirst.setText(ChatClient.user.getFirstName());
		fieldFirst.setDisable(true);
		fieldLast.setText(ChatClient.user.getLastName());
		fieldLast.setDisable(true);
		format.set(DefaultFormat);
		
		/**
		 * Class for catching my special request for Exception.
		 * Is intended for setAlert and setStyle
		 */
		@SuppressWarnings("serial")		
		class TimeBoundaryException extends Exception{
		}
		
		//Occurs when TextField looses focus 
		hourPicker.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(!arg2) { //if focus is lost
					String hour = hourPicker.getText();
					try {
						Integer inthour = Integer.parseInt(hour); //throws exception when @hour is not a parsable number
						if(inthour > 24 || inthour < 0) //throw exception if hour is out of range
							throw new TimeBoundaryException(); 
						hourPicker.setStyle("-fx-text-inner-color: green;" + "-fx-focus-color: green;");
					}catch(NumberFormatException ex) {
						setAlert("Insert only numbers", null).showAndWait();
						hourPicker.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
					}catch(TimeBoundaryException ex) {
						setAlert("hours can be only between 0 to 24", null).showAndWait();
						hourPicker.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");	
					}catch(Exception ex) {
						ex.printStackTrace();
						hourPicker.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
					}
				}
			}});

		//Occurs when TextField looses focus 
		minutesPicker.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if(!arg2) {
					String minutes = minutesPicker.getText();
					try {
						Integer intminutes = Integer.parseInt(minutes);
						if(intminutes > 60 || intminutes < 0) { throw new TimeBoundaryException(); }
						minutesPicker.setStyle("-fx-text-inner-color: green;" + "-fx-focus-color: green;");
					}catch(NumberFormatException ex) {
						setAlert("Insert only numbers", null).showAndWait();
						minutesPicker.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
					}catch(TimeBoundaryException ex) {
						setAlert("minutes can be only between 0 to 60", null).showAndWait();
						minutesPicker.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
					}catch(Exception ex) {
						ex.printStackTrace();
						minutesPicker.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
					}
			}}});
		
		HBoxAddress.setVisible(false);
		HBoxstore.setVisible(false);
		ClientUI.chat.accept("GetAllStores");
		comboStore.setItems(FXCollections.observableArrayList(ChatClient.stores));
		
		//if back button was pressed from forward screens
		if ( ChatClient.cartOrder.getDeliveryMethod() != null) {
			
			fieldBlessing.setText(ChatClient.cartOrder.getGreetingCard());
			fieldDescribtion.setText(ChatClient.cartOrder.getOrderDesc());
			datePicker.setValue(ChatClient.cartOrder.getSupplyDate());
			//timePicker.setText(ChatClient.cartOrder.getSupplyTime().toString());
			
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
				System.out.println(ChatClient.cartOrder.getFromStore());
				comboStore.setValue(ChatClient.cartOrder.getFromStore());
			}
			
		}
		
	}
}
