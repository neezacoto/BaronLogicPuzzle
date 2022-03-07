/**
 * @author Arjun
 * 2/25/2022
 */

import java.util.ArrayList;
import javafx.scene.layout.GridPane;

/**
 * Responsible for redering the grids from Grid.java into a table
 */
public class Board extends GridPane {
	private Grid[] grids;
	private int size;
	private int gridSize;
	private String[][] collections;

	// constructor
	Board(int size, int gridSize) {

		this.grids = new Grid[size];
		this.size = size;
		this.gridSize = gridSize;

		 this.setHgap(5.0d);
		 this.setVgap(5.0d);

		for (int i = 0; i < this.size; i++) {
			this.grids[i] = new Grid(this.gridSize);
		}

		ArrayList<String> collectionsList = new ArrayList<String>();
		for (String string : new String[] {
			"names",
			"years",
			"cities",
			"phones",
			"storms",
			"events",
		}) {
			collectionsList.add(string);
		}

		this.collections = new String[this.size][this.gridSize + 1];

		for (int i = 0; i < this.size; i++) {
			String collectionName = collectionsList.remove((int) Math.floor(Math.random() * collectionsList.size()));

			ArrayList<String> collectionsFromFile = new ArrayList<String>();
			for (String data : CSVReader.readFile("src/"+collectionName+".csv"))
			collectionsFromFile.add(data);

			collections[i][0] = collectionName;
			for (int j = 1; j < collections[i].length; j++) {
				collections[i][j] = collectionsFromFile.remove((int) Math.floor(Math.random() * collectionsFromFile.size()));
			}
		}

		this.add(this.grids[0], 1, 1);
		this.add(this.grids[1], 2, 1);
		this.add(this.grids[2], 1, 2);
	}


	/**
	 *
	 * @return the size of the board
	 */
	public int size() {
		return this.size;
	}

	/**
	 *
	 * @return size of each grid
	 */
	public int gridSize() {
		return this.gridSize;
	}

	/**
	 *
	 * @return the puzzle information
	 */
	public String[][] getCollections() {
		return this.collections;
	}

	/**
	 *
	 * @param index index of the grid (e.g 3x4: 0,1,2)
	 * @return
	 */
	public Grid getGrid(int index) {
		return this.grids[index];
	}
}
