/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Cliente {
    
    Socket cliente;
    private String host = "localhost";
    private int puerto = 6000;
    String mensaje;
    
    public Cliente(String host, int puerto) throws IOException{
        this.host = host;
        this.puerto = puerto;
        
        cliente = new Socket(host, puerto); 
    }

    public void init() throws IOException {
        
        PrintWriter flujoSalida = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        
        mensaje = flujoEntrada.readLine();
        System.out.println(mensaje);
        String nombre = scan();
        
        flujoSalida.print(nombre);
                   
    }
    
    private static String scan(){
        Scanner s = new Scanner(System.in);
        String sc = s.next();
        return sc;
    }
}
