package gui.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author DORIN
 *
 */
public class ApprovedCustomerOptionsController {

    @FXML
    private Button onBack,logoutBtn,userOptBtn,exit,watchOrdersButton,watchCatalogButton,orderNowButton;


    private CommonController cc = new CommonController();

	@FXML
	void onBack(ActionEvent event) throws IOException {		
		logoutBtn.fire();
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
	private void onWatchOrdersButton(ActionEvent event) throws IOException {
		cc.changeFXML(event, "CustomerOrderHistory.fxml", "Zer-Li Order History");
	}
	
	@FXML
	private void onWatchCatalogButton(ActionEvent event) throws IOException {
		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog");
	}

	@FXML
	private void onOrderNowButton(ActionEvent event) throws IOException {
		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog");
	}

}
