//en esta clase vamos a declarar los métodos
package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {// soloo metodos que devuelven super. No creaciónd e clases.

	/*
	 * String CancelarReserva (String ReservaID) throws RemoteException;
	 * 
	 * boolean Registrarse(String nombre, String apellidos, String DNI, String
	 * correoElectronico, String telefono, String password, int codPostal) throws
	 * RemoteException;
	 * 
	 * boolean IniciarSesion(String correoElectronico, String contraseña) throws
	 * RemoteException;
	 * 
	 * boolean ModificarDatosUsuario (String nombre, String apellidos, String DNI,
	 * String correoElectrónico, String teléfono, String password, int codPostal)
	 * throws RemoteException;
	 * 
	 * boolean ValidarAcceso (String CodigoQR) throws RemoteException;
	 * 
	 * boolean AñadirReseña (String usuarioID, String alojamientoID, int
	 * calificacion, String comentario) throws RemoteException;
	 * 
	 * //double CalcularEstrellasPromedio (String alojamientoID) throws
	 * RemoteException;
	 * 
	 * //boolean ModificarReserva (String reservaID, Date nuevaFechaInicio, Date
	 * nuevaFechaFin) throws RemoteException;
	 */

	String obtenerApartamentos() throws RemoteException;
}