package client;

import controllers.client.EnterIPClientController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	
	public static ClientController chat;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		EnterIPClientController eipcc = new EnterIPClientController();
		eipcc.start(primaryStage);
	}

}
