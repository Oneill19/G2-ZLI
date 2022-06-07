
package gui.client;

import java.io.IOException;
import java.util.Map;

import client.ChatClient;
import entity.AbstractProduct;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * 
 * @author Dorin
 *
 */
public class CartController {
	//ObservableList<AbstractProduct> observableList;

	// ******
	// *FXML*
	// ******
	@FXML private TableView<AbstractProduct> cartTable;
	@FXML private TableColumn<AbstractProduct, String> colDelete,colName,minusAmountCol,plusAmountCol,amountCol;
	@FXML private TableColumn<AbstractProduct, ImageView> colImage;
	@FXML private TableColumn<AbstractProduct, Double> colPrice, colAfterSale;
	@FXML private TableColumn<AbstractProduct, Integer> labelAmountCol;
	@FXML private TextField textFieldPrice, textFieldSale;
	@FXML private Button exit, exitClient, logoutBtn, onBack, userOptBtn, nextBtn; 
	private Double totalPrice = new Double(0);
	private Double totalPriceAfterSale = new Double(0);
    
    private CommonController cc = new CommonController();

	@FXML
	void onBack(ActionEvent event) throws IOException {		
		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog");
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
    void onNext(ActionEvent event) throws IOException {
    	//check if cart is empty
    	if (ChatClient.customerCart.size() == 0) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Empty Cart");
    		alert.setHeaderText("Cart is empty.\nPlease add at least 1 product to continue.");
    		alert.showAndWait();
    		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog");
    		return;
    	}

    	if (ChatClient.firstOrder)
    		totalPriceAfterSale=totalPriceAfterSale-(totalPriceAfterSale*0.2);
    	ChatClient.cartOrder.setTotalPrice(totalPrice);
    	ChatClient.cartOrder.setTotalPriceWithSale(totalPriceAfterSale);
    	cc.changeFXML(event, "PersonalDetails.fxml", "Zer-Li Personal Details");
    }
	
	public void initialize() throws IOException {
		
		textFieldPrice.setId("myTextFieldPrice");
		textFieldPrice.setId("myTextFieldSale");
		//set image view of each product in cart
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		
		for (Map.Entry<AbstractProduct, Integer> ap : ChatClient.customerCart.entrySet()) {
    		ap.getKey().setImageView();
    	}

		// Adding the Button to the cell
		colDelete.setCellFactory(
				new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() { 
					@Override
					public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
						return new MyButtons().createDeleteButtonForAbstractItem("Delete Item");
					}
				});

		// Adding the Button to the cell
		minusAmountCol.setCellFactory(
				new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {
					@Override
					public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
						return new MyButtons().createRegularButtonForAbstractItem("Minus");
					}
				});

		// Adding the Button to the cell
		plusAmountCol.setCellFactory(
				new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {
					@Override
					public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
						return new MyButtons().createRegularButtonForAbstractItem("Plus");
					}
				});
									
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		colImage.setPrefWidth(100);
		colImage.setCellValueFactory(new PropertyValueFactory<>("imageView"));
		
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colAfterSale.setCellValueFactory(new PropertyValueFactory<>("priceWithSale"));
		
		labelAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		cartTable.setId("my-table");
		cartTable.getItems().clear();
		cartTable.autosize();
		cartTable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));

		//initial TextField:totalPrice and labelamountCol
		for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
			totalPrice += entry.getKey().getPrice()*entry.getValue();
			totalPriceAfterSale += entry.getKey().getPriceWithSale()*entry.getValue();
			entry.getKey().setAmount(entry.getValue());
		}
		textFieldPrice.setText(totalPrice.toString());
		if(ChatClient.firstOrder)
			totalPriceAfterSale=totalPriceAfterSale-(totalPriceAfterSale*0.2);
		textFieldSale.setText(totalPriceAfterSale.toString());
		
	}
}