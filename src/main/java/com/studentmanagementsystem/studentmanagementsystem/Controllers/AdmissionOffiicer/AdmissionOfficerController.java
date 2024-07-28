package com.studentmanagementsystem.studentmanagementsystem.Controllers.AdmissionOffiicer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
public class AdmissionOfficerController {
    public Button registration;
    public Button records;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public Label namelbl;
    public Label gmaillbl;
    public Label phone;
    public Label idlel;

    public void StudentRegistrationBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Registration.fxml", "Student Registration");
    }

    public void StudentrecordsBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Viewrecords.fxml", "Student Records");
    }

    @FXML
    public void ProfileBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/AdmissionOfficer/AdmissionOfficerProfile.fxml", "Profile");
}

    @FXML
    public void ReportBtnClicked(ActionEvent event) throws IOException {
    changeScene(event, "/Fxml/AdmissionOfficer/Report.fxml", "Report/Suggestion");
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
}
