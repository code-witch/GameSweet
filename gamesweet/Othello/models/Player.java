package gamesweet.Othello.models;

public class Player {
	private Disk[] disks;
	private int score;
	private int numOfDisks;
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

	public int getNumOfDisks() {
		return numOfDisks;
	}

	public void setNumOfDisks(int numOfDisks) {
		this.numOfDisks = numOfDisks;
	}

	public Disk[] getDisks() {
		return disks;
	}

	public String getName() {
		return name;
	}
	
}
