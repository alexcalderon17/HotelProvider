package es.deusto.ingenieria.sd.sms.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.sms.client.gui.SMSWindow;
import es.deusto.ingenieria.sd.sms.client.gui.SwitchServerGUI;
import es.deusto.ingenieria.sd.sms.client.remote.RMIServiceLocator;

public class SMSController 
{
	
	private RMIServiceLocator rsl = null;
	
	public SMSController(String[] args) throws RemoteException 
	{		
		
        SMSWindow ventana = new SMSWindow(this);
        ventana.setVisible(true);

       // SwitchServerGUI ventana1 = new SwitchServerGUI(this);
        //ventana1.setVisible(true);

        this.rsl = new RMIServiceLocator();
        this.rsl.setService(arg[0],arg[1],arg[2]);
	}

    public List<Alojamiento> getAlojamientos(){
        return this.rsl.getService().getAlojamientos();

    }
	
    public void exit(){
    	System.exit(0);
    }
    
    public static void main(String[] args) throws RemoteException {    	
    	new SMSController(args);
    }
}