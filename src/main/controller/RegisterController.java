package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dashboard.fxml"));

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
            User newUser = new User(Integer.toString(id), username.getText(), password.getText(), null);
            DataManager.getInstance().addUsersToDB(newUser);
            window.setScene(scene);

            DashboardController dashboardController = loader.<DashboardController>getController();
            dashboardController.setUsername(username.getText());
            
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