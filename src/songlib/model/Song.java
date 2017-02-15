package songlib.model;


/**
 * 
 * Model class for a Song
 *
 */

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Song {
	
	private final StringProperty title;
	private final StringProperty artist;
	private final StringProperty album;
	private final StringProperty year;
	
	/**
	 * Default Constructor
	 */
	
	public Song() {
		this(null, null, null, null);
	}
	
	public Song(String title, String artist, String album, String year){
		this.title = new SimpleStringProperty(title);
		this.artist = new SimpleStringProperty(artist);
		this.album = new SimpleStringProperty(album);
		this.year = new SimpleStringProperty(year);
	}
	
	
	public String getTitle(){
		return title.get();
	}
	
	public void setTitle(String title){
		this.title.set(title);
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public String getArtist(){
		return artist.get();
	}
	
	public void setArtist(String artist) {
		this.artist.set(artist);
	}
	
	public StringProperty artistProperty(){
		return artist;
	}
	
	public String getAlbum(){
		return album.get();
	}
	
	public void setAlbum(String album){
		this.album.set(album);
	}
	
	public StringProperty albumProperty(){
		return album;
	}
	
	public String getYear(){
		return year.get();
	}
	
	public void setYear(String year){
		this.year.set(year);
	}
	
	public StringProperty yearProperty(){
		return year;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}
}
