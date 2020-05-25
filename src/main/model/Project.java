package model;

import java.util.ArrayList;
import java.io.Serializable;

public class Project implements Serializable {
    private String projectID;
    private String projectName;
    private String projectStatus;
    private String projectStartDate;
    private String projectEndDate;
    private int projectDaysPerWeek;
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    public Project(String projectID, String projectName, String projectStatus, ArrayList<Activity> activities, String projectStartDate, String projectEndDate, int projectDaysPerWeek) {
        super();
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.activities = activities;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectDaysPerWeek = projectDaysPerWeek;
    }

    public int getProjectDaysPerWeek() {
        return projectDaysPerWeek;
    }

    public void setProjectDaysPerWeek(int projectDaysPerWeek) {
        this.projectDaysPerWeek = projectDaysPerWeek;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return this.projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return "model.Project [name = " + this.projectName + ", status = " + this.projectStatus + ", activities = " + this.activities + "]";
    }
}
