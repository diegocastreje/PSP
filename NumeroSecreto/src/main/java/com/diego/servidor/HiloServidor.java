/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class HiloServidor extends Thread{
    
    public static AtomicBoolean ganar = new AtomicBoolean(false);
    BufferedReader flujoEntrada;
    PrintWriter flujoSalida;
    Socket socket = null;
    public static String ganador;
    String respuesta = null;

    public HiloServidor(Socket s) throws IOException {
        socket = s;
        flujoSalida = new PrintWriter(socket.getOutputStream(), true);
        flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void run(){

        System.out.println("Comunica con jugador: " + socket.toString());
        
        while(!ganar.get()){
            for(int jugadores = 1;jugadores < 3; jugadores++){
                          
            while(Servidor.partidaEnCurso){
                flujoSalida.println("Intenta adivinar un número del 0 al 10"); 
                try {
                    respuesta = flujoEntrada.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("El jugador" + socket.toString() + "ha dicho: " + respuesta);
                
                if(respuesta.trim().equals(Servidor.secreto.trim())){
                    boolean comprobar = ganar.get();
                    if(!comprobar){
                        ganar.set(true);
                        flujoSalida.println("HAS GANADO!");
                        System.out.println("Ha ganado " + socket.toString());                       
                    }else if(comprobar){
                        ganar.set(true);
                        flujoSalida.println("Este era el número ganador, pero otro jugador fue más rápido...HAS PERDIDO");
                    }
                    Servidor.partidaEnCurso = false;
                }  
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            }              
        }
        if(!respuesta.trim().equals(Servidor.secreto.trim()))
            flujoSalida.println("Has perdido...");    
        
        try {
            flujoEntrada.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        flujoSalida.close();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
