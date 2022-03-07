import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayArea {
		private Stage stage;
		private BorderPane root;
		private Scene menu,game;

		public PlayArea(Stage stage) {
				this.stage = stage;
				buildUI();
		}

		private void buildUI() {
				root = new BorderPane();

				menu = new Scene(root);
				menu.getStylesheets().add("/styles.css");
				stage.setTitle("Logic Game");
				stage.setScene(menu);
				stage.setMinWidth(750);
				stage.setMinHeight(750);
				stage.show();
		}

		private Label createLabel(String text) {
				Label label = new Label(text);
				label.setMaxWidth(Double.MAX_VALUE);
				label.setMaxHeight(Double.MAX_VALUE);
				label.setMinWidth(150);
				// BorderPane.setMargin(label, new);
				return label;
		}

}
