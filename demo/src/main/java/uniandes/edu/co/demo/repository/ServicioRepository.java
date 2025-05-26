package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Disponibilidad;
import uniandes.edu.co.demo.modelo.Servicio;

public interface ServicioRepository extends MongoRepository<Servicio, Integer> {

    // Consultar todos los servicios
    @Query(value = "{}", fields = "{ 'disponibilidad': 0 }")
    List<Servicio> buscarTodosLosServicios();

    // Consultar servicio por ID
    @Query("{'_id': ?0}")
    List<Servicio> buscarServicioPorId(int id);

    // Insertar un nuevo servicio
    default void insertarServicio(Servicio servicio) {
        save(servicio);
    }

    // Actualizar servicio
    @Query(value = "{'_id': ?0}")
    @Update("{ $set: { nombre: ?1, tipo_servicio: ?2, requiere_orden: ?3, nit_ips: ?4, disponibilidad: ?6 } }")
    void actualizarServicio(int id, String nombre, String tipo_servicio, boolean requiere_orden, String nit_ips, List<Disponibilidad> disponibilidad);

    // Eliminar servicio por ID
    @Query(value = "{'_id': ?0}", delete = true)
    void eliminarServicioPorId(int id);

    // Obtener disponibilidad de un servicio por ID
    @Query(value = "{'_id': ?0}", fields = "{ 'disponibilidad': 1 }")
    List<Disponibilidad> obtenerDisponibilidadPorServicio(int id);
    
}
