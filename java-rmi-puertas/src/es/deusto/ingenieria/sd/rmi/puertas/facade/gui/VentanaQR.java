package es.deusto.ingenieria.sd.rmi.puertas.facade.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaQR extends JFrame {
    public VentanaQR() {
        // Configuración de la ventana principal
        setTitle("Código QR de Reserva");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear panel principal con un layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Color de fondo azul claro

        // Etiqueta de instrucciones
        JLabel label = new JLabel("Mediante este código QR podrás entrar en tu habitación", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);

        // Espacio para la imagen del código QR
        JLabel qrCodePlaceholder = new JLabel();
        qrCodePlaceholder.setHorizontalAlignment(JLabel.CENTER);
        qrCodePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para indicar el espacio de la imagen
        panel.add(qrCodePlaceholder, BorderLayout.CENTER);

        // Añadir el panel a la ventana principal
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaQR ventana = new VentanaQR();
                ventana.setVisible(true);
            }
        });
    }
}

