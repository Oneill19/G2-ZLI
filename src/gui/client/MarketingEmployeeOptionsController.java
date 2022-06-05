package gui.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MarketingEmployeeOptionsController {

    @FXML private Button onBack, logoutBtn, userOptBtn, exit, addSaleButton, watchSaleButton, nextBtn;
    
    @FXML
    private Button createProduct;

    @FXML
    private Button editProduct;
    
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
    void onNext(ActionEvent event) {

    }
    
    /**
     * move to the create product screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onCreateProduct(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "CreateProduct.fxml", "Zer-Li Create Product");
    }

    /**
     * move to the edit product screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onEditProduct(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "EditProduct.fxml", "Zer-Li Edit Product");
    }

}
