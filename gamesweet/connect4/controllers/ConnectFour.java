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

public class ConnectFour extends Game {
	public ConnectFour(PlayerAmount playerAmount) {
		super(playerAmount);
	}

	private static String player1;
	private static String player2;
	private Player[] players = new Player[2];
	private Player currentP;
	private Board board;
	private String winner;
	public static int turnCount;
	private Leaderboard lb = new Leaderboard();
	private boolean containL;
	private int[] setUp = {7, 6};

	public void run() {
//		createPlayers();
//		playGame();
	}

	public boolean createSEn(String ID, int index, boolean created) {
		boolean contains = imCrSavedPlayers(ID);
		if (contains) {
			createBoard(selectDifficulty());
			System.out.println("ID is " + ID);
			if(created) {
				HashMap<String, Player> ps = lb.getPlayersL();
				Player p = ps.get(ID);
				createNewP(p, index);
			}
			
		}
//		for (Player p : lb.getPlayersL().values()) {
//			System.out.println(p);
//		}
		saveLeaderboard();
		
		return contains;
	}

	public void createGEn(int index) {
		createBoard(selectDifficulty());
		createGuestPlayer(index);
	}

	public void init(Stage primaryStage, String... playerName) {
		GUI.run(primaryStage);
		setPlayer1N(playerName[0]);
		setPlayer2N(playerName[1]);
	}

	private boolean imCrSavedPlayers(String ID) {
		boolean contained = false;
		HashMap<String, Player> ps = lb.getPlayersL();
		contained = ps.containsKey(ID);
		return contained;
	}

	private void createGuestPlayer(int index) {
		
		if(index == 1) {
			ArrayList<Chip> chips = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			Chip c = new Chip(ChipColor.YELLOW);
			chips.add(c);
		}
		Player yellow = new Player(getPlayer1N(), chips);
		players[0] = yellow;
		System.out.println("Guest 1 created");
		
		} else if (index == 2) {
			ArrayList<Chip> chips0 = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			Chip c = new Chip(ChipColor.RED);
			chips0.add(c);
		}
		Player red = new Player(getPlayer2N(), chips0);
		players[1] = red;
		System.out.println("Guest 2 created.");
		
		}		
	}

	private void createBoard(int difficulty) {
		Chip[][] chips;
		if (difficulty == 1) {
			// Easy
			chips = new Chip[5][4];
			setBoard(new Board(chips));
		} else if (difficulty == 2) {
			// Normal
			chips = new Chip[7][6];
			setBoard(new Board(chips));
		} else if (difficulty == 3) {
			// Challenging
			chips = new Chip[9][8];
			setBoard(new Board(chips));
		}
	}

	public void takeTurn(Player player) {
		setCurrentP(player);
	}

	public boolean checkForWin(Player p, Chip c) {
		boolean winner = false;
		int counter = 0;
		Chip[][] cs = board.getChips();
		// Checks if there is a connect four vertically.
		if (p != null && c != null) {
			while (!winner) {
				for (int x = 0; x < cs[0].length; x++) {
					for (int y = 0; y < cs.length; y++) {
						if (cs[y][x] == null) {
							counter = 0;
						} else {
							if (cs[y][x].getChipColor() == c.getChipColor()) {
								counter++;
							} else {
								counter = 0;
							}
							if (counter >= 4) {
								System.out.println(p.getName() + " won.");
								System.out.println("Horizontal");
								winner = true;
								break;
							}
						}
					}
				}
				break;
			}

			// Checks if there is a connect four horizontally
			while (!winner) {
				for (int y = 0; y < cs.length; y++) {
					for (int x = 0; x < cs[0].length; x++) {
						if (cs[y][x] == null) {
							counter = 0;
						} else {
							if (cs[y][x].getChipColor() == c.getChipColor()) {
								counter++;
							} else {
								counter = 0;
							}
							if (counter >= 4) {
								winner = true;
								System.out.println("Verical");
								break;
							}
						}
					}
				}
				break;
			}
			
			

			Boolean check = false;
			int checkC = 1;
			int checkR = 1;
			while (!winner) {
				for (int y = 0; y < cs.length; y++) {
					for (int x = 0; x < cs[0].length; x++) {
						checkC = 1;
						checkR = 1;
						counter = 0;
						if (cs[y][x] != null) {
							if (cs[y][x].getChipColor() == c.getChipColor()) {
								counter++;
								check = true;
								do {
									if (((y + checkC) < cs.length) && ((x - checkR) >= 0)) {
										if (cs[y + checkC][x - checkR] != null) {
											if (cs[y + checkC][x - checkR].getChipColor() == c.getChipColor()) {
												counter++;
											} else {
												break;
											}
										} else {
											break;
										}
									}
									checkC++;
									checkR++;

									if (checkC == cs.length - 1 || checkR == cs[0].length - 1) {
										check = false;
										winner = false;
										break;
									}

									if (counter >= 4) {
										System.out.println("Expiremental");
										check = false;
										winner = true;
										break;
									}
								} while (check);
							}
							if (counter >= 4 && !winner) {
								winner = true;
								System.out.println("Expiremental");
								break;
							}
							// Reset all the numbers

						}
					}
					}
					break;
				}
			
			
			

				counter = 0;
				checkC = 1;
				checkR = 1;
				while (!winner) {
					for (int y = 0; y < cs.length; y++) {
						for (int x = 0; x < cs[0].length; x++) {
							counter = 0;
							checkC = 1;
							checkR = 1;
//						try {
							// || cs[y - checkC][x - checkR] == null
							if (cs[y][x] == null) {
								counter = 0;
							} else {
								if (cs[y][x].getChipColor() == c.getChipColor()) {
									counter++;
									check = true;
									do {
										if ((((y - checkC) >= 0) && (y - checkC < cs.length)) && ((x - checkR) >= 0)
												&& (x - checkR < cs[0].length)) {
											if (cs[y - checkC][x - checkR] != null) {
												if (cs[y - checkC][x - checkR].getChipColor() == c.getChipColor()) {
													counter++;
												}
											}
										}

										checkC++;
										checkR++;

										if (checkC == y - 1 || checkR == x - 1) {
											check = false;
											break;
										}

										if (counter >= 4) {
											check = false;
											winner = true;
										}
									} while (check);
								}
								if (counter >= 4) {
									winner = true;
									System.out.println("diagnol");
									break;
								}

							}
//						} catch (ArrayIndexOutOfBoundsException aie) {
//
//						}
						}
					}
					break;
				}
		}
		return winner;
	}

	public void declareWinner(Player player) {
		System.out.println(player);
		// This winner wins!!
	}

	private int selectDifficulty() {
		int diff = 0;
		if(getSetUp()[0] == 5) {
			diff = 1;
		} else if (getSetUp()[0] == 7) {
			diff = 2;
		} else if(getSetUp()[0] == 9) {
			diff = 3;
		}
		return diff;
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
			} catch (IOException ioe2) {
				System.out.println("Exception is caught");
			}
		}
	}

	private void createNewP(Player p, int index) {
		
		if(index == 1) {
			ArrayList<Chip> chips = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			Chip c = new Chip(ChipColor.YELLOW);
			chips.add(c);
		}
		p.setChips(chips);
		players[0] = p;
		System.out.println("Saved User 1 created");
		
		} else if (index == 2) {
			ArrayList<Chip> chips0 = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			Chip c = new Chip(ChipColor.RED);
			chips0.add(c);
		}
		p.setChips(chips0);
		players[1] = p;
		System.out.println("Saved User 2 created.");
		
		}
		saveLeaderboard();
	}

	public void addToLeaderboard(Player p) {
		lb.addPlayer(p);
		saveLeaderboard();
	}

	public void saveLeaderboard() {
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
			} catch (IOException ioe2) {
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
			} catch (IOException ioe2) {
			} catch (NullPointerException npe) {
				needCre = true;
			}
		}
		if (needCre) {
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

	public Leaderboard getLeaderB() {
		return lb;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board b) {
		board = b;
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

	public String getPlayer1N() {
		return player1;
	}

	public void setPlayer1N(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2N() {
		return player2;
	}

	public void setPlayer2N(String player2) {
		this.player2 = player2;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinenr(String winner) {
		this.winner = winner;
	}
	
	public int[] getSetUp() {
		return setUp;
	}
	
	public void setSetUp(int[] setUp) {
		this.setUp = setUp;
	}
}
