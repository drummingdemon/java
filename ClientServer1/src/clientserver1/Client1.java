/*
 * Sends "Hello" to a server and disconnects upon hearing "BYE" to which
 * it responds with a similar "BYE" disconnect message
 */
package clientserver1;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author drummer
 */
public class Client1 {
    static String HOST = "127.0.0.1";
    static int PORT = 12345;
    static Socket socket;
    static Scanner input;
    static PrintWriter output;
    static ServerSocket listener;
    
    public static void main(String[] args) throws Exception {
        socket = new Socket(HOST, PORT);
        input = new Scanner(socket.getInputStream(), "utf-8");
        output = new PrintWriter(socket.getOutputStream(), false);
        sendMessage("Hello");
        String msg;
        do {
            msg = input.nextLine();
            log("server says: " + msg);
            sendMessage("BYE");
        } while (!msg.equals("BYE"));
        log("terminating");
        socket.close();
    }
    
    public static void sendMessage(String msg) {
        output.println(msg);
        output.flush();
    }
    
    static void log(String txt) {
        System.out.println("[client] " + txt);
    }
}
