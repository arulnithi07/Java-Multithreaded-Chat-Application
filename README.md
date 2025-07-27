# Java-Multithreaded-Chat-Application


Company name : CODTECH IT SOLUTIONS PVT. LTD
INTERN ID : CTO8DH682
NAME : M.MUTHU ARULNITHI 
DOMAIN : JAVA 
START DATE : 29 june
END DATE : 29 july 
MENTOR : Neela santhos kumar



🎯 Objective:
To build a multithreaded Java-based client-server chat application where multiple clients can communicate with each other in real-time using sockets and threads. This project emphasizes real-time communication, concurrency, and network programming concepts.

📘 Project Overview:
This Java console-based chat system consists of:

Server: Accepts multiple client connections using ServerSocket and handles each client in a separate thread.

Client: Connects to the server, sends messages, and receives messages from other connected clients in real-time.

Key Features:

Handles multiple clients simultaneously using threads

Broadcasts messages from one client to all others

Supports client joining, messaging, and leaving notifications

🧩 How It Works:
Start the server using ChatServer.java

Launch multiple clients using ChatClient.java

Each client enters their username and starts chatting

Server handles all messages and distributes them to all connected clients

💻 Sample Output:
▶ Server Console:
markdown
Copy
Edit
🟢 Server started on port 12345
👤 New client connected: /127.0.0.1
✅ Alice joined the chat.
👤 New client connected: /127.0.0.1
✅ Bob joined the chat.
[Alice]: Hello Bob!
[Bob]: Hey Alice!
▶ Client Console (Alice):
markdown
Copy
Edit
Enter your username:
Alice
👋 Alice has joined the chat!
[Alice]: Hello Bob!
[Bob]: Hey Alice!
▶ Client Console (Bob):
markdown
Copy
Edit
Enter your username:
Bob
👋 Bob has joined the chat!
[Alice]: Hello Bob!
[Bob]: Hey Alice!
