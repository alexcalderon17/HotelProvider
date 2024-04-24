import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Registro extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldEmail;
    private JTextField textFieldTelefono;
    private JTextField textFieldDNI;
    private JPasswordField passwordField;
    private JTextField textFieldCodigoPostal;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registro frame = new Registro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Registro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 490, 504); // Modificado para acomodar más elementos
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("BAMP");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(180, 11, 89, 14);
        contentPane.add(lblTitulo);

        JLabel lblRegistro = new JLabel("¡Registrarse!");
        lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblRegistro.setBounds(165, 36, 120, 14);
        contentPane.add(lblRegistro);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(100, 61, 250, 20);
        contentPane.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setBounds(100, 86, 250, 14);
        contentPane.add(lblNombre);

        textFieldApellido = new JTextField();
        textFieldApellido.setBounds(100, 111, 250, 20);
        contentPane.add(textFieldApellido);
        textFieldApellido.setColumns(10);

        JLabel lblApellido = new JLabel("APELLIDO");
        lblApellido.setBounds(100, 136, 250, 14);
        contentPane.add(lblApellido);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(100, 161, 250, 20);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblEmail = new JLabel("CORREO ELECTRÓNICO");
        lblEmail.setBounds(100, 186, 250, 14);
        contentPane.add(lblEmail);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setBounds(100, 211, 250, 20);
        contentPane.add(textFieldTelefono);
        textFieldTelefono.setColumns(10);

        JLabel lblTelefono = new JLabel("N° TELÉFONO");
        lblTelefono.setBounds(100, 236, 250, 14);
        contentPane.add(lblTelefono);

        textFieldDNI = new JTextField();
        textFieldDNI.setBounds(100, 261, 250, 20);
        contentPane.add(textFieldDNI);
        textFieldDNI.setColumns(10);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setBounds(100, 286, 250, 14);
        contentPane.add(lblDni);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 311, 250, 20);
        contentPane.add(passwordField);

        JLabel lblContraseña = new JLabel("CONTRASEÑA");
        lblContraseña.setBounds(100, 336, 250, 14);
        contentPane.add(lblContraseña);

        textFieldCodigoPostal = new JTextField();
        textFieldCodigoPostal.setBounds(100, 361, 250, 20);
        contentPane.add(textFieldCodigoPostal);
        textFieldCodigoPostal.setColumns(10);

        JLabel lblCodigoPostal = new JLabel("CÓDIGO POSTAL");
        lblCodigoPostal.setBounds(100, 386, 250, 14);
        contentPane.add(lblCodigoPostal);

        JButton btnRegistrarse = new JButton("REGISTRARSE");
        btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRegistrarse.setBounds(100, 411, 250, 25);
        contentPane.add(btnRegistrarse);
    }
}
