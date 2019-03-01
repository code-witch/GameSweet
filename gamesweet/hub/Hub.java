package gamesweet.hub;

import java.util.HashMap;

import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;

import javafx.stage.Stage;

public class Hub {

	private HashMap<String, Game> gameOptions = new HashMap<>();

	public Hub() {
		gameOptions.put("Game1", new Game(PlayerAmount.TWO) {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}

			@Override
			public void init(Stage stage, String... playerName) {
				// TODO Auto-generated method stub
			}
		});
		gameOptions.put("Game2", new Game(PlayerAmount.TWO) {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}

			@Override
			public void init(Stage stage, String... playerName) {
				// TODO Auto-generated method stub
			}
		});
		gameOptions.put("Game3", new Game(PlayerAmount.ONE) {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}

			@Override
			public void init(Stage stage, String... playerName) {
				// TODO Auto-generated method stub
			}
		});
		gameOptions.put("Game4", new Game(PlayerAmount.ONE) {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}

			@Override
			public void init(Stage stage, String... playerName) {
				// TODO Auto-generated method stub
			}
		});
	}

//	private void displayGames() {
//	// displays all available games	
//	}
//	
//	
//	private Game chooseGame() {
//		return null;
//	}
//	
//	private void playGame(Game game) {
////		game.getScene()
//		// this called last
//		game.run();
//	}
//	
//	public void run() {
//		playGame(chooseGame());
//	}

	public HashMap<String, Game> getGameOptions() {
		return gameOptions;
	}

	public void setGameOptions(HashMap<String, Game> gameOptions) {
		this.gameOptions = gameOptions;
	}

}
