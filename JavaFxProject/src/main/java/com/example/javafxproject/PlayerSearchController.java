package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayerSearchController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private Button byNameButton;
    @FXML
    private Button byClubCountryButton;
    @FXML
    private Button byPositionButton;
    @FXML
    private Button bySalaryRangeButton;
    @FXML
    private Button backButton;

    public void initialize() {
        byNameButton.setOnAction(e->sceneController.switchScene("PlayerSearchByName"));
        byClubCountryButton.setOnAction(e->sceneController.switchScene("PlayerSearchByClubAndCountry"));
        byPositionButton.setOnAction(e->sceneController.switchScene("PlayerSearchByPosition"));
        bySalaryRangeButton.setOnAction(e->sceneController.switchScene("PlayerSearchBySalaryRange"));
        backButton.setOnAction(e -> sceneController.switchScene("MainMenu"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }
}
