package ipl.marketplace;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServerController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label statusLabel;

    @FXML
    private TextArea messagesArea;

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

    private ServerSocket serverSocket;
    private final ExecutorService clientHandlerPool = Executors.newCachedThreadPool();

    private final List<Player> availablePlayers = Collections.synchronizedList(new ArrayList<>());
    private final List<Player> playerForSale = Collections.synchronizedList(new ArrayList<>());

    public ServerController() {
//        availablePlayers.add(new Player("Lionel Messi", "Argentina", 36, 1.70, "PSG", "Forward", 10, 500000));
//        availablePlayers.add(new Player("Cristiano Ronaldo", "Portugal", 38, 1.87, "Al-Nassr", "Forward", 7, 700000));
//        availablePlayers.add(new Player("Kylian Mbappe", "France", 24, 1.78, "PSG", "Forward", 7, 450000));
//
//        playerForSale.add(new Player("Neymar Jr.", "Brazil", 31, 1.75, "PSG", "Forward", 10, 400000));
//        playerForSale.add(new Player("Erling Haaland", "Norway", 22, 1.94, "Man City", "Forward", 9, 600000));
        try {
            readPlayers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readPlayers() throws Exception {
        final String INPUT_FILE_NAME = "src/main/players.txt";

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;

            String [] tokens = line.split(",");

            String name = tokens[0];
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

            availablePlayers.add(player);
        }
        br.close();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "1234".equals(password)) {
            statusLabel.setText("Login Successful!");
            messagesArea.appendText("Server is starting...\n");
            startServer();
        } else {
            statusLabel.setText("Login Failed!");
        }
    }

    private void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(12345);
                messagesArea.appendText("Server started on port 12345.\n");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    messagesArea.appendText("Client connected: " + clientSocket.getInetAddress() + "\n");

                    clientHandlerPool.execute(() -> handleClient(clientSocket));
                }
            } catch (IOException e) {
                messagesArea.appendText("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String username = in.readLine();
            messagesArea.appendText("Username logged in: " + username + "\n");

            String command;
            while ((command = in.readLine()) != null) {
                messagesArea.appendText("Received command: " + command + "\n");

                if ("GET_PLAYERS".equals(command)) {
                    sendPlayersByClub(username, out);
                }
                else if ("BUY_PLAYERS".equals(command)) {
                    synchronized (playerForSale) {
                        out.writeObject(new ArrayList<>(playerForSale)); // Send players for sale
                    }
                }
                else if (command.startsWith("BUY_PLAYER:")) {
                    String playerName = command.split(":")[1];
                    handleBuyPlayer(playerName, username, out);
                }
                else if (command.startsWith("SELL_PLAYER:")) {
                    String playerName = command.split(":")[1];
                    handleSellPlayer(playerName, out);
                }
                out.flush();
            }
        } catch (IOException e) {
            messagesArea.appendText("Client disconnected.\n");
        }
    }

    private void sendPlayersByClub(String username, ObjectOutputStream out) throws IOException {
        synchronized (availablePlayers) {
            List<Player> playersByClub = availablePlayers.stream()
                    .filter(player -> player.getClub().equalsIgnoreCase(username))
                    .toList();
            out.writeObject(playersByClub);
        }
        messagesArea.appendText("Sent players owned by: " + username + "\n");
    }

    private void handleBuyPlayer(String playerName, String username, ObjectOutputStream out) throws IOException {
        synchronized (playerForSale) {
            Optional<Player> player = playerForSale.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(playerName))
                    .findFirst();

            if (player.isPresent()) {
                Player boughtPlayer = player.get();
                playerForSale.remove(boughtPlayer);
                boughtPlayer.setClub(username); // Update the club name to the buyer's username
                availablePlayers.add(boughtPlayer);

                out.writeObject("Player bought successfully: " + playerName);
                messagesArea.appendText("Player bought: " + playerName + " by " + username + "\n");
            } else {
                out.writeObject("Player not available for sale: " + playerName);
                messagesArea.appendText("Player not available for sale: " + playerName + "\n");
            }
        }
    }

    private void handleSellPlayer(String playerName, ObjectOutputStream out) throws IOException {
        synchronized (availablePlayers) {
            Optional<Player> player = availablePlayers.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(playerName))
                    .findFirst();

            if (player.isPresent()) {
                Player soldPlayer = player.get();
                availablePlayers.remove(soldPlayer);
                playerForSale.add(soldPlayer);

                out.writeObject("Player sold successfully: " + playerName);
                messagesArea.appendText("Player sold: " + playerName + "\n");
            } else {
                out.writeObject("Player not found in your list: " + playerName);
                messagesArea.appendText("Player not found in your list: " + playerName + "\n");
            }
        }
    }

    @FXML
    private void handleAddPlayer() {
        try {
            String name = nameField.getText();
            String country = countryField.getText();
            int age = Integer.parseInt(ageField.getText());
            double height = Double.parseDouble(heightField.getText());
            String club = clubField.getText();
            String position = positionField.getText();
            int number = Integer.parseInt(numberField.getText());
            int weeklySalary = Integer.parseInt(weeklySalaryField.getText());

            Player newPlayer = new Player(name, country, age, height, club, position, number, weeklySalary);
            availablePlayers.add(newPlayer);
            messagesArea.appendText("Player added: " + newPlayer + "\n");

            nameField.clear();
            countryField.clear();
            ageField.clear();
            heightField.clear();
            clubField.clear();
            positionField.clear();
            numberField.clear();
            weeklySalaryField.clear();
        } catch (NumberFormatException e) {
            messagesArea.appendText("Invalid input! Please check numeric fields.\n");
        } catch (Exception e) {
            messagesArea.appendText("Error adding player: " + e.getMessage() + "\n");
        }
    }
}
