package server;

import controllers.server.ServerConnectController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application {
	public static final int DEFAULT_PORT = 5555;
	static EchoServer sv = null;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		ServerConnectController aFrame = new ServerConnectController();
		aFrame.start(primaryStage);
	}

	public static void runServer(String ip, String portString, String userName, String password, String name) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(portString);

		} catch (Throwable t) {
			System.out.println("ERROR - Could not connect!");
		}

		sv = new EchoServer(ip, port, userName, password, name);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
	
	public static void stopServer() {
		if (sv != null) {
			sv.stopListening();
		}
	}
}