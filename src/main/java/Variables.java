package main.java;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by arsen on 15.08.2016.
 */
public class Variables implements Serializable {

    public List<Triple> variable;
    transient static List<Triple> options ;

        private transient static Variables inst;
    public Map<categories, String> titleName;

    public static enum categories {things, character, people, userVariable,constant}


    public  List<Triple> FindOptions(String str){

        List<Triple> l=new ArrayList<>();

        if (str == null || str.isEmpty()) {
            return l;
        }
        str=str.toLowerCase();
        for (Triple items : options) {
            if (items.getKey().toLowerCase().contains(str)) {
                //  s=((Map.Entry) item).getKey().toString();
                l.add(items);
            }
        }
        return  l;
    }


    public  Triple FindFullVarOrConst(String str){
       Triple ret=null;
        ret=GetFirst(str);
        if(ret==null){
            List<Triple> l=new ArrayList<>();
                    l.addAll(FindOptions(str));
            if(l.size()>0){
                return  l.get(0);
            }
        }
        return  ret;
    }
    public boolean IsConstWords(String string){

        String sbuf = string.toLowerCase();
        for (Triple item : options) {
            if (sbuf.equals(item.getKey())) {
                return true;

            }
        }
        return  false;
    }
    public  void Reset(){
        variable.clear();
    }
public  static boolean Load(File file, String nameFile){
    inst=file.<Variables>Read(nameFile);
    if(inst!=null){
        return true;
    }else{
        return  false;
    }
}
public  static  void Save(File file, String nameFile){
    file.<Variables>Write(inst,nameFile);
}

    public String GetTitleName(categories index) {
        return titleName.get(index);
    }

    public categories GetCategoryByName(String name){
        for(Map.Entry<categories,String> item:titleName.entrySet() ){
            if(item.getValue().equals(name)){
                return item.getKey();
            }
        }
        return  null;
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

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private Variables() {
        variable = new ArrayList<Triple>();
        options=new ArrayList<>();
        options.add(new Triple("ввод","0",categories.constant));
        options.add(new Triple("убрать","0",categories.constant));
        options.add(new Triple("перейти","0",categories.constant));

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


    public Triple GetEquals(String key){
        for(int i=0; i<variable.size();i++){
            if(variable.get(i).getKey().equals(key)){
                return variable.get(i);
            }
        }
        return  null;
    }

    public  Triple GetFirst(String key){
        List<Triple> list=Find(key);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public  boolean Remove(String str){
        for(int i=0; i<variable.size();i++){
            if(variable.get(i).getKey().equals(str)){
                variable.remove(i);
                return  true;
            }

        }
        return  false;
    }

    public List<Triple> Find(String key) {
        List<Triple> l = new ArrayList();
        if (key == null || key.isEmpty()) {
            return l;
        }



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
