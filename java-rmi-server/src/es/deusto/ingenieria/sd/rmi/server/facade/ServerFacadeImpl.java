package es.deusto.ingenieria.sd.rmi.server.facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.ReservaDTO;
import es.deusto.ingenieria.sd.rmi.comun.dto.UsuarioDTO;

import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiHabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.exceptions.ErrorCreacionQr;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioUsuario;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioAlojamientos;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioPuertas;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioReserva;

import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioAlojamientosImpl;
import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioPuertasImpl;
import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioUsuarioImpl;
import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioReservaImpl;

import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class ServerFacadeImpl extends UnicastRemoteObject implements ServerFacade {

    private ServicioUsuario servicioUsuario;
    private ServicioReserva servicioReserva;
    private ServicioAlojamientos servicioAlojamientos;
    private ServicioPuertas servicioPuertas;

    protected ServerFacadeImpl() throws RemoteException {
        super();
        this.servicioAlojamientos = new ServicioAlojamientosImpl();
        this.servicioUsuario = new ServicioUsuarioImpl();
        this.servicioReserva = new ServicioReservaImpl();
        this.servicioPuertas = new ServicioPuertasImpl();

    }

    @Override
    public List<HabitacionDTO> obtenerHabitaciones(int IdAlojamientoSeleccionado, String fechaIni, String fechaFin)
            throws RemoteException {
        LocalDate fechaInicio = LocalDate.parse(fechaIni);
        LocalDate fechaFinal = LocalDate.parse(fechaFin);

        return servicioAlojamientos.obtenerHabitaciones(IdAlojamientoSeleccionado, fechaInicio, fechaFinal);
    }

    @Override
    public List<AlojamientoDTO> obtenerAlojamientos() throws RemoteException {
        return servicioAlojamientos.obtenerAlojamientos();
    }

    @Override
    public UsuarioDTO iniciarSesion(String usuario, String contrasenya) throws RemoteException {
        System.out.println("metodo iniciar sesion en ServerfacadeImpl"); // sout
        try {
            return servicioUsuario.iniciarSesion(usuario, contrasenya);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void registrarse(UsuarioDTO usuarioDTO) throws RemoteException {
        System.out.println("metodo registrarse en ServerfacadeImpl"); // sout

        try {
            servicioUsuario.registrarse(usuarioDTO);
            // Asume que 'servicioHotelProvider.registrarse()' también ha sido actualizado
            // para lanzar RemoteException
        } catch (Exception e) {
            // Manejar otras excepciones que no sean de tipo RemoteException
            throw new RemoteException("Error en registrarse: " + e.getMessage(), e);
        }
    }

    @Override
    public void guardarReserva(ReservaDTO reservaDTO, UsuarioDTO usuarioDTO) throws RemoteException {
        System.out.println("metodo guardarReserva en ServerfacadeImpl"); // sout
        try {
            servicioReserva.guardarReserva(reservaDTO, usuarioDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error al crear la reserva");
        }
    }

    @Override
    public byte[] abrirPuerta(String codigoReserva) throws RemoteException {
        try {
            return servicioPuertas.abrirPuerta(codigoReserva);
        } catch (Exception e) {
            // Manejar otras excepciones que no sean de tipo RemoteException
            throw new RemoteException("Error al abrir la puertas: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }

        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
        try {

            ServerFacade serverFacade = new ServerFacadeImpl();
            Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
            registry.rebind(name, serverFacade);
            System.out.println("* Server '" + name + "' active and waiting...");
        } catch (Exception e) {
            System.err.println("- Exception running the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
