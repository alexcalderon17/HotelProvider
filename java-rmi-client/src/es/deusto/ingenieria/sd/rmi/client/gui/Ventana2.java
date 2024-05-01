package es.deusto.ingenieria.sd.rmi.client.gui;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
    private Ventana1 padre;
    private ServerFacade serverFacade;

    public void setRegistroUsuarioListener(ServerFacade listener) {
        this.serverFacade = listener;
    }

    public Ventana2(Ventana1 padre) {
        this.padre = padre;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 330, 400); // Adjusted to better fit the registration fields
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Adjusted padding
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.WHITE); // Set the background color to white
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label "Registrate!" at the top
        JLabel lblRegistrate = new JLabel("Registrate!");
        lblRegistrate.setFont(new Font("Tahoma", Font.BOLD, 18)); // Set the font and size
        lblRegistrate.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(10, 0, 20, 0); // Spacing for the title
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(lblRegistrate, gbc);

        // Reset insets to default for other components
        gbc.insets = new Insets(2, 0, 2, 0);

        // Nombre label and text field
        addLabelAndTextField("NOMBRE", gbc);
        addLabelAndTextField("APELLIDO", gbc);
        addLabelAndTextField("CORREO ELECTRoNICO", gbc);
        addLabelAndTextField("Num TELEFONO", gbc);
        addLabelAndTextField("DNI", gbc);
        addLabelAndTextField("CONTRASENA", gbc);

        // Register button
        JButton btnRegister = new JButton("Registrarse");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                padre.setVisible(true);
                setVisible(false);
                String nombre = textFieldNombre.getText();
                String apellido = textFieldApellido.getText();
                String email = textFieldEmail.getText();
                String telefono = textFieldTelefono.getText();
                String dni = textFieldDni.getText();
                String contrasena = textFieldContrasena.getText();
                int codPostal = 0; // prueba

                // Verificar que el listener no sea nulo y luego llamar al método
                // registrarUsuario
                if (serverFacade != null) {
                    // serverFacade.registrarse(nombre, apellido, dni, email, telefono, contrasena,
                    // codPostal); ¿¿ERROR QEU TIENE DOS ATRIBUTOS EN SERVERFACADE PERO NO??
                }

                // Cerrar esta ventana
                setVisible(false);
            }
        });
        btnRegister.setForeground(new Color(0, 0, 255)); // Set the button text to blue
        GridBagConstraints gbc_btnRegister = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE; // Do not let the button fill the space
        gbc.anchor = GridBagConstraints.CENTER; // Align the button to the left
        contentPane.add(btnRegister, gbc);

        // Back button
        JButton btnAtrs = new JButton("ATRAS");
        btnAtrs.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAtrs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                padre.setVisible(true);
                Ventana2.this.setVisible(false);
                Ventana2.this.dispose();
            }
        });
        gbc.insets = new Insets(-20, -200, 0, 0);
        gbc.fill = GridBagConstraints.NONE; // Do not let the button fill the space
        gbc.anchor = GridBagConstraints.CENTER; // Align the button to the left
        contentPane.add(btnAtrs, gbc);
    }

    /**
     * Helper method to add a label and a text field to the panel.
     */
    private void addLabelAndTextField(String labelText, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 11)); // Set the font
        contentPane.add(label, gbc);

        JTextField textField = new JTextField(10);
        textField.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY)); // Set border for the text fields
        contentPane.add(textField, gbc);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana2 frame = new Ventana2(null); // Puedes pasar null o una referencia a otra ventana como
                                                         // argumento
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
