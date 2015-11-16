package ui;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import application.Main;
public class MediaBar extends HBox {
	
	Button playButton = new Button(">");
	Button prev = new Button("<<");
	Button next = new Button(">>");
	MediaPlayer p;
	private boolean atEndOfMedia = false;
	MediaBar mediaBar;
	public Duration duration;
	private boolean stopRequested = false;
	private final boolean repeat = false;
	static Slider time;
	ListPanel listPanel;
static Label playTime;
Label timeLabel;
public int currentitem;
Main main;
	public MediaBar(MediaPlayer play) {
		p = play;
		setStyle("-fx-background-color:#008080");
		setMinWidth(180);
		setAlignment(Pos.CENTER);

		setPadding(new Insets(10, 10, 10, 10));

		playButton.setPrefWidth(60);
	prev.setPrefWidth(40);
next.setPrefWidth(40);

		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Status status = p.getStatus();
			
				
				if (status == Status.UNKNOWN || status == Status.HALTED) {

					return;
				}

				if (status == Status.PAUSED || status == Status.READY
						|| status == Status.STOPPED) {

					if (atEndOfMedia) {
						p.seek(p.getStartTime());
						atEndOfMedia = false;
					}
					p.play();
					playButton.setText("||");
				} else {
					p.pause();
					playButton.setText(">");

				}

			}
		});
		
		next.setOnAction((ActionEvent e) -> {
			p.seek(p.getCurrentTime().multiply(1.5));
			});
		
		prev.setOnAction((ActionEvent e) -> {
			p.seek(p.getCurrentTime().divide(1.5));
			});
		
		getChildren().addAll(prev, playButton, next);	

		p.currentTimeProperty().addListener(new InvalidationListener() {
			public void invalidated(Observable ov) {
				updateValue();
			}
		});

		p.setOnPlaying(new Runnable() {
			public void run() {
				if (stopRequested) {
					p.pause();
					stopRequested = false;
				} else {
					playButton.setText("||");
				}
			}
		});

		p.setOnPaused(new Runnable() {
			public void run() {
				System.out.println("onPaused");
				playButton.setText(">");
			}
		});

		p.setOnReady(new Runnable() {
			public void run() {
				duration = p.getMedia().getDuration();
			}
		});

		p.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
		p.setOnEndOfMedia(new Runnable() {
			public void run() {
				if (!repeat) {
					playButton.setText(">");
					stopRequested = true;
					atEndOfMedia = true;
				}
			}
		});

	}
	
	public void updateValue() {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			
			public void run() {

			p.getCurrentTime();
			
			}
		});
	}

}

