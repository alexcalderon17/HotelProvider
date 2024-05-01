package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.*;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.IServer;
import es.deusto.ingenieria.sd.rmi.server.InvalidUser;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class Ventana4 extends JFrame {
    private JTable tablaApartamentos;

    public Ventana4(List<AlojamientoAtributes> alojamientos) {
        setTitle("Lista de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Descripcion");
        model.addColumn("Direccion");

    
        int id = 0;

        for (AlojamientoAtributes alojamiento : alojamientos) {
            id++;
            model.addRow(
                    
                    new Object[] { id, alojamiento.getNombre(), alojamiento.getDescripcion(), alojamiento.getDireccion() });
        }

        
        tablaApartamentos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaApartamentos);

        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
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
            // Crear la ventana y mostrar los apartamentos
            SwingUtilities.invokeLater(() -> {
                Ventana4 ventana = new Ventana4(alojamientos);
                ventana.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("- Exception running the client: " + e.getMessage());
            e.printStackTrace();

        }
    }

}
