package uniandes.edu.co.demo.modelo;

import lombok.ToString;

@ToString
public class Parentesco {
    private String nombre_parentesco;
    private int documento_contribuyente;

    public Parentesco(String nombre_parentesco, int documento_contribuyente){
        this.nombre_parentesco=nombre_parentesco;
        this.documento_contribuyente=documento_contribuyente;
    }

    public String getNombre_parentesco(){
        return nombre_parentesco;
    }
    
    public void setNombre_parentesco(String nombre_parentesco){
        this.nombre_parentesco=nombre_parentesco;
    }

    public int getDocumento_contribuyente(){
        return documento_contribuyente;
    }
    
    public void setDocumento_contribuyente(int docuemnto_contribuyente){
        this.documento_contribuyente=docuemnto_contribuyente;
    }
}
