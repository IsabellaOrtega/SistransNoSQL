package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Especialidad;
import uniandes.edu.co.demo.modelo.Medico;

public interface MedicoRepository extends MongoRepository<Medico, Integer> {

    // Consultar todos los médicos
    @Query(value = "{}", fields = "{ 'especialidad': 0 }")
    List<Medico> buscarTodosLosMedicos();

    // Consultar médico por documento
    @Query("{'documento': ?0}")
    Medico buscarMedicoPorDocumento(int documento);

    // Insertar un nuevo médico
    default void insertarMedico(Medico medico) {
        save(medico);
    }

    // Actualizar médico
    @Query(value = "{'documento': ?0}")
    @Update("{ $set: { nombre: ?1, apellidos: ?2, tipo_documento: ?3, numero_documento: ?4, especialidad: ?5 } }")
    void actualizarMedico(int numero_registro_medico, String nombre, String apellidos, String tipo_documento, int numero_documento, List<String> especialidad);

    // Eliminar médico por documento
    @Query(value = "{'documento': ?0}", delete = true)
    void eliminarMedicoPorDocumento(int documento);

    // Consultar médicos por especialidad
    @Query(value = "{'documento': ?0}", fields = "{ 'especialidad': 1 }")
    List<Especialidad> buscarMedicosPorEspecialidad(String especialidad);
    
}
