package gamesweet.connect4.GUI;

import gamesweet.base.PlayerAmount;
import gamesweet.connect4.controllers.ConnectFour;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI {
	static ConnectFour game = new ConnectFour(PlayerAmount.TWO);
	static Scene[] scenes = new Scene[2];

	public static GridPane run(Stage stage) {
		GridPane grid = new GridPane();
		try {
			Label btn = new Label();
			
			btn.setMinSize(340, 240);
			// Creating a GridPane container
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(5);
			grid.setHgap(5);

			Button guest = new Button("Guest");
			Button savedU = new Button("Returning Player");
			grid.add(guest, 1, 1);
			grid.add(savedU, 3, 1);

			Scene scene = new Scene(grid, 400, 400);
			guest.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Scene scene = new Scene(guestU(stage), 400, 500);
					stage.setScene(scene);
					stage.show();
					game.createGEn();
					System.out.println("Guest Game successfully created.");
					
				}

			});
			savedU.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Scene scene = new Scene(savedU(stage), 400, 500);
					stage.setScene(scene);
					stage.show();
				}

			});

			// How to set the Scene

			// "Printing out" To the Java FX Window
			stage.setScene(scene);
			stage.setTitle("GameSweet - Connect Four");
			stage.show();
			scenes[0] = scene;

//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}
	
	public static GridPane guestU(Stage stage) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		return grid;
	}
	
	public static GridPane savedU(Stage stage) {
		Button button = new Button("Back");
		Label label = new Label("User ID:");
		Button btn = new Button("Submit");
		GridPane grid = new GridPane();
		Alert a = new Alert(AlertType.NONE);
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		TextField name = new TextField();
		name.setPromptText("Please enter your user ID");
		name.setPrefColumnCount(10);
		name.getText();
		
		GridPane.setConstraints(name, 5, 1);
		GridPane.setConstraints(label, 0, 1);
		GridPane.setConstraints(btn, 10, 1);
		GridPane.setConstraints(button, 10, 3);
		
		System.out.println("Saved User Game successfully created.");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				boolean contains = game.createSEn(name.getText());
				if (!contains) {
					a.setAlertType(AlertType.ERROR);
					a.setContentText("The ID # \"" + name.getText() + "\" does not exist within the leaderboard."); 
	                a.show(); 
				}
			}
		});
		button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            	stage.setScene(scenes[0]);
    			stage.setTitle("GameSweet - Connect Four");
    			stage.show();             
            }
        });
		grid.getChildren().addAll(label, name, btn, button);
		return grid;
	}
	
}
