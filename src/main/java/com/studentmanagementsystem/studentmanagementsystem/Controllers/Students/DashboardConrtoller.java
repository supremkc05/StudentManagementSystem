package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ResourceBundle;
import java.net.URL;

public class DashboardConrtoller implements Initializable{

    public Text user_name;
    public Label login_date;
    public Button course_contents;
    public Button theory_course_content;
    public AnchorPane announcement_page;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        user_name.setText("Welcome, " + "Student");}
}
