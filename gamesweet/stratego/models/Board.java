package gamesweet.stratego.models;

import gamesweet.stratego.models.Tile;
import gamesweet.stratego.models.Player;

public class Board {
    private Tile[][] board;
    private Player currentTurn;
	    
    public Tile[][] getBoard() {
		return board;
	}
	public void setBoard(Tile[][] board) {
		this.board = board;
	}
	public Player getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(Player currentTurn) {
		this.currentTurn = currentTurn;
	} 

}