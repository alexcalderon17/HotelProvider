package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionAtributes;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaHabitaciones extends JFrame {
    private JList<String> listHabitaciones;
    private DefaultListModel<String> listModel;
    private JLabel lblAforo;
    private JLabel lblDescripcion;
    private JFormattedTextField txtFechaInicio;
    private JFormattedTextField txtFechaFin;
    private JButton btnReservar;
    private JButton btnAtras;
    private List<HabitacionAtributes> habitaciones; // Store the list of habitaciones

    public VentanaHabitaciones(List<HabitacionAtributes> habitaciones) {
        this.habitaciones = habitaciones; // Initialize with the passed list

        setTitle("Lista de Habitaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        initListPanel();
        initDetailsPanel();
        initBottomPanel();
    }

    private void initListPanel() {
        listModel = new DefaultListModel<>();
        habitaciones.forEach(habitacion -> listModel.addElement(habitacion.getNombre()));
        listHabitaciones = new JList<>(listModel);
        listHabitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listHabitaciones.setFont(new Font("Arial", Font.BOLD, 14));
        listHabitaciones.setBackground(new Color(242, 242, 242));
        listHabitaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(listHabitaciones);
        scrollPane.setPreferredSize(new Dimension(200, 0));

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(scrollPane, BorderLayout.CENTER);
        listPanel.setBorder(BorderFactory.createTitledBorder("Habitaciones Disponibles"));

        getContentPane().add(listPanel, BorderLayout.WEST);

        listHabitaciones.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showRoomDetails();
            }
        });
    }

    private void showRoomDetails() {
        int index = listHabitaciones.getSelectedIndex();
        if (index != -1) {
            HabitacionAtributes seleccionada = habitaciones.get(index);
            lblAforo.setText("Aforo: " + seleccionada.getAforo());
            lblDescripcion.setText("Descripción: " + seleccionada.getDescripcion());
            updateReserveButton();
        }
    }

    private void initDetailsPanel() {
        JPanel detailsPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblAforo = new JLabel();
        lblDescripcion = new JLabel();
        txtFechaInicio = createFormattedTextField();
        txtFechaFin = createFormattedTextField();
        btnReservar = new JButton("Reservar");
        btnReservar.setEnabled(false); // Botón desactivado inicialmente

        // Se añaden las etiquetas de aforo y descripción
        detailsPanel.add(new JLabel("Aforo:"));
        detailsPanel.add(lblAforo);
        detailsPanel.add(new JLabel("Descripción:"));
        detailsPanel.add(lblDescripcion);

        // Las demás componentes permanecen igual
        detailsPanel.add(new JLabel("Fecha inicio (dd/mm/aaaa):"));
        detailsPanel.add(txtFechaInicio);
        detailsPanel.add(new JLabel("Fecha fin (dd/mm/aaaa):"));
        detailsPanel.add(txtFechaFin);
        detailsPanel.add(btnReservar);

        btnReservar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Reserva realizada correctamente!"));

        getContentPane().add(detailsPanel, BorderLayout.CENTER);
    }

    private void initBottomPanel() {
        btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(e -> {
            dispose();
            EventQueue.invokeLater(() -> {
                try {
                    VentanaAlojamientos ventanaAlojamientos = new VentanaAlojamientos();
                    ventanaAlojamientos.setVisible(true);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            });
        });

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(btnAtras);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }

    private JFormattedTextField createFormattedTextField() {
        JFormattedTextField formattedTextField = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy")));
        formattedTextField.setColumns(10);
        formattedTextField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        formattedTextField.getDocument().addDocumentListener((SimpleDocumentListener) this::updateReserveButton);
        return formattedTextField;
    }

    private void updateReserveButton() {
        String fechaInicio = txtFechaInicio.getText().trim();
        String fechaFin = txtFechaFin.getText().trim();

        boolean validDates = isValidDate(fechaInicio) && isValidDate(fechaFin);
        if (validDates) {
            try {
                Date fechaInicioDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicio);
                Date fechaFinDate = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFin);

                if (fechaFinDate.after(fechaInicioDate)) {
                    btnReservar.setEnabled(true);
                } else {
                    btnReservar.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "La fecha de fin debe ser posterior a la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException e) {
                btnReservar.setEnabled(false);
            }
        } else {
            btnReservar.setEnabled(false);
        }
    }

    private boolean isValidDate(String date) {
        return date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$");
    }

    @FunctionalInterface
    interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
        void onChange();

        @Override
        default void insertUpdate(javax.swing.event.DocumentEvent e) {
            onChange();
        }

        @Override
        default void removeUpdate(javax.swing.event.DocumentEvent e) {
            onChange();
        }

        @Override
        default void changedUpdate(javax.swing.event.DocumentEvent e) {
            onChange();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
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
