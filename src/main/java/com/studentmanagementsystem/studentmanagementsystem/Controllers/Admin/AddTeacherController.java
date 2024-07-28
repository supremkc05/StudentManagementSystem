package com.studentmanagementsystem.studentmanagementsystem.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.studentmanagementsystem.studentmanagementsystem.Uses.showAlert;

public class AddTeacherController {
    public TextField tid;
    public TextField tpass;
    public TextField teachername;
    public TextField teacheremail;
    public TextField number;
    public TextField faculty;
    public Button addbtn;


    public void addbuttonpressed(ActionEvent event) {
        String tidText = tid.getText().trim();
        String tpassText = tpass.getText().trim();
        String teachernameText = teachername.getText().trim();
        String teacheremailText = teacheremail.getText().trim();
        String numberText = number.getText().trim();
        String facultyText = faculty.getText().trim();

        if (tidText.isEmpty()  || teachernameText.isEmpty() || teacheremailText.isEmpty() || tpassText.isEmpty() || numberText.isEmpty() || facultyText.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }

        String teacherData = String.format("%s,%s,%s,%s,%s,%s\n", tidText, teachernameText, teacheremailText,tpassText, numberText, facultyText);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("teacher_login_info.csv", true))) {
            bw.write(teacherData);
            showAlert("Success", "Teacher added successfully.");
        } catch (IOException e) {
            showAlert("Error", "Failed to save Teacher data.");
            e.printStackTrace();
        }
        clearFields();
    }
    private void clearFields() {
    tid.clear();
    tpass.clear();
    teachername.clear();
    teacheremail.clear();
    number.clear();
    faculty.clear();
    }
}
