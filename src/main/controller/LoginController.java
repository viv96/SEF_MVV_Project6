package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import model.DataManager;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    public LoginController() {
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        ArrayList<User> users = DataManager.getInstance().getUsers();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/dashboard.fxml"));

        Parent register = loader.load();

        Scene scene = new Scene(register);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();


        for (User user : users) {
            if (user.getName().equals(username.getText()) && user.getPassword().equals(password.getText())) {
                window.setScene(scene);

                DashboardController dashboardController = loader.<DashboardController>getController();
                dashboardController.setUsername(username.getText());

                window.show();
            }
        }

    }

    @FXML
    public void handleSignUp(ActionEvent event) throws IOException
    {
        Parent register = FXMLLoader.load(getClass().getResource("../view/register.fxml"));

        Scene scene = new Scene(register);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}