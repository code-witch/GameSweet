package gamesweet.connect4.models;

import java.io.Serializable;

import gamesweet.connect4.enums.ChipColor;

public class Chip implements Serializable {
	
	private static final long serialVersionUID = 4741536813816323698L;
	
	private final ChipColor chipColor;
	
	public Chip(ChipColor chipColor) {
		this.chipColor = chipColor;
	}
	
	public ChipColor getChipColor() {
		return chipColor;
	}
	
}
