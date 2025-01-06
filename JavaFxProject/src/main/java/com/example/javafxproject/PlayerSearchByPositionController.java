package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlayerSearchByPositionController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private TextField positionField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    @FXML
    private Label resultLabel;

    @FXML
    private VBox resultContainer;

    public void initialize() {
        searchButton.setOnAction(e -> searchPlayerByPosition());
        backButton.setOnAction(e -> sceneController.switchScene("PlayerSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void searchPlayerByPosition() {
        String position = positionField.getText().trim();

        if (position.isEmpty()) {
            resultLabel.setText("Please enter a position.");
            return;
        }
        positionField.clear();

        List<Player> players = CricketPlayerDatabase.playerSearch.getPlayersByPosition(position);
        for (Player player : players) {
            player.printInfo();
        }

        CricketPlayerDatabase.playersToShow = players;
        sceneController.switchScene("PlayerView");
    }
}
