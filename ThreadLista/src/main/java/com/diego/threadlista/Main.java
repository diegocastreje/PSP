/*
 * Crear lista de 50 numeros
    Crear metodo para llenar una lista con 50 numeros positivos
    Detectar numeros primos
    Detectar numeros impares
    Calcular la sumna de todos los numeros pares de la lista
    Sacar por pantalla los resultados

 */
package com.diego.threadlista;

import java.util.Random;


/**
 *
 * @author diego
 */
public class Main {
    
    static int lista[] = new int[50];
    
    public static void main(String[] args) throws InterruptedException {
        
        rellenarLista();
        comprobarRepetidos();
        
        Primos tprimos = new Primos(lista);
        Impares timpares = new Impares(lista);
        Pares tpares = new Pares(lista);
        Sumar tsumar = new Sumar(lista);
        
        tprimos.start();
        tprimos.join();
        
        timpares.start();
        timpares.join();
        
        tpares.start();
        tpares.join();
        
        tsumar.start();
        tsumar.join();
    }
    
    private static void rellenarLista(){
        
        Random r = new Random();
        
        for(int i = 0; i < lista.length; i++ ){
            lista[i] = r.nextInt(100 -1)+1;
            
           }
        }
    
    public static void comprobarRepetidos(){
        for(int i = 0; i < lista.length; i++){ 
            for(int u = 0; u < lista.length; u++){
                if(lista[i] == lista[u] && i != u){
                    int rnd = (int)Math.floor(Math.random() * (1 - (100 + 1)) + (100));
                    lista[i] = rnd;
                    i = 0;
                }
            }
        }
    }
                
    }

