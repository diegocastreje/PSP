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
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class HiloServidor extends Thread{
    
    BufferedReader flujoEntrada;
    PrintWriter flujoSalida;
    Socket socket = null;
    static ArrayList<Integer> numeros = new ArrayList<Integer>();
    public static AtomicBoolean activo = new AtomicBoolean(true);
    static long s;
    
    public HiloServidor(Socket s) throws IOException {
        socket = s;
        flujoSalida = new PrintWriter(socket.getOutputStream(), true);
        flujoEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void run(){
        s = System.nanoTime()/1000000000;

        while(activo.get()){
            System.out.println(System.nanoTime()/1000000000-s);
            if(System.nanoTime()/1000000000-s >= 20){
                System.out.println("Ha llegao");
                activo.set(false);
            }            
            flujoSalida.println("Introduce un número");
            String mensaje = null; 
            try {
                mensaje = flujoEntrada.readLine();
            } catch (IOException ex) {
                Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            int numero = Integer.parseInt(mensaje);
            numeros.add(numero);            
        }
        comprobarPrimos();
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

    private void comprobarPrimos(){
        int acumulador = 0;
        for(int i = 0; i < numeros.size(); i++){
            int n = numeros.get(i);
            boolean comprobante = esPrimo(n);
            if(comprobante)
                acumulador++;
            
                            
        }
        System.out.println("Hay " + acumulador + " números primos en total");
    }
    
    private boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        while ((primo) && (contador!=numero)){
            if (numero % contador == 0)
                primo = false;
            contador++;
        }
        return primo;
    }
    
}
