package com.studentmanagementsystem.studentmanagementsystem.Controllers.libarian;

import com.studentmanagementsystem.studentmanagementsystem.Models.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.openNewStage;

public class LibrarianController {
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public Button addbtn;
    public Button deletebtn;
     @FXML
    public TableView<Books> booksTable;
    @FXML
    public TableColumn<Books, String> bookId;
    @FXML
    public TableColumn <Books, String> booktitle;
    @FXML
    public TableColumn <Books, String>status;
    @FXML
    public TableColumn <Books, String>author;
    @FXML
    public TableColumn <Books, String>genre;



    @FXML
    public void initialize() {
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        booktitle.setCellValueFactory(new PropertyValueFactory<>("bookname"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        booksTable.setItems(readCsvData());
    }

    private ObservableList<Books> readCsvData() {
        ObservableList<Books> books = FXCollections.observableArrayList();
        String csvFile = "addedbooks.csv";
        String line;
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                Books book = new Books(data[0], data[1], data[2], data[3], data[4]);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void deletebtnclicked(ActionEvent event) throws IOException {
        Books selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this book?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.isPresent() && action.get() == ButtonType.OK) {
                booksTable.getItems().remove(selectedBook);
                deleteBookFromCSV(selectedBook.getBookid());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a book in the table.");
            alert.showAndWait();
        }
    }

    private void deleteBookFromCSV(String bookId) throws IOException {
        List<String> out = Files.lines(Paths.get("addedbooks.csv"))
                .filter(line -> !line.contains(bookId))
                .collect(Collectors.toList());
        Files.write(Paths.get("addedbooks.csv"), out);
    }

     public void dashboardbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Libarian/LibrarianDashboard.fxml", "Dashboard");
    }

    public void profilebtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Libarian/librarianProfile.fxml", "Profile");
    }

    public void logoutbtnClicked(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            changeScene(event, "/Fxml/Login.fxml", "login");
        }
    }

    public void Reportbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Libarian/ReportLibarian.fxml", "Report/Suggestion");

    }
    public void addbtnclicked(ActionEvent event) throws IOException {
        openNewStage(event, "/Fxml/Libarian/AddBooks.fxml", "Add Books");
    }
}