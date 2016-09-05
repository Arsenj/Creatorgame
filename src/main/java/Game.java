package main.java;

import javax.swing.event.EventListenerList;
import java.io.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Created by arsen on 15.08.2016.
 */
public class Game {
   public Variables variables;
    public    List<StructGame> structGames;
    private   int currentIndexPage;
    protected  EventListenerList listenerList;

    public  Game(){
        listenerList=new EventListenerList();

        structGames=new ArrayList<StructGame>(){
            @Override
            public boolean add(StructGame e){

              boolean ret=  super.add(e);
                if(ret){
                   currentIndexPage=this.size()-1;
                }

       /* перегрузить метод add чтоб  currentIndexPage задавало
                        SampleController конструктор там тестовое создание Страницы*/
                return ret;
            }

        };
        //variables=new Variables();
    }

    public  void  addPageChangeListener(pageChangedListener pageChangedListener){
        listenerList.add(main.java.pageChangedListener.class,pageChangedListener);
    }
    public  void  removePageChangeListener(pageChangedListener pageChangedListener){
        listenerList.remove(main.java.pageChangedListener.class,pageChangedListener);
    }

    void triggeredEvent(PageChangeEvent e){
        Object[] listeners = listenerList.getListenerList();
        for(int i=1; i<listeners.length;i++){
            ((pageChangedListener)listeners[i]).OnPageChanged(e);
        }
    }
    public  StructGame getPage(int num ){


        StructGame buf=structGames.get(num);
        if(buf!=null){
            if(currentIndexPage!=num){
                PageChangeEvent event=new PageChangeEvent(this,num,currentIndexPage);
                currentIndexPage=num;
                triggeredEvent(event);
            }


        }

        return buf;
    }

    public StructGame getCurrentPage(){
        return  structGames.get(currentIndexPage);
    }



}
