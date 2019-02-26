package models;

public class Board {
	private Space[][] spaces;
	
	public Board(Space[][] spaces) {
		setSpaces(spaces);
	}

	public Space[][] getSpaces() {
		return spaces;
	}

	public void setSpaces(Space[][] spaces) {
		this.spaces = spaces;
	}
	
}
