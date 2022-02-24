import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class Tester extends Application {
		public static void main(String[] args) {
				launch(args);
		}

		@Override
		public void start(Stage primaryStage) {
				// primaryStage.setTitle("Hello World!");
				// Button btn = new Button();
				// btn.setText("Say 'Hello World'");
				// btn.setOnAction(new EventHandler<ActionEvent>() {
				//
				// 		@Override
				// 		public void handle(ActionEvent event) {
				// 				System.out.println("Hello World!");
				// 		}
				// });

				Board board = new Board(3, 4);

				StackPane root = new StackPane();
				root.getChildren().add(board.getGrid());
				primaryStage.setScene(new Scene(root, 750, 750));
				primaryStage.show();
		}
}
