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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

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
	@FXML private TextField fieldAptNumber,fieldBlessing,fieldCity,fieldEmail,fieldFirst,fieldLast,fieldPostal,fieldSt,hourPicker,minutesPicker,fieldDescribtion, nameOfReciever, phoneOfReciever; 
    @FXML private Label labelDestination, labelStore;
    @FXML private HBox HBoxAddress, HBoxstore;
    private String deliveryData;
	
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
		cc.changeFXML(event, "Cart.fxml", "Zer-Li Cart"); 
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
	
	/**
	 * Get data from textFields and save them in CatClient.customerCart
	 * @param event
	 * @throws Exception
	 */
	@FXML
	private void onNext(ActionEvent event) throws Exception{
		boolean returnFlag=false;
		
		//check delivery method fields are initialed
		if (DeliveryMethod.getSelectedToggle() == null) {
			setAlert("Please select delivery method",null).showAndWait();
			return;
		}

		if (pickUpRadio.isSelected()) {
			//Pickup
			String store = comboStore.getValue();
			if (store == null) {
				setAlert("Click on the purple box:\nThen, select a store", "Error_ChooseDestinationStore.png").showAndWait();
				return;
			}
			ChatClient.cartOrder.setDeliveryMethod("Pickup");
			ChatClient.cartOrder.setFromStore(comboStore.getValue());
		}//Delivery
		if (!pickUpRadio.isSelected()) {
			
			if (fieldCity.getText() == null ||  fieldCity.getText().trim().isEmpty()) {
				fieldCity.setStyle(" -fx-text-box-border:red;");
				returnFlag=true;
			}
			
			if (fieldSt.getText() == null ||  fieldSt.getText().trim().isEmpty()) {
				fieldSt.setStyle(" -fx-text-box-border:red;");
				returnFlag=true;
			}
			
			if (fieldAptNumber.getText() == null ||  fieldAptNumber.getText().trim().isEmpty()) {
				fieldAptNumber.setStyle(" -fx-text-box-border:red;");
				returnFlag=true;
			}
			
			if (nameOfReciever.getText() == null ||  nameOfReciever.getText().trim().isEmpty()) {
				nameOfReciever.setStyle(" -fx-text-box-border:red;");
				returnFlag=true;
			}
			
			if (phoneOfReciever.getText() == null ||  phoneOfReciever.getText().trim().isEmpty()) {
				phoneOfReciever.setStyle( "-fx-text-box-border:red;");
				returnFlag=true;
			}
			
			if (fieldPostal.getText() == null ||  fieldPostal.getText().trim().isEmpty()) {
				fieldPostal.setStyle( "-fx-text-box-border:red;");
				returnFlag=true;
			}
			
	
			if (returnFlag) {
				setAlert("Please fill all address and reciever fields", null).showAndWait();
				return;
			}
			
			ChatClient.cartOrder.setFromStore(null);
			
			//build data for delivery
			StringBuilder sb = new StringBuilder();
			sb.append("'").append(nameOfReciever.getText()).append("', ");
			sb.append("'").append(phoneOfReciever.getText()).append("', ");
			sb.append("'").append(fieldCity.getText()).append("' ");
			sb.append("'").append(fieldSt.getText()).append("' ");
			sb.append("'").append(fieldAptNumber.getText()).append("' ");
			sb.append("'").append(fieldPostal.getText()).append("' ");
			deliveryData = sb.toString();
			ChatClient.cartOrder.setDeliveryMethod("Delivery");
		}
		
		//check date time fields are initialed 
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
				
		//set values of customerCart
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
		
		//move info between screens
		Parent root=null;
		Stage stage=null;
		Scene scene=null;
		
		PaymentController paymentController=null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
		root = loader.load();
		paymentController = loader.getController();
		if (deliveryData!=null) {
			paymentController.getData(deliveryData);
		}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
		
		/**
		 * causes TextField to change its' border to transparent when gets focus
		 */
		class myListener implements ChangeListener<Boolean>{
			TextField tf;
			
			public myListener(TextField tf) {
				this.tf = tf;
			}
			
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean entered) {
				if(entered) {
					tf.setStyle("-fx-text-box-border:transparent;");
				}
			}
		}
		
		nameOfReciever.focusedProperty().addListener(new myListener(nameOfReciever));
		fieldAptNumber.focusedProperty().addListener(new myListener(fieldAptNumber));
		fieldCity.focusedProperty().addListener(new myListener(fieldCity));
		fieldPostal.focusedProperty().addListener(new myListener(fieldPostal));
		fieldSt.focusedProperty().addListener(new myListener(fieldSt));
		phoneOfReciever.focusedProperty().addListener(new myListener(phoneOfReciever));
		
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
