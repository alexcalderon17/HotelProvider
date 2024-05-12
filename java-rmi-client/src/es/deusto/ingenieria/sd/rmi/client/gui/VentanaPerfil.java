package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

import java.awt.*;
import java.awt.event.*;

public class VentanaPerfil extends JFrame {
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoDNI;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JButton botonGuardar;
    private UsuarioDTO usuario;

    public VentanaPerfil(UsuarioDTO usuario) {
        this.usuario = usuario;

        // Configurar la ventana
        setTitle("Perfil de Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Campo de texto para el nombre
        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(usuario.getNombre());
        campoNombre.setEditable(false);
        panel.add(etiquetaNombre);
        panel.add(campoNombre);

        // Campo de texto para el apellido
        JLabel etiquetaApellido = new JLabel("Apellido:");
        campoApellido = new JTextField(usuario.getApellido());
        campoApellido.setEditable(false);
        panel.add(etiquetaApellido);
        panel.add(campoApellido);

        // Campo de texto para el DNI
        JLabel etiquetaDNI = new JLabel("DNI:");
        campoDNI = new JTextField(usuario.getDNI());
        campoDNI.setEditable(false);
        panel.add(etiquetaDNI);
        panel.add(campoDNI);

        // Campo de texto para el correo
        JLabel etiquetaCorreo = new JLabel("Correo:");
        campoCorreo = new JTextField(usuario.getCorreo());
        campoCorreo.setEditable(false);
        panel.add(etiquetaCorreo);
        panel.add(campoCorreo);

        // Campo de texto para el teléfono
        JLabel etiquetaTelefono = new JLabel("Teléfono:");
        campoTelefono = new JTextField(usuario.getTelefono());
        campoTelefono.setEditable(false);
        panel.add(etiquetaTelefono);
        panel.add(campoTelefono);

        // Botón guardar (no necesario para mostrar datos del usuario)
        botonGuardar = new JButton("Guardar");
        botonGuardar.setVisible(false); // Ocultar el botón
        panel.add(botonGuardar);

        // Agregar panel a la ventana
        add(panel);
    }
}