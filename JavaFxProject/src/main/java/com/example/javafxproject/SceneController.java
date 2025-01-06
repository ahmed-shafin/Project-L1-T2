package com.example.javafxproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneController {
    private Stage stage;
    private Map<String, Scene> scenes = new HashMap<>();

    SceneController(Stage stage) {
        this.stage = stage;
    }

    public void addScene(String name, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        Object controller = loader.getController();
        ((SceneControllerAware) controller).setSceneController(this);

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        scenes.put(name, scene);
    }

    public void switchScene(String name) {
        stage.setScene(scenes.get(name));
    }
}
