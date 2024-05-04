package es.deusto.ingenieria.sd.rmi.server.facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.comun.dto.AlojamientoAtributes;
import es.deusto.ingenieria.sd.rmi.server.dto.ApiData;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioAlojamientos;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioUsuario;
import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioAlojamientosImpl;
import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioUsuarioImpl;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class ServerFacadeImpl extends UnicastRemoteObject implements ServerFacade {

    private ServicioAlojamientos servicioAlojamientos;
    private ServicioUsuario servicioUsuario;

    protected ServerFacadeImpl() throws RemoteException {
        super();
        this.servicioAlojamientos = new ServicioAlojamientosImpl();
        this.servicioUsuario = new ServicioUsuarioImpl();
    }

    // @Override
    public List<HabitacionDTO> obtenerHabitaciones() throws RemoteException {

        return servicioAlojamientos.obtenerHabitaciones();
    }

    @Override
    public List<AlojamientoAtributes> obtenerAlojamientos() throws RemoteException {
        return servicioAlojamientos.obtenerAlojamientos();
    }

    @Override
    public boolean iniciarSesion(String usuario, String contrasenya) throws RemoteException {

        throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
    }

    @Override
    public void registrarse(String nombre, String apellido, String DNI, String correo, String telefono, String password)
            throws RemoteException {
        System.out.println("metodo registrarse en ServerfacadeImpl"); //sout
        
        try {
            servicioUsuario.registrarse(nombre, apellido, DNI, correo, telefono, password);
            // Asume que 'servicioHotelProvider.registrarse()' también ha sido actualizado
            // para lanzar RemoteException
        } catch (Exception e) {
            // Manejar otras excepciones que no sean de tipo RemoteException
            throw new RemoteException("Error en registrarse: " + e.getMessage(), e);
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
