package gui.client;

import java.io.File;
import java.time.LocalDate;

import client.ChatClient;
import client.ClientUI;
import entity.Survey;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddSurveyPDFController {

    @FXML
    private Button backButton;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<Survey, Integer> idCol;

    @FXML
    private TableView<Survey> itemTable;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label messageLabel;

    @FXML
    private TableColumn<Survey, String> nameCol;

    @FXML
    private Button saveButton;

    @FXML
    private Button userOptBtn;
    
    private CommonController cc = new CommonController();
    
    private ObservableList<Survey> observableList;

    /**
     * go to the previous screen
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onBack(ActionEvent event) throws Exception {
    	cc.changeFXML(event, "ServiceSpecialistScreen.fxml", "Zer-Li Add Service Specialist");
    }

    /**
     * exit the program
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onExit(ActionEvent event) throws Exception {
    	cc.OnExit();
    }

    /**
     * log out from the user
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onLogout(ActionEvent event) throws Exception {
    	cc.onLogout(event);
    }

    /**
     * save a pdf file in the data base
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void onSave(ActionEvent event) throws Exception {
    	// if the user not selected and survey
    	if (itemTable.getSelectionModel() == null || itemTable.getSelectionModel().getSelectedItem() == null) {
    		messageLabel.setTextFill(Color.RED);
    		messageLabel.setText("Please choose a survey");
    		return;
    	}
    	messageLabel.setText("");
    	
    	// initialize a file chooser
    	FileChooser fc = new FileChooser();
    	fc.setTitle("Choose PDF");
    	
    	// set extention to be pdf
    	fc.getExtensionFilters().add(new ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf"));
    	
    	// open the file chooser dialog
    	File f = fc.showOpenDialog(null);
    	
    	if (f != null) {
    		
    		// get the survey id
    		int surveyId = itemTable.getSelectionModel().getSelectedItem().getSurveyId();
    		
    		// add the report to the data base
    		ClientUI.chat.accept("AddNewSurveyReport" + "\t" + surveyId + "\t" + f.getName() + "\t" + LocalDate.now() + "\t" + f.getAbsolutePath());
    		
    		// if the report added successfully
    		if (ChatClient.requestSucceed) {
    			messageLabel.setTextFill(Color.GREEN);
        		messageLabel.setText("PDF successfuly added for survey id " + surveyId);
    		}
    		else {
    			messageLabel.setTextFill(Color.RED);
        		messageLabel.setText("PDF unsuccessfuly added for survey id " + surveyId);
    		}
    		
    		Survey toRemove = itemTable.getSelectionModel().getSelectedItem();
    		itemTable.getItems().remove(toRemove);
    	}
    }

    /**
     * initialize the screen
     * 
     * @throws Exception
     */
    public void initialize() throws Exception {
    	userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
    	
    	// initialize surveys
    	ChatClient.surveysWithNoReports = null;
    	
    	// get the surveys
    	ClientUI.chat.accept("GetSurveysWithNoReport");
    	
    	
    	// initialize survey table
    	observableList = FXCollections.observableArrayList(ChatClient.surveysWithNoReports);
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<>("surveyId"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("surveyName"));
    	
    	itemTable.setItems(observableList);
    }
}
