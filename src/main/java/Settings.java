package main.java;

/**
 * Created by arsen on 23.09.2016.
 */
public class Settings {
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

    public String getStrucrGameFileName() {
        return strucrGameFileName;
    }

    public String getVariableFileName() {
        return variableFileName;
    }
}
