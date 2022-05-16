package client;

import java.io.IOException;
import java.util.ArrayList;

import common.ChatIF;
import common.ReturnCommand;
import entity.Customer;
import entity.Order;
import entity.StoreWorker;
import entity.User;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

	ChatIF clientUI;
	public static ArrayList<Order> orders;
	
	public static User user = null;
	
	public static boolean awaitResponse = false;

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
	}

	/**
	 *	method to handle the messages from the server
	 *
	 *	@param msg
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleMessageFromServer(Object msg) {
		System.out.println("--> handleMessageFromServer");
		awaitResponse = false;
		if (msg instanceof ArrayList) {
			orders = (ArrayList<Order>) msg;
		}
		System.out.println(msg);
		if (msg instanceof ReturnCommand) {
			ReturnCommand rc = (ReturnCommand)msg;
			switch (rc.getCommand()) {
			case "GetUser":
				System.out.println((User)rc.getReturnValue());
				user = ((User)rc.getReturnValue());
				break;
				
			case "LogUser":
				System.out.println("User Logged");
				break;
			
			case "LogoutUser":
				user = null;
				System.out.println("User Logged out");
				break;
			}
			
		}
//		else if (msg instanceof String) {
//			// split the msg by space
//			String[] splits = ((String)msg).split(";");
//			switch (splits[0]) {
//			// case for convert the message from the server the a User object
//			case "GetUser":
//				int userId = Integer.parseInt(splits[1]);
//				String firstName = splits[2];
//				String lastName = splits[3];
//				String creditCard = splits[4];
//				String phone = splits[5];
//				String email = splits[6];
//				String password = splits[7];
//				String userRole = splits[8];
//				String status = splits[9];
//				boolean isLogged = splits[10].equals("0") ? false : true;
//				if (userRole.equals("Customer")) {
//					double balance = Double.parseDouble(splits[11]);
//					user = new Customer(userId,firstName, lastName, creditCard, phone, email, password, userRole, status, isLogged, balance);
//				}
//				else if (userRole.equals("StoreWorker") || userRole.equals("StoreManager")) {
//					String storeName = splits[11];
//					user = new StoreWorker(userId,firstName, lastName, creditCard, phone, email, password, userRole, status, isLogged, storeName);
//				}
//				else {
//					user = new User(userId, firstName, lastName, creditCard, phone, email, password, userRole, status, isLogged);
//				}
//				break;
//			
//			// case to show message that a user got logged
//			case "LogUser":
//				System.out.println("User Logged");
//				break;
//			
//			// case to show message that a user got logged out
//			case "LogoutUser":
//				user = null;
//				System.out.println("User Logged out");
//				break;
//			}	
//		}
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
