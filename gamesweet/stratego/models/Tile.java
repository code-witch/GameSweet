package gamesweet.stratego.models;

public class Tile {
    private Piece owner;

    public Tile() { }

    public boolean isOccupied() { return owner != null; }

    //TODO: add getters and setters
}