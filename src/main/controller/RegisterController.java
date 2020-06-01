package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.stage.*;
import model.Employee;
import model.Manager;
import model.User;
import model.DataManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXCheckBox managerCheckbox;

    public RegisterController() {
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        Parent register = FXMLLoader.load(getClass().getResource("../view/login.fxml"));

        Scene scene = new Scene(register);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleSignUp(ActionEvent event) throws IOException {
        ArrayList<User> users = DataManager.getInstance().getUsers();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/project.fxml"));

        Parent register = loader.load();

        Scene scene = new Scene(register);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        for (User user : users) {
            if (!isUsernameTaken(users, username.getText()) && password.getText().equals(confirmPassword.getText())) {
                if (managerCheckbox.isSelected()) {
                    Manager newManager = new Manager(username.getText(), password.getText());
                    DataManager.getInstance().addUsersToDB(newManager);
                } else {
                    Employee newEmployee = new Employee(username.getText(), password.getText());
                    DataManager.getInstance().addUsersToDB(newEmployee);
                }
                window.setScene(scene);

                ProjectController projectController = loader.<ProjectController>getController();

                window.show();
            }
        }

        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Register");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Username taken or password doesn't match");
        errorAlert.showAndWait();
    }

    private Boolean isUsernameTaken(ArrayList<User> users, String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return true;
            }
        }

        return false;
    }
}