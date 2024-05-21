package es.deusto.ingenieria.sd.rmi.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaRegistro extends JFrame {

    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEmail;
    private JTextField textFieldTelefono;
    private JTextField textFieldDni;
    private JPasswordField textFieldContrasena;
    private ServerFacade serverFacade;

    public VentanaRegistro() {
        serverFacade = RMIServiceLocator.getInstance().getService();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Ajuste del tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        getContentPane().setLayout(null); // Desactiva el layout manager
        getContentPane().setBackground(Color.WHITE);

        JLabel lblRegistrate = new JLabel("Regístrate!");
        lblRegistrate.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblRegistrate.setBounds(350, 30, 100, 30);
        getContentPane().add(lblRegistrate);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nombreLabel.setBounds(200, 80, 100, 30);
        getContentPane().add(nombreLabel);

        textFieldNombre = new JTextField(20);
        textFieldNombre.setBounds(350, 80, 250, 30);
        getContentPane().add(textFieldNombre);

        JLabel apellidoLabel = new JLabel("Apellidos:");
        apellidoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        apellidoLabel.setBounds(200, 130, 100, 30);
        getContentPane().add(apellidoLabel);

        textFieldApellido = new JTextField(20);
        textFieldApellido.setBounds(350, 130, 250, 30);
        getContentPane().add(textFieldApellido);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        emailLabel.setBounds(200, 180, 100, 30);
        getContentPane().add(emailLabel);

        textFieldEmail = new JTextField(20);
        textFieldEmail.setBounds(350, 180, 250, 30);
        getContentPane().add(textFieldEmail);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        telefonoLabel.setBounds(200, 230, 100, 30);
        getContentPane().add(telefonoLabel);

        textFieldTelefono = new JTextField(20);
        textFieldTelefono.setBounds(350, 230, 250, 30);
        getContentPane().add(textFieldTelefono);
        configurarFiltroTelefono();

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        dniLabel.setBounds(200, 280, 100, 30);
        getContentPane().add(dniLabel);

        textFieldDni = new JTextField(20);
        textFieldDni.setBounds(350, 280, 250, 30);
        getContentPane().add(textFieldDni);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contrasenaLabel.setBounds(200, 330, 120, 30);
        getContentPane().add(contrasenaLabel);

        textFieldContrasena = new JPasswordField(20);
        textFieldContrasena.setBounds(350, 330, 250, 30);
        getContentPane().add(textFieldContrasena);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setForeground(Color.BLUE);
        btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistrarse.setBounds(340, 400, 150, 40); // Ajuste de tamaño para que el texto quepa bien
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                String email = textFieldEmail.getText();
                String telefono = textFieldTelefono.getText();
                String dni = textFieldDni.getText();
                String contrasena = new String(textFieldContrasena.getPassword());

                if (validarDNI(dni)) {
                    String errorContrasena = validarContrasena(contrasena);
                    if (errorContrasena == null) {
                        UsuarioDTO usuario = new UsuarioDTO(nombre, apellido, dni, email, telefono, contrasena);
                        if (serverFacade != null) {
                            try {
                                serverFacade.registrarse(usuario);
                                // Abre la ventana de inicio de sesión después de registrarse
                                EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        try {
                                            JOptionPane.showMessageDialog(VentanaRegistro.this, "¡Registro Exitoso!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                            VentanaInicio frame = new VentanaInicio();
                                            frame.setVisible(true);
                                            // Cierra la ventana actual
                                            dispose();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        mostrarMensajeError(errorContrasena);
                    }
                } else {
                    mostrarMensajeError("El campo DNI debe tener 9 caracteres.");
                }
            }
        });
        getContentPane().add(btnRegistrarse);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAtras.setBounds(10, 510, 120, 40); // Abajo a la izquierda
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Abre la ventana de inicio después de presionar el botón "Atrás"
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            VentanaInicio frame = new VentanaInicio();
                            frame.setVisible(true);
                            // Cierra la ventana actual
                            dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        getContentPane().add(btnAtras);
    }

    private void configurarFiltroTelefono() {
        ((PlainDocument) textFieldTelefono.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int extra, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("[0-9]*")) {
                    super.insertString(fb, extra, string, attr);
                } else {
                    mostrarMensajeError("El campo teléfono solo puede incluir números.");
                }
            }

            @Override
            public void replace(FilterBypass fb, int extra, int longitud, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[0-9]*")) {
                    super.replace(fb, extra, longitud, text, attrs);
                } else {
                    mostrarMensajeError("El campo teléfono solo puede incluir números.");
                }
            }
        });
    }

    private boolean validarDNI(String dni) {
        return dni.length() == 9;
    }

    private String validarContrasena(String contrasena) {
        // Verifica la longitud de la contraseña
        if (contrasena.length() != 8) {
            return "La contraseña debe tener exactamente 8 caracteres.";
        }

        // Verifica la presencia de al menos una letra mayúscula
        if (!contrasena.matches(".*[A-Z].*")) {
            return "La contraseña debe contener al menos una letra mayúscula.";
        }

        // Verifica la presencia de al menos una letra minúscula
        if (!contrasena.matches(".*[a-z].*")) {
            return "La contraseña debe contener al menos una letra minúscula.";
        }

        // Verifica la presencia de al menos un número
        if (!contrasena.matches(".*\\d.*")) {
            return "La contraseña debe contener al menos un número.";
        }

        // Si la contraseña cumple con todas las condiciones, devuelve null (sin mensaje de error)
        return null;
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
