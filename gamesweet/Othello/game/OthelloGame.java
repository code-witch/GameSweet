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
	private static Player[] players;
	
	public OthelloGame(PlayerAmount playerAmount) {
		super(playerAmount);
		
	}


	public void run() {
	}

	public void init(Stage stage, String... playerName) {
		createBoard();
		OthelloGui gui = new OthelloGui();
		gui.start(stage);

	}
	
	

	public static void placeBlkDisk(Button[][] b, int i, int j, Image[] colors) {
		if (board[i][j].getSideColor().equals("")) {
			board[i][j].setSideColor("black");
			ImageView img = new ImageView(colors[0]);
			b[i][j].setGraphic(img);
			b[i][j].setDisable(true);
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
		ImageView img;
		boolean isChecking = true;
		int posColIndex = i + 1;
		int posRowIndex = j + 1;
		int minColIndex = i - 1;
		int minRowIndex = j - 1;
		int checkCount = 1;
		// Diagonal top lft check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[minColIndex][minRowIndex].getSideColor().equals("white")) {
				board[minColIndex][minRowIndex].setSideColor("black");
				img = new ImageView(colors[0]);
				b[minColIndex][minRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
					if (checkDisk.getSideColor().equals("white")) {
						board[minColIndex - checkCount][minRowIndex - checkCount].setSideColor("black");
						img = new ImageView(colors[0]);
						b[minColIndex - checkCount][minRowIndex - checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
				for (int k = 0; k < checkCount; k++) {
				}
			}
		}
		// vrt up check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[i][minRowIndex].getSideColor().equals("white")) {
				board[i][minRowIndex].setSideColor("black");
				img = new ImageView(colors[0]);
				b[i][minRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[i][minRowIndex - checkCount];
					if (checkDisk.getSideColor().equals("white")) {
						board[i][minRowIndex - checkCount].setSideColor("black");
						img = new ImageView(colors[0]);
						b[i][minRowIndex - checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// Diagonal top right check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[posColIndex][minRowIndex].getSideColor().equals("white")) {
				board[posColIndex][minRowIndex].setSideColor("black");
				img = new ImageView(colors[0]);
				b[posColIndex][minRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
					if (checkDisk.getSideColor().equals("white")) {
						board[posColIndex + checkCount][minRowIndex - checkCount].setSideColor("black");
						img = new ImageView(colors[0]);
						b[posColIndex + checkCount][minRowIndex - checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// horizon right check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[posColIndex][j].getSideColor().equals("white")) {
				board[posColIndex][j].setSideColor("black");
				img = new ImageView(colors[0]);
				b[posColIndex][j].setGraphic(img);
				do {
					Disk checkDisk = board[posColIndex + checkCount][j];
					if (checkDisk.getSideColor().equals("white")) {
						board[posColIndex + checkCount][j].setSideColor("black");
						img = new ImageView(colors[0]);
						b[posColIndex + checkCount][j].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// Diagonal bot right check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[posColIndex][posRowIndex].getSideColor().equals("white")) {
				board[posColIndex][posRowIndex].setSideColor("black");
				img = new ImageView(colors[0]);
				b[posColIndex][posRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
					if (checkDisk.getSideColor().equals("white")) {
						board[posColIndex + checkCount][posRowIndex + checkCount].setSideColor("black");
						img = new ImageView(colors[0]);
						b[posColIndex + checkCount][posRowIndex + checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// vrt down check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[i][posRowIndex].getSideColor().equals("white")) {
				board[i][posRowIndex].setSideColor("black");
				img = new ImageView(colors[0]);
				b[i][posRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[i][posRowIndex + checkCount];
					if (checkDisk.getSideColor().equals("white")) {
						board[i][posRowIndex + checkCount].setSideColor("black");
						img = new ImageView(colors[0]);
						b[i][posRowIndex + checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// Diagonal bot left check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[minColIndex][posRowIndex].getSideColor().equals("white")) {
				board[minColIndex][posRowIndex].setSideColor("black");
				img = new ImageView(colors[0]);
				b[minColIndex][posRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
					if (checkDisk.getSideColor().equals("white")) {
						board[minColIndex - checkCount][posRowIndex + checkCount].setSideColor("black");
						img = new ImageView(colors[0]);
						b[minColIndex - checkCount][posRowIndex + checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
				for (int k = 0; k < checkCount; k++) {
				}
			}
		}
		// horizon left check
		if (board[i][j].getSideColor().equals("black")) {
			if (board[minColIndex][j].getSideColor().equals("white")) {
				board[minColIndex][j].setSideColor("black");
				img = new ImageView(colors[0]);
				b[minColIndex][j].setGraphic(img);
				do {
					Disk checkDisk = board[minColIndex - checkCount][j];
					if (checkDisk.getSideColor().equals("white")) {
						board[minColIndex - checkCount][j].setSideColor("black");
						img = new ImageView(colors[0]);
						b[minColIndex - checkCount][j].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
				for (int k = 0; k < checkCount; k++) {
				}
			}
		}
	}

	public static void CheckWhtDisk(Button[][] b, int i, int j, Image[] colors) {
		ImageView img;
		boolean isChecking = true;
		int posColIndex = i + 1;
		int posRowIndex = j + 1;
		int minColIndex = i - 1;
		int minRowIndex = j - 1;
		int checkCount = 1;
		// Diagonal top lft check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[minColIndex][minRowIndex].getSideColor().equals("black")) {
				board[minColIndex][minRowIndex].setSideColor("white");
				img = new ImageView(colors[1]);
				b[minColIndex][minRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
					if (checkDisk.getSideColor().equals("black")) {
						board[minColIndex - checkCount][minRowIndex - checkCount].setSideColor("white");
						img = new ImageView(colors[1]);
						b[minColIndex - checkCount][minRowIndex - checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
				for (int k = 0; k < checkCount; k++) {
				}
			}
		}
		// vrt up check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[i][minRowIndex].getSideColor().equals("black")) {
				board[i][minRowIndex].setSideColor("white");
				img = new ImageView(colors[1]);
				b[i][minRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[i][minRowIndex - checkCount];
					if (checkDisk.getSideColor().equals("black")) {
						board[i][minRowIndex - checkCount].setSideColor("white");
						img = new ImageView(colors[1]);
						b[i][minRowIndex - checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// Diagonal top right check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[posColIndex][minRowIndex].getSideColor().equals("black")) {
				board[posColIndex][minRowIndex].setSideColor("white");
				img = new ImageView(colors[1]);
				b[posColIndex][minRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
					if (checkDisk.getSideColor().equals("black")) {
						board[posColIndex + checkCount][minRowIndex - checkCount].setSideColor("white");
						img = new ImageView(colors[1]);
						b[posColIndex + checkCount][minRowIndex - checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// horizon right check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[posColIndex][j].getSideColor().equals("black")) {
				board[posColIndex][j].setSideColor("white");
				img = new ImageView(colors[1]);
				b[posColIndex][j].setGraphic(img);
				do {
					Disk checkDisk = board[posColIndex + checkCount][j];
					if (checkDisk.getSideColor().equals("black")) {
						board[posColIndex + checkCount][j].setSideColor("white");
						img = new ImageView(colors[1]);
						b[posColIndex + checkCount][j].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// Diagonal bot right check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[posColIndex][posRowIndex].getSideColor().equals("black")) {
				board[posColIndex][posRowIndex].setSideColor("white");
				img = new ImageView(colors[1]);
				b[posColIndex][posRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
					if (checkDisk.getSideColor().equals("black")) {
						board[posColIndex + checkCount][posRowIndex + checkCount].setSideColor("white");
						img = new ImageView(colors[1]);
						b[posColIndex + checkCount][posRowIndex + checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// vrt down check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[i][posRowIndex].getSideColor().equals("black")) {
				board[i][posRowIndex].setSideColor("white");
				img = new ImageView(colors[1]);
				b[i][posRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[i][posRowIndex + checkCount];
					if (checkDisk.getSideColor().equals("black")) {
						board[i][posRowIndex + checkCount].setSideColor("white");
						img = new ImageView(colors[1]);
						b[i][posRowIndex + checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
			}
		}
		// Diagonal bot left check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[minColIndex][posRowIndex].getSideColor().equals("black")) {
				board[minColIndex][posRowIndex].setSideColor("white");
				img = new ImageView(colors[1]);
				b[minColIndex][posRowIndex].setGraphic(img);
				do {
					Disk checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
					if (checkDisk.getSideColor().equals("black")) {
						board[minColIndex - checkCount][posRowIndex + checkCount].setSideColor("white");
						img = new ImageView(colors[1]);
						b[minColIndex - checkCount][posRowIndex + checkCount].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
				for (int k = 0; k < checkCount; k++) {
				}
			}
		}
		// horizon left check
		if (board[i][j].getSideColor().equals("white")) {
			if (board[minColIndex][j].getSideColor().equals("black")) {
				board[minColIndex][j].setSideColor("white");
				img = new ImageView(colors[1]);
				b[minColIndex][j].setGraphic(img);
				do {
					Disk checkDisk = board[minColIndex - checkCount][j];
					if (checkDisk.getSideColor().equals("black")) {
						board[minColIndex - checkCount][j].setSideColor("white");
						img = new ImageView(colors[1]);
						b[minColIndex - checkCount][j].setGraphic(img);
						checkCount++;
					} else {
						isChecking = false;
					}
				} while (isChecking);
				for (int k = 0; k < checkCount; k++) {
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
								checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[i][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[posColIndex + checkCount][j];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[i][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[minColIndex - checkCount][j];
								if (checkDisk.getSideColor().equals("white")) {
									checkCount++;
									isChecking = true;
								} else if (checkDisk.getSideColor().equals("black")) {
									isValid = true;
									isChecking = false;
								} else {
									isValid = false;
									isChecking = false;
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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

		// check valid blk play
		for (int k = 0; k < checkBlocks.length; k++) {
			if (k == 0) {
				if (!checkBlocks[k].getSideColor().equals("")) {
					if (!board[i][j].getSideColor().equals("black")) {
						Disk checkDisk = board[minColIndex][minRowIndex];
						if (checkDisk.getSideColor().equals("black")) {
							do {
								checkDisk = board[minColIndex - checkCount][minRowIndex - checkCount];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[i][minRowIndex - checkCount];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[posColIndex + checkCount][minRowIndex - checkCount];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								}
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[posColIndex + checkCount][posRowIndex + checkCount];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[i][posRowIndex + checkCount];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[minColIndex - checkCount][posRowIndex + checkCount];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
								checkDisk = board[minColIndex - checkCount][j];
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
							} while (isChecking);
						}
						if (isValid) {
							img = new ImageView(validCrc);
							b[i][j].setGraphic(img);
							b[i][j].setDisable(false);
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
					}
					if (i == 3 && j == 3) {

						board[i][j].setSideColor("white");
					}
					if (i == 4 && j == 4) {

						board[i][j].setSideColor("white");
					}
					if (i == 3 && j == 4) {

						board[i][j].setSideColor("black");
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
					blkScore.setText("black Score: " + blkScoreValue);
				} else if (board[i][j].getSideColor().equals("white")) {
					whtScoreValue++;
					whtScore.setText("White Score: " + whtScoreValue);
				}
			}
		}

	}

}
