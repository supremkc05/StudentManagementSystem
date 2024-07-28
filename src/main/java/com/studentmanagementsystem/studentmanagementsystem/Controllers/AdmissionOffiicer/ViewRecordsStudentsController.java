package com.studentmanagementsystem.studentmanagementsystem.Controllers.AdmissionOffiicer;

import com.studentmanagementsystem.studentmanagementsystem.Models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ViewRecordsStudentsController implements Initializable {
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public TableView table;
    public Button registration;
    public Button records;
    @FXML
    public TableView<Student> studentstable;
    @FXML
    public TableColumn<Student, String> idCol;
    @FXML
    public TableColumn<Student, String> nameCol;
    @FXML
    public TableColumn<Student, String> emailCol;
    @FXML
    public TableColumn <Student, String> Faculty;
    @FXML
    public TableColumn<Student, String> gender;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        Faculty.setCellValueFactory(new PropertyValueFactory<>("Faculty"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        studentstable.setItems(readStudentsFromCSV("Login_info.csv"));
    }

    @FXML
    private ObservableList<Student> readStudentsFromCSV(String filePath) {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String line;
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(splitBy);
                if (studentData.length >= 6) {
                    Student student = new Student(studentData[0], studentData[1], studentData[2], studentData[4], studentData[5]);
                    students.add(student);
                } else {
                    System.out.println("Skipping line due to insufficient data: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    @FXML
    public void StudentRegistrationBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Registration.fxml", "Student Registration");
    }
    @FXML
    public void StudentrecordsBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/ViewRecords.fxml", "Student Records");
    }
   @FXML
    public void ProfileBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/AdmissionOfficer/AdmissionOfficerProfile.fxml", "Profile");
}

    @FXML
    public void ReportBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/AdmissionOfficer/Report.fxml", "Report/Suggestion");
}

    public void LogoutBtnClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

    if (action.isPresent() && action.get() == ButtonType.OK) {
        changeScene(event, "/Fxml/Login.fxml", "login");
    }
    }
}
