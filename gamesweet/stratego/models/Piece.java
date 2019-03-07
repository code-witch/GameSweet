package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.Color;

public abstract class Piece {
    private final String name;
    private final int rank;
    private final Color color;

    public Piece(String name, int rank, Color color){
        this.name = name;
        this.rank = rank;
        this.color = color;
    }

    public Color getColor() {
    	return color;
    }
    
	public String getName() {
		return name;
	}

	public int getRank() {
		return rank;
	}
}
