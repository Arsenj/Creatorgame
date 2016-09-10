package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arsen on 09.09.2016.
 */
public class IfThen {
    private String ifString;
    private List<Then> thenList;


    public IfThen(String ifString,List<Then> thenList){
      this.thenList=new ArrayList<Then>();
        this.ifString=ifString;
        this.thenList.addAll(thenList);
    }

    public List<Then> getThenList() {
        return thenList;
    }

    public String getIfString() {
        return ifString;
    }
}
