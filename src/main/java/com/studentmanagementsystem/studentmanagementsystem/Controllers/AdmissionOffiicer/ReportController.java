package com.studentmanagementsystem.studentmanagementsystem.Controllers.AdmissionOffiicer;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ReportController implements Initializable {
    public TextArea problembox;
    public Button submitbtn;
    public TextField problemid;
    public TextField studentid;
    public TextField problemtitle;
    public TextField stdname;
    public Button registration;
    public Button records;
    public Button profile_btn;
    public Button logout_btn;


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
      public void StudentRegistrationBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Registration.fxml", "Student Registration");
    }

    public void StudentrecordsBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Viewrecords.fxml", "Student Records");
    }

    @FXML
    public void LogoutBtnClicked(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            changeScene(event, "/Fxml/Login.fxml", "login");
        }
    }

     @FXML
        public void ProfileBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/AdmissionOfficer/AdmissionOfficerProfile.fxml", "Profile");
    }

        @FXML
    public void ReportBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/AdmissionOfficer/Report.fxml", "Report/Suggestion");
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

