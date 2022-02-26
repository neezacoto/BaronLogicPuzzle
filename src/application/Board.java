/**
 * @author: Arjun
 * 2/25/2022
 */

package application;
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
    private int height;

    //default constructor
    Board() {
		this.grids = new Grid[size];
        this.size = 3;
        this.size = 4;

		this.setHgap(5.0d);
		this.setVgap(5.0d);

		for (int i = 0; i < this.size; i++) {

			this.grids[i] = new Grid(this.height);

		}
        //the headers should be a border pane with a with the label on the side, and a v/hbox in the center
        //this.add(header[0], 0,1)
		this.add(this.grids[0], 1, 1);
		this.add(this.grids[1], 2, 1);
		this.add(this.grids[2], 1, 2);
        
	}

	// constructor
	Board(int size, int height) {

		this.grids = new Grid[size];
		this.size = size;
        this.height=height;

		 this.setHgap(5.0d);
		 this.setVgap(5.0d);

		for (int i = 0; i < this.size; i++) {

			this.grids[i] = new Grid(this.height);

		}
        
        //Christian
    

        String[] dogs = new String[] {"what","is","it","doing?"};
        this.add(new VHeader("Dog",dogs),0,1);
        this.add(new VHeader("Dog",dogs),0,2);
        this.add(new HHeader("Dog",dogs),1,0);
        this.add(new HHeader("Dog",dogs),2,0);
		this.add(this.grids[0], 1, 1);
		this.add(this.grids[1], 2, 1);
		this.add(this.grids[2], 1, 2);
        
	}
    /**
     * @author Christian
     * Responsible for creating the vertical headers for the board
     */
    private class VHeader extends BorderPane{

        VHeader(String category, String[] labels){
            super();
            Label subjects[] = new Label[labels.length]; //list of all labels to use
            Label cat = new Label(category);// creating label for category
            cat.setRotate(-90);
            

            VBox rows = new VBox(25);//rows to go down
            rows.setPadding(new Insets(0, 0, 0, 3));
        
            //adding labels to the subject list to populate the rows
            for(int i=0; i<subjects.length;i++){
                 Label entry = new Label(labels[i]);
                 subjects[i] = entry;
            }

            cat.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

            rows.getChildren().addAll(subjects);
            this.setCenter(rows);
            
            VBox header = new VBox(1);//header (left)
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
    private class HHeader extends BorderPane{

        HHeader(String category, String[] labels){
            super();
            Label subjects[] = new Label[labels.length]; //list of all labels to use
            Label cat = new Label(category);// creating label for category
            
            

            HBox cols = new HBox(25);//cols labels
            cols.setPadding(new Insets(0, 0, 0, 3));
        
            //adding labels to the subject list to populate the cols
            for(int i=0; i<subjects.length;i++){
                 Label entry = new Label(labels[i]);
                 entry.setMinHeight(50);
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

	public Grid getGrid(int index) {
		return this.grids[index];
	}
}