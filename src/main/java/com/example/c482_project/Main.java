package com.example.c482_project;

import  javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 966, 361);
        stage.setTitle("Inventory CRM");
        stage.setScene(scene);
        stage.show();
    }

    private static void addTestData() {
        // TODO Add test data here
    }
    public static void main(String[] args) {
        // TODO Include test data in the future...
        // addTestData();

        launch();
    }
}