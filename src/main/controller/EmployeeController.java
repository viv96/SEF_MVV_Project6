package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import enumerations.Competency;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private StackPane stackPane;

    @FXML
    private Pane paneEditSkills;

    @FXML
    private Pane paneEditProjects;

    @FXML
    private Pane paneTableView;

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

    @FXML
    private JFXTextField skillTF;

    @FXML
    private JFXComboBox<Competency> levelCB;

    @FXML
    private JFXButton submitAddSkills;

    @FXML
    private JFXComboBox<String> projectCB;

    @FXML
    private JFXButton submitAddProjects;

    private Integer employeeIdx;

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
                                        if (UserSession.getInstance().isManager()) {
                                            paneEditSkills.setVisible(true);
                                            paneEditProjects.setVisible(false);
                                            paneTableView.setVisible(false);
                                            employeeIdx = getIndex();
                                        } else {
                                            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                                            errorAlert.setTitle("Employee");
                                            errorAlert.setHeaderText(null);
                                            errorAlert.setContentText("You're not a Manager, access refused!");
                                            errorAlert.showAndWait();
                                        }
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
                                        if (UserSession.getInstance().isManager()) {
                                            paneEditSkills.setVisible(false);
                                            paneEditProjects.setVisible(true);
                                            paneTableView.setVisible(false);
                                            employeeIdx = getIndex();
                                        } else {
                                            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                                            errorAlert.setTitle("Employee");
                                            errorAlert.setHeaderText(null);
                                            errorAlert.setContentText("You're not a Manager, access refused!");
                                            errorAlert.showAndWait();
                                        }
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

        levelCB.getItems().addAll(
                Competency.ONE,
                Competency.TWO,
                Competency.THREE,
                Competency.FOUR,
                Competency.FIVE,
                Competency.SIX,
                Competency.SEVEN,
                Competency.EIGHT,
                Competency.NINE,
                Competency.TEN
        );

        for (Project project : DataManager.getInstance().getProjects()) {
            projectCB.getItems().add(project.getProjectID());
        }

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

    @FXML
    private void handleSubmitAddProjects(ActionEvent event) throws IOException {
        Employee employee = employeeTV.getItems().get(employeeIdx);

        String project_id = projectCB.getSelectionModel().getSelectedItem();

        // Set projects_id to that Employee
        if (project_id != null) {
            employee.setProjectsID(project_id);

            //Update that employee in our Database
            DataManager.getInstance().addUsersToDB(employee);
        }

        //Refresh TableView
        employeeTV.refresh();

        // Switch Pane
        paneEditSkills.setVisible(false);
        paneEditProjects.setVisible(false);
        paneTableView.setVisible(true);
    }

    @FXML
    private void handleSubmitAddSkills(ActionEvent event) throws IOException {
        Employee employee = employeeTV.getItems().get(employeeIdx);

        String skill = skillTF.getText();
        Competency competency = levelCB.getSelectionModel().getSelectedItem();

        if (skill != null && competency != null) {
            // Set skill to that Employee
            employee.setSkills(new Skill(skill, competency));

            //Update that employee in our Database
            DataManager.getInstance().addUsersToDB(employee);
        }

        //Refresh TableView
        employeeTV.refresh();

        // Switch Pane
        paneEditSkills.setVisible(false);
        paneEditProjects.setVisible(false);
        paneTableView.setVisible(true);
    }
}
