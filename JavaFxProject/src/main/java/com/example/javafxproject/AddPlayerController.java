package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddPlayerController implements SceneControllerAware {
    private SceneController sceneController;

    @FXML
    private TextField nameField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField clubField;

    @FXML
    private TextField positionField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField weeklySalaryField;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Label statusLabel;

    public void initialize() {
        addButton.setOnAction(e -> addPlayer());
        backButton.setOnAction(e -> sceneController.switchScene("PlayerSearch"));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    private void addPlayer() {
        try {
            String name = nameField.getText().trim();
            String country = countryField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            double height = Double.parseDouble(heightField.getText().trim());
            String club = clubField.getText().trim();
            String position = positionField.getText().trim();
            int number = Integer.parseInt(numberField.getText().trim());
            int weeklySalary = Integer.parseInt(weeklySalaryField.getText().trim());

            if (name.isEmpty() || country.isEmpty() || club.isEmpty() || position.isEmpty()) {
                statusLabel.setText("All fields must be filled.");
                return;
            }

            Player newPlayer = new Player(name, country, age, height, club, position, number, weeklySalary);

            CricketPlayerDatabase.players.add(newPlayer);

            clearFields();
            statusLabel.setText("Player added successfully!");

        } catch (NumberFormatException ex) {
            statusLabel.setText("Please enter valid numbers for age, height, jersey number, and salary.");
        } catch (Exception ex) {
            statusLabel.setText("An error occurred: " + ex.getMessage());
        }
    }

    private void clearFields() {
        nameField.clear();
        countryField.clear();
        ageField.clear();
        heightField.clear();
        clubField.clear();
        positionField.clear();
        numberField.clear();
        weeklySalaryField.clear();
    }
}
