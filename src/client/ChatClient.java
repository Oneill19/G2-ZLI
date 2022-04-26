package client;

import java.io.IOException;
import java.util.ArrayList;

import common.ChatIF;
import entities.Order;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {
	
	ChatIF clientUI;
	public static ArrayList<Order> orders;
	public static boolean awaitResponse = false;

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port);
		this.clientUI = clientUI;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessageFromServer(Object msg) {
		System.out.println("--> handleMessageFromServer");
		clientUI.display(msg.toString());
		awaitResponse = false;
		if (msg instanceof ArrayList) {
			orders = (ArrayList<Order>) msg;
		}
//		String str = msg.toString();
//		String[] result;
	}
	
	public void handleMessageFromClientUI(String msg) {
		try {
			openConnection();
			System.out.println("--> handleMessageFromClient");
			clientUI.display(msg.toString());
			awaitResponse = true;
			sendToServer(msg);
			while (awaitResponse) {
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server");
			System.exit(0);
		}
	}

}
