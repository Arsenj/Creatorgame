package main.java;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by arsen on 15.08.2016.
 */
public class Variables implements Serializable {

    public List<Triple> variable;
    //    public Map<String, Integer> things;
//    public Map<String, String> userVariable;
//    public Map<String, Integer> character;
//    public Map<String, Integer> people;
    private transient static Variables inst;
    public Map<categories, String> titleName;

    public static enum categories {things, character, people, userVariable}


    public String GetTitleName(categories index) {
        return titleName.get(index);
    }

    /*
        public void addThing(String key,Integer value){
            things.put(key,value);
        }
        public void addVariable(String key,String value){
            userVariable.put(key,value);
        }
        public void addCharacter(String key,Integer value){
            character.put(key,value);
        }


        public int findInThings(String key){
            return things.get(key);
        }
        public String findInVariables(String key){
            return userVariable.get(key);
        }
        public Integer findInCharacters(String key){
            return character.get(key);
        }
        public Integer findInPeopls(String key){
            return people.get(key);
        }
    */

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

        //List<Pair<Character,Map>>  mapArr = new ArrayList<>();

//        mapArr.add(new Pair('T',things));
//        mapArr.add(new Pair('U',userVariable));
//        mapArr.add(new Pair('C',character));
//        mapArr.add(new Pair('P',people));

        String s;
        for (Triple items : variable) {
            if (items.getKey().toString().contains(key)) {
                //  s=((Map.Entry) item).getKey().toString();
                l.add(items);
            }
        }
        return l;
    }
//    public   Variables Read(String name){
//        FileInputStream fis;
//        ObjectInputStream ois;
//        try{
//            fis=new FileInputStream(new File(name));
//            ois=new ObjectInputStream(fis);
//            return  (Variables)ois.readObject();
//        }catch (FileNotFoundException e){
//
//        }catch (IOException e){
//
//        }catch (ClassNotFoundException e){
//
//        }
//        return  null;
//
//    }
//
//
//
//    public  void Write(String name,Variables var){
//        if(var==null){
//            return;
//        }
//        FileOutputStream fos;
//        ObjectOutputStream oos;
//        try {
//            fos=new FileOutputStream(new File(name));
//            oos=new ObjectOutputStream(fos);
//            oos.writeObject(var);
//        }catch (FileNotFoundException e){
//            System.out.println(e);
//        }catch (IOException e){
//            System.out.println(e);
//        }
//    }
}
