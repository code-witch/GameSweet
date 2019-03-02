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
	private Board board;
	private static int turnCount;
	private Leaderboard lb = null;
	private boolean containL;

	public void run() {
//		createPlayers();
		createBoard(selectDifficulty());
		playGame();
	}
	
	public boolean createSEn(String ID) {
		boolean contains = imCrSavedPlayers(ID);
		if (contains) {
			createBoard(selectDifficulty());
			System.out.println("ID is " + ID);
		}
		return contains;
	}
	
	public void createGEn() {
		createGuestPlayers();
		createBoard(selectDifficulty());
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
		importLeaderboard();
		if(getContainL()) {
			HashMap<String, Player> ps = lb.getPlayersL();
			contained = ps.containsKey(ID);
		}
		return contained;
	}

	private void createGuestPlayers() {

		// Hard-coded names for Guest
		ArrayList<Chip> chips = new ArrayList<>();
		for (int i = 0; i < 21; i++) {
			Chip c = new Chip(ChipColor.BLACK);
			chips.add(c);
		}
		Player black = new Player(player1, chips);
		players[0] = black;

		// Hard-coded names for Guest
		ArrayList<Chip> chips0 = new ArrayList<>();
		for (int i = 0; i < 21; i++) {
			Chip c = new Chip(ChipColor.RED);
			chips0.add(c);
		}
		Player red = new Player(player2, chips0);
		players[0] = red;
	}

	private void createBoard(int difficulty) {
		Space[][] spaces;
		if (difficulty == 1) {
			// Easy
			spaces = new Space[5][4];
			board = new Board(spaces);
		} else if (difficulty == 2) {
			// Normal
			spaces = new Space[7][6];
			board = new Board(spaces);
		} else if (difficulty == 3) {
			// Challenging
			spaces = new Space[9][8];
			board = new Board(spaces);
		}
	}

	private void playGame() {
		do {
			if (turnCount % 2 == 0) {
				takeTurn(players[0]);
				turnCount++;
			} else if (turnCount % 2 == 1) {
				takeTurn(players[1]);
				turnCount++;
			}
		} while (!checkForWin());
		if (turnCount % 2 == 0) {
			declareWinner(players[1]);
		} else if (turnCount % 2 == 1) {
			declareWinner(players[0]);
		}

	}


	private void takeTurn(Player player) {

	}

	private boolean checkForWin() {
		return true;
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
		Leaderboard lb = new Leaderboard();
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		
		try {
			// Saving object into a file
			fileOut = new FileOutputStream("official");
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

	
	private void addToLeaderboard(Player p) {
		lb.addPlayer(p);
		saveLeaderboard(lb.getPlayersL());
	}

	
	private void saveLeaderboard(HashMap<String, Player> leaderboard) {
		lb.setPlayersL(leaderboard);
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		
		try {
			// Saving object into a file
			fileOut = new FileOutputStream("official");
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

//	private void printLeaderboard() {
//
//	}
	
	
	private void importLeaderboard() {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		boolean needCre = false;
		
		try {
			// Reading the object from a file
			fileIn = new FileInputStream("official");
			in = new ObjectInputStream(fileIn);

			// Deserialization of the object
			lb = (Leaderboard) in.readObject();
			System.out.println(lb);
			
		} catch (IOException ioe) {
			System.out.println("Exception is caught");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught.");
			needCre = true;
		} finally {
			try {
			in.close();
			fileIn.close();
			System.out.println("Deserialization completed.");
			} catch(IOException ioe2) {
				System.out.println("Exception is caught");
			} catch(NullPointerException npe) {
				needCre = true;
			}
		}
		if(needCre) {
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
	
}
