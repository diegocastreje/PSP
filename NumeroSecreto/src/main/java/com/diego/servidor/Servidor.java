/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author diego
 */
public class Servidor {
    
    public static boolean partidaEnCurso;
    public static String secreto;
    
    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        int puerto = 6000;
        ss = new ServerSocket(puerto);
        System.out.println("Servidor iniciado...");
        
        Random r = new Random();
        secreto = r.nextInt(10) + "";
        System.out.println(secreto);
        
        for(int jugadores = 1; jugadores < 3; jugadores++){
            Socket jugador = new Socket();
            jugador = ss.accept();
            HiloServidor hilo = new HiloServidor(jugador);
            hilo.start();
            if(jugadores < 2)
                System.out.println("Esperando más jugadores (" + jugadores + "/2)");
            if(jugadores == 2){
                partidaEnCurso = true; 
                System.out.println("Jugadores listos.");
            }                
        }   
    }
    
}
