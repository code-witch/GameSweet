package gamesweet.stratego.controller;

import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;
import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Color;
import gamesweet.stratego.models.*;
import gamesweet.stratego.models.Character;
import javafx.stage.Stage;

public class Stratego extends Game {
	private Player playerOne, playerTwo;
	private Board board = new Board();
	private GUI gui = new GUI();
	
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
		initBoard();
		gui.init(stage,board);
		run();
	}

	@Override
	public void run() {
//		for(Tile[] t: board.getBoard()) {
//			for (Tile tt: t) {
//				System.out.println(tt.getOwner());
//			}
//		}
	}

	public void initBoard() {
		for(int i = 0; i < 4;i++) {
			for(int j = 0; j < 10; j++) {
				board.getBoard()[j][i].setOwner(playerOne.getPieces()[i][j]);
			}
		}	
		board.getBoard()[2][4].setOwner(new Piece("", 999, null) {});
		board.getBoard()[3][4].setOwner(new Piece("", 999, null) {});
		board.getBoard()[2][5].setOwner(new Piece("", 999, null) {});
		board.getBoard()[3][5].setOwner(new Piece("", 999, null) {});
		
		board.getBoard()[6][4].setOwner(new Piece("", 999, null) {});
		board.getBoard()[7][4].setOwner(new Piece("", 999, null) {});
		board.getBoard()[6][5].setOwner(new Piece("", 999, null) {});
		board.getBoard()[7][5].setOwner(new Piece("", 999, null) {});
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 10; j++) {
				board.getBoard()[j][i+6].setOwner(playerTwo.getPieces()[i][j]);
			}
		}
	}
	
}