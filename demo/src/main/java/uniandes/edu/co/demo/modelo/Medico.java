package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="Medico")
@ToString
public class Medico {
    @Id
    private String id;
    private int numero_registro_medico;
    private String nombres;
    private String apellidos;
    private String tipo_documento;
    private int numero_documento;
    private List<Especialidad> especialidad;

    public Medico() {}

    public Medico(String nombres, String apellidos, String tipo_documento, int numero_documento, int numero_registro_medico){
        this.nombres=nombres;
        this.apellidos = apellidos;
        this.tipo_documento = tipo_documento;
        this.numero_documento = numero_documento;
        this.numero_registro_medico=numero_registro_medico;
    }

    public String getNombres(){
        return nombres;
    }

    public void setNombres(String nombres){
        this.nombres=nombres;
    }

    public String getApellidos(){
        return apellidos;
    }

    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }

    public String getTipos_documento(){
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento){
        this.tipo_documento=tipo_documento;
    }

    public int getNumero_documento(){
        return numero_documento;
    }

    public void setNumero_documento(int numero_documento){
        this.numero_documento = numero_documento;
    }
    
    public int getNumero_registro_medico(){
        return numero_registro_medico;
    }

    public void setNumero_registro_medico(int numero_registro_medico){
        this.numero_registro_medico = numero_registro_medico;
    }

    public List<Especialidad> getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(List<Especialidad> especialidad) {
        this.especialidad = especialidad;
    }



    
}
