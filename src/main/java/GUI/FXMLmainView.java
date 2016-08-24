package main.java.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by arsen on 23.08.2016.
 */

public class FXMLmainView extends Application {


    @Override
    public  void start(Stage primaryStage) throws  Exception{
        primaryStage.setTitle("My Title");
        Pane myPane=(Pane) FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene myScene=new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
    public  static void main(String[] args){
        launch(args);


    }
}
