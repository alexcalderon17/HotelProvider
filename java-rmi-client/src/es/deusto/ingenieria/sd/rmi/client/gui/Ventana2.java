package es.deusto.ingenieria.sd.rmi.client.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class Ventana2 extends JFrame {

    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEmail;
    private JTextField textFieldTelefono;
    private JTextField textFieldDni;
    private JTextField textFieldContrasena;
    private ServerFacade serverFacade;

    public void setRegistroUsuarioListener(ServerFacade listener) {
        this.serverFacade = listener;
    }

    public Ventana2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 300); 
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE); 

        JLabel lblRegistrate = new JLabel("Regístrate!");
        lblRegistrate.setFont(new Font("Tahoma", Font.BOLD, 18)); 
        lblRegistrate.setBounds(170, 20, 140, 30);
        getContentPane().add(lblRegistrate);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        nombreLabel.setBounds(20, 70, 80, 30);
        getContentPane().add(nombreLabel);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(100, 70, 250, 26);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel apellidoLabel = new JLabel("Apellidos:");
        apellidoLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        apellidoLabel.setBounds(20, 110, 80, 30);
        getContentPane().add(apellidoLabel);

        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(100, 110, 250, 26);
        getContentPane().add(textFieldApellido);
        textFieldApellido.setColumns(10);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        emailLabel.setBounds(20, 150, 80, 30);
        getContentPane().add(emailLabel);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(100, 150, 250, 26);
        getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        telefonoLabel.setBounds(20, 190, 80, 30);
        getContentPane().add(telefonoLabel);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setBounds(100, 190, 250, 26);
        getContentPane().add(textFieldTelefono);
        textFieldTelefono.setColumns(10);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        dniLabel.setBounds(20, 230, 80, 30);
        getContentPane().add(dniLabel);

        textFieldDni = new JTextField();
        textFieldDni.setBounds(100, 230, 250, 26);
        getContentPane().add(textFieldDni);
        textFieldDni.setColumns(10);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        contrasenaLabel.setBounds(20, 270, 100, 30);
        getContentPane().add(contrasenaLabel);

        textFieldContrasena = new JTextField();
        textFieldContrasena.setBounds(130, 270, 250, 26);
        getContentPane().add(textFieldContrasena);
        textFieldContrasena.setColumns(10);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setForeground(Color.BLUE);
        btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        btnRegistrarse.setBounds(380, 270, 130, 30);
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                String email = textFieldEmail.getText();
                String telefono = textFieldTelefono.getText();
                String dni = textFieldDni.getText();
                String contrasena = textFieldContrasena.getText();
                int codPostal = 0; // ¿De dónde proviene este código postal?

                if (serverFacade != null) {
                    try {
                        serverFacade.registrarse(nombre, apellido, dni, email, telefono, contrasena, codPostal); 
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getContentPane().add(btnRegistrarse);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16)); 
        btnAtras.setBounds(270, 270, 100, 30);
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        getContentPane().add(btnAtras);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana2 frame = new Ventana2(); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
