package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.Product;
import entity.Sale;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * @author Dorin
 *
 */
public class WatchSalesController {

	//FXML fields
    @FXML private Pane pane;
    @FXML private ScrollPane itemScrollPane;
    @FXML private GridPane grid;
    @FXML private Button onBack, logoutBtn, userOptBtn, exit;
    @FXML private TableView<Sale> salesTable;
    @FXML private TableColumn<Sale, String> namecol,statusCol,actionCol;
    @FXML private TableColumn<Sale, Integer> discountCol, saleIdCol;
    @FXML private VBox pickupVbox;
    @FXML private Text textSaleNumber;

    //controller fields
    private CommonController cc = new CommonController();
    private ArrayList<AbstractProduct> allAbstractProducts_in_sale = new ArrayList<AbstractProduct>();

	@FXML
	void onBack(ActionEvent event) throws IOException {		
		cc.changeFXML(event, "MarketingEmployeOptions.fxml", "Zer-Li Marketing Employe Options");
	}

	@FXML
	void onExit(ActionEvent event) throws Exception {
		cc.OnExit();
	}

	@FXML
	void onLogout(ActionEvent event) throws Exception {
		cc.onLogout(event);
	}
	
    private boolean setChosenSale(Sale sale) throws IOException {
    	grid.getChildren().clear();
    	allAbstractProducts_in_sale.clear();
    	
//    	set the selected sale
    	textSaleNumber.setText("Sale #"+ Integer.toString(sale.getIdSale()));
//    	
//    	//get items and products of selected sale
    	ClientUI.chat.accept("select_item_in_sale\t"+sale.getIdSale());
    	ClientUI.chat.accept("select_product_in_sale\t"+sale.getIdSale());
    	if (ChatClient.item_in_saleArray != null)
    		allAbstractProducts_in_sale.addAll(ChatClient.item_in_saleArray);
    	if (ChatClient.product_in_saleArray != null)
    		allAbstractProducts_in_sale.addAll(ChatClient.product_in_saleArray);
    	
    	if(allAbstractProducts_in_sale.size()<1) {
        	grid.getChildren().clear();
        	allAbstractProducts_in_sale.clear();
        	return false;
    	}
//    	
//    	show items and products of selected sale
    	int i=0;
    	for (AbstractProduct ap : allAbstractProducts_in_sale) {
    		Text name = new Text(ap.getName());
    		ImageView image = new ImageView(new Image(getClass().getResourceAsStream(ap.getImagePath()),100,100,false,false));
    		VBox vbox1 = new VBox(name,image);
    		VBox vbox2=null;
    		Text itemOrProduct=null, type=null,price=null, madeFrom=null, priceWithSale=null;
//    		
    		
    		type = new Text("Type: "+ap.getType());
    		price = new Text("Price: "+ Double.toString(ap.getPrice())+"$");
    		double d = ap.getPriceWithSale();
    		priceWithSale = new Text("S A L E: "+String.format("%.2f", d)+"$");
    		
    		name.autosize();
	    	type.autosize();
	    	price.autosize();
	    	priceWithSale.autosize();
//    		
    		name.setFont(Font.font(null,FontWeight.EXTRA_BOLD,FontPosture.REGULAR,20));
    		type.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    		price.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    		priceWithSale.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
//	    		
    		if (ap instanceof Product) {
    			itemOrProduct = new Text("A Premade Product");
    			Product product = (Product)ap;
    			madeFrom = new Text("Made from the items:\n"+product.getMadeFrom().toString());
    			madeFrom.autosize();
    			madeFrom.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    			vbox2 = new VBox(itemOrProduct,type,price,priceWithSale, madeFrom);
    		}
    		else {
    			itemOrProduct = new Text("An Item");
    			vbox2 = new VBox(itemOrProduct, type, price,priceWithSale);
    		}
//
    		itemOrProduct.autosize();
    		itemOrProduct.setFont(Font.font(null,FontWeight.BOLD,FontPosture.REGULAR,15));
    		
    		HBox hbox = new HBox(vbox1, vbox2);
    		hbox.setSpacing(15.0);
    		vbox1.setSpacing(5);
    		
    		vbox1.setPadding(new Insets(20));
    		vbox2.setPadding(new Insets(20));

    		vbox1.setAlignment(Pos.CENTER);
    		vbox2.setAlignment(Pos.CENTER);
    		
    		vbox1.setPrefWidth(260);
    		vbox2.setPrefWidth(260);
    		
    		hbox.setStyle("-fx-border-color: #E5E4E2; -fx-border-radius: 10px");
    		grid.add(hbox, 0, i++);
    	}
		grid.setHgap(10);
		grid.setVgap(10);
		return true;
    }


	/**
	 * Set the first order in the side bar
	 * @throws IOException
	 */
	private void initScrollPane() throws IOException {

		//Check if no sales
    	if (ChatClient.saleArray.size() < 1) return;
    	
    	//Check if no items in sale
		if (!setChosenSale(ChatClient.saleArray.get(0))){
    		grid.getChildren().clear();
    	}
	}
	
	private void initTable() {
		
		salesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sale>() {
			@Override
			public void changed(ObservableValue<? extends Sale> observable, Sale oldValue, Sale newValue ) {
				try { setChosenSale(newValue); } catch (IOException e) { e.printStackTrace(); }
			}
        });
		
		saleIdCol.setCellValueFactory(new PropertyValueFactory<>("idSale"));
		namecol.setCellValueFactory(new PropertyValueFactory<>("saleName"));
		discountCol.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		//TODO add action to sale tableview
		// Sets colDelete behavior
		actionCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Sale, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Sale, String> p) {
						return new SimpleStringProperty(p.getValue() != null, null);
					}
				});

		// Adding the Button to the cell
		actionCol.setCellFactory(
				new Callback<TableColumn<Sale, String>, TableCell<Sale, String>>() {

					@Override
					public TableCell<Sale, String> call(TableColumn<Sale, String> p) {
						return new MyButtons().createDeleteButtonForSale("Delete Sale");
					}
				});
		
		salesTable.setId("my-table");
		salesTable.getItems().clear();
		salesTable.setItems(FXCollections.observableArrayList(ChatClient.saleArray));
	}
	
	public void initialize() {
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		initTable();
		try {
			initScrollPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
