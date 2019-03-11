package gamesweet.Othello.models;

import java.util.Arrays;

public class Disk {
	private String[] colors = {"black", "white"};
	private String sideColor = "";
	private boolean isSandWitched = false;

	public Disk() {
	}	

	public String getSideColor() {
		return sideColor;
	}

	public void setSideColor(String sideColor) {
		this.sideColor = sideColor;
	}

	public String[] getColors() {
		return colors;
	}

	public boolean isSandWitched() {
		return isSandWitched;
	}

	public void setSandWitched(boolean isSandWitched) {
		this.isSandWitched = isSandWitched;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Disk ").append(sideColor)
				.append(", ").append(isSandWitched).append("]");
		return builder.toString();
	}
	

}
