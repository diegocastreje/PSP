/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author diego
 */
public class Servidor {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss;
        int puerto = 6000;
        ss = new ServerSocket(puerto);
        System.out.println("Servidor iniciado...");
        
        while(true){
            Socket client = new Socket();
            client = ss.accept();
            System.out.println("\nCliente conectado...");
            HiloServidor t = new HiloServidor(client);
            t.start();
        }
    }
}
