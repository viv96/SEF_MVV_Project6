package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeeController {
    @FXML
    private Label usernameLabel;

    private String username;

    public void setUsername(String username) {
        usernameLabel.setText("Hi " + username);
        this.username = username;
    }
}
