package com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class TeacherDashboardController implements Initializable {

    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public Text user_name;
    public Label login_date;
    public Button viewbtn;

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

    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/CouncellingReport.fxml", "Counselling Report");

    }

    public void viewbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/QuestionFormReport.fxml", "Question Form Report");
        {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the login date to the current date
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    login_date.setText("Today:" + currentDate.format(formatter));
    }
}
