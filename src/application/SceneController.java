package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;

public class SceneController implements Initializable{
    @FXML
    private ChoiceBox<String> boardSizes;
    
    private String[] sizes = {"3x4"};

    @FXML
    private TextArea myCluesView;

    @FXML
    private Button startButton;

    @FXML
    private BorderPane boardArea;

    @FXML 
    private Board playerBoard;

    @FXML
    private Button hint;

    @FXML
    private BorderPane scenePane;

    String[] clues = {"There are jomba beans under 4th street","perhaps invest in a fridge","there are two animals that are egg","how many waters should you drink, yes!", "Survey says: what the dog doing?"};


    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {   
        if(boardSizes != null){
            boardSizes.getItems().addAll(sizes);
            boardSizes.setOnAction(this::setSize);
        }
            
        if(myCluesView != null){
            boardArea.setCenter(new Board(3,4));
            myCluesView.setText(clueCompile());
        }
    }
    
    public String clueCompile(){
        String cluesText = "\n";
        for(String clue: clues){
            cluesText += " • "+clue+"\n\n";
        }
        return cluesText;
    }

    public void switchToSceneMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MenuArea.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       }
       
    public void switchToSceneGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PlayArea.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Logic Game");
        stage.show();
       }

    public void setSize(ActionEvent event){
        //could be utilized in future iterations to change size
        String chosenSize = boardSizes.getValue(); 
        startButton.setDisable(false);
    }

    public void hint(ActionEvent event){

    }

    public void clearErrors(ActionEvent event){

    }

    public void startOver(ActionEvent event){

    }

    public void alert(ActionEvent event){

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Winner Winner Chicken Dinner ! ! ! ! ! ! !");
        alert.setHeaderText("Congradulations you've won!");
        alert.setContentText("You've completed the game (clicking 'ok' will close the game):");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("bruh");
            stage.close();

        }

        
    }

    

    
} 