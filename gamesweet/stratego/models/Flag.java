package gamesweet.stratego.models;

import gamesweet.stratego.enumerations.Color;

public class Flag extends Piece {
    private boolean isCaptured = false;

    public Flag(String name, int rank, Color color){
        super(name,rank, color);
    }

	public boolean isCaptured() {
		return isCaptured;
	}

	public void setCaptured(boolean isCaptured) {
		this.isCaptured = isCaptured;
	}
    
    
}