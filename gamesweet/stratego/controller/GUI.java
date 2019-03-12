package gamesweet.stratego.controller;

import gamesweet.hub.App;
import gamesweet.stratego.enumerations.Color;
import gamesweet.stratego.models.Board;
import gamesweet.stratego.models.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI {
	private static int turn = 0;
	private static Stage stage;
	private static Scene mainMenuScene;
	GridPane gp = new GridPane();
	Button btn = new Button("Back");
	VBox vbox = new VBox(gp,btn);
	StrategoPane currentPane = null;
	Scene scene = new Scene(vbox);
	int row = 10;
	int col = 10;
	StrategoPane[][] grid = new StrategoPane[col][row];
	Button sceneChanger = new Button("Next Player");
	Scene noCheatScene = new Scene(new VBox(new Label("No Cheating!"),sceneChanger));

	public void init(Stage stage,Board board) {
		vbox.setAlignment(Pos.CENTER);
		((VBox)noCheatScene.getRoot()).setAlignment(Pos.CENTER);
		btn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				GUI.stage.setScene(mainMenuScene);
			}

			
		});
		sceneChanger.setMinSize(300, 300);
		sceneChanger.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(scene);
			}
			
		});
		
		GUI.stage = stage;
		mainMenuScene = App.gameSelection;
		App.playerLayout.getChildren().removeAll(App.playerOne,App.playerTwo);
		gameRun(board);
	}
	
	
	public void gameRun(Board board) {
		for (int i = 0; i < row; i++) {
			RowConstraints rc = new RowConstraints(50);
			gp.getRowConstraints().add(rc);
		}

		for (int i = 0; i < col; i++) {
			ColumnConstraints cc = new ColumnConstraints(50);
			gp.getColumnConstraints().add(cc);
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Tile currentTile = board.getBoard()[i][j];
				grid[i][j] = new StrategoPane(i,j,currentTile);
				grid[i][j].setOnMouseClicked(new GridEvent());
				gp.add(grid[i][j], i, j);
			}
		}
		gp.setStyle("-fx-grid-lines-visible: true;");
		GUI.stage.setTitle("Stratego");
		GUI.stage.setScene(scene);
		GUI.stage.show();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].tile != null) {
					if (grid[i][j].tile.getOwner() != null) {
						if (grid[i][j].tile.getOwner().getColor() != null) {
							if (grid[i][j].tile.getOwner().getColor() == Color.RED) {
								// red
								if (turn % 2 == 0) {
									// keep red labels
									grid[i][j].label.setText(grid[i][j].tile.getOwner().getName());
								}
								if (turn % 2 == 1) {
									grid[i][j].label.setText("");
								}
							} else {
								// blue
								if (turn % 2 == 0) {
									grid[i][j].label.setText("");
								}
								if (turn % 2 == 1) {
									// keep blue labels
									grid[i][j].label.setText(grid[i][j].tile.getOwner().getName());
								}
							}
						}
					}
				}
			}
		}
	}


	private class GridEvent implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			
			
			if(currentPane == null) {
				currentPane = (StrategoPane) event.getSource();
			} else {
				gp.getChildren().remove(currentPane); // previously clicked
				gp.getChildren().remove((StrategoPane) event.getSource()); // currently clicked
				StrategoPane source = (StrategoPane) event.getSource();
				int x = currentPane.x;
				int y = currentPane.y;
				currentPane.x = source.x;
				currentPane.y = source.y;
				source.x = x;
				source.y = y;
				grid[currentPane.x][currentPane.y] = currentPane;
				grid[source.x][source.y] = source;
		
				gp.add(currentPane,currentPane.x,currentPane.y);
				gp.add(source, source.x, source.y);
				
				stage.setScene(noCheatScene);
				stage.show();
				
				currentPane = null;
				turn++;
				gp.setStyle("-fx-grid-lines-visible: false;");
				gp.setStyle("-fx-grid-lines-visible: true;");
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[i].length; j++) {
						if (grid[i][j].tile != null) {
							if (grid[i][j].tile.getOwner() != null) {
								if (grid[i][j].tile.getOwner().getColor() != null) {
									if (grid[i][j].tile.getOwner().getColor() == Color.RED) {
										// red
										if (turn % 2 == 0) {
											// keep red labels
											grid[i][j].label.setText(grid[i][j].tile.getOwner().getName());
										}
										if (turn % 2 == 1) {
											grid[i][j].label.setText("");
										}
									} else {
										// blue
										if (turn % 2 == 0) {
											grid[i][j].label.setText("");
										}
										if (turn % 2 == 1) {
											// keep blue labels
											grid[i][j].label.setText(grid[i][j].tile.getOwner().getName());
										}
									}
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	private class StrategoPane extends StackPane {
		private int x, y;
		private Tile tile;
		private Label label;
		

		public StrategoPane(int x, int y, Tile tile) {
			this.x = x;
			this.y = y;
			this.tile = tile;
			
			if(this.tile == null) {
				this.setStyle("-fx-background-color: orange;");				
			} else if(this.tile.getOwner() == null) {
				this.setStyle("-fx-background-color: #004C00;"); // green #004C00
			} else if(this.tile.getOwner().getColor() == null) {
				this.setStyle("-fx-background-color: #00CCCC;"); // water #00CCCC
			} else if(this.tile.getOwner().getColor() == Color.RED) {
				this.setStyle("-fx-background-color: #992828;"); //red #992828
			} else if(this.tile.getOwner().getColor() == Color.BLUE) {
				this.setStyle("-fx-background-color: #3232AD;"); // blue #3232AD
			} else {
				this.setStyle("-fx-background-color: black;");
			}
			
//			this.setStyle(this.tile != null
//					? this.tile.getOwner() != null 
//					? this.tile.getOwner().getColor() == Color.RED 
//					? "-fx-background-color: #992828;" 
//					: "-fx-background-color: #3232AD;"
//					: "-fx-background-color: white;"
//					: "-fx-background-color: white;");
			this.label = new Label(this.tile != null
					? this.tile.getOwner() != null 
					? this.tile.getOwner().getName() : "" : "");
			this.getChildren().add(label);
			this.getChildren().get(0).setStyle("-fx-alignment: center; -fx-text-fill: white; -fx-font: 20px 'comic sans ms';");
		}

	}
}
