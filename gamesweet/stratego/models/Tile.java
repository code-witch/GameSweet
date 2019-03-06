package gamesweet.stratego.models;

public class Tile {
    private Piece owner;
    
    public Tile() { }

    public boolean isOccupied() { return owner != null; }

	public Piece getOwner() {
		return owner;
	}

	public void setOwner(Piece owner) {
		this.owner = owner;
	}


}