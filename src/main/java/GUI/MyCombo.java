package main.java.GUI;

import com.sun.org.apache.xpath.internal.operations.Variable;
import com.sun.webkit.PopupMenu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.Pair;
import main.java.Triple;
import main.java.Variables;

import java.util.List;

/**
 * Created by arsen on 28.09.2016.
 */
public class MyCombo extends TextField {
    List<Triple> list;
   public MyCombo(){
        super();
       Popup popup=new Popup();
       javafx.scene.control.ScrollPane pane = new javafx.scene.control.ScrollPane();
       popup.getContent().add(pane);
       ListView<Triple> listView=new ListView();
       pane.setContent(listView);
       listView.setPrefWidth(100);
       listView.setPrefHeight(100);


       this.focusedProperty().addListener(new ChangeListener<Boolean>() {
           @Override
           public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if(oldValue==true){
                   popup.hide();
               }
           }
       });

       listView.setCellFactory(new Callback<ListView<Triple>, ListCell<Triple>>() {
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


       this.setOnKeyReleased(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent event) {
               list= Variables.instantiate().Find( MyCombo.super.getText());
               listView.getItems().addAll(list);
                if(list.size()==0){
                    popup.hide();
                }else {
                    popup.show((Node)event.getSource(),0,0);
                }

           }
       });

       listView.selectionModelProperty().addListener(new ChangeListener<MultipleSelectionModel<Triple>>() {
           @Override
           public void changed(ObservableValue<? extends MultipleSelectionModel<Triple>> observable, MultipleSelectionModel<Triple> oldValue, MultipleSelectionModel<Triple> newValue) {
               if(newValue.getSelectedItem()!=null){

                   MyCombo.super.setText(newValue.getSelectedItem().getKey());
               }
           }
       });

    }


}
