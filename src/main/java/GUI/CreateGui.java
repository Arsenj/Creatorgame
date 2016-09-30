package main.java.GUI;

import java.awt.ScrollPane;
import java.awt.event.*;


import com.sun.org.apache.xml.internal.utils.Trie;
import com.sun.org.apache.xpath.internal.operations.Gt;
import com.sun.org.apache.xpath.internal.operations.Variable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.util.StringConverter;
import main.java.*;
import org.omg.CORBA.Object;
import sun.nio.cs.CharsetMapping;
import sun.security.ssl.Debug;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.TextArea;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by arsen on 25.08.2016.
 */

public class CreateGui {

    private int SkipifContent = 1;

    public void removeAtVariable(VBox vBox, java.lang.Object removeObj) {
        vBox.getChildren().remove(removeObj);

    }

    String CheckNameVariable(String NameNew, String oldName) {
        if (oldName == null || oldName.equals("")) {
            return null;
        }
        if ((NameNew == null || NameNew.equals(""))) {
            //
            return "Переменная не может быть пустая";
        }
        String symbols = "[] \"\"";
        boolean findSymbols = false;
        for (int i = 0; i < symbols.length(); i++) {
            if (NameNew.indexOf(symbols.charAt(i)) > 0) {
                findSymbols = true;
                break;
            }
        }
        if (findSymbols) {
            return "Недопустимый символ";
        }
        return null;
    }


    public void RecreateTreeView(TreeItem parent, Map.Entry<Integer, StructGame>[] list) {
        parent.getChildren().clear();

        for (int i = list.length - 1; i >= 0; i--) {
            AddPageToTreeView(parent, list[i].getValue());
        }
    }

    public void ClearIFContent(VBox ifContent, int skipifContent) {
        if (ifContent.getChildren().size() > skipifContent) {
            ifContent.getChildren().remove(0, ifContent.getChildren().size()-skipifContent);
        }

    }

    public void AddPageToTreeView(TreeItem parent, StructGame structGame) {


        TreeItem item = new TreeItem(structGame.id + ") " + structGame.comment);

        parent.getChildren().add(item);


    }

    public void AddNewEmptyVariable(VBox vBox, String key, String value) {
        GridPane gp;
        TextField textField1, textField2;
        gp = new GridPane();
        gp.setPadding(new Insets(0, 0, 3, 5));
        gp.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints(), new ColumnConstraints());
        textField1 = new TextField(key);
        textField1.setAlignment(Pos.TOP_LEFT);
        gp.add(textField1, 0, 0);
        textField1.setPrefWidth(60);
        textField2 = new TextField(value);
        textField2.setAlignment(Pos.TOP_LEFT);
        textField2.setPrefWidth(60);
        gp.add(textField2, 1, 0);
        Button button = new Button();
        button.setFocusTraversable(false);
        gp.add(button, 2, 0);
        double opacity = 0.3;
        button.setOpacity(opacity);
        button.setPrefWidth(10);
        button.setPrefHeight(10);

        EventHandler<ActionEvent> EventRemove = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //((Map) vBox.getUserData()).remove(textField1.getText());

                if (Variables.instantiate().Remove(textField1.getText())) {
                    vBox.getChildren().remove(gp);
                }
            }
        };


        ChangeListener<Boolean> ChangeNamevaliable = new ChangeListener<Boolean>() {//фокус
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {


                if (newValue && textField1.getUserData() == null) {//сохранение старого назваия
                    textField1.setUserData(textField1.getText() == null ? "" : textField1.getText());
                }
                String messErr = null;

                if (oldValue) {//потеря фокуса

                    if ((messErr = CheckNameVariable(textField1.getText(), (String) textField1.getUserData())) != null) {//проверка на норм. нахвание
                        System.out.println(messErr);
                        textField1.requestFocus();
                        return;
                    }

                    if ((textField1.getText() == null || textField1.getText().equals("")) && button.isDisable()) {//не ааодим нов. переменную
                        textField1.setUserData(null);
                        return;
                    }

                    if (!textField1.getText().equals((String) textField1.getUserData())) {//Если имя новое
//(((Map) vBox.getUserData()).get(textField1.getText())

                        if (Variables.instantiate().GetEquals(textField1.getText()) == null) {//Если его нет в базе

                            if (textField2.getText() == null) {
                                textField2.setText("0");
                            }
                            // ((Map) vBox.getUserData()).put(textField1.getText(), textField2.getText());

                            if (button.isDisable()) {    //показали кнопку и добавили новое поле
                                button.setOpacity(opacity);
                                button.setDisable(false);
                                AddNewEmptyVariable(vBox, null, null);
                            }
                            textField1.setUserData(null);

                            String name = null;
                            Node buf = vBox.getParent();
                            for (int i = 0; i < 5; i++) {
                                if (buf.getClass() != TitledPane.class) {
                                    buf = buf.getParent();
                                } else {
                                    name = ((TitledPane) buf).getText();
                                    break;
                                }
                            }
                            if (name != null) {
                                System.out.println("add'" + textField1.getText() + "'");
                                Variables.instantiate().variable.add(new Triple(textField1.getText(), textField2.getText(),
                                        Variables.instantiate().GetCategoryByName(name)));
                            } else {
                                System.out.println("Мне стыдно за этот костыль");

                            }

                        } else {
                            System.out.println("Это имя переменной занято");
                            textField1.requestFocus();
                        }
                    } else {
                        textField1.setUserData(null);
                    }
                }
            }
        };

        textField1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    textField2.requestFocus();
                }

            }
        });
        textField1.focusedProperty().addListener(ChangeNamevaliable);
        button.setOnAction(EventRemove);
        button.setText("X");
        if (key == null) {
            button.setDisable(true);
            button.setOpacity(0);
        }


        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(1);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(opacity);
            }
        });

        vBox.getChildren().add(gp);
    }

    public void FillContentAccordion(Accordion accordion) {
        for (TitledPane titledPane : accordion.getPanes()) {
            VBox vBox = (VBox) ((javafx.scene.control.ScrollPane) titledPane.getContent()).getContent();
            List<Triple> tripleList = Variables.instantiate().GetCategories(Variables.instantiate().GetCategoryByName(titledPane.getText()));
            for (Triple triple : tripleList) {
                AddNewEmptyVariable(vBox, triple.getKey(), triple.getValue());
            }
        }
    }


    public void CreateEmptyContentAccordion(Accordion accordion) {
        VBox vBox;
        javafx.scene.control.ScrollPane scrollPane;
        for (Map.Entry<Variables.categories, String> items : Variables.instantiate().titleName.entrySet()) {
            scrollPane = new javafx.scene.control.ScrollPane();

            vBox = new VBox();
            scrollPane.setContent(vBox);
            AddNewEmptyVariable(vBox, null, null);
            accordion.getPanes().add(new TitledPane(items.getValue(), scrollPane));

        }
    }

    public void CreateFillContentAccordion(Accordion accordion) {

        VBox vBox;
        //GridPane gp;
        TextField textField;
        GridPane gp;
        javafx.scene.control.ScrollPane scrollPane;

        for (Map.Entry<Variables.categories, String> items : Variables.instantiate().titleName.entrySet()) {
            scrollPane = new javafx.scene.control.ScrollPane();

            vBox = new VBox();
            //vBox.setUserData(items.getValue());

            scrollPane.setContent(vBox);

            List<Triple> triples;
            //for(Variables.categories categories:Variables.categories.values()){
            triples = Variables.instantiate().GetCategories(items.getKey());

            for (Triple item : triples) {
                AddNewEmptyVariable(vBox, item.getKey(), item.getValue());
            }
            AddNewEmptyVariable(vBox, null, null);
            accordion.getPanes().add(new TitledPane(items.getValue(), scrollPane));


        }
    }


    public void AddThenBlock(VBox vBox, Then whatWillChange, boolean last) {
        double opacity = 0.3;
        GridPane gp2 = new GridPane();
        if (last) {
            vBox.getChildren().add(gp2);
        } else {
            if (vBox.getChildren().size() > 0)
                vBox.getChildren().add(vBox.getChildren().size() - 1, gp2);
        }
        //GridIfBlock.add(gp2, 0, 1, 2, 1);
        vBox.setMargin(gp2, new Insets(10, 0, 10, 5));
        //GridIfBlock.setMargin(gp2, new Insets(0, 0, 20, 5));

        gp2.getRowConstraints().addAll(new RowConstraints());
        gp2.getColumnConstraints().addAll(new ColumnConstraints(),
                new ColumnConstraints(),
                new ColumnConstraints(),
                new ColumnConstraints(),
                new ColumnConstraints()
        );
        Button button = new Button();

        button.setOpacity(opacity);
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(1);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(opacity);
            }
        });


        EventHandler<ActionEvent> removeAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vBox.getChildren().remove(gp2);

            }
        };

        if (whatWillChange == null) {
            button.setText("add");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    button.setText("X");
                    button.setOnAction(removeAction);
                    AddThenBlock(vBox, null, true);
                }
            });
        } else {
            button.setText("X");
            button.setOnAction(removeAction);

        }
        gp2.add(button, 4, 1);


        ComboBox<Triple> cb = new ComboBox();


        ComboBox cb2 = new ComboBox();

        cb2.getItems().addAll("=", "+", "-");
        cb2.setValue("=");
        ComboBox<Triple> cb3 = new ComboBox();

        //  переписать combobox для переменных к чртовой матери!!!
       /* StringConverter stringConverter = new StringConverter<Triple>() {
            @Override
            public String toString(Triple user) {
                System.out.println("string converter " + user);
                if (user == null) {
                    return "1";
                } else {
                    return user.getKey();
                }

            }

            @Override
            public Triple fromString(String userId) {
                Triple triple = Variables.instantiate().FindFullVarOrConst(userId);
                System.out.println("triple" + triple.getKey());

                if (triple == null) {

                    return new Triple(userId, "0", Variables.categories.constant);
                } else {
                    return triple;
                }

            }
        };

          cb.setConverter(stringConverter);
          cb3.setConverter(stringConverter);*/
        Callback viewCellList = new Callback<ListView<Triple>, ListCell<Triple>>() {
            @Override
            public ListCell<Triple> call(ListView<Triple> param) {

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
        };
        cb.setCellFactory(viewCellList);
        cb3.setCellFactory(viewCellList);

        StringConverter<Triple> stringConverter = new StringConverter<Triple>() {
            @Override
            public String toString(Triple object) {
                if (object != null) {
                    return object.getKey();
                }
                return null;

            }

            @Override
            public Triple fromString(String string) {
                if (string != null) {
                    Triple tr = Variables.instantiate().FindFullVarOrConst(string);
                    if (tr == null) {
                        tr = new Triple(string, "0", Variables.categories.notFound);
                    }
                    return tr;
                }
                return null;
            }
        };
        cb.setConverter(stringConverter);
        cb3.setConverter(stringConverter);

        cb.setEditable(true);

        cb.setId("Combo1");
        cb2.setId("Combo2");
        cb3.setId("Combo3");


      /*  EventHandler<KeyEvent> OnKeyReleased = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                ComboBox comboBox = (ComboBox) event.getSource();
                if (!event.getText().equals("")) {


                    List<Triple> list=new ArrayList<>();
                    list.addAll(Variables.instantiate().Find(comboBox.getEditor().getText()));
                    System.out.println("size1 "+list.size());
                    list.addAll(Variables.instantiate().FindOptions(comboBox.getEditor().getText()));
                    System.out.println("size2 "+list.size());
                    if (list.size()>0) {
                        //добавление системных команд
                       // comboBox.getSelectionModel().select(-1);

                        System.out.println("Select before"+ comboBox.getSelectionModel().getSelectedIndex());

                        comboBox.getItems().removeIf((item) -> {
                            return !list.contains(item);
                        });

                        list.forEach((item) -> {
                            if (!comboBox.getItems().contains(item)) {
                                comboBox.getItems().add(item);
                            }
                        });
                        System.out.println("Select after"+ comboBox.getSelectionModel().getSelectedIndex());
                    }

                    if (!comboBox.getItems().isEmpty()) {
                        comboBox.show();
                    } else {
                        comboBox.hide();
                    }

                }

            }
        }*/
        ;
        EventHandler<KeyEvent> OnKeyReleased = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!event.getText().equals("")) {
                    ComboBox combo = (ComboBox) event.getSource();


                    // System.out.println("enter " + combo.getSelectionModel().getSelectedItem() + " " + combo.getEditor().getText() + "/  " + combo.getItems().size());


                    List<Triple> newList = Variables.instantiate().FindVarAndConst(combo.getEditor().getText());


                    combo.getItems().removeIf(new Predicate() {
                        @Override
                        public boolean test(java.lang.Object o) {
                            System.out.println("rem " + !newList.contains(o));
                            return !newList.contains(o);
                        }
                    });

                    newList.removeIf(new Predicate<Triple>() {
                        @Override
                        public boolean test(Triple triple) {
                            System.out.println("NewRem " + combo.getItems().contains(triple));
                            return combo.getItems().contains(triple);
                        }
                    });

                   /* for(int i=0; i<newList.size();i++){
                        System.out.println("  "+newList.get(i));
                    }
                    System.out.println("--------------------");*/

                  /*  for(int i=0; i<combo.getItems().size();i++){
                        System.out.println("  "+combo.getItems().get(i));
                    }
                    System.out.println("--------------------");*/
                    combo.getItems().addAll(newList);


                    if (combo.getItems().size() == 0) {
                        combo.hide();
                    } else {
                        if (event.getCode() != KeyCode.ENTER) {
                            combo.show();
                        }
                    }
                }
            }
        };
        cb.setOnKeyReleased(OnKeyReleased);

        cb3.setOnKeyReleased(OnKeyReleased);
        cb3.setEditable(true);
        cb.setPrefWidth(90);
        gp2.add(cb, 1, 1);
        gp2.add(cb2, 2, 1);
        cb3.setPrefWidth(90);
        gp2.add(cb3, 3, 1);


        FillComboThen(cb, cb2, cb3, whatWillChange);
    }

    public void FillComboThen(ComboBox cb, ComboBox cb2, ComboBox cb3, Then whatHeppened) {


        if (whatHeppened != null) {
            // String[] splittedWhatWillChange = whatHeppenedSplit(whatHeppened);

            if (whatHeppened != null && whatHeppened.getVariable1() != null && whatHeppened.getVariable2() != null) {
                cb.setValue(whatHeppened.getVariable1());
                cb3.setValue(whatHeppened.getVariable2());
                //cb.setValue(new Pair<Character, String>(whatHeppened.getVariable1().getKey(),whatHeppened.getVariable1().getValue()));
                //cb3.setValue(new Pair<Character, String>(whatHeppened.getVariable2().getKey(),whatHeppened.getVariable2().getValue()));
                if (whatHeppened.getOperator() == null) {
                    cb2.setValue("=");
                }

            } else {
                System.out.println("Error when creating thenBlock content");
            }

        }
    }


    public void setIfTrxtComponent(GridPane parent, String setText) {
        //TextField textField;
        for (Node item : parent.getChildren()) {
            if (item.getId() != null && item.getId().equals("IF")) {
                ((javafx.scene.control.TextArea) item).setText(setText);
                return;
            }
        }      //  TextField textField=(TextField) gp.getChildren().get(1);
    }

    public String getIfTrxtComponent(GridPane parent) {
        //TextField textField;
        for (Node item : parent.getChildren()) {
            if (item.getId() != null && item.getId().equals("IF")) {
                return ((javafx.scene.control.TextArea) item).getText();
            }
        }      //  TextField textField=(TextField) gp.getChildren().get(1);
        return null;
    }


    public  void PopupListSetKeyReleased(javafx.scene.control.TextArea textArea, ListView list, Popup popup1){
        textArea.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                boolean openList=false;
                if(event.getCode()== KeyCode.SHIFT && event.isControlDown()){
                    openList=true;
                    textArea.appendText("[");
                }

                if (event.getText().equals("[")|| openList) {
                    textArea.setUserData(textArea.getCaretPosition());

                    Point2D p = textArea.localToScene(0.0, 0.0);
                    double x = p.getX() + textArea.getScene().getX() + textArea.getScene().getWindow().getX();
                    double y = p.getY() + textArea.getScene().getY() + textArea.getScene().getWindow().getY();
                    x += textArea.getWidth();
                    list.getItems().clear();
                    list.getItems().addAll(Variables.instantiate().variable);
                    popup1.show(textArea, x, y);
                }


                if (event.getText().equals("]")) {
                    popup1.hide();
                    textArea.setUserData(null);

                }
            }
        });

        textArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue) {
                    popup1.hide();
                }
            }
        });
    }


    public void CreateIFBlock(VBox vBoxParent, IfThen whatHappend,ListView listView,Popup popup) {
        double opacity = 0.3;
        GridPane gp = new GridPane();

        gp.getRowConstraints().addAll(new RowConstraints(), new RowConstraints());
        gp.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints(), new ColumnConstraints());
        //FlowPane flowP=new FlowPane();
        Label labIF = new Label("IF");
        labIF.setPrefWidth(10);
        labIF.setAlignment(Pos.TOP_LEFT);
        javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea();
        textArea.setId("IF");

        PopupListSetKeyReleased(textArea,listView,popup);
        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {

                }
            }
        });

        textArea.setPrefWidth(100);
        textArea.setPrefHeight(60);
        gp.add(textArea, 1, 0);
        gp.add(labIF, 0, 0);
        VBox vBox = new VBox();
        vBox.setId("Then");
        gp.add(vBox, 1, 1);
        Button button = new Button();
        gp.add(button, 2, 0);
        button.setOpacity(opacity);
        button.setText("X");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                vBoxParent.getChildren().remove(gp);
            }
        });
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(1);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setOpacity(opacity);
            }
        });


        if (whatHappend != null) {
            textArea.setText(whatHappend.getIfString());
            //разделить "что случится" и поместить в текстовые поля
            //    String[] recHeppend = whatHappend.getValue().split(";");
            for (int i = 0; i < whatHappend.getThenList().size(); i++) {
                AddThenBlock(vBox, whatHappend.getThenList().get(i), true);
            }
        }
        AddThenBlock(vBox, null, true);

        vBoxParent.getChildren().add(vBoxParent.getChildren().size() - SkipifContent, gp);//2 button exist
        //return gp;
    }


    public ComboBox[] GetComboRec(GridPane parent) {
        ComboBox[] ret = new ComboBox[3];
        for (Node item : parent.getChildren()) {
            if (item.getId() != null && item.getId().equals("Combo1")) {
                ret[0] = (ComboBox) item;
            }
            if (item.getId() != null && item.getId().equals("Combo2")) {
                ret[1] = (ComboBox) item;
            }
            if (item.getId() != null && item.getId().equals("Combo3")) {
                ret[2] = (ComboBox) item;
            }
        }
        return ret;
    }


    public void FillThenBlock(GridPane parent, List<Then> allChange) {
        ComboBox[] cb = new ComboBox[3];
        //   String[] shortChanged=allChange.split(";");
        //whatHeppenedSplit(allChange);

        for (Node item : parent.getChildren()) {
            if (item.getId() != null && item.getId().equals("Then")) {
                VBox parentRecords = (VBox) item;
                int howWasRecord = parentRecords.getChildren().size() - 1;
                int sub = howWasRecord - allChange.size();
                if (sub > 0) {
                    parentRecords.getChildren().remove(0, sub);
                    howWasRecord = parentRecords.getChildren().size() - 1;
                } else {
                    if (sub < 0) {
                        sub *= -1;
                        for (int i = 0; i < sub; i++) {
                            AddThenBlock(parentRecords, allChange.get(i + howWasRecord), false);
                        }
                    }
                }
                Node grid;
                for (int i = 0; i < howWasRecord; i++) {
                    grid = parentRecords.getChildren().get(i);
                    if (grid.getClass() == GridPane.class) {


                        cb = GetComboRec((GridPane) grid);

                        for (int j = 0; j < cb.length; j++) {
                            if (cb[j] == null) {
                                System.out.println("Not found Combo" + (j + 1));
                                return;
                            }
                        }

                        //Крэшится когда переключаешь больший Then в Меньший
                        FillComboThen(cb[0], cb[1], cb[2], allChange.get(i));
                    }
                }
            }
        }
    }


}
