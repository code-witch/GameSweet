package gamesweet.stratego.controller;

import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;
import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Color;
import gamesweet.stratego.models.*;
import gamesweet.stratego.models.Character;
import javafx.stage.Stage;

public class Stratego extends Game {
	Player playerOne, playerTwo;
	Board board = new Board();

	public Stratego(PlayerAmount playerAmount) {
		super(playerAmount);
	}

	@Override
	public void init(Stage stage, String... playerName) {
		playerOne = new Player(playerName[0], Color.RED);
		playerTwo = new Player(playerName[1], Color.BLUE);
		board.setBoard(new Tile[10][10]);
		Tile[][] t = board.getBoard();
		for (int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[i].length; j++) {
				t[i][j] = new Tile();
			}
		}
		initCharacters();
	}

	@Override
	public void run() {

	}

	private void initCharacters() {
				
	}

}