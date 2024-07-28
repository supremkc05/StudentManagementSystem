package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


public class AddstudentController {
    public TextField stdid;
    public TextField password;
    public TextField stdname;
    public TextField email;
    public TextField gender;
    public Button addbtn;
    public TextField Faculty;

    @FXML
    private String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        Formatter formatter = new Formatter();
        for (byte b : digest) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}
public void addbuttonpressed(ActionEvent event) {
    System.out.println("Attempting to add new student details...");

    String id = stdid.getText().trim();
    String name = stdname.getText().trim();
    String emailText = email.getText().trim();
    String passwordText = hashPassword(password.getText().trim());
    String facultyText = Faculty.getText().trim();
    String genderText = gender.getText().trim();

    if (id.isEmpty() || name.isEmpty() || emailText.isEmpty() || passwordText == null || facultyText.isEmpty() || genderText.isEmpty()) {
        showAlert("Error", "All fields must be filled out, and password hashing must succeed.");
        return;
    }

    String studentData = String.format("%s,%s,%s,%s,%s,%s\n", id, name, emailText, passwordText, facultyText,genderText);

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Login_info.csv", true))) {
        bw.write(studentData);
        System.out.println("Successfully added: " + studentData);
        showAlert("Success", "Student added successfully.");
        clearFields();
    } catch (IOException e) {
        System.err.println("Failed to save student data: " + e.getMessage());
        showAlert("Error", "Failed to save student data.");
        e.printStackTrace();
    }
}
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        stdid.clear();
        stdname.clear();
        email.clear();
        password.clear();
        gender.clear();
        Faculty.clear();
    }
}