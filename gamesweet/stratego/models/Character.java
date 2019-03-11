package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Color;
import gamesweet.stratego.enumerations.Direction;
import gamesweet.stratego.interfaces.Movable;

public class Character extends Piece implements Movable {
	private final CharacterType type;

	public Character(String name, int rank, Color color, CharacterType type) {
		super(name, rank, color);
		this.type = type;
	}

	public CharacterType getType() {
		return type;
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
		if (piece.getRank() == -1) {
			// this is water
			return;
		} else if(piece.getRank() == 0) {
			// this is a flag
		} else if(piece.getRank() == 1) {
			// this is a spy
		}
		
		if (piece.getRank() < this.getRank()) {
			move(direction, 1);
		} else if(piece.getRank() > this.getRank()) {
			if (piece instanceof Character) {
				((Character) piece).move(Direction.reverse(direction), 0);
			}
			// this piece dies
		}
	}

}