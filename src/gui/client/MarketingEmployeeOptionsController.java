package gui.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Dorin
 *
 */
public class MarketingEmployeeOptionsController {

    @FXML private Button onBack, logoutBtn, userOptBtn, exit, addSaleButton, watchSaleButton, createProduct, editProduct;
    
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

	
    @FXML
    void onAddSaleButton(ActionEvent event) throws IOException {
    	cc.changeFXML(event, "AddSale.fxml", "Zer-li Add Sale");
    }
    
    /**
     * initials ChatClient.saleArray field with sales from DB
     * @param event
     * @throws IOException
     */
    @FXML
    void onWatchSales(ActionEvent event) throws IOException {
    	cc.changeFXML(event, "WatchSales.fxml", "Zer-li Watch Sales");
    }
    

}
