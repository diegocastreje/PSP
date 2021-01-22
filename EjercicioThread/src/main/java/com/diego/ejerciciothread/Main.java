/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.ejerciciothread;

import java.util.Scanner;

/**
 *
 * @author diego
 */
/*
Introducir nombre
A-Sustituir vocales y colocar en su lugar@
B-Contar caracteres y calculará la suma de todos los números de la cuenta
Ejemplo: Javier --> J=1 a=2 v=3 i=4 e=5 r=6 --> 1+2+3+4+5+6 = 21
C-Con el resultado de la suma del apartado B teneis que decir si pertenece a la serie de Fibonacci
*/
public class Main {
    
    private static String nombre;
    
    public static void main(String[] args) {
        
        System.out.println("Introduce un nombre");
        nombre = scan();
        
        A tA = new A(nombre);
        B tB = new B(nombre); 
        tA.start();
        tB.start();
        C tC = new C(tB.acumulador);
        tC.start();
    
    }  
    private static String scan(){
        Scanner s = new Scanner(System.in);
        String scan = s.next();
        return scan;
    }
}
