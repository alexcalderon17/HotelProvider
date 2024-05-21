package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class VentanaHabitaciones extends JFrame {
    private JList<String> listHabitaciones;
    private DefaultListModel<String> listModel;
    private JLabel lblAforo;
    private JLabel lblDescripcion;
    private JButton btnReservar;
    private JButton btnAtras;
    private List<HabitacionDTO> habitaciones;
    private ServerFacade serverFacade;
    private AlojamientoDTO alojamientoSeleccionado;
    private UsuarioDTO usuarioLogeado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public VentanaHabitaciones(AlojamientoDTO alojamientoSeleccionado, UsuarioDTO usuarioLogeado, LocalDate fechaInicio, LocalDate fechaFin) throws RemoteException {
        this.alojamientoSeleccionado = alojamientoSeleccionado;
        this.usuarioLogeado = usuarioLogeado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        System.out.println("Id AlojamientoSeleccionado: " + alojamientoSeleccionado.getId());
        serverFacade = RMIServiceLocator.getInstance().getService();
        habitaciones = serverFacade.obtenerHabitaciones(alojamientoSeleccionado.getId(), fechaInicio, fechaFin);
        System.out.println("Las habitaciones libres son estas: " + habitaciones);
        setTitle("Lista de Habitaciones");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        getContentPane().setLayout(null); // Desactiva el layout manager
        setBackground(new Color(255, 255, 255));

        initListPanel();
        initDetailsPanel();
        initBottomPanel();
    }

    private void initListPanel() {
        System.out.println("Entra en initListPanel");
        listModel = new DefaultListModel<>();
        habitaciones.forEach(habitacion -> listModel.addElement(habitacion.getNombre()));
        listHabitaciones = new JList<>(listModel);
        listHabitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listHabitaciones.setFont(new Font("Arial", Font.BOLD, 14));
        listHabitaciones.setBackground(new Color(242, 242, 242));
        listHabitaciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(listHabitaciones);
        scrollPane.setBounds(10, 50, 320, 390); // Ajuste del tamaño y la posición del JScrollPane

        TitledBorder listPanelBorder = BorderFactory.createTitledBorder("Habitaciones Disponibles");
        listPanelBorder.setTitleFont(new Font("Tahoma", Font.BOLD, 18));
        JPanel listPanel = new JPanel(null);
        listPanel.setBounds(10, 10, 340, 450);
        listPanel.setBorder(listPanelBorder);
        listPanel.add(scrollPane);

        getContentPane().add(listPanel);

        listHabitaciones.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showRoomDetails();
            }
        });
    }

    private void showRoomDetails() {
        int index = listHabitaciones.getSelectedIndex();
        if (index != -1) {
            HabitacionDTO seleccionada = habitaciones.get(index);
            lblAforo.setText(seleccionada.getAforo() + " Personas");
            lblDescripcion.setText("<html><body style='width: 360px;'>" + seleccionada.getDescripcion() + "</body></html>");
        }
    }

    private void initDetailsPanel() {
        JPanel detailsPanel = new JPanel(null);
        detailsPanel.setBounds(370, 10, 400, 450);
        
        TitledBorder detailsBorder = BorderFactory.createTitledBorder("Detalles de la Habitación");
        detailsBorder.setTitleFont(new Font("Tahoma", Font.BOLD, 18)); // Tamaño de fuente más grande para el borde
        detailsPanel.setBorder(detailsBorder);

        JLabel lblAforoTitle = new JLabel("Aforo:");
        lblAforoTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAforoTitle.setBounds(20, 20, 100, 30);
        detailsPanel.add(lblAforoTitle);

        lblAforo = new JLabel();
        lblAforo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAforo.setBounds(120, 20, 250, 30);
        detailsPanel.add(lblAforo);

        JLabel lblDescripcionTitle = new JLabel("Descripción:");
        lblDescripcionTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDescripcionTitle.setBounds(20, 70, 150, 30);
        detailsPanel.add(lblDescripcionTitle);

        lblDescripcion = new JLabel();
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
        lblDescripcion.setBounds(20, 110, 360, 200);
        detailsPanel.add(lblDescripcion);

        btnReservar = new JButton("Reservar");
        btnReservar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnReservar.setBounds(90, 330, 200, 40); // Centrado en la parte inferior
        btnReservar.addActionListener(e -> {
            int selectedIndex = listHabitaciones.getSelectedIndex();
            if (selectedIndex != -1) {
                HabitacionDTO habitacionSeleccionada = habitaciones.get(selectedIndex);
                ReservaDTO reserva = new ReservaDTO(usuarioLogeado.getCorreo(), alojamientoSeleccionado.getNombre(), habitacionSeleccionada.getNombre(), fechaInicio, fechaFin);
                if (serverFacade != null) {
                    try {
                        System.out.println(usuarioLogeado);
                        serverFacade.guardarReserva(reserva, usuarioLogeado);
                        JOptionPane.showMessageDialog(VentanaHabitaciones.this, "¡Reserva guardada!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error", "Error guardarReserva", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    throw new RuntimeException("El ServerFacade es null");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una habitación", "Error guardarReserva", JOptionPane.ERROR_MESSAGE);
            }
        });
        detailsPanel.add(btnReservar);

        getContentPane().add(detailsPanel);
    }

    private void initBottomPanel() {
        btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAtras.setBounds(10, 510, 100, 40); // Posicionado abajo a la izquierda
        btnAtras.addActionListener(e -> {
            dispose();
            EventQueue.invokeLater(() -> {
                try {
                    VentanaAlojamientos ventanaAlojamientos = new VentanaAlojamientos(usuarioLogeado);
                    ventanaAlojamientos.setVisible(true);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            });
        });

        getContentPane().add(btnAtras);
    }
}
