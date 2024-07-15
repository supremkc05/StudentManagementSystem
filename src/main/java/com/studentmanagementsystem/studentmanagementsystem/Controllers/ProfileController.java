package com.studentmanagementsystem.studentmanagementsystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ProfileController implements Initializable {
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        }
   @FXML
    public void dashboardBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/Student/student.fxml", "Dashboard");
    }
    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/CouncellingForm.fxml", "Councelling Form");
    }

    public void extracurriculumbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/Extracurriculum.fxml", "Extracurricular Activities");
    }
    public void OtherActivitiesBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/OtherActivities.fxml", "Other Activities");
    }

    public void SportsBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/Extracurriculum.fxml", "Extracurricular Activities");
    }

    public void ProfileBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Profile.fxml", "Profile");
    }

     public void LogoutBtnClicked(ActionEvent event) throws IOException{
        changeScene(event, "/Fxml/Login.fxml", "login");
    }

    public void ReportBtnClicked(ActionEvent event) throws IOException{
        changeScene(event, "/Fxml/Student/ReportSuggestion.fxml", "Report/Suggestion");
    }
}