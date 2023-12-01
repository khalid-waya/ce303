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

            // Create a new thread to handle the customer
            Thread clientHandlerThread = new Thread(() -> handleCustomer(customerSocket));
            clientHandlerThread.start();
        }
    }

    private void handleCustomer(Socket customerSocket) {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(customerSocket.getInputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(customerSocket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)
        ) {
            String customerName = bufferedReader.readLine();
            System.out.println("New customer: " + customerName);

            // Process customer order or perform other tasks as needed
            String orderResponse = "Welcome to Virtual Cafe, " + customerName + "!";
            bufferedWriter.write(orderResponse);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                customerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
