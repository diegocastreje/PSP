/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.jugador;

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
public class Jugador {
    
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        
        int numeroPuerto = 6000;
        
        Socket jugador = new Socket(host, numeroPuerto);
        
        PrintWriter flujoSalida = new PrintWriter(jugador.getOutputStream(), true);       
        BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(jugador.getInputStream()));       
        
        Scanner s = new Scanner(System.in);
        
        while(!HiloServidor.ganar.get()){
            String mensaje = flujoEntrada.readLine();
            System.out.println(mensaje);
            if(mensaje.trim().equals("HAS GANADO!") || mensaje.trim().equals("Este era el número ganador, pero otro jugador fue más rápido...HAS PERDIDO"))
                break;
            flujoSalida.println(s.next());                          
        }
        flujoEntrada.close();
        flujoSalida.close();
        jugador.close();
    }
    
}
