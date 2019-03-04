package gamesweet.minesweeper.GUI;

import javafx.stage.Stage;
import gamesweet.minesweeper.enumpkg.Squares;
import gamesweet.minesweeper.model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI {

	

	public static void run(Stage primaryStage, Board board) {
		try {
			VBox vBox = new VBox();
			primaryStage.setTitle("Minesweeper");

			// Set up the toolbar on the top of the GUI
			Menu gameMenu = new Menu("Game");
			gameMenu.getItems().add(new MenuItem("Difficulty"));
			gameMenu.getItems().add(new MenuItem("View Highscores"));
			Menu helpMenu = new Menu("Help");
			MenuItem h2P = new MenuItem("How to Play");
			h2P.setOnAction(e -> AlertBox.display());
			helpMenu.getItems().addAll(h2P, new MenuItem("Go back to HUB"));
			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(gameMenu, helpMenu);

			// Set up the smileyface button
			HBox smiley = new HBox();
			Button smileyButton = new Button("Smiley Face");
			Button timer = new Button("Timer");
			Button score = new Button("score");

			smiley.getChildren().addAll(score, smileyButton, timer);

			// Set up Grid
			GridPane grid = new GridPane();
			for (int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					Button square = new Button();
					if (board.getBoard()[row][col].getSquareType() == Squares.MINE) {
						square.setStyle("-fx-background-color : blue");
					}
					if(board.getBoard()[row][col].getSquareType() == Squares.NUMBER) {
						square.setStyle("-fx-background-color : red");
					}
					square.setPadding(new Insets(10, 20, 10, 20));
					grid.add(square, col, row);
				}
			}
			grid.setAlignment(Pos.CENTER);
			grid.setPadding(new Insets(10, 0, 0, 0));
			vBox.getChildren().addAll(menuBar, smiley, grid);
			Scene scene = new Scene(vBox, 290, 440);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
