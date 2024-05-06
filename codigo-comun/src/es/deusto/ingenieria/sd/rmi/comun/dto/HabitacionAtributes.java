package es.deusto.ingenieria.sd.rmi.comun.dto;

import java.io.Serializable;

public class HabitacionAtributes implements Serializable {
    private String nombre;
    private String descripcion;
    private int aforo;
    
    public HabitacionAtributes() {
    }

    public HabitacionAtributes(String nombre, String descripcion, int aforo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aforo = aforo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getAforo() {
        return aforo;
    }
    public void setAfoto(int aforo) {
        this.aforo = aforo;
    }

    

    
}
