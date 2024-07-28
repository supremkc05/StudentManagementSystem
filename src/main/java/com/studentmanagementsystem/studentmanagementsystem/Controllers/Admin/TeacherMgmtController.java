package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import com.studentmanagementsystem.studentmanagementsystem.Models.Student;
import com.studentmanagementsystem.studentmanagementsystem.Models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.*;

public class TeacherMgmtController {
    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public Button LogoutBtn;
    @FXML
    public Button backbtn;
    @FXML
    public TableView<Teacher> teachertable;
    @FXML
    public TableColumn<Teacher, String> idCol;
    @FXML
    public TableColumn<Teacher, String> nameCol;
    @FXML
    public TableColumn<Teacher, String> emailcol;
//    @FXML
//    public TableColumn<Teacher, String> passwordcol;
    @FXML
    public TableColumn<Teacher, String> numbercol;
    @FXML
    public TableColumn<Teacher, String> facultyCol;
    public Button updatebtn;

    private ObservableList<Teacher> teachers = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("tid"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("teachername"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("teacheremail"));
//        passwordcol.setCellValueFactory(new PropertyValueFactory<>("tpass"));
        numbercol.setCellValueFactory(new PropertyValueFactory<>("number"));
        facultyCol.setCellValueFactory(new PropertyValueFactory<>("faculty"));

        loadTeachers(); // Load data into table
    }

    private void loadTeachers() {
        teachers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("teacher_login_info.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Teacher teacher = new Teacher(data[0], data[1], data[2], data[4], data[5]);
                    teachers.add(teacher);
                }
            }
            teachertable.setItems(teachers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminMenu.fxml", "Admin Menu");
    }

    public void addbtnclicked(ActionEvent event) throws IOException {
        openNewStage(event, "/Fxml/Admin/Addteacher.fxml", "Add teacher");
    }

    //logout ko alert message box banako
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

    // Inside TeacherMgmtController
    public void deletebtnclicked(ActionEvent event) {
        Teacher selected = (Teacher) teachertable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this teacher?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                teachers.remove(selected);
                updateCSV();
                showAlert("Success", "Teacher deleted successfully.");
            }
        } else {
            showAlert("Error", "No Teacher selected.");
        }
    }

    private void updateCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("teacher_login_info.csv"))) {
            for (Teacher teacher : teachers) {
                pw.println(String.format("%s,%s,%s,%s,%s,%s\n", teacher.getTid(), teacher.getTeachername(), teacher.getTeacheremail(), teacher.getNumber(), teacher.getFaculty()));
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to update the CSV file.");
            e.printStackTrace();
        }
    }

    public void updatebtnclicked(ActionEvent event) {
    teachertable.getItems().clear(); // Clear the table
    ObservableList<Teacher> teachers = readteacherfromCSV("teacher_login_info.csv"); // Corrected file name to match your CSV file for teachers
    teachertable.setItems(teachers);
    }
    private ObservableList<Teacher> readteacherfromCSV(String filePath) {
    ObservableList<Teacher> teachers = FXCollections.observableArrayList();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 6) { // Corrected to match the actual CSV format
                // Assuming the Teacher constructor does not need the password, skip the fourth element
                Teacher teacher = new Teacher(data[0], data[1], data[2], data[4], data[5]);
                teachers.add(teacher);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return teachers;
}
}
