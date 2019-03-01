package gamesweet.minesweeper.GUI;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class GUI {

	public static void run(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();

			Menu gameMenu = new Menu("Game");
			gameMenu.getItems().add(new MenuItem("Difficulty"));
			gameMenu.getItems().add(new MenuItem("View Highscore"));
			gameMenu.getItems().add(new MenuItem("Reset"));

			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
