package es.deusto.ingenieria.sd.rmi.server.facade;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.ingenieria.sd.rmi.comun.facade.PuertasFacade;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioPuertas;
import es.deusto.ingenieria.sd.rmi.server.servicios.ServicioUsuario;


public class PuertasFacadeImpl extends UnicastRemoteObject implements PuertasFacade{
     private ServicioPuertas servicioPuertas;

    protected PuertasFacadeImpl() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void abrirPuerta(String codigoReserva) throws RemoteException {
        try {
            servicioPuertas.abrirPuerta(codigoReserva);;
            // Asume que 'servicioHotelProvider.registrarse()' también ha sido actualizado
            // para lanzar RemoteException
        } catch (Exception e) {
            // Manejar otras excepciones que no sean de tipo RemoteException
            throw new RemoteException("Error al abrir la puertas: " + e.getMessage(), e);
        }
    }
    
}
