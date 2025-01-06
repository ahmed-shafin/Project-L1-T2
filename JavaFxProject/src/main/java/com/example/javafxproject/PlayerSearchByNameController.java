package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlayerSearchByNameController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private TextField nameField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    @FXML
    private Label resultLabel;

    @FXML
    private VBox resultContainer;

    public void initialize() {
        searchButton.setOnAction(e -> searchPlayerByName());
        backButton.setOnAction(e -> sceneController.switchScene("PlayerSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void searchPlayerByName() {
        String playerName = nameField.getText().trim();

        if (playerName.isEmpty()) {
            resultLabel.setText("Please enter a player name.");
            return;
        }

        nameField.clear();

        List<Player> players = CricketPlayerDatabase.playerSearch.getPlayersByName(playerName);
        for (Player player : players) {
            player.printInfo();
        }

        CricketPlayerDatabase.playersToShow = players;
        sceneController.switchScene("PlayerView");
    }
}
