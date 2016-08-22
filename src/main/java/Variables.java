package main.java;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by arsen on 15.08.2016.
 */
public class Variables  implements Serializable{
     Map<String,Integer> things;
     Map<String,String> userVariable;
     Map<String,Integer> character;
     Map<String,Integer> people;
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
   public static Variables instantiate(){
        if(inst==null){
            inst=new Variables();
        }
        return  inst;
    }
  private   Variables(){
        things=new Hashtable<>();
        userVariable=new Hashtable<>();
        character=new Hashtable<>();
        people=new Hashtable<>();
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
