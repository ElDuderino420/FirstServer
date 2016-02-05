package firstserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstServer {

    static String ip;
    static int port;
    ServerSocket serverSock;

    

    public void startServer() throws IOException {

        serverSock = new ServerSocket();
        serverSock.bind(new InetSocketAddress(ip, port));
        System.out.println("Server Started, listening on port: " + port);
        while (true) {
            Socket suck = serverSock.accept();//Remember Blocking call
            ClientThread no = new ClientThread(suck);
            no.start();
            
        }

    }

    public static void main(String[] args) throws IOException {

        port = Integer.parseInt(args[0]);
        ip = args[1];
        new FirstServer().startServer();

    }

}
