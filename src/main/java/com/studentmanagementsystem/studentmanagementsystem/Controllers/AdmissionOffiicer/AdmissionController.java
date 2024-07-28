package com.studentmanagementsystem.studentmanagementsystem.Controllers.AdmissionOffiicer;

import com.studentmanagementsystem.studentmanagementsystem.Uses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ResourceBundle;
import static com.studentmanagementsystem.studentmanagementsystem.Uses.changeScene;
public class AdmissionController implements Initializable {
    public Button profile_btn;
    public Button registration;
    public Button records;
    public Button logout_btn;
    public Button Report_btn;
    public Button register;
    public TextField phonenumber_fiels;
    public ChoiceBox selector;
    public TextField firstname_field;
    public TextField lastname_field;
    public CheckBox checkbox1;
    public CheckBox checkbox;
    public CheckBox checkbox2;
    public TextField Stdid;
    public TextField email_field;
    public TextField password_fill;
    public TextField confirmpassword_fill;
    public Label errorconfirmpass;
    public Label error_lbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selector.getItems().addAll("BCA", "BIHM", "BBA", "BCS"); //course haru add gareako selector ma
        incrementAndDisplayStudentId();
    }

    private void incrementAndDisplayStudentId() {
         // the last student ID that was generated in the system
        int lastStudentId = 0;
        lastStudentId++; // Increment the ID
        Stdid.setText(String.valueOf(lastStudentId)); // Display the new ID in the TextField
    }

    private void resetErrorLabels() {
        error_lbl.setText("");
        errorconfirmpass.setText("");
    }

    private boolean validateInputs() {
        if (firstname_field.getText().isEmpty() || lastname_field.getText().isEmpty() ||
                phonenumber_fiels.getText().isEmpty() || email_field.getText().isEmpty() ||
                password_fill.getText().isEmpty() || confirmpassword_fill.getText().isEmpty() ||
                selector.getValue() == null) {
            error_lbl.setText("All fields must be filled out.");
            return false;
        }

        if (!email_field.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            error_lbl.setText("Invalid email format.");
            return false;
        }

        if (!password_fill.getText().equals(confirmpassword_fill.getText())) {
            errorconfirmpass.setText("Passwords do not match."); // Display the error message for password mismatch
            return false;
        }

        if (!checkbox.isSelected() && !checkbox1.isSelected() && !checkbox2.isSelected()) {
            error_lbl.setText("You must select at least one");
            return false;
        }

        return true;
    }

    @FXML
    public void StudentRegistrationBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Registration.fxml", "Student Registration");
    }

    @FXML
    public void StudentrecordsBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Viewrecords.fxml", "Student Records");
    }

    @FXML
    public void ProfileBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/AdmissionOfficerProfile.fxml", "Profile");
    }

    @FXML
    public void LogoutBtnClicked(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent() && action.get() == ButtonType.OK) {
            changeScene(event, "/Fxml/Login.fxml", "login");
        }
    }

    @FXML
    public void ReportBtnClicked(ActionEvent event) throws IOException {
        changeScene(event, "/Fxml/AdmissionOfficer/Report.fxml", "Report/Suggestion");
    }

    @FXML
    public void registerbtnclicked(ActionEvent event) throws IOException {
    resetErrorLabels();
    if (validateInputs()) {
        StringBuilder selectedCheckboxes = new StringBuilder();
        if (checkbox.isSelected()) selectedCheckboxes.append(checkbox.getText()).append(";");
        if (checkbox1.isSelected()) selectedCheckboxes.append(checkbox1.getText()).append(";");
        if (checkbox2.isSelected()) selectedCheckboxes.append(checkbox2.getText()).append(";");

        if (selectedCheckboxes.length() > 0) {
            selectedCheckboxes.setLength(selectedCheckboxes.length() - 1);
        }

        String hashedPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password_fill.getText().getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuffer.append(String.format("%02x", b));
            }
            hashedPassword = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            error_lbl.setText("Error encrypting password.");
            return;
        }

        //array banako jasma login_info.csv ma data save huncha
        String[] data = {"Login_info.csv", Stdid.getText(), firstname_field.getText(), email_field.getText(),
                hashedPassword, selector.getValue().toString(),
                selectedCheckboxes.toString()
        };
        Uses.saveDataCSV(data);
        System.out.println("Signup successful");
        changeScene(event, "/FXML/login.fxml", "Login");
        incrementAndDisplayStudentId();
    }
}

}
