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
public class Impares extends Thread{
    
    private int numeros[];
    
    public Impares(int numeros[]){
        this.numeros = numeros;
    }
    
    public synchronized void run(){
                
          try {
              System.out.println("\nNúmeros impares: ");
              for (int i = 0; i < numeros.length; i ++){
                  if(numeros[i]%2 != 0){
                      System.out.print(numeros[i] + " ");
                  }
                  
              }
              Thread.sleep(500);
          } catch (InterruptedException ex) {
              Logger.getLogger(Pares.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
