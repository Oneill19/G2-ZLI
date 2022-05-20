package gui.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.AbstractProduct;
import entity.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.EchoServer;

/**
 * Receives from CatalogController.java [ArrayList<AbstractProduct>] containing
 * all products and items that the user chose to add to his cart.
 * 
 * @author Dorin
 *
 */
public class CartController {
	ObservableList<AbstractProduct> observableList;
	ArrayList<AbstractProduct> productsInCatalog = null;

	// ******
	// *FXML*
	// ******
	@FXML
	private TableView<AbstractProduct> cartTable;
	@FXML
	private TableColumn<AbstractProduct, String> colDelete;
	@FXML
	private TableColumn<AbstractProduct, Image> colImage;
	@FXML
	private TableColumn<AbstractProduct, String> colName;
	@FXML
	private TableColumn<AbstractProduct, Double> colPrice;
	@FXML
	private TextField textFieldPrice;
	@FXML
	private Button exit;
	@FXML
	private Button exitClient;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button onBack;
	@FXML
	private Button userOptBtn;
	private boolean flag = false;

	@FXML
	void onBack(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("Catalog.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Catalog");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	void onExit(ActionEvent event) {

	}

	@FXML
	void onLogout(ActionEvent event) {

	}

	/**
	 * [data] is received from Catalog if Catalog is not ready then method
	 * "bypassCatalog" is used.
	 * 
	 * @param data includes list of products selected by user from catalog.
	 */
	void initData(ArrayList<AbstractProduct> data) {
		productsInCatalog = data;
		observableList = FXCollections.observableArrayList(productsInCatalog);
	}

	public void initialize() {

		if (flag == false)
			bypassCatalog();

		//Sets colDelete behavior
		colDelete.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<AbstractProduct, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractProduct, String> p) {
						return new SimpleStringProperty(p.getValue() != null, null);
					}
				});


		//create class DeleteButton
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
						// remove selected item from the table list
						observableList.remove(currentAbstractProduct);
						Double totalPrice = new Double(0);
						for (AbstractProduct ap : observableList)
							totalPrice += ap.getPrice();
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

		// new
		// Adding the Button to the cell
		colDelete.setCellFactory(
				new Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>>() {

					@Override
					public TableCell<AbstractProduct, String> call(TableColumn<AbstractProduct, String> p) {
						return new DeleteButton();
					}

				});

		/*
		 * // add cell of button delete Callback<TableColumn<AbstractProduct, String>,
		 * TableCell<AbstractProduct, String>> cellFoctory = (
		 * TableColumn<AbstractProduct, String> param) -> { // make cell containing
		 * buttons final TableCell<AbstractProduct, String> cell = new
		 * TableCell<AbstractProduct, String>() {
		 * 
		 * @Override public void updateItem(String item, boolean empty) { File
		 * deleteIconFile = null; FileInputStream fisDeleteIcon = null;
		 * super.updateItem(item, empty);
		 * 
		 * // that cell created only on non-empty rows if (empty) { setGraphic(null);
		 * setText(null); } else { try { deleteIconFile = new File(
		 * "C:\\Users\\Dorin\\eclipse-workspace\\Git\\G2-ZLI\\src\\gui\\client\\delete.png"
		 * ); fisDeleteIcon = new FileInputStream(deleteIconFile); } catch (Exception e)
		 * { e.printStackTrace(); } Button deleteButton = new Button("", new
		 * ImageView(new Image(fisDeleteIcon)));
		 * deleteButton.setId((incrementalNumber++).toString());
		 * deleteButton.setOnMouseClicked((MouseEvent event) -> { try { //
		 * System.out.println(cartTable.getSelectionModel().getSelectedIndex()); //
		 * //prints -1 System.out.println(event.getPickResult()); AbstractProduct
		 * product = cartTable.getSelectionModel().getSelectedItem(); // get
		 * 
		 * // row cartTable.getItems().remove(product); // remove focused product from
		 * list of product Double sumOfProducts = new Double(0); for (AbstractProduct ai
		 * : cartTable.getItems()) sumOfProducts += ai.getPrice();
		 * textFieldPrice.setText(sumOfProducts.toString()); } catch (Exception ex) {
		 * ex.printStackTrace(); } });
		 * 
		 * HBox managebtn = new HBox(deleteButton);
		 * managebtn.setStyle("-fx-alignment:center"); HBox.setMargin(deleteButton, new
		 * Insets(2, 2, 0, 3)); setGraphic(managebtn); setText(null);
		 * 
		 * } } }; return cell; };
		 */

		// colDelete.setCellFactory(cellFoctory);
		colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		cartTable.setId("my-table");

		cartTable.getItems().clear();
		cartTable.setItems(observableList);

		Double sumOfProducts = new Double(0);
		for (AbstractProduct ai : cartTable.getItems())
			sumOfProducts += ai.getPrice();
		textFieldPrice.setText(sumOfProducts.toString());

	}

	/*
	 * bypass Catalog Screen
	 */
	@SuppressWarnings("static-access")
	public void bypassCatalog() {
		EchoServer echoServer = new EchoServer("localhost", 5555, "root", "Aa123456", "zli");
		echoServer.connectToDB();
		Connection conn = echoServer.getConnection();
		ArrayList<AbstractProduct> list = new ArrayList<AbstractProduct>();
		ImageView im;

		Statement stmt;
		String sqlQuery = "SELECT * FROM zli.item";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				Item item = new Item(rs.getString(1), // itemSerial
						rs.getString(2), // itemName
						rs.getDouble(3), // itemPrice
						im = new ImageView(new Image(rs.getBlob(5).getBinaryStream())), // itemImage
						rs.getString(4), // itemType
						rs.getBoolean(6) // isSoldAlone

				);
				im.setFitHeight(150);
				im.setPreserveRatio(true);
				list.add(item);
			}
			productsInCatalog = list;
			observableList = FXCollections.observableArrayList(productsInCatalog);
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
