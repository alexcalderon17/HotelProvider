package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

import java.awt.*;
import java.awt.event.*;

public class VentanaPerfil extends JFrame {
    private JTextField nombre;
    private JTextField apellido;
    private JTextField DNI;
    private JTextField correo;
    private JTextField telefono;
    private JButton botonGuardar;
    private UsuarioDTO estaLogeado;

    public VentanaPerfil(UsuarioDTO estaLogeado) {
        // Configurar la ventana
        setTitle("Perfil de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Campo de texto para el nombre
        JLabel labelNombre = new JLabel();
        JLabel etiquetaNombre = new JLabel("Nombre:");
        nombre = new JTextField(15);
        labelNombre.add(etiquetaNombre);
        labelNombre.add(nombre);
        panel.add(labelNombre);

        // Campo de texto para la edad
        JPanel panelEdad = new JPanel();
        JLabel etiquetaEdad = new JLabel("Edad:");
        apellido = new JTextField(15);
        panelEdad.add(etiquetaEdad);
        panelEdad.add(apellido);
        panel.add(panelEdad);

        // Botón guardar
        JPanel panelBoton = new JPanel();
        botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarPerfil();
            }
        });
        panelBoton.add(botonGuardar);
        panel.add(panelBoton);

        // Agregar panel a la ventana
        add(panel);
    }

    private void guardarPerfil() {
        // String nombre = nombre.getText();
        String edad = apellido.getText();

        // Aquí podrías guardar el perfil en alguna estructura de datos o en una base de
        // datos
        // Por simplicidad, solo imprimiremos el perfil
        // System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }

    /*
     * public static void main(String[] args) {
     * SwingUtilities.invokeLater(new Runnable() {
     * public void run() {
     * new VentanaPerfil(estaLogeado).setVisible(true);
     * }
     * });
     * }
     */
}