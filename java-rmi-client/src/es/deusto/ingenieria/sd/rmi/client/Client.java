package es.deusto.ingenieria.sd.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.deusto.ingenieria.sd.rmi.server.IServer;
import es.deusto.ingenieria.sd.rmi.server.InvalidUser;
import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) 
		{
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}

		//if (System.getSecurityManager() == null) 
		//{
		//	System.setSecurityManager(new SecurityManager());
		//}

		ServerFacade stubServer = null;
		/**
		 * Try test message
		 */
		try 
		{
			Registry registry = LocateRegistry.getRegistry(((Integer.valueOf(args[1]))));
			// puerto, ip y server name
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			//stubServer = (IServer) java.rmi.Naming.lookup(name);
			// busca un servidor que tenga ese puerto, esa ip y ese server name
			stubServer = (ServerFacade) registry.lookup(name);
			//stubServer.obtenerApartamentos(String url, );
			System.out.println("* Message coming from the server: '");
			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	
		try{
			String apartamentos = stubServer.obtenerAlojamientos();
			System.out.println(apartamentos);
		}catch(Exception e){
			System.out.println("No se han podido imprimir los apartamentos.");
		}
		
		
		
	}
}