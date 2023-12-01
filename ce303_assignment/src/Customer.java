import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Customer {
    //hshhssh
    final int port = 1234;
    static Socket socket ;
    static InputStreamReader inputStreamReader;
    static OutputStreamWriter outputStreamWriter;
    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;
    Scanner scan;
    public Customer(String name) throws Exception {
        socket = new Socket("localhost", port);
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(name);
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


    public static void main(String[] args) throws IOException {
        System.out.println("'Barista' - Good day, what is your name: ");
      try{
          Scanner scanner = new Scanner(System.in);
          String name = scanner.nextLine();
          try{
              Customer customer = new Customer(name);
              System.out.println("Logged in successfully.");
              while (true){

              }

          } catch (Exception e) {
              socket.close();
              inputStreamReader.close();
              outputStreamWriter.close();
              bufferedReader.close();
              bufferedWriter.close();
          }

      } catch (IOException e) {
          throw new RuntimeException(e);
      }
    }


}
