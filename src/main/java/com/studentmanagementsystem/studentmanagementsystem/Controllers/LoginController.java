package com.studentmanagementsystem.studentmanagementsystem.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.studentmanagementsystem.studentmanagementsystem.App;

import java.io.*;
public class LoginController {
    public ChoiceBox<String> account_selector;
    public Label client_address_label;
    public TextField client_address_field;
    public Label client_pass_label;
    public TextField client_pass_field;
    public Button login_btn;
    public Label error_label;

    @FXML
    public void initialize() {
        account_selector.getItems().addAll("Student", "Admin","Teacher");
        account_selector.setValue("");
        error_label.setText("");
    }
//
//    private void writeCredentialsToCSV(String account, String address, String pass) {
//        try (FileWriter fw = new FileWriter("credentials.csv", true);
//             PrintWriter pw = new PrintWriter(fw)) {
//            pw.println(account + "," + address + "," + pass);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    @FXML
    public void login_btn_clicked(ActionEvent event) {
        String account = account_selector.getValue();
        String address = client_address_field.getText();
        String pass = client_pass_field.getText();

        boolean loginSuccessful = false;

        try (BufferedReader br = new BufferedReader(new FileReader("credentials.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(account) && credentials[1].equals(address) && credentials[2].equals(pass)) {
                    loginSuccessful = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (loginSuccessful) {
            error_label.setText(account + " login successful");

            // Write credentials to CSV
//            writeCredentialsToCSV(account, address, pass);

            Stage stage;
            Scene scene;
            try {
                if (account.equals("Student")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/Student/StudentMenu.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(fxmlLoader.load());
//                    stage.setFullScreen(true); //to open full screen
                    stage.setScene(scene);
                    stage.show();
                } else if (account.equals("Admin")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/Admin/AdminMenu.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(fxmlLoader.load());
                   // stage.setFullScreen(true); //to open full screen
                    stage.setScene(scene);
                    stage.show();
                }
                else if (account.equals("Teacher")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Fxml/Teacher/TeacherMenu.fxml"));
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            error_label.setText("Invalid credentials");
        }
    }

}
