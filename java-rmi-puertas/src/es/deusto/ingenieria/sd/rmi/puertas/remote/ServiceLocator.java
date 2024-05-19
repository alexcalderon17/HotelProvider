package es.deusto.ingenieria.sd.rmi.puertas.remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.deusto.ingenieria.sd.rmi.comun.facade.ServerFacade;

public class ServiceLocator {

  //no pertenece a la instancia pertenece a la clase
  private static ServiceLocator INSTANCE;
  
  ServerFacade serverFacade = null;

  public ServiceLocator(String ip, String port, String serviceName) {
    INSTANCE = this;
    this.setService(ip, port, serviceName);
  }

  public void setService(String ip, String port, String serviceName) {
    try {
      Registry registry = LocateRegistry.getRegistry(Integer.parseInt(port));
      String name = "//" + ip + ":" + port + "/" + serviceName;
      this.serverFacade = (ServerFacade) registry.lookup(name);
      System.out.println("* Message coming from the server: '");
    }

    catch (Exception e) {
      System.err.println("- Exception running the client: " + e.getMessage());
      e.printStackTrace();

    }

  }

  public ServerFacade getService() {
    return this.serverFacade;
  }


  public static ServiceLocator getInstance(){
    if (INSTANCE == null){
      throw new RuntimeException("ServiceLocator nunca ha sido instanciado");
    }
    return INSTANCE;
  }
}
