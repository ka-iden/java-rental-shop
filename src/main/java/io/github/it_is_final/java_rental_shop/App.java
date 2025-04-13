package io.github.it_is_final.java_rental_shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml-test-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 640, 480);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}