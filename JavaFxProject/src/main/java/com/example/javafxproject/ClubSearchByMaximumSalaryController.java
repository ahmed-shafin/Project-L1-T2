package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ClubSearchByMaximumSalaryController implements SceneControllerAware {
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
        searchButton.setOnAction(e -> findPlayerWithMaxSalary());
        backButton.setOnAction(e -> sceneController.switchScene("ClubSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void findPlayerWithMaxSalary() {
        String clubName = clubField.getText().trim();

        if (clubName.isEmpty()) {
            resultLabel.setText("Please enter a club name.");
            return;
        }

        List<Player> maxSalaryPlayer = CricketPlayerDatabase.clubSearch.getMaximumSalary(clubName);

        if (!maxSalaryPlayer.isEmpty()) {
            for (Player player : maxSalaryPlayer) {
                resultLabel.setText(player.toString());
            }
        } else {
            resultLabel.setText("No players found for club: " + clubName);
        }

        for (Player player : maxSalaryPlayer) {
            player.printInfo();
        }

        clubField.clear();

        CricketPlayerDatabase.playersToShow = maxSalaryPlayer;
        sceneController.switchScene("PlayerView");
    }
}
