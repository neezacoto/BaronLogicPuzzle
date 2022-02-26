/**
 * @author Arjun
 * 2/25/2022
 */

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
		}) {
			collectionsList.add(string);
		}

		this.collections = new String[this.size][this.gridSize + 1];

		for (int i = 0; i < this.size; i++) {
			String collectionName = collectionsList.remove((int) Math.floor(Math.random() * collectionsList.size()));

			ArrayList<String> collectionsFromFile = new ArrayList<String>();
			for (String data : CSVReader.readFile("C:\\Users\\tuber\\Desktop\\CS-225-Assignment-2\\src\\"+collectionName+".csv"))
			collectionsFromFile.add(data);

			collections[i][0] = collectionName;
			for (int j = 1; j < collections[i].length; j++) {
				collections[i][j] = collectionsFromFile.remove((int) Math.floor(Math.random() * collectionsFromFile.size()));
			}
		}


		// Christian

		// this.add(new VHeader(collections[0]), 0, 1);
		// this.add(new VHeader(collections[1]), 0, 2);

		// this.add(new HHeader(collections[2]), 1, 0);
		// this.add(new HHeader(collections[1]), 2, 0);

		this.add(this.grids[0], 1, 1);
		this.add(this.grids[1], 2, 1);
		this.add(this.grids[2], 1, 2);
	}

	public String[][] getCollection(){
		return collections;
	}
		/**
		 * @author Christian
		 * Responsible for creating the vertical headers for the board
		 */
		private class VHeader extends BorderPane{

			VHeader(String[] labels) {
					super();
					Label subjects[] = new Label[labels.length - 1]; // ist of all labels to use
					Label cat = new Label(labels[0]); // creating label for category
					cat.setRotate(-90);


					VBox rows = new VBox(25); // rows to go down
					rows.setPadding(new Insets(0, 0, 0, 3));

					// adding labels to the subject list to populate the rows
					for(int i = 0; i < subjects.length; i++) {
							 Label entry = new Label(labels[i + 1]);
							 subjects[i] = entry;
					}

					cat.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

					rows.getChildren().addAll(subjects);
					this.setCenter(rows);

					VBox header = new VBox(1); // header (left)
					header.setStyle("-fx-background-color: black;");

					this.setLeft(header);
					header.getChildren().add(cat);

					this.setAlignment(rows, Pos.CENTER);
					this.setAlignment(cat, Pos.CENTER);
					rows.setAlignment(Pos.CENTER);
					header.setAlignment(Pos.CENTER);

			}

		}

		/**
		 * @author Christian
		 * Responsible for creating the horizontal headers for the board
		 */
		private class HHeader extends BorderPane {

			HHeader(String[] labels) {
					super();
					Label subjects[] = new Label[labels.length - 1]; // list of all labels to use
					Label cat = new Label(labels[0]); // creating label for category



					HBox cols = new HBox(25); // cols labels
					cols.setPadding(new Insets(0, 0, 0, 3));

					// adding labels to the subject list to populate the cols
					for(int i = 0; i < subjects.length; i++) {
							 Label entry = new Label(labels[i + 1]);
							 entry.setMinHeight(100);
							 entry.setRotate(-90);
							 subjects[i] = entry;
					}

					cat.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

					cols.getChildren().addAll(subjects);
					this.setCenter(cols);

					HBox header = new HBox(1);//header (top)
					header.setStyle("-fx-background-color: black;");

					this.setTop(header);
					header.getChildren().add(cat);

					this.setAlignment(cols, Pos.TOP_CENTER);
					this.setAlignment(cat, Pos.CENTER);
					cols.setAlignment(Pos.CENTER);
					header.setAlignment(Pos.CENTER);
			}
		}

	public int size() {
		return this.size;
	}

	public int gridSize() {
		return this.gridSize;
	}

	public String[][] getCollections() {
		return this.collections;
	}

	public Grid getGrid(int index) {
		return this.grids[index];
	}
}
