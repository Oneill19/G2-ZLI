
package gui.client;

import java.io.IOException;
import java.util.Map;

import client.ChatClient;
import entity.AbstractProduct;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
	@FXML private TableColumn<AbstractProduct, String> colDelete,colName,minusAmountCol,labelAmountCol,plusAmountCol;
	@FXML private TableColumn<AbstractProduct, ImageView> colImage;
	@FXML private TableColumn<AbstractProduct, Double> colPrice;
	@FXML private TextField textFieldPrice;
	@FXML private Button exit, exitClient, logoutBtn, onBack, userOptBtn, nextBtn; 
	private Double totalPrice = new Double(0);
    
    private CommonController cc = new CommonController();

	@FXML
	void onBack(ActionEvent event) throws IOException {		
		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog",null);
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
    	ChatClient.cartOrder.setTotalPrice(totalPrice);
    	cc.changeFXML(event, "PersonalDetails.fxml", "Zer-Li Personal Details",null);
    }
	
	public void initialize() {
		//set image view of each product in cart
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		
//		for (AbstractProduct ap : ChatClient.customerCart) {
//			System.out.println(ap.getName());
//			ap.setImageView();
//		}
		
		for (Map.Entry<AbstractProduct, Integer> ap : ChatClient.customerCart.entrySet()) {
    		ap.getKey().setImageView();
    	}

		// Sets colDelete behavior
		colDelete.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<AbstractProduct, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractProduct, String> p) {
						return new SimpleStringProperty(p.getValue() != null, null);
					}
				});

		// create class DeleteButton
		class DeleteButton extends TableCell<AbstractProduct, String> {
			private Button deleteButton = null;

			DeleteButton() {
				deleteButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("delete.png"))));

				// Action when the button is pressed
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {
						// get Selected Item
						AbstractProduct currentAbstractProduct = (AbstractProduct) DeleteButton.this.getTableView()
								.getItems().get(DeleteButton.this.getIndex());
						//remove the product from cart
//						ChatClient.cart.remove(currentAbstractProduct);
						ChatClient.customerCart.remove(currentAbstractProduct); //new due amount
						
						//refresh tableview
						cartTable.getItems().clear();
//						cartTable.setItems(FXCollections.observableArrayList(ChatClient.cart));
						cartTable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));//new due amount
						
						//update the new totalPrice 
//						for (AbstractProduct ap : ChatClient.cart)
//							totalPrice += ap.getPrice();
//						textFieldPrice.setText(totalPrice.toString());
//						}
						
						//new due amount
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice()*entry.getValue();
						}
					}
				});
			}

			// Display button if the row is not empty
			@Override
			public void updateItem(String t, boolean empty) {
				super.updateItem(t, empty);
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					setGraphic(deleteButton);
				}
			}
		}

		// Adding the Button to the cell
		colDelete.setCellFactory(
				new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {

					@Override
					public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
						return new DeleteButton();
					}

				});


		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colImage.setPrefWidth(100);
		colImage.setCellValueFactory(new PropertyValueFactory<>("imageView"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		cartTable.setId("my-table");

		cartTable.getItems().clear();
		cartTable.autosize();
		cartTable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));

//		for (AbstractProduct ai : ChatClient.cart)
//			totalPrice += ai.getPrice();
		//new due amount
		for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
			totalPrice += entry.getKey().getPrice()*entry.getValue();
		}
		textFieldPrice.setText(totalPrice.toString());
	}
}