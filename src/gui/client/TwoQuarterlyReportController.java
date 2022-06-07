package gui.client;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import entity.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Koral Biton, Topaz Eldori
 *
 */
public class TwoQuarterlyReportController {
	

	@FXML
    private Button exit;

    @FXML
    private Button userOptBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private BarChart<String, Number> reportChart1;

    @FXML
    private Button Back;

    @FXML
    private BarChart<String, Number> reportChart2;


    /**
     *  Go back to the options screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onBack(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("CEORevenueQuertlyReport.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li Client->Options Screen->View Quarterly Report");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    /**
     * exit from Zer-Li system 
     * @param event
     * @throws Exception
     */
    @FXML
    void onExit(ActionEvent event) throws Exception {
    	ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		ClientUI.chat.accept("Disconnect");
		System.exit(0);
    }

    /**
     * Log out from the user and go back to login scree
     * @param event
     * @throws IOException
     */
    @FXML
    void onLogout(ActionEvent event) throws IOException {
		ClientUI.chat.accept("LogoutUser" + "\t" + ChatClient.user.getEmail());
		ChatClient.user = null;
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		new FXMLLoader();
		Pane root = FXMLLoader.<Pane>load(getClass().getResource("LoginScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Zer-Li");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    
	/**
	 * Initialize the screen
	 */
	@FXML
	void initialize() {
		//String storeName1 = "";
	//	String storeName2 = "";
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());
		setChart(ChatClient.reportsq1, reportChart1);
		setChart(ChatClient.reportsq2, reportChart2);
		
	}
	
	/**
	 *  Initialize the chart
	 * @param reports
	 * @param chart
	 */
	public void setChart(ArrayList<Report> reports, BarChart<String, Number> chart) {
		String storeName = "";
		userOptBtn.setText("Hello, " + ChatClient.user.getFirstName());

		// init data
		XYChart.Series<String, Number> dataSeries = new Series<String, Number>();
		XYChart.Series<String, Number> productRevenue = new Series<String, Number>();
		XYChart.Series<String, Number> ItemRevenue = new Series<String, Number>();
		
		float r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0, r6 = 0, r7 = 0, r8 = 0, r9 = 0, r10 = 0, r11 = 0, r12 = 0;
		float p1=0, p2=0, p3=0, p4=0, p5=0, p6=0, p7=0, p8=0, p9=0, p10=0, p11=0, p12=0;
		float i1=0, i2=0, i3=0, i4=0, i5=0, i6=0, i7=0, i8=0, i9=0, i10=0, i11=0, i12=0;

		for (Report report : reports) {
			switch (report.getReportMonth()) {
			case "01":
				r1 = report.getTotalRevenue();
				p1 = report.getRevenueProduct();
				i1= report.getRevenueItem();
				break;
			case "02":
				r2 = report.getTotalRevenue();
				p2 = report.getRevenueProduct();
				i2= report.getRevenueItem();
				break;
			case "03":
				r3 = report.getTotalRevenue();
				p3 = report.getRevenueProduct();
				i3= report.getRevenueItem();
				break;
			case "04":
				r4 = report.getTotalRevenue();
				p4 = report.getRevenueProduct();
				i4= report.getRevenueItem();
				break;
			case "05":
				r5 = report.getTotalRevenue();
				p5 = report.getRevenueProduct();
				i5= report.getRevenueItem();
				break;
			case "06":
				r6 = report.getTotalRevenue();
				p6 = report.getRevenueProduct();
				i6= report.getRevenueItem();
				break;
			case "07":
				r7 = report.getTotalRevenue();
				p7 = report.getRevenueProduct();
				i7= report.getRevenueItem();
				break;
			case "08":
				r8 = report.getTotalRevenue();
				p8 = report.getRevenueProduct();
				i8= report.getRevenueItem();
				break;
			case "09":
				r9 = report.getTotalRevenue();
				p9 = report.getRevenueProduct();
				i9= report.getRevenueItem();
				break;
			case "10":
				r10 = report.getTotalRevenue();
				p10 = report.getRevenueProduct();
				i10= report.getRevenueItem();
				break;
			case "11":
				r11 = report.getTotalRevenue();
				p11 = report.getRevenueProduct();
				i11= report.getRevenueItem();
				break;
			case "12":
				r12 = report.getTotalRevenue();
				p12 = report.getRevenueProduct();
				i12= report.getRevenueItem();
				break;
			}
		}

		for (Report report : reports) {
			storeName = report.getStoreName();
			if (report.getQuartlyNum() == 1) {
				dataSeries.getData().add(new XYChart.Data<String, Number>("January", r1));
				dataSeries.getData().add(new XYChart.Data<String, Number>("February", r2));
				dataSeries.getData().add(new XYChart.Data<String, Number>("March", r3));
				

				productRevenue.getData().add(new XYChart.Data<String, Number>("January", p1));
				productRevenue.getData().add(new XYChart.Data<String, Number>("February", p2));
				productRevenue.getData().add(new XYChart.Data<String, Number>("March", p3));
				
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("January", i1));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("February", i2));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("March", i3));
				
				
			}
			if (report.getQuartlyNum() == 2) {
				dataSeries.getData().add(new XYChart.Data<String, Number>("April", r4));
				dataSeries.getData().add(new XYChart.Data<String, Number>("May", r5));
				dataSeries.getData().add(new XYChart.Data<String, Number>("June", r6));
				
				productRevenue.getData().add(new XYChart.Data<String, Number>("April", p4));
				productRevenue.getData().add(new XYChart.Data<String, Number>("May", p5));
				productRevenue.getData().add(new XYChart.Data<String, Number>("June", p6));
				
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("April", i4));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("May", i5));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("June", i6));
				
			}
			if (report.getQuartlyNum() == 3) {
				dataSeries.getData().add(new XYChart.Data<String, Number>("July", r7));
				dataSeries.getData().add(new XYChart.Data<String, Number>("August", r8));
				dataSeries.getData().add(new XYChart.Data<String, Number>("September", r9));
				
				productRevenue.getData().add(new XYChart.Data<String, Number>("July", p7));
				productRevenue.getData().add(new XYChart.Data<String, Number>("August", p8));
				productRevenue.getData().add(new XYChart.Data<String, Number>("March", p9));
				
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("July", i7));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("August", i8));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("September", i9));
				
			}
			if (report.getQuartlyNum() == 4) {
				dataSeries.getData().add(new XYChart.Data<String, Number>("October", r10));
				dataSeries.getData().add(new XYChart.Data<String, Number>("November", r11));
				dataSeries.getData().add(new XYChart.Data<String, Number>("December", r12));
				
				productRevenue.getData().add(new XYChart.Data<String, Number>("October", p10));
				productRevenue.getData().add(new XYChart.Data<String, Number>("November", p11));
				productRevenue.getData().add(new XYChart.Data<String, Number>("December", p12));
				
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("October", i10));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("November", i11));
				ItemRevenue.getData().add(new XYChart.Data<String, Number>("December", i12));
				
			}
		}
		dataSeries.setName("Total Revenue- NIS");
		productRevenue.setName("Product Revenue- NIS");
		ItemRevenue.setName("Item Revenue- NIS");
		// set title of the chart
		chart.setTitle("Revenue Quarterly Histogram for store: " + storeName);
		// add data to chart
		chart.getData().clear();
		chart.getData().add(dataSeries);
		chart.getData().add(productRevenue);
		chart.getData().add(ItemRevenue);

	}
	

}


