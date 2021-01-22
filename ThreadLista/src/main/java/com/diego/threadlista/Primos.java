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
public class Primos extends Thread{

    int[] numeros = new int[50];
    public Primos(int[] numeros) {
        this.numeros = numeros;
    }
    
    @Override
    public synchronized void run() {
        try {
            System.out.println("\nNúmeros primos: ");
            buscarPrimos();
            
            Thread.sleep(500);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Primos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void buscarPrimos() {
         
        for (int i = 0; i <numeros.length; i ++)
            esPrimo(numeros[i]);
        
    }

    boolean esPrimo(int n) {
        for(int i=2;i<n;i++) {
            if(n % i==0)
                return false;
        }
        
        System.out.print(n + " ");
        return true;
    }

    
    
}