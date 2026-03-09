package com.pjf752;

public class Paciente {
    // Atributos de Paciente
    private String dni;
    private String nombre;
    private int nivelUrgencia; // 1 (Muy urgente) a 5 (Poco urgente)

    // Constructor
    public Paciente(String dni, String nombre, int nivelUrgencia) {
        this.dni = dni;
        this.nombre = nombre;
        this.nivelUrgencia = nivelUrgencia;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivelUrgencia(int nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    @Override
    public String toString() {
        return "Paciente ["+dni+"] "+nombre+" (Urgencia: "+nivelUrgencia+")";
    }


}