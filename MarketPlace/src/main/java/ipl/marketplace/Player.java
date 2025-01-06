package ipl.marketplace;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public String printInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\n");
        info.append(String.format("Name: %s%n", name));
        info.append(String.format("Country: %s%n", country));
        info.append(String.format("Age: %d%n", age));
        info.append(String.format("Height: %.2f%n", height));
        info.append(String.format("Club: %s%n", club));
        info.append(String.format("Position: %s%n", position));
        info.append(String.format("Number: %d%n", number));
        info.append(String.format("Weekly Salary: %d%n", weeklySalary));
        info.append("---------------\n");
        return info.toString();
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

    public void setClub(String text) {
        this.club = text;
    }
}
