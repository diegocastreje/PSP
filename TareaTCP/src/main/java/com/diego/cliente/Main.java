/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.cliente;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author diego
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000;
        System.out.println("Solicitando la conexión...");
        Cliente cliente = new Cliente(host, puerto);
        System.out.println("Petición de conexión aceptada...");
        cliente.init();               
    }
    
}
