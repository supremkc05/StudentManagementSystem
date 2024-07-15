package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExtracurriculumController implements Initializable {
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public TextField name_fiedl;
    public TextField level_fld;
    public TextField team_members_fld;
    public Button register_btn;
    public TextField Studentid_fld;
    public Button otheraactivitiesbtn;
    public Button sportschoosebtn;

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
