package controllers;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditScreenController {

	@FXML
	private Button cancelEdit;

	@FXML
	private TextField colorField;

	@FXML
	private TextField dateField;

	@FXML
	private Button edit;
	
	@FXML
	private Button exitClient;

	@FXML
	private TextField orderNumberField;

	@FXML
	private Label errorLabel;

	public String getOrderNumber() {
		return orderNumberField.getText().trim();
	}

	public String getColor() {
		return colorField.getText().trim();
	}

	public String getDate() {
		return dateField.getText().trim() + ":00";
	}

	@FXML
	public void onClickEdit(ActionEvent event) throws Exception {
		errorLabel.setText("");
		errorLabel.setTextFill(Color.color(1, 0, 0));
		if (getOrderNumber().equals("") || getColor().equals("") || getDate().equals("")) {
			errorLabel.setText("Must enter all fields!");
			return;
		}
		if (!getDate().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") && !getDate().equals("")) {
			errorLabel.setText("please enter string in {yyyy/mm/dd hh:mm} format!");
			return;
		}
		System.out.println(getDate());
		String result = String.format("Update\t%s\t%s\t%s", getOrderNumber(), getColor(), getDate());
		ClientUI.chat.accept(result);
		errorLabel.setText("Success");
		errorLabel.setTextFill(Color.color(0, 1, 0));
	}

	@FXML
	public void onCancel(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Pane root = new FXMLLoader().<Pane>load(getClass().getResource("/controllers/OptionsScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void onExit(ActionEvent event) throws Exception {
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

}
