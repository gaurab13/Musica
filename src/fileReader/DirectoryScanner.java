package fileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;

import org.apache.commons.vfs2.FileNotFoundException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import application.AppData;
import application.Song;

public class DirectoryScanner {

	// directory to be added with the help of fileChoser
	// contains list of directory chosen by user
	// not used currently
	public File[] chosenPath;

	//public final File path = new File("E:\\Music");
	public  File path;
	public String songName;
	public String songPath;
	public String songTitle;
	public String songArtist;
	public String songComposer;
	public String songGenre;
	public String songAlbum;

	public long songId = 0;

	public DirectoryScanner() {
		//search(path);
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Scan Music folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): "
					+ chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : "
					+ chooser.getSelectedFile());
			path= chooser.getSelectedFile();
		} else {
			System.out.println("No Selection ");
		}

		search(path);
	}

	private void search(File dir) {

		if (dir.isFile()) {
			// System.out.println("path is file");
			songName = dir.getName();
			if (songName.endsWith(".mp3") || songName.endsWith(".MP3")) {
				songId++;
				songName = songName.substring(0, songName.length() - 4);
				songPath = dir.getAbsolutePath();

				try {

					InputStream input = new FileInputStream(new File(songPath));
					ContentHandler handler = new DefaultHandler();
					Metadata metadata = new Metadata();
					Parser parser = new Mp3Parser();
					ParseContext parseCtx = new ParseContext();
					parser.parse(input, handler, metadata, parseCtx);
					input.close();

					songTitle = metadata.get("title");
					songArtist = metadata.get("xmDM:artist");
					songComposer = metadata.get("xmDM:composer");
					songGenre = metadata.get("xmDM:genre");
					songAlbum = metadata.get("xmDM:album");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (TikaException e) {
					e.printStackTrace();

					// TODO: handle exception

				}

				AppData.songList.add(new Song(songId, songName, songPath,
						songTitle, songArtist, songGenre, songAlbum));
			}
		} else if (dir.isDirectory()) {

			File[] list = dir.listFiles();
			for (int i = 0; i < list.length; i++) {
				search(list[i]);
			}

		}
	}
}
