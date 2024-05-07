package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaHabitaciones extends JFrame {
    private JTable tableHabitaciones;
    private DefaultTableModel tableModel;
    private ServerFacade serverFacade;

    public VentanaHabitaciones(List<HabitacionAtributes> habitaciones) {
        setTitle("Lista de Habitaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración de la tabla
        tableHabitaciones = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {"ID", "Tipo", "Precio", "Capacidad"});
        tableHabitaciones.setModel(tableModel);
        fillTable(habitaciones);

        // Añadir la tabla a un JScrollPane para permitir desplazamiento vertical
        JScrollPane scrollPane = new JScrollPane(tableHabitaciones);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        // Añadir botón Atrás
        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                EventQueue.invokeLater(() -> {
                    try {
                        VentanaAlojamientos ventanaAlojamientos = new VentanaAlojamientos();
                        ventanaAlojamientos.setVisible(true);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(btnAtras);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }
    

    private void fillTable(List<HabitacionAtributes> habitaciones) {
        for (HabitacionAtributes habitacion : habitaciones) {
            tableModel.addRow(new Object[]{
                habitacion.getNombre(),
                habitacion.getAforo(),
                habitacion.getDescripcion(),




                
            });
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        try {
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(args[1]));
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            ServerFacade stubServer = (ServerFacade) registry.lookup(name);
            List<HabitacionAtributes> habitaciones = stubServer.obtenerHabitaciones();

            SwingUtilities.invokeLater(() -> {
                VentanaHabitaciones ventana = new VentanaHabitaciones(habitaciones);
                ventana.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("Exception running the client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
