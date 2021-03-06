package main.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arsen on 18.08.2016.
 */
public class ParseIf {


  public   StringBuilder ConvertToPolandRecord(String ifText){
        Deque<Character> stack = new ArrayDeque<Character>();
        StringBuilder Sb = new StringBuilder("");
        char buf;
        String sign="&|";
        int startIndex;
        for (int i=0; i<ifText.length();i++){
            if(sign.indexOf(ifText.charAt(i))!=-1){
                stack.push(ifText.charAt(i));
                continue;
            }
            if (ifText.charAt(i) == '(') {
                stack.push('(');
                continue;
            } else if (ifText.charAt(i) == ')') {
                while (!stack.isEmpty() && (buf = stack.pop())!='(') {
                    Sb.append(buf);
                }
                continue;
            }
            Sb.append(ifText.charAt(i));
        }
        while (!stack.isEmpty()){
            Sb.append(stack.pop());
        }
        return  Sb;
    }



    //StrToInt
    static public Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //get value by key
  /* static public Object GetValueVariable(String key) {

        return Variables.instantiate().variable.;
    }*/

   /* private Object VariableToValue(String var) {
        Object IntStr = null;
        Integer buf = null;
        IntStr = tryParse(var);
        if (IntStr == null) {
            if (var.contains("\"")) {
                IntStr = var.substring(1, var.length() - 1);
            } else {
                IntStr = GetValueVariable(var);
                if ((buf = tryParse(IntStr.toString())) != null) {
                    IntStr = buf;
                }
            }
        }
        return IntStr;
    }*/

    //><=!
    private  Boolean Expression(String var1, String sing,String var2){
        if(var1==null || sing==null || var2==null){
            return  null;
        }
        String chars = ">=<=!";
        String buf;
        Object varObj1,varObj2;
        if(!chars.contains(sing)){
            System.out.println("Error sing in Expression");
            return null;
        }
        if(var1.indexOf("[")==0){
            var1=var1.substring(1,var1.length()-2);
        }
        var1= Variables.instantiate().GetValue(var1);
        if(var1==null){
            System.out.println("Error var1 not fount in Expression");
            return  null;
        }
        if(var2.indexOf("[")==0){
            var2=var1.substring(1,var2.length()-2);
        }
        buf=Variables.instantiate().GetValue(var2);
        if(buf!=null){
            var2=buf;
        }

        varObj1=tryParse(var1);
        if(varObj1==null){
            varObj1=var1;
        }
        varObj2=tryParse(var2);
        if(varObj2==null){
            varObj2=var2;
        }

        if(varObj1 instanceof  String){
            int res= ((String)varObj1).compareTo((String) varObj2);

            if (res>0 && (sing.equals(">") || sing.equals(">="))){
                return true;
            }
            if(res==0 && (sing.equals(">=") || sing.equals("<=") || sing.equals("="))){
                return true;
            }
            if(res<0 && (sing.equals("<") || sing.equals("<="))){
                return true;
            }
            if(sing.equals("!") && (res>0 || res<0) ){
                return  true;
            }
            return false;
        }
        if(varObj1 instanceof Integer){
            int v1=(Integer) varObj1;
            int v2=(Integer)varObj2;
            switch (sing){
                case "=":
                    return v1==v2;
                case ">":
                    return  v1>v2;
                case ">=":
                    return v1>=v2;
                case "<=":
                    return  v1<=v2;
                case "<":
                    return  v1<=2;
                case "!":
                    return  v1!=v2;
            }
        }
        return  null;
    }


  /*  private Boolean Expression(String[] var) {
        String chars = ">=<=!";
        String sign = "";
        String resVal = null;
        Integer buf;
        Integer first = null, second = null;
        String firstStr = null, secondStr = null;
        for (int i = 0; i < var.length; i++) {
            if (var[i] != null && chars.contains(var[i])) {

                sign = var[i];
                continue;
            }
            resVal = Variables.instantiate().Find(var[i]).get(0).getValue();
            buf= tryParse(resVal);
            if (first == null && firstStr == null) {

                if (buf==null) {
                    firstStr = resVal;
                } else {
                    first =buf;
                }
            } else {
                if (buf==null) {
                    secondStr =resVal;
                } else {
                    second = buf;
                }
            }

          *//*  if(first==null){
                first=tryParse(var[i]);
                if(first==null){
                    if(var[i].contains("\"")){
                       firstStr=var[i].substring(1,var[i].length()-2);
                    }else{
                        resVal=GetValueVariable(var[i]);
                        if(resVal instanceof String){
                            firstStr=resVal.toString();
                        }else {
                            first=(Integer)resVal;
                        }
                    }
                }
                continue;
            }*//*


          //  second=tryParse(var[i]);
            if(second!=null){
                continue;
            }else{
                second= tryParse(GetValueVariable(var[i]).toString());
            }
        }
        switch (sign) {
            case ">":
                return first > second;
            case "<":
                return first < second;
            case "=":
                if (firstStr != null && secondStr != null) {
                    return firstStr.equals(secondStr);

                } else if (first != null && second != null) {
                    return first == second;
                } else {
                    return null;
                }

            case "!":
                if (firstStr != null && secondStr != null) {
                    return !firstStr.equals(secondStr);

                } else if (first != null && second != null) {
                    return first != second;
                } else {
                    return null;
                }
            case ">=":
                return first >= second;
            case "<=":
                return first <= second;
            default:
                System.out.println("wrong sign " + sign);
                return null;
        }
    }*/

    private String[] SearchGroup(Matcher matcher) {
        String[] res = new String[3];
        int index = 0;
        for (int i = 2; i <= matcher.groupCount(); i++) {
            if (matcher.group(i) != null) {
                res[index] = matcher.group(i);
                index++;
            }
        }
        return res;
    }

    private Boolean ExpressionBool(Matcher matcher) {
        char sign = matcher.group(4).charAt(matcher.group(4).length() - 1);
        boolean first = false, second = false;
//        for(int i=0; i<matcher.groupCount();i++){
//            System.out.println(String.format("%2d %2s",i,matcher.group(i)));
//        }
        if (matcher.group(2).equals("true")) {
            first = true;
        }
        if (matcher.group(3).equals("true")) {
            second = true;
        }
        switch (sign) {
            case '|':
                return first || second;
            case '&':
                return first && second;
        }
        return null;
    }

    private Boolean RegularBool(StringBuilder text) {
        String regexp = "((true|false)(false|true)(\\||&))";
        StringBuilder Sb = text;
        Pattern pattern = Pattern.compile(regexp);
        int index = 0;
        Boolean bool = null;
        Matcher matcher;
        while (true) {
            matcher = pattern.matcher(Sb);
            if (!matcher.find()) {

                if (Sb.indexOf("true") == 0) {
                    return true;
                } else if (Sb.indexOf("false") == 0) {
                    return false;
                } else {
                    System.out.println("Error expression");
                    return null;
                }
            }
            do {
                index = Sb.indexOf(matcher.group());
                bool = ExpressionBool(matcher);
                if (bool == null) {
                    System.out.println("Error RegularBool");
                    return null;
                }
                // System.out.println(matcher.group());
                Sb = Sb.replace(index, index + matcher.group().length(), bool.toString());
            } while (matcher.find());
        }

    }

    public Boolean GetIf(String textIf) {
        //!!!Зачем проверка на IF в строке
/*
        if (textIf.length() <= 2 || !textIf.substring(0, 2).equals("if")) {
            return null;
        }
                                             .substring(2)*/
        String res = RegularExpression(textIf);


        return RegularBool(new StringBuilder(res));
    }

    //to view bool
    public String RegularExpression(String text) {
       // String regexp = "(-?\\d+)?(\".*?\")?(?:\\[(.*?)\\])?(>=|<=|[><=!])(?:\\[(.*?)\\])?(\".*?\")?(-?\\d+)?";

        String regexp =" (-?\\d+)?(\".*?\")?(?:\\[(.*?)\\])?(?:\\h*)?(>=|<=|[><=!])(?:\\h*)?(?:\\[(.*?)\\])?(\".*?\")?(-?\\d+)?";
        StringBuilder Sb = new StringBuilder(text);

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        // List<String>allMatches=new ArrayList<>();

        int index = 0;
        Boolean bool;
        String[] group = new String[3];

        while (matcher.find()) {
            //allMatches.add(matcher.group());

            if (matcher.group().equals("")) {
                continue;
            }
            index = Sb.indexOf(matcher.group());
            group=SearchGroup(matcher);
            bool = Expression(group[0],group[1],group[2]);
            if (bool == null) {
                return null;
            }

            Sb = Sb.replace(index, matcher.group().length() + index,
                    bool.toString());
        }

        return Sb.toString();
    }
}
