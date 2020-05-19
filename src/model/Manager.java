package model;

import java.util.ArrayList;

public class Manager extends User{
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
    public Manager(String name, String password) {
        super(name,password);
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
    /**********************************************************************************************************
     * Method name       : createProject(String projName, String projDescription, String projStartDate)
     * Return type       : Project
     * Creator           : Vijit Kumar
     * Method description: This method creates a new project.
     *********************************************************************************************************/
    public Project createProject(String projName, String projDescription, String projStartDate) {
        Project project = new Project(projName, projDescription, projStartDate);
        return project;
    }

    /**********************************************************************************************************
     * Method name       : createActivity(String actName, String actDescription, double duration)
     * Return type       : Activity
     * Creator           : Vijit Kumar
     * Method description: This method creates an activity for the project.
     *********************************************************************************************************/
    public Activity createActivity(String actName, String actDescription, double duration) {
        Activity activity = new Activity(actName, actDescription, duration);
        return activity;
    }
}