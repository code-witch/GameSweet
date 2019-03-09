package gamesweet.minesweeper.GUI;

import gamesweet.minesweeper.enumpkg.Squares;
import gamesweet.minesweeper.model.Board;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
//	private final Integer starttime = 0;
	private Integer seconds;

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
			h2P.setOnAction(e -> AlertBox.display());
			helpMenu.getItems().addAll(h2P, new MenuItem("Go back to HUB"));

			MenuBar menuBar = new MenuBar();
			menuBar.getMenus().addAll(gameMenu, helpMenu);

			// Set up the smileyface button
			Button smileyButton = new Button();
			Image smileyFace = new Image(getClass().getResourceAsStream("smiley.png"));
			ImageView smile = new ImageView(smileyFace);
			smile.setFitHeight(30);
			smile.setFitWidth(30);
			smileyButton.setGraphic(smile);
			smileyButton.setOnAction(e -> smileyReset());

			Label timer = new Label();
			timer.setPrefSize(50, 100);
			timer.setFont(Font.font(30));
			timer.setTextFill(Color.RED);
			
			Button score = new Button("Mines");
			score.setAlignment(Pos.CENTER_LEFT);
			bar.getChildren().addAll(score, smileyButton, timer);
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
									System.out.println("Game over");
								}
								if (rightClick == MouseButton.SECONDARY) {
									System.out.println("Flaged square");
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
								}
								if (rightClick == MouseButton.SECONDARY) {
									System.out.println("Flaged square");
								}
							}
						});
					}

					// Set up empty
					if (gameBoard.getBoard()[row][col].getSquareType() == Squares.EMPTY) {
						Button empty = new Button();
						empty.setPrefSize(50, 50);
						empty.setStyle("-fx-background-color : grey");
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
								}
								if (rightClick == MouseButton.SECONDARY) {
									System.out.println("Flaged square");
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
				bar.setMargin(smileyButton, new Insets(0, 120, 0, 120));
				currentScene = new Scene(vBox, 400, 440);
				doTime(timer);
			} else if (gridArray[0].length == 16) {
				currentScene = new Scene(vBox, 800, 800);
				bar.setMargin(smileyButton, new Insets(0, 310, 0, 310));
				doTime(timer);
			} else if (gridArray[0].length == 30) {
				currentScene = new Scene(vBox, 1300, 800);
				bar.setMargin(smileyButton, new Insets(0, 560, 0, 560));
				doTime(timer);
			}

			primaryStage.setScene(currentScene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doTime(Label lb) {
		seconds = 0;
		Timeline time = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				seconds++;
				lb.setText("" + seconds.toString());
				if (seconds >= 999) {
					time.stop();
				}
			}
		});
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(frame);
		if (time != null) {
			time.stop();
		}
		time.play();

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
