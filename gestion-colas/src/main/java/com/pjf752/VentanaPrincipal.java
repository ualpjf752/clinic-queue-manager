package com.pjf752; // ¡Recuerda poner tu paquete real!

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Al heredar de JFrame, esta clase ES una ventana
public class VentanaPrincipal extends JFrame {
    
    private GestorColas gestor;
    
    // Componentes visuales
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtUrgencia;
    private JTextArea areaCola;

    public VentanaPrincipal() {
        gestor = new GestorColas();
        gestor.cargarDatos(); // Cargar datos previos si existen
        
        // Configuración básica de la ventana
        setTitle("Gestor de Colas - Clínica");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Que el programa acabe al cerrar la ventana
        setLocationRelativeTo(null); // Centrar en la pantalla
        setLayout(new BorderLayout(10, 10)); // Layout principal
        
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // --- PANEL SUPERIOR: Formulario de entrada ---
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelEntrada.add(new JLabel("DNI del paciente:"));
        txtDni = new JTextField();
        panelEntrada.add(txtDni);

        panelEntrada.add(new JLabel("Nombre del paciente:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);

        panelEntrada.add(new JLabel("Nivel de Urgencia (1-5):"));
        txtUrgencia = new JTextField();
        panelEntrada.add(txtUrgencia);

        JButton btnRegistrar = new JButton("Registrar Paciente");
        JButton btnAtender = new JButton("Atender Siguiente");

        panelEntrada.add(btnRegistrar);
        panelEntrada.add(btnAtender);

        add(panelEntrada, BorderLayout.NORTH);

        // --- ÁREA CENTRAL: Mostrar la cola ---
        areaCola = new JTextArea();
        areaCola.setEditable(false);
        areaCola.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(areaCola);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pacientes en Espera"));
        add(scrollPane, BorderLayout.CENTER);

        // --- PROGRAMACIÓN DIRIGIDA POR EVENTOS ---
        
        // Qué pasa al hacer clic en "Registrar"
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dni = txtDni.getText();
                    String nombre = txtNombre.getText();
                    int urgencia = Integer.parseInt(txtUrgencia.getText());

                    Paciente p = new Paciente(dni, nombre, urgencia);
                    gestor.registrarPaciente(p);

                    // Limpiamos las cajas de texto tras registrar
                    txtDni.setText("");
                    txtNombre.setText("");
                    txtUrgencia.setText("");

                    actualizarPantalla();
                    gestor.guardarDatos(); // Guardar datos cada vez que se registra un nuevo paciente
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La urgencia debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Qué pasa al hacer clic en "Atender"
        btnAtender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paciente p = gestor.atenderSiguiente();
                if (p != null) {
                    JOptionPane.showMessageDialog(null, "Atendiendo a: " + p.getNombre(), "Llamada a consulta", JOptionPane.INFORMATION_MESSAGE);
                    actualizarPantalla();
                    gestor.guardarDatos(); // Guardar datos cada vez que se atiende a un paciente
                } else {
                    JOptionPane.showMessageDialog(null, "No hay pacientes en la cola.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        actualizarPantalla(); // Mostrar estado inicial
    }

    // Actualiza el cuadro de texto grande con los datos del GestorColas
    private void actualizarPantalla() {
        areaCola.setText(gestor.obtenerEstadoCola());
    }

    // Método principal para arrancar la app gráfica
    public static void main(String[] args) {
        // Ejecutar en el hilo correcto de Swing (buenas prácticas)
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}