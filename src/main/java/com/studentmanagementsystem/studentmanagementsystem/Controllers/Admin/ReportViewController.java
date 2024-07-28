package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import com.studentmanagementsystem.studentmanagementsystem.Models.ReportModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class ReportViewController {
    @FXML
    public TableView<ReportModel> reportTable;
    @FXML
    public TableColumn<ReportModel, String> problemid;
    @FXML
    public TableColumn<ReportModel, String> idcol;
    @FXML
    public TableColumn<ReportModel, String> Stdname;
    @FXML
    public TableColumn<ReportModel, String> specifycol;
    @FXML
    public Button LogoutBtn;
    @FXML
    public Button backbtn;
    @FXML
    public TableColumn<ReportModel, String> problemtitle;

    private ObservableList<ReportModel> reportData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        problemid.setCellValueFactory(new PropertyValueFactory<>("problemId"));
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        Stdname.setCellValueFactory(new PropertyValueFactory<>("stdName"));
        problemtitle.setCellValueFactory(new PropertyValueFactory<>("problemtitle"));
        specifycol.setCellValueFactory(new PropertyValueFactory<>("specify"));

        loadReportData();
        reportTable.setItems(reportData);
    }

   private void loadReportData() {
    String csvFile = "reports.csv";
    String line;
    String csvSplitBy = ",";

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        while ((line = br.readLine()) != null) {
            String[] report = line.split(csvSplitBy);
            if (report.length == 5) {
                reportData.add(new ReportModel(report[0], report[1], report[2], report[3], report[4]));
            } else {
                // Handle the error or log it
                System.err.println("Invalid CSV format: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
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

    public void backbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminMenu.fxml", "Admin Menu");
    }
}