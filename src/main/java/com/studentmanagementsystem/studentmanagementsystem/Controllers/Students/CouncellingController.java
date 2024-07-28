package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class CouncellingController implements Initializable {

    @FXML
    public Button Dashboard_btn, Counselling_btn, Extracurricular_btn, profile_btn, logout_btn, Report_btn, submitbtn;
    @FXML
    public TextField fullname, stdid, phnnumber, faculty;
    @FXML
    public TextArea detailedfld;
    @FXML
    public Label errorlbl;
    @FXML
    public ChoiceBox<String> problemchoice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        problemchoice.setItems(FXCollections.observableArrayList(
                "Academic", "Mental Health", "Student Relations", "Career Guidance", "Personal", "Other"
        ));
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

    public void LogoutBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Login.fxml", "login");
    }

    public void ReportBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/ReportSuggestion.fxml", "Report/Suggestion");
    }


    private boolean validateInputs() {
        if (fullname.getText().trim().isEmpty() || stdid.getText().trim().isEmpty() ||
            phnnumber.getText().trim().isEmpty() || faculty.getText().trim().isEmpty() ||
            detailedfld.getText().trim().isEmpty() || problemchoice.getValue() == null) {
            errorlbl.setText("All fields must be filled out.");
            return false;
        }
        if (phnnumber.getText().length() != 10) {
            errorlbl.setText("Phone number must be 10 digits long.");
            return false;
        }
        //student id  must not be empty
        if (stdid.getText().isEmpty()) {
            errorlbl.setText("Student ID must not be empty.");
            return false;
        }
        return true;
    }

public void submitbuttonclick(ActionEvent event) {
    // Check if an item is selected in problemchoice. If not, display an error and return.
    if (problemchoice.getSelectionModel().getSelectedItem() == null) {
        errorlbl.setTextFill(javafx.scene.paint.Color.RED);
        errorlbl.setText("Please select an issue before submitting.");
        return; // Stop execution
    }

    String issue = problemchoice.getSelectionModel().getSelectedItem().toString();

    // Prepare the data to be saved
    String[] data = {
        "counselling_details.csv", // CSV file path
        fullname.getText(),
        stdid.getText(),
        phnnumber.getText(),
        faculty.getText(),
        detailedfld.getText(),
        "\"" + issue + "\""
    };

    try {
        com.studentmanagementsystem.studentmanagementsystem.Uses.saveDataCSV(data);
        // Display success message
        errorlbl.setTextFill(javafx.scene.paint.Color.GREEN); // Set the text color to green
        errorlbl.setText("Form submitted successfully.");
        clearFormFields();
    } catch (IOException e) {
        // In case of an error during saving, display an error message
        errorlbl.setTextFill(javafx.scene.paint.Color.RED); // Set the text color to red for errors
        errorlbl.setText("Failed to save data. Please try again.");
        e.printStackTrace();
    }
    clearFormFields();
}
private void clearFormFields() {
    fullname.clear();
    stdid.clear();
    phnnumber.clear();
    faculty.clear();
    detailedfld.clear();
    problemchoice.getSelectionModel().clearSelection();
    }
}