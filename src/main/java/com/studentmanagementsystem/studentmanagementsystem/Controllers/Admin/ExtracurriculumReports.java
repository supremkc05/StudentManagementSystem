package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import com.studentmanagementsystem.studentmanagementsystem.Models.Extracurricular;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ExtracurriculumReports {
    @FXML
    public TableView<Extracurricular> ExtracurricularTable;
    @FXML
    public TableColumn<Extracurricular, String> namecol;
    @FXML
    public TableColumn<Extracurricular, String> idcol;
    @FXML
    public TableColumn<Extracurricular, String> levelcol;
    @FXML
    public TableColumn<Extracurricular, String> sportscol;
    @FXML
    public TableColumn<Extracurricular, String> teaminfo;
    @FXML
    public TableColumn<Extracurricular, String> memberscol;
    public Button LogoutBtn;
    public Button backbtn;

    @FXML
    private void initialize() {
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        levelcol.setCellValueFactory(new PropertyValueFactory<>("level"));
        sportscol.setCellValueFactory(new PropertyValueFactory<>("sports"));
        teaminfo.setCellValueFactory(new PropertyValueFactory<>("teaminfo"));
        memberscol.setCellValueFactory(new PropertyValueFactory<>("members"));

        loadExtracurricularData();
    }

    private void loadExtracurricularData() {
        ObservableList<Extracurricular> data = FXCollections.observableArrayList();
        String csvFile = "extracurricular.csv"; // Update this path as necessary
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(Extracurricular.fromCsvLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtracurricularTable.setItems(data);
    }

    public void Logout(ActionEvent event) throws IOException {
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

    public void backbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminMenu.fxml", "Admin Menu");
    }
}
