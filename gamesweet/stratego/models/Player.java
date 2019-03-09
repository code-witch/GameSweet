package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.Color;

public class Player {
    private final String name;
    private final Color color;
    
    
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
    
}