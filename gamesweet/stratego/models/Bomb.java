package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.Color;

public class Bomb extends Piece {
	private boolean isDefused = false;

	public Bomb(String name, int rank, Color color) {
		super(name, rank, color);
	}

	public boolean isDefused() {
		return isDefused;
	}

	public void setDefused(boolean isDefused) {
		this.isDefused = isDefused;
	}

}