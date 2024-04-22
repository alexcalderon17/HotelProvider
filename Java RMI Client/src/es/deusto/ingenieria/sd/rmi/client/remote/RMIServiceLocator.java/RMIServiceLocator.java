package es.deusto.ingenieria.sd.sms.client.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.deusto.ingenieria.sd.rmi.server.IServer;
import es.deusto.ingenieria.sd.rmi.server.InvalidUser;
import es.deusto.ingenieria.sd.rmi.server.facade.ServerFacade;

public class RMIServiceLocator
{
	/** 
	 * The Cache - Limitation: one server at a time
	 * Proposed improvement: list of services
	 */
	
	
  ServerFacade stubServer = null;

    /** Creates a new instance of RMIServiceLocator */
    public RMIServiceLocator() { }

    public void setService(String ip, String port, String serviceName) 
    {    
      try
      {
      Registry registry = LocateRegistry.getRegistry(Integer.parseInt(port));
			String name = "//" + ip + ":" + port + "/" + serviceName;
			this.stubServer = (ServerFacade) registry.lookup(name);
      System.out.println("* Message coming from the server: '");
      }

      catch (Exception e) 
		{
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
    
		}
    	
    }
    
    public ServerFacade getService() 
    {    	
    	return this.stubServer;
    }
}
