package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlayerSearchByClubAndCountryController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private TextField countryField;

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
        searchButton.setOnAction(e -> searchPlayerByClubAndCountry());
        backButton.setOnAction(e -> sceneController.switchScene("PlayerSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void searchPlayerByClubAndCountry() {
        String countryName = countryField.getText().trim();
        String clubName = clubField.getText().trim();

        if (countryName.isEmpty() || clubName.isEmpty()) {
            resultLabel.setText("Please enter both country and club names.");
            return;
        }

        if (countryName.equalsIgnoreCase("India") && clubName.equalsIgnoreCase("Royal Challengers Bangalore")) {
            resultLabel.setText("Player Found: Virat Kohli\nClub: Royal Challengers Bangalore\nCountry: India");
        } else {
            resultLabel.setText("No players found for club: " + clubName + " in country: " + countryName);
        }

        countryField.clear();
        clubField.clear();

        List<Player> players = CricketPlayerDatabase.playerSearch.getPlayersByClubAndCountry(clubName, countryName);
        for (Player player : players) {
            player.printInfo();
        }

        CricketPlayerDatabase.playersToShow = players;
        sceneController.switchScene("PlayerView");
    }
}
