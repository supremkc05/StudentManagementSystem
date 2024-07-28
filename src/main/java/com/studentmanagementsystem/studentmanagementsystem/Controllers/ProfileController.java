package com.studentmanagementsystem.studentmanagementsystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ProfileController implements Initializable {
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public Label namelbl;
    public Label idlbl;
    public Label facultylbl;
    public Label genderlbl;
    public Label gmaillbl;


    private String userEmail;

    public void setUserEmail(String email) {
        this.userEmail = email;
        updateUserDetails();
    }

    private void updateUserDetails() {
        Map<String, String> userDetails = CSVReader.getUserDetails(userEmail);
        if (!userDetails.isEmpty()) {
            namelbl.setText(userDetails.get("name"));
            idlbl.setText(userDetails.get("id"));
            facultylbl.setText(userDetails.get("faculty"));
            genderlbl.setText(userDetails.get("gender"));
            gmaillbl.setText(userDetails.get("email"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        }
    @FXML
    public void dashboardBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/Student/student.fxml", "Dashboard");
    }
    @FXML
    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/CouncellingForm.fxml", "Councelling Form");
    }
    @FXML
    public void extracurriculumbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/Extracurriculum.fxml", "Extracurricular Activities");
    }
    @FXML
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

public class CSVReader {
    public static Map<String, String> getUserDetails(String email) {
        String filePath = "C:\\Users\\supre\\Desktop\\javafx\\StudentManagementSystem\\Login_info.csv"; // Adjust the path as necessary
        String line;
        String splitBy = ",";
        Map<String, String> userDetails = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] user = line.split(splitBy);
                if (user[2].equals(email)) {
                    userDetails.put("id", user[0]);
                    userDetails.put("name", user[1]);
                    userDetails.put("email", user[2]);
                    userDetails.put("faculty", user[4]);
                    userDetails.put("gender", user[5]);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDetails;
    }
}
}
