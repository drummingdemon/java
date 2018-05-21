/*
 * Listens for incoming connections and counts them.
 * Sends the connected client its' number in line, disconnects
 * and waits for the next connection.
 */
package clientserver2;

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
    static Socket socket;
    static Scanner input;
    static PrintWriter output;
    static ServerSocket listener;
    static int connectionCount = 0;
    
    public static void main(String[] args) throws IOException {
        spawnServer();
    }
    
    public static void spawnServer() throws IOException {
        listener = new ServerSocket(PORT_NUMBER);
        socket = listener.accept();
        output = new PrintWriter(socket.getOutputStream());
        while (true) {
            if (socket.isConnected()) {
                log("connected");
                connectionCount += 1;
                sendMessage(String.valueOf(connectionCount));
                log("closed connection " + connectionCount);
                socket.close();
                output.close();
                socket = listener.accept();
                input = new Scanner(socket.getInputStream());
                output = new PrintWriter(socket.getOutputStream());                
            }
        }
    }
    
    public static void log(String txt) {
        System.out.println(txt);
    }
    
    public static void sendMessage(String txt) throws IOException {
        output.println("You are connection number " + txt);
        output.flush();
    }
}
