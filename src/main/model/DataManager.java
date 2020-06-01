package model;

import java.io.File;
import java.io.IOException;
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
        /*users = this.deserialize.user();
        for (User user : users) {
            if (!(this.users.contains(user))) {
                this.users.add(user);
            }
        }*/

        this.users = this.deserialize.user();
    }

    private void getProjectFromDB() {
        ArrayList<Project> projects = new ArrayList<Project>();

        // Remove duplicate
        /*projects = this.deserialize.project();
        for (Project project : projects) {
            if (!(this.projects.contains(project))) {
                this.projects.add(project);
            }
        }*/

        this.projects = this.deserialize.project();
    }

    public void addUsersToDB(User user) {
        int index = getUserIndex(user);

        // User already exist on DB
        System.out.println("USER INDEX : " + index);
        if (index != -1) {
            this.users.remove(index);
            this.users.add(user);
        } else {
            this.users.add(user);
        }
        // Erase file content before adding new entry
        this.eraseFile("users.ser");
        //this.serialize.user(null);
        this.serialize.user(users);
    }

    public void addProjectsToDB(Project project) {
        int index = getProjectIndex(project);

        // User already exist on DB
        System.out.println("PROJECT INDEX : " + index);
        if (index != -1) {
            this.projects.remove(index);
            this.projects.add(project);
        } else {
            this.projects.add(project);
        }
        // Erase file content before adding new entry
        this.eraseFile("projects.ser");
        this.serialize.project(projects);
    }

    public void eraseFile(String filename) {
        File file = new File(filename);

        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getUserIndex(User user) {
        int index = 0;

        for (User userExist : this.getUsers()) {
            if (userExist.getId().equals(user.getId())) {

                return index;
            }
            index++;
        }

        return -1;
    }

    public int getProjectIndex(Project project) {
        int index = 0;

        for (Project projectExist : this.getProjects()) {
            if (projectExist.getProjectID().equals(project.getProjectID())) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }
}