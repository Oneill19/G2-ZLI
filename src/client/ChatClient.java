package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import common.ChatIF;
import common.ReturnCommand;
import entity.AbstractProduct;
import entity.Complaint;
import entity.Item;
import entity.Order;
import entity.Product;
import entity.Report;
import entity.Survey;
import entity.SurveyReport;
import entity.User;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

	ChatIF clientUI;
	public static ArrayList<Order> orders;
	public static ArrayList<Order> NotAprroveorders;
	public static ArrayList<User> ApprovedUserToPer;
	public static ArrayList<User> NotApprovedUsers;
	public static ArrayList<User> waitingUsers;
	public static ArrayList<AbstractProduct> products = new ArrayList<>();
	public static HashMap<AbstractProduct, Integer> customerCart = new HashMap<>();
	public static ArrayList<String> stores = new ArrayList<>();
	public static ArrayList<Survey> surveysWithReports = new ArrayList<>();
	public static ArrayList<Survey> allSurveys = new ArrayList<>();
	public static ArrayList<Complaint> allComplaints = new ArrayList<>();
	public static ArrayList<Report> reportsq1;
	public static ArrayList<Report> reportsq2;
	public static Order cartOrder = new Order();
	public static String reportTxt;
	public static User user = null;
	
	public static SurveyReport selectedSurveyReport = null;
	
	public static boolean requestSucceed = false;

	public static boolean awaitResponse = false;

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
	}

	/**
	 * method to handle the messages from the server
	 *
	 * @param msg
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleMessageFromServer(Object msg) {
		//System.out.println("--> handleMessageFromServer");
		awaitResponse = false;
		if (msg instanceof ArrayList) {
			orders = (ArrayList<Order>) msg;
		}
		if (msg instanceof ReturnCommand) {
			ReturnCommand rc = (ReturnCommand) msg;
			switch (rc.getCommand()) {
			case "GetUser":
				user = ((User) rc.getReturnValue());
				break;
			case "LogUser":
				System.out.println("User Logged");
				break;
			case "LogoutUser":
				user = null;
				System.out.println("User Logged out");
				break;
			case "GetPendingOrders":
				NotAprroveorders = (ArrayList<Order>) rc.getReturnValue();
				break;
			case "GetNotApprovedUsers":
				NotApprovedUsers = (ArrayList<User>) rc.getReturnValue();
				break;
			case "UpdateStatusOrders":
				System.out.println("Order Status Updated");
				break;
			case "GetApprovedUsers":
				ApprovedUserToPer = (ArrayList<User>) rc.getReturnValue();
			case "ChangeUserStatus":
				System.out.println("User Status Updated");
				break;
			case "GetAllProducts":
				ChatClient.products.addAll((ArrayList<Product>) rc.getReturnValue());
				break;
			case "GetAllItems":
				ChatClient.products.addAll((ArrayList<Item>) rc.getReturnValue());
				break;
			case "GetAllStores":
				ChatClient.stores.clear();	// to fix adding duplicates
				ChatClient.stores.addAll((ArrayList<String>) rc.getReturnValue());
				break;
			case "GetSurveysWithReports":
				surveysWithReports.addAll((ArrayList<Survey>)rc.getReturnValue());
				break;	
			case "GetSurveyReport":
				selectedSurveyReport = (SurveyReport)rc.getReturnValue();
				break;	
			case "GetAllSurveys":
				allSurveys.addAll((ArrayList<Survey>)rc.getReturnValue());
				break;
			case "AddSurveyAnswer":
				requestSucceed = rc == null ? false : true;
				break;
			case "AddOrderToDB":
				ChatClient.cartOrder.setOrderNumber((int)rc.getReturnValue());
				System.out.println("Order number " + (int)rc.getReturnValue() + " was added");
				break;
			case "GetRegistersUsers":
				waitingUsers=(ArrayList<User>)rc.getReturnValue();
				break;
			case "ConfirmedUserUpdate":
				System.out.println("User Updated");
				break;
			case "getReport":
				reportTxt=(String)rc.getReturnValue();
				break;
			case "AddComplaint":
				requestSucceed = (boolean)rc.getReturnValue();
				break;
			case "OrderExist":
				requestSucceed = (boolean)rc.getReturnValue();
				break;
			case "CloseComplaint":
				requestSucceed = (boolean)rc.getReturnValue();
				break;
			case "RefundForComplaintFullAmount":
				requestSucceed = (boolean)rc.getReturnValue();
				break;
			case "RefundForComplaintNotFull":
				requestSucceed = (boolean)rc.getReturnValue();
				break;
			case "GetAllOpenComplaintsOfWorker":
				allComplaints.addAll((ArrayList<Complaint>)rc.getReturnValue());
				break;
			case "addDeliveryOrder":
				System.out.println(rc.getReturnValue().toString());
				break;
			case "addItemsInOrder":
				System.out.println(rc.getReturnValue());
				break;
			case "addProductsInOrder":
				System.out.println(rc.getReturnValue());
				break;
			case "generateReport":
				System.out.println("Report Create");
				break;

			case "GetReportByQuarter1":
				reportsq1=(ArrayList<Report>)rc.getReturnValue();
				break;
			case "GetReportByQuarter2":
				reportsq2=(ArrayList<Report>)rc.getReturnValue();
				break;
			default:
				//for debug - don't remove.
				System.out.println("ChatClient didn't recognize command");
			}
		}
	}

	public void handleMessageFromClientUI(String msg) {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer(msg);
			while (awaitResponse) {
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server");
			System.exit(0);
		}
	}

}
