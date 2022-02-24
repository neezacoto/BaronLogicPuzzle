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

/**
 * @author: Arjun & Christian
 */
public class Board {
	private Grid[] grids;
	private int size;

	// constructor
	Board(int size, int height) {
		this.size = size;
		for (int i = 0; i < size; i++) {
			this.grids[i] = new Grid(height);
		}
	}

	public int size() {
		return this.size;
	}

	public Grid getGrid(int index) {
		return this.grid;
	}
}
