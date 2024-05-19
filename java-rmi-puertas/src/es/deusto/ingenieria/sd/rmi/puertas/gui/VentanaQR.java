package es.deusto.ingenieria.sd.rmi.puertas.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class VentanaQR extends JFrame {
    public VentanaQR(byte[] codigoQR)  {
        
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
        JLabel label = new JLabel("Escanea este codigo qr en la puerta", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);

        // Espacio para la imagen del código QR
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(codigoQR);
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            JLabel qrCodePlaceholder = new JLabel(imageIcon);
            qrCodePlaceholder.setHorizontalAlignment(JLabel.CENTER);
            qrCodePlaceholder.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para indicar el espacio de la imagen
            panel.add(qrCodePlaceholder, BorderLayout.CENTER);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        add(panel);
        

    }

  
}

