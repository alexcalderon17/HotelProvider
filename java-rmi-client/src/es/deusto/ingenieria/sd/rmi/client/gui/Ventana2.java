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

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
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
        serverFacade = RMIServiceLocator.getInstance().getService();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400); // Aumento tamaño para mejor distribución
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblRegistrate = new JLabel("Regístrate!");
        lblRegistrate.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblRegistrate.setBounds(200, 10, 150, 30);
        getContentPane().add(lblRegistrate);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nombreLabel.setBounds(10, 50, 80, 30);
        getContentPane().add(nombreLabel);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(120, 50, 300, 26);
        getContentPane().add(textFieldNombre);

        JLabel apellidoLabel = new JLabel("Apellidos:");
        apellidoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        apellidoLabel.setBounds(10, 90, 90, 30);
        getContentPane().add(apellidoLabel);

        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(120, 90, 300, 26);
        getContentPane().add(textFieldApellido);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        emailLabel.setBounds(10, 130, 80, 30);
        getContentPane().add(emailLabel);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(120, 130, 300, 26);
        getContentPane().add(textFieldEmail);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        telefonoLabel.setBounds(10, 170, 90, 30);
        getContentPane().add(telefonoLabel);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setBounds(120, 170, 300, 26);
        getContentPane().add(textFieldTelefono);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        dniLabel.setBounds(10, 210, 80, 30);
        getContentPane().add(dniLabel);

        textFieldDni = new JTextField();
        textFieldDni.setBounds(120, 210, 300, 26);
        getContentPane().add(textFieldDni);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contrasenaLabel.setBounds(10, 250, 110, 30);
        getContentPane().add(contrasenaLabel);

        textFieldContrasena = new JTextField();
        textFieldContrasena.setBounds(130, 250, 290, 26);
        getContentPane().add(textFieldContrasena);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setForeground(Color.BLUE);
        btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistrarse.setBounds(370, 300, 150, 30);
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                String email = textFieldEmail.getText();
                String telefono = textFieldTelefono.getText();
                String dni = textFieldDni.getText();
                String contrasena = textFieldContrasena.getText();
                
                UsuarioDTO usuario = new UsuarioDTO(nombre, apellido, email, telefono, dni, contrasena);
                if (serverFacade != null) {
                    try {
                        serverFacade.registrarse(usuario);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getContentPane().add(btnRegistrarse);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAtras.setBounds(200, 300, 150, 30);
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        getContentPane().add(btnAtras);
    }

    public static void main(String[] args) {
        RMIServiceLocator rmiServiceLocator = new RMIServiceLocator(args[0], args[1], args[2]);
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
