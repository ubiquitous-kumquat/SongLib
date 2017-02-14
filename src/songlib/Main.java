package songlib;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import songlib.model.Song;
import songlib.view.rootPageController;

public class Main extends Application {

    private Stage primaryStage;
    private AnchorPane rootPage;
    
    
    /**
     * The data as an observable list of Songs
     */
    
    private ObservableList<Song> songData = FXCollections.observableArrayList();
    
    public Main() {
    	
    	songData.add(new Song("Blood Sweat & Tears","BTS","You Never Walk Alone","2017"));
    	songData.add(new Song("BOOMBAYAH","BlackPink","SQUARE TWO","2016"));
    	songData.add(new Song("Cheer Up","TWICE","PAGE TWO","2016"));
    	songData.add(new Song("DOPE","BTS","The Most Beautiful Moments in Life","2016"));
    	songData.add(new Song("FXXK IT","BIGBANG","MADE","2016"));
    	songData.add(new Song("Hard Carry","GOT7","",""));
    	songData.add(new Song("Heartbreaker","NCT 127","LIMITLESS - The 2nd Mini Album - EP","2017"));
    	songData.add(new Song("I Don't Disappoint","Jay Park","Everything You Wanted","2016"));
    	songData.add(new Song("HYDE","VIXX","",""));
    	songData.add(new Song("Lie","Park Jimin","You Never Walk Alone","2017"));
    	songData.add(new Song("LOSER","BIGBANG","MADE","2016"));
    	songData.add(new Song("Me Like Yuh","Jay Park","Everything You Wanted","2016"));
    	songData.add(new Song("Not Today","BTS","You Never Walk Alone","2017"));
    	songData.add(new Song("PLAYING WITH FIRE","BlackPink","SQUARE TWO","2016"));
    	songData.add(new Song("Pour Up (ft. Zico)","Dean","130 Mood: TRBL","2016"));
    	songData.add(new Song("Save Me","BTS","The Most Beautiful Moments in Life","2016"));
    	songData.add(new Song("Spring Day","BTS","You Never Walk Alone","2017"));
    	songData.add(new Song("STAY","BlackPink","SQUARE TWO","2016"));
    	songData.add(new Song("Stigma","Kim Taehyung","You Never Walk Alone","2017"));
    	songData.add(new Song("Supplementary Story","BTS","You Never Walk Alone","2017"));
    	songData.add(new Song("The Truth Is","Jay Park","Everything You Wanted","2016"));
    	songData.add(new Song("Very, Very, Very","IOI","","2016"));
    	songData.add(new Song("WHISTLE","BlackPink","SQUARE TWO","2016"));
    	
    	
    	
    }
    
    /**
     * Returns the data as observable list of Songs
     */
    
    public ObservableList<Song> getSongData(){
    	return songData;
    }
    
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Song Library App");

        initRootPage();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootPage() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/rootPage.fxml"));
            rootPage = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootPage);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Give the controller access to the main app
            rootPageController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}