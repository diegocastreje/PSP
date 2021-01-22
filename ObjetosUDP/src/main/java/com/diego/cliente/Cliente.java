/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.cliente;

import com.diego.pojo.Persona;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Cliente {
    
    private static String nombre;
    private static int edad;
    
    private static Scanner s = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Persona persona;
        int puerto = 6000;
        DatagramSocket client = new DatagramSocket();
        InetAddress direccionIP = InetAddress.getLocalHost();
        
        byte[] bytes = new byte[1024]; 
              
        //Crear datos del objeto Persona y construirlo
        System.out.println("Introduzca un nombre");
        nombre = s.next();
        System.out.println("Introduzca una edad");
        edad = s.nextInt();
        persona = new Persona(nombre,edad);
       
        //Enviar objeto Persona y representarlo
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(persona);
        bytes = baos.toByteArray();
        
        DatagramPacket paqueteOut = new DatagramPacket(bytes, bytes.length, direccionIP, puerto);
        client.send(paqueteOut);

        //Representar los datos del objeto Persona
        
        System.out.println("Datos de persona: " + nombre + ", " + edad + "\n");
        
        //Recibir paquete 
        DatagramPacket paqueteIn = new DatagramPacket(bytes, bytes.length);
        client.receive(paqueteIn); 
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        
        //Construir objeto persona con datos del paquete
        persona = (Persona)in.readObject();
        
        //Recoger datos del objeto persona y representarlos
        nombre = persona.getNombre();
        edad = persona.getEdad();
        
        System.out.println("Datos de persona: " + nombre + ", " + edad);
        
        //Cerramos Streams y Socket
        bais.close();
        in.close();
        baos.close();
        out.close();
        client.close();
    }
    
}
