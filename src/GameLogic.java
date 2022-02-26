import java.util.ArrayList;

/**
 * @author
 * singleton
 */
public class GameLogic {
	private static GameLogic instance;
	public final int BLANK = 0;
	public final int SOLID_TRUE = 1;
	public final int SOLID_FALSE = 2;
	public final int TEMP_TRUE = 3;
	public final int TEMP_FALSE = 4;
	// cell values 0 - blank
	// cell values 1 - O or true
	// cell values 2 - X or false
	// cell values 3 - temp O or temp true
	// cell values 4 - temp X or temp false
	private int[] correctCells;
	private int[] cells;
	private Board board;

	public static GameLogic getInstance() {
		return instance;
	}

	public static GameLogic newInstance(Board board) {
		instance = new GameLogic(board);
		return instance;
	}

	private GameLogic() {
		this(new Board(3, 4));
	}

	private GameLogic(Board board) {
		this.board = board;
		this.correctCells = new int[this.board.size() * this.board.gridSize() * this.board.gridSize()];
		this.cells = new int[this.board.size() * this.board.gridSize() * this.board.gridSize()];

		ArrayList<Integer> columns = new ArrayList<Integer>(4);
		for (int i = 0; i < this.board.gridSize(); i++) {
			columns.add(i);
		}

		for (int grid = 0; grid < this.board.size() - 1; grid++) {
			for (int row = 0; row < this.board.gridSize(); row++) {
				// pick random column
				int column = columns.remove((int) (Math.floor(Math.random() * columns.size())));
				// set the other cells in the same row to be false
				this.setRow(this.correctCells, grid, row, SOLID_FALSE);
				// set the other cells in the same column to be false
				this.setColumn(this.correctCells, grid, column, SOLID_FALSE);
				// set that cell to be true
				setCorrectCell(grid, row, column, SOLID_TRUE);
			}

			for (int i = 0; i < this.board.gridSize(); i++) {
				columns.add(i);
			}
		}

		// start in grid0 move over in grid1 in the same row as grid0
		// record the grid0 column, and the grid1 column
		// the grid2 row is the grid1 column the grid2 column is the grid0 column

		for (int i = 0; i < this.board.gridSize(); i++) {
			int row = findCellInColumn(this.correctCells, 0, i, SOLID_TRUE);
			int column = findCellInRow(this.correctCells, 1, row, SOLID_TRUE);

			this.setRow(this.correctCells, 2, column, SOLID_FALSE);
			this.setColumn(this.correctCells, 2, i, SOLID_FALSE);
			setCorrectCell(2, column, i, SOLID_TRUE);
		}
	}

	public String[] clues(int numberOfClues) {
		if (getInstance() == null)
			return null;

		for (int i = 0; i < this.board.getCollections().length; i++) {
			for (int j = 1; j < this.board.getCollections()[i].length; j++) {
				// this.board.getCollections()[i][j];
			}
		}

		return new String[] {
			"A clue",
			"Another clue",
			"A third clue"
		};
	}

	// method to search cells in row of grid
	private int findCellInRow(int[] cells, int grid, int row, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			if (getCellInGiantArray(cells, grid, row, i) == value)
				return i;
		}
		return -1;
	}

	// method to search cells in column of grid
	private int findCellInColumn(int[] cells, int grid, int column, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			if (getCellInGiantArray(cells, grid, i, column) == value)
			return i;
		}
		return -1;
	}

	// private method to set every cell in this row
	private void setRow(int[] cells, int grid, int row, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			setCellInGiantArray(cells, grid, row, i, value);
		}
	}

	// private method to set every cell in this column
	private void setColumn(int[] cells, int grid, int column, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			setCellInGiantArray(cells, grid, i, column, value);
		}
	}

	// macro getter
	private int getCellInGiantArray(int[] cells, int gridIndex, int rowIndex, int columnIndex) {
		return cells[gridIndex * this.board.gridSize() * this.board.gridSize() + rowIndex * this.board.gridSize() + columnIndex];
	}

	// macro setter
	private void setCellInGiantArray(int[] cells, int gridIndex, int rowIndex, int columnIndex, int value) {
		cells[gridIndex * this.board.gridSize() * this.board.gridSize() + rowIndex * this.board.gridSize() + columnIndex] = value;
	}

	// getter for the correct cell correction
	public int getCorrectCell(int gridIndex, int rowIndex, int columnIndex) {
		return this.getCellInGiantArray(this.correctCells, gridIndex, rowIndex, columnIndex);
	}

	// setter for the correct cell correction
	public void setCorrectCell(int gridIndex, int rowIndex, int columnIndex, int value) {
		this.setCellInGiantArray(this.correctCells, gridIndex, rowIndex, columnIndex, value);
	}

	// get method
	public int getCell(int gridIndex, int rowIndex, int columnIndex) {
		return this.getCellInGiantArray(this.cells, gridIndex, rowIndex, columnIndex);
	}

	// set method to set other cells in gridpane
	public void setCell(int gridIndex, int rowIndex, int columnIndex, int value) {
		this.setCellInGiantArray(this.cells, gridIndex, rowIndex, columnIndex, value);
	}

	public Board getBoard() {
		return this.board;
	}

	private boolean isSolid(int grid, int row, int column) {
		int cellValue = this.getCell(grid, row, column);
		return cellValue == BLANK || cellValue == SOLID_TRUE || cellValue == SOLID_FALSE;
	}

	/*
		When we click on a blank cell
		That cell becomes a temp X

		When we click on a temp X
		Check if there is an O or temp O in the same row or column {
			noop or warning
		} else {
			That cell becomes a temp O and any blank cells in it's row or column become temp Xs
		}
		Check if win

		When we click on a temp O
		That cell becomes blank, and any temp Xs in the same row or column become blank

		When we click on a solid cell, in any grid all temp cells solidify

		Blank cells count as solid cells
	*/
	// Arjun
	public void cellClicked (int grid, int row, int column) {
		int cellValue = this.getCell(grid, row, column);
		switch (cellValue) {
			// blank
			case BLANK:
				this.setCell(grid, row, column, TEMP_FALSE);
				break;
			// solid true O
			case SOLID_TRUE:
				this.setCell(grid, row, column, BLANK);
				break;
			// solid false X
			case SOLID_FALSE:
				this.setCell(grid, row, column, TEMP_TRUE);
				break;
			//
			case TEMP_TRUE:
				for (int i = 0; i < this.board.gridSize(); i++) {
					if (this.getCell(grid, i, column) == TEMP_FALSE)
						this.setCell(grid, i, column, BLANK);
				}
				for (int i = 0; i < this.board.gridSize(); i++) {
					if (this.getCell(grid, row, i) == TEMP_FALSE)
					this.setCell(grid, row, i, BLANK);
				}
				this.setCell(grid, row, column, BLANK);
				break;
			// temp false X
			case TEMP_FALSE:
				boolean falseOrTempFalseInSameRowOrColumn = false;
				for (int i = 0; i != row && i < this.board.gridSize(); i++) {
					for (int j = 0; j != column && j < this.board.gridSize(); j++) {
						int cell = this.getCell(grid, i, j);
						if (cell == SOLID_FALSE || cell == TEMP_FALSE)
							falseOrTempFalseInSameRowOrColumn = true;
					}
				}

				if (!falseOrTempFalseInSameRowOrColumn) {
					// 	That cell becomes a temp O and any blank cells in it's row or column become temp Xs
					for (int i = 0; i < this.board.gridSize(); i++) {
						if (this.getCell(grid, i, column) == BLANK)
							this.setCell(grid, i, column, TEMP_FALSE);
					}
					for (int i = 0; i < this.board.gridSize(); i++) {
						if (this.getCell(grid, row, i) == BLANK)
							this.setCell(grid, row, i, TEMP_FALSE);
					}
					this.setCell(grid, row, column, TEMP_TRUE);
				}
				// Check if win
				break;
		}

		// is solid?
		if (isSolid(grid, row, column)) {
			for (int i = 0; i < this.board.size(); i++) {
				for (int j = 0; j < this.board.gridSize(); j++) {
					for (int k = 0; k < this.board.gridSize(); k++) {
						if (!isSolid(i, j, k)) {
							this.setCell(i, j, k, this.getCell(i, j, k) == TEMP_TRUE ? SOLID_TRUE : SOLID_FALSE);
						}
					}
				}
			}
		}

		// update the board
		for (int i = 0; i < this.board.size(); i++) {
			for (int j = 0; j < this.board.gridSize(); j++) {
				for (int k = 0; k < this.board.gridSize(); k++) {
					this.board.getGrid(i).setCell(j, k, this.getCell(i, j, k));
				}
			}
		}
	}
}
