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
 * @author: Christian
 */
public class Board extends GridPane {
	private Grid[] grids;
	private int size;
	private int gridSize;

	// constructor
	Board(int size, int gridSize) {
		this.grids = new Grid[size];
		this.size = size;
		this.gridSize = gridSize;

		this.setHgap(5.0d);
		this.setVgap(5.0d);

		for (int i = 0; i < size; i++) {
			this.grids[i] = new Grid(this.gridSize);
		}

		this.add(this.grids[0], 1, 1);
		this.add(this.grids[1], 2, 1);
		this.add(this.grids[2], 1, 2);
	}

	public int size() {
		return this.size;
	}

	public int gridSize() {
		return this.gridSize;
	}

	public Grid getGrid(int index) {
		return this.grids[index];
	}
}
