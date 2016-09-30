package main.java;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javax.swing.event.EventListenerList;
import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by arsen on 15.08.2016.
 */
public class Game {
    //public Variables variables;
    //public    List<StructGame> structGames;
    private int countPage;
    private Map<Integer, StructGame> structGames;
    // private Map<Integer, StructGame> localStructGames;
    //private ObservableMap<Integer, StructGame> structGames;
    private int currentIdPage;
    protected EventListenerList listenerList;
    protected EventListenerList structGameChengedListenerList;
    private int lastId = 0;

    public Map.Entry<Integer,StructGame>[] getPages() {
        Map.Entry<Integer,StructGame>[] arr=new Map.Entry[structGames.size()];
         structGames.entrySet().toArray(arr);
        return arr;
    }

    public Game() {
        listenerList = new EventListenerList();
        structGameChengedListenerList = new EventListenerList();
        structGames = new Hashtable<Integer, StructGame>();
    }

    public int getCountPage() {
        return countPage;
    }

    public void Save(File file, String nameFile) {
        file.Write(structGames, nameFile);
    }

    public boolean Load(File file, String nameFile) {
        structGames = file.<Map<Integer, StructGame>>Read(nameFile);
        if (structGames != null) {
            if(structGames.size()>0){
                lastId= structGames.entrySet().iterator().next().getKey();
            }

            return true;
        } else {
            return false;
        }
    }

    public void Refresh() {
        lastId = 0;
        if (structGames != null) {
            structGames.clear();
        }
        currentIdPage = 0;
    }

    public void AddNewPage(StructGame value) {
        lastId++;
        if (value == null) {
            value = new StructGame();
        }
        Integer key = lastId;
        value.id = lastId;

        currentIdPage = lastId;

        structGames.put(key, value);
        countPage = structGames.size();
        triggeredEvent(new structGameChangedEvent(this, value, structGameChangedEvent.event.Add));
    }

    public Integer GetSerialNumberPage(int id) {
        Map.Entry<Integer, String>[] arr = new Map.Entry[structGames.size()];
        structGames.entrySet().toArray(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getKey() == id) {
                return arr.length - 1 - i;
            }
        }
        return null;
    }

    public boolean RemovePage(Integer id) {
        return RemovePage(structGames.get(id));
    }

    public boolean RemovePage(StructGame value) {
        if (value != null) {
            if (currentIdPage == value.id) {
                structGames.remove(value);
                int id = currentIdPage;
                currentIdPage = structGames.entrySet().iterator().next().getValue().id;
                PageChangeEvent event = new PageChangeEvent(this, currentIdPage, id);
                countPage = structGames.size();
                triggeredEvent(event);
                return true;
            }
            triggeredEvent(new structGameChangedEvent(this, value, structGameChangedEvent.event.Delete));
            structGames.remove(value);
            return true;
        }
        return false;
    }


    public void addStructGameChangedListener(structGameChangedListener item) {
        this.structGameChengedListenerList.add(main.java.structGameChangedListener.class, item);
    }

    public void removeStructGameChangedListener(structGameChangedListener item) {
        this.structGameChengedListenerList.remove(structGameChangedListener.class, item);
    }

    public void addPageChangeListener(pageChangedListener pageChangedListener) {
        listenerList.add(main.java.pageChangedListener.class, pageChangedListener);
    }

    public void triggeredEvent(structGameChangedEvent e) {
        Object[] listeners = structGameChengedListenerList.getListenerList();
        for (int i = 1; i < listeners.length; i++) {
            ((structGameChangedListener) listeners[i]).call(e);
        }
    }

    public void removePageChangeListener(pageChangedListener pageChangedListener) {
        listenerList.remove(main.java.pageChangedListener.class, pageChangedListener);
    }

    void triggeredEvent(PageChangeEvent e) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 1; i < listeners.length; i++) {
            ((pageChangedListener) listeners[i]).OnPageChanged(e);
        }
    }

    public boolean DeletePageById(int id) {
        StructGame structGame = structGames.get(id);
        if (structGame != null) {
            structGames.remove(structGame);
            return true;
        }
        return false;
    }

    /*private  StructGame FindById(int id){

        return structGames.get(id);
    }*/


    public StructGame getPageById(int id) {

        StructGame buf = structGames.get(id);

        if (buf != null) {
            if (currentIdPage != id) {
                PageChangeEvent event = new PageChangeEvent(this, id, currentIdPage);
                currentIdPage = id;
                triggeredEvent(event);
            }


        }

        return buf;
    }

    public void DeleteButtonFomCurrentPage(VBox parent, Button delButton, int howElemensSkip) {
        int index = parent.getChildren().indexOf(delButton) - howElemensSkip;


    }

    public StructGame getCurrentPage() {
        return structGames.get(currentIdPage);
    }


}
