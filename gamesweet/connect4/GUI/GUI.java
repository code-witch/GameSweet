package gamesweet.connect4.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import gamesweet.base.PlayerAmount;
import gamesweet.connect4.controllers.ConnectFour;
import gamesweet.connect4.enums.ChipColor;
import gamesweet.connect4.models.Chip;
import gamesweet.connect4.models.Leaderboard;
import gamesweet.connect4.models.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GUI {
	static ConnectFour game = new ConnectFour(PlayerAmount.TWO);
	static Scene[] scenes = new Scene[2];

	private static final int SIZE = 80;
	private static int turnCount = 0;

	public static GridPane run(Stage stage) {
		scenes[1] = gamesweet.hub.App.gameSelection;
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
			Button newP = new Button("Add New Player");
			Button leaderboard = new Button("Leaderboard");
			Button back = new Button("Back");
			grid.add(guest, 1, 1);
			grid.add(savedU, 1, 2);
			grid.add(newP, 1, 3);
			grid.add(leaderboard, 1, 4);
			grid.add(back, 1, 5);

			leaderboard.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					game.importLeaderboard();
					Stage lead = new Stage();
					Scene scene = new Scene(showLeaderb(lead), 500, 700);
					lead.setScene(scene);
					lead.setTitle("GameSweet - Connect Four: Leaderboard");
					lead.show();
				}

			});

			Scene scene = new Scene(grid, 400, 400);
			guest.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					game.createGEn();
					game.importLeaderboard();
					Scene scene = new Scene(play(stage), 900, 700);
					stage.setScene(scene);
					stage.show();
					System.out.println("Guest Game successfully created.");

				}

			});
			savedU.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					game.importLeaderboard();
					Scene scene = new Scene(savedU(stage), 400, 500);
					stage.setScene(scene);
					stage.show();
				}

			});
			newP.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					game.importLeaderboard();
					Scene scene = new Scene(addNew(stage), 450, 225);
					stage.setScene(scene);
					stage.show();
				}

			});
			back.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					gamesweet.hub.App.playerLayout.getChildren().removeAll(gamesweet.hub.App.playerOne,
							gamesweet.hub.App.playerTwo);
					stage.setScene(scenes[1]);
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

	private static GridPane play(Stage stage) {
		GridPane grid = new GridPane();

		int columns = 7;
		int rows = 6;

		Button lb = new Button("Leaderboard");
		Button back = new Button("Back");
		Button diffi = new Button("Change difficulty");

		Shape board = makeGrid(columns, rows);

		ArrayList<Rectangle> overlay = makeColumns(columns, rows);

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		GridPane.setConstraints(board, 5, 11, columns * 2, rows);

		for (int i = 0; i < columns; i++) {
			GridPane.setConstraints(overlay.get(i), 5, 11);
		}

		GridPane.setConstraints(lb, 3, 17);
		GridPane.setConstraints(back, 5, 17);

		lb.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage lead = new Stage();
				Scene scene = new Scene(showLeaderb(lead), 500, 700);
				lead.setScene(scene);
				lead.setTitle("GameSweet - Connect Four: Leaderboard");
				lead.show();
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(scenes[0]);
				stage.setTitle("GameSweet - Connect Four");
				stage.show();

			}

		});
		for (int i = 0; i < columns; i++) {
			int j = i;
				try {
				overlay.get(i).setOnMouseClicked(e -> {
						playGame();
						final Circle c = placeChip(game.getCurrentP(), j, rows);
						grid.add(c, 5, 11);
						if(checkForWin()) {
							Alert done = new Alert(AlertType.CONFIRMATION);
							done.setContentText(game.getWinner() + " wins!");
							done.showAndWait();
							stage.setScene(scenes[0]);
							stage.setTitle("GameSweet - Connect Four");
							stage.show();
						}
				});
			} catch (NullPointerException npe) {
				turnCount--;
			}
			
		}
//		overlay.get(0).setOnMouseClicked(e -> placeChip(game.getCurrentP(), 0, rows));
//		overlay.get(0).setOnMouseClicked(e -> overlay.get(0).setFill(Color.rgb(34, 139, 34, 0.4)));

		// Add if statement for other levels of difficulties
		grid.getChildren().addAll(board, overlay.get(0), overlay.get(1), overlay.get(2), overlay.get(3), overlay.get(4),
				overlay.get(5), overlay.get(6), lb, back);
		return grid;
	}

	private static GridPane savedU(Stage stage) {
		GridPane grid = new GridPane();
		Button button = new Button("Back");
		Button btn = new Button("Submit");
		Label label = new Label("User ID:");
		Alert a = new Alert(AlertType.NONE);

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		TextField id = new TextField();
		id.setPromptText("Please enter your user ID");
		id.setPrefColumnCount(10);
		id.getText();

		GridPane.setConstraints(id, 5, 1);
		GridPane.setConstraints(label, 0, 1);
		GridPane.setConstraints(btn, 10, 1);
		GridPane.setConstraints(button, 10, 3);

		System.out.println("Saved User Game successfully created.");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				boolean contains = game.createSEn(id.getText());
				if (!contains) {
					a.setAlertType(AlertType.ERROR);
					a.setContentText("The ID # \"" + id.getText() + "\" does not exist within the leaderboard.");
					a.show();
				} else {
					Scene scene = new Scene(play(stage), 900, 700);
					stage.setScene(scene);
					stage.show();
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
		grid.getChildren().addAll(label, id, btn, button);
		return grid;
	}

	private static GridPane addNew(Stage stage) {
		Button button = new Button("Back");
		Label lID = new Label("User ID:");
		Label lName = new Label("Screen Name:");
		Label lQuote = new Label("Quote:");
		Button btn = new Button("Submit");
		GridPane grid = new GridPane();
		Alert a = new Alert(AlertType.NONE);

		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		TextField id = new TextField();
		id.setPromptText("Please enter your user ID");
		id.setPrefColumnCount(10);

		TextField name = new TextField();
		name.setPromptText("Please enter your screen name");
		name.setPrefColumnCount(10);

		TextField quote = new TextField();
		quote.setPromptText("Please enter your catchy phrase");
		quote.setPrefColumnCount(10);

		GridPane.setConstraints(id, 5, 1);
		GridPane.setConstraints(lID, 0, 1);
		GridPane.setConstraints(name, 5, 3);
		GridPane.setConstraints(lName, 0, 3);
		GridPane.setConstraints(quote, 5, 5);
		GridPane.setConstraints(lQuote, 0, 5);
		GridPane.setConstraints(btn, 10, 1);
		GridPane.setConstraints(button, 10, 3);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				boolean contains = game.createSEn(id.getText());
				if (contains) {
					a.setAlertType(AlertType.ERROR);
					a.setContentText("The ID # \"" + id.getText() + "\" already exists within the leaderboard.");
					a.show();
				} else {
					Player p = new Player(name.getText(), id.getText(), quote.getText());
					game.addToLeaderboard(p);

					a.setAlertType(AlertType.INFORMATION);
					a.setContentText("The ID # \"" + id.getText()
							+ "\" has been added to the leaderboard. Please use this ID number to begin playing.");
					a.show();

					stage.setScene(scenes[0]);
					stage.setTitle("GameSweet - Connect Four");
					stage.show();
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
		grid.getChildren().addAll(lID, id, name, lName, quote, lQuote, btn, button);
		return grid;
	}

	private static Shape makeGrid(int columns, int rows) {
		Shape shape = new Rectangle(((columns + 1) * SIZE), ((rows + 1) * SIZE));
		int size = SIZE / 2;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				Circle circle = new Circle(size);
				circle.setCenterX(size);
				circle.setCenterY(size);
				circle.setTranslateX(x * (SIZE + 5) + SIZE / 4);
				circle.setTranslateY(y * (SIZE + 5) + SIZE / 4);

				shape = Shape.subtract(shape, circle);
			}
		}
		return shape;
	}

	private static ArrayList<Rectangle> makeColumns(int columns, int rows) {
		ArrayList<Rectangle> list = new ArrayList<>();
		for (int i = 0; i < columns; i++) {
			Rectangle r = new Rectangle(SIZE, (rows + 1) * SIZE);
			r.setTranslateX(i * (SIZE + 5) + SIZE / 4);
			r.setTranslateY(+13);
			r.setFill(Color.TRANSPARENT);

			r.setOnMouseEntered(e -> r.setFill(Color.rgb(34, 139, 34, 0.4)));
			r.setOnMouseExited(e -> r.setFill(Color.TRANSPARENT));

			list.add(r);
		}

		return list;
	}

	private static TableView<HashMap.Entry<String, Player>> showLeaderb(Stage stage) {
		TableView<Leaderboard> lb = new TableView<>();
		lb.getItems().addAll(game.getLeaderB());

		HashMap<String, Player> users = game.getLeaderB().getPlayersL();

		TableColumn<HashMap.Entry<String, Player>, String> ID = new TableColumn<>("ID");
		ID.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, Player>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Player>, String> param) {
						return new SimpleStringProperty(param.getValue().getKey());
					}

				});

		TableColumn<HashMap.Entry<String, Player>, String> nameC = new TableColumn<>("Name");
		nameC.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, Player>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Player>, String> param) {
						String key = param.getValue().getKey();
						Player p = users.get(key);
						return new SimpleStringProperty(p.getName());
					}

				});

		TableColumn<HashMap.Entry<String, Player>, String> wins = new TableColumn<>("Wins");
		wins.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, Player>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Player>, String> param) {
						String key = param.getValue().getKey();
						Player p = users.get(key);
						return new SimpleStringProperty(Integer.toString(p.getWins()));
					}

				});

		TableColumn<HashMap.Entry<String, Player>, String> losses = new TableColumn<>("Losses");
		losses.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, Player>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Player>, String> param) {
						String key = param.getValue().getKey();
						Player p = users.get(key);
						return new SimpleStringProperty(Integer.toString(p.getLosses()));
					}

				});

		TableColumn<HashMap.Entry<String, Player>, String> quote = new TableColumn<>("Quote");
		quote.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<HashMap.Entry<String, Player>, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Player>, String> param) {
						String key = param.getValue().getKey();
						Player p = users.get(key);
						return new SimpleStringProperty(p.getSlogan());
					}

				});

		ObservableList<HashMap.Entry<String, Player>> items = FXCollections.observableArrayList(users.entrySet());
		TableView<HashMap.Entry<String, Player>> table = new TableView<>(items);

		table.getColumns().setAll(ID, nameC, wins, losses, quote);

		return table;
	}

	private static Circle placeChip(Player p, int column, int rows) {
		// Remember it's an array so it would check the number of rows - 1
		int row = rows - 1;
		boolean success = false;

		Circle circle = null;

		Chip[][] chips = game.getBoard().getChips();
		Chip c = p.getChips().get(p.getChips().size() - 1);

//		Chip c = new Chip(ChipColor.RED);
		do {
			if (chips[column][row] == null && row >= 0) {
				chips[column][row] = c;
				p.removeChip();
				success = true;
				break;
			}
			row--;
		} while (row >= 0);

		int[] spot = { column, row };

		if (success) {
			int size = SIZE / 2;
			circle = new Circle(size);
			circle.setCenterX(size);
			circle.setCenterY(size);
			circle.setTranslateX(column * (SIZE + 5) + SIZE / 4);
			circle.setTranslateY((row * (SIZE + 5) + SIZE / 4) - ((SIZE * 2) + 67));

			circle.setFill(Color.AQUA);

			if (p.getChips().get(0).getChipColor() == ChipColor.RED) {
				circle.setFill(Color.RED);
			} else if (p.getChips().get(0).getChipColor() == ChipColor.YELLOW) {
				circle.setFill(Color.YELLOW);
			}
		}

		return circle;
	}

	private static void playGame() {
		Player[] players = game.getPlayers();
		if (turnCount % 2 == 0) {
			game.takeTurn(players[0]);
			turnCount++;
		} else if (turnCount % 2 == 1) {
			game.takeTurn(players[1]);
			turnCount++;
		}
	}
	
	private static boolean checkForWin() {
		Player[] players = game.getPlayers();
		Chip c = game.getCurrentP().getChips().get(0);
		boolean stopP = false;
		if (game.checkForWin(game.getPlayers()[0], c)) {
			game.declareWinner(players[0]);
			game.setWinenr(players[0].getName());
			stopP = true;
		} else if (game.checkForWin(game.getPlayers()[1], c)) {
			game.declareWinner(players[1]);
			game.setWinenr(players[1].getName());
			stopP = true;
		}
		return stopP;
		
	}

}
