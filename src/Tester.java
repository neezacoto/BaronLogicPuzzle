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

				// make a 3x4 board
				Board board = new Board(3, 4);
				GameLogic game = new GameLogic();

				BorderPane root = new BorderPane();
				final double padding = 20.0f;
				// AnchorPane.setTopAnchor(board, padding);
				// AnchorPane.setLeftAnchor(board, padding);
				// AnchorPane.setBottomAnchor(board, padding);
				// AnchorPane.setRightAnchor(board, padding);
				// root.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));
				// // FlowPane main = new FlowPane();
				root.setMargin(board, new Insets(padding, padding, padding, padding));
				root.setCenter(board);
				// root.getChildren().add(board);
				// for (int i = 0; i < board.size(); i++) {
				// 	main.getChildren().add(board.getGrid(i));
				// }
				primaryStage.setScene(new Scene(root, 750, 750));
				primaryStage.show();
		}
}
