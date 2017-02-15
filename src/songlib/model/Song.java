package songlib.model;


/**
 * 
 * Model class for a Song
 *
 */

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Song implements Comparable<Song>{
	
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
	
	public Song(String title, String artist){
		this(title,artist,null,null);
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
	
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof Song)){
			return false;
		}
		
		Song other = (Song)obj;
		if((other.getArtist().equalsIgnoreCase(this.artist.getValue())) && (other.getTitle().equalsIgnoreCase(this.title.getValue())))
			return true;
		else
			return false;
	}
	
	@Override
	public int compareTo(Song other) {
		int nameCompared = this.title.getValue().compareToIgnoreCase(other.getTitle());
		if(nameCompared != 0)
			return nameCompared;
		else{
			return this.artist.getValue().compareToIgnoreCase(other.getArtist());
		}
	}
}
