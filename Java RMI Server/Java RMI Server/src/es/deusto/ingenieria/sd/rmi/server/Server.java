//aqui vamos a implementar los metodos
package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.*;
import java.util.HashMap;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

//import ApiClient;
//import Server; //netstat ano | findstr :port

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private HashMap <String, String> registeredUsers = null;
	

	protected Server() throws RemoteException 
	{
		super();
		registeredUsers = new HashMap<String, String> ();
		//this.conexionAPI = new ConexionAPI();
	}


	//este metodo coge datos de una API que Unai nos ha proporcionado
	@Override
	public String obtenerApartamentos()
	{
		 String respuesta = null;
       	String url = "https://ds2324.arambarri.eus/api/alojamientos";
       	String token = "0518ee96193abf0dca7b3a46591653eb2b162f3fb2dd6fa681b65b97e3e00243187a1b6839aac73946715fb62719b12a1eb14afc36018935b935c2dbf293448fc98a5cde5a219fc208a3db97489b2c2c479825f212d87658ff3b369e4951b0b3f101ac8d52330262e60846ae80b45b6799c69371e4f47a548053137ada4ec6e5";

		
		 try{
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization", "Bearer " + token)
                .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){ //200 Exitoso
            respuesta = response.body();
            return respuesta;
        }else{
            System.out.println("error codigo:" + response.statusCode());
        }
        }catch(InterruptedException | URISyntaxException | IOException e) {
            System.out.println("Error en solicitud");
        }
        return respuesta;
        
		/*try{
			//String url = "https://ds2324.arambarri.eus/api/alojamientos";
			//String token = "0518ee96193abf0dca7b3a46591653eb2b162f3fb2dd6fa681b65b97e3e00243187a1b6839aac73946715fb62719b12a1eb14afc36018935b935c2dbf293448fc98a5cde5a219fc208a3db97489b2c2c479825f212d87658ff3b369e4951b0b3f101ac8d52330262e60846ae80b45b6799c69371e4f47a548053137ada4ec6e5";
			String apiResponse = ApiClient.getApiResponse(url, token);
			return apiResponse;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;*/
	}
	

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		//if (System.getSecurityManager() == null) {
		//	System.setSecurityManager(new SecurityManager());
		//}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try 
		{	
			// está configurando un servidor RMI, registrándolo en un registro local para que
			// los clientes puedan encontrarlo y preparándolo para aceptar y manejar llamadas 
			//de métodos remotos de dichos clientes.
			IServer objServer = new Server();
			Registry registry = LocateRegistry.createRegistry((Integer.valueOf(args[1])));
			//Naming.rebind(name, objServer);
			registry.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");			
		} 
		catch (Exception e) 
		{
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
		

	}
	
}