package com.example.javafxproject;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CricketPlayerDatabase extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneController sceneController = new SceneController(stage);
        sceneController.addScene("MainMenu", "MainMenu.fxml");
        //......
        sceneController.addScene("PlayerSearch", "PlayerSearch.fxml");
        sceneController.addScene("PlayerSearchByName", "PlayerSearchByName.fxml");
        sceneController.addScene("PlayerSearchByClubAndCountry", "PlayerSearchByClubAndCountry.fxml");
        sceneController.addScene("PlayerSearchByPosition", "PlayerSearchByPosition.fxml");
        sceneController.addScene("PlayerSearchBySalaryRange", "PlayerSearchBySalaryRange.fxml");
        //.......
        sceneController.addScene("ClubSearch", "ClubSearch.fxml");
        sceneController.addScene("ClubSearchByMaximumSalary", "ClubSearchByMaximumSalary.fxml");
        sceneController.addScene("ClubSearchByMaximumAge", "ClubSearchByMaximumAge.fxml");
        sceneController.addScene("ClubSearchByMaximumHeight", "ClubSearchByMaximumHeight.fxml");
        sceneController.addScene("ClubSearchByTotalSalary", "ClubSearchByTotalSalary.fxml");

        sceneController.addScene("AddPlayer", "AddPlayer.fxml");

        sceneController.addScene("PlayerView", "PlayerView.fxml");

        sceneController.switchScene("MainMenu");

        Image icon = new Image("icon.png");

        stage.getIcons().add(icon);
        stage.setTitle("Cricket Player Database");

        stage.setResizable(false);
        stage.show();
    }

    public static List<Player> players = new ArrayList<>();
    public static PlayerSearch playerSearch = new PlayerSearch(players);
    public static ClubSearch clubSearch = new ClubSearch(players);
    public static List<Player> playersToShow = new ArrayList<>();

    private static void readPlayers() throws Exception {
        final String INPUT_FILE_NAME = "src/main/resources/players.txt";

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;

            String [] tokens = line.split(",");

            String name = tokens[0];
            Player player = getPlayer(tokens, name);

            players.add(player);
        }
        br.close();
    }

    public static void writePlayers() throws Exception {
        final String OUTPUT_FILE_NAME = "src/main/resources/players.txt";

        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));

        for (Player player : players) {
            bw.write(player.toString());
            bw.newLine();
        }
        bw.close();
    }

    private static Player getPlayer(String[] tokens, String name) {
        String country = tokens[1];
        int age = Integer.parseInt(tokens[2]);
        double height = Double.parseDouble(tokens[3]);
        String club = tokens[4];
        String position = tokens[5];
        int number = 0;
        if (tokens.length > 6 && !tokens[6].isBlank())
            number = Integer.parseInt(tokens[6]);
        int weeklySalary = Integer.parseInt(tokens[7]);

        Player player = new Player(name, country, age, height, club, position, number, weeklySalary);
        return player;
    }

    public static void main(String[] args) throws Exception {
        readPlayers();
        launch(args);
    }
}