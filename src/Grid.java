/**
 * @author: Arjun
 * 2/26/2022
 */

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.Node;

/**
 * Responsible for creating the grids on the board
 */
public class Grid extends TilePane {
	private static final double cell_size = 25 * 1.5;
	private int size;

	Grid(int size) {
		this.size = size;
		double gap = 5.0d;
		this.setHgap(gap);
		this.setVgap(gap);
		this.setPrefColumns(size);
		this.setBackground(new Background(new BackgroundFill(Color.DIMGRAY.darker().darker(), null, null)));
		// double grayscale = Math.random();
		// this.setBackground(new Background(new BackgroundFill(new Color(grayscale, grayscale, grayscale, 1), null, null)));
		for (int i = 0; i < size * size; i++) {
			final String spacing = "   ";
			Label cell = new Label(spacing);
			cell.setPrefSize(cell_size, cell_size);
			cell.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int grid = -1;
					Board board = (Board) getParent();
					for (int i = 0; i < board.size(); i++) {
						if (board.getGrid(i) == Grid.this)
							grid = i;
					}

					Node label = (Node) ((event.getTarget() instanceof Label) ? event.getTarget() : ((Node) event.getTarget()).getParent());

					int index = Grid.this.getChildren().indexOf(label);
					GameLogic.getInstance().cellClicked(grid, (index - index % Grid.this.size) / Grid.this.size, index % Grid.this.size);
				}
			});
			this.getChildren().add(cell);
		}
	}
	/**
	 * 
	 * @param row index of cell
	 * @param column index of cell
	 * @return value of the cell
	 */
	public int getCell(int row, int column) {
		String str = ((Label) this.getChildren().get(row * this.size + column)).getText();
		char val = str.substring(str.length() - 1).charAt(0);

		switch (val) {
			case 'O':
				return 1;
			case 'X':
				return 2;
			default:
				return 0;
		}
	}

	/**
	 * 
	 * @param row index
	 * @param column index
	 * @param value setting the game values (blank,X,O)
	 */
	public void setCell(int row, int column, int value) {
		char val;
		switch(value) {
			case 1:
			case 3:
				val = 'O';
				break;
			case 2:
			case 4:
				val = 'X';
				break;
			default:
				val = ' ';
		}
		((Label) this.getChildren().get(row * this.size + column)).setText("   " + val);
	}

	/**
	 * resets the grid
	 */
	public void resetGrid() {
		for (int i = 0; i < this.getChildren().size(); i++) {
			((Label) this.getChildren().get(i)).setText("   ");
		}
	}
}
