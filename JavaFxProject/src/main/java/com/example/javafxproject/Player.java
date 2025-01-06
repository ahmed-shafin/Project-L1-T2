package com.example.javafxproject;

public class Player {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int number;
    private int weeklySalary;

    public Player() {}
    public Player(String name, String country, int age, double height, String club, String position, int number, int weeklySalary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.weeklySalary = weeklySalary;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%d,%.2f,%s,%s,%d,%d", name, country, age, height, club, position, number, weeklySalary);
    }

    void printInfo() {
        System.out.println();
        System.out.printf("Name: %s%n", name);
        System.out.printf("Country: %s%n", country);
        System.out.printf("Age: %d%n", age);
        System.out.printf("Height: %.2f%n", height);
        System.out.printf("Club: %s%n", club);
        System.out.printf("Position: %s%n", position);
        System.out.printf("Number: %d%n", number);
        System.out.printf("Weekly Salary: %d%n", weeklySalary);
        System.out.println("---------------");
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getClub() {
        return club;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public int getWeeklySalary() {
        return weeklySalary;
    }

    public String getName() {
        return name;
    }
}
