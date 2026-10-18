import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * ChatClient connects to ChatServer, sends messages,
 * and displays messages received from other users.
 */
public class ChatClient {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;

        // Allow the server address and port to be supplied at launch.
        if (args.length > 0) {
            host = args[0];
        }

        if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number.");
                return;
            }
        }

        try (
            Socket socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream(),
                    StandardCharsets.UTF_8
                )
            );
            PrintWriter output = new PrintWriter(
                socket.getOutputStream(),
                true,
                StandardCharsets.UTF_8
            );
            Scanner keyboard = new Scanner(System.in)
        ) {
            System.out.println("Connected to the chat server.");
            System.out.println("Type a message and press Enter to send.");
            System.out.println("Type /quit to leave the chat.");
            System.out.println("------------------------------------");

            // Receive messages independently while the user is typing.
            Thread receiver = new Thread(() -> {
                try {
                    String message;

                    while ((message = input.readLine()) != null) {
                        System.out.println();
                        System.out.println(message);
                        System.out.print("Message: ");
                    }

                    System.out.println("\nThe server closed the connection.");

                } catch (IOException e) {
                    System.out.println("\nConnection to the server ended.");
                }
            });

            receiver.setDaemon(true);
            receiver.start();

            // Read user input and send each line to the server.
            while (keyboard.hasNextLine()) {
                System.out.print("Message: ");
                String message = keyboard.nextLine();

                output.println(message);

                if (message.equalsIgnoreCase("/quit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(
                "Could not connect to the chat server: " + e.getMessage()
            );
            System.out.println(
                "Check that the server is running and the address is correct."
            );
        }
    }
}