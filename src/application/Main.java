package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.Player;
import ui.BottomBar;
import fileReader.*;
import ui.ListPanel;

import java.util.Iterator;

public class Main extends Application {
	FileChooser fileChooser;
	Player player;
	Button fullscreen;
	BottomBar b;
	MediaPlayer p;
	String fileUri;
	DirectoryScanner ds;
	Scene scene;
	ListPanel listPanel;
	public String fileName;
public MenuBar menu;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	//	ds = new DirectoryScanner();

		MenuItem open = new MenuItem("open");
		//MenuItem playlist = new MenuItem("playlist");
		MenuItem addfolder= new MenuItem("Add folder");
		Menu file = new Menu("File");
		Menu scan= new Menu("Scan");
		
		menu = new MenuBar();
		scan.getItems().add(addfolder)	;
		file.getItems().add(open);
	//	file.getItems().add(playlist);
		menu.getMenus().add(file);
		menu.getMenus().add(scan);


		menu.setStyle("-fx-background-color: #008080 ");
		menu.setMinHeight(30);

		fileChooser = new FileChooser();

		open.setOnAction(e -> {
			Player.player.pause();
			File files = fileChooser.showOpenDialog(primaryStage);
			if (files != null) {
				try {
					String demoname = files.getName();
					System.out.println("name: " + demoname);
					String demo = files.toURI().toURL().toExternalForm();
					System.out.println("Demo " + demo);

					player = new Player(demo);

					Player.player.play();
					scene = new Scene(player, 900, 550, Color.BLACK);
					primaryStage.setScene(scene);

					player.setTop(menu);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		addfolder.setOnAction(e -> {
ds= new DirectoryScanner();
			ObservableList<String> tempList = FXCollections
					.observableArrayList();
			// looping through arraylist to get fileName

			Iterator<Song> i = AppData.songList.iterator();
			while (i.hasNext()) {
				tempList.add((String) i.next().getTitle());
			}

			// getting listPanel
			listPanel = new ListPanel(tempList);

			player.setLeft(listPanel);

			// pausing music
			Player.player.pause();

			// setting ClickListener for item in listPanel
			ListPanel.list.setOnMouseClicked(new EventHandler<MouseEvent>() {

				public void handle(MouseEvent click) {

					if (click.getClickCount() == 2) {
						// Use ListView's getSelected Item
						int selectedIndex = ListPanel.list.getSelectionModel()
								.getSelectedIndex();

						try {

							Player.player.pause();
							fileUri = listPanel
									.convertToFileURL(AppData.songList.get(
											selectedIndex).getSongPath());
					
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println(fileUri);
						player = new Player(fileUri);
						Player.player.play();

						scene = new Scene(player, 900, 550, Color.BLACK);
						primaryStage.setScene(scene);
						player.setTop(menu);
						player.setLeft(listPanel);

					}
				}

			});

		});

		// BorderPane pane = new BorderPane();
		player = new Player("file:///C:/song2.mp3");

		player.setTop(menu);

		scene = new Scene(player, 900, 550, Color.BLACK);
		scene.getStylesheets().add("stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Musica");

		primaryStage.show();
	}

}
