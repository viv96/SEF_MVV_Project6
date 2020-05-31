package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
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
    private TableColumn<Employee, String> skillsTC;

    @FXML
    private TableColumn<Employee, String> projects_idTC;

    @FXML
    private TableColumn<Employee, String> actionsTC;

    @FXML
    private TableColumn<Employee, String> skillsActionTC;

    @FXML
    private TableColumn<Employee, String> projectsActionTC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        skillsTC.setCellValueFactory(data -> new SimpleStringProperty(Arrays.toString(data.getValue().getSkills().toArray())));
        projects_idTC.setCellValueFactory(data -> new SimpleStringProperty(Arrays.toString(data.getValue().getProjectsID().toArray())));
        // Set to null because we need to set a value in order to use the column
        skillsActionTC.setCellValueFactory(new PropertyValueFactory<>(null));
        projectsActionTC.setCellValueFactory(new PropertyValueFactory<>(null));

        // Create and handle Skills button
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellSkillsAction
                = //
                new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Employee, String> param) {
                        final TableCell<Employee, String> cell = new TableCell<Employee, String>() {

                            final JFXButton btnSkills = new JFXButton("Add skills");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnSkills.setStyle("-fx-background-color:  #4059a9; -fx-border-color: white; -fx-text-fill: white");
                                    btnSkills.setOnAction(event -> {
                                        Employee employee = getTableView().getItems().get(getIndex());
                                        System.out.println(employee.getName()
                                                + "   " + employee.getId());
                                    });

                                    setGraphic(btnSkills);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        // Create and handle Projects button
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellProjectsAction
                = //
                new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Employee, String> param) {
                        final TableCell<Employee, String> cell = new TableCell<Employee, String>() {

                            final JFXButton btnProjects = new JFXButton("Add projects");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btnProjects.setStyle("-fx-background-color:  #4059a9; -fx-border-color: white; -fx-text-fill: white");
                                    btnProjects.setOnAction(event -> {
                                        Employee employee = getTableView().getItems().get(getIndex());
                                        System.out.println(employee.getName()
                                                + "   " + employee.getId());
                                    });
                                    setGraphic(btnProjects);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        skillsActionTC.setCellFactory(cellSkillsAction);
        projectsActionTC.setCellFactory(cellProjectsAction);

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
