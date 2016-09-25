package main.java.GUI;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;


/**
 * Created by arsen on 20.09.2016.
 */
public final class MyTreeCell extends TreeCell {
    MyTreeCell(ContextMenu contextMenu) {
        super();

        if(contextMenu!=null){
            setContextMenu(contextMenu);
        }
    }

    @Override
    public void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.toString());
            setGraphic(getTreeItem().getGraphic());
        }
    }
}



