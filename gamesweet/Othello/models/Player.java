package gamesweet.Othello.models;

public class Player {
	private int score;
	private final String name;
	private int playOptions;
	
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

	public int getPlayOptions() {
		return playOptions;
	}

	public void setPlayOptions(int playOptions) {
		this.playOptions = playOptions;
	}
	
}
