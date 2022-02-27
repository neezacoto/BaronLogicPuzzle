/**
 * @author Arjun
 * 2/26/2022
 *
 */
import java.util.ArrayList;
/**
 * GameLogic is responsible for the logic of the board, and sending inoframtion to and from the scene.
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
	private int[] correctCells; //correct reference for cells
	private int[] cells; //current player board
	private Board board; // the board to be used
	private String[] clues; // clues to be used
	private int clueIndex; // parsing for clues
	private ArrayList<int[]> undoStates; //undo states

	/**
	 *
	 * @return an instance of itself
	 */
	public static GameLogic getInstance() {
		return instance;
	}
	/**
	 *
	 * @param board creates an instance of GameLogic
	 * @return an instance of GameLogic
	 */
	public static GameLogic newInstance(Board board) {
		instance = new GameLogic(board);
		return instance;
	}
	/**
	 * sets instance of the board that will be used
	 */
	private GameLogic() {
		this(new Board(3, 4));
	}

	/**
	 *
	 * @param board takes in a board for the GameLogic to manipulate
	 */
	private GameLogic(Board board) {
		this.board = board;
		this.correctCells = new int[this.board.size() * this.board.gridSize() * this.board.gridSize()];
		this.cells = new int[this.board.size() * this.board.gridSize() * this.board.gridSize()];
		this.undoStates = new ArrayList<int[]>();

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

		for (int i = 0; i < this.board.gridSize(); i++) {
			int row = findCellInColumn(this.correctCells, 0, i, SOLID_TRUE);
			int column = findCellInRow(this.correctCells, 1, row, SOLID_TRUE);

			this.setRow(this.correctCells, 2, column, SOLID_FALSE);
			this.setColumn(this.correctCells, 2, i, SOLID_FALSE);
			setCorrectCell(2, column, i, SOLID_TRUE);
		}
	}

	/**
	 * sets the state to that of a previous one in undostates arrayList
	 */
	public void undo() {

		if(!undoStates.isEmpty())
			this.cells = this.undoStates.remove(undoStates.size() - 1);
		updateBoard();

	}

	/**
	 *
	 * @param numberOfClues the amount of clues wanted to be displayed
	 * @return the string of clues requested
	 */
	public String[] clues(int numberOfClues) {
		if (getInstance() == null)
			return null;
		String[][] collections = this.board.getCollections();
		ArrayList<String> clueBuffer = new ArrayList<String>();
		for(int i=0;i<this.correctCells.length;i++){

			int grid = (i - i % (this.board.gridSize() * this.board.gridSize())) / (this.board.gridSize() * this.board.gridSize());
			int row = (i - i % (this.board.gridSize())) / (this.board.gridSize()) % this.board.gridSize();
			int col = (i - (grid * this.board.gridSize() * this.board.gridSize()) - row * this.board.gridSize()) % this.board.gridSize();

			if(this.getCorrectCell(grid, row, col) == SOLID_TRUE) {
				switch(grid){
					case 0:
						clueBuffer.add(collections[0][col+1] + " " + collections[2][row+1]);

						break;
					case 1:
						clueBuffer.add(collections[2][row+1] + " " + collections[1][col+1]);

						break;
					case 2:
						clueBuffer.add(collections[0][col+1] + " " + collections[1][row+1]);

						break;

				}
			}
		}
			String[] ret = new String[clueBuffer.size()];
			for(int i = 0; i < ret.length; i++){
				ret[i] = clueBuffer.remove((int) Math.floor(Math.random() * clueBuffer.size()));
			}

		this.clues = ret;
		this.clueIndex = numberOfClues - 2;

		return this.hint();
	}

	/**
	 *
	 * @return returns a list of hints to be used
	 */
	public String[] hint() {
		if (this.clueIndex < this.clues.length - 1) {
			this.clueIndex++;
			String[] clueBuffer = new String[this.clueIndex + 1];
			for (int i = 0; i <= this.clueIndex; i++) {
				clueBuffer[i] = this.clues[i];
			}
			return clueBuffer;
		} else return this.clues;
	}

	/**
	 *
	 * @param cells all cells
	 * @param grid grid index of cell
	 * @param row row index of cell
	 * @param value value of cell (Blank, X, O...)
	 * @return returns cell index in row
	 */
	private int findCellInRow(int[] cells, int grid, int row, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			if (getCellInGiantArray(cells, grid, row, i) == value)
				return i;
		}
		return -1;
	}
	/**
	 *
	 * @param cells all cells
	 * @param grid grid index of cell
	 * @param row row index of cell
	 * @param value value of cell (Blank, X, O...)
	 * @return returns cell index in col
	 */
	private int findCellInColumn(int[] cells, int grid, int column, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			if (getCellInGiantArray(cells, grid, i, column) == value)
			return i;
		}
		return -1;
	}

	/**
	 *
	 * @param cells all cells
	 * @param grid grid index of cell
	 * @param row row index of cell
	 * @param value value of cell (Blank, X, O...)
	 */
	private void setRow(int[] cells, int grid, int row, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			setCellInGiantArray(cells, grid, row, i, value);
		}
	}

	/**
	 *
	 * @param cells all cells
	 * @param grid grid index
	 * @param column  col for cell
	 * @param value value of cell (Blank, X, O...)
	 */
	private void setColumn(int[] cells, int grid, int column, int value) {
		for (int i = 0; i < this.board.gridSize(); i++) {
			setCellInGiantArray(cells, grid, i, column, value);
		}
	}

	/**
	 *
	 * @param cells all cells
	 * @param gridIndex grid index
	 * @param rowIndex row of cell
	 * @param columnIndex col of cell
	 * @return returns value of cell (Blank, X, O...)
	 * responsible for getting a cell
	 */
	private int getCellInGiantArray(int[] cells, int gridIndex, int rowIndex, int columnIndex) {
		return cells[gridIndex * this.board.gridSize() * this.board.gridSize() + rowIndex * this.board.gridSize() + columnIndex];
	}

	/**
	 *
	 * @param cells all cells
	 * @param gridIndex index of grid
	 * @param rowIndex row index of cell
	 * @param columnIndex col index of cell
	 * @param value value of cell (Blank, X, O...)
	 * responsible of setting cells
	 */
	private void setCellInGiantArray(int[] cells, int gridIndex, int rowIndex, int columnIndex, int value) {
		cells[gridIndex * this.board.gridSize() * this.board.gridSize() + rowIndex * this.board.gridSize() + columnIndex] = value;
	}

/**
 *
 * @param gridIndex index of grid
 * @param rowIndex row of cell
 * @param columnIndex col of cell
 * @return retruns the correct copy of the cell to reference
 */
	public int getCorrectCell(int gridIndex, int rowIndex, int columnIndex) {
		return this.getCellInGiantArray(this.correctCells, gridIndex, rowIndex, columnIndex);
	}

	/**
	 *
	 * @param gridIndex index of the grid
	 * @param rowIndex row cell of grid
	 * @param columnIndex col cell of grid
	 * @param value value of cell
	 */
	public void setCorrectCell(int gridIndex, int rowIndex, int columnIndex, int value) {
		this.setCellInGiantArray(this.correctCells, gridIndex, rowIndex, columnIndex, value);
	}

	/**
	 *
	 * @param gridIndex index of the grid
	 * @param rowIndex row cell of grid
	 * @param columnIndex col cell of grid
	 * @param value value of cell
	 */
	public int getCell(int gridIndex, int rowIndex, int columnIndex) {
		return this.getCellInGiantArray(this.cells, gridIndex, rowIndex, columnIndex);
	}

	/**
	 *
	 * @param gridIndex index of the grid
	 * @param rowIndex row cell of grid
	 * @param columnIndex col cell of grid
	 * @param value value of cell
	 */
	public void setCell(int gridIndex, int rowIndex, int columnIndex, int value) {
		this.setCellInGiantArray(this.cells, gridIndex, rowIndex, columnIndex, value);
	}

	/**
	 *
	 * @return the current board that GameLogic is using
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 *
	 * @param cell current cell clicked
	 * @return if the cell is true to reference
	 * checking to see if the current cell is solid or not
	 */
	private boolean isSolid(int cell) {
		return cell == BLANK || cell == SOLID_TRUE || cell == SOLID_FALSE;
	}

	/**
	 *
	 * @param cell current cell clicked
	 * @return if the cell is true to reference
	 * checking to see if the cell is correct
	 */
	private boolean isTrue(int cell) {
		return cell == SOLID_TRUE || cell == TEMP_TRUE;
	}

	/**
	 * clears errors from the board
	 * Christian
	 */
	public void clearErrors() {

				for (int i = 0; i < this.board.size(); i++) {
						for (int j = 0; j < this.board.gridSize(); j++) {
								for (int k = 0; k < this.board.gridSize(); k++) {
										if (this.getCell(i, j, k) != BLANK) {
											if (this.isTrue(this.getCorrectCell(i, j, k)) != this.isTrue(this.getCell(i, j, k))) {
												this.setCell(i, j, k, BLANK);
											}
										}
								}
						}
				}
				this.updateBoard();
		}



	public void cellClicked (int grid, int row, int column) {

		int[] copy = new int[this.cells.length];
		for(int i = 0; i<this.cells.length;i++){
			copy[i] = cells[i];
		}

		 this.undoStates.add(copy);

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
			// temp true o
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
				// check to see if there are any "true" cells already in the same row or column
				boolean trueOrTempTrueInSameRowOrColumn = false;
				for (int i = 0; i < this.board.gridSize(); i++) {
					for (int j = 0; j < this.board.gridSize(); j++) {
						int cell = this.getCell(grid, i, j);
						if ((i == row || j == column) && isTrue(cell))
							trueOrTempTrueInSameRowOrColumn = true;
					}
				}

				// if there is a "true" cell in the same row or column, noop
				if (!trueOrTempTrueInSameRowOrColumn) {
					for (int i = 0; i < this.board.gridSize(); i++) {
						for (int j = 0; j < this.board.gridSize(); j++) {
							int cell = this.getCell(grid, i, j);
							if ((i == row || j == column) && cell == BLANK) {
								this.setCell(grid, i, j, TEMP_FALSE);
							}
						}
					}

					this.setCell(grid, row, column, TEMP_TRUE);

					// next to loops check for win -Christian
					// checks the correct number of spaces
					int correctNum = 0;
						for (int i = 0;i < this.board.size(); i++) {
							for (int j = 0; j < this.board.gridSize(); j++) {
								for (int k = 0; k < this.board.gridSize(); k++) {
									if(this.getCorrectCell(i, j, k) == SOLID_TRUE){
										correctNum++;
									}
								}
							}
						}
					// checks to see if all the correct sp
					int currentNum = 0;
					for (int i = 0;i < this.board.size(); i++) {
						for (int j = 0; j < this.board.gridSize(); j++) {
							for (int k = 0; k < this.board.gridSize(); k++) {
								if(((this.getCorrectCell(i, j, k) == SOLID_TRUE) && (this.getCell(i, j, k) == SOLID_TRUE)) ||
									((this.getCorrectCell(i, j, k) == SOLID_TRUE) && (this.getCell(i, j, k) == TEMP_TRUE))){
									currentNum++;
									}
							}
						}
					}


					if(currentNum == correctNum) {
						this.updateBoard();
						SceneController.getInstance().alert();
					}
				}
				break;
		}

		// is solid?
		if (isSolid(cellValue)) {
			for (int i = 0; i < this.board.size(); i++) {
				for (int j = 0; j < this.board.gridSize(); j++) {
					for (int k = 0; k < this.board.gridSize(); k++) {
						if ((!isSolid(this.getCell(i, j, k))) && (i != grid || j != row || k != column)) {
							this.setCell(i, j, k, this.getCell(i, j, k) == TEMP_TRUE ? SOLID_TRUE : SOLID_FALSE);
						}
					}
				}
			}
		}
		this.updateBoard();
	}

	/**
	 * updates the board
	 */
	private void updateBoard() {
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
