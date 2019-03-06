package gamesweet.connect4.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import gamesweet.connect4.models.*;
import javafx.stage.Stage;
import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;
import gamesweet.connect4.GUI.GUI;
import gamesweet.connect4.enums.*;

public class ConnectFour extends Game{
	public ConnectFour(PlayerAmount playerAmount) {
		super(playerAmount);
	}

	private String player1;
	private String player2;
	private Player[] players = new Player[2];
	private Player currentP;
	private Board board;
	public static int turnCount;
	private Leaderboard lb = new Leaderboard();
	private boolean containL;

	public void run() {
//		createPlayers();
//		playGame();
	}
	
	public boolean createSEn(String ID) {
		boolean contains = imCrSavedPlayers(ID);
		if (contains) {
			createBoard(selectDifficulty());
			System.out.println("ID is " + ID);
		}
//		for (Player p : lb.getPlayersL().values()) {
//			System.out.println(p);
//		}
		saveLeaderboard();
		return contains;
	}
	
	public void createGEn() {
		createBoard(selectDifficulty());
		createGuestPlayers();
	}

	public void init(Stage primaryStage, String... playerName) {
		//Calls the GUI class
		GUI.run(primaryStage);
		player1 = playerName[0];
		player2 = playerName[1];
//		run();
	}
	
	private boolean imCrSavedPlayers(String ID) {
		boolean contained = false;
		HashMap<String, Player> ps = lb.getPlayersL();
		contained = ps.containsKey(ID);
		return contained;
	}

	private void createGuestPlayers() {

		// Hard-coded names for Guest
		ArrayList<Chip> chips = new ArrayList<>();
		for (int i = 0; i < 21; i++) {
			Chip c = new Chip(ChipColor.YELLOW);
			chips.add(c);
		}
		Player yellow = new Player(player1, chips);
		players[0] = yellow;
		System.out.println("Guest 1 created");

		// Hard-coded names for Guest
		ArrayList<Chip> chips0 = new ArrayList<>();
		for (int i = 0; i < 21; i++) {
			Chip c = new Chip(ChipColor.RED);
			chips0.add(c);
		}
		Player red = new Player(player2, chips0);
		players[1] = red;
		System.out.println("Guest 2 created.");
	}

	private void createBoard(int difficulty) {
		Chip[][] chips;
		if (difficulty == 1) {
			// Easy
			chips = new Chip[5][4];
			board = new Board(chips);
		} else if (difficulty == 2) {
			// Normal
			chips = new Chip[7][6];
			board = new Board(chips);
		} else if (difficulty == 3) {
			// Challenging
			chips = new Chip[9][8];
			board = new Board(chips);
		}
	}


	public void takeTurn(Player player) {
		setCurrentP(player);
	}

	public boolean checkForWin() {
		return false;
	}

	private void declareWinner(Player player) {
		// This winner wins!!
	}

	private void resetGame() {
		// Clear everything and restart game.
		// Button at the top of the ribbon to be clicked anytime.
	}

	private int selectDifficulty() {
		// Logic to allow users to input difficulty
		return 2;
	}

	private void createLeaderboard() {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		
		try {
			// Saving object into a file
			fileOut = new FileOutputStream("official.ser");
			out = new ObjectOutputStream(fileOut);
			// Serialization of the object
			out.writeObject(lb);
		} catch (IOException ioe) {
			System.out.println("Exception is caught");
		} finally {
			try {
			out.close();
			fileOut.close();
			System.out.println("Leaderboard created.");
			} catch(IOException ioe2) {
				System.out.println("Exception is caught");
			}
		}
	}

	private void createNewP(Player p) {
		
	}
	
	public void addToLeaderboard(Player p) {
		lb.addPlayer(p);
		saveLeaderboard();
	}

	
	private void saveLeaderboard() {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;

		try {
			// Saving object into a file
			fileOut = new FileOutputStream("official.ser");
			out = new ObjectOutputStream(fileOut);
			// Serialization of the object
			out.writeObject(lb);
		} catch (IOException ioe) {
		} finally {
			try {
			out.close();
			fileOut.close();
			System.out.println("Leaderboard saved.");
			} catch(IOException ioe2) {
			}
		}
	}	
	
	public void importLeaderboard() {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		boolean needCre = false;
		
		try {
			// Reading the object from a file
			fileIn = new FileInputStream("official.ser");
			in = new ObjectInputStream(fileIn);

			// Deserialization of the object
			lb = (Leaderboard) in.readObject();
			
		} catch (IOException ioe) {
			System.out.println("");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught.");
			needCre = true;
		} finally {
			try {
			in.close();
			fileIn.close();
			System.out.println("Import.");
			} catch(IOException ioe2) {
			} catch(NullPointerException npe) {
				needCre = true;
			}
		}
		if(needCre) {
			System.out.println("Needed Creation");
			createLeaderboard();
			setContainL(true);
			needCre = false;
		}
	}

	public boolean getContainL() {
		return containL;
	}
	
	public void setContainL(boolean containL) {
		this.containL = containL;
	}
	
	public int getValue(String column) {
		
		return 0;
	}
	
	public Leaderboard getLeaderB() {
		return lb;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Player getCurrentP() {
		return currentP;
	}
	
	public void setCurrentP(Player currentP) {
		this.currentP = currentP;
	}
	
	public Player[] getPlayers() {
		return players;
	}
}
