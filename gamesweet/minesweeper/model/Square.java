package gamesweet.minesweeper.model;

import gamesweet.minesweeper.enumpkg.Squares;

public class Square {
	private boolean isRevealed;
	private Squares squareType;
	
	public Square(boolean isRevealed, Squares squareType) {
		this.setRevealed(isRevealed);
		this.setSquareType(squareType);
	}

	public boolean isRevealed() {
		return isRevealed;
	}

	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}

	public Squares getSquareType() {
		return squareType;
	}

	public void setSquareType(Squares squareType) {
		this.squareType = squareType;
	}
	
}
