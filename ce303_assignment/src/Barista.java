import Helpers.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Barista {
    private ServerSocket serverSocket;

    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Barista server started on port " + port);

        while (true) {
            System.out.println("Waiting for a new customer...");
            Socket customerSocket = serverSocket.accept();

            // Create a new ClientHandler instance for each customer
            ClientHandler clientHandler = new ClientHandler(customerSocket);
            Thread clientHandlerThread = new Thread(clientHandler);
            clientHandlerThread.start();
        }

    }



    public static void main(String[] args) {
        Barista barista = new Barista();
        try {
            barista.startServer(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
