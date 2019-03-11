package gamesweet.Othello.game;

import gamesweet.Othello.Gui.OthelloGui;
import gamesweet.Othello.models.Disk;
import gamesweet.Othello.models.Player;
import gamesweet.base.Game;
import gamesweet.base.PlayerAmount;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class OthelloGame extends Game {
	private static Disk[][] board = new Disk[8][8];
	private static Player[] players = new Player[2];
	private static OthelloGui gui = new OthelloGui();

	public OthelloGame(PlayerAmount playerAmount) {
		super(playerAmount);

	}

	public void run() {
	}

	public void init(Stage stage, String... playerName) {
		createBoard();
		players[0] = new Player(playerName[0]);
		players[1] = new Player(playerName[1]);
		gui.start(stage);

	}

	public static void placeBlkDisk(Button[][] b, int i, int j, Image[] colors) {
		if (board[i][j].getSideColor().equals("")) {
			board[i][j].setSideColor("black");
			ImageView img = new ImageView(colors[0]);
			b[i][j].setGraphic(img);
			b[i][j].setDisable(true);
		}
		for (int col = 0; col < b.length; col++) {

		}
	}

	public static void placeWhtDisk(Button[][] b, int i, int j, Image[] colors) {
		if (board[i][j].getSideColor().equals("")) {
			board[i][j].setSideColor("white");
			ImageView img = new ImageView(colors[1]);
			b[i][j].setGraphic(img);
			b[i][j].setDisable(true);
		}
	}

	public static void CheckBlkDisk(Button[][] b, int i, int j, Image[] colors) {
		Disk[] checkBlocks = checkSurroundings(i, j);
		ImageView img;
		boolean isChecking = true;
		int posColIndex = i + 1;
		int posRowIndex = j + 1;
		int minColIndex = i - 1;
		int minRowIndex = j - 1;
		int checkCount = 1;
		for (int k = 0; k < checkBlocks.length; k++) {

			// Diagonal top lft check
			if (k == 0) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[minColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {
								board[minColIndex][minRowIndex].setSideColor("black");
								img = new ImageView(colors[0]);
								b[minColIndex][minRowIndex].setGraphic(img);
								do {
									checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
									if (checkDisk.getSideColor().equals("white")) {
										board[minColIndex - checkCount][minRowIndex - checkCount].setSideColor("black");
										img = new ImageView(colors[0]);
										b[minColIndex - checkCount][minRowIndex - checkCount].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// vrt up check
			if (k == 1) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[i][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {

								board[i][minRowIndex].setSideColor("black");
								img = new ImageView(colors[0]);
								b[i][minRowIndex].setGraphic(img);
								do {
									checkDisk = board[i][minRowIndex - checkCount];
									if (checkDisk.getSideColor().equals("white")) {
										board[i][minRowIndex - checkCount].setSideColor("black");
										img = new ImageView(colors[0]);
										b[i][minRowIndex - checkCount].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// Diagonal top right check
			if (k == 2) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[posColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {

								board[posColIndex][minRowIndex].setSideColor("black");
								img = new ImageView(colors[0]);
								b[posColIndex][minRowIndex].setGraphic(img);
								do {
									checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
									if (checkDisk.getSideColor().equals("white")) {
										board[posColIndex + checkCount][minRowIndex - checkCount].setSideColor("black");
										img = new ImageView(colors[0]);
										b[posColIndex + checkCount][minRowIndex - checkCount].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// horizon right check
			if (k == 3) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[posColIndex][j];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {

								board[posColIndex][j].setSideColor("black");
								img = new ImageView(colors[0]);
								b[posColIndex][j].setGraphic(img);
								do {
									checkDisk = board[posColIndex + checkCount][j];
									if (checkDisk.getSideColor().equals("white")) {
										board[posColIndex + checkCount][j].setSideColor("black");
										img = new ImageView(colors[0]);
										b[posColIndex + checkCount][j].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// Diagonal bot right check
			if (k == 4) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[posColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {

								board[posColIndex][posRowIndex].setSideColor("black");
								img = new ImageView(colors[0]);
								b[posColIndex][posRowIndex].setGraphic(img);
								do {
									checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
									if (checkDisk.getSideColor().equals("white")) {
										board[posColIndex + checkCount][posRowIndex + checkCount].setSideColor("black");
										img = new ImageView(colors[0]);
										b[posColIndex + checkCount][posRowIndex + checkCount].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// vrt down check
			if (k == 5) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[i][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {
								board[i][posRowIndex].setSideColor("black");
								img = new ImageView(colors[0]);
								b[i][posRowIndex].setGraphic(img);
								do {
									checkDisk = board[i][posRowIndex + checkCount];
									if (checkDisk.getSideColor().equals("white")) {
										board[i][posRowIndex + checkCount].setSideColor("black");
										img = new ImageView(colors[0]);
										b[i][posRowIndex + checkCount].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// Diagonal bot left check
			if (k == 6) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[minColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							//if (checkDisk.isSandWitched()) {

								board[minColIndex][posRowIndex].setSideColor("black");
								img = new ImageView(colors[0]);
								b[minColIndex][posRowIndex].setGraphic(img);
								do {
									checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
									if (checkDisk.getSideColor().equals("white")) {
										board[minColIndex - checkCount][posRowIndex + checkCount].setSideColor("black");
										img = new ImageView(colors[0]);
										b[minColIndex - checkCount][posRowIndex + checkCount].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}
			// horizon left check
			if (k == 7) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[minColIndex][j];
						//if (checkDisk.getSideColor().equals("white")) {
							if (checkDisk.isSandWitched()) {

								board[minColIndex][j].setSideColor("black");
								img = new ImageView(colors[0]);
								b[minColIndex][j].setGraphic(img);
								do {
									checkDisk = board[minColIndex - checkCount][j];
									if (checkDisk.getSideColor().equals("white")) {
										board[minColIndex - checkCount][j].setSideColor("black");
										img = new ImageView(colors[0]);
										b[minColIndex - checkCount][j].setGraphic(img);
										checkCount++;
									} else {
										isChecking = false;
									}
								} while (isChecking);
							//}
						}
					}
				}
			}

		}
	}

	public static void CheckWhtDisk(Button[][] b, int i, int j, Image[] colors) {
		Disk[] checkBlocks = checkSurroundings(i, j);
		ImageView img;
		boolean isChecking = true;
		int posColIndex = i + 1;
		int posRowIndex = j + 1;
		int minColIndex = i - 1;
		int minRowIndex = j - 1;
		int checkCount = 1;
		if (posColIndex < b.length && posRowIndex < b[0].length && minColIndex >= 0 && minRowIndex >= 0) {
			for (int k = 0; k < checkBlocks.length; k++) {
				// Diagonal top lft check
				if (k == 0) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[minColIndex][minRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[minColIndex][minRowIndex].setSideColor("white");
									img = new ImageView(colors[1]);
									b[minColIndex][minRowIndex].setGraphic(img);
									do {
										checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
										if (checkDisk.getSideColor().equals("black")) {
											board[minColIndex - checkCount][minRowIndex - checkCount]
													.setSideColor("white");
											img = new ImageView(colors[1]);
											b[minColIndex - checkCount][minRowIndex - checkCount].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// vrt up check
				if (k == 1) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[i][minRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[i][minRowIndex].setSideColor("white");
									img = new ImageView(colors[1]);
									b[i][minRowIndex].setGraphic(img);
									do {
										checkDisk = board[i][minRowIndex - checkCount];
										if (checkDisk.getSideColor().equals("black")) {
											board[i][minRowIndex - checkCount].setSideColor("white");
											img = new ImageView(colors[1]);
											b[i][minRowIndex - checkCount].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// Diagonal top right check
				if (k == 2) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[posColIndex][minRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[posColIndex][minRowIndex].setSideColor("white");
									img = new ImageView(colors[1]);
									b[posColIndex][minRowIndex].setGraphic(img);
									do {
										checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
										if (checkDisk.getSideColor().equals("black")) {
											board[posColIndex + checkCount][minRowIndex - checkCount]
													.setSideColor("white");
											img = new ImageView(colors[1]);
											b[posColIndex + checkCount][minRowIndex - checkCount].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// horizon right check
				if (k == 3) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[posColIndex][j];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {
									board[posColIndex][j].setSideColor("white");
									img = new ImageView(colors[1]);
									b[posColIndex][j].setGraphic(img);
									do {
										checkDisk = board[posColIndex + checkCount][j];
										if (checkDisk.getSideColor().equals("black")) {
											board[posColIndex + checkCount][j].setSideColor("white");
											img = new ImageView(colors[1]);
											b[posColIndex + checkCount][j].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// Diagonal bot right check
				if (k == 4) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[posColIndex][posRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[posColIndex][posRowIndex].setSideColor("white");
									img = new ImageView(colors[1]);
									b[posColIndex][posRowIndex].setGraphic(img);
									do {
										checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
										if (checkDisk.getSideColor().equals("black")) {
											board[posColIndex + checkCount][posRowIndex + checkCount]
													.setSideColor("white");
											img = new ImageView(colors[1]);
											b[posColIndex + checkCount][posRowIndex + checkCount].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// vrt down check
				if (k == 5) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[i][posRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[i][posRowIndex].setSideColor("white");
									img = new ImageView(colors[1]);
									b[i][posRowIndex].setGraphic(img);
									do {
										checkDisk = board[i][posRowIndex + checkCount];
										if (checkDisk.getSideColor().equals("black")) {
											board[i][posRowIndex + checkCount].setSideColor("white");
											img = new ImageView(colors[1]);
											b[i][posRowIndex + checkCount].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// Diagonal bot left check
				if (k == 6) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[minColIndex][posRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[minColIndex][posRowIndex].setSideColor("white");
									img = new ImageView(colors[1]);
									b[minColIndex][posRowIndex].setGraphic(img);
									do {
										checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
										if (checkDisk.getSideColor().equals("black")) {
											board[minColIndex - checkCount][posRowIndex + checkCount]
													.setSideColor("white");
											img = new ImageView(colors[1]);
											b[minColIndex - checkCount][posRowIndex + checkCount].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
				// horizon left check
				if (k == 7) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (board[i][j].getSideColor().equals("white")) {
							Disk checkDisk = board[minColIndex][j];
							if (checkDisk.getSideColor().equals("black")) {
								//if (checkDisk.isSandWitched()) {

									board[minColIndex][j].setSideColor("white");
									img = new ImageView(colors[1]);
									b[minColIndex][j].setGraphic(img);
									do {
										checkDisk = board[minColIndex - checkCount][j];
										if (checkDisk.getSideColor().equals("black")) {
											board[minColIndex - checkCount][j].setSideColor("white");
											img = new ImageView(colors[1]);
											b[minColIndex - checkCount][j].setGraphic(img);
											checkCount++;
										} else {
											isChecking = false;
										}
									} while (isChecking);
								//}
							}
						}
					}
				}
			}
		}
	}

	public static void validBlkMove(Button[][] b, int i, int j, Image validCrc) {

		Disk[] checkBlocks = checkSurroundings(i, j);
		ImageView img;
		int posColIndex = i + 1;
		int posRowIndex = j + 1;
		int minColIndex = i - 1;
		int minRowIndex = j - 1;
		int checkCount = 1;
		boolean isChecking = true;
		boolean isValid = false;

		// check valid blk play
		for (int k = 0; k < checkBlocks.length; k++) {
			if (k == 0) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[minColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = minColIndex - checkCount;
								int index2 = minRowIndex - checkCount;
								if (index1 > 0 && index2 > 0) {

									checkDisk = board[index1][index2];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[minColIndex][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[minColIndex][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][minRowIndex].setSandWitched(true);
						}
					}

				}
			}
			if (k == 1) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[i][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = minRowIndex - checkCount;
								if (index1 > 0) {

									checkDisk = board[i][index1];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[i][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[i][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[i][minRowIndex].setSandWitched(true);
						}
					}
				}
			}
			if (k == 2) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[posColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = posColIndex + checkCount;
								int index2 = minRowIndex - checkCount;
								if (index1 < b[i].length && index2 > 0) {

									checkDisk = board[index1][index2];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[posColIndex][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[posColIndex][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][minRowIndex].setSandWitched(true);
						}
					}
				}
			}
			if (k == 3) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[posColIndex][j];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = posColIndex + checkCount;
								if (index1 < b[i].length) {

									checkDisk = board[index1][j];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[posColIndex][j].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[posColIndex][j].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][j].setSandWitched(true);
						}
					}
				}
			}
			if (k == 4) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[posColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = posColIndex + checkCount;
								int index2 = posRowIndex + checkCount;
								if (index1 < b[i].length && index2 < b[i].length) {

									checkDisk = board[index1][index2];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[posColIndex][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[posColIndex][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][posRowIndex].setSandWitched(true);
						}
					}
				}
			}
			if (k == 5) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[i][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = posRowIndex + checkCount;
								if (index1 < b.length) {

									checkDisk = board[i][index1];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
									} else {
										isValid = false;
										isChecking = false;
										board[i][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[i][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[i][posRowIndex].setSandWitched(true);
						}
					}
				}
			}
			if (k == 6) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[minColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = minColIndex - checkCount;
								int index2 = posRowIndex + checkCount;
								if (index1 > 0 && index2 < b[i].length) {

									checkDisk = board[index1][index2];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[minColIndex][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[minColIndex][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][posRowIndex].setSandWitched(true);
						}
					}
				}
			}
			if (k == 7) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[minColIndex][j];
						if (checkDisk.getSideColor().equals("white")) {
							do {
								int index1 = minColIndex - checkCount;
								if (index1 > 0) {

									checkDisk = board[index1][j];
									if (checkDisk.getSideColor().equals("white")) {
										checkCount++;
										isChecking = true;
									} else if (checkDisk.getSideColor().equals("black")) {
										isValid = true;
										isChecking = false;
										
									} else {
										isValid = false;
										isChecking = false;
										board[minColIndex][j].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									board[minColIndex][j].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][j].setSandWitched(true);
						}
					}
				}
			}

		}
	}

	public static void validWhtMove(Button[][] b, int i, int j, Image validCrc) {

		Disk[] checkBlocks = checkSurroundings(i, j);
		ImageView img;
		int posColIndex = i + 1;
		int posRowIndex = j + 1;
		int minColIndex = i - 1;
		int minRowIndex = j - 1;
		int checkCount = 1;
		boolean isChecking = true;
		boolean isValid = false;

		if (posColIndex < b.length && posRowIndex < b[0].length && minColIndex >= 0 && minRowIndex >= 0) {
			// check valid wht play
			for (int k = 0; k < checkBlocks.length; k++) {
				// checks the top left diagonal block
				if (k == 0) {
					// checks if block has a color
					if (!checkBlocks[k].getSideColor().equals("")) {
						// checks if the current block is not blk
						if (!board[i][j].getSideColor().equals("black")) {
							// set check disk to the top left diagonal block
							Disk checkDisk = board[minColIndex][minRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = minColIndex - checkCount;
									int index2 = minRowIndex - checkCount;
									if (index1 > 0 && index2 > 0) {

										checkDisk = board[index1][index2];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[minColIndex][minRowIndex].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[minColIndex][minRowIndex].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[minColIndex][minRowIndex].setSandWitched(true);
							}
						}

					}
				}
				if (k == 1) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[i][minRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = minRowIndex - checkCount;
									if (index1 > 0) {

										checkDisk = board[i][index1];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[i][minRowIndex].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[i][minRowIndex].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[i][minRowIndex].setSandWitched(true);
							}
						}
					}
				}
				if (k == 2) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[posColIndex][minRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = posColIndex + checkCount;
									int index2 = minRowIndex - checkCount;
									if (index1 < b[i].length && index2 > 0) {

										checkDisk = board[index1][index2];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[posColIndex][minRowIndex].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[posColIndex][minRowIndex].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[posColIndex][minRowIndex].setSandWitched(true);
							}
						}
					}
				}
				if (k == 3) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[posColIndex][j];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = posColIndex + checkCount;
									if (index1 < b[i].length) {

										checkDisk = board[posColIndex + checkCount][j];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[posColIndex][j].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[posColIndex][j].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[posColIndex][j].setSandWitched(true);
							}
						}
					}
				}
				if (k == 4) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[posColIndex][posRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = posColIndex + checkCount;
									int index2 = posRowIndex + checkCount;
									if (index1 < b[i].length && index2 < b[i].length) {

										checkDisk = board[index1][index2];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[posColIndex][posRowIndex].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[posColIndex][posRowIndex].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[posColIndex][posRowIndex].setSandWitched(true);
							}
						}
					}
				}
				if (k == 5) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[i][posRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = posRowIndex + checkCount;
									if (index1 < b[i].length) {

										checkDisk = board[i][index1];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[i][posRowIndex].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[i][posRowIndex].setSandWitched(false);
									}

								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[i][posRowIndex].setSandWitched(true);
							}
						}
					}
				}
				if (k == 6) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[minColIndex][posRowIndex];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = minColIndex - checkCount;
									int index2 = posRowIndex + checkCount;
									if (index1 > 0 && index2 < b[i].length) {
										checkDisk = board[index1][index2];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[minColIndex][posRowIndex].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[minColIndex][posRowIndex].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[minColIndex][posRowIndex].setSandWitched(true);
							}
						}
					}
				}
				if (k == 7) {
					if (!checkBlocks[k].getSideColor().equals("")) {
						if (!board[i][j].getSideColor().equals("black")) {
							Disk checkDisk = board[minColIndex][j];
							if (checkDisk.getSideColor().equals("black")) {
								do {
									int index1 = minColIndex - checkCount;
									if (index1 > 0) {
										checkDisk = board[index1][j];
										if (checkDisk.getSideColor().equals("black")) {
											checkCount++;
											isChecking = true;
										} else if (checkDisk.getSideColor().equals("white")) {
											isValid = true;
											isChecking = false;
											
										} else {
											isValid = false;
											isChecking = false;
											board[minColIndex][j].setSandWitched(false);
										}
									} else {
										isValid = false;
										isChecking = false;
										board[minColIndex][j].setSandWitched(false);
									}
								} while (isChecking);
							}
							if (isValid) {
								img = new ImageView(validCrc);
								b[i][j].setGraphic(img);
								b[i][j].setDisable(false);
								board[minColIndex][j].setSandWitched(true);
							}
						}
					}
				}
			}
		}
	}

	private static Disk[] checkSurroundings(int i, int j) {
		Disk[] checkBlocks = new Disk[8];
		boolean onEdge = false;
		if (i == 0) {
			checkBlocks[0] = new Disk();
			if (j == 0) {
				checkBlocks[1] = new Disk();
				checkBlocks[2] = new Disk();
			} else {
				checkBlocks[1] = board[i][j - 1];
				checkBlocks[2] = board[i + 1][j - 1];
			}
			checkBlocks[3] = board[i + 1][j];
			if (j == 7) {
				checkBlocks[4] = new Disk();
				checkBlocks[5] = new Disk();
			} else {
				checkBlocks[4] = board[i + 1][j + 1];
				checkBlocks[5] = board[i][j + 1];
			}
			checkBlocks[6] = new Disk();
			checkBlocks[7] = new Disk();
			onEdge = true;
		} else if (i == 7) {
			if (j == 0) {
				checkBlocks[0] = new Disk();
				checkBlocks[1] = new Disk();
			} else {
				checkBlocks[0] = board[i - 1][j - 1];
				checkBlocks[1] = board[i][j - 1];
			}
			checkBlocks[2] = new Disk();
			checkBlocks[3] = new Disk();
			checkBlocks[4] = new Disk();
			if (j == 7) {
				checkBlocks[5] = new Disk();
				checkBlocks[6] = new Disk();
			} else {
				checkBlocks[5] = board[i][j + 1];
				checkBlocks[6] = board[i - 1][j + 1];
			}
			checkBlocks[7] = board[i - 1][j];
			onEdge = true;
		}
		if (j == 0) {
			checkBlocks[0] = new Disk();
			checkBlocks[1] = new Disk();
			checkBlocks[2] = new Disk();
			if (i == 7) {
				checkBlocks[3] = new Disk();
				checkBlocks[4] = new Disk();
			} else {
				checkBlocks[3] = board[i + 1][j];
				checkBlocks[4] = board[i + 1][j + 1];
			}
			checkBlocks[5] = board[i][j + 1];
			if (i == 0) {
				checkBlocks[6] = new Disk();
				checkBlocks[7] = new Disk();
			} else {
				checkBlocks[6] = board[i - 1][j + 1];
				checkBlocks[7] = board[i - 1][j];
			}
			onEdge = true;
		} else if (j == 7) {
			if (i == 0) {
				checkBlocks[0] = new Disk();
				checkBlocks[7] = new Disk();
			} else {
				checkBlocks[0] = board[i - 1][j - 1];
				checkBlocks[7] = board[i - 1][j];
			}
			checkBlocks[1] = board[i][j - 1];
			if (i == 7) {
				checkBlocks[2] = new Disk();
				checkBlocks[3] = new Disk();
			} else {
				checkBlocks[2] = board[i + 1][j - 1];
				checkBlocks[3] = board[i + 1][j];
			}
			checkBlocks[4] = new Disk();
			checkBlocks[5] = new Disk();
			checkBlocks[6] = new Disk();
			onEdge = true;
		}
		if (!onEdge) {
			checkBlocks[0] = board[i - 1][j - 1];
			checkBlocks[1] = board[i][j - 1];
			checkBlocks[2] = board[i + 1][j - 1];
			checkBlocks[3] = board[i + 1][j];
			checkBlocks[4] = board[i + 1][j + 1];
			checkBlocks[5] = board[i][j + 1];
			checkBlocks[6] = board[i - 1][j + 1];
			checkBlocks[7] = board[i - 1][j];
		}
		return checkBlocks;
	}

	public static void createBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					board[i][j] = new Disk();
					if (i == 4 && j == 3) {

						board[i][j].setSideColor("black");
						board[i][j].setSandWitched(true);
					}
					if (i == 3 && j == 3) {

						board[i][j].setSideColor("white");
						board[i][j].setSandWitched(true);
					}
					if (i == 4 && j == 4) {

						board[i][j].setSideColor("white");
						board[i][j].setSandWitched(true);
					}
					if (i == 3 && j == 4) {

						board[i][j].setSideColor("black");
						board[i][j].setSandWitched(true);
					}
					// test disk
//					if (i == 2 && j == 4) {
//						board[i][j].setSideColor("black");
//					}
				}
			}
		}
	}

	public static void trackScore(Label blkScore, Label whtScore) {
		int blkScoreValue = 0;
		int whtScoreValue = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getSideColor().equals("black")) {
					blkScoreValue++;
					players[0].setScore(blkScoreValue);
					blkScore.setText("black Score: " + blkScoreValue);
				} else if (board[i][j].getSideColor().equals("white")) {
					whtScoreValue++;
					players[1].setScore(blkScoreValue);
					whtScore.setText("White Score: " + whtScoreValue);
				}
			}
		}

	}

	public static void resetButtons(Button[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (board[i][j].getSideColor().equals("")) {
					b[i][j].setGraphic(null);
				}
				b[i][j].setDisable(true);
			}
		}
	}

	public static void endGame(Button[][] b, Label blkScore, Label whtScore) {
		int totalDisksPlayed = 0;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (!board[i][j].getSideColor().equals("")) {
					totalDisksPlayed++;
				}
			}
		}
		if (totalDisksPlayed == 64) {
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b[i].length; j++) {
					b[i][j].setDisable(true);
				}
			}
			if (players[0].getScore() > players[1].getScore()) {
				blkScore.setText("black Score: " + players[0].getScore() + "winner");
			} else if (players[0].getScore() == players[1].getScore()) {
				blkScore.setText("black Score: " + players[0].getScore() + "tied");
				whtScore.setText("black Score: " + players[1].getScore() + "tied");
			} else {
				whtScore.setText("black Score: " + players[1].getScore() + "winner");
			}
		}
	}

}
