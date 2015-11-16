package ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;

public class BottomBar extends BorderPane {
	MediaBar mediaBar;
	SeekBar seekBar;
	AddControl addControl;
	MediaPlayer p;
//NameBar nameBar;
	public BottomBar(MediaPlayer play) {
		p= play;
		//setStyle("-fx-background-color:#2F4F4F");
		
		//setMinHeight(50);
		//setMaxWidth(720);
		//setMinWidth(720);
//		nameBar= new NameBar(p);
		seekBar = new SeekBar(p);
		mediaBar = new MediaBar(p);
		addControl = new AddControl(p);
	//	setTop(nameBar);
		setLeft(mediaBar);
		setRight(addControl);
		setCenter(seekBar);
		//getChildren().addAll(mediaBar, seekBar, addControl);

	}
}

