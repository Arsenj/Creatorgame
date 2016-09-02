package main.java;

import javafx.util.Pair;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arsen on 15.08.2016.
 */
public class StructGame {
    public  int id;
    public  String text;
    public List<ButtonGame> buttons;
    private  int selectedButton;
    public List<Pair<String,String>> onLoadHappend;



    public  ButtonGame getButton(int num ){
        ButtonGame buf=buttons.get(num);
        if(buf!=null){
            selectedButton=num;
        }
        return buf;
    }
    public  int getIndexSelectedButton(){
        return  selectedButton;
    }
    public ButtonGame getSelectedButton(){
        return  buttons.get(selectedButton);
    }


    String PrintText(){
        StringBuilder sb=new StringBuilder(text);
        int posStart,posEnd;
        Object insertedVal=null;
        while ((posStart=sb.indexOf("["))!=-1){
            posEnd=sb.indexOf("]",posStart);
         insertedVal= ParseIf.GetValueVariable(sb.substring(posStart,posEnd));
           sb=sb.replace(posStart-1,posEnd+1,insertedVal.toString());
        }
        return sb.toString();
    }
  public   StructGame(){

        buttons=new ArrayList<>();
        onLoadHappend=new ArrayList<>();

    }


}
