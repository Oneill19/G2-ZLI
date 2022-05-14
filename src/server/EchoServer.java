
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
				client.sendToClient(AuthQuery.logutUser(conn, clientMsg[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		default:
			System.out.println("No Command Found");
			System.exit(-1);
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

}
//End of EchoServer class
