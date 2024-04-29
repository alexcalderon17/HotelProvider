package es.deusto.ingenieria.sd.rmi.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PuertasServer extends UnicastRemoteObject implements IPuertasServer {

    private static final long serialVersionUID = 1L;

     public PuertasServer() throws RemoteException {
        super();
    }

}