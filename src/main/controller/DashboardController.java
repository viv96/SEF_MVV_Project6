package controller;

import javafx.fxml.*;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label usernameLabel;

    private String username;

    public void setUsername(String username) {
        usernameLabel.setText("Welcome " + username);
        this.username = username;
    }

    public DashboardController() {
    }
}
