package model;

import java.util.*;

public class DataManager {
    private Serializer serialize;
    private Deserializer deserialize;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Project> projects = new ArrayList<Project>();

    public static DataManager getInstance() {
        DataManager manager = new DataManager();

        manager.serialize = new Serializer();
        manager.deserialize = new Deserializer();
        manager.getUsersFromDB();
        manager.getProjectFromDB();

        return manager;
    }

    private DataManager() {}

    private void getUsersFromDB() {
        ArrayList<User> users = new ArrayList<User>();

        // Remove duplicate
        users = this.deserialize.user();
        for (User user : users) {
            if (!(this.users.contains(user))) {
                this.users.add(user);
            }
        }
    }

    private void getProjectFromDB() {
        ArrayList<Project> projects = new ArrayList<Project>();

        // Remove duplicate
        projects = this.deserialize.project();
        for (Project project : projects) {
            if (!(this.projects.contains(project))) {
                this.projects.add(project);
            }
        }
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
}