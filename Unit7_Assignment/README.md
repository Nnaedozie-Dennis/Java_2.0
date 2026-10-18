
# Online Chat Application

## Overview

This project is a text based chat application developed in Java. It uses socket programming to allow multiple clients to connect to one central server, send messages, and receive messages from other connected users.

## Requirements

- Java Development Kit (JDK) 17 or later
- A terminal or command prompt
- An IDE such as Visual Studio Code, IntelliJ IDEA, or Eclipse (optional)

## Project Files

- `ChatServer.java`: Accepts connections, assigns user IDs, maintains the connected user list, and broadcasts messages.
- `ChatClient.java`: Connects to the server, accepts keyboard input, sends messages, and displays incoming messages.

## How to Compile

Open a terminal in the folder containing both Java files and run:

```bash
javac ChatServer.java ChatClient.java
```

## How to Run the Application

### Step 1: Start the server

In the first terminal, run:

```bash
java ChatServer
```

The server starts on port 5000 by default and waits for client connections.

### Step 2: Start the first client

Open a second terminal in the same folder and run:

```bash
java ChatClient
```

### Step 3: Start another client

Open a third terminal and run the same command:

```bash
java ChatClient
```

Each client receives a unique user ID from the server. Messages sent by one client are broadcast to all connected clients.

### Step 4: Send messages

Type a message in a client terminal and press Enter. The server broadcasts the message, including the sender's user ID, to every connected client.

To leave the chat, type:

```text
/quit
```

## Using Another Port

To start the server on port 6000, run:

```bash
java ChatServer 6000
```

Connect a client to that port with:

```bash
java ChatClient localhost 6000
```

## Implementation Details

The server uses `ServerSocket` to accept incoming connections and `Socket` to communicate with each client. A separate thread handles each client so that one connection does not prevent the server from accepting other users.

A `ConcurrentHashMap` stores the connected clients, while an `AtomicInteger` generates unique user IDs during the current server run. `BufferedReader` receives messages, and `PrintWriter` sends them. The client uses a separate receiving thread so incoming messages can be displayed while the user enters a message.

The application handles connection errors, removes disconnected clients, and closes sockets and streams when connections end.

## Connection to Course Materials

The implementation applies the Java networking concepts covered in Unit 7, including sockets, client server communication, input and output streams, and multithreading. These concepts are discussed in Eck's *Introduction to Programming Using Java*, Chapter 11, and in Pankaj's Java socket programming tutorial.

## Current Limitations

This is an educational example. Messages are sent as plain text and are not encrypted. The application does not include user passwords, persistent chat history, or a graphical interface. It is intended for local testing and learning rather than secure public deployment.

## Testing Checklist

- [ ] The server starts and waits for connections.
- [ ] Multiple clients connect to the server.
- [ ] Each client receives a unique user ID.
- [ ] A message from one client appears in all connected clients.
- [ ] A client can leave without stopping the server.
- [ ] The server continues working after a client disconnects.
