package gamesweet.Othello.application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid	 = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(10);
			grid.setHgap(10);
			
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; i++) {
					Button b = new Button("block");
					GridPane.setConstraints(b, i, j);
					grid.getChildren().add(b);
				}
			}
			
			Scene scene = new Scene(grid, 500, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("othello");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
