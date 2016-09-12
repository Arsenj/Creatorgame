package main.java;

/**
 * Created by arsen on 10.09.2016.
 */
public class Triple {
    private String key;
    private  String value;
    private  Variables.categories index;

    public Triple(String key,String value,Variables.categories index){
        this.key=key;
        this.value=value;
        this.index=index;
    }

    @Override
    public String toString() {
        return String.format("key:\"%s\"  value:\"%s\"  index:%s",key,value,index);
    }


    public Variables.categories getIndex() {
        return index;
    }

    public void setIndex(Variables.categories index) {
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
