/*
 * Upon receiving 
 */
package clientserver1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author drummer
 */
public class Server1 {
    
    static int PORT_NUMBER = 12345;
    static Socket socket;
    static Scanner input;
    static PrintWriter output;
    static ServerSocket listener;
    
    public static void main(String args[]) throws Exception  {  
        listener = new ServerSocket(PORT_NUMBER);
        socket = listener.accept();
        input = new Scanner(socket.getInputStream(), "utf-8");
        output = new PrintWriter(socket.getOutputStream(), true);
        String msg;
        do {
            msg = input.nextLine();
            if (msg.equals("Hello")) {
                sendMessage("BYE");
            }
            log("client says: " + msg);
        } while (!msg.equals("BYE"));
        log("terminating");
    }
    
    static void sendMessage(String msg) {
        output.println(msg);
        output.flush();
    }
    
    static void log(String txt) {
        System.out.println("[server] " + txt);
    }
    
}
