package gamesweet.minesweeper.GUI;



import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void display(String text) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("How to Play");
		window.setMinHeight(80);
		

		Label label = new Label();
		label.setFont(Font.font(30));
		label.setText(text);
		VBox vBox = new VBox();
		vBox.getChildren().add(label);
		
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

}
