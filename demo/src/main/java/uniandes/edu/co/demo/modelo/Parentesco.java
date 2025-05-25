package uniandes.edu.co.demo.modelo;

import lombok.ToString;

@ToString
public class Parentesco {
    private String nombre_parentesco;

    public Parentesco(String nombre_parentesco){
        this.nombre_parentesco=nombre_parentesco;
    }

    public String getNombre_parentesco(){
        return nombre_parentesco;
    }
    
    public void setNombre_parentesco(String nombre_parentesco){
        this.nombre_parentesco=nombre_parentesco;
    }
}
