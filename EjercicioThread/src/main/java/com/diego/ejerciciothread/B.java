/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.ejerciciothread;

/**
 *
 * @author diego
 */
public class B extends Thread{
     
    public static int acumulador = 0;
    private static String nombre;
    
    public B(String nombre){
        this.nombre = nombre;
    }
    
    public void run(){
        
        for(int i = 0; i < nombre.length(); i++){
            
            acumulador = acumulador + i + 1;    
            
        }
        
        System.out.println("La suma de los carácter del nombre " + nombre + " es: " + acumulador);
        
    }
}
