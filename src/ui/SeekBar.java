package ui;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SeekBar extends HBox {
	MediaPlayer p;

	Slider time = new Slider();
	MediaBar mediaBar;
	SeekBar seekBar;
Label playTime;

	public SeekBar(MediaPlayer play) {

		p = play;
		setStyle("-fx-background-color:#008080 ");
		setMinWidth(540);
setAlignment(Pos.BOTTOM_CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		
		time.setPadding(new Insets(0, 0, 0, 0));

		HBox.setHgrow(time, Priority.ALWAYS);

		playTime= new Label();
		playTime.setTextFill(Color.YELLOW);
		getChildren().add(time);
		getChildren().add(playTime);
		
		p.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				updateValue();
		
			}
		});

		


		time.valueProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				if (time.isPressed()) {
					p.seek(p.getMedia().getDuration()
							.multiply(time.getValue() / 100));
				}
			}
		});

	}
	
	public void updateValue() {
		
		Platform.runLater(new Runnable() {
			
			public void run() {
				Duration currentTime = p.getCurrentTime();
					time.setValue(p.getCurrentTime().toMillis()/p.getTotalDuration().toMillis()*100);
					playTime.setText(formatTime(currentTime));
			}
		
			
		});
	

	}

public  String formatTime(Duration elapsed) {
		Duration duration = p.getMedia().getDuration();
		int intElapsed = (int) Math.floor(elapsed.toSeconds());
		int elapsedHours = intElapsed / (60 * 60);
		if (elapsedHours > 0) {
			intElapsed -= elapsedHours * 60 * 60;
		}
		int elapsedMinutes = intElapsed / 60;
		int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
				- elapsedMinutes * 60;

		if (duration.greaterThan(Duration.ZERO)) {
			int intDuration = (int) Math.floor(duration.toSeconds());
			int durationHours = intDuration / (60 * 60);
			if (durationHours > 0) {
				intDuration -= durationHours * 60 * 60;
			}
			int durationMinutes = intDuration / 60;
			int durationSeconds = intDuration - durationHours * 60 * 60
					- durationMinutes * 60;
			if (durationHours > 0) {
				return String.format("%d:%02d:%02d/%d:%02d:%02d", elapsedHours,
						elapsedMinutes, elapsedSeconds, durationHours,
						durationMinutes, durationSeconds);
			} else {
				return String.format("%02d:%02d/%02d:%02d", elapsedMinutes,
						elapsedSeconds, durationMinutes, durationSeconds);
			}
		} else {
			if (elapsedHours > 0) {
				return String.format("%d:%02d:%02d", elapsedHours,
						elapsedMinutes, elapsedSeconds);
			} else {
				return String.format("%02d:%02d", elapsedMinutes,
						elapsedSeconds);
			}
		}
	}
}
	
		

