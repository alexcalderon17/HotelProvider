/*package es.deusto.ingenieria.sd.rmi.client.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Ventana3 extends JFrame {
    private JComboBox<String> destinoComboBox;
    private JTextField fechaLlegadaField;
    private JTextField fechaSalidaField;
    private JComboBox<Integer> viajerosComboBox;
    private Ventana1 padre;

    public Ventana3(Ventana1 padre) {
        this.padre = padre;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        destinoComboBox = new JComboBox<>();
        fechaLlegadaField = new JTextField(10);
        fechaSalidaField = new JTextField(10);
        viajerosComboBox = new JComboBox<>();

        for (int i = 1; i <= 10; i++) {
            viajerosComboBox.addItem(i);
        }

        obtenerDestinosDeBD();

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // controller.getAlojamientos(destinoComboBox.getSelectedItem(),
                //                            fechaLlegadaField.getText(),
                //                            fechaSalidaField.getText(),
                //                            (int) viajerosComboBox.getSelectedItem());
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel("Destino:"), gbc);
        gbc.gridx = 1;
        panel.add(destinoComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Fecha de Llegada:"), gbc);
        gbc.gridx = 1;
        panel.add(fechaLlegadaField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Fecha de Salida:"), gbc);
        gbc.gridx = 1;
        panel.add(fechaSalidaField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Viajeros:"), gbc);
        gbc.gridx = 1;
        panel.add(viajerosComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(buscarButton, gbc);

        getContentPane().add(panel);
    }

    private void obtenerDestinosDeBD() {
        ArrayList<String> destinos = new ArrayList<>();
        destinos.add("Destino 1");
        destinos.add("Destino 2");
        destinos.add("Destino 3");

        for (String destino : destinos) {
            destinoComboBox.addItem(destino);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ventana3(null).setVisible(true);
            }
        });
    }
}*/
