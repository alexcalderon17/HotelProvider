import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inicio frame = new Inicio();
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
    public Inicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBienvenido = new JLabel("BAMP");
        lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBienvenido.setBounds(180, 11, 89, 14);
        contentPane.add(lblBienvenido);

        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setBounds(50, 70, 120, 14);
        contentPane.add(lblCorreo);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(180, 67, 200, 20);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(50, 111, 120, 14);
        contentPane.add(lblContraseña);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 108, 200, 20);
        contentPane.add(passwordField);

        JButton btnIniciarSesion = new JButton("INICIAR SESIÓN");
        btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnIniciarSesion.setBounds(180, 149, 200, 25);
        btnIniciarSesion.setFocusPainted(false);
        contentPane.add(btnIniciarSesion);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRegistrarse.setBounds(180, 185, 200, 25);
        btnRegistrarse.setFocusPainted(false);
        contentPane.add(btnRegistrarse);
    }
}
