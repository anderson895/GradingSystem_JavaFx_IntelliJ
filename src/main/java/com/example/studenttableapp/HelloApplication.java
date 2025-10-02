package com.example.studenttableapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Load the scene without fixed width/height
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Student Grading System");
        stage.setScene(scene);

        // Set full screen
        stage.setMaximized(true);   // Maximize the window
        // stage.setFullScreen(true); // Optional: true full screen (no window borders)

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
