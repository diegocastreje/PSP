/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author diego
 */

/*
Proyecto MAVEN

-src
	#server
		-Main
		-Server
		-Tarea
	#client
		-Main
		-Client

POJO TAREA: String descripcion, estado
SOCKETS TCP CON TIPOS PRIMITIVOS
Puerto: 6000
Host: "localhost"

	Server:					Client:
	1. Iniciar servidor (main) ---------------------> 1.Cliente se conecta
	2. Acepta conexion
	3. Pregunta el nombre del cliente--------> "¿Cual es tu nombre?"
	4. Recibir nombre <----------------------- 2. Responder con el nombre
	5. Pregunta al cliente el nº de tareas---> "¿Cuantas tareas quieres realizar?"
	6. Recibe nº de tareas <------------------- 3. Responde nº de tareas
	Inica un bucle <---------------------------> Inicia un bucle
	7. Envia nº de la tarea -------------------> 4. Recibe nº de la tarea "Tarea numero n"
	8. Solicita descripcion de la tarea -------> "Escribe la tarea a realizar"
	9. Recibe descripcion <--------------------- 5. Enviar descripcion de la tarea
	tarea.setDescripcion("descripcionRecibida")
	10. Solicitar estado de la tarea ----------> "Escribe el estado de la tarea"
	11.Recibir estado de la tarea <------------- 6. Enviar estado de la tarea
	tarea.setEstado("estadoRecibido")
	Fin de bucle <------------------------------> Fin de bucle
	12. Informar de que enviara las tareas -----> "Las tareas enviadas por el servidor son a, b, c..."
	13. Enviar tareas --------------------------> 7. Recibe las tareas
*/
public class Main {
    
    public static void main(String[] args) throws IOException {
        int puerto = 6000;
        Servidor servidor = new Servidor(puerto);
        servidor.init();
    }
    
}
