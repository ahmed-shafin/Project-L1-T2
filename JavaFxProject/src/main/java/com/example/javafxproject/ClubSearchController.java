package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClubSearchController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private Button byMaximumSalaryButton;
    @FXML
    private Button byMaximumAgeButton;
    @FXML
    private Button byMaximumHeightButton;
    @FXML
    private Button yearlySalaryButton;
    @FXML
    private Button backButton;

    public void initialize() {
        byMaximumSalaryButton.setOnAction(e -> sceneController.switchScene("ClubSearchByMaximumSalary"));
        byMaximumAgeButton.setOnAction(e -> sceneController.switchScene("ClubSearchByMaximumAge"));
        byMaximumHeightButton.setOnAction(e -> sceneController.switchScene("ClubSearchByMaximumHeight"));
        yearlySalaryButton.setOnAction(e -> sceneController.switchScene("ClubSearchByTotalSalary"));
        backButton.setOnAction(e -> sceneController.switchScene("MainMenu"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}