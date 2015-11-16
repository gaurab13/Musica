package ui;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;

public class AddControl extends GridPane {
	MediaPlayer p;
	//Button shuffle;
	//Button loop;
	Slider vol = new Slider();
	Label volume = new Label("Volume: ");

	public AddControl(MediaPlayer play) {
	
		p = play;
		
		setVgap(5);
		setHgap(3);

		
		//shuffle= new Button("shuffle");
	//	GridPane.setConstraints(shuffle, 0, 0);
		
		//loop= new Button("loop");
		//GridPane.setConstraints(loop, 1, 0);
		
		GridPane.setConstraints(volume, 0, 3);
		GridPane.setConstraints(vol, 1, 3);
		
		
		setPadding(new Insets(10, 10, 10, 10));
		setMinWidth(180);
		
		setStyle("-fx-background-color:#008080");
		
		
		vol.setPrefWidth(100);
		vol.setMinWidth(80);
		vol.setValue(100);
		
		getChildren().addAll(volume, vol);
		
		vol.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if(vol.isPressed()){
					p.setVolume(vol.getValue()/100);
				}
			
			}
		});
	}
}
