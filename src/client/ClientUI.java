package client;

import javafx.application.Application;
import javafx.stage.Stage;
import controllers.EnterIPClientController;

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
