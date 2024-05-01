package es.deusto.ingenieria.sd.rmi.comun.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlojamientoAtributes  implements Serializable{ 

    private String nombre;
    private String descripcion;
    private String direccion;
    
    public AlojamientoAtributes() {
    }

    public AlojamientoAtributes(String nombre, String descripcion, String direccion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
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
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
