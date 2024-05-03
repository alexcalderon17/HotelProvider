package es.deusto.ingenieria.sd.rmi.client.gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Ventana2 extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEmail;
    private JTextField textFieldTelefono;
    private JTextField textFieldDni;
    private JTextField textFieldContrasena;
   // private Ventana1 padre;
    private ServerFacade serverFacade;

    public void setRegistroUsuarioListener(ServerFacade listener) {
        this.serverFacade = listener;
    }

    private void addLabelAndTextField(String labelText, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 11)); 
        GridBagConstraints labelGbc = new GridBagConstraints();
        labelGbc.gridx = 0;
        labelGbc.gridy = gbc.gridy;
        labelGbc.anchor = GridBagConstraints.WEST;
        labelGbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        contentPane.add(label, labelGbc);
    
        JTextField textField = new JTextField(10);
        textField.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY)); 
        GridBagConstraints textFieldGbc = new GridBagConstraints();
        textFieldGbc.gridx = 1;
        textFieldGbc.gridy = gbc.gridy;
        textFieldGbc.fill = GridBagConstraints.HORIZONTAL;
        textFieldGbc.weightx = 1.0;
        textFieldGbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        contentPane.add(textField, textFieldGbc);
    }

    public Ventana2() {
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 330, 400); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); 
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.WHITE); 
        setContentPane(contentPane);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JLabel lblRegistrate = new JLabel("Registrate!");
        lblRegistrate.setFont(new Font("Tahoma", Font.BOLD, 18)); 
        lblRegistrate.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(10, 0, 20, 0); 
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(lblRegistrate, gbc);
    
        gbc.insets = new Insets(2, 0, 2, 0);
    
        // Agregar cada campo de texto utilizando el método addLabelAndTextField
        addLabelAndTextField("NOMBRE", gbc);
        addLabelAndTextField("APELLIDO", gbc);
        addLabelAndTextField("CORREO ELECTRONICO", gbc); // Corregido el texto
        addLabelAndTextField("NUM TELEFONO", gbc); // Corregido el texto
        addLabelAndTextField("DNI", gbc);
        addLabelAndTextField("CONTRASENA", gbc);

       
        JButton btnRegister = new JButton("Registrarse");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //padre.setVisible(true);
               setVisible(false);
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                String email = textFieldEmail.getText();
                String telefono = textFieldTelefono.getText();
                String dni = textFieldDni.getText();
                String contrasena = textFieldContrasena.getText();
                int codPostal = 0; // prueba

               
               if (serverFacade != null) {
                try {
                    serverFacade.registrarse(nombre, apellido, dni, email, telefono, contrasena, codPostal); 
                } catch (RemoteException e) {
                    // Manejar la excepción aquí, por ejemplo, imprimir un mensaje de error o registrarla.
                    e.printStackTrace();
                }
}


                
                setVisible(false);
            }
        });
        btnRegister.setForeground(new Color(0, 0, 255)); 
        GridBagConstraints gbc_btnRegister = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER; 
        contentPane.add(btnRegister, gbc);

        
        JButton btnAtrs = new JButton("ATRAS");
        btnAtrs.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAtrs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // padre.setVisible(true);
                Ventana2.this.setVisible(false);
                Ventana2.this.dispose();
            }
        });
        gbc.insets = new Insets(-20, -200, 0, 0);
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER; 
        contentPane.add(btnAtrs, gbc);
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
