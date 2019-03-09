package gamesweet.minesweeper.model;

import java.util.Random;
import gamesweet.minesweeper.enumpkg.Squares;

public class Board {
	private Square[][] boardGrid = new Square[9][9];
	private Random rnd = new Random();
	private int mines;

	public void setUpBoard() {

		int boardsizex = boardGrid.length;
		int boardsizey = boardGrid[0].length;
		mines = amountOfMines(boardsizey);

		// Set up mines
		for (int i = 0; i < mines; i++) {
			Square minesquare = new Square(false, Squares.MINE, 0);
			int x = rnd.nextInt(boardsizex);
			int y = rnd.nextInt(boardsizey);
			y = checkIfOccupiedByMine(x, y);
			boardGrid[x][y] = minesquare;
		}

		// Fills the rest of the board with empty spaces
		for (int i = 0; i < boardGrid.length; i++) {
			for (int j = 0; j < boardGrid[i].length; j++) {
				if (boardGrid[i][j] == null) {
					Square empty = new Square(false, Squares.EMPTY, 0);
					boardGrid[i][j] = empty;
				}
			}
		}
		// Set up the number squares on the board
		for (int i = 0; i < boardGrid.length; i++) {
			for (int j = 0; j < boardGrid[i].length; j++) {
				if (boardGrid[i][j].getSquareType() == Squares.MINE) {
					continue;
				}
				checkAroundSquare(i, j);
			}
		}

	}

	private int checkIfOccupiedByMine(int x, int y) {
		// Check if a specific space is already occupied

		if (boardGrid[x][y] != null) {
			y = rnd.nextInt(boardGrid[0].length);
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

	public void changeBoardSize(int x, int y) {
		boardGrid = new Square[x][y];
	}

	public void checkAroundSquare(int row, int col) {
		// Checks the 9 squares around the specific square to see if its a mine
		Square number;
		int mines = 0;

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i == row && j == col) {
					continue;
				} else if (i < 0 || i > boardGrid.length - 1) {
					continue;
				} else if (j < 0 || j > boardGrid[0].length - 1) {
					continue;
				} else if (boardGrid[i][j].getSquareType() == Squares.MINE) {
					mines += 1;
				}
			}
		}
		if (mines > 0) {
			number = new Square(false, Squares.NUMBER, mines);
			boardGrid[row][col] = number;
		}
	}

	public Square[][] getBoard() {
		return boardGrid;
	}

	public void setBoard(Square[][] board) {
		this.boardGrid = board;
	}

	public int getMines() {
		return mines;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}
}
