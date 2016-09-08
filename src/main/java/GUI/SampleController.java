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
import main.java.*;
import sun.plugin.javascript.navig.Anchor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.annotation.PostConstruct;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
    @FXML
    Button createBlockIf;

    CreateGui createGui;
    java.util.List<Pair<String, Map>> listTitle;
    Game game;
    private static final int howElemensSkip = 3;
    private static final int skipifContent=1;

    public SampleController() {
        Initial();
        //тестовое для пробы
        StructGame structGame = new StructGame();
        structGame.id = 1;
        ButtonGame buttonGame=new ButtonGame("Первая кнопка");
        buttonGame.whatHappend.add(new Pair<>("([TСтол]>5&[UПогода]=\"погода\")","[TСтол]+1;[UПогода]=\"Лето\""));
        buttonGame.whatHappend.add(new Pair<>("([TСтол]>5&[UПогода]=\"погода\")","[TСтол]+1;[UПогода]=\"Лето\";move1"));
        structGame.buttons.add(buttonGame);
        buttonGame=new ButtonGame("Вторая кнопка");
        structGame.buttons.add(buttonGame);
        game.structGames.add(structGame);
        //2
        structGame=new StructGame();
        structGame.id=2;
        buttonGame=new ButtonGame("2.1");
        buttonGame.whatHappend.add(new Pair<>("IfIfIf","[TСтол]-1"));
        structGame.buttons.add(buttonGame);
        buttonGame=new ButtonGame("2.2");
        buttonGame.whatHappend.add(new Pair<>("2.2","[TСтол]=3;[TСтул]=0"));
        structGame.buttons.add(buttonGame);
        game.structGames.add(structGame);


        //test
    }

    public void FillPage(){

    }


    public  void onLostFocus(){
        System.out.println("Focus lost");
    }

    @FXML
    public void initialize() {
        GuiInit();

        GoToPage();
    }

    public  void  FillButtonProperties(ButtonGame buttonGame){
        if(buttonGame==null){
            textButton.clear();
            numberButton.clear();
        }else {
            textButton.setText(buttonGame.text);
            numberButton.setText(String.valueOf(game.getCurrentPage().buttons.indexOf(buttonGame)+1));
        }
    }


//проверить :запустить этот метод после того, как уже будет ряд кнопок
    public  void  GoToPage(){//change Exists button
        List<ButtonGame> buttonGames=game.getCurrentPage().buttons;
    int size=pageContent.getChildren().size();


        System.out.println(buttonGames.size());
        if(buttonGames.size()+howElemensSkip<size) {
            pageContent.getChildren().remove(buttonGames.size() + howElemensSkip, size);
        }
        Button buttonBuf;
        for(int i=0; i<buttonGames.size();i++){
            if(pageContent.getChildren().size()-howElemensSkip-i>0){
                buttonBuf=(Button)pageContent.getChildren().get(i+howElemensSkip);
                buttonBuf.setText(buttonGames.get(i).text);
                if(buttonBuf.isFocused()){


                }
            }else{
                AddButtonChoice(buttonGames.get(i));
                //OnaddButtonChoice();


               // pageContent.getChildren().remove(i+howElemensSkip-1);
            }
        }
        FillButtonProperties(game.getCurrentPage().getSelectedButton());
        ReloadIfContent(game.getCurrentPage().getSelectedButton());

    }


    private  void AddButtonChoice(ButtonGame buttonGame){//createVisualOnly

        Button newButton;
        String name;
        if(buttonGame!=null){

            name=buttonGame.text;
        }else{
            name="New button";
            game.getCurrentPage().buttons.add(new ButtonGame(name));
        }
        newButton = createGui.CreateButtonOfChoice(pageContent,name);

        //!!!Альтернатива фокусу - нажатие, т.к фокус срабатывал при сворачивании
        newButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                int number = pageContent.getChildren().indexOf(newButton) - howElemensSkip;

                    ButtonGame buttonGame = game.getCurrentPage().getButton(number);//numbering from zero and exists first button
                    textButton.setText(buttonGame.text);
                    numberButton.setText(String.valueOf(number + 1));

                    ReloadIfContent(buttonGame);
            }
        });

 //       newButton.focusedProperty().addListener(new ChangeListener<Boolean>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//
//                if (newValue) {
//                    int number = pageContent.getChildren().indexOf(newButton) - howElemensSkip;
//
//                    ButtonGame buttonGame = game.getCurrentPage().getButton(number);//numbering from zero and exists first button
//                    textButton.setText(buttonGame.text);
//                    numberButton.setText(String.valueOf(number + 1));
//
//                    ReloadIfContent(buttonGame);
//
//                }
//            }
//        });
//


    }

    public boolean CheckIf(String s){
        return  true;
    }

    public void CheckAndSave(){
        ButtonGame buttonGame= game.getCurrentPage().getSelectedButton();
        Node item;
        String ifText;
        for(int i=0;i<iFContent.getChildren().size()-skipifContent;i++){
            item=iFContent.getChildren().get(i);
            if(item.getClass()==GridPane.class){
                 ifText=createGui.getIfTrxtComponent((GridPane)item);
                if(CheckIf(ifText)){

                }else {
                    System.out.println(String.format("Sintax at \"if\" %d ",i+1));
                }
            }
            ComboBox[] cb=createGui.GetComboRec((GridPane)item);
            if(cb[0].getEditor().getText().equals("")){
                System.out.println("Field must not be empty");
                continue;
            };
            if(Variables.instantiate().Find(cb[0].getEditor().getText()).size()>0){
                проверки на валидность if-then блоков
            }

        }


    }


    public void ReloadIfContent(ButtonGame buttonGame){



        if(buttonGame==null){
            buttonGame=game.getCurrentPage().getSelectedButton();
        }
        Node node;
        int buttonSize=buttonGame.whatHappend.size();
        int contentSize=iFContent.getChildren().size()-skipifContent;

        System.out.println("button "+buttonSize+" contentSize "+contentSize);
        int countAdd=0;
        if(contentSize-1-buttonSize>0){
            iFContent.getChildren().remove(buttonSize,contentSize);
            contentSize=iFContent.getChildren().size()-skipifContent;
        }else if(contentSize-1<buttonSize){ //Если нехватает блоков то создаём и сразу заполняем

            countAdd=buttonSize-(contentSize);
            for(int i=0; i<countAdd;i++){
              createGui.CreateIFBlock(iFContent,buttonGame.whatHappend.get(i+contentSize));
                //CreateBlockIf();
            }
        }       //а тут остаётся только переписать существующие блоки
       // for (int j = 0; j < contentSize-1; j++) {
            for (int i = 0; i < iFContent.getChildren().size() - 1-countAdd; i++) {
                node = iFContent.getChildren().get(i);
                if (node.getClass() == GridPane.class) {
                    createGui.setIfTrxtComponent((GridPane) node,buttonGame.whatHappend.get(i).getKey());
                    createGui.FillThenBlock((GridPane)node,buttonGame.whatHappend.get(i).getValue());

                }
            }
       // }
    }


    public void TestGoTOSecondPage(){
        game.getPage(1);
    }
    public  void TestGoTOFirstPage(){
        game.getPage(0);
    }

    public void OnaddButtonChoice() {

        AddButtonChoice(null);

    }



    private void GuiInit() {

        addButtonChoice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    createBlockIf.setDisable(true);

                   iFContent.getChildren().remove(0,iFContent.getChildren().size()-1);
                    textButton.clear();
                    numberButton.clear();
                    textButton.setDisable(true);
                    numberButton.setDisable(true);
                    iFContent.setDisable(true);
                }else{

                    createBlockIf.setDisable(false);
                    textButton.setDisable(false);
                    numberButton.setDisable(false);
                    iFContent.setDisable(false);
                }

            }
        });

        pageContent.prefWidthProperty().bind(scrollPane1.widthProperty());

        numberButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {

                    Integer num=ParseIf.tryParse(numberButton.getText());
                    if(num==null || num<1 || num>pageContent.getChildren().size()-howElemensSkip){
                        numberButton.setText(String.valueOf(game.getCurrentPage().getIndexSelectedButton()+1));
                    }else{
                        int oldNum=game.getCurrentPage().getIndexSelectedButton()+1;
                        if(num==oldNum){
                            return;
                        }
                        ObservableList<Node> workingCollection=FXCollections.observableArrayList( pageContent.getChildren());
                        List<ButtonGame> buttonGames=game.getCurrentPage().buttons;
                        //???
                        if(oldNum-num<0){
                            for(int i=oldNum-1;i<num-1;i++){
                                Collections.swap(workingCollection,i+howElemensSkip,i+howElemensSkip+1);
                                Collections.swap(buttonGames,i,i+1);

                            }
                        }else{
                            for(int i=oldNum-1; i> num-1;i--){
                                Collections.swap(workingCollection,i+howElemensSkip,i+howElemensSkip-1);
                                Collections.swap(buttonGames,i,i-1);
                            }
                        }


                        pageContent.getChildren().setAll(workingCollection);
                        game.getCurrentPage().buttons=buttonGames;
                        game.getCurrentPage().setIndexSelectButton(num-1);
                        System.out.println("index "+game.getCurrentPage().getIndexSelectedButton());
                    }

                }
            }
        });


        textButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    int index = game.getCurrentPage().getIndexSelectedButton();
                    game.getCurrentPage().getSelectedButton().text = textButton.getText();
                    ObservableList<Node> observableList = pageContent.getChildren();
                    ((Button) pageContent.getChildren().get(index + howElemensSkip)).setText(textButton.getText());
                }
            }
        });
        iFContent.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(oldValue){
                    onLostFocus();
                }

            }
        });
    }

    private void Initial() {
        listTitle = new ArrayList<>();
        ObservableMap observableMap = FXCollections.observableMap(Variables.instantiate().things);
        listTitle.add(new Pair<String, Map>("Вещи", observableMap));
        observableMap = FXCollections.observableMap(Variables.instantiate().character);
        listTitle.add(new Pair<String, Map>("Характеристики", observableMap));
        observableMap = FXCollections.observableMap(Variables.instantiate().people);
        listTitle.add(new Pair<String, Map>("Люди", observableMap));
        observableMap = FXCollections.observableMap(Variables.instantiate().userVariable);
        listTitle.add(new Pair<String, Map>("Пользовательские", observableMap));


        createGui = new CreateGui();
        game = new Game();
        game.addPageChangeListener(new pageChangedListener() {
            @Override
            public void OnPageChanged(PageChangeEvent e) {
                    if(e.getLastvalue()!=e.getNewValue()){
                        GoToPage();
                    }
            }
        });

//        observableMap.addListener(new MapChangeListener() {
//            @Override
//            public void onChanged(Change change) {
//
//            }
//        });


    }


    @FXML
    public void CreateBlockIf() {
        createGui.CreateIFBlock(iFContent, null);
        //iFContent.getChildren().add();
    }

    public void FillVariable() {
        createGui.TestCreateContentAccordion(listVariable, listTitle);
    }

    public void onTextChange() {
        System.out.println("text changed");

    }

    public void checkMemory() {
        // Variables.instantiate().Find("qwe");

        // Variables.instantiate().Find("qwe");
    }


}
