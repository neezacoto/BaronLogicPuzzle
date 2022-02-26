package application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
		// double grayscale = Math.random();
		// this.setBackground(new Background(new BackgroundFill(new Color(grayscale, grayscale, grayscale, 1), null, null)));
		// this.setMaxWidth(size * cell_size);
		// this.setMaxHeight(size * cell_size);
		//
		// this.setPrefWidth(size * cell_size);
		// this.setPrefHeight(size * cell_size);

		// for (int i = 0; i < size; i++) {
		for (int i = 0; i < size * size; i++) {
			// for (int j = 0; j < size; j++) {
				// this.getRowConstraints().add(new RowConstraints(cell_size));
				// this.getColumnConstraints().add(new ColumnConstraints(cell_size));
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