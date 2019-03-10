package gamesweet.connect4.models;

public class Board {
	private Chip[][] chips;
	
	public Board(Chip[][] chips) {
		setChips(chips);
	}

	public Chip[][] getChips() {
		return chips;
	}

	public void setChips(Chip[][] chips) {
		this.chips = chips;
	}


	
}
