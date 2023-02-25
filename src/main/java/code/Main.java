package code;

import  javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class creates an app that displays the "Main Screen" of the app.
 */
public class Main extends Application {
    /**
     * This is the start method. This method creates the code to load the fxml file to display the "Main Screen" window.
     * JAVADOCS FOLDER LOCATION: located inside my project in the javaDocs folder.
     * FUTURE ENHANCEMENTS: I would like error handling to be implemented to handle negative numbers in the fields.
     * Meaning, provide errors for when a user enters in a negative number.
     * @param stage This is the variable that represents the fxml window to be displayed.
     * @throws IOException This will throw an exception if the window can't be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 966, 361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This is the main method. It is the first method that loads when you run the Java program.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}