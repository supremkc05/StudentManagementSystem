package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.showAlert;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.openNewStage;


import com.studentmanagementsystem.studentmanagementsystem.Models.AdmissionOfficer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdmissionOfficerMgmt implements Initializable {
    @FXML
    public TableView<AdmissionOfficer> admissiontable;
    @FXML
    public TableColumn<AdmissionOfficer, String> idCol;
    @FXML
    public TableColumn<AdmissionOfficer, String> namecol;
    @FXML
    public TableColumn<AdmissionOfficer, String> emailcol;
//    @FXML
//    public TableColumn<AdmissionOfficer, String> passwordcol;
    @FXML
    public TableColumn<AdmissionOfficer, String> numbercol;
    public Button add;
    public Button delete;
    public Button backbtn;
    public Button LogoutBtn;
    public Button updatebtn;
    // Other class members...

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
//        passwordcol.setCellValueFactory(new PropertyValueFactory<>("password"));
        numbercol.setCellValueFactory(new PropertyValueFactory<>("number"));

        loadAdmissionOfficers();
    }

    private void loadAdmissionOfficers() {
        ObservableList<AdmissionOfficer> officers = FXCollections.observableArrayList();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("AO_login_info.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) { // Ensure there are enough data points
                    AdmissionOfficer officer = new AdmissionOfficer(data[0], data[1], data[2], data[4]);
                    officers.add(officer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        admissiontable.setItems(officers);
    }

    public void backbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminMenu.fxml", "Admin Menu");
    }

    public void addbtnclicked(ActionEvent event) throws IOException {
        openNewStage(event, "/Fxml/Admin/AdmissionOfficer.fxml", "Add Admission Officer");
    }

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
    public void deletebtnclicked(ActionEvent event) throws IOException {
    AdmissionOfficer selected = admissiontable.getSelectionModel().getSelectedItem();
    if (selected != null) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this admission officer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            admissiontable.getItems().remove(selected);
            showAlert("Success", "Admission Officer deleted successfully.");
        }
    } else {
        showAlert("Error", "No Admission Officer selected.");
    }
}

    public void updatebtnclicked(ActionEvent event) {
        loadAdmissionOfficers();
    }
}
