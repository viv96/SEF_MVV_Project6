package model;

import java.util.ArrayList;
import java.io.Serializable;

public class Project implements Serializable {
    private String projectName;
    private String projectStatus;
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    public Project(String projectName, String projectStatus, ArrayList<Activity> activities) {
        super();
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.activities = activities;
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
