package gamesweet.base;

import javafx.stage.Stage;

public abstract class Game {
	
	private PlayerAmount playerAmount;
	
	public Game(PlayerAmount playerAmount) {
		setPlayerAmount(playerAmount); 
	}
	
	/**
	 * initializes the game
	 */
	public abstract void init(Stage stage, String...playerName);
	
	/**
	 * runs the game
	 */
	public abstract void run(); 
	
	
	public PlayerAmount getPlayerAmount() {
		return playerAmount;
	}


	public void setPlayerAmount(PlayerAmount playerAmount) {
		this.playerAmount = playerAmount;
	}
}
