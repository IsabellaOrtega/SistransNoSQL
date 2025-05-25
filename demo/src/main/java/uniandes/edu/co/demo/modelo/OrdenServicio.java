package uniandes.edu.co.demo.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="OrdenServicio")
@ToString
public class OrdenServicio {
    @Id
    private int id;
    private Date fecha;
    private String estado;

    private int numero_documento_afiliado;
    private int numero_registro_medico;

    public OrdenServicio(int id, Date fecha, String estado, int numero_documento_afiliado, int numero_registro_medico){
        this.id= id;
        this.fecha=fecha;
        this.estado=estado;
        this.numero_documento_afiliado=numero_documento_afiliado;
        this.numero_registro_medico=numero_registro_medico;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha=fecha;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado=estado;
    }

    public int getNumero_documento_afiliado(){
        return numero_documento_afiliado;
    }

    public void setNumero_documento_afiliado(int numero_documento_afiliado){
        this.numero_documento_afiliado=numero_documento_afiliado;
    }

    public int getNumero_registro_medico(){
        return numero_registro_medico;
    }

    public void setNumero_registro_medico(int numero_registro_medico){
        this.numero_registro_medico = numero_registro_medico;
    }
}
