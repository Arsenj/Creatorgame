package main.java.GUI;

import java.awt.ScrollPane;
import java.awt.event.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.util.StringConverter;
import main.java.Variables;
import org.omg.CORBA.Object;
import sun.nio.cs.CharsetMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.TextArea;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by arsen on 25.08.2016.
 */

public class CreateGui {


    public void TestCreateContentAccordion(Accordion accordion, List<Pair<String, Map>> listTitle) {

        Variables.instantiate().things.put("Стол", 1);
        Variables.instantiate().things.put("Стул", 1);
        Variables.instantiate().things.put("Палка", 1);
        Variables.instantiate().things.put("Камень", 1);
        Variables.instantiate().things.put("Деньги", 15);
        Variables.instantiate().people.put("Иван", 0);
        Variables.instantiate().userVariable.put("погода", "Зима");
        CreateContentAccordion(accordion, listTitle);

    }


    public void removeAtVariable(VBox vBox, java.lang.Object removeObj) {
        vBox.getChildren().remove(removeObj);

    }

    String ChackNameVariable(String NameNew, String oldName) {
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
                ((Map) vBox.getUserData()).remove(textField1.getText());
                vBox.getChildren().remove(gp);
            }
        };


        ChangeListener<Boolean> ChangeNamevaliable = new ChangeListener<Boolean>() {//фокус
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {


                if (newValue && textField1.getUserData() == null) {//сохранение старого назваия
                    textField1.setUserData(textField1.getText() == null ? "" : textField1.getText());
                }
                String messErr = null;

                if (oldValue) {//потеря фокуса

                    if ((messErr = ChackNameVariable(textField1.getText(), (String) textField1.getUserData())) != null) {//проверка на норм. нахвание
                        System.out.println(messErr);
                        textField1.requestFocus();
                        return;
                    }

                    if ((textField1.getText() == null || textField1.getText().equals("")) && button.isDisable()) {//не ааодим нов. переменную
                        textField1.setUserData(null);
                        return;
                    }

                    if (!textField1.getText().equals((String) textField1.getUserData())) {//Если имя новое

                        if ((((Map) vBox.getUserData()).get(textField1.getText()) == null)) {//Если его нет в базе

                            if (textField2.getText() == null) {
                                textField2.setText("0");
                            }
                            ((Map) vBox.getUserData()).put(textField1.getText(), textField2.getText());

                            if (button.isDisable()) {    //показали кнопку и добавили новое поле
                                button.setOpacity(opacity);
                                button.setDisable(false);
                                AddNewEmptyVariable(vBox, null, null);
                            }
                            textField1.setUserData(null);
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


    public void CreateContentAccordion(Accordion accordion, List<Pair<String, Map>> listTitle) {


        VBox vBox;
        //GridPane gp;
        TextField textField;
        GridPane gp;
        javafx.scene.control.ScrollPane scrollPane;
        if (listTitle == null) {
            return;
        }
        for (int i = 0; i < listTitle.size(); i++) {
            scrollPane = new javafx.scene.control.ScrollPane();

            vBox = new VBox();
            vBox.setUserData(listTitle.get(i).getValue());
            scrollPane.setContent(vBox);
            for (java.lang.Object item : listTitle.get(i).getValue().entrySet()) {

                AddNewEmptyVariable(vBox, ((Map.Entry) item).getKey().toString(), ((Map.Entry) item).getValue().toString());
//                gp=new GridPane();
//                gp.getColumnConstraints().addAll(new ColumnConstraints(),new ColumnConstraints());
//                textField=new TextField(((Map.Entry)item).getKey() .toString());
//                gp.add(textField,0,0);
//                textField=new TextField(((Map.Entry)item).getValue().toString());
//                //textField.setEditable(false);
//                gp.add(textField,1,0);
//                vBox.getChildren().add(gp);
            }
            AddNewEmptyVariable(vBox, null, null);
            accordion.getPanes().add(new TitledPane(listTitle.get(i).getKey(), scrollPane));
        }

    }

public  void AddThenBlock(VBox vBox){
    double opacity=0.3;
    GridPane gp2 = new GridPane();
    vBox.getChildren().add(gp2);
    //GridIfBlock.add(gp2, 0, 1, 2, 1);
    vBox.setMargin(gp2,new Insets(10, 0, 10, 5));
    //GridIfBlock.setMargin(gp2, new Insets(0, 0, 20, 5));

    gp2.getRowConstraints().addAll(new RowConstraints());
    gp2.getColumnConstraints().addAll(new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints()
    );
Button button=new Button();

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
    button.setText("add");
    button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            button.setText("X");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    vBox.getChildren().remove(gp2);

                }
            });
            AddThenBlock(vBox);
        }
    });

    gp2.add(button,4,1);
    //Label thenLab = new Label("THEN");
    //thenLab.setPrefWidth(50);
    //gp2.add(thenLab, 0, 0);

    ComboBox<Pair<Character, String>> cb = new ComboBox();



    ComboBox cb2 = new ComboBox();
    cb2.getItems().addAll("=","+","-");
    cb2.setValue("=");
    ComboBox<Pair<Character, String>> cb3 = new ComboBox();
    StringConverter stringConverter = new StringConverter<Pair<Character, String>>() {
        @Override
        public String toString(Pair<Character, String> user) {
            if (user == null) {
                return null;
            } else {
                return user.getValue();
            }
        }

        @Override
        public Pair<Character, String> fromString(String userId) {
            return new Pair<>(null,userId);
        }
    };
    Callback viewCellList = new Callback<ListView<Pair<Character, String>>, ListCell<Pair<Character, String>>>() {
        @Override
        public ListCell<Pair<Character, String>> call(ListView<Pair<Character, String>> param) {

            return new ListCell<Pair<Character, String>>() {
                @Override
                protected void updateItem(Pair<Character, String> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getValue());
                    }
                }
            };
        }
    };
    cb.setConverter(stringConverter);
    cb3.setConverter(stringConverter);
    cb.setCellFactory(viewCellList);
    cb3.setCellFactory(viewCellList);


    cb.setEditable(true);
    EventHandler<KeyEvent> OnKeyReleased = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

            if (!event.getText().equals("")) {
                ComboBox comboBox = (ComboBox) event.getSource();
                System.out.println(comboBox.getSelectionModel().getSelectedIndex());
                if (comboBox.getEditor().getText().equals(comboBox.getValue())) {
                    return;
                }

                List<Pair<Character, String>> list = Variables.instantiate().Find(comboBox.getEditor().getText());
                if(list!=null) {
                    comboBox.getItems().removeIf((item) -> {
                        return !list.contains(item);
                    });

                    list.forEach((item) -> {
                        if (!comboBox.getItems().contains(item)) {
                            comboBox.getItems().add(item);
                        }
                    });
                }

                if (!comboBox.getItems().isEmpty()) {
                    comboBox.show();
                } else {
                    comboBox.hide();
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
}
    public GridPane CreateIFBlock(List<Pair<String, String>> whatHappend) {
        GridPane gp = new GridPane();

        gp.getRowConstraints().addAll(new RowConstraints(), new RowConstraints());
        gp.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints());
        //FlowPane flowP=new FlowPane();
        Label labIF = new Label("IF");
        labIF.setPrefWidth(50);
        labIF.setAlignment(Pos.TOP_LEFT);
        javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea();
        textArea.setPrefWidth(100);
        textArea.setPrefHeight(60);
        gp.add(textArea, 1, 0);
        gp.add(labIF, 0, 0);
        VBox vBox=new VBox();
        gp.add(vBox,1,1);
        AddThenBlock(vBox);
        //gp.add(flowP,0,0);
        // flowP.getChildren().addAll(labIF,new Label("( "),new Label(" )"));



        return gp;
    }
}
