package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import com.studentmanagementsystem.studentmanagementsystem.Models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.Optional;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.openNewStage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentMgmtController implements Initializable {
    @FXML
    public TableView<Student> studentstable;
    @FXML
    public TableColumn<Student, String> idCol;
    @FXML
    public TableColumn<Student, String> nameCol;
    @FXML
    public TableColumn<Student, String> emailCol;
    @FXML
    public TableColumn<Student, String> gender;
    @FXML
    public TableColumn<Student, String> Faculty;
    public Button add;
    public Button delete;
    public Button LogoutBtn;
    public Button backbtn;
    public Button updatebtn;

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
    public void addbtnclicked(ActionEvent event) throws IOException {
        openNewStage(event, "/Fxml/Admin/AddStudent.fxml", "Add Student");
    }

    public void deletebtnclicked(ActionEvent event) {
        Student selected = studentstable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this student?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                studentstable.getItems().remove(selected);
                updateCSV("Login_info.csv", studentstable.getItems());
                showAlert("Success", "Student deleted successfully.");
            }
        } else {
            showAlert("Error", "No student selected.");
        }
    }

    private void updateCSV(String filePath, ObservableList<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                String line = String.join(",", student.getId(), student.getName(), student.getEmail(), student.getFaculty(), student.getGender());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update the CSV file.");
        }
    }

    private void appendToCSV(String filePath, Student student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.join(",", student.getId(), student.getName(), student.getEmail(), student.getFaculty(), student.getGender());
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to append to the CSV file.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            changeScene(event, "/Fxml/Login.fxml", "login");
        }
    }

    public void backbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminMenu.fxml", "Admin Menu");
    }

    public void updatebtnclicked(ActionEvent event)throws IOException {
    studentstable.getItems().clear();
    ObservableList<Student> students = readStudentsFromCSV("Login_info.csv");
    studentstable.setItems(students);
    }
}