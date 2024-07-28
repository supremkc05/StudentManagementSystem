package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

import java.io.IOException;
import java.util.Optional;

public class AdminMenuController {
    public Button LogoutBtn;
    public Button editStudents;
    public Button teachereditbtn;
    public Button EditAdmissionBtn;
    public Button councellingReportBtn;
    public Button reportsbtn;
    public Button libarianbtn;
    public Button extrabtn;

    public void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

    if (action.isPresent() && action.get() == ButtonType.OK) {
        changeScene(event, "/Fxml/Login.fxml", "login");
    }
        
    }

    public void EditAdmissionOfficerBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdmissionOfficerMgmt.fxml", "Edit Admission Officer");
    }

    public void CouncellingreportbtnClickced(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/Councellingreport.fxml", "Councelling Report");
    }

    public void extarbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ExtracurricularView.fxml", "Extracurricular Activities");
    }

    public void libarianbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/LibarianMgmt.fxml", "Libarian View");
    }

    public void reportbtnlicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/ReportSuggestionView.fxml", "Report View");
    }

    public void teacherbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/Teachermgmt.fxml", "Teacher View");
    }

    public void editstudentBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/Student management.fxml", "Edit Student");

    }
}
