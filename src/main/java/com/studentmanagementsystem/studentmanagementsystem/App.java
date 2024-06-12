package com.studentmanagementsystem.studentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/login.fxml"));
        Scene scene = new Scene((fxmlLoader.load()));

        //Image icon = new Image(Objects.requireNonNull(App.class.getResourceAsStream("/Images/icon.png")));
        //stage.getIcons().add(icon);



        stage.setTitle("Schoolsync");
        stage.setScene(scene);
        //stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();

    }
}