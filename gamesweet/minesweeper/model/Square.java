package gamesweet.minesweeper.model;

import gamesweet.minesweeper.enumpkg.Squares;

public class Square {
	private boolean isRevealed;
	private Squares squareType;
	private int mineAmount;
	
	public Square(boolean isRevealed, Squares squareType, int mineAmount) {
		setRevealed(isRevealed);
		setSquareType(squareType);
		setMineAmount(mineAmount);
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

	public int getMineAmount() {
		return mineAmount;
	}

	public void setMineAmount(int mineAmount) {
		this.mineAmount = mineAmount;
	}
	
}
