package models;

import enums.ChipColor;

public class Chip {
	private boolean used;
	private final ChipColor chipColor;
	
	public Chip(ChipColor chipColor) {
		this.chipColor = chipColor;
	}
	
	public ChipColor getChipColor() {
		return chipColor;
	}
	
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	
}
