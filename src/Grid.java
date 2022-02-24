import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Grid extends GridPane {
	Grid(int size) {
		final int unit_size = 25;

		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.getRowConstraints().add(new RowConstraints(unit_size * 1.5));
				this.getColumnConstraints().add(new ColumnConstraints(unit_size * 1.5));
				Button rect = new Button(" ");
				// rect.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (rect.getText().equals(" "))
							rect.setText("X");
						else if (rect.getText().equals("X")) {
							rect.setText("O");
						} else rect.setText(" ");
					}
				});
				this.add(rect, i, j);
			}
		}
	}
}
