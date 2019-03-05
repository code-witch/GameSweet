package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Direction;
import gamesweet.stratego.interfaces.Movable;
import gamesweet.stratego.models.Piece;

public class Character extends Piece implements Movable {
	private CharacterType type;

	public Character(String name, int rank, CharacterType type) {
		super(name, rank);
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
		if (type == CharacterType.SCOUT) {
			// do special movement here..
		} else if (type != CharacterType.SCOUT && amount > 1) {
			System.out.println("They can't move that character.."); // remove this
			return;
		} else {
			System.out.println("Move the character in the direction");
		}
	}

	@Override
	public void attack(Direction direction, Piece piece) {
		if (piece.getRank() < this.getRank()) {
			move(direction, 1);
		}
	}

}