import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ChatServer accepts multiple clients and broadcasts their messages.
 */
public class ChatServer {

    private static final int DEFAULT_PORT = 5000;

    // Assigns a different ID to each new client during this server run.
    private static final AtomicInteger nextUserId = new AtomicInteger(1);

    // Stores each connected user and their connection handler.
    private static final Map<Integer, ClientHandler> clients =
            new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = DEFAULT_PORT;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number.");
                return;
            }
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat server started on port " + port);
            System.out.println("Waiting for clients...");

            // Keep accepting new client connections.
            while (true) {
                Socket socket = serverSocket.accept();

                int userId = nextUserId.getAndIncrement();
                ClientHandler handler = new ClientHandler(socket, userId);

                // Start a separate thread for each connected client.
                Thread clientThread = new Thread(handler);
                clientThread.start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    /**
     * Sends a message to every currently connected client.
     */
    private static void broadcast(String message) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(message);
        }

        System.out.println(message);
    }

    /**
     * Handles communication with one connected client.
     */
    private static class ClientHandler implements Runnable {

        private final Socket socket;
        private final int userId;
        private PrintWriter output;

        public ClientHandler(Socket socket, int userId) {
            this.socket = socket;
            this.userId = userId;
        }

        @Override
        public void run() {
            try (
                Socket clientSocket = socket;
                BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream(),
                        StandardCharsets.UTF_8
                    )
                );
                PrintWriter writer = new PrintWriter(
                    clientSocket.getOutputStream(),
                    true,
                    StandardCharsets.UTF_8
                )
            ) {
                output = writer;

                // Register the client before announcing the connection.
                clients.put(userId, this);

                sendMessage(
                    "Welcome! Your user ID is User " + userId
                );

                broadcast("User " + userId + " joined the chat.");

                String message;

                // Read and broadcast messages until the client disconnects.
                while ((message = input.readLine()) != null) {
                    if (message.equalsIgnoreCase("/quit")) {
                        break;
                    }

                    if (!message.isBlank()) {
                        broadcast("User " + userId + ": " + message);
                    }
                }

            } catch (IOException e) {
                System.out.println(
                    "Connection problem with User " + userId
                );

            } finally {
                // Remove the client when its connection ends.
                clients.remove(userId);

                broadcast("User " + userId + " left the chat.");
            }
        }

        /**
         * Synchronizes writes so messages from different threads
         * do not interfere with one another.
         */
        public synchronized void sendMessage(String message) {
            if (output != null) {
                output.println(message);
            }
        }
    }
}