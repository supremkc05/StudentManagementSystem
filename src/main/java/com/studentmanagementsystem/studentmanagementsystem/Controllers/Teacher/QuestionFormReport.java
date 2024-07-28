package com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher;

import com.studentmanagementsystem.studentmanagementsystem.Models.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class QuestionFormReport {
    @FXML
    public TableColumn<Question, String> name;
    @FXML
    public TableColumn<Question, String> id;
    @FXML
    public TableColumn<Question, String> que1;
    @FXML
    public TableView<Question> answertable;
    @FXML
    public TableColumn<Question, String> que2;
    @FXML
    public TableColumn<Question, String> que3;
    @FXML
    public TableColumn<Question, String> que4;
    @FXML
    public Button Dashboard_btn;
    @FXML
    public Button Counselling_btn;
    @FXML
    public Button profile_btn;
    @FXML
    public Button logout_btn;
    @FXML
    public Button Report_btn;

    @FXML
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        que1.setCellValueFactory(new PropertyValueFactory<>("que1"));
        que2.setCellValueFactory(new PropertyValueFactory<>("que2"));
        que3.setCellValueFactory(new PropertyValueFactory<>("que3"));
        que4.setCellValueFactory(new PropertyValueFactory<>("que4"));

        answertable.setItems(readCsvData());
    }

    private ObservableList<Question> readCsvData() {
        ObservableList<Question> questions = FXCollections.observableArrayList();
        String csvFile = "answers of question 1.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy, -1); // Use -1 to include trailing empty strings
                // Check if data array has the expected number of elements
                if (data.length == 6) {
                    Question question = new Question(data[0], data[1], data[2], data[3], data[4], data[5]);
                    questions.add(question);
                } else {
                    System.out.println("Skipped row with unexpected number of columns: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @FXML
    public void dashboardbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/TeacherDashboard.fxml", "Teacher Dashboard");
    }

    @FXML
    public void profilebtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/StaffProfile.fxml", "Profile");
    }

    @FXML
    public void logoutbtnClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            changeScene(event, "/Fxml/Login.fxml", "Login");
        }
    }

    @FXML
    public void Reportbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/TeacherReport.fxml", "Report/suggestion");
    }

    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/CouncellingReport.fxml", "Counselling Report");
    }
}