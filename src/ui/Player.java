package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class Player extends BorderPane {

	Media media;
	public static MediaPlayer player;
	MediaView view;
	BorderPane mpane;

	ListPanel listPanel;
	BottomBar bottomBar;
	
	Label label;
	public Player(String file) {
		
		media = new Media(file);
		player = new MediaPlayer(media);
		view = new MediaView(player);

		mpane = new BorderPane();
		mpane.setPadding(new Insets(10, 10, 10, 10));
		mpane.setCenter(view);
		label= new Label("NOW Playing");
		label.setTextFill(Color.YELLOW);
		setCenter(mpane);
		
		bottomBar = new BottomBar(player);
		setBottom(bottomBar);

		setStyle("-fx-background-color:#000000 ");

		// player.play();

	}
}
