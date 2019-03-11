package gamesweet.hub;

import java.util.Random;

import gamesweet.base.PlayerAmount;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	Hub hub = new Hub();
	static GridPane gp = new GridPane();
	public final static Scene gameSelection = new Scene(gp);
	Button submit = new Button("Submit");
	Button back = new Button("Back");
	public static TextField playerOne;
	public static TextField playerTwo;
	public final static VBox playerLayout = new VBox();
	Scene playerScene = new Scene(playerLayout);	
	

	@Override
	public void init() {

	}
	
	@Override
	public void start(Stage stage) throws Exception {
		int counter = 0;
		int x = 0;
		int y = 0;
		for(String key : hub.getGameOptions().keySet()) {
			Button btn = new Button(key);
			btn.setMinSize(200, 200);
			btn.setAlignment(Pos.CENTER);
			btn.setStyle(String.format("-fx-text-fill: white; -fx-background-color: rgb(%d,%d,%d);-fx-font: 20px 'comic sans ms';",new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
			if(counter == 1) {
				x = 1;
				y = 0;
			}else if(counter == 2) {
				x = 0;
				y = 1;
			} else if(counter == 3) {
				x = 1;
				y = 1;
			}
			counter++;
			gp.add(btn,y,x);			
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					playerOne = new TextField("Player 1");
					playerLayout.getChildren().add(playerOne);
					
					if(hub.getGameOptions().get(key).getPlayerAmount() == PlayerAmount.TWO) {
						 playerTwo = new TextField("Player 2");	
						 playerLayout.getChildren().add(playerTwo);
						 
						 submit.setOnAction(new EventHandler<ActionEvent>() {
							 @Override
							 public void handle(ActionEvent event) {
								 hub.getGameOptions().get(key).init(stage,playerOne.getText(),playerTwo.getText());					
								 System.out.println("Player 1: " + playerOne.getText());
								 System.out.println("Player 2: " + playerTwo.getText());
							 }
						 });
						 
					} else {
						submit.setOnAction(new EventHandler<ActionEvent>() {
							 @Override
							 public void handle(ActionEvent event) {
								 hub.getGameOptions().get(key).init(stage,playerOne.getText());					
								 System.out.println("Player 1: " + playerOne.getText());
							 }
						 });
					}
		
					
					
					
					
					HBox hbox = new HBox(submit, back);
					playerLayout.getChildren().add(hbox);
					stage.setScene(playerScene);
					stage.show();
				}	
			});
			back.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					playerLayout.getChildren().removeAll(playerOne,playerTwo);
					stage.setScene(gameSelection);
					stage.show();
				}});
		}
		stage.setMinHeight(400);
		stage.setMinWidth(400);
		stage.setTitle("GameSweet");
		stage.setScene(gameSelection);
		stage.show();
	}

}
