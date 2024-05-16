package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

import java.awt.*;
import java.awt.event.*;

public class VentanaPerfil extends JFrame {
    private JLabel campoNombre;
    private JLabel campoApellido;
    private JLabel campoDNI;
    private JLabel campoCorreo;
    private JLabel campoTelefono;
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
        campoNombre = new JLabel(usuario.getNombre());
        panel.add(etiquetaNombre);
        panel.add(campoNombre);

        // Campo de texto para el apellido
        JLabel etiquetaApellido = new JLabel("Apellido:");
        campoApellido = new JLabel(usuario.getApellido());
        panel.add(etiquetaApellido);
        panel.add(campoApellido);

        // Campo de texto para el DNI
        JLabel etiquetaDNI = new JLabel("DNI:");
        campoDNI = new JLabel(usuario.getDNI());
        panel.add(etiquetaDNI);
        panel.add(campoDNI);

        // Campo de texto para el correo
        JLabel etiquetaCorreo = new JLabel("Correo:");
        campoCorreo = new JLabel(usuario.getCorreo());
        panel.add(etiquetaCorreo);
        panel.add(campoCorreo);

        // Campo de texto para el teléfono
        JLabel etiquetaTelefono = new JLabel("Teléfono:");
        campoTelefono = new JLabel(usuario.getTelefono());
        panel.add(etiquetaTelefono);
        panel.add(campoTelefono);

        // Botón guardar (no necesario para mostrar datos del usuario)
        botonGuardar = new JButton("Atras");
        botonGuardar.setVisible(false); // Ocultar el botón
        panel.add(botonGuardar);

        // Agregar panel a la ventana
        add(panel);
    }
}