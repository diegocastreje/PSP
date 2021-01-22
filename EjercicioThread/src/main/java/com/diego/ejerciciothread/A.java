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
public class A extends Thread{
    
    private static String nuevoNombre = "";
    private static String nombre;

    public A(String nombre) {
        this.nombre = nombre;
    }
    
    public void run(){
        nombre = nombre.toLowerCase();
        for(int i = 0; i < nombre.length(); i++){
            if(nombre.charAt(i) == 'a' || nombre.charAt(i) == 'e' || nombre.charAt(i) == 'i' || nombre.charAt(i) == 'o' || nombre.charAt(i) == 'u'){
                
                nuevoNombre = nuevoNombre + "@";
                
            }else{
                nuevoNombre = nuevoNombre + Character.toString(nombre.charAt(i));
            }
        }
        System.out.println(nuevoNombre);
    }
    
    
}
