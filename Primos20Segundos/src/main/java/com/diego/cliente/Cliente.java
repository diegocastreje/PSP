/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.cliente;

import com.diego.servidor.HiloServidor;
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
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        
        int numeroPuerto = 6000;
        
        Socket cliente = new Socket(host, numeroPuerto);
        
        PrintWriter flujoSalida = new PrintWriter(cliente.getOutputStream(), true);       
        BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));  
        Scanner scan = new Scanner(System.in);
        
        while(HiloServidor.activo.get()){
            System.out.println(flujoEntrada.readLine());
            flujoSalida.println(scan.next());
        }
        
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close(); 
    }
    
}
