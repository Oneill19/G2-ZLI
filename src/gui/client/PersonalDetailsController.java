package gui.client;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import client.ChatClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PersonalDetailsController {

	@FXML
	private ToggleGroup DeliveryMethod;

	@FXML
	private RadioButton DeliveryRadio;

	@FXML
	private ComboBox<?> comboStore;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button exit;

	@FXML
	private TextField fieldAptNumber;

	@FXML
	private TextField fieldBlessing;

	@FXML
	private TextField fieldCity;

	@FXML
	private TextField fieldEmail;

	@FXML
	private TextField fieldFirst;

	@FXML
	private TextField fieldLast;

	@FXML
	private TextField fieldPostal;

	@FXML
	private TextField fieldSt;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button onBack;

	@FXML
	private RadioButton pickUpRadio;

	@FXML
	private TextField timePicker;

	@FXML
	private Button userOptBtn;

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

	@FXML
	void onAptNumber(ActionEvent event) {

	}

	@FXML
	void onBack(ActionEvent event) {

	}

	@FXML
	void onBlessing(ActionEvent event) {

	}

	@FXML
	void onCity(ActionEvent event) {

	}

	@FXML
	void onDatePicker(ActionEvent event) {

	}

	@FXML
	void onDelivery(ActionEvent event) {

	}

	@FXML
	void onEmail(ActionEvent event) {

	}

	@FXML
	void onExit(ActionEvent event) {

	}

	@FXML
	void onFirst(ActionEvent event) {

	}

	@FXML
	void onLast(ActionEvent event) {

	}

	@FXML
	void onLogout(ActionEvent event) {

	}

	@FXML
	void onPickUp(ActionEvent event) {

	}

	@FXML
	void onPostal(ActionEvent event) {

	}

	@FXML
	void onSt(ActionEvent event) {

	}

	@FXML
	void onStore(ActionEvent event) {

	}

	@FXML
	void onTimePicker(ActionEvent event) {

	}

	public void initialize() {
		fieldEmail.setText(ChatClient.user.getEmail());
		fieldFirst.setText(ChatClient.user.getFirstName());
		fieldLast.setText(ChatClient.user.getLastName());
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
	}
}
