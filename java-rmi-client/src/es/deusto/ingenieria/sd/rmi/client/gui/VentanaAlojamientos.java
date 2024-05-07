package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaAlojamientos extends JFrame {
    private JTextField textFieldFechaInicio;
    private JTextField textFieldFechaFin;
    private JComboBox<Integer> comboBoxNumViajeros;
    private JTextField textFieldPrecioMin;
    private JTextField textFieldPrecioMax;
    private JTextPane textPaneInfo;
    private JList<String> alojamientosJList;
    private DefaultListModel<String> listModel;
    private JButton btnAceptar;
    private ServerFacade serverFacade;
    private List<AlojamientoAtributes> alojamientos;

    public VentanaAlojamientos() throws RemoteException {
        serverFacade = RMIServiceLocator.getInstance().getService();
        alojamientos = serverFacade.obtenerAlojamientos();

        setTitle("Lista de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para los componentes superiores
        JPanel panelSuperior = new JPanel(new GridLayout(2, 6, 5, 5));
        configuraPanelFiltros(panelSuperior);
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Lista de alojamientos
        JPanel panelAlojamientos = new JPanel(new BorderLayout());
        panelAlojamientos.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        listModel = new DefaultListModel<>();
        for (AlojamientoAtributes alojamiento : alojamientos) {
            listModel.addElement(alojamiento.getNombre());
        }
        alojamientosJList = new JList<>(listModel);
        alojamientosJList.setPreferredSize(new Dimension(200, 150)); // Ajusta este valor según necesites
        alojamientosJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !alojamientosJList.isSelectionEmpty()) {
                AlojamientoAtributes alojamientoSeleccionado = alojamientos.get(alojamientosJList.getSelectedIndex());
                textPaneInfo.setText("<html><b>Descripción:</b> <br/>" + alojamientoSeleccionado.getDescripcion() + "<br/><br/><b>Dirección:</b> <br/>" + alojamientoSeleccionado.getDireccion() + "</html>");
            }
        });
        JScrollPane listScrollPane = new JScrollPane(alojamientosJList);
        panelAlojamientos.add(listScrollPane, BorderLayout.CENTER);
        mainPanel.add(panelAlojamientos, BorderLayout.NORTH);

        // Área de texto con formato HTML para mostrar información
        textPaneInfo = new JTextPane();
        textPaneInfo.setContentType("text/html");
        textPaneInfo.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(textPaneInfo);
        infoScrollPane.setPreferredSize(new Dimension(200, 150)); // Asegúrate de ajustar también este tamaño
        mainPanel.add(infoScrollPane, BorderLayout.CENTER);

        // Botón Aceptar
        btnAceptar = new JButton("Aceptar");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnAceptar);
            mainPanel.add(panelBoton, BorderLayout.SOUTH);

    btnAceptar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (serverFacade != null) {
                try {
                    List<HabitacionAtributes> habitaciones = serverFacade.obtenerHabitaciones();
                    VentanaHabitaciones va = new VentanaHabitaciones(habitaciones);
                    va.setVisible(true);
                    dispose();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(VentanaAlojamientos.this, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
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
    RMIServiceLocator rmiServiceLocator = new RMIServiceLocator(args[0], args[1], args[2]);
    EventQueue.invokeLater(() -> {
        try {
            VentanaAlojamientos frame = new VentanaAlojamientos();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}
}
