package es.deusto.ingenieria.sd.rmi.client.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List; // Importa la interfaz List

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;// Importa la clase AlojamientoAtributes
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;



public class VentanaInicio extends JFrame {

    private JTextField textFieldEmail;
    private JTextField textFieldContrasena;
    private ServerFacade serverFacade;

    public VentanaInicio() {
        serverFacade = RMIServiceLocator.getInstance().getService();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300); 
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblLogin = new JLabel("Iniciar Sesión");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLogin.setBounds(150, 20, 150, 30);
        getContentPane().add(lblLogin);

        JLabel emailLabel = new JLabel("Correo electrónico:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        emailLabel.setBounds(20, 70, 180, 30);
        getContentPane().add(emailLabel);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(200, 70, 220, 26);
        getContentPane().add(textFieldEmail);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contrasenaLabel.setBounds(20, 120, 110, 30);
        getContentPane().add(contrasenaLabel);

        textFieldContrasena = new JTextField();
        textFieldContrasena.setBounds(200, 120, 220, 26);
        getContentPane().add(textFieldContrasena);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setForeground(Color.BLUE);
        btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnIniciarSesion.setBounds(150, 180, 150, 30);
        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText();
                String contrasena = textFieldContrasena.getText();
                
                if (serverFacade != null) {
                    try {
                        boolean loginExitoso = serverFacade.iniciarSesion(email, contrasena);
                        if (loginExitoso) {
                            //para saber quien esta logeado
                            UsuarioDTO estaLogeado = new UsuarioDTO(email);
                            JOptionPane.showMessageDialog(VentanaInicio.this, "¡Login Exitoso!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            List<AlojamientoDTO> alojamientos = serverFacade.obtenerAlojamientos();
                            VentanaAlojamientos va = new VentanaAlojamientos(estaLogeado);
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
        btnRegistrarse.setBounds(150, 220, 150, 30);
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
