
package server;

import java.io.IOException;
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
		switch (clientMsg[0]) {
		case "Load":
			orders = mysqlConnection.loadOrders(conn);
			if (orders != null) {
				try {
					client.sendToClient(orders);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case "Connected":
			String[] clientInfo = client.toString().split(" ");
			ipAddress = clientInfo[1].substring(1, clientInfo[1].length() - 1);
			ClientInfo temp = new ClientInfo(ipAddress, clientInfo[0], "Connected");
			if (!Clientlist.contains(temp)) {
				Clientlist.add(temp);
			}
			try {
				client.sendToClient("You are connected");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "Disconnect":
			clientInfo = client.toString().split(" ");
			ipAddress = clientInfo[1].substring(1, clientInfo[1].length() - 1);
			int position = searchClientByIp(ipAddress);
			if (position != -1) {
				Clientlist.remove(position);
			}
			try {
				client.sendToClient("You are disconnected");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		// clientMsg[1]= editedOrderNumber
		// clientMsg[2]= editedNewValue
		// clientMsg[3]= editedColumn
		case "CellUpdate":
			mysqlConnection.cellUpdate(conn, clientMsg[1], clientMsg[2], clientMsg[3]);
			try {
				client.sendToClient("Order Updated");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		// case for get a user from the database
		case "GetUser":
			try {
				// send the user back to client
				client.sendToClient(AuthQuery.getUser(conn, clientMsg[1], clientMsg[2]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		// case for updating logging a user
		case "LogUser":
			try {
				// send a successful message back
				client.sendToClient(AuthQuery.loginUser(conn, clientMsg[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		// case for updating logging out a user
		case "LogoutUser":
			try {
				// send a successful message back
				client.sendToClient(AuthQuery.logoutUser(conn, clientMsg[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// case to get Users awaiting registration from the database
		case "GetNotApprovedUsers":
			try {
				// send a successful message back
				client.sendToClient(StoreManagerQuery.getUsersRegsiter(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// case to get Pending Orders from the database

		case "GetPendingOrders":
			try {
				// send a successful message back
				client.sendToClient(StoreManagerQuery.getPendingOrders(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "UpdateStatusOrders":
			try {
				// send a successful message back
				client.sendToClient(StoreManagerQuery.UpdateStatusOrders(conn, clientMsg[1], clientMsg[2]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "GetApprovedUsers":
			try {
				// send a successful message back
				client.sendToClient(StoreManagerQuery.GetApprovedUsers(conn,clientMsg[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "ChangeUserStatus":
			try {
				// send a successful message back
				client.sendToClient(StoreManagerQuery.UpdateUserStatus(conn, clientMsg[1], clientMsg[2]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "GetAllProducts":
			try {
				// get all the premade products
				client.sendToClient(ProductsQuery.getAllProducts(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "GetAllItems":
			try {
				// get all the sold alone items
				client.sendToClient(ProductsQuery.getAllItems(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "GetAllStores":
			try {
				client.sendToClient(StoreQuery.getAllStores(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "GetSurveysWithReports":
			try {
				client.sendToClient(SurveyQuery.getSurveysWithReports(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "GetSurveyReport":
			try {
				client.sendToClient(SurveyQuery.getSurveyReport(conn, Integer.parseInt(clientMsg[1])));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "GetAllSurveys":
			try {
				client.sendToClient(SurveyQuery.getAllSurveys(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "AddSurveyAnswer":
			try {
				client.sendToClient(SurveyQuery.addSurveyAnswer(conn, Integer.parseInt(clientMsg[1]), // surveyId
						clientMsg[2], // userMail
						Integer.parseInt(clientMsg[3]), // answer1
						Integer.parseInt(clientMsg[4]), // answer2
						Integer.parseInt(clientMsg[5]), // answer3
						Integer.parseInt(clientMsg[6]), // answer4
						Integer.parseInt(clientMsg[7]), // answer5
						Integer.parseInt(clientMsg[8]))); // answer6
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "AddOrderToDB":
			try {
				//clientMsg[1] has the order in cart
				client.sendToClient(OrderQuery.saveOrderToDB(conn, clientMsg[1]));
			}catch(Exception e) {
				e.printStackTrace();
			}
		case "numberOfItemsInOrder":
			try {
				client.sendToClient(clientMsg);
			}catch(Exception e){
				e.printStackTrace();
			}
		case "GetRegistersUsers":
			try {
				client.sendToClient(StoreManagerQuery.getAllWaitingRegistersUsers(conn));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "ConfirmedUserUpdate":
			try {

				client.sendToClient(StoreManagerQuery.ConfirmedUserUpdate(conn, clientMsg[1], clientMsg[2]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

			case "getReport":
			try {

				client.sendToClient(ReportQuery.getReport(conn, clientMsg[1], clientMsg[2],clientMsg[3],clientMsg[4]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		default:
			System.out.println("No Command Found");
//			System.exit(-1);
			break;

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

	// TODO
	public Connection getConnection() {
		return conn;
	}

}
//End of EchoServer class
