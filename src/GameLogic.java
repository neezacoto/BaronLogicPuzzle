import java.util.ArrayList;

public class GameLogic {
	private String[] clues;
	private String[] collections;
	private int[] correctCells;
	private int[] cells;
	private static String[] collectionNames = new String[] {
		"names",
		"years",
		"cities",
	};


	public GameLogic() {
		this(3, 4, 5);
	}

	public GameLogic(int size, int gridSize, int numberOfClues) {
		this.clues = new String[numberOfClues];
		this.cells = new int[size * gridSize * gridSize];
		// pick size collections
		this.collections = new String[size];
		ArrayList<String> collectionsList = new ArrayList<String>();
		for (String string : collectionNames) {
			collectionsList.add(string);
		}

		for (int i = 0; i < size; i++) {
			this.collections[i] = collectionsList.remove((int) Math.floor(Math.random() * collectionsList.size()));
		}

		for (int i = 0; i < this.cells.length; i++) {
			System.out.println(i + ". " + this.cells[i]);
		}

		// start in grid0 move over in grid1 in the same row as grid0
		// record the grid0 column, and the grid1 column
		// the grid2 row is the grid1 column the grid2 column is the grid0 column

		// for (int grid = 0; grid < size - 1; grid++) {
		// 	for (int row = 0; row < gridSize; row++) {
		//
		// 		// pick random column
		// 		(Math.floor(Math.random() * gridSize));
		//
		// 		(boolean) Math.floor(Math.random() * 2);
		//
		// 		setCellInGiantArray(i, int row, int column, );
		// 		// for (int column = 0; column < 4; column++) {
		// 		// }
		// 	}
		// }

	}

	public int getCellInGiantArray(int gridIndex, int rowIndex, int columnIndex) {
		return this.cells[gridIndex * 4 * 4 + rowIndex * 4 + columnIndex];
	}

	public void setCellInGiantArray(int gridIndex, int rowIndex, int columnIndex, int value) {
		this.cells[gridIndex * 4 * 4 + rowIndex * 4 + columnIndex] = value;
	}

	public String[] getClues() {
		return this.clues;
	}
}
