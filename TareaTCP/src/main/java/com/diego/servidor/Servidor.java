/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Servidor {
    
    ServerSocket ss;
    int puerto = 6000;
        
    public Servidor(int puerto) throws IOException{
        this.puerto = puerto;
        
        ss = new ServerSocket(puerto);
    }
    
    public void init() throws IOException{
        
        Socket servidor = ss.accept();
        System.out.println("Conexión iniciada...");
        
        PrintWriter flujoSalida = new PrintWriter(servidor.getOutputStream(), true);
        BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));        
        
        flujoSalida.print("¿Cuál es tu nombre?");
        String nombre = flujoEntrada.readLine();
        System.out.println(nombre);
    }
    
        private static String scan(){
        Scanner s = new Scanner(System.in);
        String sc = s.next();
        return sc;
    }
}
