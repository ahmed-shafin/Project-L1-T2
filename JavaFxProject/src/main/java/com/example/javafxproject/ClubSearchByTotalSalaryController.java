package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ClubSearchByTotalSalaryController implements SceneControllerAware {
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
        searchButton.setOnAction(e -> findTotalSalary());
        backButton.setOnAction(e -> sceneController.switchScene("ClubSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void findTotalSalary() {
        String clubName = clubField.getText();

        if (clubName.isEmpty()) {
            resultLabel.setText("No players found for club: " + clubName);
            return;
        }

        int totalSalary = CricketPlayerDatabase.clubSearch.getTotalSalary(clubName);

        System.out.println(totalSalary);

        clubField.clear();
        resultLabel.setText("Total Salary of Club " + clubName + " is " + String.valueOf(totalSalary));
    }
}
