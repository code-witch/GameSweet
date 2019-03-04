package gamesweet.minesweeper.GUI;



import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("How to Play");
		window.setMinHeight(80);
		

		Label label = new Label();
		label.setText(
				"The objective of the game is to clear a rectangular board containing hidden "
				+ "'mines' or bombs without detonating any of them, with help from clues about"
				+ " the number of neighboring mines in each field. ");
		VBox vBox = new VBox();
		vBox.getChildren().add(label);
		
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.setResizable(false);
		window.showAndWait();
	}

}
