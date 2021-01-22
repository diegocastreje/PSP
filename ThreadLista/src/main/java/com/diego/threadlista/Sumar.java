/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.diego.threadlista;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author diego
 */
public class Sumar extends Thread{
    
    int[] numeros = new int[50];
    int suma = 0;
    
    public Sumar(int[] numeros) {
        this.numeros = numeros;
    }
    

    @Override
    public synchronized void run() {
        try {
            System.out.print("\nSuma de los números pares: ");
            
            for (int i = 0; i < numeros.length; i ++){
                if(numeros[i] % 2 == 0)  
                suma += numeros[i];           
            }            
            System.out.println(suma);
            
            Thread.sleep(500);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Sumar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    
}