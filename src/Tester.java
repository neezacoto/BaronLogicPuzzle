/**
 * @author Christian
 * 2/25/2022
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Tester class responsible for running the game
 */

public class Tester extends Application {

		/**
		 * start function responsible for setting the scene and stage
		 */
 @Override
 public void start(Stage stage) {
		try {

		Parent root = FXMLLoader.load(getClass().getResource("MenuArea.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		} catch(Exception e) {
		 e.printStackTrace();
		}
	}

 /**
	* main method
	*/
	public static void main(String[] args) {
		launch(args);
	}
}
