package uniandes.edu.co.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="IPS")
@ToString
public class IPS {
    @Id
    private String NIT;
    private String nombre;
    private String direccion;
    private String telefono;

    public IPS(String NIT, String nombre, String direccion, String telefono) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNIT() {
        return NIT;
    }
    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
