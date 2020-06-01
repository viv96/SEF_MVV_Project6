package controller;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.DataManager;
import model.Employee;
import model.Skill;
import model.User;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<Employee> employeeTV;

    @FXML
    private TableColumn<Employee, String> idTC;

    @FXML
    private TableColumn<Employee, String> usernameTC;

    @FXML
    private TableColumn<Employee, ArrayList<Skill>> skillsTC;

    @FXML
    private TableColumn<Employee, ArrayList<String>> projects_idTC;

    @FXML
    private TableColumn<Employee, String> actionsTC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        skillsTC.setCellValueFactory(new PropertyValueFactory<>("skills"));
        projects_idTC.setCellValueFactory(new PropertyValueFactory<>("projects_id"));

        employeeTV.setItems(getEmployees());
    }

    public ObservableList<Employee> getEmployees() {
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        for (User user : DataManager.getInstance().getUsers()) {
            if (user instanceof Employee) {
                employees.add((Employee) user);
            }
        }

        return employees;
    }
}
