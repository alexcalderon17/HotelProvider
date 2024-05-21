package es.deusto.ingenieria.sd.rmi.client.gui;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.client.remote.RMIServiceLocator;
import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;
import es.deusto.ingenieria.sd.rmi.comun.utils.DateUtils;

public class VentanaAlojamientos extends JFrame {
    private JTextField textFieldFechaInicio;
    private JTextField textFieldFechaFin;
    private JTextPane textPaneInfo;
    private JList<String> alojamientosJList;
    private DefaultListModel<String> listModel;
    private JButton btnAceptar;
    private JButton btnPerfil;
    private ServerFacade serverFacade;
    private List<AlojamientoDTO> alojamientos;
    private AlojamientoDTO alojamientoSeleccionado;
    private UsuarioDTO usuarioLogeado;

    public VentanaAlojamientos(UsuarioDTO usuarioLogeado) throws RemoteException {
        this.usuarioLogeado = usuarioLogeado;
        serverFacade = RMIServiceLocator.getInstance().getService();
        alojamientos = serverFacade.obtenerAlojamientos();

        setTitle("Lista de Apartamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        getContentPane().setLayout(null); // Desactiva el layout manager

        // Título
        JLabel lblAlojamientos = new JLabel("ALOJAMIENTOS:");
        lblAlojamientos.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAlojamientos.setBounds(10, 10, 200, 30);
        getContentPane().add(lblAlojamientos);

        // Botón Perfil en la parte superior derecha
        btnPerfil = new JButton("Perfil");
        btnPerfil.setBounds(700, 10, 80, 30);
        getContentPane().add(btnPerfil);

        // Lista de alojamientos
        listModel = new DefaultListModel<>();
        for (AlojamientoDTO alojamiento : alojamientos) {
            listModel.addElement(alojamiento.getNombre());
        }
        alojamientosJList = new JList<>(listModel);
        alojamientosJList.setBounds(10, 50, 350, 400);
        alojamientosJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !alojamientosJList.isSelectionEmpty()) {
                alojamientoSeleccionado = alojamientos.get(alojamientosJList.getSelectedIndex());
                textPaneInfo.setText("<html><b>Descripción:</b> <br/>" + alojamientoSeleccionado.getDescripcion()
                        + "<br/><br/><b>Dirección:</b> <br/>" + alojamientoSeleccionado.getDireccion() + "</html>");
            }
        });

        JScrollPane listScrollPane = new JScrollPane(alojamientosJList);
        listScrollPane.setBounds(10, 50, 350, 400);
        getContentPane().add(listScrollPane);

        // Área de texto con formato HTML para mostrar información
        textPaneInfo = new JTextPane();
        textPaneInfo.setContentType("text/html");
        textPaneInfo.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(textPaneInfo);
        infoScrollPane.setBounds(370, 50, 400, 400);
        getContentPane().add(infoScrollPane);

        // Campos de fecha en una sola fila
        JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
        lblFechaInicio.setBounds(150, 470, 100, 30);
        getContentPane().add(lblFechaInicio);

        textFieldFechaInicio = new JTextField(10);
        textFieldFechaInicio.setBounds(250, 470, 100, 30);
        getContentPane().add(textFieldFechaInicio);

        JLabel lblFechaFin = new JLabel("Fecha Fin:");
        lblFechaFin.setBounds(370, 470, 100, 30);
        getContentPane().add(lblFechaFin);

        textFieldFechaFin = new JTextField(10);
        textFieldFechaFin.setBounds(450, 470, 100, 30);
        getContentPane().add(textFieldFechaFin);

        // Botón Aceptar
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(350, 510, 100, 30);
        getContentPane().add(btnAceptar);

        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPerfil vp = new VentanaPerfil(usuarioLogeado);
                vp.setVisible(true);
                dispose();
            }
        });

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate fechaInicio = DateUtils.parseDate(textFieldFechaInicio.getText());
                LocalDate fechaFin = DateUtils.parseDate(textFieldFechaFin.getText());
                if (alojamientoSeleccionado != null && DateUtils.sonFechasValidas(fechaInicio, fechaFin)) {
                    try {
                        VentanaHabitaciones va = new VentanaHabitaciones(alojamientoSeleccionado, usuarioLogeado, fechaInicio, fechaFin);
                        va.setVisible(true);
                        dispose();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(VentanaAlojamientos.this, "Error de conexión", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        RMIServiceLocator rmiServiceLocator = new RMIServiceLocator(args[0], args[1], args[2]);
        EventQueue.invokeLater(() -> {
            try {
                UsuarioDTO testUsuarioDTO = createTestUsuarioDTO();
                VentanaAlojamientos frame = new VentanaAlojamientos(testUsuarioDTO);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static UsuarioDTO createTestUsuarioDTO() {
        // Crear un objeto de prueba UsuarioDTO para fines de demostración
        UsuarioDTO testUsuarioDTO = new UsuarioDTO();
        testUsuarioDTO.setCorreo("Test Correo");
        return testUsuarioDTO;
    }
}
