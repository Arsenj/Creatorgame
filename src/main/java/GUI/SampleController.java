package main.java.GUI;

import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import main.java.ButtonGame;
import main.java.Game;
import main.java.StructGame;
import main.java.Variables;
import sun.plugin.javascript.navig.Anchor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    @FXML
    javafx.scene.control.ScrollPane scrollPane1;
    @FXML
    VBox pageContent;
    @FXML
    Button addButtonChoice;
    @FXML
    TextField textButton;
    @FXML
    TextField numberButton;

    CreateGui createGui;
    java.util.List<Pair<String,Map>> listTitle;
    Game game;
    private static final int howElemensSkip=3;


    public SampleController(){
        Initial();
        //тестовое для пробы
StructGame structGame=new StructGame();
        structGame.id=1;

        game.structGames.add(new StructGame());
        //test
    }



    @FXML
    public void initialize() {
        GuiInit();
    }


    public  void changeNumberButton(){

    }

    public  void OnaddButtonChoice(){

        Button newButton= createGui.CreateButtonOfChoice(pageContent,"test");

        game.getCurrentPage().buttons.add(new ButtonGame(newButton.getText()));
        newButton.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    int number=pageContent.getChildren().indexOf(newButton)-howElemensSkip;

                  ButtonGame buttonGame=game.getCurrentPage().getButton(number);//numbering from zero and exists first button
                    textButton.setText(buttonGame.text);
                    numberButton.setText(String.valueOf(number+1));
                }
            }
        });

//        StructGame structGame= game.getCurrentPage();
//        structGame.buttons.add(new ButtonGame(newButton.getText()));



    }

    private void GuiInit(){
        pageContent.prefWidthProperty().bind(scrollPane1.widthProperty());

        textButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.ENTER){
                int index=game.getCurrentPage().getIndexSelectedButton();
                    game.getCurrentPage().getSelectedButton().text=textButton.getText();
                    ObservableList<Node> observableList=pageContent.getChildren();
                    ((Button)pageContent.getChildren().get(index+howElemensSkip)).setText(textButton.getText());
                }
            }
        });
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


        createGui=new CreateGui();
        game=new Game();
//        observableMap.addListener(new MapChangeListener() {
//            @Override
//            public void onChanged(Change change) {
//
//            }
//        });




    }


@FXML
  public   void CreateBlockIf(){
    createGui.CreateIFBlock(iFContent,null);
            //iFContent.getChildren().add();
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
