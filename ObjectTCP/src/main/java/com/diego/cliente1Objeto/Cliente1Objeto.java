/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.cliente1Objeto;

/**
 *
 * @author diego
 */
import java.io.*;
import java.net.*;
import com.diego.pojo.Persona;

public class Cliente1Objeto {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int Puerto = 6000;
        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, Puerto);
        
        //Flujo de entrada para objetos
        ObjectInputStream personaEntra = new ObjectInputStream(cliente.getInputStream());
        
        //Se recibe un objeto
        Persona dato = (Persona) personaEntra.readObject();//recibimos objeto
        
        //Modificamos el objeto para ver como funciona
        dato.setNombre("Juan Ramos");
        dato.setEdad(80);
        
        //Flujo de salida para objetos
        ObjectOutputStream personaSale = new ObjectOutputStream(cliente.getOutputStream());
        
        //Se envia el objeto
        personaSale.writeObject(dato);
        System.out.println("Envio: " + dato.getNombre() + "*" + dato.getEdad());
        
        //Cerramos Streams y Sockets
        personaEntra.close();
        personaSale.close();
        cliente.close();
    }
}
