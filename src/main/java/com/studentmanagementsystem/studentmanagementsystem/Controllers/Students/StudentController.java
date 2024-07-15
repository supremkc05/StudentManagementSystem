package com.studentmanagementsystem.studentmanagementsystem.Controllers.Students;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;

public class StudentController implements Initializable {

    public Button Dashboard_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button Report_btn;
    public Button Counselling_btn;
    public Button Extracurricular_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    public void councellingformclicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/Student/CouncellingForm.fxml", "Councelling Form");
    }
}
