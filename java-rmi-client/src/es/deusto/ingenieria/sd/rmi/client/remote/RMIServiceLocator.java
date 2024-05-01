package es.deusto.ingenieria.sd.rmi.client.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class RMIServiceLocator {

  private static RMIServiceLocator INSTANCE;
  

  ServerFacade stubServer = null;

 
  public RMIServiceLocator(String ip, String port, String serviceName) {
    INSTANCE = this;
    this.setService(ip, port, serviceName);

  }

  public void setService(String ip, String port, String serviceName) {
    try {
      Registry registry = LocateRegistry.getRegistry(Integer.parseInt(port));
      String name = "//" + ip + ":" + port + "/" + serviceName;
      this.stubServer = (ServerFacade) registry.lookup(name);
      System.out.println("* Message coming from the server: '");
    }

    catch (Exception e) {
      System.err.println("- Exception running the client: " + e.getMessage());
      e.printStackTrace();

    }

  }

  public ServerFacade getService() {
    return this.stubServer;
  }

  public static RMIServiceLocator getInstance(){
    if (INSTANCE == null){
      throw new RuntimeException("RMIServiceLocator nunca ha sido instanciado");
    }
    return INSTANCE;
  }
}
