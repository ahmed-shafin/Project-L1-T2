package ipl.marketplace;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientController {
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
    private TextField playerTransactionField;

    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username != null && !username.isEmpty() && "1234".equals(password)) {
            statusLabel.setText("Login Successful!");
            connectToServer(username);
        } else {
            statusLabel.setText("Login Failed!");
        }
    }

    private void connectToServer(String username) {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 12345);
                messagesArea.appendText("Connected to server\n");

                in = new ObjectInputStream(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println(username);

            } catch (IOException e) {
                messagesArea.appendText("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }

    @FXML
    private void requestPlayers() {
        new Thread(() -> {
            try {
                if (out != null) {
                    out.println("GET_PLAYERS");
                    List<Player> players = (List<Player>) in.readObject();
                    displayPlayers(players);
                }
            } catch (IOException | ClassNotFoundException e) {
                messagesArea.appendText("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }

    @FXML
    private void handleBuyPlayer() {
        String playerName = playerTransactionField.getText();
        if (playerName == null || playerName.isEmpty()) {
            messagesArea.appendText("Please enter a player name to buy.\n");
            return;
        }

        new Thread(() -> {
            try {
                if (out != null) {
                    out.println("BUY_PLAYER:" + playerName);
                    messagesArea.appendText("Attempting to buy player: " + playerName + "\n");

                    String serverResponse = (String) in.readObject();
                    messagesArea.appendText(serverResponse + "\n");
                }
            } catch (IOException | ClassNotFoundException e) {
                messagesArea.appendText("Error while buying player: " + e.getMessage() + "\n");
            }
        }).start();
    }

    @FXML
    private void handleSellPlayer() {
        String playerName = playerTransactionField.getText();
        if (playerName == null || playerName.isEmpty()) {
            messagesArea.appendText("Please enter a player name to sell.\n");
            return;
        }

        new Thread(() -> {
            try {
                if (out != null) {
                    out.println("SELL_PLAYER:" + playerName);
                    messagesArea.appendText("Attempting to sell player: " + playerName + "\n");

                    String serverResponse = (String) in.readObject();
                    messagesArea.appendText(serverResponse + "\n");
                }
            } catch (IOException | ClassNotFoundException e) {
                messagesArea.appendText("Error while selling player: " + e.getMessage() + "\n");
            }
        }).start();
    }

    @FXML
    private void handleBuyList() {
        new Thread(() -> {
            try {
                if (out != null) {
                    out.println("BUY_PLAYERS");
                    List<Player> players = (List<Player>) in.readObject();
                    displayPlayers(players);
                }
            } catch (IOException | ClassNotFoundException e) {
                messagesArea.appendText("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }

    private void displayPlayers(List<Player> players) {
        messagesArea.clear();
        for (Player player : players) {
            messagesArea.appendText(player.printInfo());
        }
    }
}
