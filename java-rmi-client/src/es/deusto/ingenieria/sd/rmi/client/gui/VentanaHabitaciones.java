package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
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
    private JFormattedTextField txtFechaInicio;
    private JFormattedTextField txtFechaFin;
    private JButton btnReservar;
    private JButton btnAtras;
    private List<HabitacionDTO> habitaciones; 
    private ServerFacade serverFacade;
    private AlojamientoDTO alojamientoSeleccionado;
    private UsuarioDTO usuarioLogeado;

    public VentanaHabitaciones(AlojamientoDTO alojamientoSeleccionado, UsuarioDTO usuarioLogeado) throws RemoteException {
        this.alojamientoSeleccionado = alojamientoSeleccionado;
        this.usuarioLogeado = usuarioLogeado;
        System.out.println("Id AlojamientoSeleccionado: " + alojamientoSeleccionado.getId());
        serverFacade = RMIServiceLocator.getInstance().getService();
        habitaciones = serverFacade.obtenerHabitaciones(alojamientoSeleccionado.getId(),);

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
            HabitacionDTO seleccionada = habitaciones.get(index);
            lblAforo.setText("Aforo: " + seleccionada.getAforo());
            lblDescripcion.setText("Descripción: " + seleccionada.getDescripcion());
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
        btnReservar.setEnabled(true); // Botón activado siempre

        btnReservar.addActionListener(e -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false); // Esto hace que SimpleDateFormat sea más estricto con las fechas
                Date fechaInicio = sdf.parse(txtFechaInicio.getText());
                Date fechaFin = sdf.parse(txtFechaFin.getText());

                // Validar si las fechas ingresadas son válidas
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicio);
                int diaInicio = calendar.get(Calendar.DAY_OF_MONTH);
                int mesInicio = calendar.get(Calendar.MONTH) + 1; // Los meses se indexan desde 0
                int añoInicio = calendar.get(Calendar.YEAR);

                calendar.setTime(fechaFin);
                int diaFin = calendar.get(Calendar.DAY_OF_MONTH);
                int mesFin = calendar.get(Calendar.MONTH) + 1;
                int añoFin = calendar.get(Calendar.YEAR);

                // Verificar si los días, meses y años son válidos
                if (isValidDate(diaInicio, mesInicio, añoInicio) && isValidDate(diaFin, mesFin, añoFin)) {
                    if (!fechaInicio.after(fechaFin)) {
                        int selectedIndex = listHabitaciones.getSelectedIndex();
                        if (selectedIndex != -1) {
                            HabitacionDTO habitacionSeleccionada = habitaciones.get(selectedIndex);
                            ReservaDTO reserva = new ReservaDTO(usuarioLogeado.getCorreo(), alojamientoSeleccionado.getNombre(), habitacionSeleccionada.getNombre(), fechaInicio, fechaFin);
                            if (serverFacade != null) {
                                try {
                                    serverFacade.guardarReserva(reserva);
                                    EventQueue.invokeLater(() -> {
                                        try {
                                            JOptionPane.showMessageDialog(VentanaHabitaciones.this, "¡Reserva guardada!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    });
                                } catch (RemoteException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Ha ocurrido un error al guardar la reserva", "Error guardarReserva", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin.", "Error de fechas", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese fechas válidas.", "Error de fechas", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese las fechas en el formato correcto (dd/mm/aaaa).", "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        });

        detailsPanel.add(new JLabel("Aforo:"));
        detailsPanel.add(lblAforo);
        detailsPanel.add(new JLabel("Descripción:"));
        detailsPanel.add(lblDescripcion);

        detailsPanel.add(new JLabel("Fecha inicio (dd/mm/aaaa):"));
        detailsPanel.add(txtFechaInicio);
        detailsPanel.add(new JLabel("Fecha fin (dd/mm/aaaa):"));
        detailsPanel.add(txtFechaFin);
        detailsPanel.add(btnReservar);

        getContentPane().add(detailsPanel, BorderLayout.CENTER);
    }

    private void initBottomPanel() {
        btnAtras = new JButton("Atrás");
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

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(btnAtras);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }

    private JFormattedTextField createFormattedTextField() {
        JFormattedTextField formattedTextField = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy")));
        formattedTextField.setColumns(10);
        formattedTextField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        return formattedTextField;
    }

    
    public static void main(String[] args) {
        RMIServiceLocator rmiServiceLocator = new RMIServiceLocator(args[0], args[1], args[2]);
        EventQueue.invokeLater(() -> {
            try {
                UsuarioDTO tesUsuarioDTO = createTestUsuarioDTO();
                AlojamientoDTO testAlojamiento = createTestAlojamiento();
                VentanaHabitaciones frame = new VentanaHabitaciones(testAlojamiento, tesUsuarioDTO);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private static AlojamientoDTO createTestAlojamiento() {
        // Create a test AlojamientoAtributes object for demonstration purposes
        AlojamientoDTO testAlojamiento = new AlojamientoDTO();
        testAlojamiento.setNombre("Test Hotel");
        testAlojamiento.setDescripcion("Un hotel de prueba para demostración.");
        testAlojamiento.setDireccion("123 Demo Street, Demo City");
        return testAlojamiento;
    }
    private static UsuarioDTO createTestUsuarioDTO() {
        // Create a test AlojamientoAtributes object for demonstration purposes
        UsuarioDTO testUsuarioDTO = new UsuarioDTO();
        testUsuarioDTO.setCorreo("Test Correo");
       
        return testUsuarioDTO;
    }

    private boolean isValidDate(int day, int month, int year) {
        if (year < 0 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int maxDays = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDays = 30;
        } else if (month == 2) {
            maxDays = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        }
        return day <= maxDays;
    }
}
