package main.java.GUI;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import main.java.Game;
import main.java.Variables;
import sun.plugin.javascript.navig.Anchor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;

import javax.annotation.PostConstruct;
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
        Initial();


    }

    @FXML
    public void initialize() {

    }

    private   void Initial(){
        listTitle=new ArrayList<>();
        ObservableMap observableMap= FXCollections.observableMap(Variables.instantiate().things);
        listTitle.add(new Pair<String,Map>("Вещи",observableMap));
        observableMap=FXCollections.observableMap(Variables.instantiate().character);
        listTitle.add(new Pair<String,Map>("Характеристики",observableMap));
        observableMap=FXCollections.observableMap(Variables.instantiate().people);
        listTitle.add(new Pair<String,Map>("Люди",observableMap));
        observableMap=FXCollections.observableMap(Variables.instantiate().userVariable);
        listTitle.add(new Pair<String,Map>("Пользовательские",observableMap));

//        observableMap.addListener(new MapChangeListener() {
//            @Override
//            public void onChanged(Change change) {
//
//            }
//        });




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

    public void checkMemory(){
       // Variables.instantiate().Find("qwe");

       // Variables.instantiate().Find("qwe");
    }



}
