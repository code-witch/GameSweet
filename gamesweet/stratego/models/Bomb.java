package gamesweet.stratego.models;

import gamesweet.stratego.models.Piece;

public class Bomb extends Piece {
    private boolean isDefused = false;
    
    public Bomb(String name, int rank){
        super(name,rank);
    }

    // TODO: add getter and setters
}