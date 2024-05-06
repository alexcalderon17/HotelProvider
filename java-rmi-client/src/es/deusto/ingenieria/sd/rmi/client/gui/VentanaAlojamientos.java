package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaAlojamientos extends JFrame {
    private JTable tablaApartamentos;
    private JTextField textFieldFechaInicio;
    private JTextField textFieldFechaFin;
    private JComboBox<Integer> comboBoxNumViajeros;
    private JTextField textFieldPrecioMin;
    private JTextField textFieldPrecioMax;

    public VentanaAlojamientos(List<AlojamientoAtributes> alojamientos) {
        setTitle("Lista de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para los componentes superiores
        JPanel panelSuperior = new JPanel(new GridLayout(2, 6, 5, 5)); // 2 filas, 6 columnas

        // TextFields para Fechas, Precio Mínimo y Máximo
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        textFieldFechaInicio = new JTextField();
        panelSuperior.add(lblFechaInicio);
        panelSuperior.add(textFieldFechaInicio);

        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        textFieldFechaFin = new JTextField();
        panelSuperior.add(lblFechaFin);
        panelSuperior.add(textFieldFechaFin);

        JLabel lblNumViajeros = new JLabel("Nº de Viajeros:");
        // JComboBox para seleccionar el número de viajeros
        comboBoxNumViajeros = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            comboBoxNumViajeros.addItem(i);
        }
        panelSuperior.add(lblNumViajeros);
        panelSuperior.add(comboBoxNumViajeros);

        JLabel lblPrecioMin = new JLabel("Precio Mínimo:");
        textFieldPrecioMin = new JTextField();
        panelSuperior.add(lblPrecioMin);
        panelSuperior.add(textFieldPrecioMin);

        JLabel lblPrecioMax = new JLabel("Precio Máximo:");
        textFieldPrecioMax = new JTextField();
        panelSuperior.add(lblPrecioMax);
        panelSuperior.add(textFieldPrecioMax);

        // Ajuste de espaciado entre los componentes
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10)); // Añadir espacio alrededor del panel

        // Añadir el panel superior al contenedor
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Botón para filtrar
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnFiltrar.setBounds(150, 220, 150, 30);
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí va la lógica de filtrado
                // Obtener datos de los filtros
                /* String fechaInicio = textFieldFechaInicio.getText();
                String fechaFin = textFieldFechaFin.getText();
                int numViajeros = (int) comboBoxNumViajeros.getSelectedItem();
                double precioMin = Double.parseDouble(textFieldPrecioMin.getText());
                double precioMax = Double.parseDouble(textFieldPrecioMax.getText());*/

                // Llamar al método de filtrado y actualizar la tabla
                // filterResults(fechaInicio, fechaFin, numViajeros, precioMin, precioMax);
            }
        });
        getContentPane().add(btnFiltrar, BorderLayout.CENTER); // Añadir el botón al centro del contenedor

        // Tabla de Apartamentos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Descripción");
        model.addColumn("Dirección");

        int id = 0;

        for (AlojamientoAtributes alojamiento : alojamientos) {
            id++;
            model.addRow(
                new Object[] { id, alojamiento.getNombre(), alojamiento.getDescripcion(), alojamiento.getDireccion() });
        }

        tablaApartamentos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaApartamentos);

        getContentPane().add(scrollPane, BorderLayout.SOUTH); // Añadir la tabla en la parte inferior
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        ServerFacade stubServer = null;

        try {
            Registry registry = LocateRegistry.getRegistry(((Integer.valueOf(args[1]))));
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            stubServer = (ServerFacade) registry.lookup(name);
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
