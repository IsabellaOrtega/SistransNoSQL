package uniandes.edu.co.demo.modelo;

import lombok.ToString;

@ToString
public class Especialidad {
    private String nombre;

    public Especialidad() { }
    
    public Especialidad(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre= nombre;
    }
}
