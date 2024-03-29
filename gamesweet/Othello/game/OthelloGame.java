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
	private static Player player1;
	private static Player player2;
	private static OthelloGui gui = new OthelloGui();
	private static int playOptions;

	public OthelloGame(PlayerAmount playerAmount) {
		super(playerAmount);
	}

	public void run() {
	}

	public void init(Stage stage, String... playerName) {
		createBoard();
		player1 = new Player(playerName[0]);
		player2 = new Player(playerName[1]);
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
		int minColIndex;
		if (i == 0) {
			minColIndex = 0;
		} else {
			minColIndex = i - 1;
		}
		int posColIndex;
		if (i == 7) {
			posColIndex = 7;
		} else {
			posColIndex = i + 1;
		}
		int minRowIndex;
		if (j == 0) {
			minRowIndex = 0;
		} else {
			minRowIndex = j - 1;
		}
		int posRowIndex;
		if (j == 7) {
			posRowIndex = 7;
		} else {
			posRowIndex = j + 1;
		}
		int checkCount = 1;
		if (posColIndex < b.length && posRowIndex < b[0].length && minColIndex >= 0 && minRowIndex >= 0) {

			// Diagonal top lft check

			if (!checkBlocks[0].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[minColIndex][minRowIndex];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isTopLeftDiagValid()) {
							checkCount = 1;
							board[minColIndex][minRowIndex].setSideColor("black");
							board[minColIndex][minRowIndex].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[minColIndex][minRowIndex].setGraphic(img);
							do {
								checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									board[minColIndex - checkCount][minRowIndex - checkCount].setSideColor("black");
									img = new ImageView(colors[0]);
									b[minColIndex - checkCount][minRowIndex - checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// vrt up check

			if (!checkBlocks[1].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[i][minRowIndex];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isVrtUpValid()) {
							checkCount = 1;
							board[i][minRowIndex].setSideColor("black");
							board[i][minRowIndex].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[i][minRowIndex].setGraphic(img);
							do {
								checkDisk = board[i][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									board[i][minRowIndex - checkCount].setSideColor("black");
									img = new ImageView(colors[0]);
									b[i][minRowIndex - checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// Diagonal top right check

			if (!checkBlocks[2].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[posColIndex][minRowIndex];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isTopRightDiagValid()) {
							checkCount = 1;
							board[posColIndex][minRowIndex].setSideColor("black");
							board[posColIndex][minRowIndex].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[posColIndex][minRowIndex].setGraphic(img);
							do {
								checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									board[posColIndex + checkCount][minRowIndex - checkCount].setSideColor("black");
									img = new ImageView(colors[0]);
									b[posColIndex + checkCount][minRowIndex - checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// horizon right check

			if (!checkBlocks[3].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[posColIndex][j];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isHorRightValid()) {
							checkCount = 1;
							board[posColIndex][j].setSideColor("black");
							board[posColIndex][j].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[posColIndex][j].setGraphic(img);
							do {
								checkDisk = board[posColIndex + checkCount][j];
								if (checkDisk.getSideColor().equals("white")) {
									board[posColIndex + checkCount][j].setSideColor("black");
									img = new ImageView(colors[0]);
									b[posColIndex + checkCount][j].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// Diagonal bot right check

			if (!checkBlocks[4].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[posColIndex][posRowIndex];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isBotRightDiagValid()) {
							checkCount = 1;
							board[posColIndex][posRowIndex].setSideColor("black");
							board[posColIndex][posRowIndex].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[posColIndex][posRowIndex].setGraphic(img);
							do {
								checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									board[posColIndex + checkCount][posRowIndex + checkCount].setSideColor("black");
									img = new ImageView(colors[0]);
									b[posColIndex + checkCount][posRowIndex + checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// vrt down check

			if (!checkBlocks[5].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[i][posRowIndex];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isVrtDwnValid()) {
							checkCount = 1;
							board[i][posRowIndex].setSideColor("black");
							board[i][posRowIndex].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[i][posRowIndex].setGraphic(img);
							do {
								checkDisk = board[i][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									board[i][posRowIndex + checkCount].setSideColor("black");
									img = new ImageView(colors[0]);
									b[i][posRowIndex + checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// Diagonal bot left check

			if (!checkBlocks[6].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[minColIndex][posRowIndex];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isBotLeftDiagValid()) {
							checkCount = 1;
							board[minColIndex][posRowIndex].setSideColor("black");
							board[minColIndex][posRowIndex].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[minColIndex][posRowIndex].setGraphic(img);
							do {
								checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									board[minColIndex - checkCount][posRowIndex + checkCount].setSideColor("black");
									img = new ImageView(colors[0]);
									b[minColIndex - checkCount][posRowIndex + checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// horizon left check

			if (!checkBlocks[7].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("black")) {
					Disk checkDisk = board[minColIndex][j];
					if (checkDisk.getSideColor().equals("white")) {
						if (checkDisk.isHorLeftValid()) {
							checkCount = 1;
							board[minColIndex][j].setSideColor("black");
							board[minColIndex][j].setSandWitched(false);
							img = new ImageView(colors[0]);
							b[minColIndex][j].setGraphic(img);
							do {
								checkDisk = board[minColIndex - checkCount][j];
								if (checkDisk.getSideColor().equals("white")) {
									board[minColIndex - checkCount][j].setSideColor("black");
									img = new ImageView(colors[0]);
									b[minColIndex - checkCount][j].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
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
		int minColIndex;
		if (i == 0) {
			minColIndex = 0;
		} else {
			minColIndex = i - 1;
		}
		int posColIndex;
		if (i == 7) {
			posColIndex = 7;
		} else {
			posColIndex = i + 1;
		}
		int minRowIndex;
		if (j == 0) {
			minRowIndex = 0;
		} else {
			minRowIndex = j - 1;
		}
		int posRowIndex;
		if (j == 7) {
			posRowIndex = 7;
		} else {
			posRowIndex = j + 1;
		}
		int checkCount = 1;
		if (posColIndex < b.length && posRowIndex < b[0].length && minColIndex >= 0 && minRowIndex >= 0) {
			// Diagonal top lft check

			if (!checkBlocks[0].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[minColIndex][minRowIndex];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isTopLeftDiagValid()) {
							checkCount = 1;
							board[minColIndex][minRowIndex].setSideColor("white");
							img = new ImageView(colors[1]);
							b[minColIndex][minRowIndex].setGraphic(img);
							do {
								checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("black")) {
									board[minColIndex - checkCount][minRowIndex - checkCount].setSideColor("white");
									img = new ImageView(colors[1]);
									b[minColIndex - checkCount][minRowIndex - checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// vrt up check

			if (!checkBlocks[1].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[i][minRowIndex];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isVrtUpValid()) {
							checkCount = 1;
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
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// Diagonal top right check

			if (!checkBlocks[2].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[posColIndex][minRowIndex];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isTopRightDiagValid()) {
							checkCount = 1;
							board[posColIndex][minRowIndex].setSideColor("white");
							img = new ImageView(colors[1]);
							b[posColIndex][minRowIndex].setGraphic(img);
							do {
								checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("black")) {
									board[posColIndex + checkCount][minRowIndex - checkCount].setSideColor("white");
									img = new ImageView(colors[1]);
									b[posColIndex + checkCount][minRowIndex - checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// horizon right check

			if (!checkBlocks[3].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[posColIndex][j];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isHorRightValid()) {
							checkCount = 1;
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
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// Diagonal bot right check

			if (!checkBlocks[4].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[posColIndex][posRowIndex];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isBotRightDiagValid()) {
							checkCount = 1;
							board[posColIndex][posRowIndex].setSideColor("white");
							img = new ImageView(colors[1]);
							b[posColIndex][posRowIndex].setGraphic(img);
							do {
								checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("black")) {
									board[posColIndex + checkCount][posRowIndex + checkCount].setSideColor("white");
									img = new ImageView(colors[1]);
									b[posColIndex + checkCount][posRowIndex + checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// vrt down check

			if (!checkBlocks[5].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[i][posRowIndex];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isVrtDwnValid()) {
							checkCount = 1;
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
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// Diagonal bot left check

			if (!checkBlocks[6].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[minColIndex][posRowIndex];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isBotLeftDiagValid()) {
							checkCount = 1;
							board[minColIndex][posRowIndex].setSideColor("white");
							img = new ImageView(colors[1]);
							b[minColIndex][posRowIndex].setGraphic(img);
							do {
								checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("black")) {
									board[minColIndex - checkCount][posRowIndex + checkCount].setSideColor("white");
									img = new ImageView(colors[1]);
									b[minColIndex - checkCount][posRowIndex + checkCount].setGraphic(img);
									checkCount++;
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

			// horizon left check

			if (!checkBlocks[7].getSideColor().equals("")) {
				if (board[i][j].getSideColor().equals("white")) {
					Disk checkDisk = board[minColIndex][j];
					if (checkDisk.getSideColor().equals("black")) {
						if (checkDisk.isHorLeftValid()) {
							checkCount = 1;
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
									isChecking = true;
								} else {
									isChecking = false;
								}
							} while (isChecking);
						}
					}
				}
			}

		}
	}

	public static void validBlkMove(Button[][] b, int i, int j, Image validCrc) {
		Disk[] checkBlocks = checkSurroundings(i, j);
		ImageView img;
		int minColIndex;
		if (i == 0) {
			minColIndex = 0;
		} else {
			minColIndex = i - 1;
		}
		int posColIndex;
		if (i == 7) {
			posColIndex = 7;
		} else {
			posColIndex = i + 1;
		}
		int minRowIndex;
		if (j == 0) {
			minRowIndex = 0;
		} else {
			minRowIndex = j - 1;
		}
		int posRowIndex;
		if (j == 7) {
			posRowIndex = 7;
		} else {
			posRowIndex = j + 1;
		}
		int checkCount = 1;
		boolean isChecking = true;
		boolean isValid = false;

		// check valid blk play
		if (posColIndex < b.length && posRowIndex < b[0].length && minColIndex >= 0 && minRowIndex >= 0) {
			if (!board[i][j].getSideColor().equals("black")) {

				if (!checkBlocks[0].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[minColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
							do {
								int index1 = minColIndex - checkCount;
								int index2 = minRowIndex - checkCount;
								if (index1 >= 0 && index2 >= 0) {

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

										// board[minColIndex][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[minColIndex][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
//								topLeftDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][minRowIndex].setTopLeftDiagValid(true);

						} else {
							board[minColIndex][minRowIndex].setTopLeftDiagValid(false);
							// topLeftDiagValid = false;
						}
					}

				}

				if (!checkBlocks[1].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[i][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[i][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[i][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// vrtUpValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[i][minRowIndex].setVrtUpValid(true);

						} else {
							board[i][minRowIndex].setVrtUpValid(false);
							// vrtUpValid = false;
						}
					}
				}

				if (!checkBlocks[2].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[posColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[posColIndex][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[posColIndex][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// topRightDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][minRowIndex].setTopRightDiagValid(true);

						} else {
							board[posColIndex][minRowIndex].setTopRightDiagValid(false);
							// topRightDiagValid = false;
						}
					}
				}

				if (!checkBlocks[3].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[posColIndex][j];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[posColIndex][j].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[posColIndex][j].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// horRightValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][j].setHorRightValid(true);

						} else {
							board[posColIndex][j].setHorRightValid(false);
							// horRightValid = false;
						}
					}
				}

				if (!checkBlocks[4].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[posColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[posColIndex][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[posColIndex][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// botRightDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][posRowIndex].setBotRightDiagValid(true);

						} else {
							board[posColIndex][posRowIndex].setBotRightDiagValid(false);
							// botRightDiagValid = false;
						}
					}
				}

				if (!checkBlocks[5].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[i][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[i][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[i][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// vrtDwnValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[i][posRowIndex].setVrtDwnValid(true);

						} else {
							board[i][posRowIndex].setVrtDwnValid(false);
							// vrtDwnValid = false;
						}
					}
				}

				if (!checkBlocks[6].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[minColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[minColIndex][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[minColIndex][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// botLeftDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][posRowIndex].setBotLeftDiagValid(true);

						} else {
							board[minColIndex][posRowIndex].setBotLeftDiagValid(false);
							// botLeftDiagValid = false;
						}
					}
				}

				if (!checkBlocks[7].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("white")) {
						Disk checkDisk = board[minColIndex][j];
						if (checkDisk.getSideColor().equals("white")) {
							checkCount = 1;
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
										// board[minColIndex][j].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[minColIndex][j].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// horLeftValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][j].setHorLeftValid(true);

						} else {
							board[minColIndex][j].setHorLeftValid(false);
							;
							// horLeftValid = false;
						}
					}
				}
			}

		}
	}
	public static void validWhtMove(Button[][] b, int i, int j, Image validCrc) {
		Disk[] checkBlocks = checkSurroundings(i, j);
		ImageView img;
		int minColIndex;
		if (i == 0) {
			minColIndex = 0;
		} else {
			minColIndex = i - 1;
		}
		int posColIndex;
		if (i == 7) {
			posColIndex = 7;
		} else {
			posColIndex = i + 1;
		}
		int minRowIndex;
		if (j == 0) {
			minRowIndex = 0;
		} else {
			minRowIndex = j - 1;
		}
		int posRowIndex;
		if (j == 7) {
			posRowIndex = 7;
		} else {
			posRowIndex = j + 1;
		}
		int checkCount = 1;
		boolean isChecking = true;
		boolean isValid = false;

		// check valid wht play
		if (posColIndex < b.length && posRowIndex < b[0].length && minColIndex >= 0 && minRowIndex >= 0) {
			if (!board[i][j].getSideColor().equals("white")) {

				// checks the top left diagonal block

				// checks if block has a color
				if (!checkBlocks[0].getSideColor().equals("")) {
					// checks if the current block is not blk
					if (!board[i][j].getSideColor().equals("black")) {
						// set check disk to the top left diagonal block
						Disk checkDisk = board[minColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
									}
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							// topLeftDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][minRowIndex].setTopLeftDiagValid(true);

						} else {
							board[minColIndex][minRowIndex].setTopLeftDiagValid(false);
							// topLeftDiagValid = false;
						}
					}

				}

				if (!checkBlocks[1].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[i][minRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[i][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[i][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// vrtUpValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[i][minRowIndex].setVrtUpValid(true);

						} else {
							board[i][minRowIndex].setVrtUpValid(false);
							// vrtUpValid = false;
						}
					}
				}

				if (!checkBlocks[2].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[posColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[posColIndex][minRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[posColIndex][minRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// topRightDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][minRowIndex].setTopRightDiagValid(true);

						} else {
							board[posColIndex][minRowIndex].setTopRightDiagValid(false);
							// topRightDiagValid = false;
						}
					}
				}

				if (!checkBlocks[3].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[posColIndex][j];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[posColIndex][j].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[posColIndex][j].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// horRightValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][j].setHorRightValid(true);

						} else {
							board[posColIndex][j].setHorRightValid(false);
							// horRightValid = false;
						}
					}
				}

				if (!checkBlocks[4].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[posColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[posColIndex][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[posColIndex][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// botRightDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[posColIndex][posRowIndex].setBotRightDiagValid(true);

						} else {
							board[posColIndex][posRowIndex].setBotRightDiagValid(false);
							// botRightDiagValid = false;
						}
					}
				}

				if (!checkBlocks[5].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[i][posRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[i][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[i][posRowIndex].setSandWitched(false);
								}

							} while (isChecking);
						}
						if (isValid) {
							// vrtDwnValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[i][posRowIndex].setVrtDwnValid(true);

						} else {
							board[i][posRowIndex].setVrtDwnValid(false);
							// vrtDwnValid = false;
						}
					}
				}

				if (!checkBlocks[6].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[minColIndex][posRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[minColIndex][posRowIndex].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[minColIndex][posRowIndex].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// botLeftDiagValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][posRowIndex].setBotLeftDiagValid(true);

						} else {
							board[minColIndex][posRowIndex].setBotLeftDiagValid(false);
							// botLeftDiagValid = false;
						}
					}
				}

				if (!checkBlocks[7].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[minColIndex][j];
						if (checkDisk.getSideColor().equals("black")) {
							checkCount = 1;
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
										// board[minColIndex][j].setSandWitched(false);
									}
								} else {
									isValid = false;
									isChecking = false;
									// board[minColIndex][j].setSandWitched(false);
								}
							} while (isChecking);
						}
						if (isValid) {
							// horLeftValid = true;
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
							board[minColIndex][j].setHorLeftValid(true);

						} else {
							board[minColIndex][j].setHorLeftValid(false);
							// horLeftValid = false;
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
						// board[i][j].setSandWitched(true);
					}
					if (i == 3 && j == 3) {

						board[i][j].setSideColor("white");
						// board[i][j].setSandWitched(true);
					}
					if (i == 4 && j == 4) {

						board[i][j].setSideColor("white");
						// board[i][j].setSandWitched(true);
					}
					if (i == 3 && j == 4) {

						board[i][j].setSideColor("black");
						// board[i][j].setSandWitched(true);
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
					player1.setScore(blkScoreValue);
					blkScore.setText("black Score: " + blkScoreValue);
				} else if (board[i][j].getSideColor().equals("white")) {
					whtScoreValue++;
					player2.setScore(whtScoreValue);
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

	public static void showTurnName(Label playername, boolean turn) {
		if (turn == false) {
			playername.setText("Current player: " + player1.getName());
		} else {
			playername.setText("Current player: " + player2.getName());
		}
	}

	public static void endGame(Button[][] b, Label blkScore, Label whtScore, boolean noPlayOptions) {
		int totalDisksPlayed = 0;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (!board[i][j].getSideColor().equals("")) {
					totalDisksPlayed++;
				}
			}
		}
		if (totalDisksPlayed == 64 || noPlayOptions) {
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b[i].length; j++) {
					b[i][j].setDisable(true);
				}
			}
			trackScore(blkScore, whtScore);
			if (player1.getScore() > player2.getScore()) {
				blkScore.setText("black Score: " + player1.getScore() + " WINNER");
			} else if (player1.getScore() == player2.getScore()) {
				blkScore.setText("black Score: " + player1.getScore() + " TIED");
				whtScore.setText("white Score: " + player2.getScore() + " TIED");
			} else {
				whtScore.setText("whte Score: " + player2.getScore() + " WINNER");
			}
		}
	}

	public static int getPlayOptions(Button[][] b) {
		playOptions = 0;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (b[i][j].isDisable() == false) {
					playOptions++;
				}
			}
		}

		return playOptions;
	}

}
