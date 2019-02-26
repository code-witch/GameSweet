package gamesweet.connect4.models;

import java.util.HashMap;

public class Leaderboard {
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
