package gamesweet.minesweeper.GUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gamesweet.hub.App;
import gamesweet.minesweeper.enumpkg.Squares;
import gamesweet.minesweeper.model.Board;
import gamesweet.stratego.models.Flag;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI {

	private Board gameBoard = new Board();
	private Button[][] gridArray;
	private static Stage newStage;
	private Timeline timeLine;
	private Integer seconds;
	private Button faceButton = new Button();
	private Label timerLabel = new Label();
	private Label score = new Label();
	private Button flag = new Button();

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
			gameMenu.getItems().addAll(difficultyMenu);

			Menu helpMenu = new Menu("Help");
			MenuItem h2P = new MenuItem("How to Play");
			h2P.setOnAction(e -> AlertBox
					.display(" The objective of the game is to clear a rectangular board containing hidden "
							+ "'mines' \n or bombs without detonating any of them, with help from clues about"
							+ " \n the number of neighboring mines in each field. "));
			MenuItem hub = new MenuItem("Go Back to HUB");
			hub.setOnAction(e -> backToHub());
			helpMenu.getItems().addAll(h2P, hub);

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

			Image flagimage = new Image(getClass().getResourceAsStream("flag.png"));
			ImageView flagView = new ImageView(flagimage);
			flagView.setFitHeight(30);
			flagView.setFitWidth(30);
			flag.setGraphic(flagView);
			flag.setDisable(false);
			flag.setOnAction(e -> resetFlag());

			score.setPrefSize(100, 100);
			score.setFont(Font.font(20));

			bar.getChildren().addAll(score, flag, faceButton, timerLabel);
			bar.setAlignment(Pos.CENTER);

			// Set up Grid
			GridPane grid = new GridPane();
			for (int row = 0; row < gridArray.length; row++) {
				for (int column = 0; column < gridArray[row].length; column++) {
					int x = row;
					int y = column;

					// Set up Mines
					if (gameBoard.getBoard()[row][column].getSquareType() == Squares.MINE) {
						Button mine = new Button();
						mine.setPrefSize(50, 50);
						mine.setStyle("-fx-background-color : blue");
						gridArray[row][column] = mine;
						grid.add(mine, row, column);
						mine.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								MouseButton leftClick = event.getButton();
								MouseButton rightClick = event.getButton();
								if (leftClick == MouseButton.PRIMARY) {
									timeLine.stop();
									flag.setDisable(true);
									for (int row = 0; row < gameBoard.getBoard().length; row++) {
										for (int column = 0; column < gameBoard.getBoard()[row].length; column++) {
											gridArray[row][column].setDisable(true);
											if (gameBoard.getBoard()[row][column].getSquareType() == Squares.MINE) {
												Image mineImage = new Image(getClass().getResourceAsStream("mine.png"));
												ImageView mineView = new ImageView(mineImage);
												mineView.setFitHeight(20);
												mineView.setFitWidth(20);
												gridArray[row][column].setGraphic(mineView);
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
									gridArray[x][y].setStyle("-fx-background-color : red");
									gridArray[x][y].setDisable(true);
									gameBoard.setMines(gameBoard.getMines() - 1);
									score.setText("Mines: " + gameBoard.getMines());

								}
							}
						});
					}

					// Set up number
					if (gameBoard.getBoard()[row][column].getSquareType() == Squares.NUMBER) {
						Button number = new Button();
						number.setPrefSize(50, 50);
						gridArray[row][column] = number;
						grid.add(number, row, column);
						number.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								MouseButton leftClick = event.getButton();
								MouseButton rightClick = event.getButton();
								if (leftClick == MouseButton.PRIMARY) {
									String num = "" + gameBoard.getBoard()[x][y].getMineAmount();
									number.setText(num);
									number.setDisable(true);
									checkForWin();
								}
								if (rightClick == MouseButton.SECONDARY) {
									gridArray[x][y].setStyle("-fx-background-color : red");
									gridArray[x][y].setDisable(true);
									gameBoard.setMines(gameBoard.getMines() - 1);
									score.setText("Mines: " + gameBoard.getMines());

								}
							}

						});
					}

					// Set up empty
					if (gameBoard.getBoard()[row][column].getSquareType() == Squares.EMPTY) {
						Button empty = new Button();
						empty.setPrefSize(50, 50);
//						empty.setStyle("-fx-background-color : grey");
						gridArray[row][column] = empty;
						grid.add(empty, row, column);
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
									gridArray[x][y].setStyle("-fx-background-color : red");
									gridArray[x][y].setDisable(true);
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
				bar.setMargin(faceButton, new Insets(0, 90, 0, 60));
				score.setText("Mines: " + gameBoard.getMines());
				doTime(timerLabel);
				currentScene = new Scene(vBox, 430, 500);
			} else if (gridArray[0].length == 16) {
				bar.setMargin(faceButton, new Insets(0, 270, 0, 230));
				score.setText("Mines: " + gameBoard.getMines());
				doTime(timerLabel);
				currentScene = new Scene(vBox, 800, 810);
			} else if (gridArray[0].length == 20) {
				bar.setMargin(faceButton, new Insets(0, 300, 0, 300));
				score.setText("Mines: " + gameBoard.getMines());
				doTime(timerLabel);
				currentScene = new Scene(vBox, 900, 900);
			}

			primaryStage.setScene(currentScene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void backToHub() {
		App.playerLayout.getChildren().removeAll(App.playerOne, App.playerTwo);
		newStage.setScene(App.gameSelection);
		newStage.show();
	}

	private void revealSquare(Button[][] gridArray, Board gameBoard, int row, int column) {
		revealNeighbors(gridArray, gameBoard, row - 1, column - 1);
		revealNeighbors(gridArray, gameBoard, row, column - 1);
		revealNeighbors(gridArray, gameBoard, row + 1, column - 1);
		revealNeighbors(gridArray, gameBoard, row - 1, column);
		revealNeighbors(gridArray, gameBoard, row + 1, column);
		revealNeighbors(gridArray, gameBoard, row - 1, column + 1);
		revealNeighbors(gridArray, gameBoard, row, column + 1);
		revealNeighbors(gridArray, gameBoard, row + 1, column + 1);
	}

	private void revealNeighbors(Button[][] gridArray, Board gameBoard, int row, int column) {
		if (row < 0 || column < 0) {
			return;
		}
		if (row > gameBoard.getBoard()[0].length - 1 || column > gameBoard.getBoard().length - 1) {
			return;
		}
		if (gridArray[row][column].getStyle() == "-fx-background-color : red") {
			return;
		}
		if (gridArray[row][column].isDisable() == true) {
			return;
		}
		if (gameBoard.getBoard()[row][column].getSquareType() == Squares.NUMBER) {
			String num = "" + gameBoard.getBoard()[row][column].getMineAmount();
			gridArray[row][column].setText(num);
			gridArray[row][column].setDisable(true);
			return;
		}
		if (gameBoard.getBoard()[row][column].getSquareType() == Squares.EMPTY) {
			gridArray[row][column].setDisable(true);
			revealSquare(gridArray, gameBoard, row, column);
		}
	}

	private void checkForWin() {
		int revealedSquares = 0;
		for (int row = 0; row < gridArray.length; row++) {
			for (int column = 0; column < gridArray[row].length; column++) {
				if (gridArray[row].length == 9) {
					if (gridArray[row][column].getStyle() == "-fx-background-color : red") {
						continue;
					}
					if (gameBoard.getBoard()[row][column].getSquareType() != Squares.MINE
							&& gridArray[row][column].isDisable() == true) {
						revealedSquares += 1;
					}
					if (revealedSquares == 71) {
						flag.setDisable(true);
						timeLine.stop();
						winFace();
					}
				} else if (gridArray[row].length == 16) {
					if (gameBoard.getBoard()[row][column].getSquareType() != Squares.MINE
							&& gridArray[row][column].isDisable() == true) {
						revealedSquares += 1;
					}
					if (revealedSquares == 216) {
						flag.setDisable(true);
						timeLine.stop();
						winFace();
					}
				} else if (gridArray[row].length == 20) {
					if (gameBoard.getBoard()[row][column].getSquareType() != Squares.MINE
							&& gridArray[row][column].isDisable() == true) {
						revealedSquares += 1;
					}
					if (revealedSquares == 330) {
						flag.setDisable(true);
						timeLine.stop();
						winFace();
					}
				}
			}
		}
	}

	private void resetFlag() {
		for (int i = 0; i < gridArray.length; i++) {
			for (int j = 0; j < gridArray[i].length; j++) {
				if (gridArray[j][i].getStyle() == "-fx-background-color : red") {
					gridArray[j][i].setDisable(false);
					gridArray[j][i].setStyle(null);
					if (gridArray[i].length == 9) {
						gameBoard.setMines(10);
						score.setText("Mines: " + gameBoard.getMines());
					} else if (gridArray[i].length == 16) {
						gameBoard.setMines(40);
						score.setText("Mines: " + gameBoard.getMines());
					} else if (gridArray[i].length == 20) {
						gameBoard.setMines(70);
						score.setText("Mines: " + gameBoard.getMines());
					}

				}
			}
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

	private void winFace() {
		for (int i = 0; i < gridArray.length; i++) {
			for (int j = 0; j < gridArray[i].length; j++) {
				if (gameBoard.getBoard()[i][j].getSquareType() == Squares.MINE) {
					gridArray[i][j].setDisable(true);
				}
			}
		}
		Image winFace = new Image(getClass().getResourceAsStream("win.png"));
		ImageView winView = new ImageView(winFace);
		winView.setFitHeight(40);
		winView.setFitWidth(40);
		faceButton.setGraphic(winView);
		flag.setDisable(true);
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
		gameBoard.changeBoardSize(20, 20);
		run(newStage);
	}

}
