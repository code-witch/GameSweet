package gamesweet.connect4.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI {

	public static void run(Stage stage) {
		try {
			Label btn = new Label();
			
			btn.setMinSize(340, 240);
			// Creating a GridPane container
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(5);
			grid.setHgap(5);

			// Defining the Name text field
//			Label label = new Label("Screen Name:");
//			TextField name = new TextField();
//			name.setPromptText("Please enter your Screen Name");
//			name.setPrefColumnCount(10);
//			name.getText();
//			GridPane.setConstraints(name, 0, 0);
//			grid.getChildren().add(name);

			Button guest = new Button("Guest");
			Button savedU = new Button("Returning Player");
			grid.add(guest, 1, 1);
			grid.add(savedU, 1, 3);

			guest.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					GridPane grid = new GridPane();
					grid.setPadding(new Insets(10, 10, 10, 10));
					grid.setVgap(5);
					grid.setHgap(5);
					Scene scene = new Scene(grid, 400, 500);
					stage.setScene(scene);
					stage.show();
				}

			});
			savedU.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {

				}

			});

			// How to set the Scene
			Scene scene = new Scene(grid, 400, 400);

			// "Printing out" To the Java FX Window
			stage.setScene(scene);
			stage.setTitle("GameSweet - Connect Four");
			stage.show();

//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
