package main.java.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sun.plugin.javascript.navig.Anchor;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by arsen on 23.08.2016.
 */
public class SampleController {
    @FXML
    AnchorPane anchor1;
    @FXML
    GridPane globGrid;
    @FXML
    AnchorPane iFContent;


    void CreateBlockIf(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main/java/GUI/IfBlock.fxml"));
        fxmlLoader.setRoot(iFContent);
      //  fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        }catch (IOException e){
System.out.println("IfBlock.fxml Not loaded");
        }
    }

    @FXML protected void handleExitButtonAction(ActionEvent event){
        CreateBlockIf();
    }
}
