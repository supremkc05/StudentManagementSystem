package com.studentmanagementsystem.studentmanagementsystem.Controllers;

import com.studentmanagementsystem.studentmanagementsystem.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public TextField client_address_field;
    public TextField client_pass_field;
    public ChoiceBox account_selector;
    public Button login_btn;
    public Label error_label;
    public Button signup_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account_selector.getItems().addAll("Student", "Admin", "Teacher", "Librarian", "Admission Officer");
        login_btn.setOnAction(event -> onLogin());
         signup_btn.setOnAction(event -> onSignup());

    }
    private void onLogin() {
        String email = client_address_field.getText();
        String password = client_pass_field.getText();
        String accountType = (String) account_selector.getValue();

        if (email.isEmpty() || password.isEmpty() || accountType == null) {
            error_label.setTextFill(Color.RED);
            error_label.setText("All fields must be filled out.");
            return;
        }

        if (authenticate(email, password, accountType)) {
            error_label.setTextFill(Color.GREEN);
            error_label.setText("Login successful!");
            // Proceed to the next scene or dashboard
            Stage stage = (Stage) error_label.getScene().getWindow();
            Model.getInstance().getViewsFactory().closeStage(stage);
            Model.getInstance().getViewsFactory().showStudentWindow();
        } else {
            error_label.setTextFill(Color.RED);
            error_label.setText("Invalid credentials. Please try again.");
        }
    }

    private boolean authenticate(String email, String password, String accountType) {
        // Placeholder for actual authentication logic
        // Implement authentication logic here
        return "user@g.com".equals(email) && "password123".equals(password) && "Student".equals(accountType);
    }
   private void onSignup() {
        Stage stage = (Stage) signup_btn.getScene().getWindow(); // tei stage ma open garna lai
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showSignupWindow();


    }
}