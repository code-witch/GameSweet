package gamesweet.minesweeper.controller;

import gamesweet.minesweeper.GUI.GUI;
import gamesweet.minesweeper.model.Board;
import javafx.stage.Stage;

public class Minesweeper {

	public void run() {
		Board board = new Board();
		board.setUpBoard();
	}

	public void init(Stage primaryStage) {
		GUI.run(primaryStage);
		run();
	}
}
