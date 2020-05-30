package model;

import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Deserializer {

    public Deserializer() {}

    public ArrayList<User> user() {
        ArrayList<User> users = new ArrayList<>();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.ser"));
            users = (ArrayList) in.readObject(); 
            in.close();
        }
        // Catch FileNotFoundException as first run of program we don't have any Users yet, so projects.ser doesn't exists.
        catch (FileNotFoundException fnfe) {}
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.print("Error: Class not found");
            c.printStackTrace();
        }

        return users;
    }

    public ArrayList<Project> project() {
        ArrayList<Project> projects = new ArrayList<>();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("projects.ser"));
            projects = (ArrayList) in.readObject(); 
            in.close();
        }
        // Catch FileNotFoundException as first run of program we don't have any Projects yet, so projects.ser doesn't exists.
        catch (FileNotFoundException fnfe) {}
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.print("Error: Class not found");
            c.printStackTrace();
        }

        return projects;
    }
}