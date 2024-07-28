package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

import java.io.IOException;
//import java.net.URL;
import java.util.Optional;
//import java.util.ResourceBundle;

public class ExtracurriculumController  {
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
    public Label errorlbl;
    public CheckBox choose5;
    public CheckBox choose4;
    public CheckBox choose3;
    public CheckBox choose2;
    public CheckBox choose1;
    public TextArea memberdetail;


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
//        changeScene(event, "/Fxml/Login.fxml", "login");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

    if (action.isPresent() && action.get() == ButtonType.OK) {
        changeScene(event, "/Fxml/Login.fxml", "login");
    }
    }
    public void ReportBtnClicked(ActionEvent event) throws IOException{
        changeScene(event, "/Fxml/Student/ReportSuggestion.fxml", "Report/Suggestion");
    }

    @FXML
public void registerbtnclicked(ActionEvent event) {
    // Check for empty fields
    if (name_fiedl.getText().isEmpty() || level_fld.getText().isEmpty() || team_members_fld.getText().isEmpty() || Studentid_fld.getText().isEmpty()) {
        errorlbl.setText("All fields must be filled out.");
        return;
    }

    // Concatenate selected sports
    StringBuilder selectedSports = new StringBuilder();
    if (choose1.isSelected()) selectedSports.append(choose1.getText()).append(";");
    if (choose2.isSelected()) selectedSports.append(choose2.getText()).append(";");
    if (choose3.isSelected()) selectedSports.append(choose3.getText()).append(";");
    if (choose4.isSelected()) selectedSports.append(choose4.getText()).append(";");
    if (choose5.isSelected()) selectedSports.append(choose5.getText()).append(";");

    // Check if at least one activity is selected
    if (selectedSports.length() == 0) {
        errorlbl.setText("You must select at least one activity.");
        return;
    }

    // Prepare data array for CSV
    String[] data = {
        "extracurricular.csv",
        name_fiedl.getText(),
        level_fld.getText(),
        Studentid_fld.getText(),
        selectedSports.toString(), // Sports column
        team_members_fld.getText(), // Team members column
        memberdetail.getText()
    };

    // Write to CSV file
    try {
        com.studentmanagementsystem.studentmanagementsystem.Uses.saveDataCSV(data);
        errorlbl.setText("Registration successful!");
        errorlbl.setTextFill(Color.GREEN);
        // Clear fields after successful registration
        clearFields();
    } catch (IOException e) {
        errorlbl.setText("Error writing to file.");
    }
}

private void clearFields() {
    name_fiedl.setText("");
    level_fld.setText("");
    team_members_fld.setText("");
    Studentid_fld.setText("");
    memberdetail.setText("");
    choose1.setSelected(false);
    choose2.setSelected(false);
    choose3.setSelected(false);
    choose4.setSelected(false);
    choose5.setSelected(false);
}
}
