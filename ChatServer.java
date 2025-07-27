
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("🟢 Server started on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("👤 New client connected: " + socket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("❌ Server error: " + e.getMessage());
        }
    }

    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler ch : clientHandlers) {
            if (ch != sender) {
                ch.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("Enter your username:");
                username = in.readLine();
                System.out.println("✅ " + username + " joined the chat.");
                broadcastMessage("👋 " + username + " has joined the chat!", this);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("[" + username + "]: " + message);
                    broadcastMessage("[" + username + "]: " + message, this);
                }
            } catch (IOException e) {
                System.out.println("❌ Client error: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    removeClient(this);
                    broadcastMessage("🚪 " + username + " left the chat.", this);
                    System.out.println("❎ " + username + " disconnected.");
                } catch (IOException e) {
                    System.out.println("❌ Error closing client socket: " + e.getMessage());
                }
            }
        }

        void sendMessage(String message) {
            out.println(message);
        }
    }
}
