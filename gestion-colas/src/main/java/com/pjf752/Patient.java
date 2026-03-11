package com.pjf752;

import java.io.Serializable;

public class Patient implements Serializable { // Clase renombrada a Patient
    
    // Para la serialización, es buena práctica definir un serialVersionUID
    private static final long serialVersionUID = 1L;
    
    // Atributos 
    private String id; // Usamos 'id' como equivalente genérico y profesional de 'dni'
    private String name;
    private int urgencyLevel; // 1 (Muy urgente) a 5 (Poco urgente)

    // Constructor
    public Patient(String id, String name, int urgencyLevel) {
        this.id = id;
        this.name = name;
        this.urgencyLevel = urgencyLevel;
    }

    // Getters y Setters en inglés
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    @Override
    public String toString() {
        return "Paciente [" + id + "] " + name + " (Urgencia: " + urgencyLevel + ")";
    }
}