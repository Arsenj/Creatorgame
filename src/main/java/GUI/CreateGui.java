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
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.stage.*;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.Pair;
import javafx.util.StringConverter;
import main.java.*;
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

    private  int SkipifContent=1;



    public void TestCreateContentAccordion(Accordion accordion) {

        CreateContentAccordion(accordion);

    }


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


    public void CreateContentAccordion(Accordion accordion) {


        VBox vBox;
        //GridPane gp;
        TextField textField;
        GridPane gp;
        javafx.scene.control.ScrollPane scrollPane;

        for ( Map.Entry<Variables.categories, String> items :Variables.instantiate().titleName.entrySet()){
            scrollPane = new javafx.scene.control.ScrollPane();

            vBox = new VBox();
            //vBox.setUserData(items.getValue());

            scrollPane.setContent(vBox);

            List<Triple> triples;
            //for(Variables.categories categories:Variables.categories.values()){
                triples=Variables.instantiate().GetCategories(items.getKey());

                for (Triple item:triples){
                    AddNewEmptyVariable(vBox, item.getKey(), item.getValue());
                }
                AddNewEmptyVariable(vBox, null, null);
                accordion.getPanes().add(new TitledPane(items.getValue(), scrollPane));
            //}




         /*   for (java.lang.Object item : items.getValue().entrySet()) {


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
            accordion.getPanes().add(new TitledPane(items.getValue(), scrollPane));
        }
*/

     /*   for (int i = 0; i < Variables.instantiate().titleName.size(); i++) {
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
        }*/

    }}





    public void AddThenBlock(VBox vBox, Then whatWillChange,boolean last) {
        double opacity = 0.3;
        GridPane gp2 = new GridPane();
        if(last){
        vBox.getChildren().add(gp2);
        }else {
            if(vBox.getChildren().size()>0)
            vBox.getChildren().add(vBox.getChildren().size()-1,gp2);
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
                    AddThenBlock(vBox, null,true);
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
        StringConverter stringConverter = new StringConverter<Triple>() {
            @Override
            public String toString(Triple user) {
                if (user == null) {
                    return null;
                } else {
                    return user.getKey();
                }

            }

            @Override
            public Triple fromString(String userId) {
                Triple triple=Variables.instantiate().GetFirst(userId);
                if(triple==null){
                    return new Triple(userId,"0", Variables.categories.constant);
                }else {
                    return triple;
                }

            }
        };
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
        cb.setConverter(stringConverter);
        cb3.setConverter(stringConverter);
        cb.setCellFactory(viewCellList);
        cb3.setCellFactory(viewCellList);
        cb.setEditable(true);

        cb.setId("Combo1");
        cb2.setId("Combo2");
        cb3.setId("Combo3");




        EventHandler<KeyEvent> OnKeyReleased = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {


                        if (!event.getText().equals("")) {
                            ComboBox comboBox = (ComboBox) event.getSource();

                            List<Triple> list = Variables.instantiate().Find(comboBox.getEditor().getText());
                            if (list != null) {

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



                FillComboThen(cb,cb2,cb3,whatWillChange);
    }

    public  void FillComboThen(ComboBox cb,ComboBox cb2,ComboBox cb3,Then whatHeppened ){



        if (whatHeppened != null) {
           // String[] splittedWhatWillChange = whatHeppenedSplit(whatHeppened);

            if (whatHeppened != null && whatHeppened.getVariable1() != null && whatHeppened.getVariable2()!=null) {
                cb.setValue(whatHeppened.getVariable1());
                cb3.setValue(whatHeppened.getVariable2());
                //cb.setValue(new Pair<Character, String>(whatHeppened.getVariable1().getKey(),whatHeppened.getVariable1().getValue()));
                //cb3.setValue(new Pair<Character, String>(whatHeppened.getVariable2().getKey(),whatHeppened.getVariable2().getValue()));
                if(whatHeppened.getOperator()==null){
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
            if (item.getId()!=null && item.getId().equals("IF")) {
                ((javafx.scene.control.TextArea) item).setText(setText);
                return;
            }
        }      //  TextField textField=(TextField) gp.getChildren().get(1);
    }

    public String getIfTrxtComponent(GridPane parent) {
        //TextField textField;
        for (Node item : parent.getChildren()) {
            if (item.getId()!=null && item.getId().equals("IF")) {
                return  ((javafx.scene.control.TextArea) item).getText();
            }
        }      //  TextField textField=(TextField) gp.getChildren().get(1);
        return null;
    }

    public void CreateIFBlock(VBox vBoxParent, IfThen whatHappend) {
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
                AddThenBlock(vBox, whatHappend.getThenList().get(i),true);
            }
        }
        AddThenBlock(vBox, null,true);


        //gp.add(flowP,0,0);
        // flowP.getChildren().addAll(labIF,new Label("( "),new Label(" )"));

        vBoxParent.getChildren().add(vBoxParent.getChildren().size() - SkipifContent, gp);//2 button exist
        //return gp;
    }


    public ComboBox[] GetComboRec(GridPane parent){
        ComboBox[] ret=new ComboBox[3];
        for (Node item:parent.getChildren()){
            if(item.getId()!=null && item.getId().equals("Combo1")){
                ret[0]=(ComboBox)item;
            }
            if(item.getId()!=null && item.getId().equals("Combo2")){
                ret[1]=(ComboBox)item;
            }
            if(item.getId()!=null && item.getId().equals("Combo3")){
                ret[2]=(ComboBox)item;
            }
        }
        return  ret;
    }


    public void FillThenBlock(GridPane parent,List<Then> allChange) {
        ComboBox[] cb=new ComboBox[3];
     //   String[] shortChanged=allChange.split(";");
                //whatHeppenedSplit(allChange);

        for (Node item : parent.getChildren()) {
            if (item.getId()!=null && item.getId().equals("Then")) {
                VBox parentRecords=(VBox) item;
                int howWasRecord=parentRecords.getChildren().size()-1;
                int sub=howWasRecord-allChange.size();
                if(sub>0){
                    parentRecords.getChildren().remove(0,sub);
                    howWasRecord=parentRecords.getChildren().size()-1;
                }else{
                    if(sub<0){
                        sub*=-1;
                        for(int i=0; i<sub;i++){
                            AddThenBlock(parentRecords,allChange.get(i+howWasRecord),false);
                        }
                    }
                }
                Node grid;
                for(int i=0; i<howWasRecord;i++){
                    grid=parentRecords.getChildren().get(i);
                    if(grid.getClass()==GridPane.class){


                        cb= GetComboRec((GridPane)grid);

                        for(int j=0; j<cb.length;j++){
                            if(cb[j]==null){
                                System.out.println("Not found Combo"+(j+1));
                                return;
                            }
                        }

                        //Крэшится когда переключаешь больший Then в Меньший
                        FillComboThen(cb[0],cb[1],cb[2],allChange.get(i));
                    }
                }
            }
        }
    }


    public Button CreateButtonOfChoice(VBox parent, String name) {
        Button button = new Button(name);

        parent.getChildren().add(button);
        return button;
    }
}
