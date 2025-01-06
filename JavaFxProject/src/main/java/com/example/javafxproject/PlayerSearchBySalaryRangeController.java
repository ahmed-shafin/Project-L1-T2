package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerSearchBySalaryRangeController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private TextField lowSalaryField;

    @FXML
    private TextField highSalaryField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    @FXML
    private Label resultLabel;

    public void initialize() {
        searchButton.setOnAction(e -> searchPlayersBySalaryRange());
        backButton.setOnAction(e -> sceneController.switchScene("PlayerSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void searchPlayersBySalaryRange() {
        String lowSalaryText = lowSalaryField.getText().trim();
        String highSalaryText = highSalaryField.getText().trim();

        if (lowSalaryText.isEmpty() || highSalaryText.isEmpty()) {
            resultLabel.setText("Please enter both minimum and maximum salary.");
            return;
        }

        try {
            int lowSalary = Integer.parseInt(lowSalaryText);
            int highSalary = Integer.parseInt(highSalaryText);

            if (lowSalary > highSalary) {
                resultLabel.setText("Minimum salary cannot be greater than maximum salary.");
                return;
            }

            List<Player> playersInRange = CricketPlayerDatabase.playerSearch.getPlayersBySalary(lowSalary, highSalary);

            CricketPlayerDatabase.playersToShow = playersInRange;
            sceneController.switchScene("PlayerView");

        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numerical values for salaries.");
        }
    }
}
