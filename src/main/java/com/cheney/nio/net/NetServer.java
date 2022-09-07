package com.cheney.nio.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetServer {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            Socket socket = serverSocket.accept();

            Scanner scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNextLine()){
                String next = scanner.nextLine();
                System.out.println(next);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
