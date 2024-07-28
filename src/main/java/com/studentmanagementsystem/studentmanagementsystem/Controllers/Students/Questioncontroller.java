package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
//import java.net.URL;
//import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class Questioncontroller {
    public Button submitbtn;
    public Button Report_btn;
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;

    public TextField answer1;
    public TextField answer2;
    public TextField answer3;
    public TextField answer4;
    public TextField name;
    public TextField id;

    @FXML
    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/CouncellingForm.fxml", "Councelling Form");
    }
    public void extracurriculumbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/Extracurriculum.fxml", "Extracurricular Activities");
    }
    public void dashboardBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/student.fxml", "Dashboard");
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


public void submitbtnclicked(ActionEvent event) {
    // Step 1: Collect answers
    String nameText = name.getText();
    String idText = id.getText();
    String answer1Text = answer1.getText();
    String answer2Text = answer2.getText();
    String answer3Text = answer3.getText();
    String answer4Text = answer4.getText();

    // Step 2: Create CSV line
    String csvLine = String.join(",",nameText,idText, answer1Text, answer2Text, answer3Text, answer4Text);

    // Step 3: Write to CSV
    String filePath = "answers of question 1.csv"; // Adjust the file path as needed
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
        bw.write(csvLine);
        bw.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Step 4: Clear text fields
    name.setText("");
    id.setText("");
    answer1.setText("");
    answer2.setText("");
    answer3.setText("");
    answer4.setText("");
}
}
