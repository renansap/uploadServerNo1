/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverno1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author igorv
 */
public class ServerNo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

//        ServerSocket servidor = new ServerSocket(4001);
//        System.out.println("Porta 4001 aberta!");
//
//        Socket cliente = servidor.accept();
//        System.out.println("Nova conexão com o cliente " +     
//            cliente.getInetAddress().getHostAddress()
//        );
//
//        Scanner s = new Scanner(cliente.getInputStream());
//        while (s.hasNextLine()) {
//            System.out.println(s.nextLine());
//        }
//
//        s.close();
//        servidor.close();
//        cliente.close();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4001);
            System.out.println("Subiu o servidor ");
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }

        try {
            out = new FileOutputStream("C:\\Users\\igorv\\Documents\\NetBeansProjects\\uploadJava\\Upload\\build\\classes\\imagens\\novo.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16 * 1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}


