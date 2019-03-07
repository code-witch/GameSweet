package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Color;

public class Player {
    private final String name;
    private final Color color;
    private Piece[][] pieces = new Piece[4][10];
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }
    
    public void takeTurn() { }

	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public void initPieces() {
		pieces[0][0] = new Character("Spy", 1, color, CharacterType.SPY);
		pieces[0][1] = new Flag("Flag", 10, color);
	}

//	Bomb (6x)
//	Scout (8x)
//	Miner (5x)
//	Sergeant (4x)
//	Lieutenant (4x)
//	Captain (4x)
//	Major (3x)
//	Colonel (2x)
//	General (1x)
//	Marshall (1x)
	
	
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
    
}