package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import model.Employee;
import model.User;
import model.DataManager;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

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

        if (users.size() == 0) {
            this.createUserAndSwitchScene(1, loader, scene, window);
        }

        if (!checkUsernameIsTaken(users)) {
            for (User user : users) {
                this.createUserAndSwitchScene(users.size(), loader, scene, window);
            }
        }
    }

    private void createUserAndSwitchScene(int id, FXMLLoader loader, Scene scene, Stage window) {
        if (password.getText().equals(confirmPassword.getText())) {
            Employee newEmployee = new Employee(Integer.toString(id), username.getText(), password.getText());
            DataManager.getInstance().addUsersToDB(newEmployee);
            window.setScene(scene);

            ProjectController projectController = loader.<ProjectController>getController();

            window.show();
        }
    }

    private boolean checkUsernameIsTaken (ArrayList<User> users) {
        for (User user : users) {
            if (user.getName().equals(username.getText())) {
                return true;
            }
        }

        return false;
    }
}