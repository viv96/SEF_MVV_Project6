package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import model.*;

import java.io.IOException;
//TO REMOVE WHEN DELETING DUMMY DATA
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import enumerations.Status;
import enumerations.availability;

public class LoginController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    // Create dummy data at StartUp
    public LoginController() {
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<String> staff = new ArrayList<String>();

        DataManager.getInstance().addUsersToDB(new Employee("1", "vivek", "azerty123", "Developer", "1", "Experienced", availability.hundred));
        DataManager.getInstance().addUsersToDB(new Employee("2", "maxime", "azerty123", "Designer", "1", "Senior", availability.hundred));
        staff.add("1");
        staff.add("2");
        activities.add(new Activity("1", staff, Status.TO_DO, new Date(), new Date()));
        staff.clear();
        staff.add("1");
        activities.add(new Activity("2", staff, Status.PENDING, new Date(), new Date()));
        staff.clear();
        staff.add("2");
        activities.add(new Activity("3", staff, Status.PENDING, new Date(), new Date()));
        LocalDate start = LocalDate.of(2020, 05, 30);
        LocalDate end = LocalDate.of(2020, 06, 9);
        projects.add(new Project("1", "Web Development", Status.TO_DO, activities, start, end, 5));
        for (Project project : projects) {
            DataManager.getInstance().addProjectsToDB(project);
        }
    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        ArrayList<User> users = DataManager.getInstance().getUsers();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/project.fxml"));

        Parent register = loader.load();

        Scene scene = new Scene(register);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();


        for (User user : users) {
            if (user.getName().equals(username.getText()) && user.getPassword().equals(password.getText())) {
                window.setScene(scene);

                ProjectController projectController = loader.<ProjectController>getController();

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