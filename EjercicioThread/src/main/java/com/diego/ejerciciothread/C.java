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
public class C extends Thread{
    
    private static int[] fibonacci = new int[10];
    private static int suma;
    private static int numero1 = 0;
    private static int numero2 = 1;
    private static int no = 0;
    
    public C(int suma){
        this.suma = suma;
    }
    
    public void run(){
        fibonacci();
        for(int i = 0; i < fibonacci.length; i++){
            if(fibonacci[i] == suma){
                System.out.println("El resultado de la suma está dentro de la sucesión de fibonacci");
            }else{               
                no = no + 1;               
            }
        }
        if( no == fibonacci.length){
            System.out.println("El resultado de la suma no está dentro de la sucesión de fibonacci");         
        }
    }
    private static int[] fibonacci(){
    
        for(int i = 0; i < fibonacci.length; i=i+2){
                
            fibonacci[i] = numero1;
            numero1 = numero1 + numero2;
            fibonacci[i+1] = numero2;
            numero2 = numero1 + numero2;
            
        }
        
        return fibonacci;
    }
}
