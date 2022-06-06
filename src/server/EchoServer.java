
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ClientInfo;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer {

	private static String ip;
	private static String userName;
	private static String pwd;
	private static String name;
	private static ObservableList<ClientInfo> Clientlist = FXCollections.observableArrayList();
	private static Connection conn;

	// for pass the client Details to Connection table.
	public static ObservableList<ClientInfo> getClientlist() {
		return Clientlist;
	}

	public EchoServer(String ip, int port, String userName, String pwd, String name) {
		super(port);
		EchoServer.ip = ip;
		EchoServer.userName = userName;
		EchoServer.pwd = pwd;
		EchoServer.name = name;
	}

	// Instance methods ************************************************

	public static boolean connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {
			System.out.println("Driver definition failed");
			return false;
		}

		try {
			String dblink = String.format("jdbc:mysql://%s/%s?serverTimezone=IST", ip, name);
			System.out.println(dblink);
			conn = DriverManager.getConnection(dblink, userName, pwd);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */

	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		connectToDB();
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String[] clientMsg = ((String) msg).split("\t");
		String ipAddress = null;
		ArrayList<Order> orders;
		try {
			switch (clientMsg[0]) {

			case "Load":
				orders = mysqlConnection.loadOrders(conn);
				if (orders != null)
					client.sendToClient(orders);
				break;
			case "Connected":
				String[] clientInfo = client.toString().split(" ");
				ipAddress = clientInfo[1].substring(1, clientInfo[1].length() - 1);
				ClientInfo temp = new ClientInfo(ipAddress, clientInfo[0], "Connected");
				if (!Clientlist.contains(temp)) {
					Clientlist.add(temp);
				}
				client.sendToClient("You are connected");
				break;
			case "Disconnect":
				clientInfo = client.toString().split(" ");
				ipAddress = clientInfo[1].substring(1, clientInfo[1].length() - 1);
				int position = searchClientByIp(ipAddress);
				if (position != -1) {
					Clientlist.remove(position);
				}
				client.sendToClient("You are disconnected");
				break;
			// clientMsg[1]= editedOrderNumber
			// clientMsg[2]= editedNewValue
			// clientMsg[3]= editedColumn
			case "CellUpdate":
				mysqlConnection.cellUpdate(conn, clientMsg[1], clientMsg[2], clientMsg[3]);
				client.sendToClient("Order Updated");
				break;
			// case for get a user from the database
			case "GetUser":
				// send the user back to client
				client.sendToClient(AuthQuery.getUser(conn, clientMsg[1], clientMsg[2]));
				break;
			// case for updating logging a user
			case "LogUser":
				// send a successful message back
				client.sendToClient(AuthQuery.loginUser(conn, clientMsg[1]));
				break;
			// case for updating logging out a user
			case "LogoutUser":
				// send a successful message back
				client.sendToClient(AuthQuery.logoutUser(conn, clientMsg[1]));
				break;
			// case to get Users awaiting registration from the database
			case "GetNotApprovedUsers":
				// send a successful message back
				client.sendToClient(StoreManagerQuery.getUsersRegsiter(conn));
				break;
			// case to get Pending Orders from the database
			case "GetPendingOrders":
				// send a successful message back
				client.sendToClient(StoreManagerQuery.getPendingOrders(conn));
				break;
			case "UpdateStatusOrders":
				// send a successful message back
				client.sendToClient(StoreManagerQuery.UpdateStatusOrders(conn, clientMsg[1], clientMsg[2]));
				break;
			case "GetApprovedUsers":
				// send a successful message back
				client.sendToClient(StoreManagerQuery.GetApprovedUsers(conn, clientMsg[1]));
				break;
			case "ChangeUserStatus":
				// send a successful message back
				client.sendToClient(StoreManagerQuery.UpdateUserStatus(conn, clientMsg[1], clientMsg[2]));
				break;
			case "GetAllProducts":
				// get all the premade products
				client.sendToClient(ProductsQuery.getAllProducts(conn));
				break;
			case "GetAllItems":
				// get all the sold alone items
				client.sendToClient(ProductsQuery.getAllItems(conn));
				break;
			case "GetAllStores":
				client.sendToClient(StoreQuery.getAllStores(conn));
				break;
			case "GetSurveysWithReports":
				client.sendToClient(SurveyQuery.getSurveysWithReports(conn));
				break;
			case "GetSurveyReport":
				client.sendToClient(SurveyQuery.getSurveyReport(conn, Integer.parseInt(clientMsg[1])));
				break;
			case "GetAllSurveys":
				client.sendToClient(SurveyQuery.getAllSurveys(conn));
				break;
			case "AddSurveyAnswer":
				client.sendToClient(SurveyQuery.addSurveyAnswer(conn, Integer.parseInt(clientMsg[1]), // surveyId
						clientMsg[2], // userMail
						Integer.parseInt(clientMsg[3]), // answer1
						Integer.parseInt(clientMsg[4]), // answer2
						Integer.parseInt(clientMsg[5]), // answer3
						Integer.parseInt(clientMsg[6]), // answer4
						Integer.parseInt(clientMsg[7]), // answer5
						Integer.parseInt(clientMsg[8]))); // answer6
				break;
			case "AddOrderToDB":
				// clientMsg[1] has the order in cart
				client.sendToClient(OrderQuery.saveOrderToDB(conn, clientMsg[1]));
				break;
			case "GetRegistersUsers":
				client.sendToClient(StoreManagerQuery.getAllWaitingRegistersUsers(conn));
				break;
			case "ConfirmedUserUpdate":
				client.sendToClient(StoreManagerQuery.ConfirmedUserUpdate(conn, clientMsg[1], clientMsg[2]));
				break;
			case "getReport":
				client.sendToClient(
						ReportQuery.getReport(conn, clientMsg[1], clientMsg[2], clientMsg[3], clientMsg[4]));
				break;
			case "addItemsInOrder":
				client.sendToClient(OrderQuery.addItemsInOrder(conn, clientMsg[1], clientMsg[2]));
				break;
			case "addProductsInOrder":
				client.sendToClient(OrderQuery.addProductsInOrder(conn, clientMsg[1], clientMsg[2]));
				break;
			case "AddComplaint":
				client.sendToClient(ComplaintQuery.addComplaint(conn, clientMsg[1], clientMsg[2], clientMsg[3], clientMsg[4], clientMsg[5], clientMsg[6], clientMsg[7], clientMsg[8]));
				break;
			case "OrderExist":
				client.sendToClient(ComplaintQuery.orderExist(conn, Integer.parseInt(clientMsg[1]),
						Integer.parseInt(clientMsg[2])));
				break;
			case "GetAllOpenComplaintsOfWorker":
				client.sendToClient(ComplaintQuery.getAllOpenComplaintsOfWorker(conn, Integer.parseInt(clientMsg[1])));
				break;
			case "RefundForComplaintFullAmount":
				client.sendToClient(ComplaintQuery.refundForComplaintFullAmount(conn, Integer.parseInt(clientMsg[1]),
						Integer.parseInt(clientMsg[2]), Integer.parseInt(clientMsg[3]), clientMsg[4]));
				break;
			case "RefundForComplaintNotFull":
				client.sendToClient(ComplaintQuery.refundForComplaintNotFull(conn, Integer.parseInt(clientMsg[1]),
						Integer.parseInt(clientMsg[2]), Integer.parseInt(clientMsg[3]), Float.parseFloat(clientMsg[4]),
						clientMsg[5]));
				break;
			case "CloseComplaint":
				client.sendToClient(ComplaintQuery.closeComplaint(conn, Integer.parseInt(clientMsg[1])));
				break;
			case "addDeliveryOrder":
				client.sendToClient(OrderQuery.addDeliveryOrder(conn, clientMsg[1], clientMsg[2]));
				break;
			case "GetReportByQuarter1":
				client.sendToClient(ReportQuery.getReportByQuarterly1(conn, clientMsg[1], clientMsg[2], clientMsg[3]));
				break;
			case "GetReportByQuarter2":
				client.sendToClient(ReportQuery.getReportByQuarterly2(conn, clientMsg[1], clientMsg[2], clientMsg[3]));
				break;
			case "getUserOrders":
				client.sendToClient(OrderQuery.getUserOrders(conn, clientMsg[1]));
				break;
			case "getOrderDeliveryData":
				client.sendToClient(OrderQuery.getOrderDeliveryData(conn, clientMsg[1]));
				break;
			case "getOrderItems":
				client.sendToClient(OrderQuery.getOrderItems(conn, clientMsg[1]));
				break;
			case "getOrderProducts":
				client.sendToClient(OrderQuery.getOrderProducts(conn, clientMsg[1]));
				break;
			case "changeOrderStatus":
				client.sendToClient(OrderQuery.changeOrderStatus(conn, clientMsg[1], clientMsg[2]));
				break;
			case "GetComplaintReportByStore":
				client.sendToClient(ComplaintQuery.getComplaintReportByStore(conn, clientMsg[1], clientMsg[2], clientMsg[3]));
				break;
			case "GetAllColors":
				client.sendToClient(ProductsQuery.getAllColors(conn));
				break;
			case "GetItemsForEdit":
				client.sendToClient(ProductsQuery.getItemsForEdit(conn, clientMsg[1]));
				break;
			case "GetItemsForCreate":
				client.sendToClient(ProductsQuery.getItemsForCreate(conn));
				break;
			case "CreateProduct":
				client.sendToClient(ProductsQuery.createProduct(conn, clientMsg[1], clientMsg[2], clientMsg[3], clientMsg[4], clientMsg[5]));
				break;
			case "CreateItem":
				client.sendToClient(ProductsQuery.createItem(conn, clientMsg[1], clientMsg[2], clientMsg[3], clientMsg[4]));
				break;
			case "EditProduct":
				client.sendToClient(ProductsQuery.editProduct(conn, clientMsg[1], clientMsg[2], clientMsg[3], clientMsg[4], clientMsg[5], clientMsg[6]));
				break;
			case "EditItem":
				client.sendToClient(ProductsQuery.editItem(conn, clientMsg[1], clientMsg[2], clientMsg[3], clientMsg[4], clientMsg[5]));
				break;
			case "insertNewSale":
				client.sendToClient(SaleQuery.insertNewSale(conn, clientMsg[1], clientMsg[2]));
				break;
			case "insertItemInSale":
				//[1]: serial
				//[2]: idSale
				client.sendToClient(SaleQuery.insertItemInSale(conn, clientMsg[1], clientMsg[2]));
				break;
			case "insertProductsInSaleToDB":
				client.sendToClient(SaleQuery.insertProductsInSaleToDB(conn, clientMsg[1], clientMsg[2]));
				break;
			case "Reminded":
				client.sendToClient(ComplaintQuery.reminded(conn, Integer.parseInt(clientMsg[1])));
				break;
			case "selectAllSales":
				client.sendToClient(SaleQuery.selectAllSales(conn));
				break;
			case "select_item_in_sale":
				client.sendToClient(SaleQuery.select_item_in_sale(conn, clientMsg[1]));
				break;
			case "select_product_in_sale":
				client.sendToClient(SaleQuery.select_product_in_sale(conn, clientMsg[1]));
				break;
			case "updateSaleStatus":
				client.sendToClient(SaleQuery.updateSaleStatus(conn, clientMsg[1], clientMsg[2]));
				break;
			case "nullifyIdSaleOfItemsWithCurrentIdSale":
				client.sendToClient(SaleQuery.nullifyIdSaleOfItemsWithCurrentIdSale(conn, clientMsg[1]));
				break;
			case "changeItemIdSale":
				client.sendToClient(SaleQuery.changeItemIdSale(conn, clientMsg[1], clientMsg[2]));
				break;
			case "updateSale":
				client.sendToClient(SaleQuery.updateSale(conn, clientMsg[1], clientMsg[2], clientMsg[3]));
				break;
			default:
				System.out.println("No Command Found" + clientMsg[0]);
				break;
			}
		} catch (Exception e) {
			System.out.println(clientMsg[0]);
			e.printStackTrace();
		}
	}

	private int searchClientByIp(String ip) {
		int position = 0;
		for (ClientInfo ci : Clientlist) {
			if ((ci.getIpAddress()).equals(ip))
				return position;
			position++;
		}
		return -1;
	}

	// TODO - delete before submitting and correct errors that occur from it
	//it is mainly to disconnect all users from the DB
	public Connection getConnection() {
		return conn;
	}

}
//End of EchoServer class
