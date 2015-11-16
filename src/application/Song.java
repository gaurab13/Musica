package application;

public class Song {

	private long songId;
	private String songTitle;
	private String songPath;
	private String songArtist;
	private String songAlbum;
	private String songName;
	
	private String songGenre;

	public Song(long songId, String songName,String songPath,String songTitle, String songArtist,String songAlbum, String songGenre) {

		this.songId = songId;
		this.songName=songName;
		this.songPath = songPath;
		
		if (songTitle != null) {
			this.songTitle = songTitle;
			}
		else{
			this.songTitle=this.songName;
		}
		
		if (songArtist != null) {
			this.songArtist = songArtist;
		}
		if (songAlbum != null) {
			this.songAlbum = songAlbum;
		}
		if (songAlbum != null) {
			this.songAlbum = songAlbum;
		}

		if (songGenre != null) {
			this.songGenre = songGenre;
		}

	}
	
	

	public long getId() {
		return this.songId;
	}

	public String getTitle() {
		return this.songTitle;

	}

	public String getSongArtist() {
		return this.songArtist;
	}

	public String getSongAlbum() {
		return this.songAlbum;

	}

	public String getSongAlubm() {
		return this.songAlbum;
	}

	public String getSongGenre() {
		return this.songGenre;
	}
	
	public String getSongPath()
	{
		return this.songPath;
	}

}
