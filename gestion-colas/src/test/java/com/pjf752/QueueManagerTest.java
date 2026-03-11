package com.pjf752;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueueManagerTest {

    private QueueManager queueManager;

    @BeforeEach
    public void setUp() {
        queueManager = new QueueManager();
    }

    @Test
    public void testRegisterPatientIncreasesTotal() {
        Patient p1 = new Patient("11111111A", "Ana", 3);
        queueManager.registerPatient(p1);
        assertEquals(1, queueManager.getTotalWaitingPatients(), "Debería haber 1 paciente en la cola");
    }

    @Test
    public void testFIFOOrderWhenAttending() {
        Patient p1 = new Patient("11111111A", "Ana", 3);
        Patient p2 = new Patient("22222222B", "Luis", 2);
        
        queueManager.registerPatient(p1);
        queueManager.registerPatient(p2);
        
        Patient attended = queueManager.attendNext();
        
        assertNotNull(attended, "El paciente atendido no debería ser null");
        assertEquals("Ana", attended.getName(), "El primer paciente atendido debería ser Ana");
        assertEquals(1, queueManager.getTotalWaitingPatients(), "Debería quedar 1 paciente en la cola");
    }

    @Test
    public void testAttendNextOnEmptyQueueReturnsNull() {
        Patient attended = queueManager.attendNext();
        assertNull(attended, "Si la cola está vacía, debe devolver null");
    }

    @Test
    public void testViewNextWithoutRemoving() {
        Patient p1 = new Patient("11111111A", "Ana", 3);
        queueManager.registerPatient(p1);
        
        Patient next = queueManager.viewNext();
        
        assertEquals("Ana", next.getName(), "viewNext debería retornar a Ana");
        assertEquals(1, queueManager.getTotalWaitingPatients(), "La cola no debería cambiar al solo visualizar");
    }

    @Test
    public void testMultiplePatients() {
        queueManager.registerPatient(new Patient("11111111A", "Ana", 3));
        queueManager.registerPatient(new Patient("22222222B", "Luis", 2));
        queueManager.registerPatient(new Patient("33333333C", "María", 1));
        
        assertEquals(3, queueManager.getTotalWaitingPatients(), "Debería haber 3 pacientes registrados");
    }

    @Test
    public void testAttendAllPatients() {
        queueManager.registerPatient(new Patient("11111111A", "Ana", 3));
        queueManager.registerPatient(new Patient("22222222B", "Luis", 2));
        
        queueManager.attendNext();
        queueManager.attendNext();
        
        assertEquals(0, queueManager.getTotalWaitingPatients(), "La cola debería quedar a 0 tras atender a todos");
    }
}