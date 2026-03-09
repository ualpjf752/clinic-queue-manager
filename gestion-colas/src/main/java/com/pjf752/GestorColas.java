package com.pjf752;

import java.util.LinkedList;
import java.util.Queue;

public class GestorColas {
    // Utilizamos la interfaz genérica Queue con nuestra clase Paciente
    private Queue<Paciente> colaPacientes;

    public GestorColas() {
        // Inicializamos la cola usando una LinkedList
        this.colaPacientes = new LinkedList<>();
    }

    // Método para añadir un paciente a la cola (Enqueue)
    public void registrarPaciente(Paciente paciente) {
        colaPacientes.offer(paciente); // 'offer' añade al final de la cola
        System.out.println("Registrado: "+paciente.getNombre());
    }

    // Método para atender y sacar al paciente de la cola (Dequeue)
    public Paciente atenderSiguiente() {
        if(colaPacientes.isEmpty()) {
            System.out.println("No hay pacientes en la cola.");
            return null;
        }
        // 'poll' recupera y elimina el primer elemento de la cola
        Paciente atendido = colaPacientes.poll();
        System.out.println("Ateniendo a: "+atendido.getNombre());
        return atendido;
    }

    // Método para ver quién es el siguiente sin sacarlo de la cola 'peek'
    public Paciente verSiguiente() {
        return colaPacientes.peek();
    }

    // Método para saber cúantoss pacientes hay esperando 'size'
    public int getTotalPacientesEnEspera() {
        return colaPacientes.size();
    }

    // Método extra para la Interfaz Gráfica: devuelve todos los pacientes en formato texto
    public String obtenerEstadoCola() {
        if (colaPacientes.isEmpty()) {
            return "No hay pacientes en espera.";
        }
        
        StringBuilder sb = new StringBuilder();
        int posicion = 1;
        // Podemos iterar sobre una Queue en Java sin sacarlos de la cola
        for (Paciente p : colaPacientes) {
            sb.append(posicion).append(". [DNI: ").append(p.getDni()).append("] ")
              .append(p.getNombre()).append(" - Urgencia: ").append(p.getNivelUrgencia()).append("\n");
            posicion++;
        }
        return sb.toString();
    }
}
