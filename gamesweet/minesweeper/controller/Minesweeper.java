package gamesweet.minesweeper.controller;

import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;
import gamesweet.minesweeper.GUI.GUI;
import gamesweet.minesweeper.model.Board;
import javafx.stage.Stage;

public class Minesweeper extends Game{

	public Minesweeper(PlayerAmount playerAmount) {
		super(playerAmount);
		
	}

	@Override
	public void run() {
		
	}


	@Override
	public void init(Stage stage, String... playerName) {
		GUI gui = new GUI();
		gui.run(stage);
		run();
	}
}

