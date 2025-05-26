package uniandes.edu.co.demo.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="Cita")
@ToString
public class Cita {
    
    @Id
    private int id;
    
    private int id_servicio;
    private int numero_documento_afiliado;
    private Date fecha_hora;
    private int id_orden_servicio;

    public Cita(int id, int id_servicio, int numero_documento_afiliado, Date fecha_hora, int id_orden_servicio) {
        this.id = id;
        this.id_servicio = id_servicio;
        this.numero_documento_afiliado = numero_documento_afiliado;
        this.fecha_hora = fecha_hora;
        this.id_orden_servicio = id_orden_servicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getNumero_documento_afiliado() {
        return numero_documento_afiliado;
    }

    public void setNumero_documento_afiliado(int numero_documento_afiliado) {
        this.numero_documento_afiliado = numero_documento_afiliado;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getId_orden_servicio() {
        return id_orden_servicio;
    }

    public void setId_orden_servicio(int id_orden_servicio) {
        this.id_orden_servicio = id_orden_servicio;
    }

    

}
