package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
public class DashboardConrtoller implements Initializable{
    @FXML
    private Text user_name;
    @FXML
    private Label login_date;
    @FXML
    private Button viewbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb){
    user_name.setText("Welcome");
    // Set the login date to the current date
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    login_date.setText("Today:" + currentDate.format(formatter));
}

    public void viewbtnclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/QuestionForm.fxml", "Questions");
    }
}
