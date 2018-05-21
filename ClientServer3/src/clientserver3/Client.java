/*
 * Connects, sends the name then trades message lines until 'EXIT' is sent
 */
package clientserver3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author drummer
 */
public class Client {
    static String HOST = "127.0.0.1";
    static int PORT_NUMBER = 12345;
    static Socket socket;
    static Scanner input;
    static Scanner remote;
    static PrintWriter output;
    
    public static void main(String[] args) throws IOException {
        connect(HOST, PORT_NUMBER);
    }
    
    public static void connect(String host, int port) throws IOException {
        socket = new Socket(HOST, PORT_NUMBER);
        input = new Scanner(System.in);
        remote = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream());
        System.out.print("Name: ");
        String name = input.nextLine();
        sendMessage(name);
        System.out.print("Message: ");
        String msg = input.nextLine();
        sendMessage(msg);
        while (socket.isConnected() && remote.hasNext()) {
            System.out.println(remote.nextLine());
            System.out.print("Message: ");
            msg = input.nextLine();
            sendMessage(msg); 
            if (msg.equals("EXIT")) {
                break;
            }           
        }
        remote.close();
        input.close();
        output.close();
    }
    
    private static void sendMessage(String txt) {
        output.println(txt);
        output.flush();
    }
    
    private static void log(String txt) {
        System.out.println(txt);
    }
}
