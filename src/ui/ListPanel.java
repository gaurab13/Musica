package ui;

import java.io.File;
import java.net.MalformedURLException;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import application.Main;

public class ListPanel extends BorderPane {
	Label allsongs;
	public static ListView<String> list;
	Main main;
	MediaBar m;
	Player lpplayer;
	
	
	public ListPanel(ObservableList<String> oList) {
		
		

		setMinWidth(150);

		allsongs = new Label("Songs");

		 allsongs.setTextFill(Color.YELLOW);
		setTop(allsongs);


		
		list = new ListView<String>(oList);

		allsongs.setStyle("-fx-text-fill:  #008080");

		setCenter(list);

	}
	
	

	public String convertToFileURL(String filepath) throws MalformedURLException {

		String path = new File(filepath).toURI().toURL().toString();

		System.out.println("PATh  " + path);
		return path;
	}

}
