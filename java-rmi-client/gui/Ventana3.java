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
    	this.padre=padre;
    	
        //super("Filtros de B�squeda");

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Crear componentes
        destinoComboBox = new JComboBox<>();
        fechaLlegadaField = new JTextField(10);
        fechaSalidaField = new JTextField(10);
        viajerosComboBox = new JComboBox<>();

        // Llenar el ComboBox de viajeros
        for (int i = 1; i <= 10; i++) {
            viajerosComboBox.addItem(i);
        }

        // Obtener destinos de la base de datos y llenar el ComboBox
        obtenerDestinosDeBD();

        // Crear el bot�n de b�squeda
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aqu� puedes llamar al m�todo getAlojamientos del controlador
                // pasando los valores de los filtros como argumentos
                // por ejemplo:
                // controller.getAlojamientos(destinoComboBox.getSelectedItem(),
                //                            fechaLlegadaField.getText(),
                //                            fechaSalidaField.getText(),
                //                            (int) viajerosComboBox.getSelectedItem());
            }
        });

        // Configurar el dise�o de la ventana con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel("Destino:"), gbc);
        gbc.gridx = 1; // Mover al pr�ximo columna
        panel.add(destinoComboBox, gbc);
        gbc.gridx = 0; // Restaurar a la columna original
        gbc.gridy++; // Mover a la pr�xima fila
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
        // Aqu� debes implementar la l�gica para conectar a la base de datos
        // y obtener los destinos disponibles, luego agregarlos al ComboBox
        // Aqu� se incluye un ejemplo b�sico sin conexi�n a base de datos
        // que puedes reemplazar con tu l�gica real
        ArrayList<String> destinos = new ArrayList<>();
        destinos.add("Destino 1");
        destinos.add("Destino 2");
        destinos.add("Destino 3");

        for (String destino : destinos) {
            destinoComboBox.addItem(destino);
        }
    }

  
}