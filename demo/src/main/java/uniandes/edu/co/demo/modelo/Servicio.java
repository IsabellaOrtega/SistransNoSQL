package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="Servicio")
@ToString
public class Servicio {
    @Id
    private int id;
    private String nombre;
    private String tipo_servicio;
    private boolean requiere_orden;
    private String nit_ips;
    private List<Disponibilidad> disponibilidad;

    public Servicio() {
    }

    public Servicio(int id, String nombre, String tipo_servicio, boolean requiere_orden, String nit_ips) {
        this.id = id;
        this.nombre = nombre;
        this.tipo_servicio = tipo_servicio;
        this.requiere_orden = requiere_orden;
        this.nit_ips = nit_ips;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo_servicio() {
        return tipo_servicio;
    }
    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }
    public boolean isRequiere_orden() {
        return requiere_orden;
    }
    public void setRequiere_orden(boolean requiere_orden) {
        this.requiere_orden = requiere_orden;
    }
    public String getNit_ips() {
        return nit_ips;
    }
    public void setNit_ips(String nit_ips) {
        this.nit_ips = nit_ips;
    }
    public List<Disponibilidad> getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(List<Disponibilidad> disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
