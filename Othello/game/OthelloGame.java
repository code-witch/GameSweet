package gamesweet.Othello.game;

import gamesweet.Othello.models.*;

public class OthelloGame {
	private Disk[][] board;
	private Player[] players;
	
	public void run() {
		init();
		
	}
	
	public void init() {
		
	}
	
	private void gameMenu() {
		String[] options = {"new game", "load game", "read rules"};
		
	}
	
	private void newGame() {
		
	}
	
	private void loadGame() {
		
	}
	
	private void saveGame() {
		
	}
	
	private void setUpGame() {
		board =  new Disk[8][8];
		board[3][3] = new Disk();
		board[3][3].setSideColor(board[3][3].getColor(1));
		board[3][4] = new Disk();
		board[3][4].setSideColor(board[3][4].getColor(0));
		board[4][3] = new Disk();
		board[4][3].setSideColor(board[4][3].getColor(0));
		board[4][4] = new Disk();
		board[4][4].setSideColor(board[4][4].getColor(1));
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; i++) {	
				System.out.print(board[i][j] + "\t");
				
			}
			System.out.println();
		}
	}
	
	private void playGame() {
		setUpGame();
	}
	
	private void printRules() {
		
	}
	
	private void printNotifcation() {
		
	}
	
	private void printBoard() {
		
	}
	
	private void validateSaveFile() {
		
	}

}
