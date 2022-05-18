package client;

import java.io.IOException;
import java.util.ArrayList;

import common.ChatIF;
import common.ReturnCommand;
import entity.Order;
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
		if (msg instanceof ReturnCommand) {
			ReturnCommand rc = (ReturnCommand)msg;
			switch (rc.getCommand()) {
			case "GetUser":
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
