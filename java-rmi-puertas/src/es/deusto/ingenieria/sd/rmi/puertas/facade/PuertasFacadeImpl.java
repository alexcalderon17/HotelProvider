package es.deusto.ingenieria.sd.rmi.puertas.facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.ingenieria.sd.rmi.comun.facade.PuertasFacade;

public class PuertasFacadeImpl extends UnicastRemoteObject implements PuertasFacade  {

    protected PuertasFacadeImpl() throws RemoteException {
        super();
        
    }

    @Override
    public boolean abrirPuerta(int numeroPuerta) throws RemoteException {
        System.out.println("Abriendo puerta");
        return true;
    }

    public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}
        //if (System.getSecurityManager() == null) {
			//System.setSecurityManager(new SecurityManager());
		//}
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		try {	
			
			PuertasFacade serverFacade = new PuertasFacadeImpl();
			Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
			registry.rebind(name, serverFacade);
			System.out.println("* Server '" + name + "' active and waiting...");			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
