package main.java;

import java.util.Map;

/**
 * Created by arsen on 18.08.2016.
 */
public class MyTest {


    public MyTest(){

    }

    public void testPolandrrecord(){
        ParseIf parseIf=new ParseIf();
        System.out.println(parseIf.ConvertToPolandRecord("([PМороз]>=20|[UПогода]=\"Зима\")&[CФантазия]>=10"));
    }

    public void testHappend(){
        Variables.instantiate().people.put("Мороз",20);
        Variables.instantiate().userVariable.put("Погода","Зима");
        Variables.instantiate().userVariable.put("Age","18");
        Variables.instantiate().character.put("Фантазия",0);
        Variables.instantiate().character.put("Знание",10);

        ButtonGame Bg=new ButtonGame();
        Bg.HappendParse("[PМороз]-15;[UПогода]=\"Лето\";[CФантазия]=[CЗнание];[UAge]=20;move12");
        //


        System.out.println(String.format(" 5=%2d  20=%2s  10=%3d",
                Variables.instantiate().people.get("Мороз"),
                Variables.instantiate().userVariable.get("Age"),
                Variables.instantiate().character.get("Фантазия")));
    }


    public  void testIfExpression() {
        //Map<String,Integer> people=Variables.instantiate().people;
        //Map<String,Integer> thingth=Variables.instantiate().things;
        Variables.instantiate().people.put("Мороз",20);
        Variables.instantiate().userVariable.put("Погода","Зима");
        Variables.instantiate().character.put("Фантазия",10);



        ParseIf ParseIf=new ParseIf();


        Boolean res=ParseIf.GetIf("if[PМороз]>=20[UПогода]=\"Зима\"&[CФантазия]>=10&");
        if(res==null){
            System.out.println("Error testIfExpression");
        }else{
            System.out.println("testIfExpression res "+ res);
        }
       /* System.out.println("Тест 'CheckVariable' "+
               );*/
        //System.out.println("Test 'CheckVariable' "+b.RegularExpression("if[PИван]10>[TСтол]1=[TСтул]1=||&&"));
    }

}
