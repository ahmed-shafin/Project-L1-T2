package com.example.javafxproject;

import java.util.ArrayList;
import java.util.List;

public class PlayerSearch {
    private List<Player> players;

    public PlayerSearch(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayersByName(String playerName) {
        List<Player> playersByName = new ArrayList<>();

        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName))
                playersByName.add(player);
        }
        return playersByName;
    }

    public List<Player> getPlayersByClubAndCountry(String clubName, String countryName) {
        List<Player> playersByClub = new ArrayList<>();

        for (Player player : players) {
            boolean okay = false;

            if (clubName.equalsIgnoreCase("ANY") && countryName.equalsIgnoreCase("ANY")) okay = true;
            else if (clubName.equalsIgnoreCase("ANY") && player.getCountry().equalsIgnoreCase(countryName)) okay = true;
            else if (player.getClub().equalsIgnoreCase(clubName) && countryName.equalsIgnoreCase("ANY")) okay = true;
            else if (player.getClub().equalsIgnoreCase(clubName) && player.getCountry().equalsIgnoreCase(countryName)) okay = true;

            if (okay)
                playersByClub.add(player);
        }
        return playersByClub;
    }

    public List<Player> getPlayersByPosition(String position) {
        List<Player> playersByPosition = new ArrayList<>();

        for (Player player : players) {
            if (player.getPosition().equalsIgnoreCase(position))
                playersByPosition.add(player);
        }
        return playersByPosition;
    }

    public List<Player> getPlayersBySalary(int lowerLimit, int upperLimit) {
        List<Player> playersBySalary = new ArrayList<>();

        for (Player player : players) {
            if (player.getWeeklySalary() >= lowerLimit && player.getWeeklySalary() <= upperLimit)
                playersBySalary.add(player);
        }
        return playersBySalary;
    }
}
