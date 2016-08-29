package main.java.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import main.java.Game;
import sun.plugin.javascript.navig.Anchor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;

/**
 * Created by arsen on 23.08.2016.
 */
public class SampleController {
    @FXML
    AnchorPane anchor1;
    @FXML
    GridPane globGrid;
    @FXML
    VBox iFContent;
    @FXML
    Accordion listVariable;
    CreateGui createGui;


    java.util.List<Pair<String,Map>> listTitle;

    public SampleController(){
        createGui=new CreateGui();
    }


@FXML
  public   void CreateBlockIf(){
            iFContent.getChildren().add(createGui.CreateIFBlock(null));
    }

    public  void FillVariable(){
            createGui.TestCreateContentAccordion(listVariable,listTitle);
    }

    public  void onTextChange(){
        System.out.println("text changed");
    }




}
