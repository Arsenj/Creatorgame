package main.java;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arsen on 14.08.2016.
 */
public class Main {
    String FileNam="Variables";

    public static void main(String[] args) throws IOException {

     MyTest test=new MyTest();

        //test.testCheckVariable();
        test.testIfExpression();

/*        Game G=new Game();
        G.variables.things.put("Стол",1);
        G.variables.things.put("Стул",1);
        G.variables.Write("Variables",G.variables);
        G.variables=G.variables.Read("Variables");
        for(Map.Entry<String,Integer> item : G.variables.things.entrySet()){
            System.out.println(String.format("%2s:%4d", item.getKey(),item.getValue()));
        }

  */


    }
}
