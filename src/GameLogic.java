import java.util.ArrayList;

/**
 * @author
 * singleton
 */
public class GameLogic {
	private static GameLogic instance;

	private String[] clues;
	private String[] collections;
	// cell values 0 - blank
	// cell values 1 - O or true
	// cell values 2 - X or false
	// cell values 3 - temp O or temp true
	// cell values 4 - temp X or temp false
	private int[] correctCells;
	private int[] cells;
	private Board board;
	private static String[] collectionNames = new String[] {
		"names",
		"years",
		"cities",
	};

	public static GameLogic getInstance() {
		if (instance == null) instance = new GameLogic();
		return instance;
	}

	public static GameLogic newInstance(Board board, int numberOfClues) {
		instance = new GameLogic(board, numberOfClues);
		return instance;
	}

	private GameLogic() {
		this(new Board(3, 4), 5);
	}

	private GameLogic(Board board, int numberOfClues) {
		this.board = board;
		this.clues = new String[numberOfClues];
		this.correctCells = new int[this.board.size() * this.board.gridSize() * this.board.gridSize()];
		this.cells = new int[this.board.size() * this.board.gridSize() * this.board.gridSize()];

		// pick size collections
		this.collections = new String[this.board.size()];
		ArrayList<String> collectionsList = new ArrayList<String>();
		for (String string : collectionNames) {
			collectionsList.add(string);
		}

		for (int i = 0; i < this.board.size(); i++) {
			this.collections[i] = collectionsList.remove((int) Math.floor(Math.random() * collectionsList.size()));
		}

		ArrayList<Integer> columns = new ArrayList<Integer>(4);
		for (int i = 0; i < this.board.gridSize(); i++) {
			columns.add(i);
		}

		for (int grid = 0; grid < this.board.size() - 1; grid++) {
			for (int row = 0; row < this.board.gridSize(); row++) {
				// pick random column
				int column = columns.remove((int) (Math.floor(Math.random() * columns.size())));
				// set the other cells in the same row to be false
				this.setRow(this.correctCells, grid, row, 2);
				// set the other cells in the same column to be false
				this.setColumn(this.correctCells, grid, column, 2);
				// set that cell to be true
				setCorrectCell(grid, row, column, 1);
				System.out.println(grid + ", " + row + ", " + column);
			}

			for (int i = 0; i < this.board.gridSize(); i++) {
				columns.add(i);
			}
		}

		// start in grid0 move over in grid1 in the same row as grid0
		// record the grid0 column, and the grid1 column
		// the grid2 row is the grid1 column the grid2 column is the grid0 column

		for (int i = 0; i < this.board.gridSize(); i++) {
			int row = findCellInColumn(this.correctCells, 0, i);
			int column = findCellInRow(this.correctCells, 1, row);

			this.setRow(this.correctCells, 2, column, 2);
			this.setColumn(this.correctCells, 2, i, 2);
			setCorrectCell(2, column, i, 1);
		}


		for (int i = 0; i < this.board.size(); i++) {
			for (int j = 0; j < this.board.gridSize(); j++) {
				String row = "[ ";
				for (int k = 0; k < this.board.gridSize(); k++) {
					int cell = this.getCorrectCell(i, j, k);
					row += cell + ", ";
					// this.board.getGrid(i).setCell(j, k, cell);
				}
				System.out.println(row + " ]");
			}
			System.out.println();
		}

		for (int i = 0; i < this.board.size(); i++) {
			for (int j = 0; j < this.board.gridSize(); j++) {
				String row = "[ ";
				for (int k = 0; k < this.board.gridSize(); k++) {
					int cell = this.getCell(i, j, k);
					row += cell + ", ";
					// this.board.getGrid(i).setCell(j, k, cell);
				}
				System.out.println(row + " ]");
			}
			System.out.println();
		}
	}


	// method to search cells in row of grid
	private int findCellInRow(int[] cells, int grid, int row) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			if (getCellInGiantArray(cells, grid, row, i) == 1)
				return i;
		}
		return -1;
	}

	// method to search cells in column of grid
	private int findCellInColumn(int[] cells, int grid, int column) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			if (getCellInGiantArray(cells, grid, i, column) == 1)
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

	// get method for clues
	public String[] getClues() {
		return this.clues;
	}

	//
	public void cellClicked (int gridIndex, int rowIndex, int columnIndex) {
		System.out.println(gridIndex + " " + rowIndex + " " + columnIndex);

		int cellValue = this.getCell(gridIndex, rowIndex, columnIndex);
		switch (cellValue) {
			// blank
			case 0:
				this.setCell(gridIndex, rowIndex, columnIndex, 4);
				break;
			// solid true O
			case 1:
				this.setCell(gridIndex, rowIndex, columnIndex, 0);
				break;
			// solid false X
			case 2:
				this.setCell(gridIndex, rowIndex, columnIndex, 3);
				break;
			// temp true O
			case 3:
				this.setCell(gridIndex, rowIndex, columnIndex, 0);
				break;
			// temp false X
			case 4:
				this.setCell(gridIndex, rowIndex, columnIndex, 3);
				break;
		}

		// is solid?
		if (cellValue == 0 || cellValue == 1 || cellValue == 2) {

		}

		// this.board.getGrid(gridIndex).setCell(rowIndex, columnIndex, this.getCell(gridIndex, rowIndex, columnIndex));
		this.updateBoard();
	}

	// private method to update board's entries
	private void updateBoard() {
		for (int i = 0; i < this.board.size(); i++) {
			for (int j = 0; j < this.board.gridSize(); j++) {
				for (int k = 0; k < this.board.gridSize(); k++) {
					this.board.getGrid(i).setCell(j, k, this.getCell(i, j, k));
				}
			}
		}
	}

}
