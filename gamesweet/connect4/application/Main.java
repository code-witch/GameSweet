package gamesweet.connect4.application;
	
import gamesweet.connect4.controllers.ConnectFour;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new ConnectFour().init(primaryStage, "Player 1", "Player 2");
	}
	
	public static void main(String[] arg) {
		launch(arg);
	}
}
