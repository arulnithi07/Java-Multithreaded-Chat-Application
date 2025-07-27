
import java.io.*;
import java.net.*;

public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // Thread to read messages from the server
            Thread readThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("❌ Disconnected from server.");
                }
            });

            readThread.start();

            // Write messages to the server
            String userMsg;
            while ((userMsg = userInput.readLine()) != null) {
                writer.println(userMsg);
            }

        } catch (IOException e) {
            System.out.println("❌ Could not connect to server: " + e.getMessage());
        }
    }
}
