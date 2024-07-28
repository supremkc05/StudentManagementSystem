package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.showAlert;

public class AddAdmissionOfficerController {
    public TextField id;
    public TextField password;
    public TextField name;
    public TextField email;
    public TextField number;
    public Button addbtn;

   public void addbuttonpressed(ActionEvent event) {
    String idText = id.getText().trim();
    String nameText = name.getText().trim();
    String emailText = email.getText().trim();
    String passwordText = password.getText().trim();
    String numberText = number.getText().trim();

    if (idText.isEmpty() || nameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || numberText.isEmpty()) {
        showAlert("Error", "All fields must be filled out.");
        return;
    }

    // Ensure each entry is on a new line
    String officerData = String.format("%s,%s,%s,%s,%s\n", idText, nameText, emailText, passwordText, numberText);

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("AO_login_info.csv", true))) {
        bw.write(officerData); // Writes the data on a new line
        showAlert("Success", "Admission Officer added successfully.");
        clearFields();
    } catch (IOException e) {
        showAlert("Error", "Failed to save Admission Officer data.");
        e.printStackTrace();
    }
}

    private void clearFields() {
        id.clear();
        name.clear();
        email.clear();
        password.clear();
        number.clear();
    }
}