package msg;


import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jude
 */
public class MessageServer {
    
    //static String[] messages = {};
    static List<String> messages = new ArrayList<>();
    static {
        messages.addAll(Arrays.asList("ghg","dwf","hzjtj"));
    }
    
    public static void main(String[] args) throws Exception {

        final int PORT = 12345;
        
        try (
                ServerSocket ss = new ServerSocket(PORT);
                Socket s = ss.accept();
                Scanner sc = new Scanner(s.getInputStream(), "utf-8");
                PrintWriter pw = new PrintWriter(s.getOutputStream());) {
            
            /*while(sc.hasNextLine()){
                String in = sc.nextLine();
                if( in.equals("DONE")){
                       break;
                   }else{
                    //String[] parts = in.split(" ");    
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int msgCount = Integer.parseInt(parts[2]);
                //kimenoSzoveg += String.valueOf(szamkonverter(in)) + " ";
                //int kimenoSzoveg = szamkonverter(in);
                //pw.println(kimenoSzoveg);
                }
            }*/
            
            while(sc.hasNextLine()){
                String in = sc.nextLine();
                if( in.equals("DONE")){
                       break;
                   }else{
                    //String in = sc.nextLine();
                    int from = Integer.parseInt(in);
                    in = sc.nextLine();
                    int to = Integer.parseInt(in);

                    //toClient(from, to);
                    for(int i = from; i < to; i++){
                        pw.println(messages.get(i));
                    }
                    pw.flush();

                    in = sc.nextLine();
                    int msgCount = Integer.parseInt(in);

                    for(int i = 0; i < msgCount; i++){
                        String data = sc.nextLine();
                        fromToClient(data);
                    }

                    }
            }
        }

    }
    
    private static void fromToClient(String data){
            messages.add(data);
    }
    
    /*private static String toClient(int from, int to){
        for(int i = from; i <= to; i++){
            return messages[i];
        }
    }*/
}
