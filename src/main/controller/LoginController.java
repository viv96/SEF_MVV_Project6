package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import enumerations.Competency;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.stage.*;
import model.*;

import java.io.IOException;
//TO REMOVE WHEN DELETING DUMMY DATA
import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import enumerations.Status;
import enumerations.availability;

public class LoginController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    // Create dummy data at StartUp
    public LoginController() {
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd"); //the date stored in the project class must be stored in yyyy-MMM-dd format.
        formatter = formatter.withLocale(Locale.ENGLISH);
        ArrayList<Activity> activities = new ArrayList<Activity>();
        ArrayList<String> staff = new ArrayList<String>();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        ArrayList<User> userList = new ArrayList<User>();
        ArrayList<Project> projectList = new ArrayList<Project>();
        ArrayList<String> projectId = new ArrayList<String>();

        userList = DataManager.getInstance().getUsers();
        projectList = DataManager.getInstance().getProjects();
        for (User user : userList) {
            System.out.println(user);
        }
        for (Project project : projectList) {
            System.out.println(project);
        }
        skills.add(new Skill("Python", Competency.SIX));
        skills.add(new Skill("Java", Competency.SIX));
        projectId.add("1");
        DataManager.getInstance().addUsersToDB(new Employee("vivek", "azerty123", skills, projectId, availability.hundred));
        skills.clear();
        skills.add(new Skill("After Effect", Competency.TWO));
        DataManager.getInstance().addUsersToDB(new Employee("maxime", "azerty123", skills, projectId, availability.hundred));
        staff.add("1");
        staff.add("2");
        skills.clear();
        skills.add(new Skill("Python", Competency.FIVE));
        skills.add(new Skill("After Effect", Competency.THREE));
        skills.add(new Skill("Java", Competency.SIX));
        LocalDate start = LocalDate.of(2020, 06, 1);
        LocalDate end = LocalDate.of(2020, 06, 10);
        start.format(formatter);
        end.format(formatter);
        activities.add(new Activity("Setting up project", "Awesome", 2, staff, start, end, availability.forty, null, skills));
        staff.clear();
        staff.add("1");
        start = LocalDate.of(2020, 06, 11);
        end = LocalDate.of(2020, 06, 20);
        skills.clear();
        skills.add(new Skill("Python", Competency.FIVE));
        skills.add(new Skill("After Effect", Competency.TWO));
        activities.add(new Activity("Working on Front-end and back-end", "Wow", 2, staff, start, end, availability.eighty, null, skills));
        staff.clear();
        staff.add("2");
        start = LocalDate.of(2020, 06, 21);
        end = LocalDate.of(2020, 06, 30);
        skills.clear();
        skills.add(new Skill("Python", Competency.TEN));
        skills.add(new Skill("After Effect", Competency.TWO));
        activities.add(new Activity("Working on Front-end", "Yes baby", 2, staff, start, end, availability.twenty, null, skills));
        start = LocalDate.of(2020, 06, 1);
        end = LocalDate.of(2020, 06, 30);
        DataManager.getInstance().addProjectsToDB(new Project("Web Development", "Amazing", Status.TO_DO, activities, start, end));
        for (User user : DataManager.getInstance().getUsers()) {
            if (user instanceof Employee) {
                ((Employee) user).setCalendar();
            }
        }*/
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
                //Init UserSession at login
                UserSession.getInstance().setUser(user);

                window.show();
                return ;
            }
        }

        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Login");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Username / password doesn't match");
        errorAlert.showAndWait();
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