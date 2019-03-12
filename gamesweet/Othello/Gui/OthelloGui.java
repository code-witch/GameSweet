package gamesweet.Othello.Gui;

import gamesweet.Othello.game.OthelloGame;
import gamesweet.base.Game;
import gamesweet.hub.App;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OthelloGui implements EventHandler<ActionEvent> {
	private Button[][] boardBlocks = new Button[8][8];
	private VBox vp = new VBox();
	private Image blkCrc = new Image("gamesweet/Othello/diskImg/blkCrc.jpg", 70, 50, true, true);
	private Image whtCrc = new Image("gamesweet/Othello/diskImg/whtCrc.png", 70, 50, true, true);
	private Image clrCrc = new Image("gamesweet/Othello/diskImg/clrCrc.png", 70, 50, true, true);
	private Image[] circles = { blkCrc, whtCrc };
	private Label blkScore = new Label();
	private Label whtScore = new Label();
	private Label playerName = new Label();
	private GridPane board;
	private boolean nextTurn = false;
	private boolean noPlayOptions = false;
	private Stage stage;

	public void start(Stage primaryStage) {
		try {
			createMenu();
			createBoard();
			score();
			showBlkValidMove();
			vp.getChildren().add(board);
			Scene scene = new Scene(vp, 500, 500);
			scene.getStylesheets().add(getClass().getResource("othelloDesign.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(scene);
			stage.setTitle("othello");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createBoard() {
		board = new GridPane();
		board.setPadding(new Insets(50, 50, 50, 50));
		board.setAlignment(Pos.CENTER);
		for (int i = 0; i < boardBlocks.length; i++) {
			for (int j = 0; j < boardBlocks[i].length; j++) {
				boardBlocks[i][j] = new Button();
				boardBlocks[i][j].setMinSize(50, 50);
				boardBlocks[i][j].setOnAction(this);
				boardBlocks[i][j].setDisable(true);
				if (i == 4 && j == 3) {
					boardBlocks[i][j].setDisable(true);
					ImageView img = new ImageView(blkCrc);
					boardBlocks[i][j].setGraphic(img);
				}
				if (i == 3 && j == 3) {
					boardBlocks[i][j].setDisable(true);
					ImageView img = new ImageView(whtCrc);
					boardBlocks[i][j].setGraphic(img);
				}
				if (i == 4 && j == 4) {
					boardBlocks[i][j].setDisable(true);
					ImageView img = new ImageView(whtCrc);
					boardBlocks[i][j].setGraphic(img);
				}
				if (i == 3 && j == 4) {
					boardBlocks[i][j].setDisable(true);
					ImageView img = new ImageView(blkCrc);
					boardBlocks[i][j].setGraphic(img);
				}
				// test button
//				if (i == 2 && j == 4) {
//					boardBlocks[i][j].setDisable(true);
//					ImageView img = new ImageView(blkCrc);
//					boardBlocks[i][j].setGraphic(img);
//				}
				board.add(boardBlocks[i][j], i, j);
			}
		}
	}

	@Override
	public void handle(ActionEvent event) {
		
		OthelloGame.resetButtons(boardBlocks);
		for (int i = 0; i < boardBlocks.length; i++) {
			for (int j = 0; j < boardBlocks[i].length; j++) {
				if (event.getSource() == boardBlocks[i][j]) {
					if (nextTurn == false) {
						OthelloGame.placeBlkDisk(boardBlocks, i, j, circles);
						OthelloGame.CheckBlkDisk(boardBlocks, i, j, circles);
						showWhtValidMove();
							nextTurn = true;
						

					} else {
						OthelloGame.placeWhtDisk(boardBlocks, i, j, circles);
						OthelloGame.CheckWhtDisk(boardBlocks, i, j, circles);
						showBlkValidMove();
							nextTurn = false;
						

					}
					
					if(OthelloGame.getPlayOptions(boardBlocks) == 0) {
						if(nextTurn == true) {
							OthelloGame.resetButtons(boardBlocks);
							showBlkValidMove();
							if(OthelloGame.getPlayOptions(boardBlocks) == 0) {
								noPlayOptions = true;
								OthelloGame.endGame(boardBlocks, blkScore, whtScore, noPlayOptions);
							}else {
								noPlayOptions = false;
								nextTurn = false;
							}
						}else {
							OthelloGame.resetButtons(boardBlocks);
							showWhtValidMove();
							if(OthelloGame.getPlayOptions(boardBlocks) == 0) {
								noPlayOptions = true;
								OthelloGame.endGame(boardBlocks, blkScore, whtScore, noPlayOptions);
							}else {
								noPlayOptions = false;
								nextTurn = false;
							}
						}
					}
					OthelloGame.showTurnName(playerName, nextTurn);
					OthelloGame.trackScore(blkScore, whtScore);
					OthelloGame.endGame(boardBlocks, blkScore, whtScore, noPlayOptions);
					System.out.println("i-" + i);
					System.out.println("j-" + j);
				}
			}
		}
	}

	private void createMenu() {
		Menu gameMenu = new Menu("_Game");
		MenuItem hub = new MenuItem("return to game hub");
		hub.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					App.playerLayout.getChildren().removeAll(App.playerOne, App.playerTwo);
					stage.setScene(App.gameSelection);
					stage.show();
				}
			});
		gameMenu.getItems().add(hub);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(gameMenu);
		vp.getChildren().add(menuBar);
	}

	private void score() {
		HBox scoreBoard = new HBox();
		blkScore.setPadding(new Insets(5, 5, 0, 5));
		whtScore.setPadding(new Insets(5, 5, 0, 5));
		playerName.setPadding(new Insets(5, 5, 0, 5));
		OthelloGame.trackScore(blkScore, whtScore);
		OthelloGame.showTurnName(playerName, nextTurn);
		scoreBoard.getChildren().addAll(blkScore, whtScore, playerName);
		vp.getChildren().add(scoreBoard);
	}

	public void showBlkValidMove() {
		for (int i = 0; i < boardBlocks.length; i++) {
			for (int j = 0; j < boardBlocks[i].length; j++) {
				OthelloGame.validBlkMove(boardBlocks, i, j, clrCrc);
			}
		}
	}

	private void showWhtValidMove() {
		for (int i = 0; i < boardBlocks.length; i++) {
			for (int j = 0; j < boardBlocks[i].length; j++) {
				OthelloGame.validWhtMove(boardBlocks, i, j, clrCrc);
			}
		}
	}

}
