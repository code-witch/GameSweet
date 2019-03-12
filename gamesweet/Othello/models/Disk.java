package gamesweet.Othello.models;

import java.util.Arrays;

public class Disk {
	private String[] colors = {"black", "white"};
	private String sideColor = "";
	private boolean isSandWitched = false;
	private boolean topLeftDiagValid;
	private boolean vrtUpValid;
	private boolean topRightDiagValid;
	private boolean horRightValid;
	private boolean botRightDiagValid;
	private boolean vrtDwnValid;
	private boolean botLeftDiagValid;
	private boolean horLeftValid;

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

	public boolean isTopLeftDiagValid() {
		return topLeftDiagValid;
	}

	public void setTopLeftDiagValid(boolean topLeftDiagValid) {
		this.topLeftDiagValid = topLeftDiagValid;
	}

	public boolean isVrtUpValid() {
		return vrtUpValid;
	}

	public void setVrtUpValid(boolean vrtUpValid) {
		this.vrtUpValid = vrtUpValid;
	}

	public boolean isTopRightDiagValid() {
		return topRightDiagValid;
	}

	public void setTopRightDiagValid(boolean topRightDiagValid) {
		this.topRightDiagValid = topRightDiagValid;
	}

	public boolean isHorRightValid() {
		return horRightValid;
	}

	public void setHorRightValid(boolean horRightValid) {
		this.horRightValid = horRightValid;
	}

	public boolean isBotRightDiagValid() {
		return botRightDiagValid;
	}

	public void setBotRightDiagValid(boolean botRightDiagValid) {
		this.botRightDiagValid = botRightDiagValid;
	}

	public boolean isVrtDwnValid() {
		return vrtDwnValid;
	}

	public void setVrtDwnValid(boolean vrtDwnValid) {
		this.vrtDwnValid = vrtDwnValid;
	}

	public boolean isBotLeftDiagValid() {
		return botLeftDiagValid;
	}

	public void setBotLeftDiagValid(boolean botLeftDiagValid) {
		this.botLeftDiagValid = botLeftDiagValid;
	}

	public boolean isHorLeftValid() {
		return horLeftValid;
	}

	public void setHorLeftValid(boolean horLeftValid) {
		this.horLeftValid = horLeftValid;
	}

	

}
