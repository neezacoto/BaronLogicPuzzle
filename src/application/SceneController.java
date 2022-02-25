package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable{
    @FXML
    private ChoiceBox<String> boardSizes;

    private String[] sizes = {"3x4"};

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {   
        if(boardSizes != null){
            boardSizes.getItems().addAll(sizes);
        }
            
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
        stage.show();
       }

    

    
} 