package gui.client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.AbstractProduct;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
	private Button exit;
	@FXML
	private Button exitClient;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button onBack;
	@FXML
	private Button userOptBtn;
	private boolean flag=false;

	@FXML
	void onBack(ActionEvent event) {

	}

	@FXML
	void onExit(ActionEvent event) {

	}

	@FXML
	void onLogout(ActionEvent event) {

	}

	void initData(ArrayList<AbstractProduct> data) {
		productsInCatalog = data;
		observableList = FXCollections.observableArrayList(productsInCatalog);
	}

	public void initialize() {

		if (flag==false)
			bypassCatalog();

		// add cell of button delete
		Callback<TableColumn<AbstractProduct, String>, TableCell<AbstractProduct, String>> cellFoctory = (
				TableColumn<AbstractProduct, String> param) -> {
			// make cell containing buttons
			final TableCell<AbstractProduct, String> cell = new TableCell<AbstractProduct, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					
					// that cell created only on non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						Button deleteButton;
						try {
							deleteButton = new Button("", new ImageView(new Image(getClass().getResourceAsStream("delete.png"))));
							deleteButton.setOnMouseClicked((MouseEvent event) -> {
								try {
									AbstractProduct product = cartTable.getSelectionModel().getSelectedItem(); //get focused row
									System.out.println("Removing "+ product.getName()); //print focused row product name
									cartTable.getItems().remove(product); //remove focused product from list of product
								} catch (Exception ex) {
									ex.printStackTrace();
								}
								HBox managebtn = new HBox(deleteButton);
								managebtn.setStyle("-fx-alignment:center");
								HBox.setMargin(deleteButton, new Insets(2, 2, 0, 3));
								setGraphic(managebtn);
								setText(null);
							});
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			};
			return cell;
		};

		colDelete.setCellFactory(cellFoctory);
		colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		cartTable.setId("my-table");

		cartTable.getItems().clear();
		cartTable.setItems(FXCollections.observableArrayList(productsInCatalog));
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
			flag=true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}