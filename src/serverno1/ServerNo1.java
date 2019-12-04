/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverno1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author igorv
 */
public class ServerNo1 {
    private static Object ToServer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        
        //String caminho = "C:\\Users\\Ksa\\Documents\\NetBeansProjects\\uploadServerNo1\\src\\arquivos\\";
        String caminho = "C:\\Users\\igor.amaral\\Documents\\NetBeansProjects\\arquivos\\";

        try {
            serverSocket = new ServerSocket(4001);
            System.out.println("Subiu o ServerNo1 ");
        } catch (IOException ex) {
            System.out.println("Não é possível configurar o servidor neste número da porta. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Não é possível aceitar a conexão do cliente. ");
        }

        try {
            in = socket.getInputStream();
            
           // System.out.println("teste "+in.toString().getClass().getName().);
            
           
        } catch (IOException ex) {
            System.out.println("Não é possível obter o fluxo de entrada do soquete. ");
        }

        
        try {
            int bytesAmount = 0;
            byte[] bytes = new byte[1024 * 1024];
            //BufferedReader InFromClient = new BufferedReader (new InputStreamReader(socket.getInputStream()));
            //DataOutputStream OutToClient = new DataOutputStream(socket.getOutputStream());
            
            final DataInputStream InFromClient = new DataInputStream(socket.getInputStream());
            final DataOutputStream OutToClient = new DataOutputStream(socket.getOutputStream());            
            int a = InFromClient.read();
            if (a==0) {
                System.out.println(InFromClient.read());
                File env = new File(caminho + "novo.001");
                System.out.println(env.getPath());
                
                FileInputStream fis = new FileInputStream(env);
                BufferedInputStream bis = new BufferedInputStream(fis);
                
                OutputStream out1 = socket.getOutputStream();
                while ((bytesAmount = bis.read(bytes)) > 0) {
                    out1.write(bytes, 0, bytesAmount);
                    //OutToClient.write(bytes, 0, bytesAmount);
                }
                System.out.println("envio do arquivo 001 para o cliente");
            } else {
                out = new FileOutputStream(caminho + "novo.001");
                           //System.out.println("out "+ out.getClass().getName());

                String caminhoServer = (caminho + "novo.001");

                File busca = new File(caminhoServer);

                FileInputStream fis1 = new FileInputStream(busca);
                BufferedInputStream bis1 = new BufferedInputStream(fis1);

               //        try (   FileInputStream fis = new FileInputStream(busca);
                //                BufferedInputStream bis = new BufferedInputStream(fis)) {
                //            
                //        }
                //        byte[] arq1 = busca;;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //byte[] bytes = new byte[16 * 1024];

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }

                out.close();
                in.close();
                socket.close();
                serverSocket.close();               
            }


            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
//        try {
//            //System.out.println("Gettt "+in.());
//            System.out.println("asd");
//       
//        } catch (FileNotFoundException ex) {
//            System.out.println("Arquivo não encontrado. ");
//        }
    }
}


