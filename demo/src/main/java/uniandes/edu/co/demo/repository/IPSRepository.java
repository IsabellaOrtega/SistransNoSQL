package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.IPS;

public interface IPSRepository extends MongoRepository<IPS, String> {

    // Consultar todas las IPS
    @Query(value = "{}")
    List<IPS> BuscarTodasLasIPS();

    // Consultar IPS por NIT
    @Query(value = "{'NIT': ?0}")
    List<IPS> BuscarIPSPorNIT(String NIT);

    // Insertar una nueva IPS
    default void insertarIPS(IPS ips) {
        save(ips);
    }

     // Actualizar IPS
    @Query(value = "{'NIT': ?0}")
    @Update("{ $set: {  nombre: ?1, direccion: ?2,telefono: ?3 } }")
    void actualizarIPS(String NIT, String nombre, String direccion, String telefono);

    // Eliminar IPS por NIT
    @Query(value = "{'NIT': ?0}", delete = true)
    void eliminarIPSPorNIT(String NIT);

    
}
