package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable {
    private String id;
    private String name;
    private String password;
    private ArrayList<String> empProjects = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String name, String password, String projectID) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        if (projectID==null){
            this.empProjects = null;
        }else {
            this.empProjects.add(projectID);
        }
    }

    public ArrayList<String> getEmpProjects() {
        return empProjects;
    }

    @Override
    public String toString() {
        return "user [id = " + this.id + ", name = " + this.name + ", password = " + this.password + "]";
    }
}
