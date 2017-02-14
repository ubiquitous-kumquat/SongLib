package songlib.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import songlib.Main;
import songlib.model.Song;



public class rootPageController {
	
	@FXML
	private TableView<Song> songTable;
	
	@FXML
	private TableColumn<Song, String> titleColumn;
	@FXML
	private TableColumn<Song, String> artistColumn;
	@FXML
	private TableColumn<Song, String> albumColumn;
	@FXML
	private TableColumn<Song, String> yearColumn;
	
	@FXML
	private Label titleLabel;
	@FXML
	private Label artistLabel;
	@FXML
	private Label albumLabel;
	@FXML
	private Label yearLabel;
	
	@FXML
	private Button editButton;
	
	// This is the reference to the main application
	
	private Main mainApp;
	
	/**
	 * This constructor is called before the initialize() method.
	 */
	
	public rootPageController(){
	}
	
	/**
	 * Initializes the controller class
	 * Called after fxml file is loaded
	 */
	
	@FXML
	private void initialize(){
		// Initializes song table with 4 columns
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
		albumColumn.setCellValueFactory(cellData -> cellData.getValue().albumProperty());
		yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
		
		// empty song details
		
		showSongDetails(null);
		
		// Listener for user selection
		// Show details when selected
		
		songTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showSongDetails(newValue));
	}
	
	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		
		songTable.setItems(mainApp.getSongData());
		
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
	
	/** 
	 * Handler for Delete Function
	 */
	
	@FXML
	private void deleteSong(){
		int index = songTable.getSelectionModel().getSelectedIndex();
		
		if(index>=0){
			songTable.getItems().remove(index);
		}else{
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Warning!");
			alert.setHeaderText("No Song Selected");
			alert.setContentText("Please select a song in the playlist.");
			
			alert.showAndWait();
		}
		
	}
	
	
	
	
}
