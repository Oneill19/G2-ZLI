package gui;

import java.io.IOException;
import java.time.LocalDate;

import client.ChatClient;
import client.ClientUI;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ShowOrdersScreenController {

	ObservableList<Order> observableList;
	private int editedOrderNumber;
	private int editedColumn;
	private Object editedNewValue;

	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private TableColumn<Order, Integer> orderNumberCol;

	@FXML
	private TableColumn<Order, Double> priceCol;

	@FXML
	private TableColumn<Order, String> greetingCardCol;

	@FXML
	private TableColumn<Order, String> colorCol;

	@FXML
	private TableColumn<Order, String> dOrderCol;

	@FXML
	private TableColumn<Order, String> shopCol;

	@FXML
	private TableColumn<Order, LocalDate> dateCol;

	@FXML
	private TableColumn<Order, LocalDate> orderDateCol;

	@FXML
	private Button onBack;

	@FXML
	private Button exitClient;

	@FXML
	public void onBack(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("OptionsScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	public void onExit(ActionEvent event) throws Exception {
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
	}

	public void initialize() {
		// Set handler on cell double click: Take input string and call accept.
		class cellHanler implements EventHandler<CellEditEvent<Order, String>> {

			@Override
			public void handle(CellEditEvent<Order, String> t) {
				((Order) t.getTableView().getItems().get(t.getTablePosition().getRow())).setColor((t.getNewValue()));
				editedOrderNumber = t.getRowValue().getOrderNumber();
				editedNewValue = t.getNewValue();
				editedColumn = t.getTablePosition().getColumn();

				try {
					ClientUI.chat.accept("CellUpdate\t" + editedOrderNumber + "\t" + editedNewValue.toString() + " \t"
							+ editedColumn);
				} catch (IOException e) {
					e.printStackTrace();
				}
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = 0;
			}
		}

		// Set table and columns as editable
		ordersTable.setEditable(true);

		colorCol.setEditable(true);
		colorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		colorCol.setOnEditCommit(new cellHanler());

		
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateCol.setCellFactory(col -> new MyDateCell());
		dateCol.setEditable(true);
		
		class dateCellHandler implements EventHandler<CellEditEvent<Order, LocalDate>> {

			@Override
			public void handle(CellEditEvent<Order, LocalDate> event) {
				event.getRowValue().setDate(event.getNewValue());
				
				editedOrderNumber = event.getRowValue().getOrderNumber();
				editedNewValue = event.getNewValue();
				editedColumn = event.getTablePosition().getColumn();

				try {
					ClientUI.chat.accept("CellUpdate\t" + editedOrderNumber + "\t" + editedNewValue.toString() + "\t"
							+ editedColumn);
				} catch (IOException e) {
					e.printStackTrace();
				}
				editedOrderNumber = 0;
				editedNewValue = null;
				editedColumn = 0;
			}
		}
		//dateCol.setOnEditCommit(event -> event.getRowValue().setDate(event.getNewValue()));
		dateCol.setOnEditCommit(new dateCellHandler());
			
		// Bind columns
		observableList = FXCollections.observableArrayList(ChatClient.orders);
		ordersTable.getItems().clear();
		orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		greetingCardCol.setCellValueFactory(new PropertyValueFactory<>("greetingCard"));
		colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
		dOrderCol.setCellValueFactory(new PropertyValueFactory<>("dorder"));
		shopCol.setCellValueFactory(new PropertyValueFactory<>("shop"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		ordersTable.setItems(observableList);
	}
	
	public class MyDateCell extends TableCell<Order, LocalDate> {
	    
	    private final DateTimeFormatter formatter ;
	    private final DatePicker datePicker ;
	    
	    public MyDateCell() {
	        
	        formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT) ;
	        datePicker = new DatePicker() ;
	        
	        datePicker.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
	            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
	                datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
	                commitEdit(LocalDate.from(datePicker.getValue()));
	            }
	            if (event.getCode() == KeyCode.ESCAPE) {
	                cancelEdit();
	            }
	        });
	        
	        datePicker.setDayCellFactory(picker -> {
	            DateCell cell = new DateCell();
	            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
	                datePicker.setValue(cell.getItem());
	                if (event.getClickCount() == 2) {
	                    datePicker.hide();
	                    commitEdit(LocalDate.from(cell.getItem()));
	                }
	                event.consume();
	            });
	            cell.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
	                if (event.getCode() == KeyCode.ENTER) {
	                    commitEdit(LocalDate.from(datePicker.getValue()));
	                }
	            });
	            return cell ;
	        });

	        contentDisplayProperty().bind(Bindings.when(editingProperty())
	                .then(ContentDisplay.GRAPHIC_ONLY)
	                .otherwise(ContentDisplay.TEXT_ONLY));
	    }
	    
	    @Override
	    public void updateItem(LocalDate date, boolean empty) {
	        super.updateItem(date, empty);
	        if (empty) {
	            setText(null);
	            setGraphic(null);
	        } else {
	            setText(formatter.format(date));
	            setGraphic(datePicker);
	        }
	    }
	    
	    @Override
	    public void startEdit() {
	        super.startEdit();
	        if (!isEmpty()) {
	            datePicker.setValue(getItem().withYear(LocalDate.now().getYear()));
	        }
	    }

	}

}
