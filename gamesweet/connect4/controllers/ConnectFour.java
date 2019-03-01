package gamesweet.connect4.controllers;

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
	private Player[] players;
	private Board board;
	private static int turnCount;

	public void run() {
//		createPlayers();
		createBoard(selectDifficulty());
		playGame();
	}

	public void init(Stage primaryStage, String... playerName) {
		//Calls the GUI class
		GUI.run(primaryStage);
		player1 = playerName[0];
		player2 = playerName[1];
//		run();
	}
	
	private void imCrSavedPlayers() {
		
	}

	private void createGuestPlayers() {
		// Prompt Player 1 for if they are on the leaderboard.
			// 2 Options:
				// Saved User
				// Guest

		// Prompt Player 1 for if they are on the leaderboard.
			// 2 Options:
				// Saved User
				// Guest

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

	// Method might be obsolete
	private void printTurnState() {

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

	}

	private void addToLeaderboard() {

	}

	private void saveLeaderboard(HashMap<String, Player> leaderboard) {

	}

	private void printLeaderboard() {

	}
}
