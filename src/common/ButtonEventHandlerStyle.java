package common;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ButtonEventHandlerStyle{
	
	/*
	 * PURPLE
	 */
	public static class purpleBackgroundOnEnter implements EventHandler<MouseEvent>{
		Button btn;
		
		public purpleBackgroundOnEnter(Button btn) {
			this.btn=btn;
		}
		
		@Override
		public void handle(MouseEvent event) {
			btn.setStyle("-fx-border-radius:20.0;" + 
							"-fx-background-radius:50;" + 
							"-fx-background-color: #EFE2FE;" + 
							"-fx-padding: 0 0 0 0;" + 
							"-fx-border-color:#aeaeae;"+
							"-fx-font-size:20;"+
							"-fx-font-weight:bold;");
		}
	}
		
	public static class purpleBackgroundOnExit implements EventHandler<MouseEvent>{
		Button btn;
	
		public purpleBackgroundOnExit(Button btn) {
			this.btn = btn;
		}

		@Override
		public void handle(MouseEvent e) {
			btn.setStyle("-fx-background-color:#2B0548;" + 
					"-fx-background-radius:50;" + 
					"-fx-border-radius:50;" + 
					"-fx-padding: 0 0 0 0;" + 
					"-fx-border-color:#aeaeae;"+
					"-fx-font-size:20;"+
					"-fx-font-weight:bold;");
		}
	}
	
	
	/*
	 * Green
	 */
	public static class greenBackgroundOnEnter implements EventHandler<MouseEvent>{
		Button btn;
		
		public greenBackgroundOnEnter(Button btn) {
			this.btn=btn;
		}
		
		@Override
		public void handle(MouseEvent event) {
			btn.setStyle("-fx-border-radius:20.0;" + 
							"-fx-background-radius:50;" + 
							"-fx-background-color: #58d058;" + 
							"-fx-padding: 0 0 0 0;" + 
							"-fx-border-color:#aeaeae;"+
							"-fx-font-size:20;"+
							"-fx-font-weight:bold;");
		}
	}
		
	public static class greenBackgroundOnExit implements EventHandler<MouseEvent>{
		Button btn;
	
		public greenBackgroundOnExit(Button btn) {
			this.btn = btn;
		}

		@Override
		public void handle(MouseEvent e) {
			btn.setStyle("-fx-background-color:Green;" + 
					"-fx-background-radius:50;" + 
					"-fx-border-radius:50;" + 
					"-fx-padding: 0 0 0 0;" + 
					"-fx-border-color:#aeaeae;"+
					"-fx-font-size:20;"+
					"-fx-font-weight:bold;");
		}
	}
	
	
	/*
	 * RED
	 */
	public static class redBackgroundOnEnter implements EventHandler<MouseEvent>{
		Button btn;
		
		public redBackgroundOnEnter(Button btn) {
			this.btn=btn;
		}
		
		@Override
		public void handle(MouseEvent event) {
			btn.setStyle("-fx-border-radius:20.0;" + 
							"-fx-background-radius:50;" + 
							"-fx-background-color: #ff5d5d;" + 
							"-fx-padding: 0 0 0 0;" + 
							"-fx-border-color:#aeaeae;"+
							"-fx-font-size:20;"+
							"-fx-font-weight:bold;");
		}
	}
		
	public static class redBackgroundOnExit implements EventHandler<MouseEvent>{
		Button btn;
	
		public redBackgroundOnExit(Button btn) {
			this.btn = btn;
		}

		@Override
		public void handle(MouseEvent e) {
			btn.setStyle("-fx-background-color: red;" + 
					"-fx-background-radius:50;" + 
					"-fx-border-radius:50;" + 
					"-fx-padding: 0 0 0 0;" + 
					"-fx-border-color:#aeaeae;"+
					"-fx-font-size:20;"+
					"-fx-font-weight:bold;");
		}
	}
}
