/*
 * Connects to server, receives the connection number and terminates
 */
package clientserver2;

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
    static int PORT = 12345;
    static Socket socket;
    static Scanner input;
    
    public static void main(String[] args) throws IOException {
        connect(HOST, PORT);
    }
    
    public static void connect(String host, int port) throws IOException {
        socket = new Socket(HOST, PORT);
        input = new Scanner(socket.getInputStream());
        while (input.hasNext() && socket.isConnected()) {
            log(input.nextLine());
        }
        log("Client disconnected");
    }
    
    private static void log(String txt) {
        System.out.println(txt);
    }
}
