package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class ClubSearchByMaximumHeightController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private TextField clubField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    @FXML
    private Label resultLabel;

    @FXML
    private VBox resultContainer;

    public void initialize() {
        searchButton.setOnAction(e -> findPlayerWithMaxHeight());
        backButton.setOnAction(e -> sceneController.switchScene("ClubSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void findPlayerWithMaxHeight() {
        String clubName = clubField.getText().trim();

        if (clubName.isEmpty()) {
            resultLabel.setText("Please enter a club name.");
            return;
        }

        List<Player> maxHeightPlayers = CricketPlayerDatabase.clubSearch.getMaximumHeight(clubName);

        if (!maxHeightPlayers.isEmpty()) {
            StringBuilder result = new StringBuilder("Players with Maximum Height:\n");
            for (Player player : maxHeightPlayers) {
                result.append(player.toString()).append("\n");
            }
            resultLabel.setText(result.toString());
        } else {
            resultLabel.setText("No players found for club: " + clubName);
        }

        for (Player player : maxHeightPlayers) {
            player.printInfo();
        }

        clubField.clear();

        CricketPlayerDatabase.playersToShow = maxHeightPlayers;
        sceneController.switchScene("PlayerView");
    }
}
