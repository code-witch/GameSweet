package models;

import java.util.ArrayList;

public class Player {
	private ArrayList<Chip> chips;
	private String name;
	private String ID;
	private int wins;
	private int losses;
	private String slogan;
	
	public Player(String name, ArrayList<Chip> chips) {
		setName(name);
		setChips(chips);
		setID("Guest");
		
	}
	public Player(String name, String ID, ArrayList<Chip> chips) {
		setName(name);
		setID(ID);
		setWins(0);
		setLosses(0);
		setSlogan("");
		setChips(chips);
	}
	public ArrayList<Chip> getChips() {
		return chips;
	}
	public void setChips(ArrayList<Chip> chips) {
		this.chips = chips;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	
	

}
