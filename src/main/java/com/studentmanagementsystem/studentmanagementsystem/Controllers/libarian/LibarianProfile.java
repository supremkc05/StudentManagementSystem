package com.studentmanagementsystem.studentmanagementsystem.Controllers.libarian;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class LibarianProfile {
    public Button Dashboard_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public Label name;
    public Label phone;
    public Label id;
    public Label email;

    public void dashboardbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Libarian/LibrarianDashboard.fxml", "Dashboard");
    }

    public void profilebtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Libarian/librarianProfile.fxml", "Profile");
    }

    public void logoutbtnClicked(ActionEvent event) throws IOException{
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
        changeScene(event, "/Fxml/Libarian/ReportLibarian.fxml", "Report/Suggestion");

    }
}
