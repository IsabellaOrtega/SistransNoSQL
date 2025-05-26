package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.OrdenServicio;

public interface OrdenServicioRepository extends MongoRepository<OrdenServicio, Integer> {

        @Query(value = "{}")
        List<OrdenServicio> buscarTodasLasOrdenes();

        @Query(value = "{'_id': ?0}")
        List<OrdenServicio> buscarOrdenPorId(int id);

        default void insertarOrden(OrdenServicio orden) {
                save(orden);
        }

        @Query(value = "{'_id': ?0}")
        @Update("{ $set: { fecha: ?1, estado: ?2, id_servicio: ?3, numero_documento_afiliado: ?4, numero_registro_medico: ?5 } }")
        void actualizarOrden(int id, String fecha, String estado, int id_servicio, int numero_documento_afiliado, int numero_registro_medico);

        @Query(value = "{'_id': ?0}", delete = true)
        void eliminarOrdenPorId(int id);

        

}
