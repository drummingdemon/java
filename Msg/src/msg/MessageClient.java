package msg;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
public class MessageClient {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        
        final String GEP = "127.0.0.1";
        final int PORT = 12345;
        
        System.out.println("filename: "+args[0]);
       
        
        
        try(
            Socket s = new Socket(GEP, PORT);
            Scanner scSock = new Scanner(s.getInputStream());
            PrintWriter pwSock = new PrintWriter(s.getOutputStream());
                
            PrintWriter pwFile = new PrintWriter(args[0]);
            //PrintWriter pwFile = new PrintWriter(new File("out.txt"))    
        ){

            //String fileName = args[0];
            /*int from = Integer.parseInt(args[1]);
            pwSock.println(from);
            int to = Integer.parseInt(args[2]);
            pwSock.println(to);
            pwSock.flush();
            
            while (scSock.hasNextLine()) {
                String num = scSock.nextLine();

                pwFile.println(num);
            }
            pwFile.flush();
            
            for(int i = from; i < to; i++){
                String num = scSock.nextLine();

                pwFile.println(num);
            }
            pwFile.flush();
            
            int msgCount = Integer.parseInt(args[3]);
            pwSock.println(msgCount);
            for(int i = 4; i < 4+msgCount; i++){
                pwSock.println(args[i]);
            } 
            pwSock.flush();*/
            //pwSock.println("DONE");
            
            
            Thread t1 = new Thread(() -> {
                int argsidx = 0;
                while (argsidx+1 < args.length) {
                    String doneOrFrom = args[++argsidx];
                    if (doneOrFrom.equals("DONE")) {
                        System.out.println("!!!DONE!!!");
                        pwSock.println(doneOrFrom);
                        pwSock.flush();
                        break;
                    }
                    int from = Integer.parseInt(doneOrFrom);
                    pwSock.println(from);
                    int to = Integer.parseInt(args[++argsidx]);
                    pwSock.println(to);
                    int msgCount = Integer.parseInt(args[++argsidx]);
                    pwSock.println(msgCount);
                    System.out.println("from : "+ from);
                    System.out.println("to   : "+ to);
                    System.out.println("count: "+ msgCount);
                    for (int i = 0; i < msgCount; i++) {
                        argsidx++;
                        pwSock.println(args[argsidx]);
                        System.out.println(" -> : "+ args[argsidx]);
                    }
                    pwSock.flush();
                }
            });
            t1.start();
            
            Thread t2 = new Thread(() -> {
		while (scSock.hasNextLine()) {
                    String data = scSock.nextLine();

                    pwFile.println(data);
                }
                pwFile.flush();
            });
            t2.start();
            
            t1.join();
            t2.join();
        }
        
    }
}
