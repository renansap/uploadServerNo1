/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverno1;

import java.io.File;
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

        ServerSocket serverSocket = null;
        
        String caminho = "C:\\Users\\Ksa\\Documents\\NetBeansProjects\\uploadServerNo1\\src\\arquivos\\";

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
            //System.out.println("Gettt "+in.());
            out = new FileOutputStream(caminho + "novo1.txt");
            //System.out.println("out "+ out.getClass().getName());
            
            
           //System.out.println("Arquivo Copiando SeverNo1");
        

        byte[] bytes = new byte[16*1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado. ");
        }
    }
}


