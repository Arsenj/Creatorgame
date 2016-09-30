package main.java; /**
 * Created by arsen on 15.08.2016.
 */

import javafx.beans.binding.StringBinding;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.regex.Matcher;

public class ButtonGame implements Serializable{
    public boolean enable;
    //public  boolean textBlock;
    public String ifButtonExist;
    public List<IfThen> whatHappend;
    //public List<Pair<String,String>> whatHappend;
    public String text;


    //new method


    //--ne method


    private void SetHashTable(String key, String value) {

        Triple tripe;
        for (int i = 0; i < Variables.instantiate().variable.size(); i++) {
            tripe = Variables.instantiate().variable.get(i);
            if (tripe.getKey().equals(key)) {
                tripe.setValue(value);
            }
        }

    }


   /* private void changeVariables(String var1,String operand, String var3) {

        //Object operand, operand2;



            switch (operand) {
                case "+":
                    if (var1 instanceof String) {
                        operand = ((String) operand).concat((String) operand2);
                    } else {
                        operand = (Integer) operand + (Integer) operand2;
                    }
                    SetHashTable(var[0], operand);
                    break;
                case "-":
                    if (operand instanceof String) {
                        operand = ((String) operand).replaceAll((String) operand2, "");
                    } else {
                        operand = (Integer) operand - (Integer) operand2;
                    }
                    SetHashTable(var[0], operand);
                    break;
                case "=":
                    SetHashTable(var[0], operand2);
                    break;
            }


        } else {
            switch (var[0]) {
                case "move":
                    System.out.println("Go to " + var[1]);
                    break;
                case "show":
                    System.out.println("Save value to " + var[1]);
                    break;
                case "hight":
                    System.out.println("Hight button " + var[1]);
                    break;


            }
        }
    }*/

   /* public void HappendParse(String StrHappend) {
        String rex = "(?:(move)(\\d+)|(\\[.*?\\])([+-=])(-?\\d+|\".*?\"|\\[.*?\\])|(show)\\[(.*?)\\]|(hight)(\\d+))";
        Pattern patern = Pattern.compile(rex);
        Matcher matcher = patern.matcher(StrHappend);
        String[] var = new String[3];
        int index = 0;
        while (matcher.find()) {
            System.out.println(matcher.group());
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i) != null) {
                    var[index] = matcher.group(i);
                    index++;
                }
            }
            changeVariables(var);
            var[2] = "";
            index = 0;
        }
    }*/


    public ButtonGame(String text) {
        enable = true;
        this.text = text;
        // textBlock=false;
        whatHappend = new ArrayList<>();
    }
}
