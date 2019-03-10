package gamesweet.minesweeper.GUI;

import gamesweet.minesweeper.enumpkg.Squares;
import gamesweet.minesweeper.model.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI {

	private Board gameBoard = new Board();
	private Button[][] gridArray;
	private static Stage newStage;
	private Button faceButton = new Button();
	private Timeline timeLine;
	private Integer seconds;
	private Label timerLabel = new Label();

	public void run(Stage primaryStage) {
		try {
			Scene currentScene = null;
			newStage = primaryStage;
			gameBoard.setUpBoard();
			gridArray = new Button[gameBoard.getBoard().length][gameBoard.getBoard()[0].length];
			VBox vBox = new VBox();
			primaryStage.setTitle("Minesweeper");

			HBox bar = new HBox();
			// Set up the toolbar on the top of the GUI
			Menu gameMenu = new Menu("Game");
			Menu difficultyMenu = new Menu("Difficulty");
			MenuItem easy = new MenuItem("Easy");
			MenuItem medium = new MenuItem("Medium");
			MenuItem hard = new MenuItem("Hard");

			easy.setOnAction(e -> easyClicked());
			medium.setOnAction(e -> mediumClicked());
			hard.setOnAction(e -> hardClicked());
			difficultyMenu.getItems().addAll(easy, medium, hard);
			gameMenu.getItems().addAll(difficultyMenu, new MenuItem("View Highscores"));

			Menu helpMenu = new Menu("Help");
			MenuItem h2P = new MenuItem("How to Play");
			h2P.setOnAction(e -> AlertBox
					.display(" The objective of the game is to clear a rectangular board containing hidden "
							+ "'mines' \n or bombs without detonating any of them, with help from clues about"
							+ " \n the number of neighboring mines in each field. "));
			helpMenu.getItems().addAll(h2P, new MenuItem("Go back to HUB"));

			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(gameMenu, helpMenu);

			// Set up the smileyface button
			Image smileyFace = new Image(getClass().getResourceAsStream("smiley.png"));
			ImageView smileView = new ImageView(smileyFace);
			smileView.setFitHeight(40);
			smileView.setFitWidth(40);
			faceButton.setGraphic(smileView);
			faceButton.setOnAction(e -> smileyReset());

			timerLabel.setPrefSize(50, 100);
			timerLabel.setFont(Font.font(30));
			timerLabel.setStyle("-fx-font-family:monospace");
			timerLabel.setTextFill(Color.RED);

			Label score = new Label();
			score.setPrefSize(100, 100);
			score.setFont(Font.font(20));

			bar.getChildren().addAll(score, faceButton, timerLabel);
			bar.setAlignment(Pos.CENTER);

			// Set up Grid
			GridPane grid = new GridPane();
			for (int row = 0; row < gridArray.length; row++) {
				for (int col = 0; col < gridArray[0].length; col++) {
					int x = col;
					int y = row;

					// Set up Mines
					if (gameBoard.getBoard()[row][col].getSquareType() == Squares.MINE) {
						Button mine = new Button();
						mine.setPrefSize(50, 50);
						mine.setStyle("-fx-background-color : blue");
						gridArray[row][col] = mine;
						grid.add(mine, col, row);
						mine.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								MouseButton leftClick = event.getButton();
								MouseButton rightClick = event.getButton();
								if (leftClick == MouseButton.PRIMARY) {
									for (int i = 0; i < gameBoard.getBoard().length; i++) {
										for (int j = 0; j < gameBoard.getBoard()[0].length; j++) {
											gridArray[i][j].setDisable(true);
											if (gameBoard.getBoard()[i][j].getSquareType() == Squares.MINE) {
												Image mineImage = new Image(getClass().getResourceAsStream("mine.png"));
												ImageView mineView = new ImageView(mineImage);
												mineView.setFitHeight(20);
												mineView.setFitWidth(20);
												gridArray[i][j].setGraphic(mineView);
											}
										}
									}
									Image sadFace = new Image(getClass().getResourceAsStream("dead.png"));
									ImageView sadView = new ImageView(sadFace);
									sadView.setFitHeight(40);
									sadView.setFitWidth(40);
									faceButton.setGraphic(sadView);
									AlertBox.display(
											" Game Over! YOU LOSE \n Press the Face or \n Change the Difficulty to Reset");
								}
								if (rightClick == MouseButton.SECONDARY) {
									System.out.println("Flaged square");
									gameBoard.setMines(gameBoard.getMines() - 1);
									score.setText("Mines: " + gameBoard.getMines());
								}
							}
						});
					}

					// Set up number
					if (gameBoard.getBoard()[row][col].getSquareType() == Squares.NUMBER) {
						Button number = new Button();
						number.setPrefSize(50, 50);
						gridArray[row][col] = number;
						grid.add(number, col, row);
						number.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								MouseButton leftClick = event.getButton();
								MouseButton rightClick = event.getButton();
								if (leftClick == MouseButton.PRIMARY) {
									String num = "" + gameBoard.getBoard()[y][x].getMineAmount();
									number.setText(num);
									number.setDisable(true);
									checkForWin();
								}
								if (rightClick == MouseButton.SECONDARY) {
									System.out.println("Flagged");
									gameBoard.setMines(gameBoard.getMines() - 1);
									score.setText("Mines: " + gameBoard.getMines());
								}
							}

						});
					}

					// Set up empty
					if (gameBoard.getBoard()[row][col].getSquareType() == Squares.EMPTY) {
						Button empty = new Button();
						empty.setPrefSize(50, 50);
//						empty.setStyle("-fx-background-color : grey");
						gridArray[row][col] = empty;
						grid.add(empty, col, row);
						empty.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								MouseButton leftClick = event.getButton();
								MouseButton rightClick = event.getButton();
								if (leftClick == MouseButton.PRIMARY) {
									empty.setDisable(true);
									revealSquare(gridArray, gameBoard, x, y);
									checkForWin();
								}
								if (rightClick == MouseButton.SECONDARY) {
									System.out.println("Flaged square");
									gameBoard.setMines(gameBoard.getMines() - 1);
									score.setText("Mines: " + gameBoard.getMines());
								}
							}
						});
					}
				}
			}

			grid.setAlignment(Pos.CENTER);
			grid.setPadding(new Insets(15, 0, 0, 0));
			vBox.getChildren().addAll(menuBar, bar, grid);
			if (gridArray[0].length == 9) {
				bar.setMargin(faceButton, new Insets(0, 90, 0, 90));
				score.setText("Mines: " + gameBoard.getMines());
				doTime(timerLabel);
				currentScene = new Scene(vBox, 430, 500);
			} else if (gridArray[0].length == 16) {
				bar.setMargin(faceButton, new Insets(0, 270, 0, 270));
				score.setText("Mines: " + gameBoard.getMines());
				doTime(timerLabel);
				currentScene = new Scene(vBox, 800, 810);
			} else if (gridArray[0].length == 30) {
				bar.setMargin(faceButton, new Insets(0, 550, 0, 550));
				score.setText("Mines: " + gameBoard.getMines());
				doTime(timerLabel);
				currentScene = new Scene(vBox, 1400, 900);
			}

			primaryStage.setScene(currentScene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doTime(Label lb) {
		if (timeLine != null) {
			timeLine.stop();
		}
		seconds = 0;
		timerLabel.setText(seconds.toString());
		timeLine = new Timeline();
		timeLine.setCycleCount(Timeline.INDEFINITE);
		timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
			@Override
			public void handle(Event arg0) {
				seconds += 1;
				timerLabel.setText(seconds.toString());
				if (seconds == 999) {
					timeLine.stop();
				}

			}
		}));
		timeLine.playFromStart();
	}

	private void checkForWin() {
		boolean winGame = false;
		int revealedSquares = 0;
		for (int i = 0; i < gridArray.length; i++) {
			for (int j = 0; j < gridArray[0].length; j++) {
				if (gridArray[0].length == 9) {
					if (gameBoard.getBoard()[i][j].getSquareType() != Squares.MINE
							&& gridArray[i][j].isDisable() == true) {
						revealedSquares += 1;
						if (revealedSquares == 71) {
							timeLine.stop();
							winFace();
						}
					}
				} else if (gridArray[0].length == 16) {
					if (gameBoard.getBoard()[i][j].getSquareType() != Squares.MINE
							&& gridArray[i][j].isDisable() == true) {
						revealedSquares += 1;
						if (revealedSquares == 216) {
							timeLine.stop();
							winFace();
						}
					}
				} else if (gridArray[0].length == 381) {
					if (gameBoard.getBoard()[i][j].getSquareType() != Squares.MINE
							&& gridArray[i][j].isDisable() == true) {
						revealedSquares += 1;
						if (revealedSquares == 71) {
							timeLine.stop();
							winFace();
						}
					}
				}
			}
		}
	}

	private void winFace() {
		for(int i = 0; i < gridArray.length; i++) {
			for(int j = 0; j < gridArray[i].length; j++) {
				if(gameBoard.getBoard()[i][j].getSquareType() == Squares.MINE) {
					gridArray[i][j].setDisable(true);
				}
			}
		}
		Image winFace = new Image(getClass().getResourceAsStream("win.png"));
		ImageView winView = new ImageView(winFace);
		winView.setFitHeight(40);
		winView.setFitWidth(40);
		faceButton.setGraphic(winView);
		AlertBox.display(" Game Over! YOU WIN! \n Press the Face or \n Change the Difficulty to Reset");
	}

	private void smileyReset() {
		gameBoard.changeBoardSize(gridArray.length, gridArray[0].length);
		run(newStage);
	}

	private void easyClicked() {
		gameBoard.changeBoardSize(9, 9);
		run(newStage);
	}

	private void mediumClicked() {
		gameBoard.changeBoardSize(16, 16);
		run(newStage);
	}

	private void hardClicked() {
		gameBoard.changeBoardSize(16, 30);
		run(newStage);
	}

	private void revealSquare(Button[][] gridArray, Board gameBoard, int col, int row) {

		revealNeighbors(gridArray, gameBoard, col - 1, row - 1);
		revealNeighbors(gridArray, gameBoard, col, row - 1);
		revealNeighbors(gridArray, gameBoard, col + 1, row - 1);
		revealNeighbors(gridArray, gameBoard, col - 1, row);
		revealNeighbors(gridArray, gameBoard, col + 1, row);
		revealNeighbors(gridArray, gameBoard, col - 1, row + 1);
		revealNeighbors(gridArray, gameBoard, col, row + 1);
		revealNeighbors(gridArray, gameBoard, col + 1, row + 1);

	}

	private void revealNeighbors(Button[][] gridArray, Board gameBoard, int col, int row) {
		if (col < 0 || row < 0) {
			return;
		}
		if (col > gameBoard.getBoard()[0].length - 1 || row > gameBoard.getBoard().length - 1) {
			return;
		}
		if (gameBoard.getBoard()[row][col].getSquareType() == Squares.NUMBER) {
			String num = "" + gameBoard.getBoard()[row][col].getMineAmount();
			gridArray[row][col].setText(num);
			gridArray[row][col].setDisable(true);
			return;
		}
		if (gridArray[row][col].isDisable() == true) {
			return;
		}
		if (gameBoard.getBoard()[row][col].getSquareType() == Squares.EMPTY) {
			gridArray[row][col].setDisable(true);
			revealSquare(gridArray, gameBoard, col, row);
		}
	}
}
