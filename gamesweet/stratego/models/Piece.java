package gamesweet.stratego.models;

public abstract class Piece {
    private final String name;
    private final int rank;

    public Piece(String name, int rank){
        this.name = name;
        this.rank = rank;
    }

	public String getName() {
		return name;
	}

	public int getRank() {
		return rank;
	}
}
