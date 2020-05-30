package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Manager extends User {

    public Manager(String id, String name, String password) {
        super (id, name, password, null);
    }

    /**********************************************************************************************************
     * Method name       : createProject(String projName, String projDescription, String projStartDate)
     * Return type       : Project
     * Creator           : Vijit Kumar
     * Method description: This method creates a new project.
     *********************************************************************************************************/
    public Project createProject(String projName, String projDescription, LocalDate projStartDate, LocalDate projEndDate) {
        Project project = new Project(projName, projDescription, projStartDate, projEndDate);
        return project;
    }

    /**********************************************************************************************************
     * Method name       : createActivity(String actName, String actDescription, double duration)
     * Return type       : Activity
     * Creator           : Vijit Kumar
     * Method description: This method creates an activity for the project.
     *********************************************************************************************************/
    public Activity createActivity(String actName, String actDescription, double duration, ArrayList<Activity> dependencies) {
        Activity activity = new Activity(actName, actDescription, duration, dependencies);
        return activity;
    }
}
