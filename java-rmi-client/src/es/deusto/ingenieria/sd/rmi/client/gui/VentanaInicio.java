package es.deusto.ingenieria.sd.rmi.client.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

public class VentanaInicio extends JFrame {

    private JTextField textFieldEmail;
    private JTextField textFieldContrasena;
    private ServerFacade serverFacade;

    public VentanaInicio() {
        serverFacade = RMIServiceLocator.getInstance().getService();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Ajuste del tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        getContentPane().setLayout(null); // Desactiva el layout manager

        JLabel lblLogin = new JLabel("INICIAR SESIÓN");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblLogin.setBounds(325, 80, 250, 40); // Posición y tamaño del label
        getContentPane().add(lblLogin);

        JLabel emailLabel = new JLabel("Correo electrónico:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        emailLabel.setBounds(200, 150, 180, 30); // Posición y tamaño del label
        getContentPane().add(emailLabel);

        textFieldEmail = new JTextField(20);
        textFieldEmail.setBounds(400, 150, 200, 30); // Posición y tamaño del campo de texto
        getContentPane().add(textFieldEmail);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contrasenaLabel.setBounds(200, 200, 110, 30); // Posición y tamaño del label
        getContentPane().add(contrasenaLabel);

        textFieldContrasena = new JTextField(20);
        textFieldContrasena.setBounds(400, 200, 200, 30); // Posición y tamaño del campo de texto
        getContentPane().add(textFieldContrasena);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setForeground(Color.BLUE);
        btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnIniciarSesion.setBounds(325, 280, 150, 40); // Posición y tamaño del botón
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText();
                String contrasena = textFieldContrasena.getText();
                
                if (serverFacade != null) {
                    try {
                        UsuarioDTO usuarioLogeado = serverFacade.iniciarSesion(email, contrasena);
                        if (usuarioLogeado != null) {
                            //para saber quien esta logeado
                            JOptionPane.showMessageDialog(VentanaInicio.this, "¡Login Exitoso!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            List<AlojamientoDTO> alojamientos = serverFacade.obtenerAlojamientos();
                            VentanaAlojamientos va = new VentanaAlojamientos(usuarioLogeado);
                            va.setVisible(true);
                            VentanaInicio.this.setVisible(false);
                            VentanaInicio.this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(VentanaInicio.this, "Correo electrónico o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(VentanaInicio.this, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        getContentPane().add(btnIniciarSesion);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistrarse.setBounds(325, 340, 150, 40); // Posición y tamaño del botón
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            VentanaRegistro frame = new VentanaRegistro();
                            frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        getContentPane().add(btnRegistrarse);
    }

    public static void main(String[] args) {
        RMIServiceLocator rmiServiceLocator = new RMIServiceLocator(args[0], args[1], args[2]);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaInicio frame = new VentanaInicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
