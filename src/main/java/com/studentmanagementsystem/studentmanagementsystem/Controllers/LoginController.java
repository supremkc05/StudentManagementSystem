package com.studentmanagementsystem.studentmanagementsystem.Controllers;

import com.studentmanagementsystem.studentmanagementsystem.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField client_address_field;
    public TextField client_pass_field;
    public ChoiceBox account_selector;
    public Button login_btn;
    public Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage= (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showStudentWindow();
    }
}
