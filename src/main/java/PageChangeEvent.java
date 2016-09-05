package main.java;

import javafx.event.Event;

import java.util.EventObject;

/**
 * Created by arsen on 04.09.2016.
 */
public class PageChangeEvent extends EventObject {

    protected int lastvalue,newValue;

    PageChangeEvent(Object source,int newValue,int lastvalue){
        super(source);
        this.lastvalue=lastvalue;
        this.newValue=newValue;
    }

    public int getLastvalue(){
        return  lastvalue;
    }
    public int getNewValue(){
       return newValue;
    }

}
