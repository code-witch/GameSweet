package gamesweet.hub;

import java.util.HashMap;

import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;
import gamesweet.connect4.controllers.ConnectFour;
import gamesweet.minesweeper.controller.Minesweeper;
import gamesweet.stratego.controller.Stratego;
import gamesweet.Othello.game.OthelloGame;

import javafx.stage.Stage;

public class Hub {

	private HashMap<String, Game> gameOptions = new HashMap<>();

	public Hub() {
		gameOptions.put("Connect Four", new ConnectFour(PlayerAmount.TWO));
		gameOptions.put("Stratego", new Stratego(PlayerAmount.TWO));
    gameOptions.put("Othello", new OthelloGame(PlayerAmount.TWO));
		gameOptions.put("Minesweeper", new Minesweeper(PlayerAmount.ONE)); 
		}

	public HashMap<String, Game> getGameOptions() {
		return gameOptions;
	}

	public void setGameOptions(HashMap<String, Game> gameOptions) {
		this.gameOptions = gameOptions;
	}

}