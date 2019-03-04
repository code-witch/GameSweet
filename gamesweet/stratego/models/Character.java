package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.models.Piece;

public class Character extends Piece{
    CharacterType type;

    public Character(String name, int rank, CharacterType type){
        super(name,rank);
        this.type = type;
    }

    //TODO: add getters and setters

}