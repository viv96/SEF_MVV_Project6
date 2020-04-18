import java.util.ArrayList;
import java.util.Scanner;

public class MainSys {
    private Serializer serialize;
    private Deserializer deserialize;
    public Scanner scan = new Scanner(System.in);
    private int menuReply;
    private String username;
    private String password;
    private ArrayList<User> users;
    private ArrayList<Project> projects;

    public MainSys() {
        this.serialize = new Serializer();
        this.deserialize = new Deserializer();
        this.getUsersFromDB();
        this.getProjectFromDB();
    }

    public void run() {
        // User u = new User("1", "joel", "azerty123");
        // Project p = new Project("WebApp", "Blablabla", new ArrayList<Activity>());
        // this.addUser(u);
        // this.addProject(p);
        scan = new Scanner(System.in);

        do {
            System.out.print("Welcome to project Management System.\n1.login\n2.quit\n3.create profile\nYour Choice: ");
            for (User user : users) {
                System.out.print(user + "\n");
            }
            for (Project project : projects) {
                System.out.print(project + "\n");
            }
            menuReply = scan.nextInt();
            switch (menuReply) {
                // TO DO LOGIN
            }
        } while (true);
    }

    private void addUser(User user) {
        this.users.add(user);
        this.serialize.user(this.users);
    }

    private void addProject(Project project) {
        this.projects.add(project);
        this.serialize.project(this.projects);
    }

    private void getUsersFromDB() {
        this.users = this.deserialize.user();
    }

    private void getProjectFromDB() {
        this.projects = this.deserialize.project();
    }

    private ArrayList<User> getUsers() {
        return this.users;
    }

    private ArrayList<Project> getProjects() {
        return this.projects;
    }
}
