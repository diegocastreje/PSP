/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.cliente;

/**
 *
 * @author diego
 */
/*
El servidor recibe un Alumno(nombre, edad y nota), añade el objeto a la lista y extrae información para enviar
Crear una lista que vaya incluyendo a cada Alumno creado en la lista e implementar un método "pasar lista" que
muestre por pantalla todos los alumnos creados hasta ese momento.

1ºCliente:
-Petición de conexión
-Mostrar un menú para insertar datos de tipo primitivo(String nombre, int edad, double nota)
-Crear objeto Alumno

2ºServidor:
-Leer objeto
-Insertar objeto en lista
-Extraer la información de cada objeto para enviar datos primitivos al cliente
-El cliente leerá los datos primitivos y los mostrará por pantalla
-El servidor pasará el método pasarLista();

*/

import com.diego.pojo.Alumno;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    
    private static Scanner s = new Scanner(System.in);
    private static String nombre;
    private static int edad;
    private static double nota;
    private static Alumno alumno;
    private static boolean condicion = true;
    private static boolean condicion2;                       
    
    public static void main(String[] args) throws IOException {
        
        String host = "localhost";
        int numeroPuerto = 6000;        
        System.out.println("Solicitando la conexión...");
        Socket cliente = new Socket(host, numeroPuerto); 
        System.out.println("Petición de conexión aceptada...");
        
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
        BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter flujoSalida = new PrintWriter(cliente.getOutputStream(), true);

        while(condicion == true){
            
            condicion2 = true;
        
        crearAlumno();        
        
        outObjeto.writeObject(alumno);       
        
        String datos = flujoEntrada.readLine();
        System.out.println(datos);     
        
        System.out.println("\n¿Quieres añadir otro alumno?");
        while(condicion2 ==true){
        String opcion = scan();
        if(opcion.equals("no")){
            System.out.println("Cerrando programa...");
            flujoSalida.println("si");
            flujoSalida.close();
            flujoEntrada.close();
            outObjeto.close();
            cliente.close();
            condicion = false;
            condicion2 = false;
        }else if(opcion.equals("si")){
            flujoSalida.println("no");
            condicion2 = false;
        }else{
            System.out.println("Por favor introduzca 'si' o 'no'");
        }
        }
        }         
    }
    
    private static void crearAlumno(){
        System.out.println("A continuación debe mostrar los datos que tendrá su objeto Alumno\n");
        System.out.println("Introduce su nombre: ");
        nombre = scan();
        System.out.println("Introduce su edad: ");
        edad = scanInt();
        System.out.println("Introduce su nota: ");
        nota = scanDouble();
        
        alumno = new Alumno(nombre, edad, nota);
    }
    
    private static String scan(){    
        String nombre = s.next();
        return nombre;
    }
    private static int scanInt(){
        int edad = s.nextInt();
        return edad;
    }    
    private static double scanDouble(){
        double nota = s.nextDouble();
        return nota;
    }       
}
