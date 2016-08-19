package main.java

import junit.framework.TestCase
import main.java.ButtonGame
import main.java.Variables
import org.junit.Test

/**
 * Created by arsen on 17.08.2016.
 */
public class MyTestTest  {

    MyTestTest(){

    }
    //@Test
   public  void testCheckVariable() {
        Map<String,Integer> people=Variables.instantiate().people;
        Map<String,Integer> thingth=Variables.instantiate().things;
        people.put("Иван",10);
        thingth.put("Стол",1);
        thingth.put("Стул",2);
        ButtonGame b=new ButtonGame();

       /* System.out.println("Тест 'CheckVariable' "+
               );*/


        assertEquals("true",0, b.Regular("((\\d+)\\[(.*?)\\]([><=!])|\\[(.*?)\\](\\d+)([><=!])|\\[(.*?)\\]\\[(.*?)\\]([><=!]))"
                ,"if[PИван]10>[TСтол]1=[TСтул]1=||&&"));
    }


}
