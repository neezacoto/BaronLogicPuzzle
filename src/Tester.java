import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.reflect.Method;

public class Tester extends Application {
		public static void main(String[] args) {
			// for (Method method : (new Grid(4)).getClass().getMethods()) {
			// 	System.out.println(method.getName());
			// }

			launch(args);
		}

		@Override
		public void start(Stage primaryStage) {
				// make a 3x4 board
				Board board = new Board(3, 4);
				GameLogic game = GameLogic.newInstance(board, 5);

				BorderPane root = new BorderPane();
				final double padding = 20.0f;
				root.setMargin(board, new Insets(padding, padding, padding, padding));
				root.setCenter(board);
				primaryStage.setScene(new Scene(root, 750, 750));
				primaryStage.show();
		}
}
