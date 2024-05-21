package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

public class VentanaPerfil extends JFrame {
    private JLabel campoNombre;
    private JLabel campoApellido;
    private JLabel campoDNI;
    private JLabel campoCorreo;
    private JLabel campoTelefono;
    private JButton botonAtras;
    private UsuarioDTO usuario;

    public VentanaPerfil(UsuarioDTO usuario) {
        this.usuario = usuario;

        // Configurar la ventana
        setTitle("Perfil de Usuario");
        setSize(800, 600); // Ajuste del tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        getContentPane().setLayout(null); // Desactiva el layout manager

        // Título
        JLabel lblTitulo = new JLabel("PERFIL USUARIO");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32)); // Tamaño de fuente más grande
        lblTitulo.setBounds(250, 30, 300, 40); // Ajuste de posición y tamaño
        getContentPane().add(lblTitulo);

        // Panel de información del usuario
        JPanel panelInfo = new JPanel(null);
        panelInfo.setBounds(100, 100, 600, 400); // Ajuste del tamaño del panel

        // Configurar el borde con un título más grande
        TitledBorder border = BorderFactory.createTitledBorder("Información del Usuario");
        border.setTitleFont(new Font("Tahoma", Font.BOLD, 18)); // Tamaño de fuente más grande para el borde
        panelInfo.setBorder(border);
        getContentPane().add(panelInfo);

        // Etiquetas y campos de texto
        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaNombre.setBounds(150, 50, 100, 30); // Centrado horizontalmente
        panelInfo.add(etiquetaNombre);

        campoNombre = new JLabel(usuario.getNombre());
        campoNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoNombre.setBounds(300, 50, 300, 30); // Ajuste del tamaño del campo
        panelInfo.add(campoNombre);

        JLabel etiquetaApellido = new JLabel("Apellido:");
        etiquetaApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaApellido.setBounds(150, 100, 100, 30); // Centrado horizontalmente
        panelInfo.add(etiquetaApellido);

        campoApellido = new JLabel(usuario.getApellido());
        campoApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoApellido.setBounds(300, 100, 300, 30); // Ajuste del tamaño del campo
        panelInfo.add(campoApellido);

        JLabel etiquetaDNI = new JLabel("DNI:");
        etiquetaDNI.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaDNI.setBounds(150, 150, 100, 30); // Centrado horizontalmente
        panelInfo.add(etiquetaDNI);

        campoDNI = new JLabel(usuario.getDNI());
        campoDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoDNI.setBounds(300, 150, 300, 30); // Ajuste del tamaño del campo
        panelInfo.add(campoDNI);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        etiquetaCorreo.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaCorreo.setBounds(150, 200, 100, 30); // Centrado horizontalmente
        panelInfo.add(etiquetaCorreo);

        campoCorreo = new JLabel(usuario.getCorreo());
        campoCorreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoCorreo.setBounds(300, 200, 300, 30); // Ajuste del tamaño del campo
        panelInfo.add(campoCorreo);

        JLabel etiquetaTelefono = new JLabel("Teléfono:");
        etiquetaTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
        etiquetaTelefono.setBounds(150, 250, 100, 30); // Centrado horizontalmente
        panelInfo.add(etiquetaTelefono);

        campoTelefono = new JLabel(usuario.getTelefono());
        campoTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
        campoTelefono.setBounds(300, 250, 300, 30); // Ajuste del tamaño del campo
        panelInfo.add(campoTelefono);

        // Botón Atrás
        botonAtras = new JButton("Atrás");
        botonAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonAtras.setBounds(350, 520, 100, 40); // Ajuste de posición
        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VentanaAlojamientos va = new VentanaAlojamientos(usuario);
                    va.setVisible(true);
                    dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
        getContentPane().add(botonAtras);
    }
}
