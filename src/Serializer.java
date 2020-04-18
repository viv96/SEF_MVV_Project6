import java.util.ArrayList;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    public Serializer() {

    }

    public void user(ArrayList<User> user) {
        try {
            FileOutputStream fileOut = new FileOutputStream("users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void project(ArrayList<Project> project) {
        try {
            FileOutputStream fileOut = new FileOutputStream("projects.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(project);
            out.close();
            fileOut.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}