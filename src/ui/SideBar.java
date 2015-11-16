package ui;
/*package application;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public class SideBar extends BorderPane {
	TreeView<String> tree;
	

	
	public SideBar() {

		setMinWidth(180);
		
		
		TreeItem<String> root,  Library, Playlist, Music;
		//root setup
				root= new TreeItem<>();
				root.setExpanded(true);
				
				//Library
				
				Library= makeBranch("Library",root);
				makeBranch("All songs",Library);
				

				Music= makeBranch("Music",root);
				makeBranch("Artist",Music);
				makeBranch("Album",Music);
				makeBranch("Genre",Music);
				

				Playlist= makeBranch("Playlist",root);
				makeBranch("new playlist1",Playlist);
				makeBranch("new playlist2",Playlist);
				makeBranch("new playlist3",Playlist);
				

				tree = new TreeView<String>(root);
				tree.setShowRoot(false);
				tree.setMaxWidth(180);
				
				
			//	tree.getSelectionModel().selectedItemProperty().addListener(arg0);;
				
				tree.getStyleClass().add("myTree"); 
	setCenter(tree);
				
	//	getChildren().add(tree);

	}


	public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
		
		
	}

}
*/