package gui.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MarketingEmployeeOptionsController {

    @FXML private Button onBack, logoutBtn, userOptBtn, exit, addSaleButton, watchSaleButton;
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
    void onAddSaleButton(ActionEvent event) throws IOException {
    	cc.changeFXML(event, "AddSale.fxml", "Zer-li Add Sale");
    }

}
