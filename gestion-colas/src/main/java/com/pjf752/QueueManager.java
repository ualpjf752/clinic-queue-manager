package com.pjf752;

import java.util.LinkedList;
import java.util.Queue;
import java.io.*; 

public class QueueManager {
    
    // Utilizamos la interfaz genérica Queue con nuestra clase Patient
    private Queue<Patient> patientQueue;

    public QueueManager() {
        // Inicializamos la cola usando una LinkedList
        this.patientQueue = new LinkedList<>();
    }

    // Método para añadir un paciente a la cola (Enqueue)
    public void registerPatient(Patient patient) {
        patientQueue.offer(patient); // 'offer' añade al final de la cola
    }

    // Método para atender y sacar al paciente de la cola (Dequeue)
    public Patient attendNext() {
        if(patientQueue.isEmpty()) {
            return null;
        }
        // 'poll' recupera y elimina el primer elemento de la cola
        return patientQueue.poll();
    }

    // Método para ver quién es el siguiente sin sacarlo de la cola 'peek'
    public Patient viewNext() {
        return patientQueue.peek();
    }

    // Método para saber cuántos pacientes hay esperando 'size'
    public int getTotalWaitingPatients() {
        return patientQueue.size();
    }

    // Método extra para la Interfaz Gráfica: devuelve todos los pacientes en formato texto (en español)
    public String getQueueState() {
        if (patientQueue.isEmpty()) {
            return "No hay pacientes en espera.";
        }
        
        StringBuilder sb = new StringBuilder();
        int position = 1;
        // Podemos iterar sobre una Queue en Java sin sacarlos de la cola
        for (Patient p : patientQueue) {
            sb.append(position).append(". [DNI: ").append(p.getId()).append("] ")
              .append(p.getName()).append(" - Urgencia: ").append(p.getUrgencyLevel()).append("\n");
            position++;
        }
        return sb.toString();
    }

    // Métodos para guardar y cargar datos usando serialización
    public void saveData() {
        // Usamos un bloque try-catch para manejar posibles errores al escribir en el disco
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("clinica_datos.dat"))) {
            oos.writeObject(patientQueue); // Guardamos la cola entera de golpe
        } catch (IOException e) {
            // Silenciado temporalmente
        }
    }

    @SuppressWarnings("unchecked") // Para evitar advertencias al convertir de Object a Queue<Patient>
    public void loadData() {
        File file = new File("clinica_datos.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                // Leemos el archivo y lo convertimos de vuelta a una Queue de Patients
                patientQueue = (Queue<Patient>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                 // Silenciado temporalmente
            }
        }
    }
}