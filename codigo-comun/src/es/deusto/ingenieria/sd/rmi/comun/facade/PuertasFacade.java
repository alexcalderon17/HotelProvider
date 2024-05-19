package es.deusto.ingenieria.sd.rmi.comun.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PuertasFacade extends Remote {

    void abrirPuerta(String codigoReserva) throws RemoteException;

}
