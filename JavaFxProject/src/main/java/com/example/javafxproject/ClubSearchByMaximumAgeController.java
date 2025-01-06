package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class ClubSearchByMaximumAgeController implements SceneControllerAware {
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
        searchButton.setOnAction(e -> findPlayerWithMaxAge());
        backButton.setOnAction(e -> sceneController.switchScene("ClubSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void findPlayerWithMaxAge() {
        String clubName = clubField.getText().trim();

        if (clubName.isEmpty()) {
            resultLabel.setText("Please enter a club name.");
            return;
        }

        List<Player> maxAgePlayer = CricketPlayerDatabase.clubSearch.getMaximumAge(clubName);

        if (!maxAgePlayer.isEmpty()) {
            StringBuilder result = new StringBuilder("Players with Maximum Age:\n");
            for (Player player : maxAgePlayer) {
                result.append(player.toString()).append("\n");
            }
            resultLabel.setText(result.toString());
        } else {
            resultLabel.setText("No players found for club: " + clubName);
        }

        for (Player player : maxAgePlayer) {
            player.printInfo();
        }

        clubField.clear();

        CricketPlayerDatabase.playersToShow = maxAgePlayer;
        sceneController.switchScene("PlayerView");
    }
}
