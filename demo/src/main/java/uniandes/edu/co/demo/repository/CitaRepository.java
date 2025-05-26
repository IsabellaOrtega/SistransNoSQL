package uniandes.edu.co.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Cita;

public interface CitaRepository extends MongoRepository<Cita, Integer> {

    // Consultar todas las citas
    @Query(value = "{}")
    List<Cita> buscarTodasLasCitas();

    // Consultar cita por ID
    @Query("{'_id': ?0}")
    List<Cita> buscarCitaPorId(int id);

    // Insertar una nueva cita
    default void insertarCita(Cita cita) {
        save(cita);
    }

    // Actualizar cita
    @Query(value = "{'_id': ?0}")
    @Update("{ $set: { id_servicio: ?1, numero_documento_afiliado: ?2, fecha_hora: ?3, id_orden_servicio: ?4 } }")
    void actualizarCita(int id, int id_servicio, int numero_documento_afiliado, Date fecha_hora, int id_orden_servicio);

    // Eliminar cita por ID
    @Query(value = "{'_id': ?0}", delete = true)
    void eliminarCitaPorId(int id);
    
    @Query("{ 'id_servicio': ?0, 'fecha_hora': { $gte: ?1, $lte: ?2 } }")
        List<Cita> buscarPorServicioYFecha(int idServicio, Date desde, Date hasta);
}
