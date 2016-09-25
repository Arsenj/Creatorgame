package main.java.GUI;


import com.sun.webkit.*;
import javafx.geometry.Point2D;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.Pair;
import main.java.*;
import main.java.File;
import sun.awt.windows.ThemeReader;
import sun.plugin.javascript.navig.Anchor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sun.reflect.generics.tree.Tree;
import sun.security.ssl.Debug;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;


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
    @FXML
    Button save;
    @FXML
    TextArea mainText;
    @FXML
    TreeItem rootPageTree;
    @FXML
    TreeView parentTreeView;
    @FXML
    Label comment1;
    @FXML
    TextField comment;


    Popup popup;


    CreateGui createGui;
    java.util.List<Pair<String, Map>> listTitle;
    Game game;
    Settings settings;


    private static final int howElemensSkip = 4;
    private static final int skipifContent = 1;

    public SampleController() {


        InitialVar();

        /*Variables.instantiate().variable.add(new Triple("Стол", "1", Variables.categories.things));
        Variables.instantiate().variable.add(new Triple("Стул", "1", Variables.categories.things));
        Variables.instantiate().variable.add(new Triple("Палка", "1", Variables.categories.things));
        Variables.instantiate().variable.add(new Triple("Камень", "1", Variables.categories.things));
        Variables.instantiate().variable.add(new Triple("Деньги", "15", Variables.categories.things));
        Variables.instantiate().variable.add(new Triple("Деньги", "15", Variables.categories.things));
        Variables.instantiate().variable.add(new Triple("Иван", "15", Variables.categories.people));
        Variables.instantiate().variable.add(new Triple("Погода", "\"Зима\"", Variables.categories.userVariable));*/


    }

    public void TestTreeView() {
        createGui.AddPageToTreeView(rootPageTree, game, game.getCurrentPage());
    }


    public void FillTree() {
        //тестовое для пробы
        /*
        StructGame structGame = new StructGame();
        structGame.id = 1;
        ButtonGame buttonGame = new ButtonGame("Первая кнопка");


        System.out.println(Variables.instantiate().variable.size());
        List<Then> thenList = new ArrayList<>();
        thenList.add(new Then("Стол", "+", "1"));
        thenList.add(new Then("Погода", "=", "\"погода\""));
        buttonGame.whatHappend.add(new IfThen("([Стол]>5&[Погода]=\"погода\")", thenList));

        //new Pair<>("([TСтол]>5&[UПогода]=\"погода\")","[TСтол]+1;[UПогода]=\"Лето\""));
        //  buttonGame.whatHappend.add(new Pair<>("([TСтол]>5&[UПогода]=\"погода\")","[TСтол]+1;[UПогода]=\"Лето\";move1"));
        structGame.buttons.add(buttonGame);
        buttonGame = new ButtonGame("Вторая кнопка");
        structGame.buttons.add(buttonGame);
        game.AddNewPage(structGame);
        //game.structGames.put(1,structGame);
        //2
        structGame = new StructGame();
        structGame.id = 2;
        buttonGame = new ButtonGame("2.1");
        thenList = new ArrayList<>();
        thenList.add(new Then("Стол", "-", "1"));
        buttonGame.whatHappend.add(new IfThen("IfIfIf", thenList));


        structGame.buttons.add(buttonGame);
        buttonGame = new ButtonGame("2.2");
        thenList = new ArrayList<>();
        thenList.add(new Then("Стол", "=", "3"));
        thenList.add(new Then("Стул", "=", "0"));
        buttonGame.whatHappend.add(new IfThen("2.2", thenList));

        structGame.buttons.add(buttonGame);
        game.AddNewPage(structGame);*/
        //game.structGames.put(null,structGame);


        //test
    }

    public void onLostFocus() {
        System.out.println("Focus lost");
    }

    @FXML
    public void initialize() {


        GuiInit();
        // GoToPage();
    }

    public void FillButtonProperties(ButtonGame buttonGame) {
        if (buttonGame == null) {
            textButton.clear();
            numberButton.clear();
            //comment.clear();
        } else {

            textButton.setText(buttonGame.text);
            numberButton.setText(String.valueOf(game.getCurrentPage().buttons.indexOf(buttonGame) + 1));
        }
    }


    //проверить :запустить этот метод после того, как уже будет ряд кнопок
    public void GoToPage() {//change Exists button
        List<ButtonGame> buttonGames = game.getCurrentPage().buttons;
        int size = pageContent.getChildren().size();



        if (buttonGames.size() + howElemensSkip < size) {
            pageContent.getChildren().remove(buttonGames.size() + howElemensSkip, size);
        }
        Button buttonBuf;
        for (int i = 0; i < buttonGames.size(); i++) {
            if (pageContent.getChildren().size() - howElemensSkip - i > 0) {
                buttonBuf = (Button) pageContent.getChildren().get(i + howElemensSkip);
                buttonBuf.setText(buttonGames.get(i).text);
                if (buttonBuf.isFocused()) {


                }
            } else {
                AddButtonChoice(buttonGames.get(i));
                //OnaddButtonChoice();


                // pageContent.getChildren().remove(i+howElemensSkip-1);
            }
        }
        FillButtonProperties(game.getCurrentPage().getSelectedButton());
        ReloadIfContent(game.getCurrentPage().getSelectedButton());

        int id=game.getCurrentPage().id;
        String commSet=id+")"+ game.getCurrentPage().comment;
        comment.setText(game.getCurrentPage().comment);
        comment1.setText(commSet);

       /* Integer index=game.GetSerialNumberPage(game.getCurrentPage().id);
        if(index!=null){
            ((TreeItem)rootPageTree.getChildren().get(index)).setValue(commSet);
        }else{
            System.out.println("TreeItem not found");
        }*/
       // протестировать добавление сcoment-a


    }


    private void AddButtonChoice(ButtonGame buttonGame) {//createVisualOnly

        Button newButton;
        String name;
        if (buttonGame != null) {

            name = buttonGame.text;

        } else {
            name = "New button";
            buttonGame = new ButtonGame(name);
            game.getCurrentPage().buttons.add(buttonGame);
        }

        newButton = new Button(name);

        pageContent.getChildren().add(newButton);



                /*Код ниже не присоединён у Button нет никакого menue а ВСЁ переделывать на J, это ...*/
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItem = new MenuItem("Добавить");
        menuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                AddButtonChoice(new ButtonGame(null));
            }
        });

        menuItem = new MenuItem("Удалить");
        menuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {


            }
        });
                    /*Конец бредокода*/


        //  newButton = createGui.CreateButtonOfChoice(pageContent, name,howElemensSkip);


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

    private void DestroyGame() {
        Variables.instantiate().Reset();
        for (TitledPane item : listVariable.getPanes()) {
            VBox vBox = (VBox) ((ScrollPane) item.getContent()).getContent();
            vBox.getChildren().remove(0, vBox.getChildren().size() - 1);
        }
        game.Refresh();
        //game=new Game();

    }

    public void NewGame() {
        DestroyGame();
        listVariable.setDisable(false);

    }

    public void AddPage() {
        game.AddNewPage(null);

    }

    public void OpenGame() {

        Frame frame = new Frame();
        FileDialog fileDialog = new FileDialog(frame);
        //fileDialog.setFile("*.txtG");
        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(java.io.File dir, String name) {
                return name.endsWith(".txtG");
            }
        });
        fileDialog.setVisible(true);
        String fileName = fileDialog.getFile();
        if (fileName != null) {
            DestroyGame();

            File file = new File();
            settings = file.<Settings>Read(fileName);
            if (settings != null) {
                boolean load = Variables.Load(file, settings.getVariableFileName());
                if (load) {
                    load = game.Load(file, settings.getStrucrGameFileName());
                    if (load) {
                        System.out.println("Game loaded");
                        LockAll(false);
                        return;

                    } else {
                        System.out.println("Game loaded");
                    }

                } else {

                    System.out.println("StrucrGame not load");
                }
            } else {
                System.out.println("Settings not load");

            }
        }
        LockAll(false);
    }

    public void SaveGame() {

    }

    void LockAll(boolean lock) {
        addButtonChoice.setDisable(lock);
        parentTreeView.setDisable(lock);
        createBlockIf.setDisable(lock);
        this.save.setDisable(lock);
        this.iFContent.setDisable(lock);
        this.anchor1.setDisable(lock);
        listVariable.setDisable(lock);
    }

    public void SaveAsGame() {
        Frame frame = new Frame();
        FileDialog fileDialog = new FileDialog(frame);

        //fileDialog.setFile("*.txtG");
        fileDialog.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(java.io.File dir, String name) {
                return name.endsWith(".txtG");
            }
        });
        fileDialog.setVisible(true);
        fileDialog.setFile(fileDialog.getName());
        settings.setNameGame(fileDialog.getName());
        File file = new File();
        file.<Settings>Write(settings, settings.getNameGame());
        Variables.Save(file, settings.getVariableFileName());
        game.Save(file, settings.getStrucrGameFileName());
    }

    public void ExitGame() {

    }

    public boolean CheckIf(String s) {
        return true;
    }

    public void CheckAndSave() {


        ButtonGame buttonGame = game.getCurrentPage().getSelectedButton();
        Node gridIf;

        String ifText = "";
        boolean ok = true;
        List<IfThen> ifThens = new ArrayList<>();
        List<Then> thenList = null;

        for (int i = 0; i < iFContent.getChildren().size() - skipifContent; i++) {//Все if-ы
            gridIf = iFContent.getChildren().get(i);
            if (gridIf.getClass() == GridPane.class) {
                ifText = createGui.getIfTrxtComponent((GridPane) gridIf);
                if (CheckIf(ifText)) {

                } else {
                    System.out.println(String.format("Sintax at \"if\" %d ", i + 1));
                    ok = false;
                    continue;
                }
            }

            for (Node vBox : ((GridPane) gridIf).getChildren()) { //get combos
                if (vBox.getClass() != VBox.class) {
                    continue;
                }

                Node gridCombo;
                ComboBox[] cb;
                Then then;
                //   int count=((VBox)vBox).getChildren().size();
                thenList = new ArrayList<>();
                for (int j = 0; j < ((VBox) vBox).getChildren().size() - 1; j++) {
                    gridCombo = ((VBox) vBox).getChildren().get(j);
                    if (gridCombo.getClass() == GridPane.class) {
                        cb = createGui.GetComboRec((GridPane) gridCombo);

                        if (cb[0].getEditor().getText().equals("")) {

                            System.out.println("Field must not be empty");
                            ok = false;
                            continue;
                        }

                        if (Variables.instantiate().Find(cb[0].getEditor().getText()).size() == 0) {
                            System.out.println("UserMessage: variable " + cb[0].getEditor().getText() + " not found");
                            ok = false;
                            continue;
                        }


                    } else {
                        continue;
                    }
                    if (ok) {
                        Triple triple = (Triple) cb[0].getValue();
                        System.out.println(triple.toString());
                        thenList.add(new Then(triple, (String) cb[1].getValue(), (Triple) cb[2].getValue()));
                    }
                    // System.out.println(String.format("Ok save %s %s %s",then.getVariable1().getKey(),then.getOperator(),then.getVariable2().getKey()));
                }
            }


            if (ok) {
                ifThens.add(new IfThen(ifText, thenList));
            }
            //game.getCurrentPage().getSelectedButton().whatHappend.add()

        }
        if (ok) {
            System.out.println("User message: All saved");
            game.getCurrentPage().getSelectedButton().whatHappend.clear();
            game.getCurrentPage().getSelectedButton().whatHappend.addAll(ifThens);
            ;
        }


    }


    public void ReloadIfContent(ButtonGame buttonGame) {


        if (buttonGame == null) {
            buttonGame = game.getCurrentPage().getSelectedButton();
        }

        int contentSize = iFContent.getChildren().size() - skipifContent;
        if (buttonGame == null) {
            iFContent.getChildren().remove(0, contentSize);
            return;
        }

        Node node;
        int buttonSize = buttonGame.whatHappend.size();

        int countAdd = 0;
        if (contentSize - 1 - buttonSize >= 0) {
            iFContent.getChildren().remove(buttonSize, contentSize);
            contentSize = iFContent.getChildren().size() - skipifContent;
        } else if (contentSize - 1 < buttonSize) { //Если нехватает блоков то создаём и сразу заполняем

            countAdd = buttonSize - (contentSize);
            for (int i = 0; i < countAdd; i++) {
                createGui.CreateIFBlock(iFContent, buttonGame.whatHappend.get(i + contentSize));
                //CreateBlockIf();
            }
        }       //а тут остаётся только переписать существующие блоки
        // for (int j = 0; j < contentSize-1; j++) {
        for (int i = 0; i < iFContent.getChildren().size() - 1 - countAdd; i++) {
            node = iFContent.getChildren().get(i);
            if (node.getClass() == GridPane.class) {
                int sizewhatHappened = buttonGame.whatHappend.size();
                createGui.setIfTrxtComponent((GridPane) node, buttonGame.whatHappend.get(i).getIfString());
                createGui.FillThenBlock((GridPane) node, buttonGame.whatHappend.get(i).getThenList());

            }
        }
        // }
    }


    public void TestGoTOSecondPage() {
        game.getPageById(2);
    }

    public void TestGoTOFirstPage() {
        game.getPageById(1);
    }

    public void OnaddButtonChoice() {

        AddButtonChoice(null);

    }


    public void SaveText() {
        game.getCurrentPage().text = mainText.getText();
    }


    public Popup CreatePopup(javafx.scene.control.TextArea parent) {


        return popup;
    }

    private Integer GetIdByValueTreeItem(String valueItem) {

        if (valueItem != null) {
            int pos = valueItem.indexOf(')');
            if (pos > 0) {
                Integer parsId = Variables.tryParse(valueItem.substring(0, pos));
                //Integer id=(Integer) myTreeCell.getUserData();
                if (parsId != null) {
                    return parsId;
                }
            }
        }
        return null;
    }


    //private  void RemoveItemTree

    private void ButtonGoToPage(MyTreeCell myTreeCell){
        Integer parsId = GetIdByValueTreeItem((String) myTreeCell.getItem());
        System.out.println("Current:"+game.getCurrentPage().id+" GoTo:"+parsId);
        if (parsId != null) {
            StructGame structGame = game.getPageById(parsId);
        }
    }

    private void GuiInit() {
        LockAll(true);

      /*  ContextMenu contextMenu=new ContextMenu();
        MenuItem menuItem=new MenuItem("add");
        menuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                System.out.println(parentTreeView.getSelectionModel().getSelectedItem());
            }
        });
        contextMenu.getItems().add(menuItem);
        parentTreeView.setContextMenu(contextMenu);
*/

      /*  parentTreeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    Node node = event.getPickResult().getIntersectedNode();
                    System.out.println(node);
                    if (rootPageTree.getChildren().contains(node)) {
                        System.out.println("first if");
                    }

                }
            }
        });*/
        createGui.CreateEmptyContentAccordion(listVariable);
        parentTreeView.setCellFactory(new Callback<TreeView, TreeCell>() {
            @Override
            public TreeCell call(TreeView param) {

                ContextMenu contextMenu = new ContextMenu();
                MyTreeCell myTreeCell = new MyTreeCell(contextMenu);

                myTreeCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.getButton()==MouseButton.PRIMARY && event.getClickCount()>=2){
                            ButtonGoToPage(myTreeCell);
                        }

                    }
                });
                //может лучше через обсерв?
                //как прри ножатии на меню понять к какой странице относится?
                MenuItem menuItem = new MenuItem("Перейти");
                contextMenu.getItems().add(menuItem);
                menuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        ButtonGoToPage(myTreeCell);
                    }
                });
                menuItem = new MenuItem("Создать");
                contextMenu.getItems().add(menuItem);
                menuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        game.AddNewPage(null);
                    }
                });
                menuItem = new MenuItem("Удалить");
                contextMenu.getItems().addAll(menuItem);
                menuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        Integer parseId = GetIdByValueTreeItem((String) myTreeCell.getItem());
                        if (parseId != null) {
                            game.DeletePageById(parseId);
                        }
                    }
                });

                System.out.println(myTreeCell.getText());
                return myTreeCell;
            }
        });


      /*  rootPageTree.getChildren().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                System.out.println("addChild");


            }
        });*/


        popup = new Popup();
        javafx.scene.control.ScrollPane pane = new javafx.scene.control.ScrollPane();
        popup.getContent().add(pane);
        ListView listView = new ListView();
        pane.setContent(listView);
        listView.setPrefWidth(100);
        listView.setPrefHeight(100);


        listView.setCellFactory(new Callback<ListView<Triple>, ListCell<String>>() {
            @Override
            public ListCell call(ListView<Triple> param) {
                // System.out.println("size: " + param.getItems().size());

                return new ListCell<Triple>() {
                    @Override
                    protected void updateItem(Triple item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getKey());
                        }
                    }
                };
            }
        });


        listView.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (!event.getCode().isArrowKey() && !event.getCode().isFunctionKey() && !event.getCode().isNavigationKey()) {
                    int startIndex = mainText.getText().lastIndexOf('[', mainText.getCaretPosition()) + 1;
                    if (startIndex > 0) {

                        List<Triple> res = Variables.instantiate().Find(mainText.getText().substring(startIndex, mainText.getCaretPosition()));
                        if (res != null && !listView.getItems().equals(res)) {
                            listView.getItems().clear();
                            listView.getItems().addAll(res);
                        }
                    }

                }

                if (event.getCode() == KeyCode.ENTER) {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        String setStr = ((Triple) listView.getSelectionModel().getSelectedItem()).getKey();
                        //System.out.println("start " + start + " finish " + finish);
                        int index = mainText.getText().lastIndexOf('[', mainText.getCaretPosition());
                        mainText.deleteText(index, mainText.getCaretPosition());

                        /*if (mainText.getCaretPosition() > 0 && mainText.getText().charAt(mainText.getCaretPosition() - 1) == '[') {
                            mainText.deletePreviousChar();
                        }*/
                        mainText.insertText(mainText.getCaretPosition(), "[" + setStr + "]");
                        popup.hide();
                    }
                }
                if (event.getCode() == KeyCode.ESCAPE) {
                    popup.hide();
                }
            }

        });


        mainText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().equals("[")) {
                    mainText.setUserData(mainText.getCaretPosition());

                    Point2D p = mainText.localToScene(0.0, 0.0);
                    double x = p.getX() + mainText.getScene().getX() + mainText.getScene().getWindow().getX();
                    double y = p.getY() + mainText.getScene().getY() + mainText.getScene().getWindow().getY();
                    x += mainText.getWidth();
                    listView.getItems().clear();
                    listView.getItems().addAll(Variables.instantiate().variable);
                    popup.show(mainText, x, y);
                }
              /*  Integer pos = (Integer) mainText.getUserData();
                if (pos != null) {
                    System.out.println("POS: " + pos + " " + mainText.getCaretPosition());
                    if (pos < mainText.getCaretPosition()) {
                        System.out.println("sub " + mainText.getText().substring(pos, mainText.getCaretPosition()));
                        listView.getItems().clear();
                        listView.getItems().addAll(
                                Variables.instantiate().Find(mainText.getText().substring(pos, mainText.getCaretPosition())));
                    }
                }*/

                if (event.getText().equals("]")) {
                    popup.hide();
                    mainText.setUserData(null);

                }
            }
        });
            /*@Override
            public void hendler(mainTextEvent e) {

            }

        });*/



       /* mainText.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getText().equals("[")) {


                    }
                    if (event.getText().equals("]") || event.getCode()==KeyCode.ESCAPE) {
                        popup.hide();
                    }
                }
            });*/
        mainText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    popup.hide();
                }
            }
        });


        //  код выше не работает. хз


        //listView.getItems().addAll(new Object[]{"1","2","3","4","5"});


        //?????????????????????
        addButtonChoice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    createBlockIf.setDisable(true);

                    iFContent.getChildren().remove(0, iFContent.getChildren().size() - 1);
                    textButton.clear();
                    numberButton.clear();
                    textButton.setDisable(true);
                    numberButton.setDisable(true);
                    iFContent.setDisable(true);
                } else {

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
                    if (game.getCurrentPage().getIndexSelectedButton() != -1) {
                        Integer num = ParseIf.tryParse(numberButton.getText());
                        if (num == null || num <= 0 || num > pageContent.getChildren().size() - howElemensSkip) {
                            if (num == 0) {
                                int number = game.getCurrentPage().getIndexSelectedButton();

                                pageContent.getChildren().remove(number + howElemensSkip);
                                game.getCurrentPage().buttons.remove(number);//numbering from zero and exists first button
                                //if(game.getCurrentPage().getIndexSelectedButton()==number){
                                numberButton.clear();
                                textButton.clear();
                                game.getCurrentPage().setIndexSelectButton(-1);
                                //}


                            }
                            numberButton.setText(String.valueOf(game.getCurrentPage().getIndexSelectedButton() + 1));
                        } else {
                            int oldNum = game.getCurrentPage().getIndexSelectedButton() + 1;
                            if (num == oldNum) {
                                return;
                            }
                            ObservableList<Node> workingCollection = FXCollections.observableArrayList(pageContent.getChildren());
                            List<ButtonGame> buttonGames = game.getCurrentPage().buttons;
                            //???
                            if (oldNum - num < 0) {
                                for (int i = oldNum - 1; i < num - 1; i++) {
                                    Collections.swap(workingCollection, i + howElemensSkip, i + howElemensSkip + 1);
                                    Collections.swap(buttonGames, i, i + 1);

                                }
                            } else {
                                for (int i = oldNum - 1; i > num - 1; i--) {
                                    Collections.swap(workingCollection, i + howElemensSkip, i + howElemensSkip - 1);
                                    Collections.swap(buttonGames, i, i - 1);
                                }
                            }


                            pageContent.getChildren().setAll(workingCollection);
                            game.getCurrentPage().buttons = buttonGames;
                            game.getCurrentPage().setIndexSelectButton(num - 1);
                            System.out.println("index " + game.getCurrentPage().getIndexSelectedButton());
                        }
                    }

                }
            }
        });



        textButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    int index = game.getCurrentPage().getIndexSelectedButton();
                    if (game.getCurrentPage().getIndexSelectedButton() != -1) {
                        game.getCurrentPage().getSelectedButton().text = textButton.getText();
                        ObservableList<Node> observableList = pageContent.getChildren();
                        ((Button) pageContent.getChildren().get(index + howElemensSkip)).setText(textButton.getText());
                    }
                }
            }
        });

        comment.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    StructGame structGame= game.getCurrentPage();
                    if(structGame!=null){
                        structGame.comment=comment.getText();

                        String commSet=structGame.id+") "+structGame.comment;

                        Integer index = game.GetSerialNumberPage(game.getCurrentPage().id);
                        if (index != null) {
                            ((TreeItem) rootPageTree.getChildren().get(index)).setValue(commSet);
                        } else {
                            System.out.println("TreeItem not found");
                        }

                        comment1.setText(commSet);

                    }
                }
            }
        });

        iFContent.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    onLostFocus();
                }

            }
        });
    }

    public void TestRemovePageId1() {
        game.RemovePage(1);
    }

    private void InitialVar() {
        //  listTitle = new ArrayList<>();
        //ObservableList<Triple> observableMap = FXCollections.observableList(Variables.instantiate().variable);

//        listTitle.add(new Pair<String, Map>("Вещи", observableMap));
//        observableMap = FXCollections.observableMap(Variables.instantiate().character);
//        listTitle.add(new Pair<String, Map>("Характеристики", observableMap));
//        observableMap = FXCollections.observableMap(Variables.instantiate().people);
//        listTitle.add(new Pair<String, Map>("Люди", observableMap));
//        observableMap = FXCollections.observableMap(Variables.instantiate().userVariable);
//        listTitle.add(new Pair<String, Map>("Пользовательские", observableMap));


        createGui = new CreateGui();
        game = new Game();

        game.addStructGameChangedListener(new structGameChangedListener() {
            @Override
            public void call(structGameChangedEvent e) {
                if (e.getEvent() == structGameChangedEvent.event.Add) {
                    TreeItem item = new TreeItem(e.getStructGame().id + ") " + e.getStructGame().comment);

                    rootPageTree.getChildren().add(item);
                    int num = game.getCountPage();
                    if (num == 1) {
                        LockAll(false);
                    }
                    if(num==0){
                        LockAll(true);
                    }


                } else if (e.getEvent() == structGameChangedEvent.event.Delete) {
                    rootPageTree.getChildren().removeIf(new Predicate() {
                        @Override
                        public boolean test(Object o) {
                            String s = (String) ((TreeItem) o).getValue();

                            if (e.getStructGame().id == GetIdByValueTreeItem(s)) {
                                return true;
                            }

                            return false;
                        }
                    });
                 //   rootPageTree.setValue(String.format("Всего страниц %n", game.getCountPage()));
                }
                GoToPage();
                rootPageTree.setValue(String.format("Всего страниц %s", game.getCountPage()));
            }
        });


        game.addPageChangeListener(new pageChangedListener() {
            @Override
            public void OnPageChanged(PageChangeEvent e) {
                if (e.getLastvalue() != e.getNewValue()) {
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
        createGui.CreateFillContentAccordion(listVariable);
    }

    public void onTextChange() {
        System.out.println("text changed");

    }

    public void checkMemory() {
        // Variables.instantiate().Find("qwe");

        // Variables.instantiate().Find("qwe");
    }


}
