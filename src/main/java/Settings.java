package main.java;

import java.io.Serializable;

/**
 * Created by arsen on 23.09.2016.
 */
public class Settings implements Serializable {
    private String nameGame;
    private  String variableFileName;
    private  String strucrGameFileName;

public Settings(String nameGame){
    setNameGame(nameGame);

}

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
        variableFileName="V"+nameGame;
        strucrGameFileName="S"+nameGame;
    }

    public String getNameGame() {
        return nameGame;
    }
    public String getFileNameGame() {
        return nameGame;
    }

    public String getStrucrGameFileName() {
        return strucrGameFileName;
    }

    public String getVariableFileName() {
        return variableFileName;
    }
}
