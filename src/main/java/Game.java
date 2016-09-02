package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arsen on 15.08.2016.
 */
public class Game {
   public Variables variables;
    public    List<StructGame> structGames;
    private   int currentIndexPage;


    public  Game(){

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


    public  StructGame getPage(int num ){

        StructGame buf=structGames.get(num);
        if(buf!=null){
            currentIndexPage=num;
        }
        return buf;
    }
    public StructGame getCurrentPage(){
        return  structGames.get(currentIndexPage);
    }



}
