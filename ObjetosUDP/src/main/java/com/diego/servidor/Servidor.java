/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

import com.diego.pojo.Persona;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Servidor {
    
    private static String nombre;
    private static int edad;
    
    private static Scanner s = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        DatagramSocket server = new DatagramSocket(6000);
        System.out.println("Esperando conexiones...");
        byte[] bytes = new byte[1024]; ; 
                       
        //Recibir paquete
        DatagramPacket paqueteIn = new DatagramPacket(bytes, bytes.length);
        server.receive(paqueteIn); 
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        
        //Construir objeto persona con datos del paquete
        Persona persona = (Persona)in.readObject();
        
        //Recoger datos del objeto persona y representarlos
        nombre = persona.getNombre();
        edad = persona.getEdad();
        
        System.out.println("Datos de persona: " + nombre + ", " + edad + "\n");
        
        //Modificar datos del objeto persona
        System.out.println("Introduzca un nuevo nombre");
        nombre = s.next();
        System.out.println("Introduzca una nueva edad");
        edad = s.nextInt();
      
        persona.setNombre(nombre);
        persona.setEdad(edad);
        
        //Recoger dirección y puerto de cliente
        InetAddress direccionIP = paqueteIn.getAddress();
        int puerto = paqueteIn.getPort();
        
        //Enviar paquete con el objeto Persona
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);       
        out.writeObject(persona);
        bytes = baos.toByteArray();
        DatagramPacket paqueteOut = new DatagramPacket(bytes, bytes.length, direccionIP, puerto);
        server.send(paqueteOut);
        
        System.out.println("Datos de la persona modificada: " + nombre + ", " + edad);
        
        //Cerramos Streams y Socket
        baos.close();
        out.close();
        bais.close();
        in.close();
        server.close();        
    }
    
}
