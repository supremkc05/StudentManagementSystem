package com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class TeacherReportController implements Initializable {
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button profile_btn;
    public Button logout_btn;
    public TextField problemid;
    public Button submitbtn;
    public TextArea problembox;
    public TextField studentid;
    public TextField problemtitle;
    public TextField stdname;
    public Button Report_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        problemid.setText(generateProblemId());
    }

    private String generateProblemId() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder("PRB");

        // Generate two letters
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(letters.length());
            sb.append(letters.charAt(index));
        }

        // Generate four numbers
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(numbers.length());
            sb.append(numbers.charAt(index));
        }

        return sb.toString();
    }
    @FXML
    public void dashboardbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/TeacherDashboard.fxml", "TeacherDashboard");
    }

    public void profilebtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/StaffProfile.fxml", "Profile");
    }

    public void logoutbtnClicked(ActionEvent event) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

    if (action.isPresent() && action.get() == ButtonType.OK) {
        changeScene(event, "/Fxml/Login.fxml", "login");
    }
    }

    public void Reportbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/TeacherReport.fxml", "Report/Suggestion");
    }

    public void clickCounsellingForm(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/CouncellingReport.fxml", "Counselling Report");
    }

    public void onSubmitBtnClicked(ActionEvent event) {
         // Gather data from form fields
        String problemId = problemid.getText();
        String studentId = studentid.getText();
        String problemTitle = problemtitle.getText();
        String studentName = stdname.getText();
        String problemDescription = problembox.getText();

        // Save to CSV
        saveDetailsToCsv(problemId, studentId, problemTitle, studentName, problemDescription);
    }

    private void saveDetailsToCsv(String problemId, String studentId, String problemTitle, String studentName, String problemDescription) {
        String filePath = "reports.csv"; // Specify your CSV file path here
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.join(",", problemId, studentId, studentName,problemTitle, problemDescription);
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //fields lai clear garna
    problemid.setText("");
    studentid.setText("");
    problemtitle.setText("");
    stdname.setText("");
    problembox.setText("");

    problemid.setText(generateProblemId()); //feri naya problem id generate garne
    }
}

