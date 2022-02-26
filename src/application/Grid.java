package application;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;


public class Grid extends TilePane {
	public static final double cell_size = 25 * 1.5;

	Grid(int size) {
		System.out.println("grid");
		double gap = 5.0d;
        this.setStyle("-fx-border-width: 1; -fx-border-color: black;");
		this.setHgap(gap);
		this.setVgap(gap);
		this.setPrefColumns(size);
		this.setBackground(new Background(new BackgroundFill(Color.DIMGRAY.darker().darker(), null, null)));
	
		for (int i = 0; i < size * size; i++) {

				final String spacing = "   ";
				Label rect = new Label(spacing);
				rect.setPrefSize(cell_size, cell_size);
				rect.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
				rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("click");
						if (rect.getText().equals(spacing))
							rect.setText(spacing + "X");
						else if (rect.getText().equals(spacing + "X")) {
							rect.setText(spacing + "O");
						} else rect.setText(spacing);
					}
				});
				// this.add(rect, i, j);
				this.getChildren().add(rect);
			// }
		}
	}

	public void resetGrid() {
		for (int i = 0; i < this.getChildren().size(); i++) {
			((Label) this.getChildren().get(i)).setText("   ");
		}
	}
}