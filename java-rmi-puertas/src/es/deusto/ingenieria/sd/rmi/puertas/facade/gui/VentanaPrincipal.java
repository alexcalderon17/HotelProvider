package es.deusto.ingenieria.sd.rmi.puertas.facade.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        // Configuración de la ventana principal
        setTitle("Introduzca su Código de Reserva");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear panel principal con un layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); // Color de fondo azul claro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta de instrucciones
        JLabel label = new JLabel("Ingrese su Código de Reserva:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        // Campo de texto para introducir el código
        JTextField textField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textField, gbc);

        // Botón de aceptar
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(new Font("Arial", Font.BOLD, 14));
        acceptButton.setBackground(new Color(100, 149, 237)); // Azul medio
        acceptButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(acceptButton, gbc);

        // Acción del botón de aceptar
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoReserva = textField.getText();
                JOptionPane.showMessageDialog(panel, "Código de reserva introducido: " + codigoReserva);
            }
        });

        // Añadir el panel a la ventana principal
        add(panel);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            }
        });
    }
}
