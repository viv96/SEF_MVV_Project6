package model;

import java.util.ArrayList;
import java.io.Serializable;

public class Project implements Serializable {
    private static int projectNumID = 0;
    private String projectID;
    private String projectName;
    private String projectDescription;
    private String projectStartDate;
    private pstatus projectStatus;
    private enum pstatus {OPEN, ASSIGNED, COMPLETED}; //Still need to think about the types of status we need
    private ArrayList<Activity> listOfActivities;

    //Constructor
    public Project(String name, String description, String startDate){
        this.projectName = name;
        this.projectDescription = description;
        this.projectStartDate = startDate;
        this.projectStatus = pstatus.OPEN;
        projectNumID++;
        generateProjectId();

        //Creating a dummy activity and adding it to arraylist.
        Activity activity = new Activity("sample", "This is a sample activity", 0);
        listOfActivities = new ArrayList<Activity>();
        listOfActivities.add(activity);
    }

    /***************************************************************************************
     * Method name       : generateProjectId()
     * Method description: This method is called in a constructor and then generates a unique
     *                     ID for an project object.
     * Creator           : Vijit Kumar
     * Date              : 04/05/2020
     ***************************************************************************************/
    public void generateProjectId() {
        String s1 = "P";
        String str = String.format("%d", projectNumID);
        String s = s1+str;
        this.projectID = s;
    }

    @Override
    public String toString() {
        return "model.Project [name = " + this.projectName + ", status = " + this.projectStatus + ", activities = " + this.activities + "]";
    }
}
