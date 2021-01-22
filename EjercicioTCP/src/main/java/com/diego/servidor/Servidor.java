/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

/**
 *
 * @author diego
 */


import com.diego.pojo.Alumno;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    
    private static ArrayList<Alumno> alumnos = new ArrayList<Alumno>();;
    private static String nombre;
    private static int edad;
    private static double nota;
    private static boolean condicion = true;
    private static String cierre = "no";
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        
        int puerto = 6000;  
        ServerSocket ss = new ServerSocket(puerto);
        Socket servidor = ss.accept();
        System.out.println("Conexión iniciada...");
        
        
        ObjectInputStream inObjeto = new ObjectInputStream(servidor.getInputStream());
        PrintWriter flujoSalida = new PrintWriter(servidor.getOutputStream(), true);
        BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
        
        while(condicion == true){
        
        Alumno alumno = (Alumno) inObjeto.readObject();
        
        alumnos.add(alumno);
        
        nombre = alumno.getNombre();
        edad = alumno.getEdad();
        nota = alumno.getNota();
        
        String datos = "Nombre: " + nombre + " Edad: " + edad + " Nota: " + nota;
         
        flujoSalida.println(datos); 
        
        System.out.println("Lista de alumnos: ");
        pasarLista();
        cierre = flujoEntrada.readLine();
        if(cierre.equals("si")){
            System.out.println("Cerrando programa...");
            flujoEntrada.close();
            inObjeto.close();
            flujoSalida.close();
            servidor.close(); 
            ss.close();
            condicion = false;
        }
                  
        }
    }
    
    private static void pasarLista(){       
        for(int i = 0; i < alumnos.size(); i++)
            System.out.println(alumnos.get(i).getNombre()+"*"+alumnos.get(i).getEdad()+"*"+alumnos.get(i).getNota() + "\n");       
    }
}
