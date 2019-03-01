package gamesweet.minesweeper.GUI;

import javafx.stage.Stage;



import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class GUI {

	public static void run(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			primaryStage.setTitle("Minesweeper");
			
			//Set up the toolbar on the top of the GUI
			Menu gameMenu = new Menu("Game");
			gameMenu.getItems().add(new MenuItem("Difficulty"));
			gameMenu.getItems().add(new MenuItem("View Highscores"));
			Menu helpMenu = new Menu("Help");
			helpMenu.getItems().add(new MenuItem("How to Play"));
			helpMenu.getItems().add(new MenuItem("Cheat Mode"));
			helpMenu.getItems().add(new MenuItem("Go back to HUB"));
			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(gameMenu, helpMenu);
			
			root.setTop(menuBar);
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
