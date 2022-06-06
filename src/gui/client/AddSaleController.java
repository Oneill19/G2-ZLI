package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.AbstractProduct;
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

    @FXML
    void onNext(ActionEvent event) {

    }
    
    
    /**
	 * 
	 * causes TextField to change its' border to transparent when gets focus
	 * @author Dorin
	 *
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
	 * @return [0]: productInSale, [1]: itemInSale
	 */
	private String[] getListItems() {
	    StringBuilder productInSale = new StringBuilder();
	    StringBuilder itemInSale = new StringBuilder();
	    String[] ret = new String[2];	    
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
			}
		}
		if(productInSale.length()>0)
			productInSale.delete(productInSale.length()-1, productInSale.length());
		if(itemInSale.length()>0)
			itemInSale.delete(itemInSale.length()-1, itemInSale.length());
		
		ret[0] = productInSale.toString();
		ret[1] = itemInSale.toString();
		System.out.println("getListItems(): "+itemInSale);
		System.out.println("getListProducts(): "+productInSale);
		return ret;
	}
	
	/**
	 * @param idSale		the id of the sale
	 * @param productInSale	the id of the products
	 */
	private void insertProductInSaleToDB(int idSale, String productInSale) {
		try {
			ClientUI.chat.accept("insertProductsInSaleToDB\t"+idSale+"\t"+productInSale);
		}catch(Exception e){
			e.printStackTrace();
		}
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
		String productInSale,itemInSale, ret[];
		int idSale;
		
		//• only Integer inputs 
		//• 100>integer>0
		if (!checkSaleAmountRestrictions()) return;
		
		//get list of serial numbers to be added to sale
		if ((ret = getListItems()) == null)
			return;
		productInSale = ret[0];
		itemInSale = ret[1];
		
		try {
//			insert the new sale to table 'sale' in db
			ClientUI.chat.accept("insertNewSale\t"+saleName.getText()+"\t"+saleAmount.getText());
			if (ChatClient.returnSaleID == null) {
				System.out.println("problem in getting idSale");
				return;
			}				
			idSale = ChatClient.returnSaleID;
			System.out.println("idSale in AddSaleController: "+idSale);
			
//			set saleId of AP instance
			for(AbstractProduct ap : apList) {
				ap.setSale(idSale);
			}
			
			//if there are items in the sale
			if(itemInSale.length()>0) {
				
				//insert to item_in_sale
				ClientUI.chat.accept("insertItemInSale\t"+itemInSale+"\t"+idSale);
				if (!ChatClient.requestSucceed) {
					System.out.println("problem in inserting item_in_sale");
					return;
				}
				
				//change table item(idSale) to updated idSale
				ClientUI.chat.accept("changeItemIdSale\t"+itemInSale+"\t"+idSale);
				if(!ChatClient.requestSucceed) {

					System.out.println("problem in inserting item_in_sale");
					return;
				}
			}
			
			
			if(productInSale.length()>0)
				insertProductInSaleToDB(idSale,productInSale );
		}catch(Exception e) {
			System.out.println("problem in sale");
			e.printStackTrace();
		}
		
		try {
			cc.changeFXML(event, "MarketingEmployeOptions.fxml", "Marketing Employe Options");
		} catch (IOException e) {
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
