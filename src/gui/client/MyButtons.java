package gui.client;

import java.io.IOException;
import java.util.Map;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

		catalogButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				catalogButton.setStyle("-fx-border-radius:20.0;" + 
									"-fx-background-radius:50;" + 
									"-fx-background-color: #EFE2FE;" + 
									"-fx-padding: 0 0 0 0;" + 
									"-fx-border-color:#aeaeae;");
			}
		});

		catalogButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				catalogButton.setStyle("-fx-background-color:#a297d5;" + 
										"-fx-background-radius:50;" + 
										"-fx-border-radius:50;" + 
										"-fx-padding: 0 0 0 0;" + 
										"-fx-border-color:#aeaeae;");
			}
		});

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

			// Action when the button is pressed
			deleteButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					T selected = DeleteButtonCell.this.getTableView().getItems().get(DeleteButtonCell.this.getIndex());
					if (command.equals("Cancel Order")) {
						Order selectedO = (Order)selected;
						String newStatus = "WAITING_FOR_CANCELATION";
						selectedO.setOrderStatus(newStatus);
						try {
							ClientUI.chat.accept("changeOrderStatus\t"+selectedO.getOrderNumber()+"\t"+newStatus);
						} catch (IOException e) {
							e.printStackTrace();
						}
						deleteButton.setOnMouseExited(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								deleteButton.setStyle("-fx-background-color:green;" + "-fx-background-radius:50;"
										+ "-fx-border-radius:50;" + "-fx-padding: 0 0 0 0;" + "-fx-border-color:#aeaeae;");
							}
						});
						deleteButton.setDisable(true);
						

						Text text1 = (Text)((Node) t.getTarget()).getScene().lookup("#status1");
						text1.setText("Waiting for cancelation");
						Text text2 = (Text)((Node) t.getTarget()).getScene().lookup("#status2");
						text2.setText("Waiting for cancelation");
					}
					else if (command.equals("Delete Item")) {
						ChatClient.customerCart.remove(selected); // new due amount
						@SuppressWarnings("unchecked")
						TableView<AbstractProduct> mytable = (TableView<AbstractProduct>) ((Node) t.getTarget())
								.getScene().lookup("#my-table");

						ChatClient.customerCart.remove(selected); // new due amount
						mytable.getItems().clear();
						mytable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));// new due
																												// amount

						TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene()
								.lookup("#myTextFieldPrice");
						// new due amount
						Double totalPrice = 0.0;
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice() * entry.getValue();
						}
						totalPriceField.setText(totalPrice.toString());
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
						
						if(ChatClient.customerCart.get(selectedproduct) == 1) 
							{
							ChatClient.customerCart.remove(selectedproduct); // new due amount
							@SuppressWarnings("unchecked")
							TableView<AbstractProduct> mytable = (TableView<AbstractProduct>) ((Node) t.getTarget())
									.getScene().lookup("#my-table");

							ChatClient.customerCart.remove(selectedproduct); // new due amount
							mytable.getItems().clear();
							mytable.setItems(FXCollections.observableArrayList(ChatClient.customerCart.keySet()));// new due
																													// amount

							TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene()
									.lookup("#myTextFieldPrice");
							// new due amount
							Double totalPrice = 0.0;
							for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
								totalPrice += entry.getKey().getPrice() * entry.getValue();
							}
							totalPriceField.setText(totalPrice.toString());
							return;
							}
						ChatClient.customerCart.put(selectedproduct, ChatClient.customerCart.get(selectedproduct) - 1);
						
						// new due amount
						Double totalPrice = 0.0;
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice() * entry.getValue();
							entry.getKey().setAmount(entry.getValue());
						}
						
						TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene()
								.lookup("#myTextFieldPrice");
						totalPriceField.setText(totalPrice.toString());

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
						TextField totalPriceField = (TextField) ((Node) t.getTarget()).getScene()
								.lookup("#myTextFieldPrice");
						for (Map.Entry<AbstractProduct, Integer> entry : ChatClient.customerCart.entrySet()) {
							totalPrice += entry.getKey().getPrice() * entry.getValue();
							entry.getKey().setAmount(entry.getValue());
						}
						totalPriceField.setText(totalPrice.toString());
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
	 * @return a styled button with purple background for updates,
	 * with setOnMouseEntered styling and setOnMouseExitStyling.
	 * It is ment for creating buttons that implement TableCell[AbstractProduct,String]
	 */
	public RegularButtonCell<AbstractProduct,String> createRegularButtonForAbstractItem(String command){
		return new RegularButtonCell<AbstractProduct, String>(command);
	}
}
