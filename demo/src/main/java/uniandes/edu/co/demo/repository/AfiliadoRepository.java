package uniandes.edu.co.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.demo.modelo.Afiliado;
import uniandes.edu.co.demo.modelo.Parentesco;

public interface AfiliadoRepository extends MongoRepository<Afiliado, Integer> {

    // Consultar todos los afiliados
    @Query(value = "{}", fields = "{ 'parentesco': 0 }")
    List<Afiliado> buscarTodosLosAfiliados();

    // Consultar afiliado por número de documento
    @Query("{'numero_documento': ?0}")
    List<Afiliado> buscarAfiliadoPorNumeroDocumento(int numeroDocumento);

    // Insertar un nuevo afiliado
    default void insertarAfiliado(Afiliado afiliado) {
        save(afiliado);
    }

    // Actualizar afiliado
    @Query(value = "{'numero_documento': ?0}")
    @Update("{ $set: { tipo_documento: ?1, nombres: ?2, apellidos: ?3, fecha_nacimiento:?4, direccion: ?5, telefono: ?6, tipo_afiliado: ?7, parentesco: ?8 } }")
    void actualizarAfiliado(int numeroDocumento, String tipoDocumento, String nombres, String apellidos, String fechaNacimiento, String direccion, String telefono, String tipoAfiliado, String parentesco);

    // Eliminar afiliado por número de documento
    @Query(value = "{'numero_documento': ?0}", delete = true)
    void eliminarAfiliadoPorNumeroDocumento(int numeroDocumento);

    @Query(value = "{'numero_documento': ?0}", fields = "{'parentesco': 1}")
    List<Parentesco> buscarParentescoPorNumeroDocumento(int numeroDocumento);
    
}
