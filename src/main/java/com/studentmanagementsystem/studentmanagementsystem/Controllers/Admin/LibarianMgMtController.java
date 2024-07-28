package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import com.studentmanagementsystem.studentmanagementsystem.Models.Libarian;
import com.studentmanagementsystem.studentmanagementsystem.Models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.openNewStage;

public class LibarianMgMtController implements Initializable {
    public TableView<Libarian>libariantable;
    public TableColumn<Libarian, String> idCol1;
    public TableColumn <Libarian, String>nameCol1;
    public TableColumn<Libarian, String> emailcol;
//    public TableColumn<Libarian, String> passwordcol;
    public TableColumn<Libarian, String> numbercol;
    public Button LogoutBtn;
    public Button add;
    public Button delete;
    public Button backbtn;
    public Button updatebtn;


    private ObservableList<Libarian> libarians = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        numbercol.setCellValueFactory(new PropertyValueFactory<>("number"));

        loadLibrariansFromCSV(); // Corrected method call
    }

    private void loadLibrariansFromCSV() {
        ObservableList<Libarian> librarians = FXCollections.observableArrayList();
        String filePath = "librarian_login_info.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Libarian libarian = new Libarian(data[0], data[1], data[2], data[3], data[4]);
                    librarians.add(libarian);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        libariantable.setItems(librarians); // Corrected variable name
    }

    public void backbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Admin/AdminMenu.fxml", "Admin Menu");
    }

    public void addbtnclicked(ActionEvent event) throws IOException {
        openNewStage(event, "/Fxml/Admin/Addlibrarian.fxml", "Add librarian");
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
        Libarian selected = (Libarian) libariantable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this librarian?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                libariantable.getItems().remove(selected);
                showAlert("Success", "Librarian deleted successfully.");
            }
        } else {
            showAlert("Error", "No librarian selected.");
        }
    }
//alert message dekhauna lai kaam lagxa hai
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void updatebtnclicked(ActionEvent event) {
        loadLibrariansFromCSV();
    }
}
