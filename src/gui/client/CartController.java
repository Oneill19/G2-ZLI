
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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
	@FXML private TableColumn<AbstractProduct, String> colDelete,colName,minusAmountCol,labelAmountCol,plusAmountCol,amountCol;
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
	
	public void initialize() throws IOException {		
		//set image view of each product in cart
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		
		for (Map.Entry<AbstractProduct, Integer> ap : ChatClient.customerCart.entrySet()) {
    		ap.getKey().setImageView();
    	}

		// create class DeleteButton
		class DeleteButton extends TableCell<AbstractProduct, String> {
			private Button deleteButton = null;

			DeleteButton() {
				deleteButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("delete.png"),50,50,false,true)));
				deleteButton.setStyle("-fx-background-color:transparent;");

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
						
						//new due amount
						totalPrice=0.0;
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice()*entry.getValue();
						}
						textFieldPrice.setText(totalPrice.toString());
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
		
		// Sets colDelete behavior
		colDelete.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<AbstractProduct, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractProduct, String> p) {
						return new SimpleStringProperty(p.getValue() != null, null);
					}
				});

		// Adding the Button to the cell
		colDelete.setCellFactory(
				new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {

					@Override
					public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
						return new DeleteButton();
					}

				});
		
		
		
		
		
		//******************
		//***AmountColumn***
		//******************		
		// create class DeleteButton
				class minusAmountButton extends TableCell<AbstractProduct, String> {
					private Button minusAmountButton = null;

					minusAmountButton() {
						minusAmountButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("minusAmount2.png"))));
						minusAmountButton.setStyle("-fx-background-color:transparent;");

						// Action when the button is pressed
						minusAmountButton.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent t) {
								// get Selected Item
								AbstractProduct currentAbstractProduct = (AbstractProduct) minusAmountButton.this.getTableView()
										.getItems().get(minusAmountButton.this.getIndex());
								//remove one product from cart
								ChatClient.customerCart.put(currentAbstractProduct, ChatClient.customerCart.get(currentAbstractProduct)-1);															
								
								//new due amount
								totalPrice=0.0;
								for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
									totalPrice += entry.getKey().getPrice()*entry.getValue();
								}
								textFieldPrice.setText( Double.toString(totalPrice-currentAbstractProduct.getPrice()));
								textFieldPrice.setText(totalPrice.toString());
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
							setGraphic(minusAmountButton);
						}
					}
				}
				
				// Sets colDelete behavior
				minusAmountCol.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<AbstractProduct, String>, ObservableValue<String>>() {

							@Override
							public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractProduct, String> p) {
								return new SimpleStringProperty(p.getValue() != null, null);
							}
						});

				// Adding the Button to the cell
				minusAmountCol.setCellFactory(
						new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {

							@Override
							public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
								return new minusAmountButton();
							}

						});
		
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colName.getStyle().concat("-fx-alignment: CENTER;");
		colImage.setPrefWidth(100);
		colImage.getStyle().concat("-fx-alignment: CENTER;");
		colImage.setCellValueFactory(new PropertyValueFactory<>("imageView"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		colPrice.getStyle().concat("-fx-alignment: CENTER;");
		amountCol.getStyle().concat("-fx-alignment: CENTER;");
		
		cartTable.setId("my-table");

		cartTable.getItems().clear();
		cartTable.autosize();
		cartTable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));

		for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
			totalPrice += entry.getKey().getPrice()*entry.getValue();
		}
		textFieldPrice.setText(totalPrice.toString());
	}
}