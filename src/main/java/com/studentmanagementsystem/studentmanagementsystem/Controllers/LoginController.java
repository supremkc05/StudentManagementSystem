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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@FXML
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
        Stage stage = (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewsFactory().closeStage(stage);

        // Redirect based on account type
        switch (accountType) {
            case "Student":
                Model.getInstance().getViewsFactory().showStudentWindow();
                break;
            case "Admin":
                Model.getInstance().getViewsFactory().showAdminWindow();
                break;
            case "Teacher":
                Model.getInstance().getViewsFactory().showTeacherWindow();
                break;
            case "Librarian":
                Model.getInstance().getViewsFactory().showLibrarianWindow();
                break;
            case "Admission Officer":
                Model.getInstance().getViewsFactory().showAdmissionOfficerWindow();
                break;
            default:
                error_label.setTextFill(Color.RED);
                error_label.setText("Invalid account type.");
                break;
        }
    } else {
        error_label.setTextFill(Color.RED);
        error_label.setText("Invalid credentials. Please try again.");
    }
}
    private boolean authenticate(String email, String password, String accountType) {
    System.out.println("Authenticating user...");
    if ("Admin".equals(accountType)) {
        // Direct comparison for Admin in plain text
        return "admin@admin.com".equals(email) && "adminpass".equals(password);
    }
    String filePath = getFilePathForAccountType(accountType);
    if (filePath == null) {
        System.out.println("Unsupported account type.");
        return false;
    }
    System.out.println("Reading from file: " + filePath);
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        String splitBy = ",";
        while ((line = br.readLine()) != null) {
            String[] userDetails = line.split(splitBy);
            if ("Student".equals(accountType)) {
                // For Student, hash the input password and compare
                String hashedInputPassword = hashPassword(password);
                if (userDetails.length > 3 && userDetails[2].equals(email) && userDetails[3].equals(hashedInputPassword)) {
                    return true;
                }
            } else {
                // For other account types, compare in plain text
                if (userDetails.length > 3 && userDetails[2].equals(email) && userDetails[3].equals(password)) {
                    return true;
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println(filePath + " file not found.");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("An error occurred while reading the file.");
        e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
        System.out.println("Error hashing the password.");
        e.printStackTrace();
    }
    return false;
}

private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashedBytes = md.digest(password.getBytes());
    StringBuilder stringBuffer = new StringBuilder();
    for (byte b : hashedBytes) {
        stringBuffer.append(String.format("%02x", b));
    }
    return stringBuffer.toString();
}
private String getFilePathForAccountType(String accountType) {
    switch (accountType) {
        case "Student":
            return "login_info.csv";
        case "Teacher":
            return "teacher_login_info.csv";
        // Add more cases for other account types as needed
        case "Admission Officer":
            return "AO_login_info.csv";
        case "Librarian":
            return "librarian_login_info.csv";
        default:
            return null;
    }
}
@FXML
   private void onSignup() {
        Stage stage = (Stage) signup_btn.getScene().getWindow(); // tei stage ma open garna lai
        Model.getInstance().getViewsFactory().closeStage(stage);
        Model.getInstance().getViewsFactory().showSignupWindow();
    }
}