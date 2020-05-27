package model;

import java.util.ArrayList;

public class Manager {
    private String name;
    private Serializer serialize;
    private Deserializer deserialize;

/*
    public static Manager getInstance() {
        Manager manager = new Manager();
        manager.serialize = new Serializer();
        manager.deserialize = new Deserializer();
        manager.getUsersFromDB();
        manager.getProjectFromDB();
        return manager;
    }
*/
    //Constructor
    public Manager(String name) {
        this.name = name;
    }

/*
    private void getUsersFromDB() {
        this.users = this.deserialize.user();
    }

    private void getProjectFromDB() {
        this.projects = this.deserialize.project();
    }

    public void addUsersToDB(User user) {
        this.users.add(user);
        this.serialize.user(users);
    }

    public void addProjectsToDB(Project project) {
        this.projects.add(project);
        this.serialize.project(projects);
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }
*/
    /*******************************************************************************************************************
     * Method name       : createProject(String , String , String )
     * Return type       : Project
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method creates a new project.
     ******************************************************************************************************************/
    public Project createProject(String projName, String projDescription, String projStartDate) {
        Project project = new Project(projName, projDescription, projStartDate);
        return project;
    }

    /*******************************************************************************************************************
     * Method name       : createActivity(Project , String , String , double, ArrayList<String> )
     * Return type       : Activity
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method creates an activity for the project.
     ******************************************************************************************************************/
    public Activity createActivity(Project project,String actName, String actDescription, double duration, ArrayList<Activity> dependencies) {
        Activity activity = new Activity(actName, actDescription, duration, dependencies);
        return activity;
    }

    /*******************************************************************************************************************
     * Method name       : addActivitiesToProject()
     * return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method adds an activity to the listOfActivities which is part of the project class.
     ******************************************************************************************************************/
    public void addActivityToProject(Project project, Activity activity){
        project.updateListOfActivities(activity);
    }

    /*******************************************************************************************************************
     * Method name       : viewAllActivitiesInProject(Project )
     * return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method allows the Project manager to view the listOfActivities inside a project.
     ******************************************************************************************************************/
    public void viewAllActivitiesInProject(Project project){
        for(Activity act: project.getListOfActivities()){
            System.out.println(act.toString());
        }
    }

    /*******************************************************************************************************************
     * Method name       : calculateCriticalPath(Project )
     * return type       : void
     * Creator           : Vijit Kumar (s3799493)
     * Method description: This method allows the Project manager to calculate the critical path of the project.
     ******************************************************************************************************************/
    public void calculateCriticalPath(Project project){
        project.criticalPath();
    }
}