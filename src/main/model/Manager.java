package model;

import enumerations.Status;
import enumerations.availability;

import java.lang.reflect.Array;
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
    public Project createProject(String projId, String projName, Status projStatus, ArrayList<Activity> projActivities, String projDescription, LocalDate projStartDate, LocalDate projEndDate) {
        Project project = new Project(projId, projName, projDescription, projStatus, projActivities, projStartDate, projEndDate);
        return project;
    }

    /**********************************************************************************************************
     * Method name       : createActivity(String actName, String actDescription, double duration)
     * Return type       : Activity
     * Creator           : Vijit Kumar
     * Method description: This method creates an activity for the project.
     *********************************************************************************************************/
    public Activity createActivity(String actId, String actName, String actDescription, double duration, ArrayList<String> actStaff, LocalDate actStartDate, LocalDate actEndDate, availability actDayPerWeek, ArrayList<Activity> dependencies, ArrayList<Skill> actSkillRequired) {
        Activity activity = new Activity(actId, actName, actDescription, duration, actStaff, actStartDate, actEndDate, actDayPerWeek, dependencies, actSkillRequired);
        return activity;
    }
}
