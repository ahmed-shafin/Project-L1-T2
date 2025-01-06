package com.example.javafxproject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class ClubSearch {
    private List<Player> players;

    public ClubSearch(List<Player> players) {
        this.players = players;
    }

    List<Player> getMaximumSalary(String clubName) {
        List<Player> maxSalary = new ArrayList<>();
        int maximumSalary = 0;

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                maximumSalary = max(maximumSalary, player.getWeeklySalary());
            }
        }

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                if (player.getWeeklySalary() == maximumSalary) {
                    maxSalary.add(player);
                }
            }
        }
        return maxSalary;
    }

    List<Player> getMaximumAge(String clubName) {
        List<Player> maxAge = new ArrayList<>();
        int maximumAge = 0;

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                maximumAge = max(maximumAge, player.getAge());
            }
        }

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                if (player.getAge() == maximumAge) {
                    maxAge.add(player);
                }
            }
        }
        return maxAge;
    }

    List<Player> getMaximumHeight(String clubName) {
        List<Player> maxHeight = new ArrayList<>();
        double maximumHeight = 0;

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                maximumHeight = max(maximumHeight, player.getHeight());
            }
        }

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                if (player.getHeight() == maximumHeight) {
                    maxHeight.add(player);
                }
            }
        }
        return maxHeight;
    }

    int getTotalSalary(String clubName) {
        int total = 0;

        for (Player player : players) {
            if (player.getClub().equalsIgnoreCase(clubName)) {
                total += player.getWeeklySalary() * 52;
            }
        }
        return total;
    }
}
