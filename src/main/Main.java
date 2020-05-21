import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
            primaryStage.setTitle("Software Management - MVV");
            primaryStage.setResizable(false);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            System.out.print("Error loading login.fxml file.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}