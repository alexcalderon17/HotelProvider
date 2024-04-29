package es.deusto.ingenieria.sd.rmi.server.facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import es.deusto.ingenieria.sd.rmi.server.dto.AlojamientoDTO;
import es.deusto.ingenieria.sd.rmi.server.dto.HabitacionDTO;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioAlojamientos;
import es.deusto.ingenieria.sd.rmi.server.servicios.impl.ServicioAlojamientosImpl;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class ServerFacadeImpl extends UnicastRemoteObject implements ServerFacade {

    private ServicioAlojamientos servicioHotelProvider;

    protected ServerFacadeImpl() throws RemoteException {
        super();
        this.servicioHotelProvider = new ServicioAlojamientosImpl();
        // TODO Auto-generated constructor stub
    }

    // @Override
    public List<HabitacionDTO> obtenerHabitaciones() throws RemoteException {
        // estamos llamando al metodo que de verdad lo hace (en la carpeta servicios)
        return servicioHotelProvider.obtenerHabitaciones();
    }

    @Override
    public String obtenerAlojamientos() throws RemoteException {
        return servicioHotelProvider.obtenerAlojamientos();
    }

    @Override
    public boolean iniciarSesion(String usuario, String contrasenya) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
    }

    @Override
    public void registrarse(String usuario, String contrasenya) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarse'");
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
            System.exit(0);
        }
        // if (System.getSecurityManager() == null) {
        // System.setSecurityManager(new SecurityManager());
        // }
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
        try {
            // está configurando un servidor RMI, registrándolo en un registro local para
            // que
            // los clientes puedan encontrarlo y preparándolo para aceptar y manejar
            // llamadas
            // de métodos remotos de dichos clientes.
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
