package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class CouncellingController  implements Initializable {

    public TextField fullname_field;
    public TextField student_id;
    public TextField phonenumber_fld;
    public TextField faculty_fld;
    public TextArea detailed_fld;
    public CheckBox academic_;
    public CheckBox MHC_issue;
    public CheckBox SR_issue;
    public CheckBox Career_guideness;
    public CheckBox personal_issue;
    public CheckBox other_fld;

    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
        public void extracurriculumbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/Extracurriculum.fxml", "Extracurricular");
    }

    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/CouncellingForm.fxml", "Councelling Form");
    }

    public void dashboardBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/student.fxml", "Dashboard");

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


