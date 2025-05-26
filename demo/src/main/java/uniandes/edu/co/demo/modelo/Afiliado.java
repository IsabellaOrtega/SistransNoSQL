package uniandes.edu.co.demo.modelo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="Afiliado")
@ToString
public class Afiliado {
    @Id
    private String id;
    private int numero_documento;
    private String tipo_documento;
    private String nombres;
    private String apellidos;
    private Date fecha_nacimiento;
    private String direccion;
    private String telefono;
    private String tipo_afiliado;
    private List<Parentesco> parentesco;

    public Afiliado(String tipo_documento, int numero_documento, String nombres, String apellidos, Date fecha_nacimiento, String direccion, String telefono, String tipo_afiliado){
        this.tipo_documento=tipo_documento;
        this.numero_documento=numero_documento;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.fecha_nacimiento=fecha_nacimiento;
        this.direccion=direccion;
        this.telefono=telefono;
        this.tipo_afiliado=tipo_afiliado;
    }

    public String getTipo_documento(){
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento){
        this.tipo_documento=tipo_documento;
    }

    public int getNumero_documento(){
        return numero_documento;
    }

    public void setNumero_documento(int numero_documento){
        this.numero_documento=numero_documento;
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

    public Date getFecha_nacimiento(){
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento){
        this.fecha_nacimiento=fecha_nacimiento;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion=direccion;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono=telefono;
    }

    public String getTipo_afiliado(){
        return tipo_afiliado;
    }

    public void setTipo_afiliado(String tipo_afiliado){
        this.tipo_afiliado=tipo_afiliado;
    }

    public List<Parentesco> getParentesco() {
        return parentesco;
    }
    public void setParentesco(List<Parentesco> parentesco) {
        this.parentesco = parentesco;
    }

}
