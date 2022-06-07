package gui.client;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Order;
import entity.Sale;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Class MyButtons() implements Custom-Made by user buttons to simplify code, make it reusable, and shorter.
 * @author Dorin
 *
 */
public class MyButtons extends Button {

	private CommonController cc = new CommonController();
	Button catalogButton;

	/**
	 * @return a styled button with purple background for regular clicks
	 * with setOnMouseEntered styling and setOnMouseExitStyling
	 * its' setOnAction changes from current fxml to Catalog.fxml
	 */
	public Button createRegularButton() {
		catalogButton = new Button("Catalog", new ImageView(new Image(getClass().getResourceAsStream("catalogIcon.png"), 37, 37, false, true)));
		catalogButton.setStyle("-fx-background-color:#a297d5;" + 
								"-fx-background-radius:50;" + 
								"-fx-border-radius:50;" + 
								"-fx-padding: 0 0 0 0;" + 
								"-fx-border-color:#aeaeae;");
		catalogButton.setPadding(new Insets(5, 0, 0, 5));
		catalogButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				try {
					cc.changeFXML(t, "Catalog.fxml", "Zer-li Catalog");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		catalogButton.setOnMouseEntered(new ButtonEventHandlerStyle.greenBackgroundOnEnter(catalogButton));
		catalogButton.setOnMouseExited(new ButtonEventHandlerStyle.greenBackgroundOnExit(catalogButton));

		return catalogButton;
	}

	/**
	 * @author Dorin
	 *
	 * @param <T> - The Object of TableCell
	 * @param <V>
	 */
	class DeleteButtonCell<T, V> extends TableCell<T, V> {
		private Button deleteButton = null;
		String comand;

		/**
		 * @param command for special setOnAction.handle() 
		 * Currently varies by "Cancel Order" and "Delete Item" functionalities.
		 */
		DeleteButtonCell(String command) {
			comand=command;
			if (command.equals("Cancel Order"))
				deleteButton = new Button("Cancel Order", new ImageView(
						new Image(getClass().getResourceAsStream("deleteOrder.png"), 40, 40, false, true)));
			else if (command.equals("Delete Item"))
				deleteButton = new Button("",
						new ImageView(new Image(getClass().getResourceAsStream("delete.png"), 40, 40, false, true)));
			else if(command.equals("Delete Sale"))
				deleteButton = new Button("DeActivate",
						new ImageView(new Image(getClass().getResourceAsStream("delete.png"), 40, 40, false, true)));

			deleteButton.setStyle("-fx-background-color:red;" + "-fx-background-radius:50;" + "-fx-border-radius:50;"
					+ "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
			deleteButton.setPadding(new Insets(5, 0, 0, 5));
			deleteButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					deleteButton.setStyle("-fx-border-radius:20.0;" + "-fx-background-radius:50;"
							+ "-fx-background-color: #EFE2FE;" + "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
				}
			});
			deleteButton.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					deleteButton.setStyle("-fx-background-color:red;" + "-fx-background-radius:50;"
							+ "-fx-border-radius:50;" + "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
				}
			});
			
			String refundRules = "Refund Information\n"
					+ "Full Refund: if supply time is more than 3 hours from now\n"
					+ "50% Refund: if supply time is between 3 to 1 hours from now\n"
					+ "No Refund: if supply time is less than 1 hour from now";
			
			//tooltip refund information
			if(command.equals("Cancel Order")) {
				Tooltip refundInfo = new Tooltip(refundRules);
				deleteButton.setTooltip(refundInfo);
			}
			
			// Action when the button is pressed
			deleteButton.setOnAction(new EventHandler<ActionEvent>() {
				
				private boolean setAlerts(Order order) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText(refundRules);
					alert.setTitle("Info");
					
					switch (order.getOrderStatus()) {
					case "CANCELED":
						alert.setHeaderText(""
								+ "Order "+order.getOrderNumber()+" was already canceled.");
						alert.showAndWait();
						return false;
					case "WAITING_FOR_CANCELATION":
						alert.setHeaderText(""
								+ "Order "+order.getOrderNumber()+" was already requested for cancellation.\n"
										+ "A store manager will soon address it.");
						alert.showAndWait();
						return false;
					case "COMPLETED":
						alert.setHeaderText("Order "+order.getOrderNumber()+" is already completed.\n"
								+ "for more information please consult with a store worker.");
						alert.showAndWait();
						return false;
					}
					
					int hourdiff = order.getSupplyTime().getHour() - LocalTime.now().getHour();
					alert.setHeaderText("There are "+Integer.toString(hourdiff)+ " hours untill the end of you're supply date.\n"
							+ "A store manager will soon address you're cancelation request.\n"
							+ "You will be informed by SMS and Mail.");
					alert.showAndWait();
					return true;
				}

				@SuppressWarnings("unchecked")
				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					T selected = DeleteButtonCell.this.getTableView().getItems().get(DeleteButtonCell.this.getIndex());
					
					if (command.equals("Cancel Order")) {
						Order selectedO = (Order)selected;
						
						//Check if changes are need to be made to order
						if (!setAlerts((Order)selectedO)) return;
						
						//Chnage orders' status
						String newStatus = "WAITING_FOR_CANCELATION";
						selectedO.setOrderStatus(newStatus);
						try {
							ClientUI.chat.accept("changeOrderStatus\t"+selectedO.getOrderNumber()+"\t"+newStatus);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						//insert datetime of order request for cancelation
						try {
							ClientUI.chat.accept("insertTo_order_cancelation\t"+Integer.toString(selectedO.getOrderNumber()));
						} catch (IOException e1) {
							System.out.println("problem inserting to order_cancelation");
							e1.printStackTrace();
						}
						if(!ChatClient.requestSucceed) {
							System.out.println("insertion to order_cancelation had a problem");
							return;
						}
							
						deleteButton.setOnMouseExited(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								deleteButton.setStyle("-fx-background-color:green;" + "-fx-background-radius:50;"
										+ "-fx-border-radius:50;" + "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
							}
						});
						deleteButton.setDisable(true);
						
						//Change display status
						Text text1 = (Text)((Node) t.getTarget()).getScene().lookup("#status1");
						text1.setText("Waiting for cancelation");
						Text text2 = (Text)((Node) t.getTarget()).getScene().lookup("#status2");
						text2.setText("Waiting for cancelation");
					}
					
					//If button is of deleting an item
					else if (command.equals("Delete Item")) {
						
						//Get screens' objects
						TableView<AbstractProduct> mytable =(TableView<AbstractProduct>)((Node) t.getTarget()).getScene().lookup("#my-table");
						TextField totalPriceField = (TextField)((Node) t.getTarget()).getScene().lookup("#myTextFieldPrice");
						TextField totalPriceSale =  (TextField)((Node) t.getTarget()).getScene().lookup("#myTextFieldSale");
							
						//set updates to table and label
						ChatClient.customerCart.remove(selected); // new due amount
						mytable.getItems().clear();
						mytable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));
						Double totalPrice = 0.0;
						Double totalSale = 0.0;
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice() * entry.getValue();
							totalSale +=entry.getKey().getPriceWithSale()*entry.getValue();
						}
						totalPriceField.setText(totalPrice.toString());
						if(ChatClient.firstOrder)
							totalSale=totalSale-(totalSale*0.2);
						totalPriceSale.setText(totalSale.toString());
					}
					
					//If button is of deActivate sale
					else if(command.equals("Delete Sale")) {
						
						//get Screens' objects
						Node lookup =((Node) t.getTarget()).getScene().lookup("#my-table");
						TableView<Sale> mytable;
						mytable = (TableView<Sale>)lookup;
						
						Sale sale=null;
						if (selected instanceof Sale)
							sale = (Sale)selected;
						//HCI
						deleteButton.setStyle("-fx-background-color:green;" + "-fx-background-radius:50;"
								+ "-fx-border-radius:50;" + "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
						deleteButton.setDisable(true);
						
						//check if sale is Active or not
						if(sale.getStatus().equals("NOT ACTIVE")) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Sale is Not Active");
							alert.setHeaderText("Sale #"+sale.getIdSale()+" is already Not Active.");
							alert.showAndWait();
							return;
						}
							
						//change sale's status
						try {
							ClientUI.chat.accept("updateSaleStatus\t"+sale.getIdSale()+"\t"+"NOT ACTIVE");
							sale.setStatus("NOT ACTIVE");
						} catch (IOException e) {
							System.out.println("Update status didn't work.");
							e.printStackTrace();
							return;
						}
						
						//change idSale of items that are currently with this sale
						try {
							ClientUI.chat.accept("nullifyIdSaleOfApWithCurrentIdSale\t"+Integer.toString(sale.getIdSale())+"\t"+"item");
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						//change idSale of products that are currently with this sale
						try {
							ClientUI.chat.accept("nullifyIdSaleOfApWithCurrentIdSale\t"+Integer.toString(sale.getIdSale())+"\t"+"product");
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						mytable.getItems().clear();
						mytable.setItems(FXCollections.observableArrayList(ChatClient.saleArray));
					}
				}
			});
		}

		// Display button if the row is not empty
		@Override
		public void updateItem(V t, boolean empty) {
			super.updateItem(t, empty);
			if (empty) {
				setGraphic(null);
				setText(null);
			}
			else {
				setGraphic(deleteButton);
			}
		}
	}
	
	
	/**
	 * @author Dorin
	 *
	 * @param <T> - the parameter to extend TabelCell
	 * @param <V>
	 */
	class RegularButtonCell<T, V> extends TableCell<T, V> {
		private Button regularBtn = null;

		RegularButtonCell(String command) {
			if (command.equals("Minus"))
				regularBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream("minusAmount2.png"), 30, 30, false, false)));
			else if (command.equals("Plus"))
				regularBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream("plusAmount2.png"), 30, 30, false, false)));

			regularBtn.setStyle("-fx-background-color:#a297d5;" + 
								"-fx-background-radius:50;" + 
								"-fx-border-radius:50;" + 
								"-fx-padding: 0 0 0 0;" + 
								"-fx-border-color:#aeaeae;");
			regularBtn.setPadding(new Insets(5, 0, 0, 5));
			regularBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					regularBtn.setStyle("-fx-border-radius:20.0;" + 
										"-fx-background-radius:50;" + 
										"-fx-background-color:#dad5ee;" + 
										"-fx-padding: 0 0 0 0;" + 
										"-fx-border-color:#aeaeae;");
				}
			});
			regularBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					regularBtn.setStyle("-fx-background-color:#a297d5;" + "-fx-background-radius:50;"
							+ "-fx-border-radius:50;" + "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
				}
			});

			// Action when the button is pressed
			regularBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					if (command.equals("Minus")) {
						AbstractProduct selectedproduct = (AbstractProduct) RegularButtonCell.this.getTableView().getItems()
								.get(RegularButtonCell.this.getIndex());
						
						if(ChatClient.customerCart.get(selectedproduct) == 1){
							ChatClient.customerCart.remove(selectedproduct); // new due amount
							
							//Add Screen objects
							@SuppressWarnings("unchecked")
							TableView<AbstractProduct> mytable = (TableView<AbstractProduct>) ((Node) t.getTarget()).getScene().lookup("#my-table");
							TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene().lookup("#myTextFieldPrice");
							TextField totalSaleField = (TextField) ((Node) t.getTarget()).getScene().lookup("#myTextFieldSale");
							
							ChatClient.customerCart.remove(selectedproduct); // new due amount
							mytable.getItems().clear();
							mytable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));// new due

							// new due amount
							Double totalPrice = 0.0;
							Double totalSale = 0.0;
							for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
								totalPrice += entry.getKey().getPrice() * entry.getValue();
								totalSale += entry.getKey().getPriceWithSale()* entry.getValue();
							}
							totalPriceField.setText(totalPrice.toString());
							if(ChatClient.firstOrder)
								totalSale=totalSale-(totalSale*0.2);
							totalSaleField.setText(totalSale.toString());
							return;
						}
						
						
						ChatClient.customerCart.put(selectedproduct, ChatClient.customerCart.get(selectedproduct) - 1);
						
						// new due amount
						Double totalPrice = 0.0;
						Double totalPriceSale=0.0;
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice() * entry.getValue();
							totalPriceSale +=entry.getKey().getPriceWithSale()*entry.getValue();
							entry.getKey().setAmount(entry.getValue());
						}
						
						TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene().lookup("#myTextFieldPrice");
						TextField totalSaleField = (TextField) ((Node) t.getTarget()).getScene().lookup("#myTextFieldSale");
						totalPriceField.setText(totalPrice.toString());
						if(ChatClient.firstOrder)
							totalPriceSale=totalPriceSale-(totalPriceSale*0.2);
						totalSaleField.setText(totalPriceSale.toString());

						@SuppressWarnings("unchecked")
						TableView<AbstractProduct> mytable = (TableView<AbstractProduct>) ((Node) t.getTarget())
								.getScene().lookup("#my-table");
						mytable.refresh();

					} else if (command.equals("Plus")) {
						AbstractProduct currentAbstractProduct = (AbstractProduct) RegularButtonCell.this.getTableView()
								.getItems().get(RegularButtonCell.this.getIndex());
						// add one product from cart
						ChatClient.customerCart.put(currentAbstractProduct,
								ChatClient.customerCart.get(currentAbstractProduct) + 1);

						// new due amount
						Double totalPrice = 0.0;
						Double totalSale = 0.0;
						TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene().lookup("#myTextFieldPrice");
						TextField totalSaleField = (TextField) ((Node) t.getTarget()).getScene().lookup("#myTextFieldSale");
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice() * entry.getValue();
							totalSale += entry.getKey().getPriceWithSale()*entry.getValue();
							entry.getKey().setAmount(entry.getValue());
						}
						totalPriceField.setText(totalPrice.toString());
						if(ChatClient.firstOrder)
							totalSale = totalSale-(totalSale*0.2);
						totalSaleField.setText(totalSale.toString());
						@SuppressWarnings("unchecked")
						TableView<AbstractProduct> mytable = (TableView<AbstractProduct>) ((Node) t.getTarget())
								.getScene().lookup("#my-table");
						mytable.refresh();
					}
				}
			});
		}

		// Display button if the row is not empty
		@Override
		public void updateItem(V t, boolean empty) {
			super.updateItem(t, empty);
			if (empty) {
				setGraphic(null);
				setText(null);
			} else {
				setGraphic(regularBtn);
			}
		}
	}

	/**
	 * @return a styled button with red background for deletions,
	 * with setOnMouseEntered styling and setOnMouseExitStyling.
	 * It is ment for creating buttons that implement TableCell[Order,String]
	 */
	public DeleteButtonCell<Order, String> createDeleteButtonForTable() {
		return new DeleteButtonCell<Order, String>("Cancel Order");
	}

	/**
	 * @return a styled button with red background for deletions,
	 * with setOnMouseEntered styling and setOnMouseExitStyling.
	 * It is ment for creating buttons that implement TableCell[AbstractProduct,String]
	 */
	public DeleteButtonCell<AbstractProduct, String> createDeleteButtonForAbstractItem(String command) {
		return new DeleteButtonCell<AbstractProduct, String>(command);
	}
	
	/**
	 * @return a styled button with red background for deletions,
	 * with setOnMouseEntered styling and setOnMouseExitStyling.
	 * It is ment for creating buttons that implement TableCell[Sale,String]
	 */
	public DeleteButtonCell<Sale, String> createDeleteButtonForSale(String command) {
		return new DeleteButtonCell<Sale, String>(command);
	}
	
	/**
	 * @return a styled button with purple background for updates,
	 * with setOnMouseEntered styling and setOnMouseExitStyling.
	 * It is ment for creating buttons that implement TableCell[AbstractProduct,String]
	 */
	public RegularButtonCell<AbstractProduct,String> createRegularButtonForAbstractItem(String command){
		return new RegularButtonCell<AbstractProduct, String>(command);
	}
}
