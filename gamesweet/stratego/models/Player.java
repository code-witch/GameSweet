package gamesweet.stratego.models;

import java.util.ArrayList;
import java.util.Collections;

import gamesweet.stratego.enumerations.CharacterType;
import gamesweet.stratego.enumerations.Color;

public class Player {
    private final String name;
    private final Color color;
    private Piece[][] pieces = new Piece[4][10];
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        initPieces();
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
		pieces[0][0] = new Character("1", 1, color, CharacterType.SPY);
		pieces[0][1] = new Flag("F", 0, color);
		pieces[0][2] = new Character("10", 10, color, CharacterType.MARSHAL);
		pieces[0][3] = new Character("9",9,color,CharacterType.GENERAL);
		
		pieces[0][4] = new Character("8",8,color,CharacterType.COLONEL);
		pieces[0][5] = new Character("8",8,color,CharacterType.COLONEL);
		
		pieces[0][6] = new Character("7",7,color,CharacterType.MAJOR);
		pieces[0][7] = new Character("7",7,color,CharacterType.MAJOR);
		pieces[0][8] = new Character("7",7,color,CharacterType.MAJOR);
		
		pieces[0][9] = new Character("6",6,color,CharacterType.CAPTAIN);
		pieces[1][0] = new Character("6",6,color,CharacterType.CAPTAIN);
		pieces[1][1] = new Character("6",6,color,CharacterType.CAPTAIN);
		pieces[1][2] = new Character("6",6,color,CharacterType.CAPTAIN);

		pieces[1][3] = new Character("5",5,color,CharacterType.LIEUTENANT);
		pieces[1][4] = new Character("5",5,color,CharacterType.LIEUTENANT);
		pieces[1][5] = new Character("5",5,color,CharacterType.LIEUTENANT);
		pieces[1][6] = new Character("5",5,color,CharacterType.LIEUTENANT);
		
		pieces[1][7] = new Character("4",4,color,CharacterType.SERGEANT);
		pieces[1][8] = new Character("4",4,color,CharacterType.SERGEANT);
		pieces[1][9] = new Character("4",4,color,CharacterType.SERGEANT);
		pieces[2][0] = new Character("4",4,color,CharacterType.SERGEANT);

		pieces[2][1] = new Character("3",3,color,CharacterType.MINER);
		pieces[2][2] = new Character("3",3,color,CharacterType.MINER);
		pieces[2][3] = new Character("3",3,color,CharacterType.MINER);
		pieces[2][4] = new Character("3",3,color,CharacterType.MINER);
		pieces[2][5] = new Character("3",3,color,CharacterType.MINER);
		
		pieces[2][6] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[2][7] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[2][8] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[2][9] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[3][0] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[3][1] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[3][2] = new Character("2",2,color,CharacterType.SCOUT);
		pieces[3][3] = new Character("2",2,color,CharacterType.SCOUT);
		
		pieces[3][4] = new Bomb("B",100,color);
		pieces[3][5] = new Bomb("B",100,color);
		pieces[3][6] = new Bomb("B",100,color);
		pieces[3][7] = new Bomb("B",100,color);
		pieces[3][8] = new Bomb("B",100,color);
		pieces[3][9] = new Bomb("B",100,color);
		
		ArrayList<Piece> al = new ArrayList<>();
		for(int i = 0; i < pieces.length;i++) {
			for(int j = 0; j < pieces[i].length; j++) {
				al.add(pieces[i][j]);
			}
		}
		Collections.shuffle(al);
		int counter = 0;
		for(int i = 0; i < pieces.length;i++) {
			for(int j = 0; j < pieces[i].length; j++) {
				pieces[i][j] = al.get(counter);
				counter++;
			}
		}
	}




	
	
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
    
}