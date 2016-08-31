package main.java;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by arsen on 15.08.2016.
 */
public class Variables implements Serializable {
    public Map<String, Integer> things;
    public Map<String, String> userVariable;
    public Map<String, Integer> character;
    public Map<String, Integer> people;
    private transient static Variables inst;

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
        things = new Hashtable<>();
        userVariable = new Hashtable<>();
        character = new Hashtable<>();
        people = new Hashtable<>();
    }

    public List<Pair<Character,String>> Find(String key) {
        if(key==null || key.isEmpty()){
            return  null;
        }
        List<Pair<Character,String>> l = new ArrayList();

        List<Pair<Character,Map>>  mapArr = new ArrayList<>();

        mapArr.add(new Pair('T',things));
        mapArr.add(new Pair('U',userVariable));
        mapArr.add(new Pair('C',character));
        mapArr.add(new Pair('P',people));

        String s;
        for (Pair<Character,Map> items : mapArr) {
            for (java.lang.Object item : items.getValue().entrySet()) {
                {
                    if (((Map.Entry) item).getKey().toString().contains(key)) {
                        s=((Map.Entry) item).getKey().toString();

                        l.add(new Pair(items.getKey(),s));
                    }
                }
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
