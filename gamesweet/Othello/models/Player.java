package gamesweet.Othello.models;

public class Player {
	private int score;
	private final String name;
	
	public Player(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}
	
}
