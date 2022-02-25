import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @author: Arjun & Christian
 */
public class Board extends GridPane {
	private Grid[] grids;
	private int size;

	// constructor
	Board(int size, int height) {
		// this.setMaxWidth(height * Grid.cell_size);
		// this.setMaxHeight(height * Grid.cell_size);
		//
		// this.setPrefWidth(height * Grid.cell_size);
		// this.setPrefHeight(height * Grid.cell_size);

		this.grids = new Grid[size];
		this.size = size;

		this.setHgap(5.0d);
		this.setVgap(5.0d);

		// int row_size = size;
		// int row = 0;
		// int j = 0;

		// System.out.println(row_size / 2.0f);
		// System.out.println(Math.ceil(row_size / 2));
		// System.out.println((int) Math.ceil(row_size / 2.0f));
		// this.setPrefColumns(2);
		// this.setPrefRows(2);
		for (int i = 0; i < size; i++) {
			// System.out.println("row_size " + row_size);
			// System.out.println("row " + row);
		// for (int i = 0; i < size; i++) {
			this.grids[i] = new Grid(height);
			// this.setBackground(new Background(new BackgroundFill(Color.DIMGRAY.brighter(), null, null)));
			// this.getRowConstraints().add(new RowConstraints(height * Grid.cell_size * 1.4));
			// this.getColumnConstraints().add(new ColumnConstraints(height * Grid.cell_size * 1.4));
			// this.getChildren().add(this.grids[i]);
			// this.add(this.grids[j++], row, i);
			// if (row_size >= (int) Math.ceil(row_size / 2.0f)) {
			// 	row_size -= (int) Math.ceil(row_size / 2.0f);
			// 	i = 0;
			// 	row++;
			// }
		}
		this.add(this.grids[0], 1, 1);
		this.add(this.grids[1], 2, 1);
		this.add(this.grids[2], 1, 2);
	}

	public int size() {
		return this.size;
	}

	public Grid getGrid(int index) {
		return this.grids[index];
	}
}
