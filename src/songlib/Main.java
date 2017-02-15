package songlib;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;


import songlib.model.Song;
import songlib.view.rootPageController;




public class Main extends Application {
	
    private AnchorPane rootPage;
	
	@Override
	public void start(Stage primaryStage){
		
	       try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("view/rootPage2.fxml"));
	            rootPage = (AnchorPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootPage);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
	}
 
	  public static void main(String[] args) {
	      launch(args);
	  }
}