import java.util.ArrayList;

public class MainSys {
    Serializer serialize;
    Deserializer deserialize;
    ArrayList<User> users;
    ArrayList<Project> projects;

    public MainSys() {
        this.serialize = new Serializer();
        this.deserialize = new Deserializer();
        this.getUsersFromDB();
        this.getProjectFromDB();
    }

    public void run() {
        // User u = new User("1", "joel", "azerty123");
        // Project p = new Project("WebApp", "Blablabla");
        // this.addUser(u);
        // this.addProject(p);
        while (true) {
            for (User user : users) {
                System.out.print(user + "\n");
            }
            for (Project project : projects) {
                System.out.print(project + "\n");
            }
            break;
            // TO DO : login
        }
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
