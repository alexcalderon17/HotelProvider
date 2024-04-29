package es.deusto.ingenieria.sd.rmi.client.gui;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


public class Ventana1 extends JFrame {

    private ServerFacade serverFacade; 
    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JTextField textFieldPassword;
    Ventana2 ventana2 = null;
    Ventana3 ventana3 = null;

    public Ventana1() {
        try{
            serverFacade = RMIServiceLocator.getInstance().getService();
            List<AlojamientoAtributes> alojamientos = serverFacade.obtenerAlojamientos();
			System.out.println(alojamientos);
        }catch(Exception e){
            System.out.println("Algo ha salido mal");


        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE); // Set the background color to white
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        contentPane.setLayout(gbl_contentPane);

        JLabel lblWelcome = new JLabel("Bienvenido!");
        lblWelcome.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
        gbc_lblWelcome.gridwidth = 2;
        gbc_lblWelcome.insets = new Insets(20, 0, 20, 0); // Top padding to separate from the top
        gbc_lblWelcome.gridx = 0;
        gbc_lblWelcome.gridy = 0;
        contentPane.add(lblWelcome, gbc_lblWelcome);

        JLabel lblEmail = new JLabel("Correo electronico");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.LINE_END;
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 0;
        gbc_lblEmail.gridy = 1;
        contentPane.add(lblEmail, gbc_lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBorder(new LineBorder(Color.GRAY, 1)); // Set the text field border
        GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
        gbc_textFieldEmail.insets = new Insets(0, 0, 5, 20); // Right padding to separate from the right edge
        gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldEmail.gridx = 1;
        gbc_textFieldEmail.gridy = 1;
        contentPane.add(textFieldEmail, gbc_textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblPassword = new JLabel("Contrasena");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.LINE_END;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 2;
        contentPane.add(lblPassword, gbc_lblPassword);

        textFieldPassword = new JTextField();
        textFieldPassword.setBorder(new LineBorder(Color.GRAY, 1)); // Set the text field border
        GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
        gbc_textFieldPassword.insets = new Insets(0, 0, 20, 20); // Bottom and right padding
        gbc_textFieldPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldPassword.gridx = 1;
        gbc_textFieldPassword.gridy = 2;
        contentPane.add(textFieldPassword, gbc_textFieldPassword);
        textFieldPassword.setColumns(10);

        JButton btnLogin = new JButton("INICIAR SESION");
        btnLogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        		ventana3 = new Ventana3(null);
                ventana3.setVisible(true);
               setVisible(false);
        	}
        });
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
        gbc_btnLogin.gridwidth = 2;
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 3;
        contentPane.add(btnLogin, gbc_btnLogin);

        JButton btnRegister = new JButton("Registrarse");
        btnRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ventana2 = new Ventana2(null);
                ventana2.setVisible(true);
               setVisible(false);
        	}
        });
        btnRegister.setForeground(new Color(0, 0, 255)); // Set the button text to blue
        GridBagConstraints gbc_btnRegister = new GridBagConstraints();
        gbc_btnRegister.gridwidth = 2;
        gbc_btnRegister.gridx = 0;
        gbc_btnRegister.gridy = 4;
        contentPane.add(btnRegister, gbc_btnRegister);
       
        // Crear una instancia de Ventana2 y pasar this como argumento al constructor
       // Ventana2 ventana2 = new Ventana2(this);
        //ventana2.setVisible(true);
       // setVisible(false); // Ocultar la Ventana1 actual
    }

    // Metodo main para iniciar la aplicacion
    public static void main(String[] args) {
        RMIServiceLocator rmiServiceLocator = new RMIServiceLocator(args[0], args[1], args[2]);
        // Utiliza EventQueue.invokeLater para asegurarte de que la creacion de la interfaz de usuario
        // se realiza en el hilo de despacho de eventos de Swing para evitar problemas de concurrencia
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana1 frame = new Ventana1(); // Crea una instancia de tu ventana
                    frame.setVisible(true); // Haz visible la ventana
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
