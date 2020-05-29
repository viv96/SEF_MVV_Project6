package model;

import java.util.ArrayList;

public class DataManager {
    private Serializer serialize;
    private Deserializer deserialize;
    private ArrayList<User> users;
    private ArrayList<Project> projects;

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
}