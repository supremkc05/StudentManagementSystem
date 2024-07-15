package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;
import java.net.URL;

public class DashboardConrtoller implements Initializable {

    public Text user_name;
    public Label login_date;
    public Button course_contents;
    public Button theory_course_content;
    public AnchorPane announcement_page;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user_name.setText("Welcome, " + "Student");
    }
    //for date change according to system date
    public void setLogin_date(String date) {
        LocalDate currentDate = LocalDate.now(); // Get the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // Define a date format
        String formattedDate = currentDate.format(formatter); // Format the current date
        login_date.setText(formattedDate); // Set the formatted date as text of the login_date label

    }
}
