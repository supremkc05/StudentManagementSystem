package com.studentmanagementsystem.studentmanagementsystem.Controllers.Teacher;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

import com.studentmanagementsystem.studentmanagementsystem.Models.CouncellingModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CouncellingReportController implements Initializable {

    public Text user_name;
    public Button Report_btn;
    public Button Dashboard_btn;
    public Button Counselling_btn;
    public Button profile_btn;
    public Button logout_btn;
    public TableView CouncellingReportsTable;
    public TableColumn namecol;
    public TableColumn idcol;
    public TableColumn problemcol;
    public TableColumn specifycol;


    private List<CouncellingModel> readCounsellingData() {
    List<CouncellingModel> counsellingData = new ArrayList<>();
    String path = "counselling_details.csv"; // Adjust the path as necessary
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            CouncellingModel model = new CouncellingModel(values[0], values[1], values[5], values[4]);
            counsellingData.add(model);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return counsellingData;
}


private void loadDataIntoTable() {
    List<CouncellingModel> counsellingData = readCounsellingData();

    ObservableList<CouncellingModel> data = FXCollections.observableArrayList(counsellingData);

    // Assuming namecol, idcol, problemcol, and specifycol are the TableColumn IDs for respective columns
    namecol.setCellValueFactory(new PropertyValueFactory<CouncellingModel, String>("name"));
    idcol.setCellValueFactory(new PropertyValueFactory<CouncellingModel, String>("id"));
    problemcol.setCellValueFactory(new PropertyValueFactory<CouncellingModel, String>("problem"));
    specifycol.setCellValueFactory(new PropertyValueFactory<CouncellingModel, String>("specify"));

    CouncellingReportsTable.setItems(data);
}
    @FXML
    public void dashboardbtnclicked(ActionEvent event) throws IOException{
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
        changeScene(event, "/Fxml/Teacher/TeacherReport.fxml", "Report/suggestion");
    }

    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Teacher/CouncellingReport.fxml", "Counselling Report");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            loadDataIntoTable();
    }
}

