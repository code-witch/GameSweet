package gamesweet.Othello.Gui;

import gamesweet.Othello.game.OthelloGame;
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
	private GridPane board;
	private boolean nextTurn = false;

	public void start(Stage primaryStage) {
		try {
			createMenu();
			createBoard();
			score();
			showValidMove();
			vp.getChildren().add(board);
			Scene scene = new Scene(vp, 500, 500);
			scene.getStylesheets().add(getClass().getResource("othelloDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("othello");
			primaryStage.show();
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
					if(nextTurn == false) {
						OthelloGame.placeBlkDisk(boardBlocks, i, j, circles);
						OthelloGame.CheckBlkDisk(boardBlocks, i, j, circles);
						nextTurn = true;
					}else {
						OthelloGame.placeWhtDisk(boardBlocks, i, j, circles);
						OthelloGame.CheckWhtDisk(boardBlocks, i, j, circles);
						nextTurn = false;
					}
					showValidMove();
					OthelloGame.trackScore(blkScore, whtScore);
					OthelloGame.endGame(boardBlocks, blkScore, whtScore);
					System.out.println("i-" + i);
					System.out.println("j-" + j);
				}
			}
		}
	}

	private void createMenu() {
		Menu gameMenu = new Menu("_Game");
		gameMenu.getItems().add(new MenuItem("return to game hub"));

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(gameMenu);
		vp.getChildren().add(menuBar);
	}

	private void score() {
		HBox scoreBoard = new HBox();
		blkScore.setPadding(new Insets(5, 5, 0, 5));
		whtScore.setPadding(new Insets(5, 5, 0, 5));
		OthelloGame.trackScore(blkScore, whtScore);
		scoreBoard.getChildren().addAll(blkScore, whtScore);
		vp.getChildren().add(scoreBoard);
	}

	public void showValidMove() {
		for (int i = 0; i < boardBlocks.length; i++) {
			for (int j = 0; j < boardBlocks[i].length; j++) {
				if(nextTurn == false) {
					OthelloGame.validBlkMove(boardBlocks, i, j, clrCrc);
				}else {
					OthelloGame.validWhtMove(boardBlocks, i, j, clrCrc);
				}
			}
		}
	}

}
