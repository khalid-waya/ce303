package Helpers;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket; private final Customer_Order customerOrder;

    public ClientHandler(Socket socket, Customer_Order customerOrder) {
        this.socket = socket;
        this.customerOrder = customerOrder;
    }

    @Override
    public void run() {
        String customerName;
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)

        ) {
            customerName = bufferedReader.readLine();
            System.out.println("New customer: " + customerName);

            // Process customer order or perform other tasks as needed
            String orderResponse = "Welcome to Virtual Cafe, " + customerName + "!";
            bufferedWriter.write(orderResponse);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            // Close the socket
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
