import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Customer implements AutoCloseable {
    //hshhssh
    final int port = 1234;
    Socket socket ;
    InputStreamReader inputStreamReader;
    OutputStreamWriter outputStreamWriter;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
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


    public static void main(String[] args) {
        Socket socket = null;

        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try{
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("Enter name");
            Scanner scan = new Scanner(System.in);

            while (true){

                String msgToSend =  scan.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Barista - '"+ bufferedReader.readLine()+ "'");


                    if (msgToSend.equalsIgnoreCase("BYE")){break;}
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (socket != null) socket.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
            }catch (IOException e){
            }
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
