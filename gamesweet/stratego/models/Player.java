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
		pieces[0][1] = new Flag("Flag", 0, color);
		pieces[0][2] = new Character("Marshal", 10, color, CharacterType.MARSHAL);
		pieces[0][3] = new Character("General",9,color,CharacterType.GENERAL);
		
		pieces[0][4] = new Character("Colonel",8,color,CharacterType.COLONEL);
		pieces[0][5] = new Character("Colonel",8,color,CharacterType.COLONEL);
		
		pieces[0][6] = new Character("Major",7,color,CharacterType.MAJOR);
		pieces[0][7] = new Character("Major",7,color,CharacterType.MAJOR);
		pieces[0][8] = new Character("Major",7,color,CharacterType.MAJOR);
		
		pieces[0][9] = new Character("Captain",6,color,CharacterType.CAPTAIN);
		pieces[1][0] = new Character("Captain",6,color,CharacterType.CAPTAIN);
		pieces[1][1] = new Character("Captain",6,color,CharacterType.CAPTAIN);
		pieces[1][2] = new Character("Captain",6,color,CharacterType.CAPTAIN);

		pieces[1][3] = new Character("Lieutenant",5,color,CharacterType.LIEUTENANT);
		pieces[1][4] = new Character("Lieutenant",5,color,CharacterType.LIEUTENANT);
		pieces[1][5] = new Character("Lieutenant",5,color,CharacterType.LIEUTENANT);
		pieces[1][6] = new Character("Lieutenant",5,color,CharacterType.LIEUTENANT);
		
		pieces[1][7] = new Character("Sergeant",4,color,CharacterType.SERGEANT);
		pieces[1][8] = new Character("Sergeant",4,color,CharacterType.SERGEANT);
		pieces[1][9] = new Character("Sergeant",4,color,CharacterType.SERGEANT);
		pieces[2][0] = new Character("Sergeant",4,color,CharacterType.SERGEANT);

		pieces[2][1] = new Character("Miner",3,color,CharacterType.MINER);
		pieces[2][2] = new Character("Miner",3,color,CharacterType.MINER);
		pieces[2][3] = new Character("Miner",3,color,CharacterType.MINER);
		pieces[2][4] = new Character("Miner",3,color,CharacterType.MINER);
		pieces[2][5] = new Character("Miner",3,color,CharacterType.MINER);
		
		pieces[2][6] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[2][7] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[2][8] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[2][9] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[3][0] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[3][1] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[3][2] = new Character("Scout",2,color,CharacterType.SCOUT);
		pieces[3][3] = new Character("Scout",2,color,CharacterType.SCOUT);
		
		pieces[3][4] = new Bomb("Bomb",100,color);
		pieces[3][5] = new Bomb("Bomb",100,color);
		pieces[3][6] = new Bomb("Bomb",100,color);
		pieces[3][7] = new Bomb("Bomb",100,color);
		pieces[3][8] = new Bomb("Bomb",100,color);
		pieces[3][9] = new Bomb("Bomb",100,color);
		
	}




	
	
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
    
}