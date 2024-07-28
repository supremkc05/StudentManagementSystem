package com.studentmanagementsystem.studentmanagementsystem;
import com.studentmanagementsystem.studentmanagementsystem.Controllers.ProfileController;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Uses {
    private String userEmail;

    public static Stage getCurrentStage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        return stage;
    }

    public static void changeScene(ActionEvent event, String sceneName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = getCurrentStage(event);
        stage.setTitle(title);
        stage.setScene(scene);
        // resizable close
        stage.setResizable(false);
    }

    public static void saveDataCSV(String[] data) throws IOException {
        // Validate that the data array has at least 2 elements (filename and at least one data point)
        if (data.length < 2) {
            throw new IllegalArgumentException("Data array must contain at least a filename and one data point.");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(data[0], true))) {
            // Dynamically build the CSV line from the data array, starting from index 1
            StringBuilder csvLine = new StringBuilder();
            for (int i = 1; i < data.length; i++) {
                csvLine.append(data[i]);
                if (i < data.length - 1) {
                    csvLine.append(",");
                }
            }
            bw.write(csvLine.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
}
public static <Parent> void openNewStage(ActionEvent event, String fxmlPath, String title) {
    try {
        FXMLLoader loader = new FXMLLoader(Uses.class.getResource(fxmlPath));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene((javafx.scene.Parent) root));
        newStage.setTitle(title);
        newStage.setResizable(false);
        newStage.setOnHiding(e -> {
            // Refresh the table or perform other actions when the stage is hiding
        });
        newStage.show();
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception, e.g., show an alert dialog
    }
}
    public static void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void showAlert(String title, String content) {
        showAlert(title, content, Alert.AlertType.INFORMATION);
    }
}
