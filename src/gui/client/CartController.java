
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	@FXML private TableColumn<AbstractProduct, Double> colPrice;
	@FXML private TableColumn<AbstractProduct, Integer> labelAmountCol;
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
    	//check if cart is empty
    	if (ChatClient.customerCart.size() == 0) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Empty Cart");
    		alert.setHeaderText("Cart is empty.\nPlease add at least 1 product to continue.");
    		alert.showAndWait();
    		cc.changeFXML(event, "Catalog.fxml", "Zer-Li Catalog",null);
    		return;
    	}
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
				deleteButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("delete.png"),40,40,false,true)));
				deleteButton.setStyle("-fx-background-color:transparent;");
				deleteButton.setOnMouseEntered( new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent e) {
			           deleteButton.setStyle("-fx-border-radius:20.0;" +"border-radius: 5;" + 
			          "-fx-background-color: #EFE2FE;"+"-fx-padding: 0 0 0 0;");
			          }
			        });
				deleteButton.setOnMouseExited( new EventHandler<MouseEvent>() {
			          @Override
			          public void handle(MouseEvent e) {
			        	  deleteButton.setStyle("-fx-background-color:transparent;");
			          }
			        });
				
				// Action when the button is pressed
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {
						// get Selected Item
						AbstractProduct currentAbstractProduct = (AbstractProduct) DeleteButton.this.getTableView()
								.getItems().get(DeleteButton.this.getIndex());
						//remove the product from cart
						ChatClient.customerCart.remove(currentAbstractProduct); //new due amount
						
						//refresh tableview
						cartTable.getItems().clear();
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
		// create class minusAmountButton
				class minusAmountButton extends TableCell<AbstractProduct, String> {
					private Button minusAmountButton = null;

					minusAmountButton() {
						minusAmountButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("minusAmount2.png"),30,30,false,false)));
						minusAmountButton.setStyle("-fx-background-color:transparent;");
						minusAmountButton.setOnMouseEntered( new EventHandler<MouseEvent>() {
					          @Override
					          public void handle(MouseEvent e) {
					        	  minusAmountButton.setStyle("-fx-background-color: #EFE2FE;"+"-fx-padding: 0 0 0 0;");
					        	  //+"-fx-padding: 0 0 0 0;"
					          }
					        });
						minusAmountButton.setOnMouseExited( new EventHandler<MouseEvent>() {
					          @Override
					          public void handle(MouseEvent e) {
					        	  minusAmountButton.setStyle("-fx-background-color:transparent;");
					          }
					        });

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
									entry.getKey().setAmount(entry.getValue());
								}
								textFieldPrice.setText( Double.toString(totalPrice-currentAbstractProduct.getPrice()));
								textFieldPrice.setText(totalPrice.toString());
								cartTable.refresh();
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
				
				
				
				
				
				
				
				
				
				
				
				// create class plusAmountButton				
				class PlusAmountButton extends TableCell<AbstractProduct, String> {
					private Button plusAmountButton = null;

					PlusAmountButton() {
						plusAmountButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("plusAmount2.png"),30,30,false,false)));
						plusAmountButton.setStyle("-fx-background-color:transparent;");
						plusAmountButton.setOnMouseEntered( new EventHandler<MouseEvent>() {
					          @Override
					          public void handle(MouseEvent e) {
					        	  plusAmountButton.setStyle( "-fx-background-color: #EFE2FE;"+"-fx-padding: 0 0 0 0;");
					        	  //+"-fx-padding: 0 0 0 0;"
					          }
					        });
						plusAmountButton.setOnMouseExited( new EventHandler<MouseEvent>() {
					          @Override
					          public void handle(MouseEvent e) {
					        	  plusAmountButton.setStyle("-fx-background-color:transparent;");
					          }
					        });

						// Action when the button is pressed
						plusAmountButton.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent t) {
								// get Selected Item
								AbstractProduct currentAbstractProduct = (AbstractProduct) PlusAmountButton.this.getTableView()
										.getItems().get(PlusAmountButton.this.getIndex());
								//remove one product from cart
								ChatClient.customerCart.put(currentAbstractProduct, ChatClient.customerCart.get(currentAbstractProduct)+1);															
								
								//new due amount
								totalPrice=0.0;
								for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
									totalPrice += entry.getKey().getPrice()*entry.getValue();
									entry.getKey().setAmount(entry.getValue());
								}
								textFieldPrice.setText( Double.toString(totalPrice-currentAbstractProduct.getPrice()));
								textFieldPrice.setText(totalPrice.toString());
								cartTable.refresh();
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
							setGraphic(plusAmountButton);
						}
					}
				}
				
				// Sets colDelete behavior
				plusAmountCol.setCellValueFactory(
						new Callback<TableColumn.CellDataFeatures<AbstractProduct, String>, ObservableValue<String>>() {

							@Override
							public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractProduct, String> p) {
								return new SimpleStringProperty(p.getValue() != null, null);
							}
						});

				// Adding the Button to the cell
				plusAmountCol.setCellFactory(
						new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {

							@Override
							public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
								return new PlusAmountButton();
							}
						});
									
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//		colName.getStyle().concat("-fx-alignment: CENTER;");
		
		colImage.setPrefWidth(100);
//		colImage.getStyle().concat("-fx-alignment: CENTER;");
		colImage.setCellValueFactory(new PropertyValueFactory<>("imageView"));
		
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//		colPrice.getStyle().concat("-fx-alignment: CENTER;");
		
		labelAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
//		labelAmountCol.getStyle().concat("-fx-alignment: CENTER;");
		
//		amountCol.getStyle().concat("-fx-alignment: CENTER;");
		
		cartTable.setId("my-table");
		cartTable.getItems().clear();
		cartTable.autosize();
		cartTable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));

		//initial TextField:totalPrice and labelamountCol
		for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
			totalPrice += entry.getKey().getPrice()*entry.getValue();
			entry.getKey().setAmount(entry.getValue());
		}
		textFieldPrice.setText(totalPrice.toString());
	}
}