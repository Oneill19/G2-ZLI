package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
import entity.CustomProduct;
import entity.Item;
import entity.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class controls the ability of Marketing Employee to add a sale to the application.
 * this class handles all the necessary situations for making changes to 
 * the tables: item_in_sale, product_in_sale, item, product, sale
 * in order for the information to be concurrent and reliable.
 * @author Dorin
 *
 */
public class AddSaleController {

	private CommonController cc = new CommonController();
    @FXML private Button onBack, logoutBtn, userOptBtn, exit, saveSale;
    @FXML private TextField saleName, saleAmount;
    @FXML private ListView<AbstractProduct> itemsList;
    private ArrayList<AbstractProduct> apList = new ArrayList<AbstractProduct>();

	@FXML
	void onBack(ActionEvent event) throws IOException {
		cc.changeFXML(event, "MarketingEmployeOptions.fxml", "Zer-li Marketing Employe Options");
	}

	@FXML
	void onExit(ActionEvent event) throws Exception {
		cc.OnExit();
	}

	@FXML
	void onLogout(ActionEvent event) throws Exception {
		cc.onLogout(event);
	}
    
    
    /**
	 * causes TextField to change its' border to transparent when gets focus
	 */
	private class myListener implements ChangeListener<Boolean>{
		TextField tf;
		
		public myListener(TextField tf) {
			this.tf = tf;
		}
		
		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean entered) {
			if(entered) {
				tf.setStyle("-fx-text-box-border:transparent;");
			}
		}
	}
	
	/**
	 * @param errDesc		error to show on the alert box
	 * @param imagePath		an image to attach the alert
	 * @return				Alert object
	 */
	private Alert setAlert(String errDesc, String imagePath) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText(errDesc);
		if(imagePath != null) 
			alert.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(imagePath))));
		return alert;
	}
	
	/**
	 * Restrictions:
	 * <li>Integer inputs
	 * <li>100>integer>0
	 */
	private boolean checkSaleAmountRestrictions() {
		int intSaleAmount;
    	//check saleAmount restrictions
    	try {
    		intSaleAmount = Integer.parseInt(saleAmount.getText());
    	}catch(NumberFormatException nfe) {
    		saleAmount.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
    		setAlert("Sale amount can only be with numbers.",null).showAndWait();
    		return false;
    	}
    	if(intSaleAmount>100 || intSaleAmount<0) {
    		setAlert("Sale discount amount is 0%-100%", null).showAndWait();
    		saleAmount.setStyle("-fx-text-inner-color: red;"+"-fx-focus-color:red;" + "-fx-border-color:red;");
    		return false;
    	}
    	saleAmount.setStyle("-fx-text-inner-color: green;" + "-fx-focus-color: green;");
    		return true;
    	}
	
	/**
	 * <li>Extracts serialNumber into corresponding list
	 * @return [0]: productInSale, [1]: itemInSale, [2]:customInSale
	 */
	private String[] getListItems() {
	    StringBuilder productInSale = new StringBuilder();
	    StringBuilder itemInSale = new StringBuilder();
	    StringBuilder custonInSale = new StringBuilder();
	    
	    String[] ret = new String[3];	    
		ObservableList<AbstractProduct> listItems;
		listItems = itemsList.getSelectionModel().getSelectedItems();
		apList.addAll(listItems);
		if(listItems.size()==0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Choose from list");
			alert.setHeaderText("Choose at least one object from list");
			alert.showAndWait();
			return null;
		}
		for(AbstractProduct ap : listItems) {
			if (ap instanceof Product) {
				productInSale.append(ap.getSerialNumber()+" ");
			}
			else if(ap instanceof Item) {
				itemInSale.append(ap.getSerialNumber()+" ");
			}else if(ap instanceof CustomProduct) {
				custonInSale.append(ap.getSerialNumber()+" ");
			}
		}
		if(productInSale.length()>0)
			productInSale.delete(productInSale.length()-1, productInSale.length());
		if(itemInSale.length()>0)
			itemInSale.delete(itemInSale.length()-1, itemInSale.length());
		if(custonInSale.length()>0)
			custonInSale.delete(custonInSale.length()-1, custonInSale.length());
		
		ret[0] = productInSale.toString();
		ret[1] = itemInSale.toString();
		ret[2]=custonInSale.toString();
		
		System.out.println("list custom products:: "+custonInSale);
		return ret;
	}

	
	/**
	 * <li> check saleAmount text field restrictions
	 * <li> add to table: sale and return idSale
	 * <li> add to table: item_in_sale according to returned idSale
	 * <li> update table: item(idSale) to be idSale
	 * @param event
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@FXML
	private void onSave(ActionEvent event){
		String productInSale,itemInSale,customInSale, ret[];
		int idSale;

		//• only Integer inputs 
		//• 100>integer>0
		if (!checkSaleAmountRestrictions()) return;

		//get list of serial numbers to be added to sale
		if ((ret = getListItems()) == null)
			return;
		productInSale = ret[0];
		itemInSale = ret[1];
		customInSale = ret[2];

		try {
			//			insert the new sale to table 'sale' in db
			ClientUI.chat.accept("insertNewSale\t"+saleName.getText()+"\t"+saleAmount.getText());
			if (ChatClient.returnSaleID == null) {
				System.out.println("problem in getting idSale");
				return;
			}				
			idSale = ChatClient.returnSaleID;

			//set saleId of AP instance
			for(AbstractProduct ap : apList) {
				ap.setSale(idSale);
			}

			//if there are items in the sale
			if(itemInSale.length()>0) {

				//insert to item_in_sale
				ClientUI.chat.accept("insertApInSale\t"+itemInSale+"\t"+idSale+"\t"+"item_in_sale");
				if (!ChatClient.requestSucceed) {
					System.out.println("problem in inserting item_in_sale");
					return;
				}

				//change table item(idSale) to updated idSale
				ClientUI.chat.accept("changeApIdSale\t"+itemInSale+"\t"+idSale+"\t"+"item");
				if(!ChatClient.requestSucceed) {
					System.out.println("problem");
					return;
				}
			}

			//if there are items in the sale
			if(productInSale.length()>0) {

				//insert to product_in_sale
				ClientUI.chat.accept("insertApInSale\t"+productInSale+"\t"+idSale+"\t"+"product_in_sale");
				if (!ChatClient.requestSucceed) {
					System.out.println("problem in inserting product_in_sale");
					return;
				}
				//				
				//				change table product(idSale) to updated idSale
				ClientUI.chat.accept("changeApIdSale\t"+productInSale+"\t"+idSale+"\t"+"product");
				if(!ChatClient.requestSucceed) {
					System.out.println("problem");
					return;
				}
			}

			try {
				cc.changeFXML(event, "MarketingEmployeOptions.fxml", "Marketing Employe Options");
			}catch (IOException e) {
				e.printStackTrace();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initList() {
		itemsList.getItems().clear();
		itemsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		itemsList.getItems().addAll(ChatClient.products);
	}
	
    public void initialize() {
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	saleAmount.focusedProperty().addListener(new myListener(saleAmount));
//    	checkSaleAmountRestrictions();
    	initList();
    }

}
