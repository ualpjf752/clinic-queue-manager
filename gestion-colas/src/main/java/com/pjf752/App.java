package com.pjf752;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    
    // Usamos la nueva clase QueueManager en lugar de GestorColas
    private QueueManager queueManager;
    
    // Componentes visuales 
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtUrgency;
    private JTextArea queueArea;

    public App() {
        queueManager = new QueueManager();
        queueManager.loadData(); // Cargar datos previos si existen
        
        // Configuración básica de la ventana
        setTitle("Gestor de Colas - Clínica");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10)); 
        
        initComponents();
    }

    private void initComponents() {
        // --- PANEL SUPERIOR: Formulario de entrada ---
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("DNI del paciente:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Nombre del paciente:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Nivel de Urgencia (1-5):"));
        txtUrgency = new JTextField();
        inputPanel.add(txtUrgency);

        JButton btnRegister = new JButton("Registrar Paciente");
        JButton btnAttend = new JButton("Atender Siguiente");

        inputPanel.add(btnRegister);
        inputPanel.add(btnAttend);

        add(inputPanel, BorderLayout.NORTH);

        // ÁREA CENTRAL: Mostrar la cola 
        queueArea = new JTextArea();
        queueArea.setEditable(false);
        queueArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(queueArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pacientes en Espera"));
        add(scrollPane, BorderLayout.CENTER);

        // PROGRAMACIÓN DIRIGIDA POR EVENTOS
        
        // Qué pasa al hacer clic en "Registrar"
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = txtId.getText();
                    String name = txtName.getText();
                    int urgency = Integer.parseInt(txtUrgency.getText());

                    Patient p = new Patient(id, name, urgency);
                    queueManager.registerPatient(p);

                    // Limpiamos las cajas de texto tras registrar
                    txtId.setText("");
                    txtName.setText("");
                    txtUrgency.setText("");

                    updateScreen();
                    queueManager.saveData(); // Guardar datos
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La urgencia debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Qué pasa al hacer clic en "Atender"
        btnAttend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient p = queueManager.attendNext();
                if (p != null) {
                    // Usamos el nuevo getter getName() de Patient
                    JOptionPane.showMessageDialog(null, "Atendiendo a: " + p.getName(), "Llamada a consulta", JOptionPane.INFORMATION_MESSAGE);
                    updateScreen();
                    queueManager.saveData(); // Guardar datos tras sacar a alguien
                } else {
                    JOptionPane.showMessageDialog(null, "No hay pacientes en la cola.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        updateScreen(); // Mostrar estado inicial
    }

    // Actualiza el cuadro de texto grande
    private void updateScreen() {
        queueArea.setText(queueManager.getQueueState());
    }

    // MÉTODO PRINCIPAL
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App(); // Instanciamos la nueva clase App
            app.setVisible(true);
        });
    }
}