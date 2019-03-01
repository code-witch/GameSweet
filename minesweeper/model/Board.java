package gamesweet.minesweeper.model;

import java.util.Random;
import gamesweet.minesweeper.enumpkg.Squares;

public class Board {
	private Square[][] board = new Square[9][9];
	private Random rnd = new Random();
	
	public void setUpBoard() {
		/* 
		 * Get the length of the board 
		 * Add mine squares to the board first (easy: 10 mines, medium: 40 mines, hard:99 mines, custom: ? mines)
		 * Then fill the rest of the board with empty squares
		 * 	if(there's a mine square near the empty square){
		 * 		change the empty square from empty to number
		 * 	} else {
		 * 		leave the square as an empty square
		 * 	}
		 * For the number square call the checkForMines() to figure out what number to show on screen
		 * 
		 */
		
//		changeBoardSize();
		int boardsizex = board.length;
		int boardsizey = board[0].length;
		int mines = amountOfMines(boardsizey);

		// Set up mines
		for (int i = 0; i < mines; i++) {
			Square minesquare = new Square(false, Squares.MINE);
			int x = rnd.nextInt(boardsizex);
			int y = rnd.nextInt(boardsizey);
			y = checkIfOccupiedByMine(x, y);
			board[x][y] = minesquare;
		}

		// Fills the rest of the board with empty spaces
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					Square empty = new Square(false, Squares.EMPTY);
					board[i][j] = empty;
				}
			}
		}
		// Set up the number squares on the board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				checkAroundSquare(i, j);
			}
		}

	}

	private int checkIfOccupiedByMine(int x, int y) {
		// Check if a specific space is already occupied
		if (board[x][y] != null) {
			y = rnd.nextInt(board[0].length);
			checkIfOccupiedByMine(x, y);
		}
		return y;
	}

	private int amountOfMines(int boardsizey) {
		// Sets the amount of mines placed on the board
		int amountOfMines = 0;
		if (boardsizey == 9) {
			amountOfMines = 10;
		} else if (boardsizey == 16) {
			amountOfMines = 40;
		} else if (boardsizey == 30) {
			amountOfMines = 99;
		}
		return amountOfMines;
	}

	public void changeBoardSize() {
		//Change size of board array depending on the difficulty (easy:9x9, medium:16x16, hard:16x30, custom:?x?)
		
	}
	public void checkAroundSquare(int row, int col) {
		//Checks the 9 squares around the specific square to see if its a mine 
		Square number;
		int mines = 0;
		
		for(int i = row - 1; i <= row + 1; i++) {
			for(int j = col - 1; j <= col + 1; j++) {
				if(i == row && j == col) {
					continue;
				} else if(i < 0 || i > board[0].length - 1) {
					continue;
				} else if(j < 0 || j > board[0].length - 1) {
					continue;
				} else if(board[i][j].getSquareType() == Squares.MINE){
					mines++;
				}
			}
		}
		if(mines > 0) {
			number = new Square(false, Squares.NUMBER);
			board[row][col] = number;
		} 
	}
}
