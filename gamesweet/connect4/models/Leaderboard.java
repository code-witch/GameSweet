package gamesweet.connect4.models;

import java.io.Serializable;
import java.util.HashMap;

public class Leaderboard implements Serializable{
	private static final long serialVersionUID = 5537828521763831851L;
	
	private HashMap<String, Player> playersL;
	
	public Leaderboard() {
		setPlayersL(new HashMap<String, Player>());
	}

	public HashMap<String, Player> getPlayersL() {
		return playersL;
	}

	public void setPlayersL(HashMap<String, Player> playersL) {
		this.playersL = playersL;
	}
	
	public void addPlayer(Player player) {
		String key = player.getID();
		HashMap<String, Player> leads = getPlayersL();
		leads.put(key, player);
		setPlayersL(leads);
	}
	
}
