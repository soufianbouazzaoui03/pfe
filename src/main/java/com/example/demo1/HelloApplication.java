package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static Stage closeStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FirstPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 759, 500);
        //String css = this.getClass().getResource("style.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        closeStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}