package gamesweet.connect4.GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI {

	public static void run(Stage primaryStage) {
		try {
			//Creating a GridPane container
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(5);
			grid.setHgap(5);
			
			//Defining the Name text field
//			Label label = new Label("Screen Name:");
//			TextField name = new TextField();
//			name.setPromptText("Please enter your Screen Name");
//			name.setPrefColumnCount(10);
//			name.getText();
//			GridPane.setConstraints(name, 0, 0);
//			grid.getChildren().add(name);
			
			Button guest
			
			//How to set the Scene
			Scene scene = new Scene(grid, 400, 400);
			
			//"Printing out" To the Java FX Window
			primaryStage.setScene(scene);
			primaryStage.setTitle("Other Demo");
			primaryStage.show();

			
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
