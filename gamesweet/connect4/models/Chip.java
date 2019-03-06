package gamesweet.connect4.models;

import gamesweet.connect4.enums.ChipColor;

public class Chip {
	private final ChipColor chipColor;
	
	public Chip(ChipColor chipColor) {
		this.chipColor = chipColor;
	}
	
	public ChipColor getChipColor() {
		return chipColor;
	}
	
}
