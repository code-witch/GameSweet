package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Direction;
import gamesweet.stratego.interfaces.Movable;
import gamesweet.stratego.models.Piece;

public class Character extends Piece implements Movable {
    protected CharacterType type;

    public Character(String name, int rank, CharacterType type){
        super(name,rank);
        this.type = type;
    }

	public CharacterType getType() {
		return type;
	}

	public void setType(CharacterType type) {
		this.type = type;
	}

	@Override
	public void move(Direction directioon, int amount) {
		
	}

	@Override
	public void attack(Direction direction) {
		
	}


}