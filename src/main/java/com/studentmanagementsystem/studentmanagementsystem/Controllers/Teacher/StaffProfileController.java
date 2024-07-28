package com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class StaffProfileController {
    public Label name;
    public Label phone;
    public Label id;
    public Label email;
    public Label faculty;
    @FXML
    private Button Counselling_btn;

    @FXML
    private Button Dashboard_btn;

    @FXML
    private Button Report_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button profile_btn;

    @FXML
    public void dashboardbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/TeacherDashboard.fxml", "Teacher Dashboard");
    }

    @FXML
    public void profilebtnclicked(ActionEvent event) throws IOException {
        // Corrected the file path by adding the .fxml extension
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
    @FXML
    public void clickCounsellingForm(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/CouncellingReport.fxml", "Counselling Report");
    }
}