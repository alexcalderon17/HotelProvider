package es.deusto.ingenieria.sd.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.deusto.ingenieria.sd.rmi.server.IServer;
import es.deusto.ingenieria.sd.rmi.server.InvalidUser;

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

		IServer stubServer = null;
		/**
		 * Try test message
		 */
		try 
		{
			Registry registry = LocateRegistry.getRegistry(((Integer.valueOf(args[1]))));
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			//stubServer = (IServer) java.rmi.Naming.lookup(name);
			stubServer = (IServer) registry.lookup(name);
			//stubServer.obtenerApartamentos(String url, );
			System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");
			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		
				
		/**
		 * Try registering user
		 */

		try{
			//String url = "https://ds2324.arambarri.eus/api/alojamientos";
			//String token = "0518ee96193abf0dca7b3a46591653eb2b162f3fb2dd6fa681b65b97e3e00243187a1b6839aac73946715fb62719b12a1eb14afc36018935b935c2dbf293448fc98a5cde5a219fc208a3db97489b2c2c479825f212d87658ff3b369e4951b0b3f101ac8d52330262e60846ae80b45b6799c69371e4f47a548053137ada4ec6e5";
			String apartamentos = stubServer.obtenerApartamentos();
			System.out.println(apartamentos);
		}catch(Exception e){
			System.out.println("No se han podido imprimir los apartamentos.");
		}
		try
		{			

			stubServer.registerUser("Test1", "Test1");
			System.out.println("* Added user Test1");
			
			stubServer.registerUser("Test2", "Test2");
			System.out.println("* Added user Test2");
			
			stubServer.registerUser("Test3", "Test3");
			System.out.println("* Added user Test3");
			
			stubServer.registerUser("Test3", "Test3");
			System.out.println("* Added user Test3");
		}
		catch (InvalidUser iu)
		{
			System.err.println("- Exception running the client: " + iu.getErrorMessage());
		}
		catch (Exception e)
		{
			System.err.println("- Exception running the client: " + e.getMessage());
		}
		
		
		
		/**
		 * Try say message
		 */
		try
		{
			System.out.println("* Message coming from the server: " + stubServer.sayMessage("Test1", "Test1", "Message 1"));
			System.out.println("* Message coming from the server: " + stubServer.sayMessage("Test2", "Test2", "Message 2"));
			System.out.println("* Message coming from the server: " + stubServer.sayMessage("Test3", "Test3", "Message 3"));
			System.out.println("* Message coming from the server: " + stubServer.sayMessage("Test3", "Test4", "Message 4"));
			System.out.println("* Message coming from the server: " + stubServer.sayMessage("Test4", "Test4", "Message 5"));
		}
		catch (InvalidUser iu)
		{
			System.err.println("- Exception running the client: " + iu.getErrorMessage());
		}
		catch (Exception e)
		{
			System.err.println("- Exception running the client: " + e.getMessage());
		}
		
	}
}