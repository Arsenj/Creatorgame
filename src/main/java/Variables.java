package main.java;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by arsen on 15.08.2016.
 */
public class Variables implements Serializable {

    public List<Triple> variable;
        private transient static Variables inst;
    public Map<categories, String> titleName;

    public static enum categories {things, character, people, userVariable,constant}


    public boolean IsConstWords(String string){
        String[] options = {"move", "hight", "show"};
        String sbuf = string.toLowerCase();
        for (String item : options) {
            if (sbuf.equals(item)) {
                return true;

            }
        }
        return  false;
    }

    public String GetTitleName(categories index) {
        return titleName.get(index);
    }

    public List<Triple> GetCategories(categories categ){
        List<Triple> l=new ArrayList<>();

        Variables.instantiate().variable.forEach(triple -> {
            if(triple.getIndex()==categ){
                l.add(triple);
            }
        });

        return l;
    }

    public static Variables instantiate() {
        if (inst == null) {
            inst = new Variables();
        }
        return inst;
    }

    private Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private Variables() {
        variable = new ArrayList<Triple>();
        titleName = new Hashtable<>();
        titleName.put(categories.things, "Вещи");
        titleName.put(categories.character, "Характеристики");
        titleName.put(categories.people, "Люди");
        titleName.put(categories.userVariable, "Пользовательские");
    }

    ;


    public  String GetValue(String key) {
        for (Triple items : variable) {
            if (items.getKey().equals(key)) {
                return items.getValue();
            }
        }
        ;
        return null;
    }
//        things = new Hashtable<>();
//        userVariable = new Hashtable<>();
//        character = new Hashtable<>();
//        people = new Hashtable<>();


    public  Triple GetFirst(String key){
        List<Triple> list=Find(key);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public List<Triple> Find(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        List<Triple> l = new ArrayList();


        String s;
        for (Triple items : variable) {
            if (items.getKey().toString().contains(key)) {
                //  s=((Map.Entry) item).getKey().toString();
                l.add(items);
            }
        }
        return l;
    }

}
