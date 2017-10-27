//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {
	
    public static void main( String[] args ){
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		window.setTitle("My Event");
		window.setScene(scene);
		window.show();
	}

	public static Window getPrimaryStage() {
		return null;
	}
	
}
