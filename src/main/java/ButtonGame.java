package main.java; /**
 * Created by arsen on 15.08.2016.
 */
import javafx.beans.binding.StringBinding;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.util.regex.Matcher;

public class ButtonGame {
    public boolean enable;
    public  boolean textBlock;
    public String ifButtonExist;
    public List<Pair<String,String>> whatHappend;


 private    void SetHashTable(String var,Object val){
        char type=var.charAt(0);
        String key=var.substring(1,var.length());
        switch (type) {
            case 'T':

                 Variables.instantiate().things.put(key,(Integer)val);
                break;

            case 'U':
                 Variables.instantiate().userVariable.put(key,val.toString());
                break;

            case 'P':
                 Variables.instantiate().people.put(key,(Integer) val);
                break;
            case 'C':
                 Variables.instantiate().character.put(key,(Integer)val);
                break;
        }
    }

 private    void changeVariables(String[] var){

        Object operand,operand2;
        String key;
        if(var[0].indexOf('[')!=-1){
            operand= ParseIf.GetValueVariable(var[0]=var[0].substring(1,var[0].length()-1));
            if(var[2].indexOf('[')!=-1){
                operand2= ParseIf.GetValueVariable(var[2]=var[2].substring(1,var[2].length()-1));
            }else if(var[2].indexOf('\"')!=-1){
                operand2=var[2].substring(1,var[2].length()-1);
            }else{
                operand2=ParseIf.tryParse(var[2]);
            }

            if(operand2==null){
                System.out.println("Error operand2");
                return;
            }

            switch (var[1]){
                case "+":
                    if(operand instanceof String){
                        operand=((String)operand).concat((String)operand2);
                    }else{
                        operand=(Integer)operand+(Integer)operand2;
                    }
                    SetHashTable(var[0],operand);
                    break;
                case "-":
                    if(operand instanceof String){
                        operand= ((String) operand).replaceAll((String)operand2,"");
                    }else{
                        operand=(Integer)operand-(Integer)operand2;
                    }
                    SetHashTable(var[0],operand);
                    break;
                case "=":
                    SetHashTable(var[0],operand2);
                    break;
            }


        }else if(var[0].equals("move")){
            System.out.println("Go to "+var[1]);
        }
    }

  public   void HappendParse(String StrHappend){
        String rex="(?:(move)(\\d+)|(\\[.*?\\])([+-=])(-?\\d+|\".*?\"|\\[.*?\\]))";
        Pattern patern=Pattern.compile(rex);
        Matcher matcher=patern.matcher(StrHappend);
        String[] var=new String[3];
        int index=0;
        while (matcher.find()){
            System.out.println(matcher.group());
            for (int i=1; i<=matcher.groupCount();i++){
                if(matcher.group(i)!=null){
                    var[index]=matcher.group(i);
                    index++;
                }
            }
            changeVariables(var);
            var[2]="";
            index=0;
        }
    }


 public    ButtonGame(){
     whatHappend=new ArrayList<>();
    }
}
