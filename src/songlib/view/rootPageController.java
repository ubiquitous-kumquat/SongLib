package songlib.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import songlib.model.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;


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
	private TextField fileField;
	
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
//	private ObservableList<Song> listViewData_IMPORT = FXCollections.observableArrayList();
	
	
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
		
		// empty song details
		
		listView.setItems(listViewData);
		
		// Listener for user selection
		// Show details when selected
		
		listView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showSongDetails(newValue));
		if(!listViewData.isEmpty())
			listView.getSelectionModel().select(0);

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
	
	private int findSong(String name, String artist){
		int i = 0;
		int result = 0;
		if(listViewData.size() == 0)
			return -2;
		Song curr = new Song(name,artist);
		if(curr.compareTo(listViewData.get(0)) < 0) //need to add to front
			return 0;
		for(i = 0; i < listViewData.size(); i++){
			result = curr.compareTo(listViewData.get(i));
			if( result <= 0 )
				return i;
		}
		return i;
	}
	
	
	private void addSong(Song song){
//		Song song = new Song(titleField.getText(),artistField.getText(),albumField.getText(),yearField.getText());
		if(song.getTitle().trim().length() <= 0 || song.getArtist().trim().length() <= 0){
			illegalArgumentError(false);	
			return;
		}
		int index = findSong(song.getTitle(),song.getArtist());
		if(index == -2) //first addition
			listViewData.add(song);
		else if(index == listViewData.size()){ //adding to end
			if (listViewData.get(index-1).equals(song)) //song already exists
				illegalArgumentError(true);
			if(song.compareTo(listViewData.get(index-1)) > 0) //add behind last
				listViewData.add(song);
			else
				listViewData.add(index-1,song);
		}else{ //adding to somewhere that isn't the front or end
			if(listViewData.get(index).equals(song)) //song already exists
				illegalArgumentError(true);	
			else
				listViewData.add(index,song); //adding to list in proper index
		}
		listView.getSelectionModel().select(index);
	}
	
//	@FXML
//	private void addSong(){
//		listViewData.add(new Song(titleField.getText(),artistField.getText(),albumField.getText(),yearField.getText()));
//
//	}
	
	@FXML
	private void addCancel(){

		titleField.setText("");
		artistField.setText("");
		albumField.setText("");
		yearField.setText("");

	}
	
	@FXML
	private void editCancel(){
		showSongDetails(null);

	}

	
	//Pass in the song to be edited
/*	@FXML
	private void editSong(Song originalSong, Song newSong) throws Exception{
		if(listViewData==null || listViewData.size() == 0)
			return;
		if(listViewData.size() == 1){
			listViewData.remove(0);
		}
		int index = findSong(originalSong.getTitle(),originalSong.getArtist());
		if(!(listViewData.get(index).equals(originalSong))) //song did not exist in list
			throw new IllegalArgumentException();
		listViewData.remove(index);
		listViewData.addSong();
	} */
	
	@FXML
	private void editSong(){
		
		Song editedSong = new Song(titleLabel.getText(),artistLabel.getText(),albumLabel.getText(),yearLabel.getText());
		listViewData.set(listView.getSelectionModel().getSelectedIndex(), editedSong);
	

	}
	
	@FXML
	private void deleteSong(){
		if(!confirmation())
			return;
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index>=0){
			listView.getItems().remove(index);
			if(listViewData.size() == index && listViewData.size() != 0){//the last thing was deleted
				listView.getSelectionModel().select(index-1);
			}else if(listViewData.size() == 0){//list is empty, don't select anything
				listView.getSelectionModel().clearSelection();
			}else{
				listView.getSelectionModel().select(index);
			}
		}else{
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!");
			alert.setHeaderText("No Song Selected");
			alert.setContentText("Please select a song in the playlist.");
			
			alert.showAndWait();
		}
		
	}
	@FXML
	private void edit(){
		if(!confirmation())
			return;
//		Song selected = listViewData.get(listView.getSelectionModel().getSelectedIndex());
//		int index = listView.getSelectionModel().getSelectedIndex();
		Song song = new Song(titleLabel.getText(),artistLabel.getText(),albumLabel.getText(),yearLabel.getText());
		if(listViewData.contains(song)){
			illegalArgumentError(true);
			listView.getSelectionModel().clearAndSelect(listView.getSelectionModel().getSelectedIndex());
			return;
		}
		listViewData.remove(listView.getSelectionModel().getSelectedIndex());
		addSong(song);
	}
	@FXML
	private void add(){
		if(!confirmation())
			return;
		Song song = new Song(titleField.getText(),artistField.getText(),albumField.getText(),yearField.getText());
		addSong(song);
	}
	
	private void illegalArgumentError(boolean duplicate){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("An action requires further attention");
		if(duplicate)
			alert.setContentText("Song already exists in list. Please enter information for a song not already in list");
		else
			alert.setContentText("Please enter title and artist name, at the least.");
		alert.showAndWait();
	}
	
	private boolean confirmation(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("An action requires further attention");
		alert.setContentText("Are you sure?");
		
		alert.showAndWait();
		
		if(alert.getResult() == ButtonType.OK)	//continue
			return true;
		else									//stop
			return false;
		
	}
	
	public ObservableList<Song> readFile(String filepath) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		ObservableList<Song> listViewData_IMPORT = FXCollections.observableArrayList();
		
		String line = "";
		while((line = br.readLine()) != null){
			String[] split = line.split(",");
			String album = "";
			String year = "";
			if(split[2].length() > 2){
				album = split[2].substring(1, split[2].length()-1);
			}
			if(split[3].length() > 2){
				year = split[3].substring(1, split[3].length()-1);
			}
			listViewData_IMPORT.add(new Song(split[0].substring(1, split[0].length()-1),split[1].substring(1, split[1].length()-1),album,year)); 
		}
		return listViewData_IMPORT;
	} 

	@FXML
	private void importList(){
		String filename = fileField.getText();
//		file = ".."+File.separator+"src"+ File.separator+file+".txt";
		File file = new File("src" + File.separator + filename+".txt");
		
		try {
			listViewData = readFile(file.getAbsolutePath());
			initialize();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			illegalArgumentError(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			illegalArgumentError(false);
		}
		
	}


	@FXML
	private void exportList(){
		String file = fileField.getText();
		String bigString = "";
//		bigString.re
		for(int i = 0; i < listViewData.size(); i++){
			bigString+="\"";
			bigString+=listViewData.get(i).getTitle();
			bigString+="\"";
			bigString+=",";
			bigString+="\"";
			bigString+=listViewData.get(i).getArtist();
			bigString+="\"";
			bigString+=",";
			bigString+="\"";
			bigString+=listViewData.get(i).getAlbum();
			bigString+="\"";
			bigString+=",";
			bigString+="\"";
			bigString+=listViewData.get(i).getYear();
			bigString+="\"";
			bigString+="\n";
		}
		try {
			writeFile(file,bigString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			illegalArgumentError(false);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			illegalArgumentError(false);
		}
		
	}
	
	private void writeFile(String filename, String bigString) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer;
		File file = new File("src" + File.separator + filename+".txt");
		
		if(file.exists()){
			System.out.println("File exists!");
		}else{
			System.out.println("File DOESN'T exists!");
			System.out.println(new File("src"+ File.separator+filename+".txt").getAbsoluteFile());
		}
		writer = new PrintWriter(file);
		System.out.println(bigString);
		for(String s: bigString.split("\n")){
			writer.println(s);
		}
		writer.close();
	} 
	
	
}
