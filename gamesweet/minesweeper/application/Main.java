package gamesweet.minesweeper.application;
	
import gamesweet.minesweeper.controller.Minesweeper;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new Minesweeper().init(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
