package songlib.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import songlib.Main;
import songlib.model.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;



public class rootPageController {
	
	@FXML
	private ListView<Song> listView;

	/**
	@FXML
	private TableColumn<Song, String> titleColumn;
	@FXML
	private TableColumn<Song, String> artistColumn;
	@FXML
	private TableColumn<Song, String> albumColumn;
	@FXML
	private TableColumn<Song, String> yearColumn;
	*/
	
	@FXML
	private TextField titleLabel;
	@FXML
	private TextField artistLabel;
	@FXML
	private TextField albumLabel;
	@FXML
	private TextField yearLabel;
	
	@FXML
	private Button editButton;
	
	
	
	
	@FXML
	private TextField titleField;
	@FXML
	private TextField artistField;
	@FXML
	private TextField albumField;
	@FXML
	private TextField yearField;
	
	@FXML
	private Button addButton;

	
	private ObservableList<Song> listViewData = FXCollections.observableArrayList();
	
	
	/**
	 * This constructor is called before the initialize() method.
	 */
	
	public rootPageController(){
		
		listViewData.add(new Song("Blood Sweat & Tears","BTS","You Never Walk Alone","2017"));
		listViewData.add(new Song("BOOMBAYAH","BlackPink","SQUARE TWO","2016"));
		listViewData.add(new Song("Cheer Up","TWICE","PAGE TWO","2016"));
    	listViewData.add(new Song("DOPE","BTS","The Most Beautiful Moments in Life","2016"));
    	listViewData.add(new Song("FXXK IT","BIGBANG","MADE","2016"));
    	listViewData.add(new Song("Hard Carry","GOT7","",""));
    	listViewData.add(new Song("Heartbreaker","NCT 127","LIMITLESS - The 2nd Mini Album - EP","2017"));
    	listViewData.add(new Song("I Don't Disappoint","Jay Park","Everything You Wanted","2016"));
    	listViewData.add(new Song("HYDE","VIXX","",""));
    	listViewData.add(new Song("Lie","Park Jimin","You Never Walk Alone","2017"));
    	listViewData.add(new Song("LOSER","BIGBANG","MADE","2016"));
    	listViewData.add(new Song("Me Like Yuh","Jay Park","Everything You Wanted","2016"));
    	listViewData.add(new Song("Not Today","BTS","You Never Walk Alone","2017"));
    	listViewData.add(new Song("PLAYING WITH FIRE","BlackPink","SQUARE TWO","2016"));
    	listViewData.add(new Song("Pour Up (ft. Zico)","Dean","130 Mood: TRBL","2016"));
    	listViewData.add(new Song("Save Me","BTS","The Most Beautiful Moments in Life","2016"));
    	listViewData.add(new Song("Spring Day","BTS","You Never Walk Alone","2017"));
    	listViewData.add(new Song("STAY","BlackPink","SQUARE TWO","2016"));
    	listViewData.add(new Song("Stigma","Kim Taehyung","You Never Walk Alone","2017"));
    	listViewData.add(new Song("Supplementary Story","BTS","You Never Walk Alone","2017"));
    	listViewData.add(new Song("The Truth Is","Jay Park","Everything You Wanted","2016"));
    	listViewData.add(new Song("Very, Very, Very","IOI","","2016"));
    	listViewData.add(new Song("WHISTLE","BlackPink","SQUARE TWO","2016"));
	}
	
	/**
	 * Initializes the controller class
	 * Called after fxml file is loaded
	 */
	
	@FXML
	private void initialize(){
		
		// empty song details
		
		listView.setItems(listViewData);
		
		// Listener for user selection
		// Show details when selected
		
		listView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showSongDetails(newValue));
	}
	
	/**
	 * Fills all text fields to show details about a song
	 * If specified song is null -> text fields are empty
	 */
	
	private void showSongDetails(Song song){
		if (song != null){
			titleLabel.setText(song.getTitle());
			artistLabel.setText(song.getArtist());
			albumLabel.setText(song.getAlbum());
			yearLabel.setText(song.getYear());
		}else{
			titleLabel.setText("");
			artistLabel.setText("");
			albumLabel.setText("");
			yearLabel.setText("");
		}
	}
	
	
	@FXML
	private void addSong(){
		listViewData.add(new Song(titleField.getText(),artistField.getText(),albumField.getText(),yearField.getText()));

	}
	
	@FXML
	private void addCancel(){
		titleField.setText("");
		artistField.setText("");
		albumField.setText("");
		yearField.setText("");

	}
	
	@FXML
	private void editCancel(){
		
		int index = listView.getSelectionModel().getSelectedIndex();

	}
	
	@FXML
	private void deleteSong(){
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index>=0){
			listView.getItems().remove(index);
		}else{
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!");
			alert.setHeaderText("No Song Selected");
			alert.setContentText("Please select a song in the playlist.");
			
			alert.showAndWait();
		}
		
	}
	

	
	
	
}
