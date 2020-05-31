package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeserializerTest {

    private ArrayList<User> users;
    private ArrayList<Project> projects;
    private Serializer serializer;
    private Deserializer deserializer;

    @BeforeEach
    public void setUp() {
        this.users = new ArrayList<User>();
        this.projects = new ArrayList<Project>();
        this.serializer = new Serializer();
        this.deserializer = new Deserializer();


        this.users.add(new User("maxime","azerty123"));
        this.users.add(new User( "vivek","azerty123"));
        this.serializer.user(this.users);
    }

    // Clear everything after each test
    @AfterEach
    public void tearDown() {
        this.users.clear();
        try {
            new FileOutputStream("users.ser").close();
            new FileOutputStream("projects.ser").close();
            File fUser = new File("users.ser");
            File fProject = new File("projects.ser");
            fUser.delete();
            fProject.delete();
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace().toString());
        } catch (IOException e) {
            System.out.println(e.getStackTrace().toString());
        }
    }

    @Test
    public void testGetUsers() {
        ArrayList<User> users;

        System.out.println("-- Testing Deserialization of users --");
        users = this.deserializer.user();
        assertEquals(this.users.size(), users.size());
        for (int i = 0; i < this.users.size(); i++) {
            assertEquals(this.users.get(i).toString(), users.get(i).toString());
        }
    }

    @Test
    public void testGetProjects() {
        ArrayList<Project> projects;

        System.out.println("-- Testing Deserialization of projects --");
        projects = this.deserializer.project();
        assertEquals(this.projects.size(), projects.size());
        for (int i = 0; i < this.projects.size(); i++) {
            assertEquals(this.projects.get(i).toString(), projects.get(i).toString());
        }
    }
}