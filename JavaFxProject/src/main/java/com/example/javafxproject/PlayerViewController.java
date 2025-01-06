package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewController implements SceneControllerAware {
    private List<Player> players = new ArrayList<>();
    private SceneController sceneController;

    @FXML
    private TextArea playerTextArea;

    @FXML
    private Button backButton;

    @FXML
    private Button showPlayersButton;

    public void initialize() {
        playerTextArea.setText("Press Show Players button to show players");
        backButton.setOnAction(e -> {
            playerTextArea.clear();
            playerTextArea.setText("Press Show Players button to show players");
            sceneController.switchScene("MainMenu");
        });

        showPlayersButton.setOnAction(e -> {
            setPlayers();
            displayPlayers();
        });
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public void displayPlayers() {
        StringBuilder playerInfo = new StringBuilder();
        for (Player player : players) {
            playerInfo.append(formatPlayerInfo(player)).append("\n\n");
        }
        playerTextArea.setText(playerInfo.toString());
    }

    private String formatPlayerInfo(Player player) {
        return String.format("""
                Name: %s
                Country: %s
                Age: %d
                Height: %.2f m
                Club: %s
                Position: %s
                Number: %d
                Weekly Salary: $%d
                """, player.getName(), player.getCountry(), player.getAge(),
                player.getHeight(), player.getClub(), player.getPosition(),
                player.getNumber(), player.getWeeklySalary());
    }

    public void setPlayers() {
        players.clear();
        if (CricketPlayerDatabase.playersToShow != null) {
            players.addAll(CricketPlayerDatabase.playersToShow);
        }
    }
}
