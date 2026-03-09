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
}
