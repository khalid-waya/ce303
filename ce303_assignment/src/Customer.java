import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Customer implements AutoCloseable{
    //hshhssh
    final int port = 1234;
    static Socket socket ;
    static InputStreamReader inputStreamReader;
    static OutputStreamWriter outputStreamWriter;
    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;
    Scanner scan;
    public Customer(String host, int port) throws Exception {
        socket = new Socket(host, port);
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        scan = new Scanner(System.in); // Initialize the Scanner
        String line = scan.nextLine();
        if (line.trim().equalsIgnoreCase("success")) throw new Exception(line);
    }

    public String[] getOrders() throws IOException {
        bufferedWriter.write("ORDERS");
        String line = bufferedReader.readLine();
        int numberOfOrders = Integer.parseInt(line);
        String[] orders = new String[ numberOfOrders ];
        for (int i = 0; i < numberOfOrders; i++){
            line = bufferedReader.readLine();
            orders[i] = line;
        }
        return orders;
    }

//    public int[] getAccountNumbers()
//    {   writer.println("ACCOUNTS");                      // Send "command"
//        String line = reader.nextLine();                 // Read response (expecting: number of accounts)
//        int numberOfAccounts = Integer.parseInt( line ); // Parse response
//        int [] accounts = new int[ numberOfAccounts ];   // Create array to hold accounts
//        for (int i = 0; i < numberOfAccounts; i++) { line = reader.nextLine();  accounts[ i ] = Integer.parseInt( line ); } // Populate accounts
//        return accounts;   // Return accounts array (or, to be exact, a reference to the array)
//    }

    private static void getAndSendUserName() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("'Server' - what is your name: ");
        String userName = scanner.nextLine();

        try {
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String response = bufferedReader.readLine();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
        scan.close();
    }
    public static void main(String[] args) {
        System.out.println("Press enter to walk in to cafe");
        try (Customer customer = new Customer("localhost", 1234)) {
            System.out.println("Welcome to the Virtual Cafe!");
            getAndSendUserName();
            while (true) {

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
