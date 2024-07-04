package com.studentmanagementsystem.studentmanagementsystem;

import com.studentmanagementsystem.studentmanagementsystem.Models.Model;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;

//import java.util.Objects;

public class App extends Application {
    @Override

    public void start(Stage stage){
        Model.getInstance().getViewsFactory().showLoginWindow();
    }
}
