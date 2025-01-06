package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private Button searchPlayersButton;
    @FXML
    private Button searchClubsButton;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button exitButton;

    public void initialize() {
        searchPlayersButton.setOnAction(e -> sceneController.switchScene("PlayerSearch"));
        searchClubsButton.setOnAction(e -> sceneController.switchScene("ClubSearch"));
        addPlayerButton.setOnAction(e -> sceneController.switchScene("AddPlayer"));
        exitButton.setOnAction(e -> {
            try {
                CricketPlayerDatabase.writePlayers();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        });
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
