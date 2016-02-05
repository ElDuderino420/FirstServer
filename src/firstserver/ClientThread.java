/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class ClientThread extends Thread {

    Socket suck;
    int count;

    public ClientThread(Socket suck) {
        this.suck = suck;
    }

    @Override
    public void run() {

        try {
            OutputStream os = suck.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            Scanner scan;
            boolean stop = false;
            pw = new PrintWriter(suck.getOutputStream(), true);
            scan = new Scanner(suck.getInputStream());
            System.out.println("Waiting for data from client");
            pw.println(new Date().toString() + count++);
            pw.println("Hi client, start sending strings :)");
            while (!stop) {
                String line = scan.nextLine();
                if (line.equals("#stop#") || line.equals("#STOP#")) {
                    stop = true;
                    pw.println("Stopping...");
                    
                }
                else if (line.startsWith("UPPER#")) {
                    pw.println(line.substring(line.indexOf("#")+1).toUpperCase());
                }
                else if (line.startsWith("LOWER#")) {
                    pw.println(line.substring(line.indexOf("#")+1).toLowerCase());
                }
                else if (line.startsWith("REVERSE#")) {
                    String rev = "";
                    line = line.substring(line.indexOf("#")+1);
                    
                    for (int i = line.length()-1; i >= 0; i--) {
                        rev = rev + line.charAt(i);
                    }
                    
                    pw.println(rev);
                }
                else if (line.startsWith("TRANSLATE#")) {
                    
                    if(line.substring(line.indexOf("#")+1).equals("hund")){
                        pw.println("dog");
                    }
                    if(line.substring(line.indexOf("#")+1).equals("dog")){
                        pw.println("hund");
                    }
                    if(line.substring(line.indexOf("#")+1).equals("cat")){
                        pw.println("kat");
                    }
                    if(line.substring(line.indexOf("#")+1).equals("kat")){
                        pw.println("cat");
                    }
                    if(line.substring(line.indexOf("#")+1).equals("fugl")){
                        pw.println("bird");
                    }
                    if(line.substring(line.indexOf("#")+1).equals("bird")){
                        pw.println("fugl");
                    }
                    pw.println("#NOT_FOUND");
                    
                } else {
                    pw.println(line);
                }
                
            }

        } catch (Exception e) {
            System.err.print(e.getMessage());
        }

    }

}
