package com.example.parkingsystemgui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ParkingSystemApp.fxml"));
            VBox root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Parking System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}