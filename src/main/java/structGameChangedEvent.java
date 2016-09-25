package main.java;

import java.util.EventObject;

/**
 * Created by arsen on 21.09.2016.
 */
public class structGameChangedEvent extends EventObject{
  public   enum event{Add,Delete}
    private StructGame structGame;
    private event event;
    public  StructGame getStructGame(){
        return structGame;
    }
    public  event getEvent(){
        return  event;
    }

    public structGameChangedEvent(Object sours,StructGame structGame,event event){
        super(sours);
        this.structGame=structGame;
        this.event=event;
    }
}
