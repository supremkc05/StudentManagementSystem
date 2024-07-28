package com.studentmanagementsystem.studentmanagementsystem.Controllers.libarian;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddBooksController {
    public TextField bookid;
    public TextField booktitle;
    public TextField author;
    public TextField genre;
    public Button addbtn;
    public TextField status;

    public void Addbtnclicked(ActionEvent event) {
    String id = bookid.getText();
    String title = booktitle.getText();
    String authorName = author.getText();
    String bookGenre = genre.getText();
    String bookStatus = status.getText();

    if (id.isEmpty() || title.isEmpty() || authorName.isEmpty() || bookGenre.isEmpty() || bookStatus.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
        return;
    }

    String newBook = String.join(",", id, title, authorName, bookGenre, bookStatus);

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("addedbooks.csv", true))) {
        bw.write(newBook);
        bw.newLine(); // Ensure a new line is added after each entry
        bw.flush(); // Explicitly flush the stream to ensure all data is written
        showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully.");
        clearFields();
    } catch (IOException e) {
        showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the book.");
    }
}

    private void clearFields() {
        bookid.clear();
        booktitle.clear();
        author.clear();
        genre.clear();
        status.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
