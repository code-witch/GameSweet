package gamesweet.stratego.controller;

import gamesweet.stratego.enumerations.Color;
import gamesweet.stratego.models.Board;
import gamesweet.stratego.models.Tile;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI {
	private static Stage stage;
	GridPane gp = new GridPane();
	StrategoPane currentPane = null;
	Scene scene = new Scene(gp);
	int row = 10;
	int col = 10;
	StrategoPane[][] grid = new StrategoPane[col][row];

	public void init(Stage stage,Board board) {
		GUI.stage = stage;
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
	}

//	public void run() {
//	}

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
				gp.add(currentPane,currentPane.x,currentPane.y);
				gp.add(source, source.x, source.y);
				currentPane = null;
				gp.setStyle("-fx-grid-lines-visible: false;");
				gp.setStyle("-fx-grid-lines-visible: true;");
				
			}
		}
		
	}
	
	private class StrategoPane extends StackPane {
		private int x, y;
		private Tile tile;
		

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
			this.getChildren().add(new Label(this.tile != null
					? this.tile.getOwner() != null 
					? this.tile.getOwner().getName() : "" : ""));
			this.getChildren().get(0).setStyle("-fx-alignment: center; -fx-text-fill: white; -fx-font: 20px 'comic sans ms';");
			System.out.println(this.getChildren().get(0).getStyle());
		}

	}
}
