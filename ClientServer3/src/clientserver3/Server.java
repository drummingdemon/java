/*
 * Clients 1 & 2 send their names, respectively after one another,
 * then they trade messages until one of them sends 'EXIT'
 */
package clientserver3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author drummer
 */
public class Server {
    static int PORT_NUMBER = 12345;
    static Socket socket1;
    static Socket socket2;
    static ServerSocket listener;
    static PrintWriter output1;
    static PrintWriter output2;
    static Scanner input1;
    static Scanner input2;
    
    public static void main(String[] args) throws IOException {
        spawnServer();
    }
    
    public static void spawnServer() throws IOException {
        listener = new ServerSocket(PORT_NUMBER);
        log("Waiting for client 1...");
        socket1 = listener.accept();
        log("Waiting for client 2...");
        socket2 = listener.accept();
        input1 = new Scanner(socket1.getInputStream());
        input2 = new Scanner(socket2.getInputStream());
        output1 = new PrintWriter(socket1.getOutputStream());
        output2 = new PrintWriter(socket2.getOutputStream());
        log("Client1 name?");
        String name1 = input1.nextLine();
        log("Client2 name?");
        String name2 = input2.nextLine();
        while (true) {
            String msg = input1.nextLine();
            if (msg.equals("EXIT")) {
                break;
            }
            sendMessage(output2, "<" + name1 + ">" + msg);
            msg = input2.nextLine();
            if (msg.equals("EXIT")) {
                break;
            }
            sendMessage(output1, "<" + name2 + ">" + msg);
        }
        socket1.close();
        socket2.close();
    }
    
    private static void log(String txt) {
        System.out.println(txt);
    }
    
    private static void sendMessage(PrintWriter destination, String text) {
        destination.println(text);
        destination.flush();
    }
}
