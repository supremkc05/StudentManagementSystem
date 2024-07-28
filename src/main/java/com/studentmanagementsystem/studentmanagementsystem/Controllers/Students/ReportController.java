package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ReportController implements Initializable {

    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public TextField problemid;
    public TextField studentid;
    public TextField problemtitle;
    public TextField stdname;
    public TextArea problembox;
    public Button submitbtn;

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
        changeScene(event, "/Fxml/Login.fxml", "login");
    }

    public void ReportBtnClicked(ActionEvent event) throws IOException{
        changeScene(event, "/Fxml/Student/ReportSuggestion.fxml", "Report/Suggestion");
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
