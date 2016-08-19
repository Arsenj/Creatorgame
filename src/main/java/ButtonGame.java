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
    public List<Pair<String,List<String>>> whatHappend;


 public    ButtonGame(){
     whatHappend=new ArrayList<>();
    }
}
