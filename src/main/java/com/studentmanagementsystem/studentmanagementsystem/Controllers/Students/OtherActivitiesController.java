package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class OtherActivitiesController implements Initializable {
    public Button sportschoosebtn;
    public Button otheraactivitiesbtn;
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public TextField fullname;
    public TextField studentid;
    public TextField level;
    public CheckBox choose1;
    public CheckBox choose3;
    public CheckBox choose2;
    public Button registerbtn;
    public Label error;

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

    public void LogoutBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Login.fxml", "login");
    }

    public void ReportBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/ReportSuggestion.fxml", "Report/Suggestion");
    }

    public void ProfileBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Profile.fxml", "Profile");
    }

    @FXML
public void registerbtnclicked(ActionEvent event) {
    // Check for empty fields
    if (fullname.getText().isEmpty() || level.getText().isEmpty() || studentid.getText().isEmpty() ) {
        error.setText("All fields must be filled out.");
        error.setTextFill(Color.RED);
        return;
    }

    StringBuilder selectedActivities = new StringBuilder();
    if (choose1.isSelected()) selectedActivities.append(choose1.getText()).append(";");
    if (choose2.isSelected()) selectedActivities.append(choose2.getText()).append(";");
    if (choose3.isSelected()) selectedActivities.append(choose3.getText()).append(";");

    if (selectedActivities.length() == 0) {
        error.setText("You must select at least one activity.");
        error.setTextFill(Color.RED);
        return;
    }

    String[] data = {
        "extracurricular.csv",
        fullname.getText(),
        studentid.getText(),
        level.getText(),
        selectedActivities.toString() // Activities column
    };

    // Write to CSV file
    try {
        com.studentmanagementsystem.studentmanagementsystem.Uses.saveDataCSV(data);
        error.setText("Registration successful!");
        error.setTextFill(Color.GREEN);
        // Clear fields after successful registration
        clearForm();
    } catch (IOException e) {
        error.setText("Error writing to file.");
    }
}

private void clearForm() {
    fullname.setText("");
    studentid.setText("");
    level.setText("");
    choose1.setSelected(false);
    choose2.setSelected(false);
    choose3.setSelected(false);
}
}