package gamesweet.connect4.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	private static final long serialVersionUID = -3006259327832435648L;
	
	private ArrayList<Chip> chips;
	private String name;
	private String ID;
	private int wins;
	private int losses;
	private String slogan;
	private boolean saved;
	
	public Player(String name, ArrayList<Chip> chips) {
		setName(name);
		setChips(chips);
		setID("Guest");
		setSaved(false);
		
	}
	
	public Player(String name, String ID, String slogan) {
		setName(name);
		setID(ID);
		setWins(0);
		setLosses(0);
		setSlogan(slogan);
		setSaved(true);
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
	
	public void removeChip() {
		int c = getChips().size();
		ArrayList<Chip> chips = getChips();
		chips.remove(c-1);
		setChips(chips);
	}
	
	public boolean getSaved() {
		return saved;
	}
	
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [getChips()=");
		builder.append(getChips());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getWins()=");
		builder.append(getWins());
		builder.append(", getLosses()=");
		builder.append(getLosses());
		builder.append(", getSlogan()=");
		builder.append(getSlogan());
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
