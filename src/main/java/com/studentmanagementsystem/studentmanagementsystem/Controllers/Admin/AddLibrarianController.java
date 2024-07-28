package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class AddLibrarianController {
    public TextField id;
    public TextField password;
    public TextField name;
    public TextField email;
    public TextField number;
    public TextField gender; // Added gender TextField
    public Button addbtn;

    //add button to add the librarian details
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

    String librarianData = String.format("%s,%s,%s,%s,%s\n", idText, nameText, emailText, passwordText, numberText);
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("librarian_login_info.csv", true))) {
        bw.write(librarianData);
        showAlert("Success", "Librarian added successfully.");
        clearFields();
    } catch (IOException e) {
        showAlert("Error", "Failed to save librarian data.");
        e.printStackTrace();
    }
}
// field clear garna lai after adding the libarian
    private void clearFields() {
        id.clear();
        name.clear();
        email.clear();
        password.clear();
        number.clear();
    }
    // alert message show gareako as it shows the alert message in a dialogue box
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}