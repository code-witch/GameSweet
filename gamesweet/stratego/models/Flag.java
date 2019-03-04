package gamesweet.stratego.models;

import gamesweet.stratego.models.Piece;

public class Flag extends Piece {
    private boolean isCaptured = false;

    public Flag(String name, int rank){
        super(name,rank);
    }
}