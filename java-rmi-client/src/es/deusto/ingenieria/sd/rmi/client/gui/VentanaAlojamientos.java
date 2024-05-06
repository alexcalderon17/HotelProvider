package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaAlojamientos extends JFrame {
    private JTextField textFieldFechaInicio;
    private JTextField textFieldFechaFin;
    private JComboBox<Integer> comboBoxNumViajeros;
    private JTextField textFieldPrecioMin;
    private JTextField textFieldPrecioMax;
    private JTextArea textAreaInfo;
    private JList<String> listAlojamientos;
    private DefaultListModel<String> listModel;

    public VentanaAlojamientos(List<AlojamientoAtributes> alojamientos) {
        setTitle("Lista de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para los componentes superiores
        JPanel panelSuperior = new JPanel(new GridLayout(2, 6, 5, 5));
        configuraPanelFiltros(panelSuperior);
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Botones a la izquierda para la descripción y dirección
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JButton btnDescripcion = new JButton("Descripción");
        JButton btnDireccion = new JButton("Dirección");
        leftPanel.add(btnDescripcion);
        leftPanel.add(btnDireccion);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Panel de alojamientos con lista
        JPanel panelAlojamientos = new JPanel(new BorderLayout());
        panelAlojamientos.setBorder(BorderFactory.createEmptyBorder(110, 40, 110, 40)); // Espacio alrededor del panel

        listModel = new DefaultListModel<>();
        for (AlojamientoAtributes alojamiento : alojamientos) {
            listModel.addElement(alojamiento.getNombre()); // Solo nombre
        }
        listAlojamientos = new JList<>(listModel);
        panelAlojamientos.add(new JScrollPane(listAlojamientos), BorderLayout.CENTER);

        mainPanel.add(panelAlojamientos, BorderLayout.CENTER);

        // Area de texto para mostrar información
        textAreaInfo = new JTextArea();
        textAreaInfo.setEditable(false);
        mainPanel.add(textAreaInfo, BorderLayout.SOUTH);

        // Acción de los botones
        btnDescripcion.addActionListener(e -> {
            if (!listAlojamientos.isSelectionEmpty()) {
                textAreaInfo.setText(alojamientos.get(listAlojamientos.getSelectedIndex()).getDescripcion());
            }
        });
        btnDireccion.addActionListener(e -> {
            if (!listAlojamientos.isSelectionEmpty()) {
                textAreaInfo.setText(alojamientos.get(listAlojamientos.getSelectedIndex()).getDireccion());
            }
        });
    }

    private void configuraPanelFiltros(JPanel panel) {
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        textFieldFechaInicio = new JTextField();
        panel.add(lblFechaInicio);
        panel.add(textFieldFechaInicio);

        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        textFieldFechaFin = new JTextField();
        panel.add(lblFechaFin);
        panel.add(textFieldFechaFin);

        JLabel lblNumViajeros = new JLabel("Nº de Viajeros:");
        comboBoxNumViajeros = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            comboBoxNumViajeros.addItem(i);
        }
        panel.add(lblNumViajeros);
        panel.add(comboBoxNumViajeros);

        JLabel lblPrecioMin = new JLabel("Precio Mínimo:");
        textFieldPrecioMin = new JTextField();
        panel.add(lblPrecioMin);
        panel.add(textFieldPrecioMin);

        JLabel lblPrecioMax = new JLabel("Precio Máximo:");
        textFieldPrecioMax = new JTextField();
        panel.add(lblPrecioMax);
        panel.add(textFieldPrecioMax);
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        try {
            Registry registry = LocateRegistry.getRegistry((Integer.valueOf(args[1])));
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            ServerFacade stubServer = (ServerFacade) registry.lookup(name);
            List<AlojamientoAtributes> alojamientos = stubServer.obtenerAlojamientos();

            SwingUtilities.invokeLater(() -> {
                VentanaAlojamientos ventana = new VentanaAlojamientos(alojamientos);
                ventana.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("- Exception running the client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
