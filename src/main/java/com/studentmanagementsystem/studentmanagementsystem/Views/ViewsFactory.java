package com.studentmanagementsystem.studentmanagementsystem.Views;

import com.studentmanagementsystem.studentmanagementsystem.Controllers.Students.StudentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewsFactory {
    //student views
    //private final StringProperty StudentSelectedMenuItem;
    private AnchorPane dashboardView;
    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Student/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    //for login
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }
    //for signup
    public void showSignupWindow() {
    try {
        AnchorPane signupPane = FXMLLoader.load(getClass().getResource("/Fxml/Signup.fxml")); // Load the FXML
        Scene scene = new Scene(signupPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Schoolsync");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/user-management.png")));
        stage.show();
        stage.setResizable(false); // Disable resizing
        //open in same window
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception to understand what went wrong
    }
}
//for student dashboard
    public void showStudentWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Student/student.fxml"));
        StudentController studentController = new StudentController();
        loader.setController(studentController);
        createStage(loader);
    }
      public void showAdmissionOfficerWindow() {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AdmissionOfficer/Registration.fxml"));
         createStage(loader);
    }

  private void createStage(FXMLLoader loader) {
    try {
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Schoolsync");
        //icon for the stage
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/user-management.png")));
        stage.show();
        stage.setResizable(false); // Disable resizing
    } catch (Exception e) {
        e.printStackTrace();        // Log the exception to understand what went wrong
    }
}
    public void closeStage(Stage stage){
        stage.close();
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminMenu.fxml"));
        createStage(loader);
    }

    public void showTeacherWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Teacher/TeacherDashboard.fxml"));
    createStage(loader);
    }

public void showLibrarianWindow() {
    // Correct the path if there's a typo or case sensitivity issue
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Libarian/LibrarianDashboard.fxml"));
    createStage(loader);
}
}
