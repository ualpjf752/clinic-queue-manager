package com.pjf752;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorColasTest {

    private GestorColas gestor;

    @BeforeEach
    public void setUp() {
        gestor = new GestorColas();
    }

    @Test
    public void testRegistrarPacienteAumentaElTotal() {
        Paciente p1 = new Paciente("11111111A", "Ana", 3);
        gestor.registrarPaciente(p1);
        assertEquals(1, gestor.getTotalPacientesEnEspera(), "Debería haber 1 paciente en la cola");
    }

    @Test
    public void testOrdenFIFOAlaHoraDeAtender() {
        Paciente p1 = new Paciente("11111111A", "Ana", 3);
        Paciente p2 = new Paciente("22222222B", "Luis", 2);
        gestor.registrarPaciente(p1);
        gestor.registrarPaciente(p2);
        Paciente atendido = gestor.atenderSiguiente();
        assertNotNull(atendido, "El paciente atendido no debería ser null");
        assertEquals("Ana", atendido.getNombre(), "El primer paciente atendido debería ser Ana");
        assertEquals(1, gestor.getTotalPacientesEnEspera(), "Debería quedar 1 paciente en la cola");
    }

    @Test
    public void testAtenderSiguienteEnColaVaciaDevuelveNull() {
        Paciente atendido = gestor.atenderSiguiente();
        assertNull(atendido, "Si la cola está vacía, debe devolver null");
    }

    @Test
    public void testVerSiguienteSinEliminar() {
        Paciente p1 = new Paciente("11111111A", "Ana", 3);
        gestor.registrarPaciente(p1);
        Paciente siguiente = gestor.verSiguiente();
        assertEquals("Ana", siguiente.getNombre(), "verSiguiente debería retornar a Ana");
        assertEquals(1, gestor.getTotalPacientesEnEspera(), "La cola no debería cambiar");
    }

    @Test
    public void testMultiplesPacientes() {
        gestor.registrarPaciente(new Paciente("11111111A", "Ana", 3));
        gestor.registrarPaciente(new Paciente("22222222B", "Luis", 2));
        gestor.registrarPaciente(new Paciente("33333333C", "María", 1));
        assertEquals(3, gestor.getTotalPacientesEnEspera(), "Debería haber 3 pacientes");
    }

    @Test
    public void testAtenderTodosLosPacientes() {
        gestor.registrarPaciente(new Paciente("11111111A", "Ana", 3));
        gestor.registrarPaciente(new Paciente("22222222B", "Luis", 2));
        gestor.atenderSiguiente();
        gestor.atenderSiguiente();
        assertEquals(0, gestor.getTotalPacientesEnEspera(), "No debería haber pacientes");
    }
}